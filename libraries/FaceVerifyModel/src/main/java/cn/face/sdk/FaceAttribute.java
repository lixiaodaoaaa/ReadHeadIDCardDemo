package cn.face.sdk;


public class FaceAttribute {

	static FaceAttribute attri = null;
	
	public FaceAttribute() {
		FaceCommon.loadLibrarys();
	}

	public static FaceAttribute getInstance() {

		if (null == attri) {
			attri = new FaceAttribute();
		}
		return attri;
	}


	static public native int cwCreateAttributeHandle(String pConfigurePath, String pLicence);

	static public native int cwReleaseAttributeHandle(int pAttributeHandle);
	
	static public native int cwGetAgeEval(int pAttributeHandle, byte[] dataAlign, int iWidth, int iHeight, int iChannels,FaceAttrRet attr);
	static public native int cwGetGenderEval(int pAttributeHandle, byte[] dataAlign, int iWidth, int iHeight, int iChannels,FaceAttrRet attr);
	
	static public native int cwGetRaceEval(int pAttributeHandle, byte[] dataAlign, int iWidth, int iHeight, int iChannels,FaceAttrRet attr);

}
