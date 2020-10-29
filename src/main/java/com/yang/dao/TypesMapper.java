package com.yang.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TypesMapper extends BaseMapper<Type> {
}
