package cn.face.sdk;




public class FaceVersion {

	static FaceVersion version = null;
	
	public FaceVersion() {
		FaceCommon.loadLibrarys();
	}

	public static FaceVersion getInstance() {

		if (null == version) {
			version = new FaceVersion();
		}
		return version;
	}


	// 版本信息及授权SDK
	static public native String cwGetSDKVersion();
	
	static public native String cwGetDeviceInfo();
	
	static public native String cwGetLicence(String sAppKey, String sSecret, String sProductId);

}
