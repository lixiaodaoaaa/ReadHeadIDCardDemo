package cn.face.sdk;


public class FaceDetTrack {

	static FaceDetTrack sCJNI = null;
	
	public FaceDetTrack() {
		FaceCommon.loadLibrarys();
	}

	public static FaceDetTrack getInstance() {

		if (null == sCJNI) {
			sCJNI = new FaceDetTrack();
		}
		return sCJNI;
	}


	static public native int cwCreateDetHandle(String pConfigFile, String pLicence);

	static public native int cwReleaseDetHandle(int pDetector);

	static public native int cwGetFaceParam(int pDetector, FaceParam param);
	static public native int cwSetFaceParam(int pDetector, FaceParam param, String pConfigFile);

	static public native int cwFaceDetection(int pDetector,
											 byte[] pFrameImg,
											 int iWidth, 
											 int iHeight, 
											 int iFormat, 
											 int iAngle, 
											 int iMirror,
											 int iOp,
											 FaceInfo[] pFaceBuffer);
	
	static public native int cwResetDetTrackState(int pDetector);

}
