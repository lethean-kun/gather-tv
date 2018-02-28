package com.zz.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author dzk
 * Created by lethean on 2018/2/28.
 */
@Controller
public class FocusErrorController implements ErrorController{

    private static final String ERROR_PATH = "/error";

    @RequestMapping(value=ERROR_PATH)
    public String handleError(){
        return "error/404";
    }



    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
