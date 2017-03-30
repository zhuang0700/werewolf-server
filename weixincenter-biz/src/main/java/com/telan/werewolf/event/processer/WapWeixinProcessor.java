package com.telan.werewolf.event.processer;

import com.telan.werewolf.core.util.WeixinApiUrlHolder;
import com.telan.werewolf.domain.base.WxMerchantDO;
import com.telan.werewolf.model.request.SubscribeEventRequest;
import com.telan.werewolf.model.response.BaseRspMsg;
import com.telan.werewolf.service.Impl.MessageReplyServiceImpl;
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
