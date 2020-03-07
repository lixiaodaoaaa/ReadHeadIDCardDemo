package com.brc.idauth.api;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.InputStream;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/11/26
       Time     :  20:50
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class CentificationUtils {
    
    public static InputStream loadCentiFile(Context context){
        AssetManager assetManager = context.getAssets();
        try {
            return assetManager.open("_.adenservices.com_ca.crt");
        } catch (Exception e) {
            return null;
        }
    }
}
