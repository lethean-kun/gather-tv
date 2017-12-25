package com.zz.mapper;

import com.zz.model.LiveType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author dzk
 * Created by lethean on 2017/12/24.
 */
@Mapper
public interface TypeMapper {

    /**
     * 爬取类型存入数据库
     * @param liveType
     * @return
     */
    @Insert("INSERT INTO live_type(type_pic,type_name,msg_channel) " +
            "VALUES(#{typePic},#{typeName},#{msgChannel})")
    int insertType(LiveType liveType);


    /**
     * 获取所有分类 按火热度降序
     * @return
     */
    @Select("SELECT id,type_pic as typePic,type_name as typeName,msg_channel as msgChannel " +
            "FROM live_type order by heat desc")
    List<LiveType> getLiveTypes();

}
