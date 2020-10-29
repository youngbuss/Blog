package com.yang.controller.admin;

import com.yang.pojo.User;
import com.yang.service.UserServiceImpl;
import com.yang.util.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping({"","/tologin"})
    public  String login(){
        return "admin/login";
    }

    @PostMapping("/login")
    public  String tologin(@RequestParam("username") String name, @RequestParam("password") String pwd, RedirectAttributes redirectAttributes,HttpSession session){
        System.out.println("验证登陆"+name+"   "+pwd);
        User user = userService.checkuser(name,pwd);

        if(user!=null){
            if(user.getPassword().equals(MD5Utils.code(pwd))){
                session.setAttribute("userid",user.getId());
                session.setAttribute("loginUser",name);
                session.setAttribute("nickname",user.getNickname());
                redirectAttributes.addFlashAttribute("user",user);
                return "redirect:/admin/main";
            }else {
                redirectAttributes.addFlashAttribute("mess","密码错误！");
                System.out.println("密码错误");
                return "redirect:/admin";
            }
        }else {
            redirectAttributes.addFlashAttribute("mess","登录用户不存在！");
            System.out.println("用户不存在");
            return "redirect:/admin";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/admin";
    }
}
