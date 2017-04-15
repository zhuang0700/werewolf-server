package com.telan.weixincenter.mapper.channel;

import com.telan.weixincenter.domain.channel.WxChannelDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxChannelMapper{

	List<WxChannelDO> queryChannelListByWxChannelClassId(Long channelClassId);

	List<WxChannelDO> queryChannelListByMerchantId(@Param("wxMerchantId") Long wxMerchantId);

	List<WxChannelDO> queryChannelListByWxChannel(WxChannelDO wxChannel);

	//List<WxChannelFansDO> queryChannelFansListByChannelId(@Param("id")Long id);

	int insertSelective(WxChannelDO wxChannel);

	int updateByPrimaryKeySelective(WxChannelDO wxChannel);

	WxChannelDO selectByPrimaryKey(Long parseInt);

	int deleteByPrimaryKey(@Param("id") Long id);

}
