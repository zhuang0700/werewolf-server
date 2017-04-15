package com.telan.weixincenter.service.fans;

import java.util.List;

import com.telan.weixincenter.domain.fans.WxFansTagDO;

/**
 * @author : zhangchao
 * @date : 2015年8月7日 上午11:57:20
 * @Description: 微信粉丝标签
 */
public interface WxFansTagService{

	List<WxFansTagDO> getFansTagByUserId(Long wxMerchantId);

}
