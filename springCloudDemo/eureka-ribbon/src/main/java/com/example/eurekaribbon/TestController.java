package com.example.eurekaribbon;

import com.example.eurekaribbon.service.HelloServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: TestController
 * @author: yu.liu
 * @date: 2020/10/27 21:07
 * @description:
 */
@RestController
public class TestController {

    @Autowired
    private HelloServiceImpl helloService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Object test() {
        return helloService.sayHello();
    }
}
