package com.zz.service.impl;

import com.zz.mapper.FollowMapper;
import com.zz.model.LiveShow;
import com.zz.model.UserFollow;
import com.zz.service.UserFollowService;
import com.zz.util.ShowNumFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author dzk
 * Created by lethean on 2018/1/28.
 */
@Service
public class UserFollowServiceImpl implements UserFollowService{

    @Autowired
    private FollowMapper followMapper;

    @Override
    public List<LiveShow> getFollowList(int id) {
        List<LiveShow> list = followMapper.selectFollowLive(id);
        if(list!=null) {
            //对list进行按观看人数降序排序
            Collections.sort(list);
            for (LiveShow liveShow : list) {
                liveShow.setShowNum(ShowNumFormat.returnCount(liveShow.getShowNum()));
            }
        }
        return list;
    }

    @Override
    public int forMyFollow(UserFollow userFollow) {
        followMapper.UpdateMyFollow(userFollow);
        return 1;
    }

    @Override
    public int isMyFollow(UserFollow userFollow) {
        int status = 0;
        UserFollow uf = followMapper.isMyFollow(userFollow);
        if(uf!=null){
            status = 1;
        }
        return status;
    }
}
