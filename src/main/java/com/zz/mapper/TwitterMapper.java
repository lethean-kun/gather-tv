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


}
