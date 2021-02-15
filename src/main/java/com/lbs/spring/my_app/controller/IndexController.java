package com.lbs.spring.my_app.controller;

import com.lbs.spring.my_app.annotation.LoginRequired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping("/sourceA")
    //http://localhost:8081/sourceA
    public String sourceA(){
        return "你正在访问sourceA资源";
    }

    @GetMapping("/sourceB")
    @LoginRequired
    public String sourceB(){
        return "你正在访问sourceB资源";
    }
}
