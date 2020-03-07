package com.brc.idauth.ui.splash;

import android.util.Log;

import com.brc.idauth.base.MainDataManager;
import com.brc.idauth.bean.DeviceBean;
import com.daolion.net.request.RequestUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import okhttp3.RequestBody;
import rx.Observable;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-18
       Time     :  21:28
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class SplashModel implements SplashContract.Model {
    @Override
    public Observable<JsonObject> registerDevice() {
        ArrayList<DeviceBean> deviceList = new ArrayList<>();
        deviceList.add(MainDataManager.getInstance().getAppDeviceBean());

        String postStr = new Gson().toJson(deviceList);
        System.out.println("putMsg is " + postStr);
        Log.i("request", postStr);
        RequestBody requestBody = RequestUtils.getJsonRequestBody(postStr);
        return apiService.registDevice(requestBody);
    }
}
