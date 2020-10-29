package com.yang.service;

import com.yang.pojo.Blog;
import com.yang.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TypeService {
    int saveType(Type type);

    Type getType(Long id);

    Type getTypeByName(String name);

    Map<String,Object> listType(int current, int size);

    List<Type> listType();

    List<Type> listSomeType();

    //List<Type> listTypeTop(Integer size);

    Type updateType(Long id,Type type);
    void deleteType(Long id);

    //获取该分类下的所有博客
    HashMap<Type,List<Blog>> getBlogs();

    HashMap<String,Object> getBlogs(long id);
}
