package com.brc.idauth.ui.mine;

import android.support.annotation.NonNull;

import com.brc.idauth.bean.DeviceBean;
import com.brc.idauth.utils.DeviceMacUtils;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/8/22
       Time     :  14:03
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class MineModle implements MineContract.Model {


    @NonNull
    public DeviceBean getAppDeviceBean() {
        DeviceBean deviceBean = new DeviceBean();
        deviceBean.setIotId(DeviceMacUtils.getMacAddress());
        return deviceBean;
    }
}
