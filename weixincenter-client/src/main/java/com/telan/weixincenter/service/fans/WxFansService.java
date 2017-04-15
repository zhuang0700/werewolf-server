package com.telan.weixincenter.service.fans;

import java.util.List;

import com.telan.weixincenter.domain.fans.WxFansTagDO;
import com.telan.weixincenter.result.WXBaseResult;
import com.telan.weixincenter.domain.fans.WxFansDO;

/**
 * @author : zhangchao
 * @date : 2015年8月7日 上午11:56:03
 * @Description: 微信粉丝
 */
public interface WxFansService{
	
	public WXBaseResult<WxFansDO> getFans(long userId) ;
	
	/**
	 * @author : zhangchao
	 * @date : 2015年8月11日 下午6:13:00
	 * @Description: 查询粉丝总数
	 */
	int getCountFansNumsByAll(long wxMerchantId);

	int getTodayAddFansNums(long wxMerchantId, String dateToday);

	int getMonthAddFansNums(long wxMerchantId, String firstDay, String lastDay);

	void delFansByUserId(long wxMerchantId);
	
//	JSONObject getFansJSONObjectStr(String accessToken);
//
//	WxSubscribeFansDTO getJSONObjectStr(String accessToken, String oppenid);
//
//	JSONObject getUserGroup(String accessToken, String outStr);


	List<WxFansTagDO> getFansTagByUserId(long wxMerchantId);

	void updateFansByOpenid(String openidli, String groupid);

}
