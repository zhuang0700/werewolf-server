package com.telan.weixincenter.mapper.base;

import com.telan.weixincenter.domain.base.WxSubscribeDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxSubscribeMapper{

	void updateSubscribeByOpenId(@Param("wxMerchantId") Long wxMerchantId, @Param("fromUserName") String fromUserName, @Param("subscribeType") Integer subscribeType);

	List<WxSubscribeDO> getWxSubscribeListByOpenIdAndMerchantId(@Param("wxMerchantId") Long wxMerchantId, @Param("fromUserName") String fromUserName);

	List<WxSubscribeDO> queryChannelFansListByChannelId(@Param("wxMerchantId") Long wxMerchantId, @Param("channelId") Long channelId);

	int getWxSubscribeCountByChannelIdAndMerchantId(@Param("wxMerchantId") Long wxMerchantId, @Param("channelId") Long channelId);

	int getSubscribeNumByMerchantIdAndChannelId(@Param("wxMerchantId") Long wxMerchantId, @Param("channelId") Long channelId,
												@Param("subscribe") Integer subscribe);

	WxSubscribeDO queryWxSubscribeByOpenIdAndMerchantId(@Param("wxMerchantId") Long wxMerchantId,
														@Param("fromUserName") String fromUserName);

	int updateById(WxSubscribeDO wxSubscribe);

	int insertSelective(WxSubscribeDO wxSubscribe);
	
	int insert(WxSubscribeDO wxSubscribe);

	int updateByOpenId(WxSubscribeDO wxSubscribe);
	
	WxSubscribeDO queryWxSubscribeByUserId(@Param("userId") long userId);
	
	public WxSubscribeDO getSubscribeDO(@Param("openId") String openId, @Param("wxMerchantId") long wxMerchantId) ;
	
	
	public WxSubscribeDO selectByPrimaryKey(long id);

	WxSubscribeDO getWxSubscribeInfoByOpenId(@Param("openId") String openId);

	int countWxSubscribeChannel(Long merchantId, Long channelId, int subscribeType);

}