package com.brc.idauth.bean;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
       Author   :  lixiaodaoaaa
       Date     :  2019-08-14
       Time     :  09:25
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛

    用于构建 EventUploadRequest 请求;
 */


import java.util.List;

public class DeviceModel {

    /*

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
     */

    private String iotId;
    private String nickName;
    private int channel;
    private String productModel;
    private String productName;
    private String productKey;
    private String status;
    private DeviceProperties deviceProperties;
    private List<DeviceEvent> deviceEventList;

    public DeviceModel() {
    }


    public DeviceModel(String iotId, String nickName, int channel, String productModel, String productName, String productKey, String status, DeviceProperties deviceProperties, List<DeviceEvent> deviceEventList) {
        this.iotId = iotId;
        this.nickName = nickName;
        this.channel = channel;
        this.productModel = productModel;
        this.productName = productName;
        this.productKey = productKey;
        this.status = status;
        this.deviceProperties = deviceProperties;
        this.deviceEventList = deviceEventList;
    }

    public String getIotId() {
        return iotId;
    }

    public void setIotId(String iotId) {
        this.iotId = iotId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DeviceProperties getDeviceProperties() {
        return deviceProperties;
    }

    public void setDeviceProperties(DeviceProperties deviceProperties) {
        this.deviceProperties = deviceProperties;
    }

    public List<DeviceEvent> getDeviceEventList() {
        return deviceEventList;
    }

    public void setDeviceEventList(List<DeviceEvent> deviceEventList) {
        this.deviceEventList = deviceEventList;
    }

    @Override
    public String toString() {
        return "DeviceModel{" +
                "iotId='" + iotId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", channel='" + channel + '\'' +
                ", productModel='" + productModel + '\'' +
                ", productName='" + productName + '\'' +
                ", productKey='" + productKey + '\'' +
                ", status='" + status + '\'' +
                ", deviceProperties=" + deviceProperties +
                ", deviceEventList=" + deviceEventList +
                '}';
    }
}
