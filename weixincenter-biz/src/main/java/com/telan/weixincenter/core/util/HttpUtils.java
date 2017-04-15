package com.telan.weixincenter.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.UUID;

public class HttpUtils {
	private static Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);

	public static String doRequest(String requestUrl, HttpMethodEnum requestMethod, String paramBody) {

		String log = "UUID = "+UUID.randomUUID() + " ; HttpUtils : ";
		
		LOGGER.info(log + " http weixin api request >>> request = " + requestUrl + " ;paramBody = " + paramBody);
		
		String result = null;

		try {
			
			HttpsURLConnection httpUrlConn = setSSLContext(requestUrl,requestMethod);
			

			// 当有数据需要提交时
			if (null != paramBody) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(paramBody.getBytes("UTF-8"));
				outputStream.close();
			}

			result = connection2String(httpUrlConn);
			
		LOGGER.info(log + " http weixin api result >>> request = " + requestUrl + " ;paramBody = " + paramBody + " ;result" + result);
		} catch (ConnectException ce) {
			LOGGER.error(log + "Weixin request { " + requestUrl + " } connection timed out. paramBody : " + paramBody + ce.getMessage(),ce);
		} catch (Exception e) {
			LOGGER.error(log + "http request error:{} requestUrl : " + requestUrl + "paramBody : " + paramBody, e);
		} 

		return result;
	}

	private static String connection2String(HttpsURLConnection httpUrlConn) {

		
		StringBuffer buffer = new StringBuffer();
		// 将返回的输入流转换成字符串
		BufferedReader bufferedReader = null;
		try {
			
			bufferedReader = new BufferedReader(new InputStreamReader(httpUrlConn.getInputStream(), "utf-8"));
		

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}

		} catch (IOException e) {
			LOGGER.error("HttpsURLConnection2String is error," + e.getMessage(), e);
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					LOGGER.error("bufferedReader close error," + e.getMessage(), e);
				}
			}
		}
		
		httpUrlConn.disconnect();
		
		String result = buffer.toString();
		
		return result;
	}

	public static String doGet(String requestUrl, String paramBody) {
		return doRequest(requestUrl, HttpMethodEnum.GET, paramBody);
	}

	public static String doPost(String requestUrl, String paramBody) {
		return doRequest(requestUrl, HttpMethodEnum.POST, paramBody);
	}
	
	public static String doRequest(String requestUrl, HttpMethodEnum requestMethod) {

		String result = null;
		
		String log = "UUID = "+UUID.randomUUID();
		LOGGER.info(log + " http weixin api request >>> request = " + requestUrl);
		
		try {
			HttpsURLConnection httpUrlConn = setSSLContext(requestUrl,requestMethod);
			// 将返回的输入流转换成字符串
			result = connection2String(httpUrlConn);
			
			LOGGER.info(log + " http weixin api result >>> request = " + requestUrl + " ;result" + result);
		} catch (ConnectException ce) {
			LOGGER.error(log + " Weixin request {" + requestUrl + "} connection timed out." + ce.getMessage(),ce);
		} catch (Exception e) {
			LOGGER.error(log + " http request error:{} requestUrl :" + requestUrl + e.getMessage(), e);
		}

		return result;
	}

	
	private static HttpsURLConnection setSSLContext(String requestUrl,HttpMethodEnum requestMethod) throws Exception{
		
		HttpsURLConnection httpUrlConn = null;
		// 创建SSLContext对象，并使用我们指定的信任管理器初始化
		TrustManager[] tm = { new MyX509TrustManager() };
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		sslContext.init(null, tm, new java.security.SecureRandom());
		// 从上述SSLContext对象中得到SSLSocketFactory对象
		SSLSocketFactory ssf = sslContext.getSocketFactory();

		URL url = new URL(requestUrl);
		httpUrlConn = (HttpsURLConnection) url.openConnection();
		httpUrlConn.setSSLSocketFactory(ssf);

		httpUrlConn.setDoOutput(true);
		httpUrlConn.setDoInput(true);
		httpUrlConn.setUseCaches(false);
		// 设置请求方式（GET/POST）
		httpUrlConn.setRequestMethod(requestMethod.getCode());

		if (requestMethod == HttpMethodEnum.GET) {
			httpUrlConn.connect();
		}
		
		
		return httpUrlConn;
	}
}
