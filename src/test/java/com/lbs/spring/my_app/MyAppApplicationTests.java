package com.lbs.spring.my_app;

import com.lbs.spring.my_app.dao.UserMapper;
import com.lbs.spring.my_app.entity.User;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import org.junit.Assert;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class MyAppApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    void insetTest() {
        User user = new User();
        user.setAge(28);
        user.setRealName("lbs");
//        user.setId(1992L);
        user.setEmail("lbs1203940926@163.com");
        int rows = userMapper.insert(user);
        log.error("lbsTest--影响记录数:" + rows);
    }


}
