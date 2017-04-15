package com.telan.weixincenter.event.processer;

import com.telan.weixincenter.core.util.WeixinApiUrlHolder;
import com.telan.weixincenter.domain.base.WxMerchantDO;
import com.telan.weixincenter.model.request.SubscribeEventRequest;
import com.telan.weixincenter.model.response.BaseRspMsg;
import com.telan.weixincenter.service.Impl.MessageReplyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class WapWeixinProcessor extends BaseProcessor{
	@Autowired
	private WeixinApiUrlHolder weixinApiUrlHolder;
	@Autowired
	private MessageReplyServiceImpl messageReplyService;
	
	
	@Override
	public BaseRspMsg processSubscribeEvent(SubscribeEventRequest eventMsg, WxMerchantDO wxMerchantDO) {
		// 默认返回的文本消息内容
		BaseRspMsg response = null;
		if (wxMerchantDO != null) {
//			subscribeService.subscribeIgnoreRegisterUser((SubscribeEventRequest) eventMsg, wxMerchantDO);
			response = messageReplyService.getCommonEventReply(eventMsg, wxMerchantDO.getId(),
					weixinApiUrlHolder.getWeixinCenterDomain());

		}
		return response;
	}
	
}
