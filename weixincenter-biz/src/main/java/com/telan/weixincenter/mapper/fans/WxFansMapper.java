package com.telan.weixincenter.mapper.fans;


import com.telan.weixincenter.domain.fans.WxFansDO;
import com.telan.weixincenter.domain.fans.WxFansTagDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxFansMapper{
	
	public WxFansDO getFans(long userId);

	int getCountFansNumsByAll(@Param("wxMerchantId") long wxMerchantId);

	int getTodayAddFansNums(@Param("wxMerchantId") long userId, @Param("dateToday") String dateToday);

	int getMonthAddFansNums(@Param("wxMerchantId") long userId, @Param("firstDay") String firstDay, @Param("lastDay") String lastDay);

	void delFansByMerchantId(@Param("wxMerchantId") long wxMerchantId);


	List<WxFansTagDO> getFansTagByMerchantId(@Param("wxMerchantId") long wxMerchantId);

	List<WxFansDO> getFansByMerchantId(WxFansDO fans);

	void updateFansByOpenid(@Param("openidli") String openidli, @Param("groupid") String groupid);

	int insertSelective(WxFansDO fans);


}