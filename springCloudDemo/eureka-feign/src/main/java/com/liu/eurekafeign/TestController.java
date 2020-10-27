package com.liu.eurekafeign;

import com.liu.eurekafeign.service.HelloService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @className: TestController
 * @author: yu.liu
 * @date: 2020/10/27 21:38
 * @description:
 */
@RestController
public class TestController {

    @Resource
    private HelloService helloService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String sayHello() {
        return helloService.sayHello();
    }

}
