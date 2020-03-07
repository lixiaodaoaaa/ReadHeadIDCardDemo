package cn.face.realtime; /**
 * Project Name:cloudwalk2
 * File Name:CloudwalkLocalFaceSDK.java
 * Package Name:cn.cloudwalk
 * Date:2016-4-27下午4:57:54
 * Copyright @ 2010-2016 Cloudwalk Information Technology Co.Ltd All Rights Reserved.
 */


import android.content.Context;

import cn.face.sdk.FaceGroup;
import cn.face.sdk.FaceInfo;
import cn.face.sdk.FaceInterface;
import cn.face.sdk.FaceInterface.cw_img_angle_t;
import cn.face.sdk.FaceInterface.cw_img_mirror_t;
import cn.face.sdkproc.LocalSDK;

/**
 * ClassName: LocalFaceSDK <br/>
 * Description: <br/>
 * date: 2016-4-27 下午4:57:54 <br/>
 *
 * @author 284891377
 * @since JDK 1.7
 */
public class LocalFaceSDK {
    private static final int MAX_NUM = 10;

    private FaceInfoCallback faceInfoCallback;
    static LocalFaceSDK mFaceSDK;

    // FaceDetTrack faceDetTrack;
    // static FaceParam param = new FaceParam();

    // private int missFrameSet = 15;
    // private int missFrame;
    LocalSDK mLocalSDK;
    int faceOp = FaceInterface.cw_op_t.CW_OP_ALIGN;

    public int getFaceOp() {
        return faceOp;
    }

    public void setFaceOp(int faceOp) {
        this.faceOp = faceOp;
    }

    Context mContext;
    /**
     * 每帧图像人脸数量
     **/
    int faceNum;
    FaceInfo[] faceInfos;


    /**
     * 单例实例化
     *
     * @param mContext
     * @return CloudwalkLocalFaceSDK
     */
    public static LocalFaceSDK getInstance(Context mContext) {

        if (null == mFaceSDK) {
            mFaceSDK = new LocalFaceSDK(mContext);

        }
        return mFaceSDK;
    }

    private LocalFaceSDK(Context mContext) {
        this.mContext = mContext;

        faceInfos = new FaceInfo[MAX_NUM];
        for (int i = 0; i < MAX_NUM; i++) {
            faceInfos[i] = new FaceInfo();
        }
    }

    public int cwInit() {
        int ret = 0;
        mLocalSDK = LocalSDK.getInstance(mContext);
        cwStart();

        return ret;
    }

    public int cwDestory() {

        faceInfoCallback = null;

        cwStop();
        return 0;

    }


    /**
     * cwFaceInfoCallback:设置人脸信息回掉. <br/>
     *
     * @param faceInfoCallback
     * @author:284891377 Date: 2016年6月16日 下午2:35:09
     * @since JDK 1.7
     */
    public void cwFaceInfoCallback(FaceInfoCallback faceInfoCallback) {
        this.faceInfoCallback = faceInfoCallback;
    }

    // 线程
    private Thread videoThread = null;
    private boolean bDetecting = false;
    private byte[] mFrame;
    private Object lockPreview = new Object(); // 视频预览中, 抓图与处理间的同步对象
    // 每帧图像
    int frameFormat;
    private int frameW;
    private int frameH;
    private int frameAngle;
    private int frameMirror;
    private int caremaType;

    /**
     * cwStart:开始人脸检测 <br/>
     *
     * @return
     * @author:284891377 Date: 2016-4-22 下午3:51:17
     * @since JDK 1.7
     */
    private int cwStart() {
        bDetecting = true;
        if (null == videoThread) {
            videoThread = new Thread(new VideoFrameRunnable());
            videoThread.start();
        } else {

        }

        return 0;

    }

    /**
     * cwStop:停止人脸检测 <br/>
     *
     * @return
     * @author:284891377 Date: 2016-4-22 下午3:51:17
     * @since JDK 1.7
     */
    private int cwStop() {
        bDetecting = false;
        if ((null != videoThread) && !bDetecting) {
            try {
                synchronized (lockPreview) {
                    lockPreview.notify();
                }
                videoThread.join();
                videoThread = null;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return 0;

    }

    /**
     * cwPushFrame:Push视频桢数据 <br/>
     *
     * @param frameData   数据帧
     * @param frameW      图片宽
     * @param frameH      图像高
     * @param frameFormat 图像格式 {@link cn.cloudwalk.FaceInterface.ImageForm}
     * @param caremaType  摄像头和屏幕方向 {@link cn.cloudwalk.FaceInterface.CaremaType}
     * @author:284891377 Date: 2016-4-22 下午4:03:24
     * @since JDK 1.7
     */
    public void cwPushFrame(byte[] frameData, int frameW, int frameH, int frameFormat, int caremaType) {
        this.frameW = frameW;
        this.frameH = frameH;
        this.frameFormat = frameFormat;
        if (this.caremaType != caremaType) {
//			TestLog.netE(TAG, "摄像头,屏幕方向变");
        }
        this.caremaType = caremaType;
        switch (caremaType) {
            case CaremaType.FRONT_LANDSCAPE:
                this.frameAngle = cw_img_angle_t.CW_IMAGE_ANGLE_0;
                this.frameMirror = cw_img_mirror_t.CW_IMAGE_MIRROR_HOR;
                break;
            case CaremaType.FRONT_PORTRAIT:
                this.frameAngle = cw_img_angle_t.CW_IMAGE_ANGLE_270;
                this.frameMirror = cw_img_mirror_t.CW_IMAGE_MIRROR_HOR;
                break;
            case CaremaType.BACK_LANDSCAPE:
                this.frameAngle = cw_img_angle_t.CW_IMAGE_ANGLE_0;
                this.frameMirror = cw_img_mirror_t.CW_IMAGE_MIRROR_NONE;
                break;
            case CaremaType.BACK_PORTRAIT:
                this.frameAngle = cw_img_angle_t.CW_IMAGE_ANGLE_270;
                this.frameMirror = cw_img_mirror_t.CW_IMAGE_MIRROR_NONE;
                break;
        }

        synchronized (lockPreview) {
            this.mFrame = frameData;
            lockPreview.notify();
        }

    }

    // 视频帧处理线程
    class VideoFrameRunnable implements Runnable {

        public void run() {
            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_LOWEST);
            FaceGroup faceGroup = null;
            while (bDetecting) {
                synchronized (lockPreview) {
                    try {

                        lockPreview.wait();

                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                    processVideoFrame(mFrame);
                }
            }
            // 结束
            faceGroup = new FaceGroup(0, faceInfos);
            if (null != faceInfoCallback) {
                faceInfoCallback.detectFaceInfo(faceGroup, mFrame, frameW, frameH);
            }
            videoThread.interrupt();

            // int ret = xfReleaseDetector();

        }
    }

    public int cwFaceDetectTrack(byte[] data,
                                 int width,
                                 int height,
                                 int format,
                                 int angle,
                                 int mirror) {
        int faceNum = 0;
        FaceGroup faceGroup = null;
        faceNum = mLocalSDK.FaceDetectBase(data, width, height, format, angle, mirror, faceOp, faceInfos);
        if (faceNum > 0 && faceNum < LocalSDK.ERRCODE_MIN) {
            faceGroup = new FaceGroup(faceNum, faceInfos);
            faceInfoCallback.detectFaceInfo(faceGroup, data, width, height);
        } else {
            faceInfoCallback.detectFaceInfo(null, data, width, height);
        }

        return faceNum;
    }

    /**
     * 处理每一帧图像
     *
     * @param yuv_data
     */
    private void processVideoFrame(byte[] yuv_data) {
        if (yuv_data == null || !bDetecting)
            return;
        faceNum = cwFaceDetectTrack(yuv_data, frameW, frameH, frameFormat, frameAngle, frameMirror);
//		Long startTime = System.currentTimeMillis();

    }

}
