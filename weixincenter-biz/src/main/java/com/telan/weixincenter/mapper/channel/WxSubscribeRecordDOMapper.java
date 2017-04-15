package com.telan.weixincenter.mapper.channel;

import com.telan.weixincenter.domain.channel.SubScribeRecordGroupDO;
import com.telan.weixincenter.domain.channel.SubscribeRecordDO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface WxSubscribeRecordDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SubscribeRecordDO record);

    int insertSelective(SubscribeRecordDO record);

    SubscribeRecordDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SubscribeRecordDO record);

    int updateByPrimaryKey(SubscribeRecordDO record);
    
    List<SubscribeRecordDO> getWxSubscribeRecordDoListByOpenIdAndMerchantId(
			@Param("wxMerchantId") Long wxMerchantId, @Param("fromUserName") String fromUserName);

	SubscribeRecordDO getSubScribeRecordDOByMerchantIdAndOpenId(@Param("wxMerchantId") Long wxMerchantId,
																@Param("fromUserName") String fromUserName);

	int getSubscribeNumByMerchantIdAndChannelId(@Param("wxMerchantId") Long wxMerchantId, @Param("channelId") Long channelId,
												@Param("subscribe") Integer subscribe, @Param("dBefore") Date dBefore);

	List<SubScribeRecordGroupDO> querySubscribeRecordByMerchantIdGroupByChannel(
			@Param("wxMerchantId") Long wxMerchantId, @Param("dBefore") Date dBefore);

	int getSubscribeNumByMerchantIdAndChannelIdForChannelUpdate(@Param("wxMerchantId") Long wxMerchantId,
																@Param("channelId") Long channelId, @Param("subscribe") Integer subscribe);

}