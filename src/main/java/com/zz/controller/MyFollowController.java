package com.zz.controller;

import com.zz.model.Result;
import com.zz.model.UserFollow;
import com.zz.service.UserFollowService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author dzk
 * Created by lethean on 2018/1/28.
 */
@Controller
public class MyFollowController {

    @Resource
    UserFollowService userFollowService;

    @RequestMapping("myFollow/{id}")
    public String myFollow(HttpServletRequest request,
                           @PathVariable("id") int id){
        List list = userFollowService.getFollowList(id);
        request.setAttribute("liveList",list);

        return "live-show/allLive";
    }

    @ResponseBody
    @RequestMapping("subFollow")
    public void subFollow(HttpServletRequest request,
                         UserFollow userFollow){
        userFollowService.forMyFollow(userFollow);
    }

    @ResponseBody
    @RequestMapping("isMyFollow")
    public Result isMyFollow(HttpServletRequest request,
                             UserFollow userFollow){
        int status = userFollowService.isMyFollow(userFollow);
        Result result = new Result();
        result.setStatus(status);
        return result;
    }

}
