package com.telan.werewolf.model.request;
public class MenuClickEventRequest extends BaseEventReqMsg {
	private String eventKey;
	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

}
