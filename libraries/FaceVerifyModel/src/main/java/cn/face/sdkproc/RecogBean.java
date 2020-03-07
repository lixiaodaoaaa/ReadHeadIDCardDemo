/**
 * Project Name:localSdkDemo
 * File Name:RecogBean.java
 * Package Name:cn.cloudwalk.sdk
 * Date:2016年9月21日上午10:17:22
 * Copyright @ 2010-2016 Cloudwalk Information Technology Co.Ltd All Rights Reserved.
 *
*/

package proc;

/**
 * ClassName:RecogBean <br/>
 * Description: TODO Description. <br/>
 * Date: 2016年9月21日 上午10:17:22 <br/>
 * 
 * @author 284891377
 * @version
 * @since JDK 1.7
 */
public class RecogBean {
	/**
	 * >=20000：错误码，0：成功
	 */
	public int ret;
	/**
	 * 人脸相似度 范围0-1，如果m个probe特征与n个filed特征比对，则分数数组个数为m*n个
	 */
	public float[] scores;
	
	
	public RecogBean(int iScoresNum) {
		scores = new float[iScoresNum];
	}
}
