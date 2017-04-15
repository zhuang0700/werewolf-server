package com.telan.weixincenter.service;

import com.telan.weixincenter.model.request.BaseReqMsg;
import com.telan.weixincenter.model.request.TextMessageRequest;
import com.telan.weixincenter.model.response.BaseRspMsg;

public interface MessageReplyService {
	/**
	 * 获取事件触发时的回复信息
	 * @param eventMsg
	 * @param userId
	 * @param urlPath
	 * @return
	 */
	public BaseRspMsg getCommonEventReply(BaseReqMsg eventMsg, Long wxMerchantId, String urlPath) ;

	/**
	 * 获取发送消息的回复
	 * @param eventMsg
	 * @param userId
	 * @param urlPath
	 * @return
	 */
	public BaseRspMsg getCommonMessageReply(TextMessageRequest eventMsg, Long wxMerchantId, String urlPath) ;
//
//	/**
//	 * 获取菜单点击事件的回复
//	 * @param eventMsg
//	 * @param userId
//	 * @param urlPath
//	 * @return
//	 */
//	public BaseRspMsg getMenuMessageReply(MenuClickEventRequest eventMsg, Long wxMerchantId, String urlPath) ;
}
