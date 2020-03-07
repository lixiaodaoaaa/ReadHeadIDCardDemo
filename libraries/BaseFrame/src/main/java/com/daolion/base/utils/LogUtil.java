package com.daolion.base.utils;

import android.text.TextUtils;
import android.util.Log;

import com.ll.base.BuildConfig;

import java.util.Locale;

/**
 * 日志打印类，集成android.util.Log 的基础函数
 * @author miaoxiongfei@foxmail.com
 * @date 2016-03-28 11:07
 */
public class LogUtil{


    public static boolean LOG = BuildConfig.DEBUG;  // 是否打印日志,默认在debug模式下打印

    public static int LOG_LEVEL = Log.VERBOSE;        // 控制打印级别,默认DEBUG及以上级别打印

//    public static int WRITE_LEVEL = Log.ERROR;      //日志写入级别
//    private static String defaultPath = "";         //日志写入本地文件路径

    public static void v(String msg) {
        if (LOG && LOG_LEVEL <= Log.VERBOSE) {
            Log.i(getTag(null), buildMessage(msg));
        }
    }

    public static void v(String tag, String... msg) {
        if (LOG && LOG_LEVEL <= Log.VERBOSE) {
            Log.i(getTag(tag), buildMessage(msg));
        }
    }

    public static void i(String msg) {
        if (LOG && LOG_LEVEL <= Log.INFO) {
            Log.i(getTag(null), buildMessage(msg));
        }
    }

    public static void i(String tag, String... msg) {
        if (LOG && LOG_LEVEL <= Log.INFO) {
            Log.i(getTag(tag), buildMessage(msg));
        }
    }

    public static void d(String msg) {
        if (LOG && LOG_LEVEL <= Log.DEBUG ) {
            Log.d(getTag(null), buildMessage(msg));
        }
    }

    public static void d(String tag, String... msg) {
        if (LOG && LOG_LEVEL <= Log.DEBUG ) {
            Log.d(getTag(tag), buildMessage(msg));
        }
    }

    public static void w(String msg) {
        if (LOG && LOG_LEVEL <= Log.WARN ) {
            Log.w(getTag(null), buildMessage(msg));
        }
    }

    public static void w(String tag, String... msg) {
        if (LOG && LOG_LEVEL <= Log.WARN ) {
            Log.w(getTag(tag), buildMessage(msg));
        }
    }

    public static void e(String msg) {
        if (LOG && LOG_LEVEL <= Log.ERROR ) {
            Log.e(getTag(null), buildMessage(msg));
        }
    }

    public static void e(String tag, String... msg) {
        if (LOG && LOG_LEVEL <= Log.ERROR ) {
            Log.e(getTag(tag), buildMessage(msg));
        }
    }

    public static void e(String tag, String msg, Exception e) {
        if (LOG && LOG_LEVEL <= Log.ERROR ) {
            Log.e(getTag(tag), buildMessage(msg), e);
        }
    }

    public static void e(String tag, String msg, Throwable t) {
        if (LOG && LOG_LEVEL <= Log.ERROR ) {
            Log.e(getTag(tag), buildMessage(msg), t);
        }
    }

    public static void wtf(String msg){
        if (LOG) {
            Log.wtf(getTag(null), buildMessage(msg));
        }
    }

    private static String getTag(String tag) {
        if(!TextUtils.isEmpty(tag)){
            return tag;
        }
        StackTraceElement[] trace = new Throwable().fillInStackTrace()
                                                   .getStackTrace();
        String callingClass = "";
        for (int i = 2; i < trace.length; i++) {
            Class clazz = trace[i].getClass();
            if (!LogUtil.class.equals(clazz)) {
                callingClass = trace[i].getClassName();
                callingClass = callingClass.substring(callingClass
                        .lastIndexOf('.') + 1);
                break;
            }
        }
        return callingClass;
    }

    private static String buildMessage(String... msg) {

        if(null == msg || msg.length == 0){
            return "";
        }

        StackTraceElement[] trace = new Throwable().fillInStackTrace()
                                                   .getStackTrace();
        String caller = "";
        for (int i = 2; i < trace.length; i++) {
            Class clazz = trace[i].getClass();
            if (!clazz.equals(LogUtil.class)) {
                caller = trace[i].getMethodName();
                break;
            }
        }
        if(msg.length == 1){
            return String.format(Locale.US, "[%s] %s: %s", Thread.currentThread().getName(), caller, msg[0]);
        }else{
            StringBuilder builder = new StringBuilder();
            for(int i=0;i<msg.length;i++){
                builder.append(msg[i]);
            }
            return String.format(Locale.US, "[%s] %s: %s", Thread.currentThread().getName(), caller, builder.toString());
        }
    }


}
