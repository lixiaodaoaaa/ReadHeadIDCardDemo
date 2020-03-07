package com.brc.idauth.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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
    
    public static final SimpleDateFormat BIRTHDAY = new SimpleDateFormat("yyyy-MM-dd");
    
    public static final SimpleDateFormat CURRENT_WORK_ORDER_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
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
    
    public static String toBirthDay(Date date){
        return BIRTHDAY.format(date);
    }
    

    
    public static String formLongToOrderTime(long timeMillions){
        return CURRENT_WORK_ORDER_FORMATTER.format(new Date(timeMillions));
    }
    
    public static String getLogContentDateFormat(){
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return LOG_CONTENT_FORMATTER.format(curDate);
    }

    /**
     * 获取前n天日期、后n天日期
     *
     * @param distanceDay 前几天 如获取前7天日期则传-7即可；如果后7天则传7
     * @return
     */
    public static long getOldDate(int distanceDay) {
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay);
        return date.getTime().getTime();
    }

    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static void get(String strTime,String formatType) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = formatter.parse(strTime);

    }

    public static String stringData(){
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if("1".equals(mWay)){
            mWay ="天";
        }else if("2".equals(mWay)){
            mWay ="一";
        }else if("3".equals(mWay)){
            mWay ="二";
        }else if("4".equals(mWay)){
            mWay ="三";
        }else if("5".equals(mWay)){
            mWay ="四";
        }else if("6".equals(mWay)){
            mWay ="五";
        }else if("7".equals(mWay)){
            mWay ="六";
        }
        return mYear + "-" + mMonth + "-" + mDay+"\n"+"星期"+mWay;
    }


}


