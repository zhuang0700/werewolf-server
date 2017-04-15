package com.telan.weixincenter.model.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.telan.weixincenter.event.parser.XStreamCDATA;

public class MusicMessageResponse extends BaseRspMsg {
	@XStreamCDATA
	@XStreamAlias("Title")
	private String title;
	@XStreamCDATA
	@XStreamAlias("Description")
	private String description;
	@XStreamCDATA
	@XStreamAlias("MusicUrl")
	private String musicUrl;
	@XStreamCDATA
	@XStreamAlias("HqMusicUrl")
	private String hqMusicUrl;
	@XStreamCDATA
	@XStreamAlias("ThumbMediaId")
	private String thumbMediaId;

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

	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public String getHqMusicUrl() {
		return hqMusicUrl;
	}

	public void setHqMusicUrl(String hqMusicUrl) {
		this.hqMusicUrl = hqMusicUrl;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

}
