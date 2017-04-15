package com.telan.weixincenter.event.processer;

import com.alibaba.fastjson.JSONObject;
import com.telan.weixincenter.core.util.WeixinApiUrlHolder;
import com.telan.weixincenter.domain.base.WxMerchantDO;
import com.telan.weixincenter.model.Event;
import com.telan.weixincenter.model.MsgType;
import com.telan.weixincenter.model.request.*;
import com.telan.weixincenter.model.response.BaseRspMsg;
import com.telan.weixincenter.model.response.TextMessageResponse;
import com.telan.weixincenter.service.Impl.MessageReplyServiceImpl;
import com.telan.weixincenter.utils.AnswerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseProcessor implements EventProcessor {
	private static Logger LOGGER = LoggerFactory.getLogger(BaseProcessor.class);

	@Autowired
	private WeixinApiUrlHolder weixinApiUrlHolder;

	@Autowired
	private MessageReplyServiceImpl messageReplyService;

	public BaseRspMsg processRequest(BaseReqMsg requestMessage) {
		BaseRspMsg response = null;
		LOGGER.debug("requestMessage={}", JSONObject.toJSONString(requestMessage));

		// 公众帐号
		String toUserName = requestMessage.getToUserName();
		// 消息类型
		MsgType msgType = requestMessage.getMsgType();

		// 通过公众账号找userInfo
//		WxMerchantDO wxMerchantDO = wxMerchantManager.selectByOpenId(toUserName);
		WxMerchantDO wxMerchantDO = new WxMerchantDO();
//		if (wxMerchantDO != null)// 判断公众号信息是否存在
//		{
		switch (msgType) {
			case TEXT:
				response = processTextMessage((TextMessageRequest) requestMessage, wxMerchantDO);
				break;
//			case IMAGE:
//				response = processImageMessage((ImageMessageRequest) requestMessage, wxMerchantDO);
//				break;
//			case LOCATION:
//				response = processLocationMessage((LocationMessageRequest) requestMessage, wxMerchantDO);
//				break;
//			case VOICE:
//				response = processVoiceMessage((VoiceMessageRequest) requestMessage, wxMerchantDO);
//				break;

			case EVENT:
				response = processEventRequest((BaseEventReqMsg) requestMessage,
						weixinApiUrlHolder.getWeixinCenterDomain());
				break;
			default:
				response = processExceptionRequest(requestMessage, wxMerchantDO);
				break;

		}

//		} else {
//			LOGGER.warn("no matched merchant info!");
//			response = processOtherRequest(requestMessage, wxMerchantDO);
//		}

		LOGGER.debug("response={}",JSONObject.toJSONString(response));
		return response;
	}

	public BaseRspMsg processEventRequest(BaseEventReqMsg requestMessage, String urlPath) {
		Event event = requestMessage.getEvent();
		String toUserName = requestMessage.getToUserName();
		// 通过公众账号找userInfo
//		WxMerchantDO wxMerchantDO = wxMerchantManager.selectByOpenId(toUserName);
		WxMerchantDO wxMerchantDO = new WxMerchantDO();
		BaseRspMsg response = null;
		switch (event) {
		case SUBSCRIBE :
			response = processSubscribeEvent((SubscribeEventRequest) requestMessage, wxMerchantDO);
			break;
		case SCAN:
			response = processSubscribeEvent((SubscribeEventRequest) requestMessage, wxMerchantDO);
			break;
		case UNSUBSCRIBE:
			response = processUnscribeEvent((SubscribeEventRequest) requestMessage, wxMerchantDO);
			break;
		case VIEW:
			response = processViewEvent((MenuViewEventRequest) requestMessage, wxMerchantDO);
			break;
		case LOCATION:
			response = processLocationEvent((LocationEventRequest) requestMessage, wxMerchantDO);
			break;
		case CLICK:
			response = processClickEvent((MenuClickEventRequest) requestMessage, wxMerchantDO);
			break;

		}

		return response;
	}

	public BaseRspMsg processSubscribeEvent(SubscribeEventRequest eventMsg, WxMerchantDO wxMerchantDO) {
		// 默认返回的文本消息内容
		//		if (wxMerchantDO != null) {
//			// 发送MQ消息,关注事件
//			//FIXME 处理subscribe的返回结果
//			subscribeService.subscribe((SubscribeEventRequest) eventMsg, wxMerchantDO);
//			response = messageReplyService.getCommonEventReply(eventMsg, wxMerchantDO.getId(),
//					weixinApiUrlHolder.getWeixinCenterDomain());
//
//		}
		return getDefaultResponse();
	}

	public BaseRspMsg processScanEvent(SubscribeEventRequest eventMsg, WxMerchantDO wxMerchantDO) {
		// 默认返回的文本消息内容
		BaseRspMsg response = null;
//		if (wxMerchantDO != null) {
//			subscribeService.subscribe((SubscribeEventRequest) eventMsg, wxMerchantDO);
//			response = messageReplyService.getCommonEventReply(eventMsg, wxMerchantDO.getId(),
//					weixinApiUrlHolder.getWeixinCenterDomain());
//
//		}
		return response;
	}

	public BaseRspMsg processUnscribeEvent(SubscribeEventRequest eventMsg, WxMerchantDO wxMerchantDO) {
		// 默认返回的文本消息内容
		BaseRspMsg response = null;
//		subscribeService.subscribe((SubscribeEventRequest) eventMsg, wxMerchantDO);
//		subscribeService.unsubscribe((SubscribeEventRequest) eventMsg, wxMerchantDO);

		return response;
	}

	public BaseRspMsg processViewEvent(MenuViewEventRequest eventMsg, WxMerchantDO wxMerchantDO) {
		// 暂不处理
		return null;
	}

	public BaseRspMsg processLocationEvent(LocationEventRequest eventMsg, WxMerchantDO wxMerchantDO) {
		// 暂不处理
		return null;
	}

	public BaseRspMsg processClickEvent(MenuClickEventRequest eventMsg, WxMerchantDO wxMerchantDO) {
		BaseRspMsg response = null;
//		response = messageReplyService.getMenuMessageReply(eventMsg, wxMerchantDO.getId(),
//				weixinApiUrlHolder.getWeixinCenterDomain());

		return response;
	}

	public BaseRspMsg processTextMessage(TextMessageRequest eventMsg, WxMerchantDO wxMerchantDO) {
		BaseRspMsg response = null;
		// 进行关键字自动回复操作
		response = messageReplyService.getCommonMessageReply((TextMessageRequest) eventMsg, wxMerchantDO.getId(),
				weixinApiUrlHolder.getWeixinCenterDomain());

		return response;
	}

	public BaseRspMsg processImageMessage(ImageMessageRequest eventMsg, WxMerchantDO wxMerchantDO) {
		// 暂不 处理
		return null;
//		return messageReplyService.getTextReplyMessage(eventMsg, "") ;
	}

	public BaseRspMsg processLocationMessage(LocationMessageRequest eventMsg, WxMerchantDO wxMerchantDO) {
		// 暂不处理
		return null;
	}

	public BaseRspMsg processVoiceMessage(VoiceMessageRequest eventMsg, WxMerchantDO wxMerchantDO) {
		// 暂不处理
		return null;
	}

	public BaseRspMsg processOtherRequest(BaseReqMsg eventMsg, WxMerchantDO wxMerchantDO) {
		// 暂不处理
		return null;
	}

	public BaseRspMsg processExceptionRequest(BaseReqMsg eventMsg, WxMerchantDO wxMerchantDO) {
		// 暂不处理
		return null;
	}

	private TextMessageResponse getDefaultResponse() {
		TextMessageResponse textMessageResponse = new TextMessageResponse();
		textMessageResponse.setContent(AnswerUtils.getDefaultMessage());
		return textMessageResponse;
	}

}
