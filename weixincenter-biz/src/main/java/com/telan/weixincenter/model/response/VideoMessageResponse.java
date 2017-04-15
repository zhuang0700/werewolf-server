package com.telan.weixincenter.model.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.telan.weixincenter.event.parser.XStreamCDATA;

public class VideoMessageResponse extends BaseRspMsg {
	@XStreamCDATA
	@XStreamAlias("MediaId") 
	private String mediaId;
	@XStreamCDATA
	@XStreamAlias("Title") 
	private String title;
	@XStreamCDATA
	@XStreamAlias("Description") 
	private String description;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
