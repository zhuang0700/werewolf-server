package com.telan.weixincenter.model.request;
public class MenuClickEventRequest extends BaseEventReqMsg {
	private String eventKey;
	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

}
