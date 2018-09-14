package com.zz.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

    public static String dataFormat(Date date){

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        String formatDate = df.format(date);
        return formatDate;
    }

}
