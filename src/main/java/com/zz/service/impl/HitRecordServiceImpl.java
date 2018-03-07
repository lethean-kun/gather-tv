package com.zz.service.impl;

import com.zz.mapper.HitRecordMapper;
import com.zz.model.HitRecord;
import com.zz.model.Twitter;
import com.zz.service.HitRecordService;
import com.zz.service.TwitterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HitRecordServiceImpl implements HitRecordService {

    @Resource
    HitRecordMapper hitRecordMapper;

    @Resource
    TwitterService twitterService;

    @Override
    public Twitter UpdateHitRecord(HitRecord hitRecord) {
        if(hitRecord == null){
            return null;
        }

        int status = hitRecordMapper.UpdateHitRecord(hitRecord);
        Twitter twitter = twitterService.getTwitter(hitRecord.getTwitterId());

        return twitter;
    }

}
