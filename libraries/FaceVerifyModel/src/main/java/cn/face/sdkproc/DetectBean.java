package cn.face.sdkproc; /**
 * Project Name:localSdkDemo
 * File Name:DetectBean.java
 * Package Name:cn.cloudwalk.sdk
 * Date:2016年9月21日上午10:17:22
 * Copyright @ 2010-2016 Cloudwalk Information Technology Co.Ltd All Rights Reserved.
 *
*/



import cn.face.sdk.FaceInfo;

/**
 * ClassName:DetectBean <br/>
 * Description: TODO Description. <br/>
 * Date: 2016年9月21日 上午10:17:22 <br/>
 * 
 * @author 284891377
 * @version
 * @since JDK 1.7
 */
public class DetectBean {
	public int ret;
	public final int MAX_NUM=6;
	public int faceNum;
	public FaceInfo[] faceInfos;
	public DetectBean() {
		faceInfos=new FaceInfo[MAX_NUM];
		for(int i=0;i<MAX_NUM;i++){
			faceInfos[i]=new FaceInfo();
		}
	}
	
}
