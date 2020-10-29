package com.yang.controller;

import com.yang.pojo.Blog;
import com.yang.pojo.Type;
import com.yang.service.BlogServiceImpl;
import com.yang.service.TypeServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class IndexTypeController {

    @Autowired
    TypeServiceimpl typeServiceimpl;
    @Autowired
    BlogServiceImpl blogService;
    @RequestMapping("/types")
    public String typesindex(Model model){
        System.out.println("进入分类页面");
        HashMap<Type, List<Blog>> map = typeServiceimpl.getBlogs();

        model.addAttribute("types",map);
        return "types";
    }
    @RequestMapping("/types/{id}")
    public String typesdetail(Model model, @PathVariable("id") long id){
        System.out.println("查看分类："+id);
        HashMap<String, Object> map = typeServiceimpl.getBlogs(id);
        Type type =(Type) map.get("type");
        model.addAttribute("type",type);
        List<Blog> blogs = (List)map.get("blogs");

        model.addAttribute("blogs",blogs);
        return "typedetail";
    }
}
