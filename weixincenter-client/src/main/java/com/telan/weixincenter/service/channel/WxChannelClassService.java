package com.telan.weixincenter.service.channel;

import java.util.List;

import com.telan.weixincenter.domain.channel.WxChannelClassDO;

/**
 * @author : zhangchao
 * @date : 2015年10月26日 上午11:20:12
 * @Description: 渠道类型
 */
public interface WxChannelClassService{

	List<WxChannelClassDO> queryChannelClassListByUserId(Long wxMerchantId);
}
