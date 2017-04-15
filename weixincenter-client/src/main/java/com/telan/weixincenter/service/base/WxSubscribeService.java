package com.telan.weixincenter.service.base;

import java.util.List;

import com.telan.weixincenter.domain.base.WxSubscribeDO;
import com.telan.weixincenter.result.WXBaseResult;

/**
 * @author : zhangchao
 * @date : 2015年7月31日 下午12:43:33
 * @Description: 粉丝关注者管理接口
 */
public interface WxSubscribeService {

	/**
	 * @author : zhangchao
	 * @date : 2015年8月3日 上午10:32:26
	 * @Description: 修改关注者的状态
	 */
	void updateSubscribeByOpenId(Long wxMerchantId, String fromUserName, Integer subscribeType);

	/**
	 * @author : zhangchao
	 * @date : 2015年8月3日 上午11:14:59
	 * @Description: 根据参数查询关注者的信息集合
	 */
	List<WxSubscribeDO> getWxSubscribeListByOpenIdAndUserId(Long wxMerchantId,
			String fromUserName);

	List<WxSubscribeDO> queryChannelFansListByChannelId(Long wxMerchantId ,Long channelId);
	
	/**
	 * @author : zhangchao
	 * @date : 2015年11月27日 下午6:11:59
	 * @Description: 根据渠道ID查询粉丝的数量
	 */
	int getWxSubscribeCountByChannelIdAndUserId(Long wxMerchantId, Long channelId);
	
	/**
	 * 根据用户的id查询出关注者信息
	 * @param userId
	 * @return
	 */
	public WXBaseResult<WxSubscribeDO> getSubscribeInfoByUserId(Long userId);
	
	/**
	 * @author : zhangchao
	 * @date : 2016年4月12日 下午5:37:30
	 * @Description: 根据用户OPENID查询粉丝信息
	 * 
	 * 
	 */
	WXBaseResult<WxSubscribeDO> getWxSubscribeInfoByOpenId(String openId);
	

}
