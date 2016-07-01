package com.kang.user;

import com.sense.cloud.entity.User;
import com.sense.cloud.service.UserService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.UUID;

/**
 * Created by kang on 16-5-30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-mybatis.xml"})
public class Test {

    @Autowired
    private UserService userService;


    /*@Before
    public void before(){

        ApplicationContext context=new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-mybatis.xml"});
        userService= (UserService) context.getBean("userService");
    }*/

    @org.junit.Test
    public void addUser(){
        User user=new User();
        user.setId(UUID.randomUUID().toString().replaceAll("-",""));
        user.setName("kang");
        user.setBirthday(new Date());
        user.setSalary(15000d);

        userService.addUser(user);
    }

}

