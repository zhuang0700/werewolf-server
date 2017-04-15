package com.telan.weixincenter.service.base;

import java.util.List;

import com.telan.weixincenter.domain.base.WxMerchantDO;
import com.telan.weixincenter.domain.base.WxMenuDO;

/**
 * @author : zhangchao
 * @date : 2015年7月29日 上午10:39:53
 * @Description: 自定义菜单接口
 */
public interface WxMenuService{

	List<WxMenuDO> findAllWxMenu(Long wxMerchantId);

	int createMenu(WxMerchantDO wxUserInfoPo);

}
