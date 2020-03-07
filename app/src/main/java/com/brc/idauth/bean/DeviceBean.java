package com.brc.idauth.bean;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-09
       Time     :  17:05
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class DeviceBean {


    private static final String DEVICE_NAME = "云从人证一体机";
    /**
     * [
     * {
     * "channel": 0,
     * "iotId": "macdizhi",
     * "nickName": "人证一体机",
     * "productKey": "YC-CW-IS1340B",设备型号
     * "productModel": "云从人证一体机",
     * "productName": "云从人证一体机"
     * }
     * ]
     */

    private int channel = 0;

    private String iotId;

    private String productKey = "YC-CW-IS1340B";

    private String nickName = DEVICE_NAME;
    private String productModel = DEVICE_NAME;
    private String productName = DEVICE_NAME;

    private String projectName;
    private String projectId;
    private String houseId = projectId;


    /**
     * // 未激活
     * public static final int DeviceStatusNormal = 0;
     * // 在线
     * public static final int DeviceStatusOnlined = 1;
     * // 离线
     * public static final int DeviceStatusOffline = 3;
     * // 无效
     * public static final int DeviceStatusDisable = 8;
     */
    private String status;


    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public String getIotId() {
        return iotId;
    }

    public void setIotId(String iotId) {
        this.iotId = iotId;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.houseId= projectName;
        this.projectName = projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
