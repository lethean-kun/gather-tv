package com.zz.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author dzk
 * Created by lethean on 2018/3/6.
 */
@Mapper
public interface CommentMapper {

    /**
     * 获取文章评论数
     * @param twitterId
     * @return
     */
    @Select("SELECT count(*) FROM comment WHERE twitter_id=#{twitterId}")
    int getCommentCount(int twitterId);
}
