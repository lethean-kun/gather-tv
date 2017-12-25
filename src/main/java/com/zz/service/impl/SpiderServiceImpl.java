package com.zz.service.impl;


import com.zz.mapper.LiveShowMapper;
import com.zz.mapper.TypeMapper;
import com.zz.model.LiveShow;
import com.zz.model.LiveType;
import com.zz.service.SpiderService;
import com.zz.util.CrowingLiveList;
import com.zz.util.CrowingLiveType;
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

    @Autowired
    TypeMapper typeMapper;

    @Override
    @Scheduled(fixedRate = 250000)
    public int forInsertLive() throws Exception {

        logger.info("250秒抓取一次数据");

        //设置状态为未播
        liveShowMapper.updateLiveIsShow();
        List<LiveShow> liveShows = CrowingLiveList.crowingHuya();
        for(LiveShow liveShow:liveShows){
            logger.info(liveShow.toString());
            liveShowMapper.updateLive(liveShow);
        }
        return 0;
    }

    @Override
//    @Scheduled(fixedRate = 50000000)
    public int forInsertType() throws Exception {
        List<LiveType> liveTypes = CrowingLiveType.getLiveTypes();
        for (LiveType liveType:liveTypes) {
            typeMapper.insertType(liveType);
        }
        return 0;
    }
}
