package com.zz.controller;

import com.zz.mapper.TypeMapper;
import com.zz.model.LiveShow;
import com.zz.model.LiveType;
import com.zz.model.Parameter;
import com.zz.service.LiveShowService;
import com.zz.service.LiveTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lethean on 2017/12/21.
 */
@Controller
public class LiveController {

    private final Logger logger = LoggerFactory.getLogger(LiveController.class);

    @Resource
    LiveShowService liveShowService;

    @Resource
    LiveTypeService liveTypeService;


    @RequestMapping("index")
    public String toIndex(){

        return "index";
    }

    @RequestMapping("toLiveType")
    public String toLiveType(HttpServletRequest request){
        request.setAttribute("ctx", "http://"+request.getRemoteHost()+":"+request.getLocalPort()+"/");
        List<LiveType> liveTypes = liveTypeService.getAllType();
        request.setAttribute("liveTypes",liveTypes);

        return "liveType";
    }

    @ResponseBody
    @RequestMapping("getLiveList")
    public List<LiveShow> getLiveList(Parameter parameter){

        List list = liveShowService.getLiveList(parameter);

        return list;
    }

    @RequestMapping("liveDetail/{id}")
    public String liveDetail(@PathVariable("id") int id,
                             HttpServletRequest request){
        request.setAttribute("ctx", "http://"+request.getRemoteHost()+":"+request.getLocalPort()+"/");
        logger.info("http://"+request.getRemoteHost()+":"+request.getLocalPort()+"/");
        LiveShow liveShow = liveShowService.getLiveDetail(id);
        request.setAttribute("liveShow",liveShow);
        return "liveDetail";
    }


}
