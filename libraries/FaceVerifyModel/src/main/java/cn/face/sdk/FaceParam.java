package cn.face.sdk;

public class FaceParam {
	/**
	 * roi区域设置, 默认整帧图像
	 **/
	public int roiX; 
	public int roiY;
	public int roiWidth;
	public int roiHeight;
	/**
	 * 人脸尺寸范围-最小尺寸，默认100
	 **/
	public int minSize;
	/**
	 * 人脸尺寸范围-最大尺寸，默认400
	 **/
	public int maxSize;

}