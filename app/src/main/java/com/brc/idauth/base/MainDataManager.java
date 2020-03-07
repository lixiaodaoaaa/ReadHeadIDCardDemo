package com.brc.idauth.base;

import android.content.Context;

import com.brc.idauth.Constants;
import com.brc.idauth.bean.DeviceBean;
import com.brc.idauth.utils.DeviceMacUtils;
import com.daolion.base.control.FrameApplication;
import com.daolion.base.utils.SharedPrefsUtils;


/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
       Author   :  lixiaodaoaaa
       Date     :  2018/8/22
       Time     :  13:27
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class MainDataManager {

    private static MainDataManager instance;
    private Context context;

    //内存中存放的 设备信息；
    private DeviceBean deviceBean;


    private MainDataManager() {
        context = FrameApplication.getInstance().getApplicationContext();
    }

    public static MainDataManager getInstance() {
        if (instance == null) {
            instance = new MainDataManager();
        }
        return instance;
    }





    /**
     * 设备是否被激活 如果激活返回True
     *
     * @return
     */
    public boolean isActived() {
        boolean isActivitated = SharedPrefsUtils.getBooleanPreference(context, Constants.KEY_IS_ACTIVATED, false);
        return isActivitated;
    }


    public DeviceBean getAppDeviceBean() {
        DeviceBean deviceBean = new DeviceBean();
        deviceBean.setIotId(DeviceMacUtils.getMacAddress());
        return deviceBean;
    }


    public String getLocalFaceLicence() {
        return SharedPrefsUtils.getStringPreference(context, Constants.KEY_FACE_LICENCE);

    }


}
