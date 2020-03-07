package com.brc.idauth.bean;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-13
       Time     :  22:02
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class EventPara {

    /**
     * "caseId": "207ad25c6126712b42cb82db92009aeb",
     * "deviceNo": "云从人证一体机",
     * "attestTime": 1561981292000,
     * "spotImg": "https://brcimgs.oss-cn-beijing.aliyuncs.com/camera/20190701/fcc92f31ec04d574b266db7ab376692f",
     * "name": "张冠融",
     * "cardNo": "33062119700509875X",
     * "cardImg": "https://brcimgs.oss-cn-beijing.aliyuncs.com/camera/20190701/fcc92f31ec04d574b266db7ab376692f",
     * "sex": 1
     */

    private String caseId;
    private String deviceNo;
    private long attestTime;
    private String spotImg;
    private String name;
    private String cardNo;
    private String cardImg;
    private int sex;


    public EventPara() {
    }

    public EventPara(String caseId, String deviceNo, long attestTime, String spotImg, String name, String cardNo, String cardImg, int sex) {
        this.caseId = caseId;
        this.deviceNo = deviceNo;
        this.attestTime = attestTime;
        this.spotImg = spotImg;
        this.name = name;
        this.cardNo = cardNo;
        this.cardImg = cardImg;
        this.sex = sex;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public long getAttestTime() {
        return attestTime;
    }

    public void setAttestTime(long attestTime) {
        this.attestTime = attestTime;
    }

    public String getSpotImg() {
        return spotImg;
    }

    public void setSpotImg(String spotImg) {
        this.spotImg = spotImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardImg() {
        return cardImg;
    }

    public void setCardImg(String cardImg) {
        this.cardImg = cardImg;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "EventPara{" +
                "caseId='" + caseId + '\'' +
                ", deviceNo='" + deviceNo + '\'' +
                ", attestTime=" + attestTime +
                ", spotImg='" + spotImg + '\'' +
                ", name='" + name + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", cardImg='" + cardImg + '\'' +
                ", sex=" + sex +
                '}';
    }
}
