package com.zz.controller;

import com.zz.model.Parameter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dzk
 * Created by lethean on 2018/3/2.
 */
@Controller
public class TwitterController {

    @RequestMapping("toAllTwitter")
    public String toAllTwitter(HttpServletRequest request,
                             Parameter parameter){

        return "users-twitter/allTwitter";
    }

}
