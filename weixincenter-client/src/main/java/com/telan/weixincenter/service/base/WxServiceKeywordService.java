package com.telan.weixincenter.service.base;

import java.util.List;

import com.telan.weixincenter.domain.base.WxServiceKeywordDO;

/**
 * @author : zhangchao
 * @date : 2015年7月29日 上午10:41:54
 * @Description: 关键词管理接口
 */
public interface WxServiceKeywordService{

	/**
	 * @author : zhangchao
	 * @date : 2015年8月3日 上午11:15:36
	 * @Description: 根据关键词查询
	 */
	List<WxServiceKeywordDO> infoKeyWordLike(String content, Long wxMerchantId);

	void deleteByRuId(Long ruleId);

	List<WxServiceKeywordDO> queryWxKeyWordListByRuleId(Long ruleId);

}
