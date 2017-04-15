package com.telan.weixincenter.service.base;

import java.util.List;

import com.telan.weixincenter.domain.base.WxMenuItemDO;
/**
 * @author : zhangchao
 * @date : 2015年7月29日 上午10:40:30
 * @Description: 子菜单接口
 */
public interface WxMenuItemService {

	WxMenuItemDO queryWxItemInfo(Integer evenkeyNum, Long userId);

	List<WxMenuItemDO> queryItemListByMenuId(Long menuId);

	void deleteByMenuId(Long menuId);
	

}
