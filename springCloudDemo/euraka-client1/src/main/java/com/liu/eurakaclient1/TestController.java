package com.liu.eurakaclient1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: TestController
 * @author: yu.liu
 * @date: 2020/10/27 21:22
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
