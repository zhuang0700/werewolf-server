package com.telan.weixincenter.service.base;

import com.telan.weixincenter.domain.base.WxServiceBackDO;

/**
 * @author : zhangchao
 * @date : 2015年7月29日 上午10:41:20
 * @Description: 自动回复接口
 */
public interface WxServiceBackService {

	/**
	 * @author : zhangchao
	 * @date : 2015年8月3日 上午11:16:43
	 * @Description: 查询回复的信息
	 */
	WxServiceBackDO queryWxBackByUserIdAndBackType(Long wxMerchantId, Integer backType);

}
