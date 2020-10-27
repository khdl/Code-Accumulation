package com.example.eurekaribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @className: HelloServiceImpl
 * @author: yu.liu
 * @date: 2020/10/27 21:09
 * @description:
 */
@Service
public class HelloServiceImpl {

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @HystrixCommand(fallbackMethod = "sayHelloFallback")
    public String sayHello() {
        logger.info("Try to consume ...");
        return restTemplate.getForObject("http://SERVICE-HELLO/hello", String.class);
    }


    public String sayHelloFallback() {
        return "服务不可用";
    }

}
