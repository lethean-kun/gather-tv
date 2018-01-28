package com.zz.service;

import com.zz.model.LiveShow;
import com.zz.model.UserFollow;

import java.util.List;

/**
 * @author dzk
 * Created by lethean on 2018/1/28.
 */
public interface UserFollowService {

    /**
     * 获取所有关注直播列表
     * @param id 用户id
     * @return
     */
    List<LiveShow> getFollowList(int id);

    /**
     * 关注或取消
     * @param userFollow
     * @return
     */
    int forMyFollow(UserFollow userFollow);

    /**
     * 是否关注
     * @param userFollow
     * @return
     */
    int isMyFollow(UserFollow userFollow);
}
