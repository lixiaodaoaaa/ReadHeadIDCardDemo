package com.brc.idauth.ui.verify;

import android.util.Log;

import com.brc.idauth.FileConstant;
import com.brc.idauth.base.MainDataManager;
import com.brc.idauth.bean.DeviceBean;
import com.brc.idauth.bean.EventPara;
import com.brc.idauth.bean.IdCardBean;

import java.util.List;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-11
       Time     :  16:16
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class VerifyPresenter extends VerifyContract.Presenter {

    private String TAG = VerifyPresenter.class.getName();

    private String idCardHeadFilePath = FileConstant.ID_CARD_HEAD_FILE_PATH;
    private int uploadIdCardHeadCount = 0;
    private int uploadFaceCount = 0;
    private String headUrl;
    private String faceUrl;


    protected VerifyPresenter(VerifyContract.View mView, VerifyContract.Model mModel) {
        super(mView, mModel);
    }

    @Override
    public void getListItemBean() {
        List<ItemBean> listItemBean = mModel.getListItemBean();
        mView.getListItemBeanSuccess(listItemBean);
    }

    @Override
    public void getRealItemBeanByIdCard(IdCardBean idCardBean) {
        if (idCardBean != null) {
            List<ItemBean> realListItem = mModel.getRealItemBeanByIdCard(idCardBean);
            mView.getRealItemBeanSuccess(realListItem);
        }
    }

    @Override
    public void uploadIDcardHeadPicture() {
        uploadIdCardHeadCount++;
        rxManager.add(schedule(mModel.uploadPicture(idCardHeadFilePath)).subscribe(respData -> {
            String url = respData.getData().getUrl();
            Log.i("upload ", "上传身份证照片成功，上传照片的地址为" + url);
//            mView.uploadIdCardHeadResult(true);

            headUrl = respData.getData().getUrl();

        }, throwable -> {
            Log.i(TAG, "上传文件失败，失败原因为" + throwable.getMessage());
            if (uploadFaceCount < 3) {
                uploadIDcardHeadPicture();
            }
        }));
    }

    @Override
    public void uploadFacePicture(String facePath) {
        uploadFaceCount++;
        rxManager.add(schedule(mModel.uploadPicture(facePath)).subscribe(respData -> {
            String url = respData.getData().getUrl();
            Log.i("upload ", "上传人脸照片成功，上传照片的地址为" + url);
//            mView.uploadFaceResult(true);
            faceUrl = respData.getData().getUrl();
        }, throwable -> {
            Log.i(TAG, "上传文件失败，失败原因为" + throwable.getMessage());
            if (uploadFaceCount < 3) {
                uploadFacePicture(facePath);
            }
        }));
    }

    @Override
    public void uploadEvent(IdCardBean idCardBean) {


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
        Log.i(TAG,"执行 事件上报 上报到 iot 平台");


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

        rxManager.add(schedule(mModel.uploadEvent(eventPara)).subscribe(respData -> {
            String msg = respData.message;
            Log.i(TAG, " 上报事件 结果" + msg);
//            mView.uploadEventResult(true);
        }, throwable -> {
            Log.i(TAG, " 上报事件发生错误，错误原因为" + throwable.getMessage());
//            mView.uploadEventResult(false);
        }));

    }


}
