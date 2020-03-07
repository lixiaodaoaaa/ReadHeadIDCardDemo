package com.daolion.base.utils;

import android.content.Context;
import android.widget.TextView;

import com.daolion.base.control.FrameApplication;


/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019/1/21
       Time     :  11:24
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class WidgetUtils {
    
    
    /*
        设置TextView drawable Right icon is
     */
    public  static  void setTvDrawableRight(TextView textView, int id){
        Context context = FrameApplication.getCtx();
        textView.setCompoundDrawablesWithIntrinsicBounds(null, null, ResourceReader.readDrawable(context, id), null);
        textView.setCompoundDrawablePadding(DensityUtils.dp2px(context, 5));
    }
}
