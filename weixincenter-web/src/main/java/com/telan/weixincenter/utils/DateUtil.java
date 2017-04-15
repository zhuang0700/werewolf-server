package com.telan.weixincenter.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

 
public class DateUtil {

	private static final String FORMAT="yyyy-MM-dd HH:mm";
	public static void main(String[] args) throws Exception {
		
		System.out.println("->时间转换："+formatTime("1399426892") );
	}

	public static String formatTime(String createTime) 
	 {
		 long msgCreateTime = Long.parseLong(createTime) * 1000L;    
		  
		    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
		  
		    return format.format(new Date(msgCreateTime)); 
	 }
	/**
	 * 获得当前日期的字符串格式
	 * @param format
	 * @return
	 */
	public static String getCurDateStr(String format){
		Calendar c=Calendar.getInstance();
		return date2Str(c,format);
	}
	/**
	 * 把calendar转换成字符串
	 * @param c
	 * @return
	 */
	public static String date2Str(Calendar c){//yyyy-MM-dd HH:mm:ss
		return date2Str(c,null);
	}
	public static String date2Str(Calendar c,String format){
		if(c==null){
			return null;
		}		
		return date2Str(c.getTime(),format);
	}

	public static String date2Str(Date d){//yyyy-MM-dd HH:mm:ss
		return date2Str(d,null);
	}
	
	/**
     *把日期转换成制定的格式
     * @param d
     * @param format
     * @return
     */
	public static String date2Str(Date d,String format){//yyyy-MM-dd HH:mm:ss
		if(d==null ){
			return null;
		}
		if(format==null || format.length()==0){
			format=FORMAT;
		}
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		String s=sdf.format(d);
		return s;
	}
	
	/**
	 * 字符串转换成日期
	* @param str
	 * @return date
	 */
	 public static Date StrToDate(String str) throws Exception{	   
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    Date date = null;
	    date = format.parse(str);
	    return date;
	 }


}  