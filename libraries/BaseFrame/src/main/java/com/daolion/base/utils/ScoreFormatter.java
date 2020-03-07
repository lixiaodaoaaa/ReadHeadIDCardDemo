package com.daolion.base.utils;

import java.text.DecimalFormat;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/10/23
       Time     :  10:42
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class ScoreFormatter {
    
    public static String getShowValue(float score){
        if(score==0){
            return "0.00";
        }
        DecimalFormat decimalFormat ;
        if(score<10){
            decimalFormat = new DecimalFormat("0.00");
        }else{
            decimalFormat = new DecimalFormat("00.00");
        }
        return decimalFormat.format(score);
    }
}
