package com.telan.weixincenter.core.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static void main(String[] args) throws Exception {

		System.out.println("->时间转换：" + formatTime("1399426892"));
	}

	public static String formatTime(String createTime) {
		long msgCreateTime = Long.parseLong(createTime) * 1000L;

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return format.format(new Date(msgCreateTime));
	}
	public static Date getWeixinDate(String createTime) {
		long msgCreateTime = Long.parseLong(createTime) * 1000L;
		return new Date(msgCreateTime);
	}

}