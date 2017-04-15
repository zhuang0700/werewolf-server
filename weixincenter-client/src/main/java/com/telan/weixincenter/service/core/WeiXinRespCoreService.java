package com.telan.weixincenter.service.core;

import java.util.Map;

import com.telan.weixincenter.domain.menu.AccessTokenDO;


/**
 * @author : zhangchao
 * @date : 2015年7月23日 下午2:00:14
 * @Description: 微信核心接口响应
 */
public interface WeiXinRespCoreService 
{

	/**
	 * @author : zhangchao
	 * @param url_path 
	 * @param basePath 
	 * @date : 2015年7月23日 下午4:10:35
	 * @Description: 微信核心处理数据接口
	 */
	String processRequest(Map<String, String> requestMap,String url_path);

	AccessTokenDO getAccessToken(String appid, String appsercet);

	String getJsapiTicket(String accessToken,String appid);

//	User setSubscribeMsgAndReturnUserId(WxUserInfoPo userInfo, String openId, String access_token);
}
