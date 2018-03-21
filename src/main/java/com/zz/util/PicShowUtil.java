package com.zz.util;

import com.zz.model.Twitter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class PicShowUtil {

    public static List<Twitter> forThreePic(List<Twitter> twitters) {


        //筛选出三张图片下方显示
        for (Twitter twitter : twitters) {
            List<String> imagesList = new ArrayList<>();
            String blogInfo = twitter.getFeeling();
            Document doc = Jsoup.parse(blogInfo);
            Elements jpgs = doc.select("img[src]"); //　查找图片
            for (int i = 0; i < jpgs.size(); i++) {
                Element jpg = jpgs.get(i);
                String temp = jpg.toString();
                imagesList.add(temp);
                if (i == 2) {
                    break;
                }
            }
            twitter.setImagesList(imagesList);
        }
        return twitters;
    }
}
