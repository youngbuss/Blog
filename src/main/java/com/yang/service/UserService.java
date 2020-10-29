package com.yang.service;

import com.yang.pojo.User;

import java.util.List;

public interface UserService {
    User checkuser(String username, String password);
    User Getuser(String username);
    List<User> getallusers();
    User GetUser(Long id);

}
