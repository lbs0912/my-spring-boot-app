package com.lbs.spring.my_app.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ApiTestController {

    @GetMapping("/test1")
    public String test1Api() {
        //开始时间
        long start = System.currentTimeMillis();
        System.out.println("Elapsed time-API test1: " + (System.currentTimeMillis() - start));
        return "test1Api";
    }
}
