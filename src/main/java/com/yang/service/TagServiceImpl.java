package com.yang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yang.dao.BlogMapper;
import com.yang.dao.BlogTagMapper;
import com.yang.dao.TagsMapper;
import com.yang.pojo.Blog;
import com.yang.pojo.BlogTag;
import com.yang.pojo.Tag;
import com.yang.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    BlogMapper blogMapper;
    @Autowired
    TagsMapper tagsMapper;
    @Autowired
    BlogTagMapper blogTagMapper;
    @Override
    public List<Tag> listTag() {
        List<Tag> tags = tagsMapper.selectList(null);
        return tags;
    }


    @Override
    public int saveTag(Tag tag) {
        int i  = tagsMapper.insert(tag);
        return  i ;
    }

    @Override
    public Tag getTag(Long id) {
        Tag tag = tagsMapper.selectById(id);
        return tag;
    }



    @Override
    public Tag updateTag(Long id, Tag tag) {
        tagsMapper.updateById(tag);
        return tagsMapper.selectById(id);
    }

    @Override
    public void deleteTag(Long id) {
        tagsMapper.deleteById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        Tag tag = tagsMapper.selectOne(wrapper);
        return  tag;
    }

    @Override
    public List<Tag> listSomeTag() {
        Page<Tag> page =new Page<>(1,5);
        List<Tag> tags = tagsMapper.selectPage(page, null).getRecords();
        return tags;
    }

    @Override
    public List<Tag> GetTags(String ids) {

        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.inSql("id",ids);
        List<Tag> tags = tagsMapper.selectList(wrapper);
        return tags;
    }

    @Override
    public Map<Long, Long> GetTagBlog() {
        HashMap<Long,Long> hashmap = new HashMap<>();
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.select("count(blogid) as c,tagid").groupBy("tagid");
        List<Map<String,Long>> list = blogTagMapper.selectMaps(wrapper);
        for (Map<String, Long> map : list) {
            hashmap.put((Long)map.get("tagid"),map.get("c"));
        }
        return hashmap;
    }

    @Override
    public List<Blog> GetBlogs(Long id) {
        QueryWrapper<BlogTag> wrapper = new QueryWrapper<>();
        wrapper.eq("tagid",id);
        List<BlogTag> blogTags = blogTagMapper.selectList(wrapper);
        List<Blog> list = new ArrayList<>();
        for (BlogTag blogTag : blogTags) {
            Blog blog = blogMapper.selectById(blogTag.getBlogid());
            list.add(blog);
        }
        return list;
    }
}
