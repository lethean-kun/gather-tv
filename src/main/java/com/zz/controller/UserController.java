package com.zz.controller;

import com.zz.model.Result;
import com.zz.model.User;
import com.zz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dzk
 * Created by lethean on 2017/12/31.
 */
public class UserController {

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("register")
    public Result register(User user,HttpServletRequest request){
        Result result = new Result();

        int status = userService.register(user);
        if(status>0){
            result.setStatus(1);
            result.setMessage("注册成功");
            request.getSession().setAttribute("user",user);
        }else {
            result.setStatus(0);
            result.setMessage("注册失败");
        }
        return result;
    }

}
