package com.telan.weixincenter.service.channel;

import java.util.List;

import com.telan.weixincenter.domain.channel.WxChannelDO;

public interface WxChannelService{

	List<WxChannelDO> queryChannelListByUserId(Long wxMerchantId);

	List<WxChannelDO> queryChannelListByWxChannel(WxChannelDO wxChannel);

	int addChannel(WxChannelDO wxChannel, String access_token);

	//List<WxChannelFansDO> queryChannelFansListByChannelId(Long channelId);

	List<WxChannelDO> queryChannelListByWxChannelClassId(String channelClassId);

	void updateWxChannelNumByChannelId(String qrscene_id, String fromUserName,
			Long wxMerchantId);

}
