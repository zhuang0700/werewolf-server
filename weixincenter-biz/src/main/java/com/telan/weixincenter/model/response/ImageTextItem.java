package com.telan.weixincenter.model.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.telan.weixincenter.event.parser.XStreamCDATA;

public class ImageTextItem {
	@XStreamCDATA
	@XStreamAlias("Title") 
	private String title;
	@XStreamCDATA
	@XStreamAlias("Description") 
	private String description;
	@XStreamCDATA
	@XStreamAlias("PicUrl") 
	private String picUrl;
	@XStreamCDATA
	@XStreamAlias("Url") 
	private String url;

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

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
