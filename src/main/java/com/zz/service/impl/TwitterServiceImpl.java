package com.zz.service.impl;

import com.zz.mapper.TwitterMapper;
import com.zz.model.Twitter;
import com.zz.service.TwitterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dzk
 * Created by lethean on 2018/3/5.
 */
@Service
public class TwitterServiceImpl implements TwitterService {


    @Resource
    private TwitterMapper twitterMapper;

    @Override
    public List<Twitter> getAllTwitter() {

        List<Twitter> twitters = twitterMapper.allTwitter();

        return twitters;
    }
}
