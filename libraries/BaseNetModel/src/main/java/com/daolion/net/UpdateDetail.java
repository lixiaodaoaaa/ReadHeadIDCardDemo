package com.daolion.net;

public class UpdateDetail {

    /**
     * id : 6
     * appEnglishName : brc
     * appChineseName : 蓝光
     * changelog : 阈值70,k1,k,Merge remote-tracking branch
     * 'remotes/origin/jam',登录页面延时销毁,ui修改,修复猫眼对讲功能,Merge remote-tracking
     * branches 'remotes/origin/Icarus' and 'remotes/origin/jam',ui修改,网络请求中增加
     * 服务器异常,Merge branch 'master' of 10.0.24.185:IOT/iotbox into jam
     * appVersion : 2.0.1
     * versionCode : 2
     * installUrl : http://brcapppublic.oss-cn-hangzhou.aliyuncs
     * .com/Andriod_iotbox_20190417.5/8/app-release.apk
     * appImage : null
     * appType : Android
     * updateTime : 2019-04-17T15:33:26.000+0800
     * status : 1
     * forcedUpdate : 0
     * versionSize : 128549566
     */

    private int id;
    private String appEnglishName;
    private String appChineseName;
    private String changelog;
    private String appVersion;
    private int versionCode;
    private String installUrl;
    private Object appImage;
    private String appType;
    private String updateTime;
    private int status;
    private int forcedUpdate;
    private String versionSize;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppEnglishName() {
        return appEnglishName;
    }

    public void setAppEnglishName(String appEnglishName) {
        this.appEnglishName = appEnglishName;
    }

    public String getAppChineseName() {
        return appChineseName;
    }

    public void setAppChineseName(String appChineseName) {
        this.appChineseName = appChineseName;
    }

    public String getChangelog() {
        return changelog;
    }

    public void setChangelog(String changelog) {
        this.changelog = changelog;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getInstallUrl() {
        return installUrl;
    }

    public void setInstallUrl(String installUrl) {
        this.installUrl = installUrl;
    }

    public Object getAppImage() {
        return appImage;
    }

    public void setAppImage(Object appImage) {
        this.appImage = appImage;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getForcedUpdate() {
        return forcedUpdate;
    }

    public void setForcedUpdate(int forcedUpdate) {
        this.forcedUpdate = forcedUpdate;
    }

    public String getVersionSize() {
        return versionSize;
    }

    public void setVersionSize(String versionSize) {
        this.versionSize = versionSize;
    }
}
