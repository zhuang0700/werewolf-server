package com.telan.weixincenter.core.util;

import com.alibaba.fastjson.JSONObject;

import com.telan.weixincenter.convert.WxUserInfoConvertor;
import com.telan.weixincenter.domain.menu.MenuDO;
import com.telan.weixincenter.domain.result.WxSubscribeFansDTO;
import com.telan.weixincenter.entity.WxUserInfo;
import com.telan.weixincenter.result.WXBaseResult;
import com.telan.weixincenter.result.WXReturnCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class WeixinApiProxy {

	private static Logger LOGGER = LoggerFactory.getLogger(WeixinApiProxy.class);

	@Autowired
	private WeixinApiUrlHolder weixinApiUrlHolder;

	/**
	 * 
	 * @param requestUrl
	 * @param requestMethod
	 * @param paramBody
	 * @return
	 */
	public static JSONObject httpRequest(String requestUrl, HttpMethodEnum requestMethod, String paramBody) {
		JSONObject jsonObject = null;
		String responseStr = HttpUtils.doRequest(requestUrl, requestMethod, paramBody);
		jsonObject = JSONObject.parseObject(responseStr);

		return jsonObject;
	}

	public static JSONObject httpRequest(String requestUrl, HttpMethodEnum requestMethod) {
		LOGGER.debug("requestUrl={}", requestUrl);

		JSONObject jsonObject = null;
		String responseStr = HttpUtils.doRequest(requestUrl, requestMethod);
		jsonObject = JSONObject.parseObject(responseStr);

		LOGGER.debug("jsonObject={}", jsonObject);

		return jsonObject;
	}

	@Deprecated
	public JSONObject getAccessToken(String appid, String appsecret) {

		String requestUrl = weixinApiUrlHolder.getAccessTokenUrl().replace("{APPID}", appid).replace("{APPSECRET}",
				appsecret);
		JSONObject jsonObject = httpRequest(requestUrl, HttpMethodEnum.GET);

		return jsonObject;
	}

	@Deprecated
	public JSONObject getJsapiTicket(String accessToken, String appid) {

		String requestUrl = weixinApiUrlHolder.getJsapiTicketUrl().replace("{ACCESS_TOKEN}", accessToken);
		JSONObject jsonObject = httpRequest(requestUrl, HttpMethodEnum.GET, null);

		return jsonObject;
	}

	public JSONObject getFansGroup(String accessToken, Long wxMerchantId) {

		JSONObject groupjsonObject = getAllGroup(accessToken);
		return groupjsonObject;
	}

	public JSONObject getSubscribeFansInfo(String accessToken, String fromUserName) {

		String requestUrl = weixinApiUrlHolder.getBaseUrl().replace("{ACCESS_TOKEN}", accessToken).replace("{FromUserName}",
				fromUserName);
		JSONObject jsonObject = httpRequest(requestUrl, HttpMethodEnum.GET);
		LOGGER.debug("getSubscribeFansInfo  accessToken={}, fromUserName={},  result={}", accessToken, fromUserName, jsonObject.toJSONString());
		return jsonObject;
	}
	public WXBaseResult<WxSubscribeFansDTO> getSubscribeFansDTO(String accessToken, String fromUserName) {
		WXBaseResult<WxSubscribeFansDTO> result = new WXBaseResult<WxSubscribeFansDTO>();
		try{
			JSONObject json = getSubscribeFansInfo(accessToken, fromUserName);
			if( json == null || StringUtils.isBlank(json.toJSONString()) ){
				result.setErrorCode(WXReturnCode.WEIXIN_GET_WX_USERINFO_ERROR);
				return result ;
			}
			WxUserInfo info = JSONObject.parseObject(json.toJSONString(), WxUserInfo.class) ;
			if( json == null || StringUtils.isBlank(json.toJSONString()) ){
				result.setErrorCode(WXReturnCode.JSON_PARSE_ERROR);
				return result ;
			}
			WxSubscribeFansDTO dto = WxUserInfoConvertor.wxUserInfo2WxSubscribeFansDTO(info);
			result.setValue(dto);
		}catch( Exception e){
			LOGGER.error("getSubscribeFansDTO  accessToken={},  fromUserName={}", accessToken, fromUserName);
			result.setErrorCode(WXReturnCode.SYSTEM_ERROR);
		}
		return result;
	}

	public WXBaseResult<WxSubscribeFansDTO> getOuathFansDTO(String accessToken, String openId) {
		WXBaseResult<WxSubscribeFansDTO> result = new WXBaseResult<WxSubscribeFansDTO>();
		try{
			JSONObject json = getWebAuthUserInfo(accessToken, openId);
			if( json == null || StringUtils.isBlank(json.toJSONString())
					|| !WxApiUtil.isWeixinRequestSuccess(json)){
				result.setErrorCode(WXReturnCode.WEIXIN_GET_WX_USERINFO_ERROR);
				return result ;
			}
			WxUserInfo info = JSONObject.parseObject(json.toJSONString(), WxUserInfo.class) ;
			if( json == null || StringUtils.isBlank(json.toJSONString()) ){
				result.setErrorCode(WXReturnCode.JSON_PARSE_ERROR);
				return result ;
			}
			WxSubscribeFansDTO dto = WxUserInfoConvertor.wxUserInfo2WxSubscribeFansDTO(info);
			result.setValue(dto);
		}catch( Exception e){
			LOGGER.error("getOuathFansDTO  accessToken={},  openId={}", accessToken, openId);
			result.setErrorCode(WXReturnCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	public JSONObject getWebAuthUserInfo(String accessToken, String FromUserName) {

		String requestUrl = weixinApiUrlHolder.getSnsapiUserInfoBaseUrl().replace("{ACCESS_TOKEN}", accessToken)
				.replace("{OPENID}", FromUserName);
		JSONObject jsonObject = httpRequest(requestUrl, HttpMethodEnum.GET);
		LOGGER.info("jsonObject:" + jsonObject);

		return jsonObject;
	}

	public JSONObject getOpenIdByOAuth2(String appId, String secret, String code) {
		String requestUrl = weixinApiUrlHolder.getSnsapiBaseurl().replace("{APPID}", appId).replace("{SECRET}", secret)
				.replace("{CODE}", code);
		JSONObject jsonObject = httpRequest(requestUrl, HttpMethodEnum.GET);
		LOGGER.debug("jsonObject:" + jsonObject);

		return jsonObject;
	}

	public JSONObject getFansJSONObjectStr(String accessToken) {

		String requestUrl = weixinApiUrlHolder.getFansUrl().replace("{ACCESS_TOKEN}", accessToken).replace("{NEXT_OPENID}",
				"");
		JSONObject jsonObject = httpRequest(requestUrl, HttpMethodEnum.GET);

		return jsonObject;
	}

	
	public JSONObject createMenu(MenuDO menu, String accessToken) {
		// 拼装创建菜单的url
		String url = weixinApiUrlHolder.getMenuCreateUrl().replace("{ACCESS_TOKEN}", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.toJSONString(menu);
		// 把为空的部分替换掉
		jsonMenu = jsonMenu.replaceAll(",null", "");
		jsonMenu = jsonMenu.replaceAll("null,", "");
		LOGGER.info("jsonMenu:" + jsonMenu);
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, HttpMethodEnum.POST, jsonMenu);
		
		return jsonObject;
	}

	// 通过用户的OpenID查询其所在的GroupID

	public JSONObject getUserGroup(String accessToken, String outStr) {

		String requestUrl = weixinApiUrlHolder.getUserGroupUrl().replace("{ACCESS_TOKEN}", accessToken);
		JSONObject jsonObject = httpRequest(requestUrl, HttpMethodEnum.POST, outStr);
		
		return jsonObject;
	}

	public JSONObject getAllGroup(String accessToken) {

		String requestUrl = weixinApiUrlHolder.getAllGroupUrl().replace("{ACCESS_TOKEN}", accessToken);
		JSONObject jsonObject = httpRequest(requestUrl, HttpMethodEnum.GET);
		
		return jsonObject;
	}

	
	public JSONObject addUserGroup(String accessToken, String outStr) {
		String requestUrl = weixinApiUrlHolder.getAddGroupUrl().replace("{ACCESS_TOKEN}", accessToken);
		JSONObject jsonObject = httpRequest(requestUrl, HttpMethodEnum.POST, outStr);
		
		return jsonObject;
	}

	public JSONObject updateUserGroup(String accessToken, Long groupId, String groupName) {

		JSONObject obj = new JSONObject();
		obj.put("name", groupName);
		obj.put("id", groupId);

		JSONObject objgroup = new JSONObject();
		objgroup.put("group", obj);

		String requestUrl = weixinApiUrlHolder.getUpdateGroupUrl().replace("{ACCESS_TOKEN}", accessToken);
		JSONObject jsonObject = httpRequest(requestUrl, HttpMethodEnum.POST, objgroup.toString());
		
		return jsonObject;
	}

	public JSONObject delUserGroup(String access_token, Long groupId) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		obj.put("id", groupId);

		JSONObject objgroup = new JSONObject();
		objgroup.put("group", obj);

		String requestUrl = weixinApiUrlHolder.getDelGroupUrl().replace("{ACCESS_TOKEN}", access_token);
		JSONObject jsonObject = httpRequest(requestUrl, HttpMethodEnum.POST, objgroup.toString());
		
		
		return jsonObject;
	}

	
	public JSONObject moveUserGroup(String accessToken, String openid, String groupid) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		obj.put("openid", openid);
		obj.put("to_groupid", groupid);

		String requestUrl = weixinApiUrlHolder.getMoveGroupUrl().replace("{ACCESS_TOKEN}", accessToken);
		JSONObject jsonObject = httpRequest(requestUrl, HttpMethodEnum.POST, obj.toString());
		
		return jsonObject;
	}

	public JSONObject sendMassTemplate(String accessToken, Map<Object, Object> map) {
		// TODO Auto-generated method stub
		String requestUrl = weixinApiUrlHolder.getSendMassTemplateUrl().replace("{ACCESS_TOKEN}", accessToken);
		
		JSONObject obj = JSONObject.parseObject(JSONObject.toJSONString(map));
		JSONObject jsonObject = httpRequest(requestUrl, HttpMethodEnum.POST, obj.toString());
		
		return jsonObject;
	}

	
	public JSONObject shortURL(String accessToken, String URL) {

		String requestUrl = weixinApiUrlHolder.getLongUrltoShortUrl().replace("{ACCESS_TOKEN}", accessToken);
		String jsonMsg = "{\"action\":\"long2short\",\"long_url\":\"" + URL + "\"}";
		LOGGER.info("jsonMsg:" + jsonMsg);
		JSONObject jsonObject = httpRequest(requestUrl, HttpMethodEnum.POST, jsonMsg);
		
		return jsonObject;
	}

	public JSONObject qrcodeCreateUrl(String accessToken, String Str) {

		String requestUrl = weixinApiUrlHolder.getQrcodeCreateUrl().replace("{ACCESS_TOKEN}", accessToken);
		// String jsonMsg =
		// "{\"action\":\"long2short\",\"long_url\":\""+URL+"\"}";
		String jsonMsg = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"0"
				+ Str + "\"}}}";
		LOGGER.info("jsonMsg:" + jsonMsg);
		JSONObject jsonObject = httpRequest(requestUrl, HttpMethodEnum.POST, jsonMsg);
		
		return jsonObject;
	}

	public JSONObject getUserSummaryUrl(String accessToken, String begin_date, String end_date) {
		String requestUrl = weixinApiUrlHolder.getUserSummaryUrl().replace("{ACCESS_TOKEN}", accessToken);
		String jsonMsg = "{\"begin_date\":\"" + begin_date + "\",\"end_date\":\"" + end_date + "\"}";
		LOGGER.info("getUserSummaryUrl_jsonMsg:" + jsonMsg);
		JSONObject jsonObject = httpRequest(requestUrl, HttpMethodEnum.POST, jsonMsg);
		
		return jsonObject;
	}

	
	public JSONObject getUserCumulateUrl(String accessToken, String begin_date, String end_date) {
		String requestUrl = weixinApiUrlHolder.getUserCumulateUrl().replace("{ACCESS_TOKEN}", accessToken);
		String jsonMsg = "{\"begin_date\":\"" + begin_date + "\",\"end_date\":\"" + end_date + "\"}";
		LOGGER.info("getUsercumulateUrl_jsonMsg:" + jsonMsg);
		JSONObject jsonObject = httpRequest(requestUrl, HttpMethodEnum.POST, jsonMsg);
		
		return jsonObject;
	}

	
	public JSONObject uploadMedia(String accessToken, String type, String mediaFileUrl) {
		String requestUrl = weixinApiUrlHolder.getUploadMediaUrl().replace("{ACCESS_TOKEN}", accessToken).replace("{TYPE}",
				type);
		
		JSONObject resultObj = null;
		
		OutputStream outputStream = null;
		BufferedReader bufferedReader = null;
		// 定义数据分隔符
		String boundary = "------------7da2e536604c8";
		try {
			URL uploadUrl = new URL(requestUrl);
			HttpURLConnection uploadConn = (HttpURLConnection) uploadUrl.openConnection();
			uploadConn.setDoOutput(true);
			uploadConn.setDoInput(true);
			uploadConn.setRequestMethod("POST");
			// 设置请求头Content-Type
			uploadConn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			// 获取媒体文件上传的输出流（往微信服务器写数据）
			outputStream = uploadConn.getOutputStream();

			URL mediaUrl = new URL(mediaFileUrl);
			HttpURLConnection meidaConn = (HttpURLConnection) mediaUrl.openConnection();
			meidaConn.setDoOutput(true);
			meidaConn.setRequestMethod("GET");

			// ======================
			String urlPath = meidaConn.getURL().getFile();
			LOGGER.info("urlPath:" + urlPath);
			String fileFullName = urlPath.substring(urlPath.lastIndexOf("/") + 1);
			LOGGER.info("fileFullName:" + fileFullName);
			// =======================
			// 从请求头中获取内容类型
			String contentType = meidaConn.getHeaderField("Content-Type");
			LOGGER.info("contentType:" + contentType);

			// 根据内容类型判断文件扩展名
			String fileExt = FileUtil.getFileEndWitsh(contentType);
			LOGGER.info("fileExt:" + fileExt);

			// 请求体开始
			outputStream.write(("--" + boundary + "\r\n").getBytes());
			outputStream.write(
					String.format("Content-Disposition: form-data; name=\"media\"; filename=\"file1%s\"\r\n", fileExt)
							.getBytes());
			outputStream.write(String.format("Content-Type: %s\r\n\r\n", contentType).getBytes());

			// 获取媒体文件的输入流（读取文件）
			BufferedInputStream bis = new BufferedInputStream(meidaConn.getInputStream());
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1) {
				// 将媒体文件写到输出流（往微信服务器写数据）
				outputStream.write(buf, 0, size);
			}
			// 请求体结束
			outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
			outputStream.close();
			bis.close();
			meidaConn.disconnect();

			// 获取媒体文件上传的输入流（从微信服务器读数据）
			InputStream inputStream = uploadConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			bufferedReader = new BufferedReader(inputStreamReader);
			StringBuffer buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			uploadConn.disconnect();

			// 使用JSON-lib解析返回结果
			resultObj = JSONObject.parseObject(buffer.toString());
			// 测试打印结果
			LOGGER.info("打印测试结果" + resultObj);
			
		} catch (Exception e) {
			String error = String.format("上传媒体文件失败：%s", e);
			LOGGER.error(error);
		}finally{
			try{
				if(outputStream != null){
					outputStream.close();
				}
				
				if(bufferedReader != null){
					bufferedReader.close();
				}
			}catch(Exception e){
				LOGGER.error("error to close stream",e);
			}
			
		}
		return resultObj;
	}

	
//	public String downloadMedia(String accessToken, String mediaId, String savePath) {
//		String myfileUrl = null;
//		// 拼接请求地址
//		String requestUrl = weixinApiUrlHolder.getDownloadMediaUrl().replace("{ACCESS_TOKEN}", accessToken)
//				.replace("{MEDIA_ID}", mediaId);
//		HttpURLConnection conn = null;
//		try {
//			URL url = new URL(requestUrl);
//			conn = (HttpURLConnection) url.openConnection();
//			conn.setDoInput(true);
//			conn.setRequestMethod("GET");
//
//			// if (!savePath.endsWith("/")) {
//			// savePath += "/";
//			// }
//			// 根据内容类型获取扩展名
//			String fileExt = FileUtil.getFileEndWitsh(conn.getHeaderField("Content-Type"));
//			LOGGER.info("fileExt:" + fileExt);
//			// 将mediaId作为文件名
//			// filePath = savePath + mediaId + fileExt;
//
//			// BufferedInputStream bis = new
//			// BufferedInputStream(conn.getInputStream());
//
//			// 判断文件名是否为空。为空set null值
//			myfileUrl = UploadFileUtil.uploadFile(conn.getInputStream(), FileUtil.getFileExt2(fileExt), null);
//
//			// FileOutputStream fos = new FileOutputStream(new File(filePath));
//			// byte[] buf = new byte[8096000];
//			// int size = 0;
//			// while ((size = bis.read(buf)) != -1)
//			// fos.write(buf, 0, size);
//			// fos.close();
//			// bis.close();
//			String info = String.format("下载媒体文件成功，myfileUrl=" + myfileUrl);
//			System.out.println(info);
//		} catch (Exception e) {
//			myfileUrl = null;
//			String error = String.format("下载媒体文件失败：%s", e);
//			System.out.println(error);
//		}finally{
//			if(conn != null){
//				conn.disconnect();
//			}
//		}
//		return myfileUrl;
//	}
}
