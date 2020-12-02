package com.lbs.spring.my_app;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbs.spring.my_app.dao.UserMapper;
import com.lbs.spring.my_app.entity.User;

import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        user.setAge(20);
        user.setName("lbs");
//        user.setId(1992L);
        user.setRemark("remark-lbs");
        user.setEmail("lbs1203940926@163.com");
        int rows = userMapper.insert(user);
        log.error("lbsTest--影响记录数:" + rows);
    }

    @Test
    void queryTest() {
        User user = userMapper.selectById(1992L);
        log.error("lbsTest--query-user:" + JSONUtil.toJsonStr(user));
        Map<String, Object> map = new HashMap<>();

        map.put("age", 28);
        List<User> list = userMapper.selectByMap(map);
        log.error("lbsTest--query-user:" + JSONUtil.toJsonStr(list));
    }


    /**
     * 条件构造器 查询名字中包含lbs 并且年龄小于28的
     */
    @Test
    void selectByWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.like("name", "lbs")
                .lt("age", 28); //支持链式调用
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }


    /**
     * 条件构造器
     */
    @Test
    void selectByWrapper2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.like("name", "lbs") //支持链式调用
                .between("age", 20, 40)
                .isNotNull("email")
                .orderByDesc("age")
                .orderByAsc("id");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }


    /**
     * 条件构造器 子查询 创建日期为2019-2-14 并且直属上级名字中包含王 "date_format(create_time,'%Y-%m-%d') AND select id from
     * user where name like '王%'"
     */
    @Test
    void selectByWrapper3() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0}", "2019-02-14")
                .inSql("manager_id", "select id from user where name like '王%'");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 条件构造器  select 不列出全部字段
     */
    @Test
    void selectByWrapper4() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.select("id", "name").like("name", "lbs") //支持链式调用
                .between("age", 20, 40)
                .isNotNull("email")
                .orderByDesc("age")
                .orderByAsc("id");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }


    /**
     * 条件构造器  入参为Map
     */
    @Test
    void selectByWrapperByMap() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.select("id", "name").like("name", "lbs") //支持链式调用
                .between("age", 20, 40)
                .isNotNull("email")
                .orderByDesc("age")
                .orderByAsc("id");
        List<Map<String, Object>> list = userMapper.selectMaps(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * Lambda条件构造器
     */
    @Test
    void selectByWrapperByLambda() {
        //创建Lambda条件构造器的3种方式
//        LambdaQueryWrapper<User> lambdaQueryWrapper = new QueryWrapper<User>().lambda();
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.<User>lambdaQuery();
        //方法引用 防止误写
        lambdaQueryWrapper.like(User::getName, "lbs").lt(User::getAge, 28);

        //防止误写
//        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
//        queryWrapper.like("name", "lbs")
//                .lt("age", 28); //支持链式调用


        List<User> list = userMapper.selectList(lambdaQueryWrapper);
        list.forEach(System.out::println);
    }


    //分页插件
    // BaseMapper IPage @TODO

    /**
     * 条件构造器  select 不列出全部字段
     */
    @Test
    void selectByWrapperPagination() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.ge("age", 1); //>=26

        Page<User> page = new Page<User>(1, 2);

        IPage<User> iPage = userMapper.selectPage(page, queryWrapper);
        System.out.println("lbsTest--iPage:" + JSONUtil.toJsonStr(iPage));
        System.out.println("lbsTest--总页数:" + iPage.getPages());
        System.out.println("lbsTest--总记录数:" + iPage.getTotal());

        List<User> list = iPage.getRecords();
        list.forEach(System.out::println);


//        Page page = new Page<>(0,2);
//        userMapper.selectPage(page, null);
//
//        page.getRecords().forEach(System.out::println);
//        System.out.println(page.getCurrent());
//        System.out.println(page.getPages());
//        System.out.println(page.getSize());
//        System.out.println(page.getTotal());

    }


}
