package cn.face.sdkproc; /**
 * Project Name:cloudwalk2
 * File Name:CloudwalkSDK.java
 * Package Name:cn.cloudwalk
 * Date:2016-4-27下午4:57:54
 * Copyright @ 2010-2016 Cloudwalk Information Technology Co.Ltd All Rights Reserved.
 */


import android.content.Context;

import java.io.File;

import cn.face.sdk.FaceAttrRet;
import cn.face.sdk.FaceAttribute;
import cn.face.sdk.FaceDetTrack;
import cn.face.sdk.FaceInfo;
import cn.face.sdk.FaceInterface;
import cn.face.sdk.FaceParam;
import cn.face.sdk.FaceRecog;


/**
 * ClassName: LocalSDK <br/>
 * Description: <br/>
 * date: 2016-4-27 下午4:57:54 <br/>
 *
 * @author 284891377
 * @since JDK 1.7
 */
public class LocalSDK {
    public static final int ERRCODE_MIN = FaceInterface.cw_errcode_t.CW_UNKNOWN_ERR;
    static LocalSDK mlocalSDK;
    int faceMinSize, faceMaxSize;
    String pLicence;
    String sModelPath;

    /**
     * 单例实例化
     *
     * @param mContext
     * @return CloudwalkSDK
     */
    public static LocalSDK getInstance(Context mContext) {

        if (null == mlocalSDK) {
            mlocalSDK = new LocalSDK();

        }
        return mlocalSDK;
    }

    private LocalSDK() {

    }

    /**
     * cwInit:初始化. <br/>
     *
     * @param pLicence    授权码
     * @param faceMinSize 设置检测人脸的最小尺寸 小于最小尺寸的人脸将无法被算法检测到
     * @param faceMaxSize 设置检测人脸的最大尺寸 大于最大尺寸的人脸将无法被算法检测到
     * @param sModelPath  模型sd卡路径 如:/sdcard/CWModels，请先解压模型文件到sdcard
     * @author:284891377 Date: 2016-4-22 下午3:46:41
     * @since JDK 1.7
     * 创建成功返回0，错误返回错误码
     */
    public int CreateHandles(String pLicence, int faceMinSize, int faceMaxSize, String sModelPath) {
        this.pLicence = pLicence;
        this.faceMinSize = faceMinSize;
        this.faceMaxSize = faceMaxSize;
        this.sModelPath = sModelPath;

        // 由于此demo需要用到检测，识别和属性句柄，因此这里直接一次性创建三个句柄
        // 实际使用中，根据需求创建对应句柄，如果是多线程，不能多线程共用句柄，比如，两个线程都要检测，就要创建两个检测句柄
        int iRet = CreateDetChannel();
        if (iRet >= ERRCODE_MIN) {
            return iRet;
        }

        // 创建识别句柄
        iRet = CreateRecogHandle();
        if (iRet >= ERRCODE_MIN) {
            return iRet;
        }

        // 创建属性句柄
        iRet = CreateAttriHandle();
        if (iRet >= ERRCODE_MIN) {
            return iRet;
        }

        return 0;
    }

    /**
     * cwDestory:资源释放 <br/>
     *
     * @author:284891377 Date: 2016-4-27 下午5:33:14
     * @since JDK 1.7
     */
    public void DestoryHandles() {

        // 程序退出时再销毁句柄，不要在程序使用过程中频繁创建，销毁句柄
        DestoryFaceDetect();
        DestoryFeature();
        DestoryAttriHandle();

    }

    // 人脸检测---------------------------------------
    // 人脸检测
    FaceDetTrack mFaceDetTrack;
    int detHandle = -1;
    FaceParam mFaceParam;

    /**
     * cwCreateDetTrackChannel:创建检测句柄，程序启动创建，退出时销毁<br/>
     * 一个线程对应一个句柄<br/>
     */
    public int CreateDetChannel() {
        if (detHandle != -1) {
            return detHandle;
        }

        mFaceDetTrack = FaceDetTrack.getInstance();
        // 检测配置文件路径，默认放置到sdcard根目录
        String sDetModelPath = new StringBuilder(sModelPath).append(File.separator).append("_configs_frontend_x86_arm.xml").toString();
        int ret = mFaceDetTrack.cwCreateDetHandle(sDetModelPath, pLicence);
        if (ret >= ERRCODE_MIN) {
            detHandle = -1;    // 置为-1表示创建失败
            return ret;
        }

        detHandle = ret;
        // 创建句柄后设置检测参数
        if (mFaceParam == null) {
            mFaceParam = new FaceParam();
        }
        mFaceDetTrack.cwGetFaceParam(detHandle, mFaceParam);
        mFaceParam.minSize = faceMinSize;
        mFaceParam.maxSize = faceMaxSize;
        mFaceDetTrack.cwSetFaceParam(detHandle, mFaceParam, sDetModelPath);
//		TestLog.netE(TAG, "检测通道耗时:" + (System.currentTimeMillis() - start));
        return ret;
    }

    /**
     * cwFaceDetect:人脸检测 <br/>
     *
     * @param 图片byte数据
     * @return DetectBean @see cn.cloudwalk.sdk.DetectBean
     * @author:284891377 Date: 2016年9月21日 上午10:32:39
     * @since JDK 1.7
     */
    public DetectBean FaceDetect(byte[] data, int width, int height, int format, int angle, int mirror, int faceOp) {
        DetectBean detectBean = new DetectBean();

        // 判断检测句柄是否创建
        if (detHandle == -1) {
            detectBean.ret = (int) FaceInterface.cw_errcode_t.CW_UNINITIALIZED_ERR;
            return detectBean;
        }
        // 人脸检测
        int faceNum = mFaceDetTrack.cwFaceDetection(detHandle, data, width, height, format, angle, mirror, faceOp, detectBean.faceInfos);
        if (faceNum >= ERRCODE_MIN) {
            detectBean.ret = faceNum;   // 检测异常
        } else {
            detectBean.faceNum = faceNum;  // detectBean.faceNum为0表示未检测到人脸
        }
        return detectBean;
    }

    /**
     * @param data
     * @param width
     * @param height
     * @param format
     * @param angle
     * @param mirror
     * @param faceOp
     * @param faceInfos
     * @return faceNum  faceNum>ERRCODE_MIN 错误码
     */
    public int FaceDetectBase(byte[] data, int width, int height, int format, int angle, int mirror, int faceOp,
                              FaceInfo[] faceInfos) {
        // 判断检测句柄是否创建
        if (detHandle == -1) {
            return (int) FaceInterface.cw_errcode_t.CW_UNINITIALIZED_ERR;
        }

        // 人脸检测
        return mFaceDetTrack.cwFaceDetection(detHandle, data, width, height, format, angle, mirror, faceOp,
                faceInfos);
    }

    /**
     * cwDestoryFaceDetect:关闭人脸跟踪检测，在功能不使用后应关闭 <br/>
     * 建议在onStop或者onDestory调用<br/>
     *
     * @author:284891377 Date: 2016年9月21日 上午10:30:01
     * @since JDK 1.7
     */
    public int DestoryFaceDetect() {
        int ret = 0;
        if (detHandle != -1) {
            ret = mFaceDetTrack.cwReleaseDetHandle(detHandle);
            if (ret == FaceInterface.cw_errcode_t.CW_SDKLIT_OK)
                detHandle = -1;
        }
        return ret;
    }
    // 人脸检测---------------------------------------


    // 特征提取---------------------------------------

    FaceRecog recog = null;
    public int iRecogHandle = -1;
    public int iFeaLen = -1;

    /**
     * cwCreateFeatureChannel:创建特征句柄，程序启动创建，退出时销毁<br/>
     * 一个线程对应一个句柄<br/>
     */
    public int CreateRecogHandle() {
//		long start = System.currentTimeMillis();
        if (iRecogHandle != -1) {
            return iRecogHandle;
        }

        recog = FaceRecog.getInstance();
        String sRecogModelPath = new StringBuilder(sModelPath).append(File.separator).append("CWR_Config_1_1.xml").toString();
        int ret = recog.cwCreateRecogHandle(sRecogModelPath, pLicence, 0);
        if (ret >= ERRCODE_MIN) {
            iRecogHandle = -1;   // 置为-1表示创建失败
        } else {
            iRecogHandle = ret;
            iFeaLen = recog.cwGetFeatureLength(iRecogHandle);
        }

//		TestLog.netE(TAG, "特征通道耗时:" + (System.currentTimeMillis() - start));
        return ret;
    }

    public FeatureBean GetFeatureFromImgData(byte[] imgData) {
        FeatureBean featureBean = new FeatureBean(iFeaLen);
        // 判断识别句柄是否创建
        if (iRecogHandle == -1) {
            featureBean.ret = (int) FaceInterface.cw_errcode_t.CW_UNINITIALIZED_ERR;
            return featureBean;
        }

        FaceInfo[] pFaceBuffer = new FaceInfo[3];
        for (int i = 0; i < 3; i++) {
            pFaceBuffer[i] = new FaceInfo();
        }
        featureBean.ret = FaceDetectBase(imgData, 0, 0, FaceInterface.cw_img_form_t.CW_IMAGE_BINARY, 0, 0,
                FaceInterface.cw_op_t.CW_OP_DET | FaceInterface.cw_op_t.CW_OP_ALIGN, pFaceBuffer);
        if (featureBean.ret >= ERRCODE_MIN) {
            return featureBean;    // 发生异常
        } else if (featureBean.ret < 1) {

            featureBean.ret = -1;  // 未检测到人脸
        } else {
            // 提取检测到的第一张人脸，为最大人脸
            featureBean.ret = recog.cwGetFaceFeature(iRecogHandle, pFaceBuffer[0].alignedData, pFaceBuffer[0].alignedW,
                    pFaceBuffer[0].alignedH, pFaceBuffer[0].nChannels, featureBean.btFeature);

            //	long end = System.currentTimeMillis() - startTime;
        }

        return featureBean;
    }


    // 1：1人脸比对
    public VerifyBean Verify(byte[] btFeaProbe, byte[] btFeaFiled) {
        VerifyBean verifyBean = new VerifyBean();

        // 判断识别句柄是否创建
        if (iRecogHandle == -1) {
            verifyBean.ret = (int) FaceInterface.cw_errcode_t.CW_UNINITIALIZED_ERR;
            return verifyBean;
        }

        // 人脸比对
        float[] pScores = new float[1];
        // 1:1
        verifyBean.ret = recog.cwComputeMatchScore(iRecogHandle, btFeaProbe, btFeaFiled, 1, pScores);
        verifyBean.score = pScores[0];

        return verifyBean;
    }

    // 1：N人脸检索
    public proc.RecogBean Recog(byte[] btFeaProbe, byte[] btFeaFiled, int iFiledNum) {
        proc.RecogBean recogBean = new proc.RecogBean(iFiledNum);
        // 判断识别句柄是否创建
        if (iRecogHandle == -1) {
            recogBean.ret = (int) FaceInterface.cw_errcode_t.CW_UNINITIALIZED_ERR;
            return recogBean;
        }

        // 1:n
        recogBean.ret = recog.cwComputeMatchScore(iRecogHandle, btFeaProbe, btFeaFiled, iFiledNum, recogBean.scores);
        return recogBean;
    }

    /**
     * cwDestoryFeature:关闭特征提取，在功能不使用后应关闭 <br/>
     * 建议在onStop或者onDestory调用<br/>
     *
     * @author:284891377 Date: 2016年9月21日 上午10:30:01
     * @since JDK 1.7
     */
    public int DestoryFeature() {
        int ret = 0;
        if (iRecogHandle != -1) {
            ret = recog.cwReleaseRecogHandle(iRecogHandle);
            if (ret == FaceInterface.cw_errcode_t.CW_SDKLIT_OK)
                iRecogHandle = -1;
        }
        return ret;
    }


    // 属性分析---------------------------------------
    FaceAttribute attri = null;
    int ageGroupChannel = -1;
    int genderChannel = -1;
    int raceChannel = -1;

    /**
     * cwCreateAttriHandle:创建属性句柄，程序启动创建，退出时销毁 <br/>
     * 一个线程对应一个句柄<br/>
     *
     * @author:284891377 Date: 2016年9月30日 上午11:30:30
     * @since JDK 1.7
     */
    public int CreateAttriHandle() {

        attri = FaceAttribute.getInstance();
        String sAttriAgePath = new StringBuilder(sModelPath).append(File.separator).append("attribute")
                .append(File.separator).append("ageGroup").append(File.separator).append("cw_age_group_config.xml")
                .toString();
        int ret = attri.cwCreateAttributeHandle(sAttriAgePath, pLicence);
        if (ret >= ERRCODE_MIN) {
            return ret;
        } else {
            ageGroupChannel = ret;
        }

        String sAttriGenderPath = new StringBuilder(sModelPath).append(File.separator).append("attribute")
                .append(File.separator).append("faceGender").append(File.separator).append("cw_gender_config.xml")
                .toString();
        ret = attri.cwCreateAttributeHandle(sAttriGenderPath, pLicence);
        if (ret >= ERRCODE_MIN) {
            return ret;
        } else {
            genderChannel = ret;
        }

        String sAttriRacePath = new StringBuilder(sModelPath).append(File.separator).append("attribute")
                .append(File.separator).append("faceRace").append(File.separator).append("cw_race_config.xml")
                .toString();
        ret = attri.cwCreateAttributeHandle(sAttriRacePath, pLicence);
        if (ret >= ERRCODE_MIN) {
            return ret;
        } else {
            raceChannel = ret;
        }

        return ret;
    }

    /**
     * cwGetAttriFromImgData:获取属性信息，包括年龄和性别. <br/>
     *
     * @return AttributeBean
     * @author:284891377 Date: 2016年9月30日 上午11:30:30
     * @since JDK 1.7
     */
    public AttributeBean GetAttriFromImgData(byte[] imgData) {
        AttributeBean attriBean = new AttributeBean();
        if (ageGroupChannel == -1) {
            attriBean.ret = (int) FaceInterface.cw_errcode_t.CW_UNINITIALIZED_ERR;
            return attriBean;
        }

        FaceInfo[] pFaceBuffer = new FaceInfo[3];
        for (int i = 0; i < 3; i++) {
            pFaceBuffer[i] = new FaceInfo();
        }
        attriBean.ret = FaceDetectBase(imgData, 0, 0,
                FaceInterface.cw_img_form_t.CW_IMAGE_BINARY, 0, 0,
                FaceInterface.cw_op_t.CW_OP_DET
                        | FaceInterface.cw_op_t.CW_OP_ALIGN, pFaceBuffer);
        if (attriBean.ret >= ERRCODE_MIN) {

            return attriBean;
        } else if (attriBean.ret < 1) {

            attriBean.ret = -1;
        } else {
            FaceAttrRet attrAge = new FaceAttrRet();
            attriBean.ret = attri.cwGetAgeEval(ageGroupChannel,
                    pFaceBuffer[0].alignedData, pFaceBuffer[0].alignedW,
                    pFaceBuffer[0].alignedH, pFaceBuffer[0].nChannels, attrAge);

            if (attriBean.ret == 0) {
                attriBean.ageGroup = attrAge.m_iValue;
            }

            FaceAttrRet attrGender = new FaceAttrRet();
            attriBean.ret = attri.cwGetGenderEval(genderChannel,
                    pFaceBuffer[0].alignedData, pFaceBuffer[0].alignedW,
                    pFaceBuffer[0].alignedH, pFaceBuffer[0].nChannels, attrGender);

            if (attriBean.ret == 0) {
                attriBean.gender = attrGender.m_iValue;
            }

            FaceAttrRet attrRace = new FaceAttrRet();
            attriBean.ret = attri.cwGetRaceEval(raceChannel,
                    pFaceBuffer[0].alignedData, pFaceBuffer[0].alignedW,
                    pFaceBuffer[0].alignedH, pFaceBuffer[0].nChannels, attrRace);

            if (attriBean.ret == 0) {
                attriBean.race = attrRace.m_iValue;
            }
        }

        return attriBean;
    }

    /**
     * cwDestoryAttriHandle:销毁属性识别通道. <br/>
     *
     * @return 0成功 其它失败
     * @author:284891377 Date: 2016年9月30日 上午11:30:30
     * @since JDK 1.7
     */
    public int DestoryAttriHandle() {
        int ret = 0;
        if (ageGroupChannel != -1) {
            ret = attri.cwReleaseAttributeHandle(ageGroupChannel);
            if (ret == FaceInterface.cw_errcode_t.CW_SDKLIT_OK)
                ageGroupChannel = -1;
        }

        if (genderChannel != -1) {
            ret = attri.cwReleaseAttributeHandle(genderChannel);
            if (ret == FaceInterface.cw_errcode_t.CW_SDKLIT_OK)
                genderChannel = -1;
        }

        if (raceChannel != -1) {
            ret = attri.cwReleaseAttributeHandle(raceChannel);
            if (ret == FaceInterface.cw_errcode_t.CW_SDKLIT_OK)
                raceChannel = -1;
        }
        return ret;
    }


    /**
     * 获取错误码说明
     */
    public static String getErrMsg(int errCode) {
        String errMsg = "成功";
        switch (errCode) {
            case FaceInterface.cw_errcode_t.CW_SDKLIT_OK: // 成功
                errMsg = "成功";
                break;
            case FaceInterface.cw_errcode_t.CW_UNKNOWN_ERR:
                errMsg = "未知错误";
                break;
            case FaceInterface.cw_errcode_t.CW_DETECT_INIT_ERR:
                errMsg = "初始化人脸检测器失败";
                break;
            case FaceInterface.cw_errcode_t.CW_KEYPT_INIT_ERR:
                errMsg = "初始化关键点检测器失败";
                break;
            case FaceInterface.cw_errcode_t.CW_QUALITY_INIT_ERR:
                errMsg = "初始化跟踪器失败";
                break;
            case FaceInterface.cw_errcode_t.CW_DET_ERR:
                errMsg = "检测失败";
                break; // 检测失败
            case FaceInterface.cw_errcode_t.CW_TRACK_ERR:
                errMsg = "跟踪失败";
                break; // 跟踪失败
            case FaceInterface.cw_errcode_t.CW_KEYPT_ERR:
                errMsg = "提取关键点失败";
                break; // 提取关键点失败
            case FaceInterface.cw_errcode_t.CW_ALIGN_ERR:
                errMsg = "对齐人脸失败";
                break; // 对齐人脸失败
            case FaceInterface.cw_errcode_t.CW_QUALITY_ERR:
                errMsg = "质量评估失败";
                break; // 质量评估失败
            case FaceInterface.cw_errcode_t.CW_EMPTY_FRAME_ERR:
                errMsg = "空图像";
                break; // 空图像
            case FaceInterface.cw_errcode_t.CW_UNSUPPORT_FORMAT_ERR:
                errMsg = "图像格式不支持";
                break; // 图像格式不支持
            case FaceInterface.cw_errcode_t.CW_ROI_ERR:
                errMsg = "ROI设置失败";
                break; // ROI设置失败
            case FaceInterface.cw_errcode_t.CW_UNINITIALIZED_ERR:
                errMsg = "尚未初始化";
                break; // 尚未初始化
            case FaceInterface.cw_errcode_t.CW_MINMAX_ERR:
                errMsg = "最小最大人脸设置失败";
                break; // 最小最大人脸设置失败
            case FaceInterface.cw_errcode_t.CW_OUTOF_RANGE_ERR:
                errMsg = "数据范围错误";
                break; // 数据范围错误
            case FaceInterface.cw_errcode_t.CW_UNAUTHORIZED_ERR:
                errMsg = "未授权";
                break; // 未授权
            case FaceInterface.cw_errcode_t.CW_METHOD_UNAVAILABLE:
                errMsg = "方法无效";
                break; // 方法无效
            case FaceInterface.cw_errcode_t.CW_PARAM_INVALID:
                errMsg = "参数无效";
                break; // 参数无效
            case FaceInterface.cw_errcode_t.CW_BUFFER_EMPTY:
                errMsg = "缓冲区空";
                break; // 缓冲区空
            case FaceInterface.cw_errcode_t.CW_FILE_UNAVAILABLE:
                errMsg = "文件不存在";
                break; // 文件不存在
            case FaceInterface.cw_errcode_t.CW_DEVICE_UNAVAILABLE:
                errMsg = "设备不存在";
                break; // 设备不存在
            case FaceInterface.cw_errcode_t.CW_DEVICE_ID_UNAVAILABLE:
                errMsg = "设备id不存在";
                break; // 设备id不存在
            case FaceInterface.cw_errcode_t.CW_EXCEEDMAXHANDLE_ERR:
                errMsg = "超过授权最大句柄数";
                break; // 超过授权最大句柄数
            case FaceInterface.cw_errcode_t.CW_RECOG_FEATURE_MODEL_ERR:
                errMsg = " 加载特征识别模型失败";
                break; // 加载特征识别模型失败
            case FaceInterface.cw_errcode_t.CW_RECOG_ALIGNEDFACE_ERR:
                errMsg = "对齐图片数据错误";
                break; // 对齐图片数据错误
            case FaceInterface.cw_errcode_t.CW_RECOG_MALLOCMEMORY_ERR:
                errMsg = "预分配特征空间不足";
                break; // 预分配特征空间不足
            case FaceInterface.cw_errcode_t.CW_RECOG_FEATUREDATA_ERR:
                errMsg = "特征数据错误";
                break; // 特征数据错误
            case FaceInterface.cw_errcode_t.CW_RECOG_EXCEEDMAXFEASPEED:
                errMsg = "超过授权最大提特征速度";
                break; // 超过授权最大提特征速度
            case FaceInterface.cw_errcode_t.CW_RECOG_EXCEEDMAXCOMSPEED:
                errMsg = "超过授权最大比对速度";
                break; // 超过授权最大比对速度

        }
        return errMsg;

    }
}
