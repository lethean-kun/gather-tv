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
public class DynamicController {

    @RequestMapping("toAllDynamic")
    public String toAllDynamic(HttpServletRequest request,
                             Parameter parameter){

        return "users-dynamic/allDynamic";
    }

}
