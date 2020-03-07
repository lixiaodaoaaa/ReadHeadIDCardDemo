package com.brc.idauth.bean;

import java.util.List;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-14
       Time     :  09:22
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class EventUploadRequest {

    private List<DeviceModel>  deviceModels;


    public EventUploadRequest(List<DeviceModel> deviceModels) {
        this.deviceModels = deviceModels;
    }

    public List<DeviceModel> getDeviceModels() {
        return deviceModels;
    }

    public void setDeviceModels(List<DeviceModel> deviceModels) {
        this.deviceModels = deviceModels;
    }

    @Override
    public String toString() {
        return "EventUploadRequest{" +
                "deviceModels=" + deviceModels +
                '}';
    }
}
