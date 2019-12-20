package com.yu.memcache.config;

import com.yu.memcache.manager.MemcachedManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className: MemcachedConfiguration
 * @author: yu.liu
 * @date: 2019/8/7 10:54
 * @description:  配置
 */
@Configuration
public class MemcachedConfiguration {

    @Bean(initMethod = "init", destroyMethod = "disConnect")
    public MemcachedManager memcachedManager(){
        return  new MemcachedManager();
    }

}
