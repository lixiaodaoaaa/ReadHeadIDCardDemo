package com.daolion.base.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by lixiaodaoaaa on 2016/10/18.
 */
public class DensityUtils{
    public DensityUtils(){
    }
    
    
    /**
     * 将 dp转化为实际的px
     *
     * @param context
     * @param dp
     * @return px
     */
    public static int dp2px(Context context, float dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
    
    
    public static int pxTodip(Context context, float pxValue){
        float scale=context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5F);
    }
    
    public static int pxTosp(Context context, float pxValue, float fontScale){
        return (int) (pxValue / fontScale + 0.5F);
    }
    
    public static int spTopx(float spValue, float fontScale){
        return (int) (spValue * fontScale + 0.5F);
    }
    
    @SuppressLint({"NewApi"})
    public static int[] getScreenSize(Context context){
        int[] screenSize=new int[2];
        boolean measuredWidth=false;
        boolean measuredheight=false;
        Point size=new Point();
        WindowManager w=(WindowManager) context.getSystemService("window");
        int measuredWidth1;
        int measuredheight1;
        if (Build.VERSION.SDK_INT >= 13) {
            w.getDefaultDisplay().getSize(size);
            measuredWidth1=size.x;
            measuredheight1=size.y;
        }else {
            Display d=w.getDefaultDisplay();
            measuredWidth1=d.getWidth();
            measuredheight1=d.getHeight();
        }
        
        screenSize[0]=measuredWidth1;
        screenSize[1]=measuredheight1;
        return screenSize;
    }
}
