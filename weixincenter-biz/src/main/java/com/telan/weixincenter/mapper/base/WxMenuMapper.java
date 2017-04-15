package com.telan.weixincenter.mapper.base;

import com.telan.weixincenter.domain.base.WxMenuDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxMenuMapper{

	List<WxMenuDO> findAllWxMenu(@Param("wxMerchantId") Long wxMerchantId);

	int insertSelective(WxMenuDO wxMenu);

	WxMenuDO selectByPrimaryKey(@Param("id") Long id);

	int updateByPrimaryKeySelective(WxMenuDO wxMenu);

	int deleteByPrimaryKey(@Param("id") Long id);
	

	
	
}