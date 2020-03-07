package cn.face.sdk;


public interface FaceInterface {
	
	// 图像格式
	interface cw_img_form_t extends FaceInterface {
		int CW_IMAGE_GRAY8 = 0;
		int CW_IMAGE_BGR888 = 1;
		int CW_IMAGE_BGRA8888 = 2;
		int CW_IMAGE_RGB888 = 3;
		int CW_IMAGE_RGBA8888 = 4;
		int CW_IMAGE_YUV420P = 5;
		int CW_IMAGE_YV12 = 6;
		int CW_IMAGE_NV12 = 7;
		int CW_IMAGE_NV21 = 8;
		int CW_IMAGE_BINARY = 9;
	}

	// 图像旋转角度（逆时针）
	interface cw_img_angle_t extends FaceInterface {
		int CW_IMAGE_ANGLE_0 = 0;
		int CW_IMAGE_ANGLE_90 = 1;
		int CW_IMAGE_ANGLE_180 = 2;
		int CW_IMAGE_ANGLE_270 = 3;
	}
	
	// 图像镜像
	interface cw_img_mirror_t extends FaceInterface {
		int CW_IMAGE_MIRROR_NONE = 0;        // 不镜像  
		int CW_IMAGE_MIRROR_HOR = 1;         // 水平镜像
		int CW_IMAGE_MIRROR_VER = 2;         // 垂直镜像
		int CW_IMAGE_MIRROR_HV = 3;          // 垂直和水平镜像
	}
	
	// 检测开关选项
	interface cw_op_t extends FaceInterface {
		int CW_OP_DET     = 0;                  // (1 << 0) 进行人脸检测，并返回人脸矩形位置
		int CW_OP_TRACK   = 2;                  // (1 << 1)进行人脸跟踪，并返回人脸跟踪的ID
		int CW_OP_KEYPT   = 4;		            // (1<<2)进行人脸关键点检测，并返回人脸上的关键点坐标信息
		int CW_OP_ALIGN   = 8;                  // (1 << 3)进行人脸图像对齐，并返回对齐人脸数据，用来提特征
		int CW_OP_QUALITY = 16;                 // (1 << 4)人脸质量分（质量分子项开关在配置文件中配置）
		int CW_OP_ALL     = 30;                 // （所有开关综合）总开关
	}
	
	// 特征句柄功能
	interface cw_recog_pattern_t extends FaceInterface {
		int CW_FEATURE_EXTRACT = 0;
		int CW_RECOGNITION = 1;
	}

	// 通用错误码
	interface cw_errcode_t extends FaceInterface {
		int CW_SDKLIT_OK = 0;                        // 成功
		
		int CW_UNKNOWN_ERR = 20000;                  // 未知错误
		int CW_DETECT_INIT_ERR = 20001;				 // 初始化人脸检测器失败:如加载模型失败等
		int CW_KEYPT_INIT_ERR = 20002;				 // 初始化关键点检测器失败：如加载模型失败等
		int CW_QUALITY_INIT_ERR = 20003;			 // 初始化跟踪器失败：如加载模型失败等
		
		int CW_DET_ERR = 20004;                      // 检测失败
		int CW_TRACK_ERR = 20005;                    // 跟踪失败
		int CW_KEYPT_ERR = 20006;                    // 提取关键点失败
		int CW_ALIGN_ERR = 20007;                    // 对齐人脸失败
		int CW_QUALITY_ERR = 20008;                  // 质量评估失败

		int CW_EMPTY_FRAME_ERR = 20009;              // 空图像
		int CW_UNSUPPORT_FORMAT_ERR = 20010;         // 图像格式不支持
		int CW_ROI_ERR = 20011;                      // ROI设置失败
		int CW_UNINITIALIZED_ERR = 20012;            // 尚未初始化
		int CW_MINMAX_ERR = 20013;                   // 最小最大人脸设置失败
		int CW_OUTOF_RANGE_ERR = 20014;              // 数据范围错误	
		int CW_UNAUTHORIZED_ERR = 20015;             // 未授权	
		int CW_METHOD_UNAVAILABLE = 20016;	         // 方法无效
		int CW_PARAM_INVALID = 20017;                // 参数无效
		int CW_BUFFER_EMPTY = 20018;				 // 缓冲区空
		
		int CW_FILE_UNAVAILABLE = 20019;             // 文件不存在：如加载的模型不存在等.
		int CW_DEVICE_UNAVAILABLE = 20020;   	     // 设备不存在：如GPU等.
		int CW_DEVICE_ID_UNAVAILABLE = 20021; 		 // 设备id不存在：如GPU id等
		int CW_EXCEEDMAXHANDLE_ERR = 20022;          // 超过授权最大句柄数
		
		int CW_RECOG_FEATURE_MODEL_ERR = 20023;		 // 加载特征识别模型失败   
		int CW_RECOG_ALIGNEDFACE_ERR = 20024;		 // 对齐图片数据错误
		int CW_RECOG_MALLOCMEMORY_ERR = 20025;       // 预分配特征空间不足  

		int CW_RECOG_FEATUREDATA_ERR = 20026;		 // 特征数据错误
		int CW_RECOG_EXCEEDMAXFEASPEED = 20027;	     // 超过授权最大提特征速度
		int CW_RECOG_EXCEEDMAXCOMSPEED = 20028;	     // 超过授权最大比对速度
		int CW_RECOG_GROUPSIZE_ERR = 20029;          // 特征比对特征数N超过最大授权数
		int CW_RECOG_CONVERT_ERR = 20030;            // 特征转换失败
		int CW_RECOG_NOFACEDET = 20031;              // 未检测到人脸
		
		int CW_LICENCE_JSON_CREATE_ERR = 20032;      // Json操作失败
		int CW_LICENCE_DECRYPT_ERR = 20033;          // 加密失败
		int CW_LICENCE_HTTP_ERROR = 20034;           // HTTP失败
		int CW_LICENCE_MALLOCMEMORY_ERR = 20035;     // 授权内存分配不足
		int CW_LICENCE_KEY_DEVICE_ERR = 20036;       // 获取设备文件错误
		int CW_LICENCE_KEY_LICENSE_ERR = 20037;      // 获取授权文件错误
		int CW_LICENCE_KEY_INSTALL_ERR = 20038;      // 安装授权文件错误
		
		int CW_ATTRI_AGEGENDER_MODEL_ERR=20039;      // 加载年龄性别模型失败
		int CW_ATTRI_EVAL_AGE_ERR=20040;             // 年龄识别失败
		int CW_ATTRI_EVAL_GENDER_ERR=20041;          // 性别识别失败
		int CW_ATTRI_EVAL_RACE_ERR=20042;            // 种族识别失败
		
	}
	
	// 质量分检测错误码
interface cw_quality_errcode_t extends FaceInterface {
		int CW_QUALITY_OK            = 0;				// 质量分数据有效
		int	CW_QUALITY_NO_DATA       = 20150;		    // 质量分数据无效，原因：尚未检测
		int	CW_QUALITY_ERROR_UNKNOWN = 20151;           // 未知错误
	}

	// 红外活体检测结果返回值
	interface cw_nirliv_det_rst_t extends FaceInterface {
		int CW_NIR_LIV_DET_LIVE = 0;				// 以阈值0.5判断为活体
		int CW_NIR_LIV_DET_UNLIVE = 1;				// 以阈值0.5判断为非活体
		int CW_NIR_LIV_DET_DIST_FAILED = 2;			// 人脸距离检测未通过
		int CW_NIR_LIV_DET_SKIN_FAILED = 3;			// 人脸肤色检测未通过
		int CW_NIR_LIV_DET_NO_PAIR_FACE = 4;		// 未匹配到人脸
		int CW_NIR_LIV_DET_IS_INIT = 5;				// 红外活体检测结果初始值
	}

	// 红外活体检测错误码
	interface cw_nirliveness_err_t extends FaceInterface {
		int CW_NIRLIV_OK = 0;                          // 红外检测成功
			
		int CW_NIRLIV_ERR_CREATE_HANDLE = 26000;       // 创建红外活体检测句柄失败
		int CW_NIRLIV_ERR_FREE_HANDLE = 26001;		   // 释放红外活体检测句柄失败
		int CW_NIRLIV_ERR_FACE_PAIR = 26002;		   // 人脸匹配初始化失败
		int CW_NIRLIV_ERR_CREAT_LOG_DIR = 26003;	   // 创建日志路径失败	
		int CW_NIRLIV_ERR_MODEL_NOTEXIST = 26004;      // 输入模型不存在
		int CW_NIRLIV_ERR_MODEL_FAILED = 26005;        // 输入模型初始化失败
		int CW_NIRLIV_ERR_INPUT_UNINIT = 26006;        // 输入未初始化
		int CW_NIRLIV_ERR_NIR_NO_FACE = 26007;         // 输入红外图片没有人脸
		int CW_NIRLIV_ERR_VIS_NO_FACE = 26008;         // 输入可见光图片没有人脸
		int CW_NIRLIV_ERR_NO_PAIR_FACE = 26009;        // 输入可见光和红外图片人脸未能匹配
		int CW_NIRLIV_ERR_PUSH_DATA = 26010;           // 输入数据失败
		int CW_NIRLIV_ERR_NUM_LANDMARKS = 26011;       // 输入可见光图片和红外图片关键点个数不等
		int CW_NIRLIV_ERR_NO_LANDMARKS = 26012;        // 输入红外图片没有人脸关键点
		int CW_NIRLIV_ERR_INPUT_IMAGE = 26013;         // 输入红外图片或者可见光图片不是多通道
		int CW_NIRLIV_ERR_UNAUTHORIZED = 26014;        // 没有license（未授权）
		int CW_NIRLIV_ERR_FACE_NUM_ERR = 26015;        // 未开启人脸匹配开关时，可见光或红外图像人脸大于1
		int CW_NIRLIV_ERR_CAM_UNCW = 26016;	           // 非云从定制摄像头
		int CW_NIRLIV_ERR_UNKNOWN = 26017;             // 未知结果
		int CW_NIRLIV_ERR_MAXHANDLE = 26018;		   // 超过最大红外活体最大授权句柄数
		int CW_NIRLIV_ERR_NIRIMAGE = 26019;		       // 输入红外图片数据错误
		int CW_NIRLIV_ERR_VISIMAGE = 26020;			   // 输入可见光图片数据错误
	}
}
	
	
	