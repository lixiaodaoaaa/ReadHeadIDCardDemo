package com.brc.idauth.utils;

import android.content.Context;
import android.widget.Toast;

import com.brc.idauth.Constants;
import com.brc.idauth.base.MainDataManager;
import com.daolion.base.utils.SharedPrefsUtils;
import com.daolion.base.utils.StringUtils;

import cn.face.FaceConStant;
import cn.face.sdk.FaceVersion;
import cn.face.sdkproc.LocalSDK;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-10
       Time     :  17:01
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class FaceSDKUtils {

    private static String APPKEY = "bd12dd468b58b2ce861bc8b84b503a3d";
    private static String APPSECRET = "25fd38b8df144ecdae32d5c4a91a4faa";
    private static String PRODUCTID = "030e400a-bcb2-11e8-9b2e-484d7eb93a7b";

    private final static int INIT_SUCCESS_RECT = 0;

    private static boolean isInitSuccess = false;


    public static void initSDK(Context context) {
        if (isInitSuccess) {
            return;
        }
        LocalSDK localSDDK = LocalSDK.getInstance(context); ;
        FaceVersion faceVersion = FaceVersion.getInstance(); ;


        String localFaceLicence = MainDataManager.getInstance().getLocalFaceLicence();

        if (StringUtils.isEmpty(localFaceLicence)) {
            //TODO 保存在线授权
            // String licence = FaceVersion.getInstance().cwGetLicence(APPKEY, APPSECRET, PRODUCTID);
            //Log.e("==========", licence);
            SharedPrefsUtils.setStringPreference(context, Constants.KEY_FACE_LICENCE, "");
        }


        //申请在线授权
//        onLineRequestLicence();
        // 创建句柄，句柄只需要创建一次，程序启动创建，退出时再销毁


        int initResult = localSDDK.CreateHandles(FaceConStant.sLicence, FaceConStant.faceMinSize, FaceConStant.faceMaxSize, FaceConStant.sModelDir);
        if (initResult != INIT_SUCCESS_RECT) {
            // 创建句柄失败，请根据错误码检测原因
            Toast.makeText(context, "人脸sdk 授权失败，请检查：" + initResult, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "人脸sdk 授权成功，当前SDK版本为" + faceVersion, Toast.LENGTH_SHORT).show();
        }
    }
}
