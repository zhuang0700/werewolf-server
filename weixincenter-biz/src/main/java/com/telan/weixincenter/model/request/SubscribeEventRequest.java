package com.telan.weixincenter.model.request;

import com.telan.weixincenter.core.util.WxApiUtil;
import com.telan.weixincenter.model.SubscribeFromType;

import java.io.Serializable;

public class SubscribeEventRequest extends BaseEventReqMsg implements Serializable{
	private static final long serialVersionUID = 1L;
	//扫描二维码，如果之前未关注过，则有此值
	private String eventKey;
	//扫描二维码，如果之前未关注过，则有此值
	private String ticket;

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public int getChannelId(){
		return WxApiUtil.getQrsceneChannelId(eventKey);
	}
	
	public SubscribeFromType getSubscribeFromType(){
		int channelId = getChannelId();
		SubscribeFromType type = SubscribeFromType.getSubscribeFromType(channelId);
		if( type == null ){
			type = SubscribeFromType.COMMON ;
		}
		return type ;
	}
}
