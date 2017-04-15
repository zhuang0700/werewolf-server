package com.telan.weixincenter.domain.fans;

import java.io.Serializable;
import java.util.Date;

public class WxFansGroupDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4797095007586360467L;

	private Long id;

	private Long wxMerchantId;

	private String groupName;

	private Integer fansCount;

	private Date gmtCreated;

	private Date gtmModified;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getFansCount() {
		return fansCount;
	}

	public void setFansCount(Integer fansCount) {
		this.fansCount = fansCount;
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

	public Date getGmtCreated() {
		return gmtCreated;
	}

	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

	public Date getGtmModified() {
		return gtmModified;
	}

	public void setGtmModified(Date gtmModified) {
		this.gtmModified = gtmModified;
	}

}