package com.liu.eurekafeign.service;

import com.liu.eurekafeign.service.impl.HelloServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @className: HelloService
 * @author: yu.liu
 * @date: 2020/10/27 21:36
 * @description:
 */

@FeignClient(value = "SERVICE-HELLO", fallback = HelloServiceFallback.class)
public interface HelloService {

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    String sayHello();
}
