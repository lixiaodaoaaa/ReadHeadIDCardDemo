package com.daolion.bean;

/**
 * Created by lixiaodaoaaa on 2016/11/1.
 */

public class UpdateVersionBean {


    /**
     * code : 1.2.2    版本号
     * url : http://www.baidu.com  更新URL
     * isMandatory : true  是否强制更新
     */

    private String code;
    private String url;
    private boolean isMandatory;
    private String description;
    private String title = "版本更新";
    private boolean fromMain = true;
    private boolean isHasDownloadInSdCard = false;//是否已经下载到本地了;

    public boolean isFromMain() {
        return fromMain;
    }

    public void setFromMain(boolean fromMain) {
        this.fromMain = fromMain;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isMandatory() {
        return isMandatory;
    }

    public void setMandatory(boolean mandatory) {
        isMandatory = mandatory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() { return code;}

    public void setCode(String code) { this.code = code;}

    public String getUrl() { return url;}

    public void setUrl(String url) { this.url = url;}

    public boolean isIsMandatory() { return isMandatory;}

    public void setIsMandatory(boolean isMandatory) { this.isMandatory = isMandatory;}

    public UpdateVersionBean(String code, String url, boolean isMandatory, String description) {
        this.code = code;
        this.url = url;
        this.isMandatory = isMandatory;
        this.description = description;
    }

    public UpdateVersionBean(String code, String url, boolean isMandatory) {
        this.code = code;
        this.url = url;
        this.isMandatory = isMandatory;
    }

    public boolean isHasDownloadInSdCard() {
        return isHasDownloadInSdCard;
    }

    public void setHasDownloadInSdCard(boolean hasDownloadInSdCard) {
        isHasDownloadInSdCard = hasDownloadInSdCard;
    }

    @Override
    public String toString() {
        return "UpdateVersionBean{" +
                "isMandatory=" + isMandatory +
                ", url='" + url + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
