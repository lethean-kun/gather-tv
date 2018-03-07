package com.zz.service.impl;

import com.zz.mapper.CommentMapper;
import com.zz.mapper.HitRecordMapper;
import com.zz.mapper.TwitterMapper;
import com.zz.model.Twitter;
import com.zz.service.TwitterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dzk
 * Created by lethean on 2018/3/5.
 */
@Service
public class TwitterServiceImpl implements TwitterService {


    @Resource
    private TwitterMapper twitterMapper;

    @Resource
    private HitRecordMapper hitRecordMapper;

    @Resource
    private CommentMapper commentMapper;

    @Override
    public List<Twitter> getAllTwitter() {

        List<Twitter> twitters = twitterMapper.allTwitter();
        for (Twitter twitter:twitters){
            twitter.setLikeHit(hitRecordMapper.getLikeHit(twitter.getId()));
            twitter.setDislikeHit(hitRecordMapper.getDisLikeHit(twitter.getId()));
            twitter.setReplyHit(commentMapper.getCommentCount(twitter.getId()));
        }
        return twitters;
    }

    @Override
    public Twitter getTwitter(int twitterId) {

        Twitter twitter = twitterMapper.getTwitter(twitterId);
        twitter.setLikeHit(hitRecordMapper.getLikeHit(twitter.getId()));
        twitter.setDislikeHit(hitRecordMapper.getDisLikeHit(twitter.getId()));
        twitter.setReplyHit(commentMapper.getCommentCount(twitter.getId()));
        return twitter;
    }
}
