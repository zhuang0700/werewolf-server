package com.telan.weixincenter.mapper.fans;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

public interface WxFansGroupMapper{

	void delGroupByMerchantId(@Param("wxMerchantId") Long wxMerchantId);

	JSONObject getMerchantGroup(String accessToken, String outStr);

}