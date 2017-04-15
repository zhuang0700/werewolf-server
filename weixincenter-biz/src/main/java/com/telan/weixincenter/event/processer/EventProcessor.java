package com.telan.weixincenter.event.processer;

import com.telan.weixincenter.domain.base.WxMerchantDO;
import com.telan.weixincenter.model.request.*;
import com.telan.weixincenter.model.response.BaseRspMsg;

public interface EventProcessor {
	public BaseRspMsg processRequest(BaseReqMsg requestMessage);

	public BaseRspMsg processEventRequest(BaseEventReqMsg requestMessage, String url_path);

	public BaseRspMsg processSubscribeEvent(SubscribeEventRequest eventMsg, WxMerchantDO wxMerchantDO);

	public BaseRspMsg processScanEvent(SubscribeEventRequest eventMsg, WxMerchantDO wxMerchantDO);

	public BaseRspMsg processUnscribeEvent(SubscribeEventRequest eventMsg, WxMerchantDO wxMerchantDO);

	public BaseRspMsg processViewEvent(MenuViewEventRequest eventMsg, WxMerchantDO wxMerchantDO);

	public BaseRspMsg processLocationEvent(LocationEventRequest eventMsg, WxMerchantDO wxMerchantDO);

	public BaseRspMsg processClickEvent(MenuClickEventRequest eventMsg, WxMerchantDO wxMerchantDO);

	public BaseRspMsg processTextMessage(TextMessageRequest eventMsg, WxMerchantDO wxMerchantDO);

	public BaseRspMsg processImageMessage(ImageMessageRequest eventMsg, WxMerchantDO wxMerchantDO);
	
	public BaseRspMsg processLocationMessage(LocationMessageRequest eventMsg, WxMerchantDO wxMerchantDO);

	
	public BaseRspMsg processVoiceMessage(VoiceMessageRequest eventMsg, WxMerchantDO wxMerchantDO);
	
	public BaseRspMsg processOtherRequest(BaseReqMsg eventMsg, WxMerchantDO wxMerchantDO);
	
	public BaseRspMsg processExceptionRequest(BaseReqMsg eventMsg, WxMerchantDO wxMerchantDO);
	
	

}
