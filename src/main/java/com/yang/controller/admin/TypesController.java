package com.yang.controller.admin;

import com.yang.pojo.Tag;
import com.yang.pojo.Type;
import com.yang.service.TypeServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class TypesController {

    @Autowired
    TypeServiceimpl typeServiceimpl;
    @RequestMapping("/types")
    public String types( Model model){

        int current = 1;
        int size = 100;

        System.out.println("分类管理"+"current:"+current+"size:"+size);
        Map<String,Object> map= typeServiceimpl.listType(current, size);
        model.addAttribute("page",map);
        return "admin/types";
    }
    @RequestMapping("/types/input")
    public String toadddtype(){
        return "admin/addtype";
    }
    @RequestMapping("/types/add")
    public String addtype(Type type){

        int i = typeServiceimpl.saveType(type);
        System.out.println("新增分类id:"+i);
        return "redirect:/admin/types";
    }
    @GetMapping("/types/update/{id}")
    public String toupdate(@PathVariable("id")long id,Model model){
        Type type = typeServiceimpl.getType(id);
        model.addAttribute("type",type);
        return "admin/updatetype";

    }
    @PostMapping("/types/update")
    public  String updateEmployee(Type type){
        System.out.println("执行更新："+type);
        typeServiceimpl.updateType(type.getId(),type);
        return "redirect:/admin/types";
    }
    @GetMapping("/types/delete/{id}")
    public String delete(@PathVariable("id")long id){
        System.out.println("删除id:"+id);
        typeServiceimpl.deleteType(id);
        return "redirect:/admin/types";
    }


    @RequestMapping("/types/checktype")
    @ResponseBody
    public String check(String name){
        String msg = "";
        System.out.println(name);
        Type type =typeServiceimpl.getTypeByName(name);
        if(type==null){
            msg = "ok";
        }else {
            msg = "该类别已经存在";
        }
        return msg;
    }
}
