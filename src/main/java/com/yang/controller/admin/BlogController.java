package com.yang.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.deploy.net.HttpRequest;
import com.yang.pojo.Blog;
import com.yang.pojo.Tag;
import com.yang.pojo.Type;
import com.yang.pojo.User;
import com.yang.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/blogs")
public class BlogController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    BlogServiceImpl blogService;
    @Autowired
    TypeServiceimpl typeServiceimpl;
    @Autowired
    TagServiceImpl tagService;
    @Autowired
    RedisServiceImpl redisService;

    @RequestMapping({"","/"})
    public String blog(RedirectAttributes redirectAttributes){
        int current = 1;
        int size =5;
        HashMap<String, Object> hashMap = blogService.listallBlog(current, size);
        redirectAttributes.addFlashAttribute("total",hashMap.get("total"));
        redirectAttributes.addFlashAttribute("list",hashMap.get("list"));
        List<Type> type = typeServiceimpl.listType();
        HashMap<Long,String> types = new HashMap<Long, String>();
        for (Type type1 : type) {
            types.put(type1.getId(),type1.getName());
        }
        redirectAttributes.addFlashAttribute("types",types);
        return "redirect:/admin/main";
    }

    @RequestMapping("/input")
    public String input(HttpSession session, Model model, HttpServletRequest request){
        String loginUser = (String)request.getSession().getAttribute("loginUser");
        User user = userService.Getuser(loginUser);
        List<Type> types = typeServiceimpl.listType();
        List<Tag> tags = tagService.listTag();
        model.addAttribute("user",user);
        model.addAttribute("types",types);
        model.addAttribute("tags", tags);
        model.addAttribute("blog", new Blog());
        return "admin/publishblog";
    }

    @RequestMapping("/search")
    public Object search(String title, Long typeId, String recommend,Model model) throws Exception{
        Map<String,Object> res= new HashMap<>();
        List<Type> type = typeServiceimpl.listType();
        HashMap<Long,String> types = new HashMap<Long, String>();
        for (Type type1 : type) {
            types.put(type1.getId(),type1.getName());
        }
        System.out.println(title);
        System.out.println(typeId);
        System.out.println(recommend);
        title = title.equals("")?null:title;

        int r = recommend==null?0:1;
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("title",title);
        hashMap.put("type",typeId);
        hashMap.put("recommend",r);
        HashMap<String, Object> map = blogService.pageBlog(1, 5, hashMap);
        System.out.println(map.get("total"));
        System.out.println(map.get("list"));

        model.addAttribute("total",map.get("total"));
        model.addAttribute("list",map.get("list"));
        model.addAttribute("types",types);

        return "admin/blogs";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id")long id){
        blogService.deleteBlog(id);
        redisService.removeView(id);
        return "redirect:/admin/blogs";
    }
    @PostMapping("/publish")
    public String publish(Blog blog, HttpServletRequest request){
        long userid = (long)request.getSession().getAttribute("userid");
        System.out.println(blog);
        blog.setUser(userid);
        blogService.saveBlog(blog);
        //发布博客的时候存储views
        redisService.CreateView(blog.getId());
        return "redirect:/admin/blogs";
    }
    @RequestMapping("/update/{id}")
    public String update(@PathVariable("id")long id,Model model) {
        System.out.println("编辑博客："+id);
        Blog blog = blogService.getBlog(id);
       /* String tagids = blog.getTagids();
        if(tagids!=null){
            String[] strings = tagids.split(",");
            Long[] ids = new  Long[strings.length];
            for (int i = 0; i < strings.length; i++) {
                ids[i] = Long.valueOf(strings[i]);
            }
        }else {
            Long[] ids = {};
        }*/

        model.addAttribute("blog",blog);
        List<Type> type = typeServiceimpl.listType();
        HashMap<Long,String> types = new HashMap<Long, String>();
        for (Type type1 : type) {
            types.put(type1.getId(),type1.getName());
        }
        List<Tag> tag = tagService.listTag();
        HashMap<Long,String> tags = new HashMap<Long, String>();
        for (Tag tag1 : tag) {
            tags.put(tag1.getId(),tag1.getName());
        }

        model.addAttribute("types",types);
        model.addAttribute("tags", tags);

        return "admin/updateblog";
    }

    @RequestMapping("/updateblog")
    public String update(Blog blog,HttpServletRequest request) {
        System.out.println("更新博客信息：");
        System.out.println(blog);
        long userid = (long)request.getSession().getAttribute("userid");
        blog.setUser(userid);
        blogService.updateBlog(blog);
        return "redirect:/admin/blogs";
    }
}
