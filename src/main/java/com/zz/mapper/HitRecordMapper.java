package com.zz.mapper;

import com.zz.model.HitRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author dzk
 * Created by lethean on 2018/3/6.
 */
@Mapper
public interface HitRecordMapper {

    /**
     * 获取文章点赞数
     * @param twitterId
     * @return
     */
    @Select("SELECT count(*) FROM hit_record WHERE twitter_id=#{twitterId} and is_like = 2")
    int getLikeHit(int twitterId);

    /**
     * 获取文章点踩数
     * @param twitterId
     * @return
     */
    @Select("SELECT count(*) FROM hit_record WHERE twitter_id=#{twitterId} and is_like = 1")
    int getDisLikeHit(int twitterId);

    /**
     * 点击赞或者踩
     * @param hitRecord
     * @return
     */
    @Insert("INSERT INTO hit_record(user_id, twitter_id,hit_date,is_like) " +
            "VALUES(#{userId}, #{twitterId},now(),#{isLike})" +
            "ON DUPLICATE KEY UPDATE is_like= #{isLike}")
    int UpdateHitRecord(HitRecord hitRecord);

}
