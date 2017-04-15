package com.telan.weixincenter.mapper.channel;


import com.telan.weixincenter.domain.channel.WxChannelClassDO;

import java.util.List;

public interface WxChannelClassMapper{

	List<WxChannelClassDO> queryChannelClassListByMerchantId(Long wxMerchantId);

	int insertSelective(WxChannelClassDO wxChannelClass);

	int deleteByPrimaryKey(Long channelClassId);

	WxChannelClassDO selectByPrimaryKey(Long channelClassId);

	int updateByPrimaryKeySelective(WxChannelClassDO wxChannelClass);

}
