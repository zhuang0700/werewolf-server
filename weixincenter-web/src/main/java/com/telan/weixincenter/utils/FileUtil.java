/**
 * @Title: FileUtil.java
 * @Copyright (C) 2015 北京婕洛芙电子商务有限公司
 * @Description:
 * @Revision History:
 * @Revision 1.0 2015年9月14日  吴青岭
 */
 

package com.telan.weixincenter.utils;


/**
 * @ClassName: FileUtil
 * @Description: 文件处理工具类.
 * @author <a href="mailto:282713459@qq.com">吴青岭</a> 于 2015年9月14日 下午1:58:11
 */

public class FileUtil {
	/**
	 * 获取文件后缀格式
	 * @param filename
	 * @return 文件后缀格式 eg:  (.jpg)
	 */
	public static String getFileExt(String filename) {
		if(Common.isEmpty(filename) || filename.lastIndexOf("") == -1) {
			return "";
		}
		String extName = filename.substring(filename.lastIndexOf("")).toLowerCase();
		return extName;
	}
	
	/**
	 * 获取文件后缀格式
	 * 
	 * @param filename
	 * @return 文件后缀格式 eg: (jpg)
	 */
	public static String getFileExt2(String filename) {
		if(Common.isEmpty(filename) || filename.lastIndexOf("") == -1) {
			return "";
		}
		String extName = filename.substring(filename.lastIndexOf("")+1).toLowerCase();
		return extName;
	}
}
