package com.telan.weixincenter.service.base;

import java.util.List;

import com.telan.weixincenter.domain.base.WxServiceRuleDO;

/**
 * @author : zhangchao
 * @date : 2015年7月29日 上午10:42:25
 * @Description: 规则回复
 */
public interface WxServiceRuleService{

	List<WxServiceRuleDO> queryRuleBackListByMerchantId(Long wxMerchantId);

}
