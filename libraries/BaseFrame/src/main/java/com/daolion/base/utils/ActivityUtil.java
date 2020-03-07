package com.daolion.base.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author miaoxiongfei@foxmail.com
 * @date 2016-06-23 15:58
 */
public class ActivityUtil{
    
    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     */
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId){
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        if (fragment.isAdded()) {
            transaction.show(fragment);
        }else {
            transaction.replace(frameId, fragment);
        }
//        transaction.addToBackStack(null);
        transaction.commit();
    }
    
    /**
     * @param context
     * @param intent
     */
    public static void switchTo(Context context, Intent intent){
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        context.startActivity(intent);
    }
    
    
    /**
     * 退出应用
     *
     * @param activity
     */
    public static void exitApp(Activity activity){
        if (null != activity) {
            activity.finish();
        }
    }
    
    
    public static void switchTo(Activity activity, Class<? extends Activity> target, int requestCode){
        Intent intent=new Intent(activity, target);
        activity.startActivityForResult(intent, requestCode);
        activity.finish();
    }
    
    
    public static void setValueToIntent(Intent intent, String key, Object val){
        if (null == key || null == val) return;
        if (val instanceof Boolean)
            intent.putExtra(key, (Boolean) val);
        else if (val instanceof Boolean[])
            intent.putExtra(key, (Boolean[]) val);
        else if (val instanceof String)
            intent.putExtra(key, (String) val);
        else if (val instanceof String[])
            intent.putExtra(key, (String[]) val);
        else if (val instanceof Integer)
            intent.putExtra(key, (Integer) val);
        else if (val instanceof Integer[])
            intent.putExtra(key, (Integer[]) val);
        else if (val instanceof Long)
            intent.putExtra(key, (Long) val);
        else if (val instanceof Long[])
            intent.putExtra(key, (Long[]) val);
        else if (val instanceof Double)
            intent.putExtra(key, (Double) val);
        else if (val instanceof Double[])
            intent.putExtra(key, (Double[]) val);
        else if (val instanceof Float)
            intent.putExtra(key, (Float) val);
        else if (val instanceof Float[])
            intent.putExtra(key, (Float[]) val);
        else {
            throw new IllegalArgumentException("Not support data Type!");
        }
    }
    
    
    private static void setTranslucentStatus(Activity activity, boolean on){
        Window win=activity.getWindow();
        WindowManager.LayoutParams winParams=win.getAttributes();
        final int bits=WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags|=bits;
        }else {
            winParams.flags&=~bits;
        }
        win.setAttributes(winParams);
    }
    
    
    public static Intent buildGalleryIntent(Uri outputUri){
        Intent intent=null;
        
        if (Build.VERSION.SDK_INT < 19) {
            intent=new Intent(Intent.ACTION_GET_CONTENT);
        }else {
            intent=new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }
        intent.setType("image/*");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        return intent;
    }
    
    
    /**
     * 获取含包名的类名
     *
     * @param context
     * @param simpleName 不含包名的类名
     * @return
     */
    public static String getFullClassNameBySimpleClassName(Context context, String simpleName){
        String fullClassName=null;
        try {
            String packageName=context.getPackageName();
            PackageInfo pInfo=context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            ActivityInfo[] aInfos=pInfo.activities;
            if (aInfos != null) {
                for (ActivityInfo activityInfo : aInfos) {
                    String name=activityInfo.name;
                    if (getClassSimpleName(name).equals(simpleName)) {
                        fullClassName=name;
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return fullClassName;
    }
    
    /**
     * 获取不含包名的类名
     *
     * @param name 类全名(含包名)
     * @return
     */
    private static String getClassSimpleName(String name){
        String simpleName=name.substring(name.lastIndexOf(".") + 1);
        return simpleName;
    }
}
