package com.daolion.base.utils;

import java.util.List;

/**
 * @author miaoxiongfei@foxmail.com
 * @date 2016-06-19 11:37
 */
public class ArrayUtil {
    
    public static boolean isEmpty(List list){
        return list == null || list.isEmpty();
    }
    
    public static void toString(List list){
        if (isEmpty(list)) {
            LogUtil.d("null");
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Object o : list) {
            stringBuilder.append(o.toString()).append(",");
        }
        LogUtil.d(stringBuilder.toString());
    }
    
    // 2. 通过HashSet踢除重复元素
  
    
}
