package com.zz.service.impl;

import com.zz.mapper.CommentMapper;
import com.zz.mapper.HitRecordMapper;
import com.zz.mapper.TwitterMapper;
import com.zz.model.Comment;
import com.zz.model.Twitter;
import com.zz.service.TwitterService;
import com.zz.util.DateFormat;
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

        return formatTwitter(twitters);
    }

    @Override
    public List<Twitter> getUserTwitter(int userId) {
        List<Twitter> twitters = twitterMapper.userTwitters(userId);


        return formatTwitter(twitters);

    }

    @Override
    public Twitter getTwitter(int twitterId) {

        Twitter twitter = twitterMapper.getTwitter(twitterId);
        twitter.setLikeHit(hitRecordMapper.getLikeHit(twitter.getId()));
        twitter.setDislikeHit(hitRecordMapper.getDisLikeHit(twitter.getId()));
        twitter.setReplyHit(commentMapper.getCommentCount(twitter.getId()));
        return twitter;
    }

    @Override
    public int publishTwitter(Twitter twitter) {
        return twitterMapper.insertTwitter(twitter);
    }

    @Override
    public int insertCommet(Comment comment) {
        if (comment == null) {
            return 0;
        }
        return commentMapper.insertComment(comment);
    }

    @Override
    public int deleteTwitter(Twitter twitter) {


        int status = twitterMapper.deleteTwitter(twitter);
        if(status>0){
            return 1;
        }

        return 0;
    }

    public List<Twitter> formatTwitter(List<Twitter> twitters){

        for (Twitter twitter : twitters) {
            twitter.setLikeHit(hitRecordMapper.getLikeHit(twitter.getId()));
            twitter.setDislikeHit(hitRecordMapper.getDisLikeHit(twitter.getId()));
            twitter.setReplyHit(commentMapper.getCommentCount(twitter.getId()));
            twitter.setPublishDate(DateFormat.dataFormat(twitter.getCreatDate()));
        }
        return twitters;
    }


}
