package com.zz.util;

import java.text.DecimalFormat;

/**
 * @author dzk
 * Created by lethean on 2017/12/23.
 */
public class ShowNumFormat {

    public static String status = "万";
    public static Double limt = 10000.0;

    public static String forGetCount(String showNum){

        //如果带数字万则转化
        if(showNum.contains(status)){
            String[] nums = showNum.split(status);
            Double num = Double.parseDouble(nums[0])*10000;
            showNum = String.valueOf(num);
        }
        return showNum;
    }

    public static String returnCount(String showNum){

        //如果大于10000则转化为万显示
        if(Double.valueOf(showNum)>limt){

            Double num = Double.parseDouble(showNum)/10000;
            DecimalFormat df = new DecimalFormat("#.0");
            showNum = String.valueOf(df.format(num))+status;
        }
        return showNum;
    }

    public static void main(String[] args) {
        System.out.println(forGetCount("20000"));
        String s = "125555000.0";
        System.out.println(returnCount(s));
    }
}
