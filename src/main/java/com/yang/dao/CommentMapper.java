package com.yang.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.pojo.Blog;
import com.yang.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommentMapper extends BaseMapper<Comment> {
}
