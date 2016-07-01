package com.sense.cloud.controller;

import com.sense.cloud.entity.User;
import com.sense.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by kang on 16-5-30.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 返回字符串
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public String addUser(){

        System.out.println("========================");
        return "123";
    }

    /**
     * 返回实体，自动转换为JSON
     * @param id
     * @return
     */
    @RequestMapping("get")
    @ResponseBody
    public User getUser(String id){
        User user= userService.getUserById("715617f1893e47d5bf165aca69426c38");
        System.out.println(user);
        return user;
    }

    /**
     * 返回具体页面,还有一种是返回Model
     * @return
     */
    @RequestMapping("index")
    public String toIndex(){
        return "index";
    }

    /**
     * restful风格
     * @param id
     */
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable("id")String id){
        System.out.println("id:"+id);
    }
}
