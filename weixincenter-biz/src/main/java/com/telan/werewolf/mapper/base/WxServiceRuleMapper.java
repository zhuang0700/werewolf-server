package com.telan.werewolf.mapper.base;

import com.telan.werewolf.domain.base.WxServiceRuleDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxServiceRuleMapper{

	List<WxServiceRuleDO> queryRuleBackListByMerchantId(@Param("wxMerchantId") Long wxMerchantId);

	WxServiceRuleDO selectByPrimaryKey(@Param("id") Long id);

	int updateByPrimaryKeySelective(WxServiceRuleDO wxServiceRule);

	int insertSelective(WxServiceRuleDO wxServiceRule);

	int deleteByPrimaryKey(@Param("id") Long id);


	
	
	
}