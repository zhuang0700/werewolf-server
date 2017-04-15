package com.telan.weixincenter.mapper.base;

import com.telan.weixincenter.domain.base.WxServiceBackDO;
import org.apache.ibatis.annotations.Param;

public interface WxServiceBackMapper{

	WxServiceBackDO queryWxBackByMerchantIdAndBackType(@Param("wxMerchantId") Long wxMerchantId, @Param("backType") Integer backType);

	int updateByPrimaryKeySelective(WxServiceBackDO wxServiceBack);

	int insertSelective(WxServiceBackDO wxServiceBack);


	
}