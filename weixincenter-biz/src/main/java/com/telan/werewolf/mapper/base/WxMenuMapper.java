package com.telan.werewolf.mapper.base;

import com.telan.werewolf.domain.base.WxMenuDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxMenuMapper{

	List<WxMenuDO> findAllWxMenu(@Param("wxMerchantId") Long wxMerchantId);

	int insertSelective(WxMenuDO wxMenu);

	WxMenuDO selectByPrimaryKey(@Param("id") Long id);

	int updateByPrimaryKeySelective(WxMenuDO wxMenu);

	int deleteByPrimaryKey(@Param("id") Long id);
	

	
	
}