package com.zz.mapper;

import com.zz.model.LiveShow;
import com.zz.model.Parameter;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author dzk
 * Created by lethean on 2017/12/22.
 */
@Mapper
public interface LiveShowMapper {

    /**
     * 获取所有直播列表
     * @return
     */
    @Select({"<script>",
                "SELECT id, person_name as personName, pic_url as picUrl, type,live_title as liveTitle,show_num as showNum,msg_channel as msgChannel,live_url as liveUrl " +
                "FROM live_show where is_show=1",
                "<when test='type!=null'>",
                    "and type=#{type}",
                "</when>",
            "</script>"})
    List<LiveShow> getAllLive(Parameter parameter);

    /**
     * 获取直播详情
     * @return
     */
    @Select("SELECT id, person_name as personName, pic_url as picUrl, type,live_title as liveTitle,show_num as showNum,msg_channel as msgChannel,live_url as liveUrl " +
            "FROM live_show where id=#{id}")
    LiveShow getLiveDetail(int id);

    /**
     *爬取数据存入数据库 名称和平台相同则更新
     * @param liveShow
     * @return
     */
    @Insert("INSERT INTO live_show(person_name, pic_url, type,live_title,show_num,msg_channel,live_url,is_show) " +
            "VALUES(#{personName}, #{picUrl}, #{type},#{liveTitle},#{showNum},#{msgChannel},#{liveUrl},1)" +
            "ON DUPLICATE KEY UPDATE pic_url=#{picUrl},show_num=#{showNum},type=#{type},live_title=#{liveTitle},is_show=1")
    int updateLive(LiveShow liveShow);

    /**
     * 每次抓取之前 类似心跳吧所有直播状态改为0（未播）
     * @return
     */
    @Update("update live_show set is_show=0")
    int updateLiveIsShow();

}
