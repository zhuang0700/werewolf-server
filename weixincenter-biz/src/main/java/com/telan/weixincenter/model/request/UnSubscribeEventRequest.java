package com.telan.weixincenter.model.request;

import com.telan.weixincenter.model.Event;

public class UnSubscribeEventRequest extends BaseReqMsg {
	private Event event;

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

}
