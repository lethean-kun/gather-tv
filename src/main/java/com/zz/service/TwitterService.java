package com.zz.service;

import com.zz.model.Twitter;

import java.util.List;

/**
 * @author dzk
 * Created by lethean on 2018/3/5.
 */
public interface TwitterService {

    /**
     * 获取所有动态
     * @return
     */
    List<Twitter> getAllTwitter();


}
