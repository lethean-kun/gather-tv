package com.zz.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zz.model.Parameter;
import com.zz.model.Result;
import com.zz.model.Twitter;
import com.zz.model.User;
import com.zz.service.TwitterService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dzk
 *         Created by lethean on 2018/3/2.
 */
@Controller
public class TwitterController {
    private final Logger logger = LoggerFactory.getLogger(TwitterController.class);

    @Resource
    TwitterService twitterService;

    @Value("${web.picupload-path}")
    private String picUploadPath;

    @Value("${web.return-path}")
    private String returnPath;

    @RequestMapping("toAllTwitter")
    public String toAllTwitter(HttpServletRequest request,
                               Parameter parameter) {
        //所有用户动态
        List<Twitter> twitters = twitterService.getAllTwitter();
        //筛选出三张图片下方显示
        for(Twitter twitter:twitters){
            List<String> imagesList= new ArrayList<>();
            String blogInfo=twitter.getFeeling();
            Document doc= Jsoup.parse(blogInfo);
            Elements jpgs=doc.select("img[src]"); //　查找图片
            for(int i=0;i<jpgs.size();i++){
                Element jpg=jpgs.get(i);
                logger.info(jpg.toString());
                String temp = jpg.toString();
                imagesList.add(temp);
                if(i==2){
                    break;
                }
            }
            twitter.setImagesList(imagesList);
        }
        request.setAttribute("twitters", twitters);

        return "users-twitter/allTwitter";
    }

    @RequestMapping("publishTwitter")
    public void publishTwitter(Twitter twitter,
                               HttpServletResponse response) throws IOException {
        twitterService.publishTwitter(twitter);
        response.sendRedirect("/toAllTwitter");
    }

    /**
     * 基于用户标识的头像上传
     *
     * @param file 图片
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "picUpload")
    public JSONObject picUpload(@RequestParam("file") MultipartFile file,
                                HttpServletRequest request) throws Exception {

        // 获取图片的文件名
        String fileName = file.getOriginalFilename();
        // 获取图片的扩展名
        String extensionName = StringUtils.substringAfter(fileName, ".");
        // 新的图片文件名 = 获取时间戳+"."图片扩展名
        String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;

        // 文件路径
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        //如果上传目录为/static/images/upload/，则可以如下获取：
//                    File upload = new File(path.getAbsolutePath(),webUploadPath);
//                    logger.info("upload url:"+upload.getAbsolutePath());
        String filePath = path.getAbsolutePath() + picUploadPath;
        logger.info("上传图片路径" + filePath);

        File dest = new File(filePath, newFileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        JSONObject json = new JSONObject();
        json.put("errno", 0);
        // 上传到指定目录
        file.transferTo(dest);

        List<String> list = new ArrayList<>();
        list.add(returnPath + "/" + newFileName);
        logger.info("返回地址为" + filePath);
        json.put("data", list);
        logger.info(json.toString());
        return json;
    }
}
