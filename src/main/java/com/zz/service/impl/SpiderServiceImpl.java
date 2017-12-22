package com.zz.service.impl;

import com.zz.mapper.LiveShowMapper;
import com.zz.model.LiveShow;
import com.zz.service.SpiderService;
import com.zz.util.Crowing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lethean on 2017/12/22.
 */
@Component
public class SpiderServiceImpl implements SpiderService {

    @Autowired
    LiveShowMapper liveShowMapper;
    @Override

    @Scheduled(fixedRate = 50000)
    public int forInsertLive() throws Exception {

        System.out.println("50秒一次");
        List<LiveShow> liveShows = Crowing.crowingHuya();
        for(LiveShow liveShow:liveShows){
            liveShowMapper.updateLive(liveShow);
        }
        return 0;
    }
}
