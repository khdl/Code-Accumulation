package com.yu.redis_test.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.lang.reflect.Method;

/**
 * @className: RedisConfig
 * @author: yu.liu
 * @date: 2019/8/13 15:21
 * @description:
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory){
        RedisCacheManager cacheManager = RedisCacheManager.builder(factory).build();
        return cacheManager;
    }

    @Override
    @Bean
    public KeyGenerator keyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... params) {
               StringBuffer stringBuffer = new StringBuffer();
               stringBuffer.append(o.getClass().getName());
               stringBuffer.append(method.getName());
               for(Object obj : params){
                   stringBuffer.append(obj.toString());
               }
                System.out.println("调用Redis生成key："+ stringBuffer.toString());
                return stringBuffer.toString();
            }
        };
    }

}
