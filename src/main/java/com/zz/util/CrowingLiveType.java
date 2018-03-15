package com.zz.util;

import com.zz.model.LiveType;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dzk
 * Created by lethean on 2017/12/24.
 */
public class CrowingLiveType {

    @Value("${liveShow.zan-qi.url}")
    private static String url;

    @Value("${liveShow.zan-qi.name}")
    private static String ZanQi;

    public static List<LiveType> getLiveTypes() throws Exception {

        HttpClient client = ForHttpClient.getHttpClientInstance();
        //获取网站响应的html
        String html = CrowingLiveList.getRawHtml(client, url);
        List<LiveType> liveTypes = getData(html);

        return liveTypes;
    }

    public static List<LiveType> getData(String html) throws Exception {
        //获取的数据，存放在集合中
        List<LiveType> data = new ArrayList<LiveType>();
        //采用Jsoup解析
        Document doc = Jsoup.parse(html);
        //获取html标签中的内容
        Elements elements = doc.select("ul[class=clearfix]").select("li[class=js-game-list-item]");
        for (Element ele : elements) {
            String picurl = ele.select("a").select("div[class=img-box]").select("img").attr("src");
            String type = ele.select("a").select("p[class=name]").text();

            LiveType liveType = new LiveType();
            //对象的值
            liveType.setTypeName(type);
            liveType.setTypePic(picurl);
            liveType.setMsgChannel(ZanQi);
            //将每一个对象的值，保存到List集合中
            data.add(liveType);
        }
        //返回数据
        return data;
    }
}
