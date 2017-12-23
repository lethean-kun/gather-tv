package com.zz.controller;

import com.zz.model.LiveShow;
import com.zz.service.LiveShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lethean on 2017/12/21.
 */
@Controller
public class LiveController {

    @Resource
    LiveShowService liveShowService;


    @RequestMapping("index")
    public String toIndex(){

        return "index";
    }

    @ResponseBody
    @RequestMapping("getLiveList")
    public List<LiveShow> getLiveList(){

        List list = liveShowService.getLiveList();

        return list;
    }


}
