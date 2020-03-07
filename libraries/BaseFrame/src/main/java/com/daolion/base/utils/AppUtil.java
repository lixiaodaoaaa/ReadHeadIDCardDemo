package com.digops.utils;

import android.content.pm.PackageManager;

import com.daolion.base.control.FrameApplication;


/**
 *
 */
public class AppUtil {
    
    
    public static final String getCurrentVersionName(){
        try {
            FrameApplication appCation = FrameApplication.getInstance();
            PackageManager packageManger = appCation.getPackageManager();
            return packageManger.getPackageInfo(appCation.getPackageName(), 0).versionName;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
}
