package com.zz.mapper;

import com.zz.model.LiveShow;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by lethean on 2017/12/22.
 */
@Mapper
public interface LiveShowMapper {

    /**
     * 获取所有直播列表
     * @return
     */
    @Select("SELECT id, person_name as personName, pic_url as picUrl, type,live_title as liveTitle,show_num as showNum,msg_channel as msgChannel,live_url as liveUrl " +
            "FROM live_show")
    List<LiveShow> getAllLive();

    /**
     *爬取数据存入数据库 名称和平台相同则更新
     * @param liveShow
     * @return
     */
    @Insert("INSERT INTO live_show(person_name, pic_url, type,live_title,show_num,msg_channel,live_url) " +
            "VALUES(#{personName}, #{picUrl}, #{type},#{liveTitle},#{showNum},#{msgChannel},#{liveUrl})" +
            "ON DUPLICATE KEY UPDATE pic_url=#{picUrl},show_num=#{showNum},type=#{type},live_title=#{liveTitle}")
    int updateLive(LiveShow liveShow);

}
