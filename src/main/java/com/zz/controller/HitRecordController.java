package com.zz.controller;

import com.zz.model.HitRecord;
import com.zz.model.Result;
import com.zz.model.Twitter;
import com.zz.service.HitRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class HitRecordController {

    private final Logger logger = LoggerFactory.getLogger(HitRecordController.class);

    @Resource
    HitRecordService hitRecordService;

    @RequestMapping("hitIt")
    @ResponseBody
    public Result hitIt(HitRecord hitRecord){

        Result result = new Result();
        Twitter twitter = hitRecordService.UpdateHitRecord(hitRecord);

        logger.info("点赞记录"+hitRecord);

        if(twitter!=null){
            result.setStatus(1);
            result.setData(twitter);
        }
        result.setStatus(0);
        return result;
    }


}
