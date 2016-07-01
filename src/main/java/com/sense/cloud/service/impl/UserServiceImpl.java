package com.sense.cloud.service.impl;

import com.sense.cloud.dao.UserMapper;
import com.sense.cloud.entity.User;
import com.sense.cloud.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by kang on 16-5-30.
 */

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    public void addUser(User user) {
        userMapper.insert(user);
    }

    public User getUserById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
