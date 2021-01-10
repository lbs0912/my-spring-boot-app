package com.lbs.spring.my_app.controller;

import com.lbs.spring.my_app.bean.ConfigBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyBatisTest {
    @Value("${config.test.name}")
    private String server;


    @Autowired
    private ConfigBean configBean;

    @GetMapping("testValue")
    public void test(String str) {
        System.out.println("str is" + str);
        System.out.println("server is" + server);
        System.out.println("name is" + configBean.getName());
        System.out.println("age is" + configBean.getAge());
    }
}
