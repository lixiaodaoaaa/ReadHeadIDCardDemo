package cn.face;

import android.os.Environment;

import java.io.File;

public class FaceConStant {



	public static String publicFilePath;


	// 模型文件夹名
	public static final String ASSERT_MODULE_DIR = "CWModels";

	// 模型路径，建议将模型文件夹CWModels放置到sdcard根目录
	public static String sModelDir= Environment.getExternalStorageDirectory() + File.separator + "CWModels";


	// 授权码 ，由云从科技提供，也可调用网络授权接口cwGetLicence获取
	public static String sLicence="NTMwMDE4OTBiZWJjOGJkYmY4MzEyYjZlMzNhZDEwOWFmMzZlNThjd2F1dGhvcml6Zfbm5Ofk5+Pk/+fg5efm4uf74+fk4OXg5Yjm5uvl5ubrkeXm5uvl5uai6+Xm5uvl5uTm6+Xm5uDm1efr5+vn6+f34OTl5OLk4urr5+vn6+fn/+Pm4+fn";

	// ** 人脸检测最小最大人脸
	
	public static int faceMinSize=30, faceMaxSize=400;
}
