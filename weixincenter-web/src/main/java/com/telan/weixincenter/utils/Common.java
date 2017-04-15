package com.telan.weixincenter.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * 通用的工具类 包括字符串、文件、 日期等用到时可自行添加完善
 * @author xusq
 */
public class Common {
	
	/**
	 * 判断是否为空字符串
	 * @param string
	 * @return 字符串是否为空
	 */
	public static boolean isEmpty(String string) {
		boolean result = false;
		if(string == null) {
			return true;
		}
		if("".equals(string.trim())) {
			return true;
		}
		return result;
	}
	
	/**
	 * 判断对象是否为空
	 * @param object 对象
	 * @return boolean TRUE:空 FALST:不空
	 */
	public static boolean isNull(Object object){
		return object == null ? true : false;
	}
	//--------------------字符串是否在集合里---------------------//
	/**
	 * 值是否在集合里
	 * @param string 值
	 * @param array 集合
	 * @return boolean TRUE:在集合里 FALST:不在集合里
	 */
	public static boolean inArray(String string, Object[] array) {
		boolean result = false;
		for(Object object : array) {
			if(object == null) {
				continue;
			}
			if(string.equals(object.toString())) {
				result = true;
			}
		}
		return result;
	}
	/**
	 * 判断集合是否为空
	 * @param object 集合
	 * @return boolean TRUE:空 FALST:不空
	 */
	public static boolean isEmptyArray(Object object){
		if(!isNull(object)){
			if(object instanceof Object[]){
				return ((Object[])object).length > 0 ? false : true;
			}else if(object instanceof List){
				return ((List<?>)object).isEmpty();
			}else if(object instanceof Map){
				return ((Map<?,?>)object).isEmpty();
			}
		}
		return true;
	}
	
	/**
	 * 判断字符串是否在集合里
	 * @param string
	 * @param array
	 * @return 字符串在集合里的索引
	 */
	public static Integer inArrayOfIndex(String string, Object[] array) {
		Integer result = -1;
		for(int i = 0; i < array.length; i++) {
			Object object = array[i];
			if(object == null) {
				continue;
			}
			if(string.equals(object.toString())) {
				result = i;
				break;
			}
		}
		return result;
	}
	//--------------------时间相关---------------------//
	/**
	 * 获取当前时间的时间戳
	 * @return Timestamp
	 */
	public static Timestamp getNow(){
		return new Timestamp(new Date().getTime());
	}
	/**
	 * 获取当前时间的时间戳串
	 * @param (format)
	 * @return String  Timestamp
	 */
	public static String getNowStamp(String...  format){
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");   
		if(null==format){
			sdf=new SimpleDateFormat("format");   
		}
        return sdf.format(new Date())+new Random().nextInt(9)+new Random().nextInt(9)+new Random().nextInt(9);  
	}
	/**
	 * 获取纯数字的UUID串
	 * @return String  UUID
	 */
	public static String getUUIDStamp(){
		String uuid = UUID.randomUUID().toString().replace("-", "");  
	  //  System.out.println(uuid.length());  
	   // System.out.println(uuid);  
		return uuid;
	}

	/**
	 * 日期转字符串
	 * @param date  日期
	 * @param format 格式 (默认yyyy-MM-dd) 可自定义 但记住大小写(yyyyMMddHHmmss)  
	 * @return "2014-02-01"
	 */
	public static String dateToString(Date date, String format){
		if(isNull(date)){
			return "";
		}
		if(isEmpty(format)){
			format="yyyy-MM-dd";
		}
		try {
			  SimpleDateFormat sdf = new SimpleDateFormat(format);
			  return sdf.format(date);
		} catch (Exception e) {
			
		}
		 return "";
	}
	/**
	 * 日期转字符串
	 * @param date  日期
	 * @param format 格式 (默认yyyy-MM-dd) 可自定义 但记住大小写(yyyyMMddHHmmss)  
	 * @return "2014-02-01"
	 */
	public static Date stringToDate(String dateStr, String format){
		if(isEmpty(dateStr)){
			return null;
		}
		if(isEmpty(format)){
			format="yyyy-MM-dd";
		}
	    try
	    {
	    	SimpleDateFormat formatter = new SimpleDateFormat(format);
	    	return  formatter.parse(dateStr);
	    }catch (Exception e) {
			e.printStackTrace();
		}
	    return null;
	}
	//--------------------数字相关---------------------//
	/**
	 * 字符串转Integer
	 * @param intStr  字符串
	 * @return Integer  转换值
	 */
	public static Integer stringToInteger(String intStr){
		return !Common.isEmpty(intStr) ? Integer.parseInt(intStr) : null;
	}
	
	/**
	 * 字符串转Float
	 * @param intStr  字符串
	 * @return Float  转换值
	 */
	public static Float stringToFloat(String intStr){
		return !Common.isEmpty(intStr) ? Float.parseFloat(intStr) : null;
	}
	
	/**
	 * 字符串转Long
	 * @param intStr  字符串
	 * @return Long  转换值
	 */
	public static Long stringToLong(String intStr){
			return !Common.isEmpty(intStr) ? Long.parseLong(intStr) : null;
	}
	
	/**
	 * 数组[] 转 字符串
	 * @param arry  数组
	 * @return String  转换后字符串
	 */
	public static String arryToString(Object[] arry){
		if(arry == null)
			return "";
		return Arrays.toString(arry).replace("[", "").replace("]", "").replace(" ", "");
	}

	/**
	 * 获取指定长度的随机数字序列
	 * 
	 * @param n 指定长度
	 * @return 随机数字序列
	 */
	public static String getRandom(Integer n) {
		String result = "";
		Random random = new Random(System.currentTimeMillis());
		for(int i = 0; i < n; i++) {
			result += random.nextInt(10);
		}
		return result;
	}
	
	/**
	 * 随机 1- max(不含) 的正整数
	 * 
	 * @param max
	 * @return
	 */
	public static Integer randomNumber(Integer max) {
		Random ran = new Random();
		return ran.nextInt(max - 1) + 1;
	}
	
	//--------------------文件相关---------------------//
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
	
	
	/**
	 * 获取项目 请求BASE路径
	 * @param request
	 * @return
	 */
	public static String getRootRequestPath(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		return basePath;
	}
	
	/**
	 * 获取项目 物理根路径
	 * @param request
	 * @return
	 */
	public static String getRealPath(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath("/");
	}
	
	/**
	 * 获取项目 物理根路径
	 * @param orgFileName 原文件名
	 * @return
	 */
	public static String getNewFileName(String orgFileName, String ntype){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = (!Common.isEmpty(ntype) ? ntype+"_" : "") + sdf.format(new Date())+ getFileExt(orgFileName);
		return fileName;
	}
	
	/**
	 * 正则匹配
	 * 
	 * @param regular 正则表达式
	 * @param value 要匹配的值
	 * @return 是否匹配
	 * */
	public static boolean regularMatches(String regular, String value) {
		boolean returnValue = false;
		if(value != null) {
			Pattern p = Pattern.compile(regular);
			if(p.matcher(value).matches()) {
				returnValue = true;
			}
		}
		return returnValue;
	}
	/**
	 * 正则匹配邮箱
	 * */
	public static boolean regularEmail(String value) {
			
		return regularMatches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$",value);
	}
	/**
	 * 正则手机号
	 * */
	public static boolean regularMobile(String value) {
		return regularMatches("^1\\d{10}$",value);
	}
	/**
	 * MD5加密
	 * */
	public static String md5(String plainText) {
		if(Common.isEmpty(plainText))return "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte[] b = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString().toUpperCase();
			//System.out.println("result: " + buf.toString());// 32位的加密
			//System.out.println("result: " + buf.toString().substring(8, 24));// 16位的加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	
	public static void main(String[] args) {

	}
}
