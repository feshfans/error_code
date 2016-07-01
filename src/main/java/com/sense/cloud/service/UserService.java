package com.sense.cloud.service;

import com.sense.cloud.entity.User;

/**
 * Created by kang on 16-5-30.
 */
public interface UserService {

    void addUser(User user);
    User getUserById(String id);

}

