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

    /**
     * 动态判断用户名是否可用
     * @param username
     * @return
     */
    User getUserName(String username);

    /**
     * 用户登陆
     * @param user
     * @return
     */
    int getUser(User user);

    /**
     * 获取user对象
     * @param user
     * @return
     */
    User togetUser(User user);

    /**
     * 获取用户通过id
     * @param useriId
     * @return
     */
    User getUserById(int useriId);
    /**
     * 更新用户头像
     * @return
     */
    int updateUserHeadPic(User user);
}
