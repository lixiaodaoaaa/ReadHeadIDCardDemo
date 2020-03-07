package com.daolion.func;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;

import com.huashi.otg.sdk.HSIDCardInfo;
import com.huashi.otg.sdk.HsOtgApi;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.Executors;

import de.greenrobot.event.EventBus;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-11
       Time     :  00:48
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class IDCardSDK {


    private static final String TAG = IDCardSDK.class.getName();

    private static IDCardSDK idCardSDK;


    public static final int INIT_SUCCESS = 1;

    // 授权目录
    private String filepath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/wltlib";

    private HsOtgApi hsOtgApi;
    private boolean isAutoReadCard = true;
    private Handler handler;

    private IDCardSDK() {

    }

    public static IDCardSDK getInstance() {
        if (idCardSDK == null) {
            idCardSDK = new IDCardSDK();
        }
        return idCardSDK;
    }


    public int initSDK(Handler handler, Context context) {
        this.handler = handler;

        hsOtgApi = new HsOtgApi(handler, context);
        // 因为第一次需要点击授权，所以第一次点击时候的返回是-1所以我利用了广播接受到授权后用handler发送消息
        int ret = hsOtgApi.init();

        if (ret == INIT_SUCCESS) {
            //初始化成功 一直读卡
            Executors.newSingleThreadExecutor().execute(new AlwaysReadCardRunnable());
            Log.i("initSDK", "初始化 身份证读卡器成功");
        }
        return ret;
    }

    private class AlwaysReadCardRunnable implements Runnable {
        @Override
        public void run() {
            HSIDCardInfo icCardInfo;
            while (isAutoReadCard) {
                synchronized (hsOtgApi) {
                    //读取成功阻塞3秒
                    SystemClock.sleep(4 * 100);
                    if (hsOtgApi.Authenticate(200, 200) != 1) {
                        Log.i(TAG, "卡认证失败");
                        //SystemClock.sleep(1 * 1000);
                        SystemClock.sleep(1 * 1000);
                    } else {
                        icCardInfo = new HSIDCardInfo();
                        ReadCardEvent cardEvent = new ReadCardEvent();
                        if (hsOtgApi.ReadCard(icCardInfo, 200, 1300) == 1) {
                            int ret = hsOtgApi.Unpack(filepath, icCardInfo.getwltdata());// 照片解码
                            if (ret != 0) {
                                Log.i(TAG, "unzip  success");
                            }
                            cardEvent.setCardInfo(icCardInfo);
                            EventBus.getDefault().post(cardEvent);
                            Log.i(TAG, "读卡成功");
                            SystemClock.sleep(5 * 100);
                        }
                        else{
                            Log.i(TAG, "读卡shibai");
                        }
                        //SystemClock.sleep(3 * 1000);
                    }
                }
            }
        }
    }

    private Bitmap unZipHeadPic(HSIDCardInfo icCardInfo) {
        Bitmap bmp = null;
        int ret = hsOtgApi.Unpack(filepath, icCardInfo.getwltdata());// 照片解码
        if (ret != 0) {
            return bmp;
        }

        try {
            FileInputStream fis = new FileInputStream(filepath + "/zp.bmp");
            bmp = BitmapFactory.decodeStream(fis);
            fis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmp;
    }

}
