package com.zz.service.impl;


import com.zz.mapper.LiveShowMapper;
import com.zz.mapper.TypeMapper;
import com.zz.model.LiveShow;
import com.zz.model.LiveType;
import com.zz.service.SpiderService;
import com.zz.thread.ThreadCrowingLiveList;
import com.zz.util.CrowingLiveList;
import com.zz.util.CrowingLiveType;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

/**
 * @author dzk
 *         Created by lethean on 2017/12/22.
 */
@Component
public class SpiderServiceImpl implements SpiderService {

    private final Logger logger = LoggerFactory.getLogger(SpiderServiceImpl.class);

    @Autowired
    LiveShowMapper liveShowMapper;

    @Autowired
    ThreadCrowingLiveList threadCrowingLiveList;

    @Autowired
    CrowingLiveList crowingLiveList;

    @Autowired
    TypeMapper typeMapper;

    @Override
    @Scheduled(fixedRate = 300000)
    public int forInsertLive() throws Exception {

        logger.info("10分钟抓取一次数据");


        //设置状态为未播
        liveShowMapper.updateLiveIsShow();
        Long s = System.currentTimeMillis();

//        Set<Future<List<LiveShow>>> futures = Sets.newHashSet();
//        threadCrowingLiveList.getAllLiveShow().clear();
//        Future<List<LiveShow>> hYList = threadCrowingLiveList.crowingHuya();
//        futures.add(hYList);
//        Future<List<LiveShow>> lZList = threadCrowingLiveList.crowingLongZhu();
//        futures.add(lZList);
//        Future<List<LiveShow>> ZQList = threadCrowingLiveList.crowingZanQi();
//        futures.add(ZQList);
//        Future<List<LiveShow>> QMList = threadCrowingLiveList.crowingQuanMin();
//        futures.add(QMList);
//        //等待所有的爬虫线程执行完成
//        boolean flag;
//        while (true) {
//            flag = true;
//            Iterator<Future<List<LiveShow>>> isDoneIterator = futures.iterator();
//            while (isDoneIterator.hasNext()) {
//                if (!isDoneIterator.next().isDone()) {
//                    flag = false;
//                }
//            }
//            if (flag) {
//                logger.info("批处理所有的线程执行完成...");
//                break;
//            }
//        }
        CrowingLiveList.allLive.clear();

        crowingLiveList.crowingHuya();
        crowingLiveList.crowingLongZhu();
        crowingLiveList.crowingQuanMin();
        crowingLiveList.crowingZanQi();

        Long e = System.currentTimeMillis();
        logger.info("线程爬取数据耗时耗时" + (e - s));

        List<LiveShow> liveShows = new ArrayList<>();
        liveShows.addAll(CrowingLiveList.allLive);
        Long star = System.currentTimeMillis();
        //未做成全部插入，虽然循环很消耗性能
        // for(LiveShow liveShow:liveShows){
        //     liveShowMapper.updateLive(liveShow);
        // }
        //质的提升 相对于循环快了100多倍
        liveShowMapper.newUpdateLive(liveShows);
        Long end = System.currentTimeMillis();
        logger.info("批量insert耗时" + (end - star));
        logger.info("共爬到数据{},详细信息{}", liveShows.size(), liveShows.toString());
        return 0;
    }


    @Override
//    @Scheduled(fixedRate = 50000000)
    public int forInsertType() throws Exception {
        List<LiveType> liveTypes = CrowingLiveType.getLiveTypes();
        for (LiveType liveType : liveTypes) {
            typeMapper.insertType(liveType);
        }
        return 0;
    }
}
