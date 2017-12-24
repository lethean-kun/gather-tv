package com.zz.service;

import com.zz.model.LiveShow;
import com.zz.model.Parameter;

import java.util.List;

/**
 * Created by lethean on 2017/12/23.
 */
public interface LiveShowService {

    /**
     * 获取所有直播列表
     * @return
     */
    List<LiveShow> getLiveList(Parameter parameter);

    /**
     * 获取直播详情
     * @return
     */
    LiveShow getLiveDetail(int id);
}
