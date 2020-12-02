package com.lbs.spring.my_app;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lbs.spring.my_app.dao.UserMapper;
import com.lbs.spring.my_app.entity.User;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import javax.annotation.Resource;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class MyAppApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    void basicDemo1() {
        log.error("----- 入门Demo 1 ------");
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    void insetTest() {
        User user = new User();
        user.setAge(28);
        user.setName("lbs");
        user.setId(1992L);
        user.setRemark("remark-lbs");
        user.setEmail("lbs1203940926@163.com");
        int rows = userMapper.insert(user);
        log.error("lbsTest--影响记录数:" + rows);
    }

    @Test
    void queryTest() {
        User user = userMapper.selectById(1330168733250916353L);
        log.error("lbsTest--query-user:" + JSONUtil.toJsonStr(user));
        Map<String,Object> map = new HashMap<>();

        map.put("age",20);
        List<User> list = userMapper.selectByMap(map);
        log.error("lbsTest--query-user:" + JSONUtil.toJsonStr(list));
    }


    @Test
    void selectByWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.like("name","lbs0912")
                .lt("age",40); //支持链式调用
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }



}
