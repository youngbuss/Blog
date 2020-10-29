package com.yang.controller;

import com.yang.pojo.Comment;
import com.yang.pojo.User;
import com.yang.service.CommentServiceImpl;
import com.yang.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    CommentServiceImpl commentService;

    private  final static String avator = "https://images.unsplash.com/photo-1603688042009-67d3ecaa3bca?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80";
    @RequestMapping("/blog/comments")
    public String commment(Comment comment, HttpServletRequest request){
        System.out.println(comment);
        HttpSession session = request.getSession();
        Object userid = session.getAttribute("userid");
        if(userid!=null){
            User user = userService.GetUser((Long) userid);
            comment.setAvatar(user.getAvatar());
            comment.setNickname(user.getNickname());
            comment.setEmail(null);
            comment.setAdmincomment(true);
        }else {
            comment.setAvatar(avator);
        }
        if(comment.getParentcommentid()!= -1){
            Comment c = commentService.GetComment(comment.getParentcommentid());
            comment.setParentcommentname(c.getNickname());
        }

        commentService.saveComment(comment);
        return "redirect:/blog/comments/" + comment.getBlogid();
    }


    @RequestMapping("/blog/comments/{id}")
    public String comments(@PathVariable("id") Long blogId, Model model) {
        System.out.println("读取评论列表"+blogId);
        model.addAttribute("comments", commentService.listCommentByBlogId(blogId));
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        HashMap<Long,List<Comment>> maps = new HashMap<>();
        for (Comment comment : comments) {
            List<Comment> childComment = commentService.getChildComment(comment.getId());
            maps.put(comment.getId(),childComment);
            System.out.println(comment.getId());
            childComment.forEach(System.out::println);
        }
        model.addAttribute("child", maps);

        return "blog :: commentList";
    }
}
