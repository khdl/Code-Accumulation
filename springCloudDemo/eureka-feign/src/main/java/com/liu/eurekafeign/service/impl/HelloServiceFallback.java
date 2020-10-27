package com.liu.eurekafeign.service.impl;

import com.liu.eurekafeign.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @className: HelloServiceFallback
 * @author: yu.liu
 * @date: 2020/10/27 21:50
 * @description:
 */
@Service
public class HelloServiceFallback implements HelloService {
    @Override
    public String sayHello() {
        return "服务不可用";
    }
}
