package com.zz.mapper;

import com.zz.model.Twitter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
    @Select("select id,user_id as userId,feeling,creat_date as creatDate,delete_date as deleteDate,like_hit as likeHit,dislike_hit as dislikeHit,reply_hit as replyHit from user_twitter")
    List<Twitter> allTwitter();

}
