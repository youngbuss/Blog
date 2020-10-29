package com.yang.service;

import com.yang.pojo.Blog;
import com.yang.pojo.BlogTag;
import com.yang.pojo.Tag;
import com.yang.pojo.Type;

import java.util.List;
import java.util.Map;

public interface TagService {
    int saveTag(Tag tag);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    List<Tag> listTag();

    List<Tag> listSomeTag();

    // List<Type> listType();

    //List<Type> listTypeTop(Integer size);

    Tag updateTag(Long id, Tag tag);
    void deleteTag(Long id);

    //参数为id组合
    List<Tag> GetTags(String ids);

    //获取每个Tag所对应的博客

    Map<Long,Long> GetTagBlog();

    List<Blog> GetBlogs(Long id);
}
