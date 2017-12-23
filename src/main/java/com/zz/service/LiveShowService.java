package com.zz.service;

import com.zz.model.LiveShow;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lethean on 2017/12/23.
 */
@Service
public interface LiveShowService {

    /**
     * 获取所有直播列表
     * @return
     */
    List<LiveShow> getLiveList();
}
