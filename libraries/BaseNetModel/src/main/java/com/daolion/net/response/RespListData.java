package com.daolion.net.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/9/3
       Time     :  20:27
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */


public class RespListData<T> extends ResponseModel implements Serializable {
    
    @SerializedName("content") private ArrayList<T> data;
    
    
    public RespListData(){
        this.data = new ArrayList<>();
    }
    
    public ArrayList<T> getData(){
        return data;
    }
    
    
    @Override
    public String toString(){
        String result = "";
        StringBuilder sb = new StringBuilder();
        for (T t : data) {
            sb.append(t.toString());
        }
        return "RespListData" + sb.toString();
    }
}
    
