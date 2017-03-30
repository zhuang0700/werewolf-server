package com.telan.werewolf.mapper.base;

import com.telan.werewolf.domain.base.WxMenuItemDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxMenuItemMapper{


	WxMenuItemDO queryWxItemInfo(@Param("evenkeyNum") Integer evenkeyNum, @Param("wxMerchantId") Long wxMerchantId);

	List<WxMenuItemDO> queryItemListByMenuId(@Param("menuId") Long menuId);

	void deleteByMenuId(@Param("menuId") Long menuId);

	int deleteByPrimaryKey(@Param("id") Long id);

	WxMenuItemDO selectByPrimaryKey(@Param("id") Long id);

	int updateByPrimaryKeySelective(WxMenuItemDO wxMenuItem);

	int insertSelective(WxMenuItemDO wxMenuItem);


	
}