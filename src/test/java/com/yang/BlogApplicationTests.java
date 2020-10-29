package com.yang;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.dao.BlogMapper;
import com.yang.dao.BlogTagMapper;
import com.yang.dao.TagsMapper;
import com.yang.dao.UserMapper;
import com.yang.pojo.*;
import com.yang.service.BlogServiceImpl;
import com.yang.service.RedisServiceImpl;
import com.yang.service.TagServiceImpl;
import com.yang.service.TypeServiceimpl;
import com.yang.vo.UserQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    BlogTagMapper blogTagMapper;
    @Autowired
    BlogMapper blogMapper;
    @Autowired
    TagsMapper tagsMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    TypeServiceimpl typeServiceimpl;
    @Autowired
    BlogServiceImpl blogServiceimpl;
    @Autowired
    TagServiceImpl tagService;
    @Autowired
    RedisServiceImpl redisService;
    @Test
    void contextLoads() {

        /*int s = redisService.ViewsIncr(2L);
        System.out.println(s);*/
        redisService.removeView(2l);
    }

}


