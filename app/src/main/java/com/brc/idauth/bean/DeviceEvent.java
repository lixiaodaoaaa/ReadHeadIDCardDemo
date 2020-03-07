package com.brc.idauth.bean;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-13
       Time     :  21:56
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
    事件上报的Event
 */
public class DeviceEvent {

    /*
    {
                    "iotId": "06:09:36:ED:1B:39",
                    "alarmLevel": "1",
                    "productKey": "YC-CW-IS1340B",
                    "msgTime": 1561981292000,
                    "deviceName": "云从人证一体机",
                    "eventType": "IDENTITY",
                    "eventName": "云从人证一体机",
                    "eventParams":
                    "
                        {
                            "caseId": "207ad25c6126712b42cb82db92009aeb",
                            "deviceNo": "云从人证一体机",
                            "attestTime": 1561981292000,
                            "spotImg": "https://brcimgs.oss-cn-beijing.aliyuncs.com/camera/20190701/fcc92f31ec04d574b266db7ab376692f",
                            "name": "张冠融",
                            "cardNo": "33062119700509875X",
                            "cardImg": "https://brcimgs.oss-cn-beijing.aliyuncs.com/camera/20190701/fcc92f31ec04d574b266db7ab376692f",
                            "sex": 1
                        }",
                    "
                    "description": "客户信息上传"
                }
     */


    private String iotId;
    private String alarmLevel;
    private String productKey;
    private long msgTime;
    private String deviceName;
    private String eventType;
    private String eventName;
    private String description;
    private String eventParams;

    public String getIotId() {
        return iotId;
    }

    public void setIotId(String iotId) {
        this.iotId = iotId;
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public long getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(long msgTime) {
        this.msgTime = msgTime;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventParams() {
        return eventParams;
    }

    public void setEventParams(String eventParams) {
        this.eventParams = eventParams;
    }

    @Override
    public String toString() {
        return "DeviceEvent{" +
                "iotId='" + iotId + '\'' +
                ", alarmLevel='" + alarmLevel + '\'' +
                ", productKey='" + productKey + '\'' +
                ", msgTime=" + msgTime +
                ", deviceName='" + deviceName + '\'' +
                ", eventType='" + eventType + '\'' +
                ", eventName='" + eventName + '\'' +
                ", description='" + description + '\'' +
                ", eventParams='" + eventParams + '\'' +
                '}';
    }
}
