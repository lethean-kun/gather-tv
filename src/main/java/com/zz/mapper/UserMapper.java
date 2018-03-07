package com.zz.mapper;

import com.zz.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author dzk
 * Created by lethean on 2017/12/31.
 */
@Mapper
public interface UserMapper {


    /**
     * 注册插入数据库
     * @param user
     * @return
     */
    @Insert("INSERT INTO user (nick_name,password,phone,email) " +
            "VALUES(#{username},#{password},#{phone},#{email})")
    int insertUser(User user);

    /**
     * 动态判断用户名是否可用
     * @param username
     * @return
     */
    @Select("SELECT id,nick_name as username,phone,email " +
            "FROM user WHERE nick_name=#{username}")
    User selectUserName(String username);

    /**
     * 用户手机登陆
     * @param user
     * @return
     */
    @Select("SELECT id,nick_name as username,password,phone,email,head_pic as headPic " +
            "FROM user WHERE phone=#{phone}")
    User selectUser(User user);


    @Select("SELECT id,nick_name as username,password,phone,email,head_pic as headPic " +
            "FROM user where id=#{userId}")
    User selectUserById(int userId);


    /**
     * 更新用户头像 （简单加了）
     * @param user
     * @return
     */
    @Update("UPDATE user set head_pic = #{headPic} WHERE id = #{id}")
    int updateUserHeadPic(User user);

}
