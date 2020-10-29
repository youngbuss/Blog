package com.yang.service;

import com.yang.pojo.Blog;

import java.util.HashMap;
import java.util.List;

public interface BlogService {

    Blog getBlog(Long id);
    int GetTotal();
    HashMap<String,Object> pageBlog(int current, int page, HashMap<String,Object> hashMap);
    List<Blog>  listBlog(int current, int page, HashMap<String,Object> hashMap);
    HashMap<String, Object>  listallBlog(int current, int page);
    int saveBlog(Blog blog);

    Blog updateBlog(Blog blog);

    void deleteBlog(Long id);

    List<Blog> getbyTimeDesc();

    HashMap<Integer,List<Blog>> getBlogByYear();
}
