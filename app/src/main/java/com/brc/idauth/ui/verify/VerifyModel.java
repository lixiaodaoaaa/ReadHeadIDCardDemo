package com.brc.idauth.ui.verify;

import com.brc.idauth.bean.Convert.DeviceModelConvert;
import com.brc.idauth.bean.DeviceModel;
import com.brc.idauth.bean.EventPara;
import com.brc.idauth.bean.EventUploadRequest;
import com.brc.idauth.bean.IdCardBean;
import com.daolion.net.request.RequestUtils;
import com.daolion.net.response.PictureInfo;
import com.daolion.net.response.Resp;
import com.daolion.net.utils.JsonUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-11
       Time     :  16:12
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class VerifyModel implements VerifyContract.Model {

    private final int POSITON_NAME = 0;
    private final int POSITON_GENDER = 1;
    private final int POSITON_NATION = 2;
    private final int POSITON_FROM = 3;
    private final int POSITON_ADDRESS = 4;
    private final int POSITON_IDCARDNUMBER = 5;
    private final int POSITON_SIGNED_POLICE = 6;
    private final int POSITON_VALID_TIME = 7;

    private final String TAG = VerifyModel.class.getName();


    @Override
    public List<ItemBean> getListItemBean() {
        return creatListItemBean();
    }

    @Override
    public List<ItemBean> getRealItemBeanByIdCard(IdCardBean idCardBean) {
        ArrayList<ItemBean> listBean = new ArrayList<>();
        listBean.add(new ItemBean("姓名", idCardBean.getName()));
        listBean.add(new ItemBean("性别", idCardBean.getGender()));
        listBean.add(new ItemBean("民族", idCardBean.getPeople()));
        listBean.add(new ItemBean("出生日期", idCardBean.getBirthDay()));
        listBean.add(new ItemBean("地址", idCardBean.getAddress()));
        listBean.add(new ItemBean("身份证号", idCardBean.getIdNumber()));
        listBean.add(new ItemBean("签发机关", idCardBean.getDepartment()));
        listBean.add(new ItemBean("有效日期", idCardBean.getEndDate()));
        return listBean;
    }

    @Override
    public Observable<Resp<PictureInfo>> uploadPicture(String filePath) {
        return apiService.uploadPicture(getRequestBody(filePath));
    }

    @Override
    public Observable<Resp<String>> uploadEvent(EventPara eventPara) {
        DeviceModel deviceModel = DeviceModelConvert.getDeviceModelByEventPara(eventPara);



        List<DeviceModel> deviceModelList = new ArrayList<>();
        deviceModelList.add(deviceModel);

        EventUploadRequest eventUploadRequest = new EventUploadRequest(deviceModelList);

        RequestBody requestBody = RequestUtils.getJsonRequestBody(JsonUtils.toJson(eventUploadRequest));
        return apiService.uploadEvent(requestBody);
    }


    public RequestBody getRequestBody(String filepath) {
        File file = new File(filepath);
        MultipartBody.Builder multiPatrBuilder = new MultipartBody.Builder();
        RequestBody requestBody = RequestUtils.getFormRequestBodyByFile(file);
        multiPatrBuilder.addFormDataPart("photo", file.getName(), requestBody);
        return multiPatrBuilder.build();
    }


    private List<ItemBean> creatListItemBean() {
        ArrayList<ItemBean> listBean = new ArrayList<>();

        listBean.add(new ItemBean("姓名"));
        listBean.add(new ItemBean("性别"));
        listBean.add(new ItemBean("民族"));
        listBean.add(new ItemBean("出身"));
        listBean.add(new ItemBean("地址"));
        listBean.add(new ItemBean("身份证号"));
        listBean.add(new ItemBean("签发机关"));
        listBean.add(new ItemBean("有效日期"));
        return listBean;
    }
}
