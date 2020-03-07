package cn.face.sdk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-10
       Time     :  15:31
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class FaceGroup implements Serializable {


    //总共有几张脸
    private int num;

    //FaceInfo数组
    private FaceInfo[] faceInfos;
    private List<FaceInfo> faceInfoList;


    public FaceGroup() {
    }

    public FaceGroup(int num, FaceInfo[] faceInfos) {
        this.num = num;
        this.faceInfos = faceInfos;
        faceInfoList = new ArrayList<FaceInfo>();
        for (FaceInfo faceInfo : faceInfos) {
            faceInfoList.add(faceInfo);
        }
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public FaceInfo[] getFaceInfos() {
        return faceInfos;
    }

    public void setFaceInfos(FaceInfo[] faceInfos) {
        this.faceInfos = faceInfos;
        faceInfoList.clear();
        for(FaceInfo faceInfo:faceInfos){
            faceInfoList.add(faceInfo);
        }
    }

    public List<FaceInfo> getFaceInfoList() {
        return faceInfoList;
    }

    public void setFaceInfoList(List<FaceInfo> faceInfoList) {
        this.faceInfoList = faceInfoList;
    }

    @Override
    public String toString() {
        return "FaceGroup{" +
                "num=" + num +
                ", faceInfos=" + Arrays.toString(faceInfos) +
                ", faceInfoList=" + faceInfoList +
                '}';
    }
}
