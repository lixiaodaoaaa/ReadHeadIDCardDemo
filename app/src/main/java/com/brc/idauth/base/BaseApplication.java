package com.brc.idauth.base;

import android.content.Context;

import com.daolion.base.control.FrameApplication;
import com.daolion.base.crash.BaseAppException;


/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/8/6
       Time     :  10:55
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class BaseApplication extends FrameApplication {
    
    
    private static BaseApplication singleton;

    public BaseApplication(){
        super();
    }
    
    
    public static Context getCtx(){
        return getInstance().getApplicationContext();
    }
    
    public static BaseApplication getInstance(){
        return singleton;
    }
    
    @Override
    public void onCreate(){
        super.onCreate();
        singleton = this;
        Thread.setDefaultUncaughtExceptionHandler(BaseAppException.getInstance(this));
    }
    
}
