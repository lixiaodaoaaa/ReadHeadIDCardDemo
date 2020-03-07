package com.brc.idauth.utils.picuploaduitls;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.brc.idauth.bean.IdCardBean;

@Entity
public class PicUploadInfo {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String picA;
    public String picB;
    @Embedded
    public IdCardBean info;
    public boolean picAUpload;
    public boolean picBUpload;
    public boolean infoUpload;
    public long createTime;
    public boolean verifyResult;

    @Override
    public String toString() {
        return "PicUploadInfo{" +
                "id=" + id +
                ", picA='" + picA + '\'' +
                ", picB='" + picB + '\'' +
                ", info='" + info + '\'' +
                ", picAUpload=" + picAUpload +
                ", picBUpload=" + picBUpload +
                ", infoUpload=" + infoUpload +
                ", createTime='" + createTime + '\'' +
                ", verifyResult='" + verifyResult + '\'' +
                '}';
    }
}
