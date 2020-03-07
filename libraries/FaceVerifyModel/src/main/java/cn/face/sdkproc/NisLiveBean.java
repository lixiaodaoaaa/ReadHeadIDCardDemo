package cn.face.sdkproc; /**
 * Project Name:localSdkDemo
 * File Name:RecogBean.java
 * Package Name:cn.cloudwalk.sdk
 * Date:2016年9月21日上午10:17:22
 * Copyright @ 2010-2016 Cloudwalk Information Technology Co.Ltd All Rights Reserved.
 *
*/



import cn.face.sdk.FaceNisLiveResInfo;

/**
 * NisLiveBean <br/>
 * Description: TODO Description. <br/>
 * Date: 2016年9月21日 上午10:17:22 <br/>
 * 
 * @author 284891377
 * @version
 * @since JDK 1.7
 */
public class NisLiveBean {
	/**
	 * >=20000：错误码，0：成功
	 */
	public int ret;
	/**
	 * 返回的红外检测信息数组
	 */
	public FaceNisLiveResInfo nisRetInfo;
	
	public NisLiveBean() {
		ret = 0;
		nisRetInfo=new FaceNisLiveResInfo();
	}
}
