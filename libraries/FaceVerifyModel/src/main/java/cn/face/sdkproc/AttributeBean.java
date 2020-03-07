package cn.face.sdkproc; /**
 * Project Name:localSdkDemo
 * File Name:AttributeBean.java
 * Package Name:cn.cloudwalk.sdk
 * Date:2016年9月21日上午10:17:22
 * Copyright @ 2010-2016 Cloudwalk Information Technology Co.Ltd All Rights Reserved.
 *
*/


/**
 * ClassName:AttributeBean <br/>
 * Description: TODO Description. <br/>
 * Date: 2016年9月21日 上午10:17:22 <br/>
 * 
 * @author 284891377
 * @version
 * @since JDK 1.7
 */
public class AttributeBean {
	/**
	 * >=20000：错误码，-1：未检测到人脸，0：成功
	 */
	public int ret;
	/**
	 * 年龄段
	 */
	public int ageGroup;
	/**
	 * 性别
	 */
	public int gender;
	
	/**
	 * 人种
	 */
	public int race;
	
}
