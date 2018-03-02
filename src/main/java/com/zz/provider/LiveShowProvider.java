package com.zz.provider;

import com.zz.model.LiveShow;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;


/**
 * @author dzk
 * Created by lethean on 2018/1/29.
 */
public class LiveShowProvider {

    /**
     * 爬取的数据存入数据库 名称和平台相同则更新
     * @param map
     * @return
     */
    public String insertAll(Map map) {
        List<LiveShow> users = (List<LiveShow>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO live_show ");
        sb.append("(person_name, pic_url, type,live_title,show_num,msg_channel,live_url,is_show) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].personName}, #'{'list[{0}].picUrl}, #'{'list[{0}].type}, #'{'list[{0}].liveTitle}, #'{'list[{0}].showNum}, #'{'list[{0}].msgChannel},#'{'list[{0}].liveUrl},1)");
        for (int i = 0; i < users.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < users.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("ON DUPLICATE KEY UPDATE pic_url = VALUES(pic_url),type = VALUES(type),live_title = VALUES(live_title),show_num = VALUES(show_num),is_show = 1");
        return sb.toString();
    }
}
