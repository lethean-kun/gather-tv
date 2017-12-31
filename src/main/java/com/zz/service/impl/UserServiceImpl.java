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

    @Override
    public User getUserName(String username) {


        return userMapper.selectUserName(username);
    }

    @Override
    public int getUser(User user) {
        User cUser = userMapper.selectUser(user);

        if(cUser==null){
            //用户不存在
            return 0;
        }else if(cUser.getPassword().equals(user.getPassword())){
            //登陆成功
            return 1;
        }
        //密码错误
        return -1;
    }

    @Override
    public User togetUser(User user) {

        return userMapper.selectUser(user);
    }
}
