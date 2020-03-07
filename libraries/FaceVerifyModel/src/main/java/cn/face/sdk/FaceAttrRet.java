package cn.face.sdk;

public class FaceAttrRet {

	/* *
	 * 年龄段
	 * 0为小孩，1为成年人   2为老人
	 * 
	 *  性别
	 * 0为女士，1为男士
	 * 
	 * 
	 * 普遍意义上的人种 黑、白、黄
	 * 0   黑种人   1      白种人     2  黄种人
	 * */
	public int m_iValue = 0;
	
	/* *
	 *  置信分数
	 *  为0--1之间的数字，置信分数越高，估值越可靠
	 * */
	public float m_fConfidence = 0.0f;
}