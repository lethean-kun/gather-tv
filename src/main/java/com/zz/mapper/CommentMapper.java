package com.zz.mapper;

import com.zz.model.Comment;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

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

    /**
     * 获取文章所有评论
     * @param twitterId
     * @return
     */
    @Select("SELECT id,user_id,twitter_id as twitterId,content,comment_date as commentDate " +
            "FROM comment WHERE twitter_id=#{twitterId}")
    @Results({
            @Result(column = "user_id",property = "userId"),
            @Result(column = "user_id",property = "user",one = @One(
                    select = "com.zz.mapper.UserMapper.selectUserById",
                    fetchType= FetchType.EAGER
            ))

    })
    List<Comment> selectCommentsByTwitter(int twitterId);

    /**
     * 评论
     * @param comment
     * @return
     */
    @Insert("INSERT INTO comment(user_id,twitter_id,content,comment_date) values(#{userId},#{twitterId},#{content},now())")
    int insertComment(Comment comment);
}
