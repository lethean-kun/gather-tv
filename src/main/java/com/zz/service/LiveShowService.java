package com.zz.service;

import com.zz.model.LiveShow;
import com.zz.model.Parameter;

import java.util.List;

/**
 * @author dzk
 * Created by lethean on 2017/12/23.
 */
public interface LiveShowService {

    /**
     * 获取所有直播列表
     * @param parameter 包括搜索 分页 类型等
     * @return
     */
    List<LiveShow> getLiveList(Parameter parameter);

    /**
     * 获取所有关注直播列表
     * @param id 用户id
     * @return
     */
    List<LiveShow> getFollowList(int id);

    /**
     * 获取直播详情
     * @param id room_id
     * @return
     */
    LiveShow getLiveDetail(int id);
}
