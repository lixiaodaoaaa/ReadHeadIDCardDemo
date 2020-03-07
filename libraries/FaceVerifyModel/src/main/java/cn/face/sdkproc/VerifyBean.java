package cn.face.sdkproc; /**
 * Project Name:localSdkDemo
 * File Name:VerifyBean.java
 * Package Name:cn.cloudwalk.sdk
 * Date:2016年9月21日上午10:17:22
 * Copyright @ 2010-2016 Cloudwalk Information Technology Co.Ltd All Rights Reserved.
 */


/**
 * ClassName:VerifyBean <br/>
 * Description: TODO Description. <br/>
 * Date: 2016年9月21日 上午10:17:22 <br/>
 *
 * @author 284891377
 * @version
 * @since JDK 1.7
 */
public class VerifyBean {

    public static final int VERIFY_SUCCESS = 0;
    /**
     * >=20000：错误码，0：成功
     */
    public int ret;
    /**
     * 人脸相似度 范围0-1
     */
    public float score;
}
