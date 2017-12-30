package com.zz.service.impl;

import com.zz.mapper.UserMapper;
import com.zz.model.User;
import com.zz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dzk
 * Created by lethean on 2017/12/31.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public int register(User user) {
        int status = userMapper.insertUser(user);
        return status;
    }
}
