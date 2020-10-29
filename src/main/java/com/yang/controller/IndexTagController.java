package com.yang.controller;

import com.yang.pojo.Blog;
import com.yang.pojo.BlogTag;
import com.yang.pojo.Tag;
import com.yang.pojo.Type;
import com.yang.service.BlogServiceImpl;
import com.yang.service.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/blog")
public class IndexTagController {
    @Autowired
    BlogServiceImpl blogService;

    @Autowired
    TagServiceImpl tagService;


    @RequestMapping("/tags")
    public String tags(Model model){
        Map<Long, Long> map = tagService.GetTagBlog();
        Map<Tag,Long> tags = new HashMap<>();
        for (Long id : map.keySet()) {
            Tag tag = tagService.getTag(id);
            tags.put(tag,map.get(id));
        }
        model.addAttribute("tags",tags);
        return "tags";
    }
    @RequestMapping("/tags/{id}")
    public String tagdetail(@PathVariable("id") Long id,Model model){
        System.out.println("查看Tag："+id);
        Tag tag = tagService.getTag(id);

        //获取包含该tag的博客

        List<Blog> blogs = tagService.GetBlogs(id);
        model.addAttribute("type",tag);
        model.addAttribute("blogs",blogs);

        return "typedetail";
    }
}
