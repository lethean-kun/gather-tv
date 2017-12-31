package com.zz.controller;

import com.zz.model.Result;
import com.zz.model.User;
import com.zz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dzk
 * Created by lethean on 2017/12/31.
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("register")
    public Result register(User user,HttpServletRequest request){
        Result result = new Result();
        String username = request.getParameter("username");
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

    @ResponseBody
    @RequestMapping("login")
    public Result login(User user,HttpServletRequest request){
        Result result = new Result();
        int status = userService.getUser(user);
        if(status==1){
            result.setStatus(1);
            result.setMessage("登陆成功");
            User zUser = userService.togetUser(user);
            request.getSession().setAttribute("user",zUser);
        }else if(status==-1){
            result.setStatus(-1);
            result.setMessage("密码错误");
        }else if(status==0){
            result.setStatus(0);
            result.setMessage("用户不存在");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("check")
    public Result check(String username,HttpServletRequest request){
        Result result = new Result();

        User user = userService.getUserName(username);
        if(user==null){
            result.setStatus(1);
            result.setMessage("昵称可用");
        }else {
            result.setStatus(0);
            result.setMessage("昵称不可用");
        }
        return result;
    }

}
