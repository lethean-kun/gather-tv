package com.zz.service;

import com.zz.model.User;

/**
 * @author dzk
 * Created by lethean on 2017/12/31.
 */
public interface UserService {

    /**
     * 用户注册
     * @param user
     * @return
     */
    int register(User user);
}
