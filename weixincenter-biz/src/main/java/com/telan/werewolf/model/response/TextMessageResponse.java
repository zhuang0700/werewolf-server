package com.telan.werewolf.model.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.telan.werewolf.event.parser.XStreamCDATA;

@XStreamAlias("xml") 
public class TextMessageResponse extends BaseRspMsg {
	@XStreamCDATA
	@XStreamAlias("Content") 
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
