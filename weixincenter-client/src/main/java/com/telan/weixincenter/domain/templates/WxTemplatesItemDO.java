package com.telan.weixincenter.domain.templates;

import java.io.Serializable;
import java.util.Date;

public class WxTemplatesItemDO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long templateItemId;

	private Long templateId;

	private Long wxMerchantId;

	private String fieldName;

	private String fieldValue;

	private String fieldColor;

	private Date gmtCreated;

	private Date gmtModified;

	public Long getTemplateItemId() {
		return templateItemId;
	}

	public void setTemplateItemId(Long templateItemId) {
		this.templateItemId = templateItemId;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public Long getWxMerchantId() {
		return wxMerchantId;
	}

	public void setWxMerchantId(Long wxMerchantId) {
		this.wxMerchantId = wxMerchantId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getFieldColor() {
		return fieldColor;
	}

	public void setFieldColor(String fieldColor) {
		this.fieldColor = fieldColor;
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