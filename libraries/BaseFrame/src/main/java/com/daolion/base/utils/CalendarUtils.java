package com.digops.utils;

import java.util.Calendar;
import java.util.Date;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/8/16
       Time     :  19:56
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class CalendarUtils{
    public static Calendar getAfterThreeYearCalendar(){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, 3);
        return calendar;
    }
    
    public static Calendar getCurrentCalendar(){
        return Calendar.getInstance();
    }
}
