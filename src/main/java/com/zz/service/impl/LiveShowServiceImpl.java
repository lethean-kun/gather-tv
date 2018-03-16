package com.zz.service.impl;

import com.zz.mapper.LiveShowMapper;
import com.zz.model.LiveShow;
import com.zz.model.Parameter;
import com.zz.service.LiveShowService;
import com.zz.util.ShowNumFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author dzk
 * Created by lethean on 2017/12/23.
 */
@Service
public class LiveShowServiceImpl implements LiveShowService {

    private final Logger logger = LoggerFactory.getLogger(LiveShowServiceImpl.class);

    @Autowired
    LiveShowMapper liveShowMapper;

    @Value("${liveShow.hu-ya.name}")
    private String HuYa;

    @Value("${liveShow.quan-min.name}")
    private String QuanMin;

    @Value("${liveShow.zan-qi.name}")
    private String ZanQi;

    @Value("${liveShow.long-zhu.name}")
    private String LongZhu;

    @Override
    public List<LiveShow> getLiveList(Parameter parameter) {
        List<LiveShow> list = liveShowMapper.getAllLive(parameter);
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
        if(liveShow.getMsgChannel().equals(HuYa)){
            String[] str = liveShow.getLiveUrl().split("/");
            String url = str[str.length-1];
            url = "http://liveshare.huya.com/"+url+"/huyacoop.swf";
            logger.info("url为"+url);
            //不同类别直播平台标识(1-虎牙龙珠、2-全民战旗)
            liveShow.setStatus(1);
            liveShow.setLiveUrl(url);
        }else if(liveShow.getMsgChannel().equals(LongZhu)){
            String[] str = liveShow.getLiveUrl().split("-");
            String url = str[str.length-1];
            url = "http://r.plures.net/proton/flash/streaming-ifp2rgic.swf?autoPlay=1&roomId="+url;
            logger.info("url为"+url);
            //不同类别直播平台标识(1-虎牙龙珠、2-全民战旗)
            liveShow.setStatus(1);
            liveShow.setLiveUrl(url);
        }else if(liveShow.getMsgChannel().equals(ZanQi)){

            String url = "http://www.zhanqi.tv/live/embed?roomId="+liveShow.getLiveUrl();
            logger.info("url为"+url);
            //不同类别直播平台标识(1-虎牙龙珠、2-全民战旗)
            liveShow.setStatus(2);
            liveShow.setLiveUrl(url);
        }else if(liveShow.getMsgChannel().equals(QuanMin)){
            //www.quanmin.tv/42416
            String[] str = liveShow.getLiveUrl().split("/");
            String url = str[str.length-1];
            url = "http://www.quanmin.tv/embed/"+url;
            logger.info("url为"+url);
            //不同类别直播平台标识(1-虎牙龙珠、2-全民战旗)
            liveShow.setStatus(2);
            liveShow.setLiveUrl(url);
        }
        return liveShow;
    }
}
