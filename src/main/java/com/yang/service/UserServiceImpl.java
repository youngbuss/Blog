package com.yang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yang.dao.UserMapper;
import com.yang.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl  implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Override
    public User checkuser(String username, String password) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);

        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
        return user;
    }

    @Override
    public User Getuser(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);

        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
        return user;
    }

    @Override
    public List<User> getallusers() {
        return  userMapper.selectList(null);
    }

    @Override
    public User GetUser(Long id) {
        return  userMapper.selectById(id);
    }
}
