package com.yu.redis_test;

import com.yu.redis_test.entity.User;
import com.yu.redis_test.service.RedisService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTestApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisService redisService;

    @Test
    public void contextLoads() {
        stringRedisTemplate.opsForValue().set("aaa", "1111");
        Assert.assertEquals("1111", stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void get() {
        User user = new User();
        user.setName("ceshi");
        user.setAge(22);
        redisService.add("userByName:" + user.getName(), user, 10L);
        List<User> list = new ArrayList<>();
        list.add(user);
        redisService.add("list", list, 10L);
        User user1 = redisService.get("userByName:wangjianfeng");
        List<User> list2 = redisService.getUserList("list");

    }



}
