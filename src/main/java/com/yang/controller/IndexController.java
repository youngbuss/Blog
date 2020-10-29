package com.yang.controller;

import com.yang.NotFoundException;
import com.yang.pojo.Blog;
import com.yang.pojo.Tag;
import com.yang.pojo.Type;
import com.yang.pojo.User;
import com.yang.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    BlogServiceImpl blogService;
    @Autowired
    TypeServiceimpl typeServiceimpl;
    @Autowired
    TagServiceImpl tagService;
    @Autowired
    UserServiceImpl userService;

    static  final  int size = 2;
    public Model GetDatas(Model model){

        //获取全部分类
        List<Type> typess = typeServiceimpl.listType();
        List<Type> types  =typeServiceimpl.listSomeType();
        HashMap<Long,String> map = new HashMap<>();
        for (Type type : typess) {
            map.put(type.getId(),type.getName());
        }
        //获取全部标签
        List<Tag> tags = tagService.listSomeTag();
        //获取全部作者
        List<User> users = userService.getallusers();
        HashMap<Long,User> usermap = new HashMap<>();
        for (User user : users) {
            usermap.put(user.getId(),user);
        }

        //获得推荐文章 按时间排序
        List<Blog> blogs = blogService.getbyTimeDesc();
        //封装数据

        model.addAttribute("types",types);
        model.addAttribute("typemap",map);
        model.addAttribute("tags",tags);
        model.addAttribute("users",usermap);
        model.addAttribute("recommend",blogs);

        return model;
    }

    @GetMapping({"/","/blog"})
    public String index(Model model){

        System.out.println("------index------");
        model = GetDatas(model);
        //首先获取所有博客
        HashMap<String, Object> blogmap = blogService.listallBlog(1, size);
        //获取总页数
        long total = (long)blogmap.get("total");
        long pages = total%size==0?total/size:total/size+1;


        //封装数据
        model.addAttribute("totals",blogmap.get("total"));
        model.addAttribute("blogs",blogmap.get("list"));
        model.addAttribute("current",1);
        model.addAttribute("pages",pages);

        return "index";
    }
    @GetMapping("/blog/{page}")
    public String pageindex(@PathVariable("page") int current,Model model){

        model = GetDatas(model);
        System.out.println("查询页:"+current);
        HashMap<String, Object> blogmap = blogService.listallBlog(current,size);
        //获取总页数
        long total = (long)blogmap.get("total");
        long pages = total%size==0?total/size:total/size+1;
        //封装数据
        model.addAttribute("totals",blogmap.get("total"));
        model.addAttribute("blogs",blogmap.get("list"));
        model.addAttribute("current",current);
        model.addAttribute("pages",pages);
        return "index";
    }

    @GetMapping("/tags")
    public String tags(){

        System.out.println("------tag------");
        return "tags";
    }
    @GetMapping("/blog/details")
    public String blog(){

        System.out.println("------blog------");
        return "blog";
    }
    @GetMapping("/blog/aboutme")
    public String about(){

        System.out.println("------About me------");
        return "aboutme";
    }
}
