package com.yang.controller;

import com.yang.pojo.Blog;
import com.yang.pojo.Tag;
import com.yang.pojo.User;
import com.yang.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class IndexBlogController {
    @Autowired
    BlogServiceImpl blogService;
    @Autowired
    TypeServiceimpl typeServiceimpl;
    @Autowired
    TagServiceImpl tagService;
    @Autowired
    UserServiceImpl userService;

    @Autowired
    RedisServiceImpl redisService;
    @RequestMapping("/detail/{id}")
    public String blogdetail(@PathVariable("id") long id, Model model){
        Blog blog = blogService.getBlog(id);
        int views = redisService.ViewsIncr(id);
        blog.setViews(views);
        model.addAttribute("blog",blog);

        User user = userService.GetUser(blog.getUser());
        model.addAttribute("user",user);

        List<Tag> tags = tagService.GetTags(blog.getTagids());
        model.addAttribute("tags",tags);
        return "blog";
    }

    @RequestMapping("/archives")
    public String archives(Model model){
        int total = blogService.GetTotal();
        HashMap<Integer, List<Blog>> blog = blogService.getBlogByYear();
        model.addAttribute("total",total);
        model.addAttribute("archiveMap",blog);
        return "archives";
    }
}
