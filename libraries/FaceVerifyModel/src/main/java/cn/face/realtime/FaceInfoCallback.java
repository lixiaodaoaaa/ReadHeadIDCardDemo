/**
 * Project Name:cloudwalk2
 * File Name:FaceInfoCallback.java
 * Package Name:cn.cloudwalk.callback
 * Date:2016-4-27
 * Copyright @ 2010-2016 Cloudwalk Information Technology Co.Ltd All Rights Reserved.
 *
 */

package cn.face.realtime;

import cn.face.sdk.FaceGroup;

/**
 * ClassName: FaceInfoCallback <br/>
 * Description: <br/>
 * date: 2016-4-27 <br/>
 *
 * @author 284891377
 * @version 
 * @since JDK 1.7
 */
public interface FaceInfoCallback {
    /**
     * 
     * detectFaceInfo:人脸检测回调. <br/>
     * @author:284891377   Date: 2016年6月16日 下午2:27:30
     * @param FaceGroup  每个人脸质量信息和人脸坐标信息
     * @since JDK 1.7
     */
//	public void detectFaceInfo(FaceInfo[] faceInfos,int faceNum);

    public void detectFaceInfo(FaceGroup faceGroup, byte[] data, int width, int height);

}

