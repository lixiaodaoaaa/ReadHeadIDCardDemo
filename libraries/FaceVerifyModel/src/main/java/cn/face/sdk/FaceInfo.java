package cn.face.sdk;


public class FaceInfo {

	public final static int CHECKED_FACE=1;

	public FaceInfo()
	{

	}

	public int detected; // 0: 跟踪到的人脸; 1: 检测到的人脸; 2:检测到但不会被进行后续计算(关键点)的人脸; 
                         // 3: 可能是静态误检框；4:大角度人脸; 5:关键点错误; 6:不需再处理的人脸（只有标记为1的人脸，关键点、
                         // 对齐、质量分才有效；但除0之外其他都可能有口罩分）7:被估计为低质量人脸

	public int trackId;  // 人脸ID（ID<0表示没有进入跟踪）
	
	// face rect
	public int x;        // 人脸框
	public int y;        
	public int width;    
	public int height;  
	
	// face_point关键点，最多68个关键点，目前使用9点关键点模型
	public float[]   keypt_x;      // 关键点x坐标
	public float[]   keypt_y;      // 关键点y坐标
	public float     keyptScore;   // 关键点得分
	
	// face_aligned
	public byte[] alignedData;  // 对齐人脸数据，用来提特征
	public int alignedW;    
	public int alignedH;      
	public int nChannels;     
	
	// face_quality
	public int   errcode;		// 质量分析错误码
	public float[] scores;      // 质量分分数项，具体含义（根据数据下标顺序）:
	  /* 0 - 人脸质量总分，0.65-1.0
	   * 1 - 清晰度，越大表示越清晰，推荐范围0.65-1.0
	   * 2 - 亮度，越大表示越亮，推荐范围0.2-0.8
	   * 3 - 人脸角度，左转为正，右转为负
	   * 4 - 人脸角度，抬头为正，低头为负
	   * 5 - 人脸角度，顺时针为正，逆时针为负
	   * 6 - 肤色接近真人肤色程度，越大表示越真实，推荐范围0.5-1.0
	   * 7 - 戴黑框眼镜置信度，越大表示戴黑框眼镜的可能性越大，推荐范围0.0-0.5
	   * 8 - 戴墨镜的置信分，越大表示戴墨镜的可能性越大，推荐范围0.0-0.5
	   */
}
