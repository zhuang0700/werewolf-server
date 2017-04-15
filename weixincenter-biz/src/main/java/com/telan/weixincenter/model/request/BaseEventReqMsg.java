package com.telan.weixincenter.model.request;

import com.telan.weixincenter.model.Event;

import java.io.Serializable;

public class BaseEventReqMsg extends BaseReqMsg implements Serializable{
	private static final long serialVersionUID = 1L;
	private Event event;
	
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
}
