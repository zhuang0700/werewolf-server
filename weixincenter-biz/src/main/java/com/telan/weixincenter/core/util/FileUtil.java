package com.telan.weixincenter.core.util;


import org.apache.commons.lang3.StringUtils;

public class FileUtil {
	/**
	 * 根据内容类型判断文件扩展名
	 * 
	 * @param contentType
	 *            内容类型
	 * @return
	 */
	public  static String getFileEndWitsh(String contentType) {
		String fileEndWitsh = "";
		if ("image/jpeg".equals(contentType))
			fileEndWitsh = ".jpg";
		else if ("image/png".equals(contentType))
			fileEndWitsh = ".png";
		else if ("audio/mpeg".equals(contentType))
			fileEndWitsh = ".mp3";
		else if ("audio/x-mpeg".equals(contentType))
			fileEndWitsh = ".mp3";
		else if ("audio/x-wav".equals(contentType))
			fileEndWitsh = ".wav";
		else if ("audio/amr".equals(contentType))
			fileEndWitsh = ".amr";
		else if ("video/mp4".equals(contentType))
			fileEndWitsh = ".mp4";
		else if ("video/mpeg4".equals(contentType))
			fileEndWitsh = ".mp4";
		return fileEndWitsh;
	}

	/**
	 * 获取文件后缀格式
	 * 
	 * @param filename
	 * @return 文件后缀格式 eg: (jpg)
	 */
	public  static String getFileExt2(String filename) {
		if (StringUtils.isEmpty(filename) || filename.lastIndexOf(".") == -1) {
			return "";
		}
		String extName = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
		return extName;
	}

}
