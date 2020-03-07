package com.brc.idauth.bean.Convert;

import com.brc.idauth.base.MainDataManager;
import com.brc.idauth.bean.DeviceBean;
import com.brc.idauth.bean.DeviceEvent;
import com.brc.idauth.bean.DeviceModel;
import com.brc.idauth.bean.DeviceProperties;
import com.brc.idauth.bean.EventPara;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-14
       Time     :  10:10
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛

    将EventPara转化为  DeviceModel请求 主要用来构建 请求体：
{
    "deviceModels": [
        {
            "iotId": "06:09:36:ED:1B:39",
            "nickName": "云从人证一体机",
            "channel": "0",
            "productModel": "云从人证一体机",
            "productName": "云从人证一体机",
            "productKey": "YC-CW-IS1340B",
            "status": "1",
            "deviceProperties": {
                "iotId": "06:09:36:ED:1B:39"
            },
            "deviceEvents": [
                {
                    "iotId": "06:09:36:ED:1B:39",
                    "alarmLevel": "1",
                    "productKey": "YC-CW-IS1340B",
                    "msgTime": 1561981292000,
                    "deviceName": "云从人证一体机",
                    "eventType": "IDENTITY",
                    "eventName": "云从人证一体机",
                    "eventParams": "{
										"caseId": "207ad25c6126712b42cb82db92009aeb",
										"deviceNo": "云从人证一体机",
										"attestTime": 1561981292000,
										"spotImg": "https://brcimgs.oss-cn-beijing.aliyuncs.com/camera/20190701/fcc92f31ec04d574b266db7ab376692f",
										"name": "张冠融",
										"cardNo": "33062119700509875X",
										"cardImg": "https://brcimgs.oss-cn-beijing.aliyuncs.com/camera/20190701/fcc92f31ec04d574b266db7ab376692f",
										"sex": 1
									}",
                    "description": "客户信息上传"
                }
            ]
        }
    ]
}


 */
public class DeviceModelConvert {

    public static DeviceModel getDeviceModelByEventPara(EventPara eventPara) {

        DeviceModel deviceModel = new DeviceModel();

        DeviceBean deviceBean = MainDataManager.getInstance().getAppDeviceBean();


        deviceModel.setIotId(deviceBean.getIotId());
        deviceModel.setNickName(deviceBean.getNickName());

        deviceModel.setChannel(deviceBean.getChannel());
        deviceModel.setProductModel(deviceBean.getProductModel());


        deviceModel.setProductName(deviceBean.getProductName());

        deviceModel.setProductKey(deviceBean.getProductKey());


        deviceModel.setStatus(deviceBean.getStatus());
        deviceModel.setDeviceProperties(new DeviceProperties(deviceBean.getIotId()));


        List<DeviceEvent> deviceEventList = new ArrayList<>();
        deviceEventList.add(getDeviceEventByEventPara(eventPara));
        deviceModel.setDeviceEventList(deviceEventList);

        return deviceModel;
    }


    private static DeviceEvent getDeviceEventByEventPara(EventPara eventPara) {
        DeviceEvent deviceEvent = new DeviceEvent();
        DeviceBean deviceBean = MainDataManager.getInstance().getAppDeviceBean();

        deviceEvent.setIotId(deviceBean.getIotId());
        deviceEvent.setAlarmLevel("1");
        deviceEvent.setProductKey(deviceBean.getProductKey());
        deviceEvent.setMsgTime(eventPara.getAttestTime());
        deviceEvent.setDeviceName(deviceBean.getProductName());
        deviceEvent.setEventType("IDENTITY");
        deviceEvent.setEventName(deviceBean.getNickName());
        deviceEvent.setEventParams(new Gson().toJson(eventPara));
        deviceEvent.setDescription("用户信息上传");
        return deviceEvent;
    }
}
