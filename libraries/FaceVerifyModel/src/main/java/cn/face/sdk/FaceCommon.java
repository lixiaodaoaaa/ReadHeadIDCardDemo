package cn.face.sdk;

public class FaceCommon {
	
	private static void loadLibrary(String libraryName) {
		System.loadLibrary(libraryName);
	}

	public static void loadLibrarys() {
		// TODO Auto-generated method stub
		loadLibrary("crypto");
		loadLibrary("ssl");
		loadLibrary("deepnet");
		loadLibrary("CWFaceDetTrack");
		loadLibrary("CwRecog");
		loadLibrary("CwFaceAttribution");
		loadLibrary("CWFaceSDK");
		loadLibrary("CWFaceSDKJni");
	}
}
