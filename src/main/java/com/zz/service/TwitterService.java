package com.zz.service;

import com.zz.model.Comment;
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

    /**
     * 获取用户动态
     * @param userId
     * @return
     */
    List<Twitter> getUserTwitter(int userId);

    /**
     * 查询单个动态
     * @param twitterId
     * @return
     */
    Twitter getTwitter(int twitterId);

    /**
     * 发布动态
     * @param twitter
     * @return
     */
    int publishTwitter(Twitter twitter);

    /**
     * 评论
     * @param comment
     * @return
     */
    int insertCommet(Comment comment);


}
