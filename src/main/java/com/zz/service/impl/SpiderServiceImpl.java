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

import java.util.ArrayList;
import java.util.List;

/**
 * @author dzk
 * Created by lethean on 2017/12/22.
 */
@Component
public class SpiderServiceImpl implements SpiderService {

    private final Logger logger = LoggerFactory.getLogger(SpiderServiceImpl.class);

    @Autowired
    LiveShowMapper liveShowMapper;

    @Autowired
    TypeMapper typeMapper;

    @Override
    @Scheduled(fixedRate = 2500000)
    public int forInsertLive() throws Exception {

        logger.info("250秒抓取一次数据");

        //设置状态为未播
        liveShowMapper.updateLiveIsShow();
        List<LiveShow> hYList = CrowingLiveList.crowingHuya();
        List<LiveShow> lZList = CrowingLiveList.crowingLongZhu();
        List<LiveShow> ZQList = CrowingLiveList.crowingZanQi();
        List<LiveShow> QMList = CrowingLiveList.crowingQuanMin();

        List<LiveShow> liveShows = new ArrayList<>();
        liveShows.addAll(hYList);
        liveShows.addAll(lZList);
        liveShows.addAll(ZQList);
        liveShows.addAll(QMList);
        Long star = System.currentTimeMillis();
        //未做成全部插入，虽然循环很消耗性能，日后再做
        // for(LiveShow liveShow:liveShows){
        //     liveShowMapper.updateLive(liveShow);
        // }
        //质的提升 相对于循环快了100多倍
        liveShowMapper.newUpdateLive(liveShows);
        Long end = System.currentTimeMillis();
        logger.info("耗时"+(end-star));
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
