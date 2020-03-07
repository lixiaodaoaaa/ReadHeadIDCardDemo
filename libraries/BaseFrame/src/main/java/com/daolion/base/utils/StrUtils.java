package com.daolion.base.utils;

import java.util.ArrayList;
import java.util.List;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/10/26
       Time     :  13:36
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class StrUtils {
    
    
    public static ArrayList getStrArray(String value, String splitValue){
        ArrayList<String> arrayList = new ArrayList();
        String[] strs = value.split(splitValue);
        for (int i = 0; i < strs.length; i++) {
            arrayList.add(strs[i]);
        }
        return arrayList;
    }
    
    
    public static String getStrByListString(List<String> storePicPathList){
        StringBuilder sb = new StringBuilder();
        for (String picPath : storePicPathList) {
            sb.append(getFileNameByPath(picPath)).append(",");
        }
        return sb.toString().substring(0,sb.toString().length()-1);
    }
    
    public static String getFileNameByPath(String filePath){
        return filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
    }
}
