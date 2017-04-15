/**
 * @Title: MD5Util.java
 * @Copyright (C) 2015 北京婕洛芙电子商务有限公司
 * @Description:
 * @Revision History:
 * @Revision 1.0 2015年9月19日  吴青岭
 */
 

package com.telan.weixincenter.utils;

import java.security.MessageDigest;

/**
 * @ClassName: MD5Util
 * @Description: Description of this class
 * @author <a href="mailto:282713459@qq.com">吴青岭</a> 于 2015年9月19日 下午12:07:10
 */

public class MD5Util {
	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes(charsetname)));
		} catch (Exception exception) {
		}
		return resultString;
	}
	
	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
		"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
}
