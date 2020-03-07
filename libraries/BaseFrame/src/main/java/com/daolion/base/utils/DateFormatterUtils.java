package com.digops.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/9/5
       Time     :  15:33
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class DateFormatterUtils {
    
    
    public static final SimpleDateFormat FORMATTER_SERVER_ONE = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS",
                                                                                     Locale.getDefault());
    
    public static final SimpleDateFormat CLIENT_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public static final SimpleDateFormat CURRENT_WORK_ORDER_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    public static final SimpleDateFormat LOCAL_SHOW = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    private static final SimpleDateFormat LOG_CONTENT_FORMATTER = new SimpleDateFormat("MM/dd-HH:mm:ss",
                                                                                       Locale.getDefault());
    
    
    public static String fDateToServ(long curMillions){
        return FORMATTER_SERVER_ONE.format(new Date(curMillions));
    }
    
    public static String dateToTemTaskDateStr(Date date){
        return FORMATTER_SERVER_ONE.format(date);
    }
    
    public static String dateToShow(Date date){
        return LOCAL_SHOW.format(date);
    }
    
    public static String formattDateToServerStrDate(Date date){
        return CLIENT_FORMATTER.format(date);
    }
    
    
    public static String formatterLongTimeToStrDate(long timeMillions){
        return CLIENT_FORMATTER.format(new Date(timeMillions));
    }
    
    public static String formLongToOrderTime(long timeMillions){
        return CURRENT_WORK_ORDER_FORMATTER.format(new Date(timeMillions));
    }
    
    public static String getLogContentDateFormat(){
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return LOG_CONTENT_FORMATTER.format(curDate);
    }
    
}


