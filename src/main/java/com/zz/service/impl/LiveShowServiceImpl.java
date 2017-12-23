package com.zz.service.impl;

import com.zz.mapper.LiveShowMapper;
import com.zz.model.LiveShow;
import com.zz.service.LiveShowService;
import com.zz.util.Crowing;
import com.zz.util.ShowNumFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by lethean on 2017/12/23.
 */
@Service
public class LiveShowServiceImpl implements LiveShowService {

    private final Logger logger = LoggerFactory.getLogger(LiveShowServiceImpl.class);

    @Autowired
    LiveShowMapper liveShowMapper;

    @Override
    public List<LiveShow> getLiveList() {
        List<LiveShow> list = liveShowMapper.getAllLive();
        //对list进行按观看人数降序排序
        Collections.sort(list);
        for(LiveShow liveShow:list){
            liveShow.setShowNum(ShowNumFormat.returnCount(liveShow.getShowNum()));
        }
        return list;
    }

    @Override
    public LiveShow getLiveDetail(int id) {
        LiveShow liveShow = liveShowMapper.getLiveDetail(id);
        //不同来源直播对应不同的处理
        if(liveShow.getMsgChannel().equals(Crowing.HuYa)){
            String[] str = liveShow.getLiveUrl().split("/");
            String url = str[str.length-1];
            url = "http://liveshare.huya.com/"+url+"/huyacoop.swf";
            logger.info("url为"+url);
            liveShow.setLiveUrl(url);
        }
        return liveShow;
    }
}
