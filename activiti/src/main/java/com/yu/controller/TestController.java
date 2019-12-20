package com.yu.controller;

import com.yu.service.TestService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @className: TestController
 * @author: yu.liu
 * @date: 2019/8/15 13:17
 * @description:
 */
@Controller
@RequestMapping("act")
public class TestController {

    @Autowired
    public TestService testService;

    @RequestMapping("test")
    public void test(HttpServletRequest request, HttpServletResponse response){
           testService.deploy();
    }



}
