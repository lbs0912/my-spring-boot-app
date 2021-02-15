package com.lbs.spring.my_app.controller;

import com.lbs.spring.my_app.annotation.LoginRequired;
import com.lbs.spring.my_app.annotation.WebLogger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @WebLogger("学生实体")
    public String sourceB(){
        return "你正在访问sourceB资源";
    }

    @WebLogger("学生实体")
    @GetMapping("/sourceC/{source_name}")
    public String sourceC(@PathVariable("source_name") String sourceName){
        return "你正在访问sourceC资源";
    }


}
