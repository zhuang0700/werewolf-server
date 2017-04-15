/**
 * @Title: MicroCodeUtil.java
 * @Copyright (C) 2015 北京婕洛芙电子商务有限公司
 * @Description:
 * @Revision History:
 * @Revision 1.0 2015年9月7日  吴青岭
 */
 

package com.telan.weixincenter.utils;


/**
 * @ClassName: MicroCodeUtil
 * @Description: code生成工具.
 * @author <a href="mailto:282713459@qq.com">吴青岭</a> 于 2015年9月7日 下午2:15:38
 */

public class MicroCodeUtil {

	/**
	 * 获取MF订单编号
	 */
	public static String getMFOrderCode() {
		/*if (mfOrderCounter.get() > 999999) {
			mfOrderCounter.set(1);
		}*/
		RandomGUID myGUID = new RandomGUID();
		StringBuffer buffer  = new StringBuffer();
		buffer.append("MF");
		buffer.append(DateUtil.getCurDateStr("yyyyMMdd"));
		/*buffer.append(String.format("%06d", mfOrderCounter.getAndIncrement()));*/
		buffer.append(myGUID.toString().substring(0,6));
		return buffer.toString();
	}
	
	
	/**
	 * 获取订单编号
	 */
	public static String getGFOrderCode() {
		/*if (gfOrderCounter.get() > 999999) {
			gfOrderCounter.set(1);
		}*/
		RandomGUID myGUID = new RandomGUID();
		StringBuffer buffer  = new StringBuffer();
		buffer.append("GF");
		buffer.append(DateUtil.getCurDateStr("yyyyMMdd"));
		buffer.append(myGUID.toString().substring(0,6));
		return buffer.toString();
	}
	/**
	 * @Description:获取32位字符串
	 * @param ordercode
	 * @return
	 * @throws
	 */
	public static String getpayOrderCode(String ordercode){
		RandomGUID myGUID = new RandomGUID();
		StringBuffer buffer  = new StringBuffer();
		buffer.append(ordercode);
		buffer.append(DateUtil.getCurDateStr("yyyyMMddHHmm"));
		buffer.append(myGUID.toString().substring(0,4));
		return buffer.toString();
	}
	/**
	 * @Description:更加orderId拼装32位字符
	 * @param orderId
	 * @return
	 * @throws
	 */
	public static String getpayOrderId(Integer orderId){
		String str = String.valueOf(orderId);
		int strLen = str.length();
	     StringBuffer sb = null;
	     while (strLen < 32) {
	           sb = new StringBuffer();
	           sb.append("0").append(str);// 左(前)补0
	        // sb.append(str).append("0");//右(后)补0
	           str = sb.toString();
	           strLen = str.length();
	     }
	     return str;
	}
}
