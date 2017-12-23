package com.zz.service.impl;


import com.zz.mapper.LiveShowMapper;
import com.zz.model.LiveShow;
import com.zz.service.SpiderService;
import com.zz.util.Crowing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lethean on 2017/12/22.
 */
//@Component
public class SpiderServiceImpl implements SpiderService {

    private final Logger logger = LoggerFactory.getLogger(SpiderServiceImpl.class);

    @Autowired
    LiveShowMapper liveShowMapper;

    @Override
    @Scheduled(fixedRate = 50000)
    public int forInsertLive() throws Exception {

        logger.info("50秒抓取一次数据");
        List<LiveShow> liveShows = Crowing.crowingHuya();
        for(LiveShow liveShow:liveShows){
            logger.info(liveShow.toString());
            liveShowMapper.updateLive(liveShow);
        }
        return 0;
    }
}
