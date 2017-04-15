package com.telan.weixincenter.domain.fans;

import java.io.Serializable;
import java.util.Date;

public class WxFansToTagDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8912292596301493122L;

	private Long id;

	private Long wxMerchantId;

	private String oppenId;

	private Long tagId;

	private Date gmtCreated;

	private Date gmtModified;

	private WxFansTagDO wxFansTag;

	public WxFansTagDO getWxFansTag() {
		return wxFansTag;
	}

	public void setWxFansTag(WxFansTagDO wxFansTag) {
		this.wxFansTag = wxFansTag;
	}

	public String getOppenId() {
		return oppenId;
	}

	public void setOppenId(String oppenId) {
		this.oppenId = oppenId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getWxMerchantId() {
		return wxMerchantId;
	}

	public void setWxMerchantId(Long wxMerchantId) {
		this.wxMerchantId = wxMerchantId;
	}

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public Date getGmtCreated() {
		return gmtCreated;
	}

	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

}