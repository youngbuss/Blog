package com.yang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yang.dao.BlogMapper;
import com.yang.dao.TypesMapper;
import com.yang.pojo.Blog;
import com.yang.pojo.Tag;
import com.yang.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TypeServiceimpl implements TypeService{
    @Autowired
    private TypesMapper typesMapper;
    @Autowired
    private BlogMapper blogMapper;

    @Override
    public Type getType(Long id) {
        return  typesMapper.selectById(id);
    }

    @Override
    public int saveType(Type type) {
        int insert = typesMapper.insert(type);
        return insert;
    }

    @Override
    public Map<String,Object> listType(int current, int size) {
        Page<Type> page = new Page<>(current,size);
        typesMapper.selectPage(page,null);
        List<Type> list = page.getRecords();
        long total = page.getTotal();
        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("list",list);
        return map;
    }

    @Override
    public Type updateType(Long id, Type type) {
        typesMapper.updateById(type);
        return typesMapper.selectById(id);
    }

    @Override
    public void deleteType(Long id) {
        typesMapper.deleteById(id);
    }

    @Override
    public Type getTypeByName(String name) {
        QueryWrapper<Type> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        Type type = typesMapper.selectOne(wrapper);
        return  type;
    }

    @Override
    public List<Type> listType() {
        List<Type> list = typesMapper.selectList(null);
        return list;
    }

    @Override
    public List<Type> listSomeType() {
        Page<Type> page = new Page<>(1,5);
        return  typesMapper.selectPage(page,null).getRecords();

    }

    @Override
    public HashMap<Type, List<Blog>> getBlogs() {
        HashMap<Type,List<Blog>> TBmaps = new HashMap<>();
        //获取所有的types
        List<Type> list = typesMapper.selectList(null);
        for (Type type : list) {
            Long typeId = type.getId();
            //通过typeid 查询博客
            QueryWrapper<Blog> wrapper = new QueryWrapper();
            wrapper.eq("type",typeId);
            List<Blog> blogs = blogMapper.selectList(wrapper);
            TBmaps.put(type,blogs);
        }
        return TBmaps;
    }

    @Override
    public HashMap<String, Object> getBlogs(long id) {
        HashMap<String, Object> TBmaps = new HashMap<>();
        //获取所有的types
        List<Type> list = typesMapper.selectList(null);
        Type type = typesMapper.selectById(id);
        QueryWrapper<Blog> wrapper = new QueryWrapper();
        wrapper.eq("type",type.getId());
        List<Blog> blogs = blogMapper.selectList(wrapper);
        TBmaps.put("type",type);
        TBmaps.put("blogs",blogs);
        return TBmaps;
    }
}
