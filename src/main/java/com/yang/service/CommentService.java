package com.yang.service;

import com.yang.pojo.Comment;

import java.util.List;

public interface CommentService {

    void saveComment(Comment comment);

    List<Comment> listCommentByBlogId(Long blogId);

    List<Comment> getChildComment(Long id);

    Comment GetComment(Long id);
}
