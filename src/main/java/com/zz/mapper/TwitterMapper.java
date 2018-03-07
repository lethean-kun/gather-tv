package com.zz.mapper;

import com.zz.model.Twitter;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author dzk
 * Created by lethean on 2018/3/5.
 */
@Mapper
public interface TwitterMapper {


    /**
     * 发布动态
     * @param twitter
     * @return
     */
    @Insert("INSERT INTO user_twitter(user_id,feeling,creat_date) VALUE(#{userId},#{feeling},now())")
    int insertTwitter(Twitter twitter);

    /**
     * 查询所有动态
     * @return
     */
    @Select("SELECT id,user_id,feeling,creat_date as creatData,delete_date as deleteDate,like_hit as likeHit,dislike_hit as dislikeHit,reply_hit as replyHit " +
            "FROM user_twitter")
    @Results({
            @Result(column = "user_id",property = "userId"),
            @Result(column = "user_id",property = "user",one = @One(
                    select = "com.zz.mapper.UserMapper.selectUserById",
                    fetchType= FetchType.EAGER
            ))

    })
    List<Twitter> allTwitter();

    /**
     * 查询动态
     * @return
     */
    @Select("SELECT id,user_id,feeling,creat_date as creatData,delete_date as deleteDate,like_hit as likeHit,dislike_hit as dislikeHit,reply_hit as replyHit " +
            "FROM user_twitter WHERE id=#{twitterId}")
    @Results({
            @Result(column = "user_id",property = "userId"),
            @Result(column = "user_id",property = "user",one = @One(
                    select = "com.zz.mapper.UserMapper.selectUserById",
                    fetchType= FetchType.EAGER
            ))

    })
    Twitter getTwitter(int twitterId);

    /**
     * 点赞
     * @return
     */
    @Update("UPDATE user_twitter SET like_hit = like_hit+1 WHERE id=#{twitterId}")
    int updateLikeHit(int twitterId);

    /**
     * 点踩
     * @return
     */
    @Update("UPDATE user_twitter SET dislike_hit = dislike_hit+1 WHERE id=#{twitterId}")
    int updateDisLikeHit(int twitterId);

    /**
     * 回复
     * @return
     */
    @Update("UPDATE user_twitter SET reply_hit = reply_hit+1 WHERE id=#{twitterId}")
    int updateReplyHit(int twitterId);

}
