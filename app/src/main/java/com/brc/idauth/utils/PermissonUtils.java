package com.brc.idauth.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-09
       Time     :  21:41
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class PermissonUtils {


    public static void requestCameraPermission(Activity activity) {

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            return;
        }
        //没有相机权限
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {

        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, 100);
        }

    }


    public static void requestPermmisson(Activity activity,String string) {

        if (ContextCompat.checkSelfPermission(activity,string) == PackageManager.PERMISSION_GRANTED) {
            return;
        }
        //没有相机权限
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,string)) {

        } else {
            ActivityCompat.requestPermissions(activity, new String[]{string}, 100);
        }

    }

}
