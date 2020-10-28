package com.liu.eurekaclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: TestController
 * @author: yu.liu
 * @date: 2020/10/27 20:45
 * @description:
 */
@RestController
public class TestController {

    @Value("${server.port}")
    private String port;


    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello() {
        return "结果来自：" + port;
    }
}
