package com.yu.service;

import com.yu.entity.User;
import com.yu.mapper.UserMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @className: UserService
 * @author: yu.liu
 * @date: 2019/8/7 16:39
 * @description:
 */
@Service
public class UserService  {

    @Resource
    private UserMapper userMapper;

    @Cacheable(value="userCache",key="#p0")
    public User findByName(String name){
        System.out.println("去数据库查询了数据");
        return userMapper.findByUsername(name);
    }

    @CachePut(value="userCache",key="#p0.userId")
    public void update(User user){
        System.out.println("更新了数据库数据");
        userMapper.update(user);
    }


    @CacheEvict(value="userCache",key="#p0",beforeInvocation=true)
    public void delete(String username){
        System.out.println("删除了数据库数据");
        userMapper.delete(username);
    }

}
