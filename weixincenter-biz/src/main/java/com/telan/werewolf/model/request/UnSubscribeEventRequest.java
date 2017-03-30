package com.telan.werewolf.model.request;

import com.telan.werewolf.model.Event;

public class UnSubscribeEventRequest extends BaseReqMsg {
	private Event event;

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

}
