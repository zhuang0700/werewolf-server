package com.telan.werewolf.mapper.base;

import com.telan.werewolf.domain.base.WxServiceBackDO;
import org.apache.ibatis.annotations.Param;

public interface WxServiceBackMapper{

	WxServiceBackDO queryWxBackByMerchantIdAndBackType(@Param("wxMerchantId") Long wxMerchantId, @Param("backType") Integer backType);

	int updateByPrimaryKeySelective(WxServiceBackDO wxServiceBack);

	int insertSelective(WxServiceBackDO wxServiceBack);


	
}