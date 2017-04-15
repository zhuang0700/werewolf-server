package com.telan.weixincenter.service.fans;

import com.alibaba.fastjson.JSONObject;
/**
 * @author : zhangchao
 * @date : 2015年8月7日 上午11:56:47
 * @Description: 微信粉丝分组
 */
public interface WxFansGroupService{

	JSONObject getUserGroup(String accessToken, String outStr);

	void delGroupByUserId(Long wxMerchantId);

}
