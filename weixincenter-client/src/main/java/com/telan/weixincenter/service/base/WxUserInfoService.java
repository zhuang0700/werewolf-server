package com.telan.weixincenter.service.base;

import java.util.List;

import com.telan.weixincenter.domain.base.WxMerchantDO;


/**
 * @author : zhangchao
 * @date : 2015年7月15日 下午4:17:09
 * @Description: 微信公众号基础接口
 */
public interface WxUserInfoService{

	/**
	 * @author : zhangchao
	 * @date : 2015年8月3日 上午10:32:04
	 * @Description: 过呢据openId 查询公众号的绑定信息
	 */
	WxMerchantDO selectByOpenId(String toUserName);

	/**
	 * @author : zhangchao
	 * @date : 2015年8月7日 下午5:14:23
	 * @Description: 根据管理员ID查询公众号信息
	 */
	List<WxMerchantDO> selectWxUserInfoByEmployeeId(Long employeeId);

	/**
	 * @author : zhangchao
	 * @date : 2015年8月27日 上午10:26:21
	 * @Description: 查询所有的公众号用户
	 */
	List<WxMerchantDO> getAllUserInfo();

}
