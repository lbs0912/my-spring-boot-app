package com.lbs.spring.my_app.controller;

import com.lbs.spring.my_app.dao.UserMapper;
import com.lbs.spring.my_app.entity.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class MybatisPlusTestController {
    @Resource
    private UserMapper userMapper;

    @GetMapping("mybatis/plus/user/insert")
    public void insertUser() {
        User user = new User();
        user.setAge(28);
        user.setRealName("lbs");
//        user.setId(1992L);
        user.setEmail("lbs1203940926@163.com");
        int rows = userMapper.insert(user);
        System.out.println("影响记录数：" + rows);
    }
}

