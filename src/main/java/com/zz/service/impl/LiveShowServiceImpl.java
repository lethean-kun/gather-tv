package com.zz.service.impl;

import com.zz.mapper.LiveShowMapper;
import com.zz.model.LiveShow;
import com.zz.service.LiveShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lethean on 2017/12/23.
 */
@Service
public class LiveShowServiceImpl implements LiveShowService {

    @Autowired
    LiveShowMapper liveShowMapper;

    @Override
    public List<LiveShow> getLiveList() {
        List list = liveShowMapper.getAllLive();
        return list;
    }
}
