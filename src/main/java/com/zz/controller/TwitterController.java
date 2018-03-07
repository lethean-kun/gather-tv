package com.zz.controller;

import com.zz.model.Parameter;
import com.zz.model.Twitter;
import com.zz.service.TwitterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author dzk
 * Created by lethean on 2018/3/2.
 */
@Controller
public class TwitterController {

    @Resource
    TwitterService twitterService;

    @RequestMapping("toAllTwitter")
    public String toAllTwitter(HttpServletRequest request,
                             Parameter parameter){
        //所有用户动态
        List<Twitter> twitters = twitterService.getAllTwitter();
        request.setAttribute("twitters",twitters);

        return "users-twitter/allTwitter";
    }

    @RequestMapping("publishTwitter")
    public String publishTwitter(Twitter twitter){


        return "users-twitter/allTwitter";
    }

}
