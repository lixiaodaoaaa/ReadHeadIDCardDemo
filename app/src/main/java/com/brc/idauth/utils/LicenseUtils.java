package com.brc.idauth.utils;

import android.text.TextUtils;
import com.brc.idauth.net.ApiManager;
import com.daolion.base.utils.SPUtil;
import org.json.JSONException;
import org.json.JSONObject;
import cn.face.sdk.FaceVersion;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LicenseUtils {
    public static final String LICENSE = "LICENSE";
    private static LicenseUtils mLicenseUtils = new LicenseUtils();
    public static LicenseUtils getInstance(){
        return mLicenseUtils;
    }
    LicenseCallBack callBack;
    public interface LicenseCallBack{
        void getLicense(String license);
        void error(String error);
    }
    public void getLicense(LicenseCallBack callBack){
        if (null == callBack)
            return;
        this.callBack = callBack;
        if (!SPUtil.getInstance().getValue(LICENSE,"").equals("")){
            this.callBack.getLicense(SPUtil.getInstance().getValue(LICENSE,""));
        }else {
            requestLiscenseFromService();
        }
    }

    private void requestLiscenseFromService(){
        ApiManager.getInstance().getApiService().requestLiscense()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JSONObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.error(e.toString());
                    }

                    @Override
                    public void onNext(JSONObject jsonObject) {
                        try {
                            String licence = jsonObject.getString("");
                            if (!TextUtils.isEmpty(licence)){
                                SPUtil.getInstance().setValue(LICENSE,licence);
                                callBack.getLicense(licence);
                            }else{
                                onLineRequestLicenceFromCW();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            callBack.error(e.toString());
                        }
                    }
                });
    }

    private void onLineRequestLicenceFromCW() {
        String appKey = "bd12dd468b58b2ce861bc8b84b503a3d";
        String appSecret = "25fd38b8df144ecdae32d5c4a91a4faa";
        String productId = "030e400a-bcb2-11e8-9b2e-484d7eb93a7b";
        String licence = FaceVersion.getInstance().cwGetLicence(appKey, appSecret, productId);
        if (!TextUtils.isEmpty(licence))
            SPUtil.getInstance().setValue(LICENSE,licence);
        this.callBack.getLicense(licence);
        uploadLicence(licence);
    }

    private void uploadLicence(String licence){
        JSONObject body = new JSONObject();
//        body.put("licence",licence);
        ApiManager.getInstance().getApiService().uploadLicence(new JSONObject())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JSONObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(JSONObject jsonObject) {

                    }
                });
    }
}
