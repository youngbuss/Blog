package com.yang.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.pojo.BlogTag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BlogTagMapper extends BaseMapper<BlogTag> {
}
