package com.yang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yang.dao.BlogMapper;
import com.yang.pojo.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Service
public class BlogServiceImpl  implements BlogService{

    @Autowired
    BlogMapper blogMapper;
    @Override
    public Blog getBlog(Long id) {
        return blogMapper.selectById(id);
    }


    @Override
    public int GetTotal() {
        return  blogMapper.selectList(null).size();
    }

    @Override
    public HashMap<String, Object> pageBlog(int current, int size, HashMap<String, Object> map) {
        Page<Blog> page = new Page<>(current,size);
        String title = (String)map.get("title");
        int type = (int)map.get("type");
        int r = (int)map.get("recommend");
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        if (title!=null)
            wrapper.like("title",title);
        if(type!=0)
            wrapper.eq("type",type);
        wrapper.eq("recommend",r);
        blogMapper.selectPage(page, wrapper);
        List<Blog> blogs = page.getRecords();
        HashMap<String,Object> result = new HashMap<>();
        result.put("list",blogs);
        result.put("total",page.getTotal());
        return result;
    }

    @Override
    public List<Blog> listBlog(int current, int size, HashMap<String,Object> map) {
        Page<Blog> page = new Page<>(current,size);
        String title = (String)map.get("title");
        int type = (int)map.get("type");
        int r = (int)map.get("recommend");
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        if (title!=null)
            wrapper.like("title",title);
        if(type!=0)
            wrapper.eq("type",type);
        wrapper.eq("recommend",r);
        blogMapper.selectPage(page, wrapper);
        List<Blog> blogs = page.getRecords();
        return blogs;
    }

    @Override
    public HashMap<String, Object> listallBlog(int current, int size) {
        Page<Blog> page = new Page<>(current,size);
        blogMapper.selectPage(page, null);
        List<Blog> blogs = page.getRecords();
        HashMap<String,Object> result = new HashMap<>();
        result.put("list",blogs);
        result.put("total",page.getTotal());
        return result;
    }

    @Override
    public int saveBlog(Blog blog) {

        return blogMapper.insert(blog);
    }

    @Override
    public Blog updateBlog(Blog blog) {
        blogMapper.updateById(blog);
        return blogMapper.selectById(blog.getId());
    }

    @Override
    public void deleteBlog(Long id) {
        blogMapper.deleteById(id);
    }

    @Override
    public List<Blog> getbyTimeDesc() {
        Page<Blog> page = new Page<>(1,2);
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.eq("recommend",1).orderByDesc("update_time");
        List<Blog> blogs = blogMapper.selectPage(page,wrapper).getRecords();
        return blogs;
    }

    @Override
    public HashMap<Integer, List<Blog>> getBlogByYear() {
        HashMap<Integer,List<Blog>> map = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        List<Blog> blogs = blogMapper.selectList(null);
        for (Blog blog : blogs) {
            calendar.setTime(blog.getUpdateTime());
            int year = calendar.get(Calendar.YEAR);
            if(map.keySet().contains(year)){
                map.get(year).add(blog);
            }else {
                List<Blog> list = new ArrayList<>();
                list.add(blog);
                map.put(year,list);
            }
        }

        return map;
    }
}
