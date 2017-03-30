package com.telan.werewolf.mapper.base;

import com.telan.werewolf.domain.base.WxMerchantDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxMerchantMapper{

	WxMerchantDO selectByOpenId(@Param("toUserName") String toUserName);

	List<WxMerchantDO> selectWxMerchantByEmployeeId(@Param("employeeId") Long employeeId);

	List<WxMerchantDO> getAllMerchantInfo();

	WxMerchantDO selectByPrimaryKey(@Param("id") Long id);

	int updateByPrimaryKey(WxMerchantDO wxMerchantDO);

	int insert(WxMerchantDO wxMerchantDO);

	WxMerchantDO selectByAppId(@Param("appId") String appId);
	
}