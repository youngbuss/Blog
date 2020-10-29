package com.yang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yang.dao.CommentMapper;
import com.yang.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentMapper commentMapper;
    @Override
    public void saveComment(Comment comment) {
        commentMapper.insert(comment);
    }

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("blogid",blogId);
        List<Comment> comments = commentMapper.selectList(wrapper);
        return comments;
    }

    @Override
    public List<Comment> getChildComment(Long id) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("parentcommentid",id).or().eq("rootid",id);
        List<Comment> comments = commentMapper.selectList(wrapper);
        return comments;
    }

    @Override
    public Comment GetComment(Long id) {
        return commentMapper.selectById(id);
    }
}
