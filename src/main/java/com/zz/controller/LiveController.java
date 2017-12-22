package com.zz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lethean on 2017/12/21.
 */
@Controller
public class LiveController {

    @RequestMapping("index")
    public String toIndex(){

        return "index";
    }

}
