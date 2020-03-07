package com.brc.idauth.ui.splash;

import android.util.Log;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-18
       Time     :  21:22
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class SplashPresenter extends SplashContract.Presenter {

    private String TAG = SplashPresenter.class.getName();


    protected SplashPresenter(SplashContract.View mView, SplashContract.Model mModel) {
        super(mView, mModel);
    }

    @Override
    void registDevice() {
        rxManager.add(schedule(mModel.registerDevice()).subscribe(stringResp -> {
            Log.i("response", stringResp.toString());
            mView.registerDeviceResult(true);
            Log.i(TAG,"设备注册成功.....");
        }, throwable -> {
            Log.i("response", throwable.getMessage());
            mView.registerDeviceResult(true);
            Log.i(TAG,"设备注册失败.....");
        }));


    }
}
