package com.telan.weixincenter.domain.resp;

public class UrlLinkMessageDO extends BaseMessageDO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	//标题
	private String Title;
	// 图文消息描述
	private String Description;
	// 点击跳转链接
	private String Url;
	
	private String Event;
	
	private String EventKey;
	Integer MsgId;//消息ID

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public Integer getMsgId() {
		return MsgId;
	}

	public void setMsgId(Integer msgId) {
		MsgId = msgId;
	}
 
	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	 
 

}
