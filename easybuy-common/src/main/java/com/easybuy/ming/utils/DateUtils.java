package com.easybuy.ming.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ming on 2016/11/10.
 */
public class DateUtils {


    public static String DateToString(Date date){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       String dateTime= format.format(date);
        return dateTime;
    }
}
