package com.telan.weixincenter.model.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.telan.weixincenter.event.parser.XStreamCDATA;

public class VoiceMessageResponse extends BaseRspMsg {
	
	@XStreamCDATA
	@XStreamAlias("MediaId") 
	private String mediaId;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
	
}
