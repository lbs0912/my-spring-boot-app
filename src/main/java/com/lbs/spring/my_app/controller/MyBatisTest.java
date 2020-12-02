package com.lbs.spring.my_app.controller;

import com.lbs.spring.my_app.entity.User;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * https://blog.nowcoder.net/n/641e9cfd478545c1a5ba7fbb1c86a897
 */
public class MyBatisTest {


//    public static void main(String[] args) throws Exception {
//        new MyBatisTest().testGetUserInfo();
//    }

//    public void testGetUserInfo() throws Exception{
//        //1. 加载核心配置文件
//        SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
//        InputStream inputStream= Resources.getResourceAsStream("SqlMapConfig.xml");
//
//        //2. 解析核心配置文件并创建SqlSessionFactory对象
//        SqlSessionFactory sqlSessionFactory=sqlSessionFactoryBuilder.build(inputStream);
//
//        //3. 创建核心对象
//        SqlSession sqlSession=sqlSessionFactory.openSession();
//
//        //4. 得到Mapper代理对象
//        UserDao UserDao=sqlSession.getMapper(UserDao.class);
//
//        //5. 调用自定义的方法实现查询功能
//        List<User> list= UserDao.getEmpTotalByDept();
//        for(User user:list){
//            System.out.println(user);
//        }
//
//        //6. 关闭sqlSession
//        sqlSession.close();
//    }
}
