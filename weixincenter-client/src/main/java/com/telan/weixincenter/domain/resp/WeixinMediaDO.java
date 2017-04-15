package com.telan.weixincenter.domain.resp;

public class WeixinMediaDO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 媒体文件id
	private String MediaId;
	
	//媒体类型                     媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
	private String MediaType;
	
	//媒体文件上传时间戳
	private Integer createdAt;
	
	//form-data中媒体文件标识，有filename、filelength、content-type等信息
	private String media;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getMediaType() {
		return MediaType;
	}

	public void setMediaType(String mediaType) {
		MediaType = mediaType;
	}

	public Integer getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Integer createdAt) {
		this.createdAt = createdAt;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}
	
	
}
