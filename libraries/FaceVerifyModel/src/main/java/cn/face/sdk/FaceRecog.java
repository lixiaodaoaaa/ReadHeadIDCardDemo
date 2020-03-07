package cn.face.sdk;

public class FaceRecog {

	static FaceRecog recog = null;
	
	public FaceRecog() {
		FaceCommon.loadLibrarys();
	}

	public static FaceRecog getInstance() {

		if (null == recog) {
			recog = new FaceRecog();
		}
		return recog;
	}


	// 人脸特征识别SDK
	static public native int cwCreateRecogHandle(String pConfigurePath,
                                                 String pLicence,
                                                 int emRecogPattern);

	static public native int cwReleaseRecogHandle(int pRecogHandle);

	static public native int cwGetFeatureLength(int pRecogHandle);
	
	static public native int cwGetFaceFeature(int pRecogHandle, byte[] dataAlign, int iWidth, int iHeight, int iChannels, byte[] pFeatureData);
	
	static public native int cwComputeMatchScore(int pRecogHandle, byte[] pFeaProbe, byte[] pFeaFiled, int iFeaFiledNum, float[] pScores);

}
