package com.brc.idauth.thread;

import android.content.Context;
import android.util.Log;

import com.brc.idauth.bean.event.VerifyResult;
import com.brc.idauth.control.AjustPara;
import com.brc.idauth.utils.FileUtil;

import java.io.File;
import java.io.IOException;

import cn.face.sdk.FaceGroup;
import cn.face.sdk.FaceInfo;
import cn.face.sdk.FaceRecog;
import cn.face.sdkproc.FeatureBean;
import cn.face.sdkproc.LocalSDK;
import cn.face.sdkproc.VerifyBean;
import de.greenrobot.event.EventBus;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-11
       Time     :  00:05
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class CheckedFaceWithIDCardThread extends Thread {


    private FaceGroup faceGroup;
    private LocalSDK localSDK;
    private Context context;

    public CheckedFaceWithIDCardThread(Context context, FaceGroup faceGroup) {
        this.faceGroup = faceGroup;
        this.context = context;
        localSDK = LocalSDK.getInstance(context);
    }


    @Override
    public void run() {
        super.run();
        analayFaceGroup(faceGroup);

    }

    private void analayFaceGroup(FaceGroup faceGroup) {

        if (null != faceGroup && faceGroup.getNum() > 0) {

            for (FaceInfo face : faceGroup.getFaceInfoList()) {
                if (face == null || face.detected != FaceInfo.CHECKED_FACE || face.alignedData == null) {
                    return;
                }
                verifyWithIDcard(face);
            }
        }

    }

    private void verifyWithIDcard(FaceInfo face) {
        FeatureBean faceFeature = new FeatureBean(localSDK.iFeaLen);
        faceFeature.ret = FaceRecog.getInstance().cwGetFaceFeature(localSDK.iRecogHandle,
                face.alignedData,
                face.alignedW,
                face.alignedH,
                face.nChannels,
                faceFeature.btFeature);

        FeatureBean idCardFeature = null;
        try {
            idCardFeature = localSDK.GetFeatureFromImgData(getIDBytes());
            VerifyBean mVerifyBean = localSDK.Verify(faceFeature.btFeature, idCardFeature.btFeature);
            if (mVerifyBean.ret == 0) {
                Log.e("========", "相似度：" + mVerifyBean.score);
                boolean isVerifySucess = mVerifyBean.score > AjustPara.JUGE_IS_SAME_PERSON_SCORE;
                EventBus.getDefault().post(new VerifyResult(isVerifySucess));
            } else {
                Log.e("========", "人脸比对错误：" + mVerifyBean.ret);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到身份证bytes信息
     *
     * @return
     */
    private byte[] getIDBytes() throws IOException {
        byte[] idCardBmpBytes = FileUtil.file2byte(new File("/mnt/sdcard/wltlib/zp.bmp"));
        return idCardBmpBytes;
    }
}
