package com.telan.werewolf.mapper.channel;


import com.telan.werewolf.domain.channel.WxChannelClassDO;

import java.util.List;

public interface WxChannelClassMapper{

	List<WxChannelClassDO> queryChannelClassListByMerchantId(Long wxMerchantId);

	int insertSelective(WxChannelClassDO wxChannelClass);

	int deleteByPrimaryKey(Long channelClassId);

	WxChannelClassDO selectByPrimaryKey(Long channelClassId);

	int updateByPrimaryKeySelective(WxChannelClassDO wxChannelClass);

}
