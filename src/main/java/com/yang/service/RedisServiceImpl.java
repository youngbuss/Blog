package com.yang.service;

import com.yang.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService{
    @Autowired
    private RedisUtil redisUtil;


    @Override
    public void CreateView(Long i) {
        String id = Long.toString(i);
        redisUtil.set(id,0);
    }

    @Override
    public int ViewsIncr(Long i) {
        String id = Long.toString(i);
        if (redisUtil.hasKey(id)){
            redisUtil.incr(id,1);
        }else {
            redisUtil.set(id,1);
        }
        return (int)redisUtil.get(id);
    }

    @Override
    public void removeView(Long i) {
        String id = Long.toString(i);
        redisUtil.del(id);
    }
}
