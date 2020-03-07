package com.daolion.base.control;

import android.app.Application;
import android.content.Context;

import com.daolion.base.crash.BaseAppException;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-04
       Time     :  17:32
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class FrameApplication extends Application {


    private static FrameApplication singleton;

    public FrameApplication(){
        super();
    }


    public static Context getCtx(){
        return getInstance().getApplicationContext();
    }

    public static FrameApplication getInstance(){
        return singleton;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        singleton = this;
        Thread.setDefaultUncaughtExceptionHandler(BaseAppException.getInstance(this));
    }
}
