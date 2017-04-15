package com.telan.weixincenter.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class TextMessageRequest extends BaseReqMsg {
	@XStreamAlias("Content")
	private String content;
	@XStreamAlias("MsgId")
	private String msgId;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

}
