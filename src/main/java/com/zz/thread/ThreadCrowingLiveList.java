package com.zz.thread;

import com.zz.model.LiveShow;
import com.zz.util.ForHttpClient;
import com.zz.util.ShowNumFormat;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author dzk
 * Created by lethean on 2017/12/22.
 */
@Component
public class ThreadCrowingLiveList {

    private final Logger logger = LoggerFactory.getLogger(ThreadCrowingLiveList.class);

    @Value("${liveShow.hu-ya.name}")
    private String HuYa;

    @Value("${liveShow.quan-min.name}")
    private String QuanMin;

    @Value("${liveShow.zan-qi.name}")
    private String ZanQi;

    @Value("${liveShow.long-zhu.name}")
    private String LongZhu;

    @Value("${liveShow.hu-ya.url}")
    private String HuYaUrl;

    @Value("${liveShow.quan-min.url}")
    private String QuanMinUrl;

    @Value("${liveShow.zan-qi.url}")
    private String ZanQiUrl;

    @Value("${liveShow.long-zhu.url}")
    private String LongZhuUrl;


    private List<LiveShow> allLiveShow = Collections.synchronizedList(new LinkedList<LiveShow>());
    /**
     * 先简单写 实现功能 之后优化 相关写入配置文件
     *
     * @return
     * @throws Exception
     */
    @Async("getExecutor")
    public Future<List<LiveShow>> crowingHuya() throws Exception {

        HttpClient client = ForHttpClient.getHttpClientInstance();
        //获取网站响应的html，这里调用了HTTPUtils类
        String html = getRawHtml(client, HuYaUrl);
        //抓取的数据
        List<LiveShow> liveShows = getHuYaData(html);
        allLiveShow.addAll(liveShows);
        logger.info("爬取完毕-HuYa");
        return new AsyncResult<>(liveShows);

    }

    @Async("getExecutor")
    public Future<List<LiveShow>> crowingLongZhu() throws Exception {

        HttpClient client = ForHttpClient.getHttpClientInstance();
        String html = getRawHtml(client, LongZhuUrl);
        List<LiveShow> liveShows = getLongZhuData(html);
        allLiveShow.addAll(liveShows);
        logger.info("爬取完毕-LongZhu");

        return new AsyncResult<>(liveShows);


    }

    @Async("getExecutor")
    public Future<List<LiveShow>> crowingZanQi() throws Exception {

        HttpClient client = ForHttpClient.getHttpClientInstance();
        String html = getRawHtml(client, ZanQiUrl);
        List<LiveShow> liveShows = getZanQiData(html);
        allLiveShow.addAll(liveShows);
        logger.info("爬取完毕-ZanQi");

        return new AsyncResult<>(liveShows);

    }

    @Async("getExecutor")
    public Future<List<LiveShow>> crowingQuanMin() throws Exception {

        HttpClient client = ForHttpClient.getHttpClientInstance();
        String html = getRawHtml(client, QuanMinUrl);
        List<LiveShow> liveShows = getQuanMinData(html);
        allLiveShow.addAll(liveShows);
        logger.info("爬取完毕-QuanMin");

        return new AsyncResult<>(liveShows);

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

    public  List<LiveShow> getHuYaData(String html) throws Exception {
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

    public  List<LiveShow> getLongZhuData(String html) throws Exception {
        //获取的数据，存放在集合中
        List<LiveShow> data = new ArrayList<LiveShow>();
        //采用Jsoup解析
        Document doc = Jsoup.parse(html);
        //获取html标签中的内容
        Elements elements = doc.select("div[class=list-con]").select("a[class=livecard]");
        for (Element ele : elements) {
            String url = ele.attr("id");
            String picurl = ele.select("img[class=livecard-thumb ]").attr("src");
            String tilte = ele.select("h3[class=listcard-caption]").text();
            String name = ele.select("span[class=livecard-modal]").select("strong[class=livecard-modal-username]").text();
            String headpic = ele.select("img[class=livecard-avatar]").attr("src");
            String type = ele.select("ul[class=livecard-meta]").select("li[class=livecard-meta-item livecard-meta-game]").select("span[class=livecard-meta-item-text]").text();
            String livenum = ele.select("ul[class=livecard-meta]").select("li[class=livecard-meta-item livecard-meta-views]").select("span[class=livecard-meta-item-text]").text();

            //创建一个对象，这里可以看出，使用Model的优势，直接进行封装
            LiveShow liveShow = new LiveShow();
            //对象的值
            liveShow.setPicUrl(picurl);
            liveShow.setPersonName(name);
            liveShow.setLiveTitle(tilte);
            liveShow.setLiveUrl(url);
            liveShow.setType(type);
            liveShow.setShowNum(ShowNumFormat.forGetCount(livenum));
            liveShow.setMsgChannel(LongZhu);
            //将每一个对象的值，保存到List集合中
            data.add(liveShow);
        }
        //返回数据
        return data;
    }

    public  List<LiveShow> getZanQiData(String html) throws Exception {
        //获取的数据，存放在集合中
        List<LiveShow> data = new ArrayList<LiveShow>();
        //采用Jsoup解析
        Document doc = Jsoup.parse(html);
        //获取html标签中的内容
        Elements elements = doc.select("ul[class=clearfix js-room-list-ul]").select("li");
        for (Element ele : elements) {
            String url = ele.attr("data-room-id");
            String picurl = ele.select("a[class=js-jump-link]").select("div[class=imgBox]").select("img").attr("src");
            String tilte = ele.select("a[class=js-jump-link]").select("div[class=info-area]").select("span[class=name]").text();
            String name = ele.select("a[class=js-jump-link]").select("div[class=info-area]").select("div[class=meat]").select("span[class=anchor anchor-to-cut dv]").text();
//            String headpic = ele.select("img[class=livecard-avatar]").attr("src");
            String type = ele.select("a[class=js-jump-link]").select("div[class=info-area]").select("div[class=meat]").select("span[class=game-name dv]").text();
            String livenum = ele.select("a[class=js-jump-link]").select("div[class=info-area]").select("div[class=meat]").select("span[class=views]").select("span[class=dv]").text();

            //创建一个对象，这里可以看出，使用Model的优势，直接进行封装
            LiveShow liveShow = new LiveShow();
            //对象的值
            liveShow.setPicUrl(picurl);
            liveShow.setPersonName(name);
            liveShow.setLiveTitle(tilte);
            liveShow.setLiveUrl(url);
            liveShow.setType(type);
            liveShow.setShowNum(ShowNumFormat.forGetCount(livenum));
            liveShow.setMsgChannel(ZanQi);
            //将每一个对象的值，保存到List集合中
            data.add(liveShow);
        }
        //返回数据
        return data;
    }

    public  List<LiveShow> getQuanMinData(String html) throws Exception {
        //获取的数据，存放在集合中
        List<LiveShow> data = new ArrayList<LiveShow>();
        //采用Jsoup解析
        Document doc = Jsoup.parse(html);
        //获取html标签中的内容
        Elements elements = doc.select("ul[class=list_w-videos_video-list]").select("li[class=list_w-video]");
        for (Element ele : elements) {
            String url = ele.select("div").select("div[class=common_w-card]").select("a[class=common_w-card_href]").attr("href");
            String picurl = ele.select("div").select("div[class=common_w-card]").select("a[class=common_w-card_href]").select("div[class=common_w-card_cover-wrap]").select("img[class=common_w-card_cover]").attr("src");
            String tilte = ele.select("div").select("div[class=common_w-card]").select("a[class=common_w-card_href]").select("div[class=common_w-card_bottom]").select("div[class=common_w-card_bottom-no-avatar]").select("p[class=common_w-card_title]").text();
            String name = ele.select("div").select("div[class=common_w-card]").select("a[class=common_w-card_href]").select("div[class=common_w-card_bottom]").select("div[class=common_w-card_bottom-no-avatar]").select("div[class=common_w-card_info]").select("span[class=common_w-card_host-name]").text();
            //保留 String headpic = ele.select("img[class=livecard-avatar]").attr("src");
            String type = ele.select("div").select("div[class=common_w-card]").select("a[class=common_w-card_category]").text();
            String livenum = ele.select("div").select("div[class=common_w-card]").select("a[class=common_w-card_href]").select("div[class=common_w-card_bottom]").select("div[class=common_w-card_bottom-no-avatar]").select("div[class=common_w-card_info]").select("span[class=common_w-card_views-num]").text();
            if (livenum != null && livenum.length() > 0) {
                //创建一个对象，这里可以看出，使用Model的优势，直接进行封装
                LiveShow liveShow = new LiveShow();
                //对象的值
                liveShow.setPicUrl(picurl);
                liveShow.setPersonName(name);
                liveShow.setLiveTitle(tilte);
                liveShow.setLiveUrl(url);
                liveShow.setType(type);
                liveShow.setShowNum(ShowNumFormat.forGetCount(livenum));
                liveShow.setMsgChannel(QuanMin);
                //将每一个对象的值，保存到List集合中
                data.add(liveShow);
            }
        }
        //返回数据
        return data;
    }

    public List<LiveShow> getAllLiveShow() {
        return allLiveShow;
    }

    public void setAllLiveShow(List<LiveShow> allLiveShow) {
        this.allLiveShow = allLiveShow;
    }
}
