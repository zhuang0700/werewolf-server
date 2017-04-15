package com.telan.weixincenter.mapper.channel;

import com.telan.weixincenter.domain.channel.SubScribeStatisDO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface WxSubScribeStatisDOMapper {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(SubScribeStatisDO record);

    int insertSelective(SubScribeStatisDO record);

    SubScribeStatisDO selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(SubScribeStatisDO record);

    int updateByPrimaryKey(SubScribeStatisDO record);

    int deleteByMerchantId(@Param("wxMerchantId") Long wxMerchantId);

	SubScribeStatisDO getSubScribeStatisDOByMerchantIdAndChannelId(@Param("wxMerchantId") Long wxMerchantId,
                                                                   @Param("channelId") Long channelId, @Param("dBefore") Date dBefore);

	List<SubScribeStatisDO> queryChannelFansStatisListByMerchantId(@Param("wxMerchantId") Long wxMerchantId);

	int getSubscribeNumByMerchantIdAndChannelIdForChannelUpdate(@Param("wxMerchantId") Long wxMerchantId,
                                                                @Param("channelId") Long channelId, @Param("subscribeType") Integer subscribeType);

}