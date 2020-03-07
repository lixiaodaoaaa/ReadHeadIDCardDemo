package cn.face.sdkproc; /**
 * Project Name:cloudwalk2
 * File Name:CloudwalkSDK.java
 * Package Name:cn.cloudwalk
 * Date:2016-4-27下午4:57:54
 * Copyright @ 2010-2016 Cloudwalk Information Technology Co.Ltd All Rights Reserved.
 */


import android.content.Context;

import java.io.File;

import cn.face.sdk.FaceInterface;
import cn.face.sdk.FaceNisLiveParam;
import cn.face.sdk.FaceNisLiveness;


/**
 * ClassName: NisLivenessSDK <br/>
 * Description: 红外活体检测基础接口使用示例<br/>
 * date: 2017-11-27 下午4:57:54 <br/>
 *
 * @author 284891377
 * @since JDK 1.7
 */
public class NisLivenessSDK {

	public static final int ERRNISCODE_MIN = FaceInterface.cw_nirliveness_err_t.CW_NIRLIV_ERR_CREATE_HANDLE;
	static NisLivenessSDK nisSDK;
	FaceNisLiveness m_faceLive = null;
	LocalSDK m_FaceSDK = LocalSDK.getInstance(null);
	int m_NisHandle = -1;
	
	/**
	 * 单例实例化
	 *
	 * @param mContext
	 * @return NisLivenessSDK
	 */
	public static NisLivenessSDK getInstance(Context mContext) {

		if (null == nisSDK) {
			nisSDK = new NisLivenessSDK();
		}
		return nisSDK;
	}

	private NisLivenessSDK() {

	}

	/**
	 * 创建红外句柄<br/>
	 *
	 * @param pLicence
	 *            授权码
	 * @param sModelPath
	 *            模型sd卡路径 如:/sdcard/CWModels，请先解压模型文件到sdcard
	 * @author:284891377 Date: 2016-4-22 下午3:46:41
	 * @since JDK 1.7
	 * 创建成功返回0，错误返回错误码
	 */
	public int CreateNisHandle(String pLicence, String sModelDir) {
		
		if (m_NisHandle != -1)
		{
			return m_NisHandle;
		}
		
		m_faceLive = FaceNisLiveness.getInstance();
		// 检测配置文件路径，默认放置到sdcard根目录
		String sModelPath = new StringBuilder(sModelDir).append(File.separator).append("nirLiveness_model_20181015_fast.bin").toString();
		String sRecogPath = new StringBuilder(sModelDir).append(File.separator).append("hd171019.bin").toString();
		String sMatrixParaDir = new StringBuilder(sModelDir).append(File.separator).append("matrix_para640x480.xml").toString();
		String sLogDir = new StringBuilder(sModelDir).append(File.separator).append("log").toString();
		float fThresh = 0.35f;    // 肤色阈值
		
		int ret = m_faceLive.cwCreateNirLivenessHandle(sModelPath, sRecogPath, sMatrixParaDir, sLogDir, fThresh, pLicence);
		if (ret >= ERRNISCODE_MIN)
		{
			m_NisHandle = -1;    // 置为-1表示创建失败
			return ret;
		}
		
		m_NisHandle = ret;
		return ret;
	}

	/**
	 * DestoryNisHandles:销毁句柄，资源释放 <br/>
	 *
	 * @author:284891377 Date: 2016-4-27 下午5:33:14
	 * @since JDK 1.7
	 */
	public int DestoryNisHandles() {

		// 程序退出时再销毁句柄，不要在程序使用过程中频繁创建，销毁句柄
		int ret = 0;
		if (m_NisHandle != -1) {
			ret = m_faceLive.cwReleaseNirLivenessHandle(m_NisHandle);
			if (ret == FaceInterface.cw_errcode_t.CW_SDKLIT_OK)
				m_NisHandle = -1;
		}
		return ret;
	}


	/**
	 * FaceNirLiveDetect:红外活体检测，多人脸时只判断最大人脸 <br/>
	 * 
	 * @author:284891377 Date: 2016年9月21日 上午10:32:39
	 * @param imgData
	 *            图片byte数据
	 * @return DetectBean @see cn.cloudwalk.sdk.DetectBean
	 * @since JDK 1.7
	 */
	public NisLiveBean FaceNirLiveDetect(byte[] dataVis, int widthVis, int heightVis, int formatVis,
											  byte[] dataNir, int widthNir, int heightNir, int formatNir) {
		
		NisLiveBean nisRet = new NisLiveBean();
		
		// 可见光人脸检测
		int iOP = FaceInterface.cw_op_t.CW_OP_DET | FaceInterface.cw_op_t.CW_OP_KEYPT | FaceInterface.cw_op_t.CW_OP_QUALITY;
		DetectBean detBeanVis = m_FaceSDK.FaceDetect(dataVis, widthVis, heightVis, formatVis, 0, 0, iOP);
		if (detBeanVis.ret != 0) {
			nisRet.ret = detBeanVis.ret;
			return nisRet;
		} else {
			if (detBeanVis.faceNum < 1) {
				nisRet.ret = FaceInterface.cw_nirliveness_err_t.CW_NIRLIV_ERR_VIS_NO_FACE;
				return nisRet;
			}
		}
		// 只取第一个人脸
		int iKeyPtNum = detBeanVis.faceInfos[0].keypt_y.length;
		float visKeyPtScore = detBeanVis.faceInfos[0].keyptScore;
		float visSkinScore = detBeanVis.faceInfos[0].scores[6];
		float[] visKeypt_x = new float[iKeyPtNum];
		float[] visKeypt_y = new float[iKeyPtNum];
		System.arraycopy(detBeanVis.faceInfos[0].keypt_x, 0, visKeypt_x, 0, iKeyPtNum);
		System.arraycopy(detBeanVis.faceInfos[0].keypt_y, 0, visKeypt_y, 0, iKeyPtNum);
		
		// 红外检测
		DetectBean detBeanNir = m_FaceSDK.FaceDetect(dataNir, widthNir, heightNir, formatNir, 0, 0, iOP);
		if (detBeanNir.ret != 0) {
			nisRet.ret = detBeanNir.ret;
			return nisRet;
		} else {
			if (detBeanNir.faceNum < 1) {
				nisRet.ret = FaceInterface.cw_nirliveness_err_t.CW_NIRLIV_ERR_NIR_NO_FACE;
				return nisRet;
			}
		}
		// 只取第一个人脸
		float nirKeyPtScore = detBeanNir.faceInfos[0].keyptScore;
		float nirSkinScore = detBeanNir.faceInfos[0].scores[6];
		float[] nirKeypt_x = new float[iKeyPtNum];
		float[] nirKeypt_y = new float[iKeyPtNum];
		System.arraycopy(detBeanNir.faceInfos[0].keypt_x, 0, nirKeypt_x, 0, iKeyPtNum);
		System.arraycopy(detBeanNir.faceInfos[0].keypt_y, 0, nirKeypt_y, 0, iKeyPtNum);
		
		// 红外检测参数赋值
		FaceNisLiveParam nisParam = new FaceNisLiveParam();
		nisParam.nLandmarks = iKeyPtNum; // 关键点个数
		// 可见光图片赋值
		nisParam.visWidth = widthVis;
		nisParam.visHeight = heightVis;
		nisParam.visFormat = formatVis; 
		// 红外图片赋值
		nisParam.nirWidth = widthNir;
		nisParam.nirHeight = heightNir;
		nisParam.nirFormat = formatNir;

		nisRet.ret = m_faceLive.cwFaceNirLivenessDet(m_NisHandle, nisParam,
				dataVis, visSkinScore, visKeyPtScore, visKeypt_x, visKeypt_y,
				dataNir, nirSkinScore, nirKeyPtScore, nirKeypt_x, nirKeypt_y,
				nisRet.nisRetInfo);
		
		return nisRet;
	}

}