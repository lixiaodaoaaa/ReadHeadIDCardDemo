package cn.face.sdk;

;

public class FaceNisLiveness {

	static FaceNisLiveness nirLive = null;
	
	public FaceNisLiveness() {
		FaceCommon.loadLibrarys();
	}

	public static FaceNisLiveness getInstance() {

		if (null == nirLive) {
			nirLive = new FaceNisLiveness();
		}
		return nirLive;
	}

	static public native int cwCreateNirLivenessHandle(String pNirModelPath,
                                                       String pRecogModelPath,
                                                       String pPairFilePath,
                                                       String pLogPath,
                                                       float fSkinThresh,
                                                       String pLicence);

	static public native int cwReleaseNirLivenessHandle(int pHandle);
	
	static public native int cwFaceNirLivenessDet(int pHandle, FaceNisLiveParam pLivenessDetInfo, 
			          byte[] pVisData, float visSkinScore, float visKeyPtScore, float[] visKeypt_x, float[] visKeypt_y, 
			          byte[] pNirData, float nirSkinScore, float nirKeyPtScore,float[] nirKeypt_x, float[] nirKeypt_y, 
		              FaceNisLiveResInfo pNirLivenessRes);

}
