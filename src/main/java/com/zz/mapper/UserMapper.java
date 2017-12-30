package com.zz.mapper;

import com.zz.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

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
    @Insert("INSERT INTO user (nice_name,phone,email) " +
            "VALUES(#{nickName},#{phone},#{email})")
    int insertUser(User user);

}
