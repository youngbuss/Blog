package com.yang.service;

public interface RedisService {
    void  CreateView(Long id);
    int  ViewsIncr(Long id);
    void  removeView(Long id);
}
