package com.zz.util;

import com.zz.model.LiveShow;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dzk
 * Created by lethean on 2017/12/22.
 */
public class CrowingLiveList {

    public static String HuYa = "虎牙直播";
    public static String QuanMin = "全民直播";
    public static String ZanQi = "战旗直播";
    public static String LongZhu = "龙珠直播";

    public static void main(String[] args) throws Exception {
        crowingHuya();
    }

    /**
     * 先简单写 实现功能 之后优化 相关写入配置文件
     * @return
     * @throws Exception
     */
    public static List<LiveShow> crowingHuya() throws Exception {

        //初始化一个httpclient
        HttpClient client = new DefaultHttpClient();
        //我们要爬取的一个地址，这里可以从数据库中抽取数据，然后利用循环，可以爬取一个URL队列
        String url = "http://www.huya.com/l?areafib=1";
        //抓取的数据
        List<LiveShow> liveShows = URLParser(client, url);
        return liveShows;

    }

    public static List<LiveShow> URLParser(HttpClient client, String url) throws Exception {
        //用来接收解析的数据
        List<LiveShow> Data = new ArrayList<LiveShow>();
        //获取网站响应的html，这里调用了HTTPUtils类
        String html = getRawHtml(client, url);

        Data = getData(html);

        return Data;
    }

    public static String getRawHtml(HttpClient client, String personalUrl) throws IOException {
        //获取响应文件，即html，采用get方法获取响应数据
        HttpGet getMethod = new HttpGet(personalUrl);
        HttpResponse response;
        response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
        try {
            //执行get方法
            response = client.execute(getMethod);
            int StatusCode = response.getStatusLine().getStatusCode();
            //如果状态响应码为200，则获取html实体内容或者json文件
            if (StatusCode == 200) {
                String html = EntityUtils.toString(response.getEntity(), "utf-8");
                EntityUtils.consume(response.getEntity());
                return html;
            } else {
                EntityUtils.consume(response.getEntity());
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            // getMethod.abort();
        }
        return null;
    }

    public static List<LiveShow> getData(String html) throws Exception {
        //获取的数据，存放在集合中
        List<LiveShow> data = new ArrayList<LiveShow>();
        //采用Jsoup解析
        Document doc = Jsoup.parse(html);
        //获取html标签中的内容
        Elements elements = doc.select("ul[class=live-list clearfix areafib]").select("li[class=game-live-item]");
        for (Element ele : elements) {
            String url = ele.select("a[class=video-info new-clickstat]").attr("href");
            String picurl = ele.select("a[class=video-info new-clickstat]").select("img[class=pic]").attr("data-original");
            String tilte = ele.select("a[class=title new-clickstat]").attr("title");
            String name = ele.select("span[class=txt]").select("span[class=avatar fl]").select("i").text();
            String headpic = ele.select("span[class=txt]").select("span[class=avatar fl]").select("img").attr("src");
            String type = ele.select("span[class=txt]").select("span[class=game-type fr]").select("a").text();
            String livenum = ele.select("span[class=txt]").select("span[class=num]").select("i[class=js-num]").text();

            //创建一个对象，这里可以看出，使用Model的优势，直接进行封装
            LiveShow liveShow = new LiveShow();
            //对象的值
            liveShow.setPicUrl(picurl);
            liveShow.setPersonName(name);
            liveShow.setLiveTitle(tilte);
            liveShow.setLiveUrl(url);
            liveShow.setType(type);
            liveShow.setShowNum(ShowNumFormat.forGetCount(livenum));
            liveShow.setMsgChannel(HuYa);
            //将每一个对象的值，保存到List集合中
            data.add(liveShow);
        }
        //返回数据
        return data;
    }
}
