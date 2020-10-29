package com.yang.controller.admin;

import com.yang.pojo.Tag;
import com.yang.pojo.Type;
import com.yang.service.TagService;
import com.yang.service.TagServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class TagsController {

    @Autowired
    TagServiceImpl tagserviceimpl;

    @RequestMapping("/tags/checktag")
    @ResponseBody
    public String check(String name){
        String msg = "";
        System.out.println(name);
        Tag tag = tagserviceimpl.getTagByName(name);
        if(tag==null){
            msg = "ok";
        }else {
            msg = "标签已经存在";
        }
        return msg;
    }

    @RequestMapping("/tags")
    public String tags( Model model){

        List<Tag> list = tagserviceimpl.listTag();
        model.addAttribute("list",list);
        return "admin/tags";
    }
    @RequestMapping("/tags/input")
    public String toadddtag(){
        return "admin/addtag";
    }
    @RequestMapping("/tags/add")
    public String addtag(Tag tag){

        int i = tagserviceimpl.saveTag(tag);
        System.out.println(tag);
        System.out.println("新增分类id:"+i);
        return "redirect:/admin/tags";
    }
    @GetMapping("/tags/update/{id}")
    public String toupdate(@PathVariable("id")long id,Model model){
        Tag tag = tagserviceimpl.getTag(id);

        model.addAttribute("tag",tag);
        return "admin/updatetag";

    }
    @PostMapping("/tags/update")
    public  String updateEmployee(Tag tag){
        System.out.println("执行更新："+tag);
        tagserviceimpl.updateTag(tag.getId(),tag);
        return "redirect:/admin/tags";
    }
    @GetMapping("/tags/delete/{id}")
    public String delete(@PathVariable("id")long id){
        System.out.println("删除id:"+id);
        tagserviceimpl.deleteTag(id);
        return "redirect:/admin/tags";
    }
}
