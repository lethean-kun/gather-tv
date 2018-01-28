package com.zz.mapper;

import com.zz.model.LiveShow;
import com.zz.model.UserFollow;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author dzk
 * Created by lethean on 2018/1/28.
 */
@Mapper
public interface FollowMapper {


    /**
     * 获取用户关注列表
     * @param id
     * @return
     */
    @Select("SELECT id, person_name as personName, pic_url as picUrl, type,live_title as liveTitle,show_num as showNum,msg_channel as msgChannel,live_url as liveUrl " +
            "FROM live_show where is_show=1 and id in (select room_id from user_follow where status=1 and user_id = #{id})")
    List<LiveShow> selectFollowLive(int id);

    /**
     * 点击关注
     * @param userFollow
     * @return
     */
    @Insert("INSERT INTO user_follow(user_id, room_id) " +
            "VALUES(#{userId}, #{roomId})" +
            "ON DUPLICATE KEY UPDATE status= CASE when status=1 then 0 when status=0 then 1 END")
    int UpdateMyFollow(UserFollow userFollow);

    @Select("SELECT user_id as userId, room_id as roomId FROM user_follow WHERE user_id = #{userId} and room_id = #{roomId} and status=1")
    UserFollow isMyFollow(UserFollow userFollow);
}
