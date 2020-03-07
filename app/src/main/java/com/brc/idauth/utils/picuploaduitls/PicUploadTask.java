package com.brc.idauth.utils.picuploaduitls;

import android.util.Log;
import com.brc.idauth.base.BaseApplication;
import com.brc.idauth.base.MainDataManager;
import com.brc.idauth.base.RxManager;
import com.brc.idauth.bean.Convert.DeviceModelConvert;
import com.brc.idauth.bean.DeviceBean;
import com.brc.idauth.bean.DeviceModel;
import com.brc.idauth.bean.EventPara;
import com.brc.idauth.bean.IdCardBean;
import com.brc.idauth.net.ApiManager;
import com.brc.idauth.ui.verify.VerifyModel;
import com.daolion.base.control.ObservableSchedule;
import com.daolion.net.APIService;
import com.daolion.net.response.PictureInfo;
import com.daolion.net.response.Resp;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import rx.Observable;
import rx.Observer;

public class PicUploadTask {
    APIService apiService = ApiManager.getInstance().getApiService();

    VerifyModel picUpModel = new VerifyModel();

    String faceUrl = "", headUrl = "";


    private static PicUploadTask instance = new PicUploadTask();

    public static PicUploadTask getInstance() {
        return instance;
    }

    public void run() {
        if (!NetUtil.isNetworkAvailable(BaseApplication.getCtx())) {
            return;
        }

        PicUploadDatabase.getInstance().getPicUploadDao().select(false)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .subscribe(new SingleObserver<List<PicUploadInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<PicUploadInfo> picUploadInfos) {
                        for (PicUploadInfo info : picUploadInfos) {
                            uploadPicture(info, "picA");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void uploadPicture(PicUploadInfo picUploadInfo, String type) {
        String filePath = type.equals("picA") ? picUploadInfo.picA : picUploadInfo.picB;
        if (filePath.equals(""))
            return;
        picUpModel.uploadPicture(filePath).subscribe(new Observer<Resp<PictureInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("=====", e.toString());
            }

            @Override
            public void onNext(Resp<PictureInfo> pictureInfoResp) {
                if (type.equals("picA")) {
                    faceUrl = pictureInfoResp.getData().getUrl();
                    PicUploadDBUtil.getInstance().updateAstate(true, picUploadInfo.id);
                    uploadPicture(picUploadInfo, "picB");
                } else if (type.equals("picB")) {
                    headUrl = pictureInfoResp.getData().getUrl();
                    PicUploadDBUtil.getInstance().updateBstate(true, picUploadInfo.id);
                    uploadEvent(picUploadInfo.info, picUploadInfo.id);
                }
            }
        });
    }

    public void uploadEvent(IdCardBean idCardBean, int id) {
        // caseId 先设置为死的  207ad25c6126712b42cb82db92009aeb
        String caseId = "207ad25c6126712b42cb82db92009aeb";
        //TODO
        /*
                    private String caseId;
                    private String deviceNo;
                    private long attestTime;
                    private String spotImg;
                    private String name;
                    private String cardNo;
                    private String cardImg;
                    private int sex;
         */
        Log.i("TAG", "执行 事件上报 上报到 iot 平台");

        DeviceBean deviceBean = MainDataManager.getInstance().getAppDeviceBean();
        EventPara eventPara = new EventPara();
        eventPara.setCaseId(caseId);
        eventPara.setDeviceNo(deviceBean.getNickName());
        eventPara.setAttestTime(System.currentTimeMillis());
        eventPara.setSpotImg(faceUrl);
        eventPara.setName(idCardBean.getName());
        eventPara.setCardNo(idCardBean.getIdNumber());
        eventPara.setCardImg(headUrl);
        if (idCardBean.getGender().equals("男")) {
            eventPara.setSex(1);
        } else {
            eventPara.setSex(0);
        }

        DeviceModel deviceModel = DeviceModelConvert.getDeviceModelByEventPara(eventPara);
        List<DeviceModel> deviceModelList = new ArrayList<>();
        deviceModelList.add(deviceModel);
        new RxManager().add(schedule(picUpModel.uploadEvent(eventPara)).subscribe(respData -> {
            PicUploadDBUtil.getInstance().updateInfoState(true,id);
        }, throwable -> {
            Log.i("uploadEvent", "  upload event failed  here" + throwable.getMessage());
        }));
    }


    protected <T> Observable<T> schedule(Observable<T> observable) {
        return ObservableSchedule.schedule(observable);
    }
}
