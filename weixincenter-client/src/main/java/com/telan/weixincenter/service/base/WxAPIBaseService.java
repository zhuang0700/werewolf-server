package com.telan.weixincenter.service.base;

import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
import com.telan.weixincenter.domain.fans.WxFansGroupDO;
import com.telan.weixincenter.domain.menu.AccessTokenDO;
import com.telan.weixincenter.domain.resp.WeixinMediaDO;


public interface WxAPIBaseService {
	//FIXME 接口不能反悔JSON对象出去，要返回结构化的对象。而且不要使用net.sf.json，使用fast JSON，这个的性能很好
	JSONObject getAllGroup(String access_token);
	
	public AccessTokenDO getAccessToken(String appid, String appsercet);

	JSONObject getUserGroup(String accessToken, String outStr);
	
	JSONObject addUserGroup(String access_token, String objgroup);
	
	JSONObject updateUserGroup(String access_token, Long groupId,
			String groupName);
	
	JSONObject delUserGroup(String access_token, Long groupId);
	
	JSONObject moveUserGroup(String access_token, String openidli,
			String groupId);
	
	List<WxFansGroupDO> getFansGroup(String access_token,Long wxMerchantId);
	
	JSONObject sendMassTemplate(String accessToken, Map<Object, Object> map);

	String shortURL(String accessToken, String URL);

	String downloadMedia(String serverId, Long wxMerchantId);

	WeixinMediaDO uploadMedia(Long wxMerchantId, String type, String filePath);
	
	
}
