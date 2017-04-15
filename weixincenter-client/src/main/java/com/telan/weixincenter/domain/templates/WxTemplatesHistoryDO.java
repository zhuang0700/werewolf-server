package com.telan.weixincenter.domain.templates;

import java.io.Serializable;
import java.util.Date;

public class WxTemplatesHistoryDO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long massHistoryId;

	private Long wxMerchantId;

	private String templatename;

	private Integer sendtotals;

	private Integer arrivedtotals;

	private String templateid;

	private String failemsg;

	private Date gmtCreated;

	private Date gmtModified;

	public Long getMassHistoryId() {
		return massHistoryId;
	}

	public void setMassHistoryId(Long massHistoryId) {
		this.massHistoryId = massHistoryId;
	}

	public Long getWxMerchantId() {
		return wxMerchantId;
	}

	public void setWxMerchantId(Long wxMerchantId) {
		this.wxMerchantId = wxMerchantId;
	}

	public String getTemplatename() {
		return templatename;
	}

	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}

	public Integer getSendtotals() {
		return sendtotals;
	}

	public void setSendtotals(Integer sendtotals) {
		this.sendtotals = sendtotals;
	}

	public Integer getArrivedtotals() {
		return arrivedtotals;
	}

	public void setArrivedtotals(Integer arrivedtotals) {
		this.arrivedtotals = arrivedtotals;
	}

	public String getTemplateid() {
		return templateid;
	}

	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}

	public String getFailemsg() {
		return failemsg;
	}

	public void setFailemsg(String failemsg) {
		this.failemsg = failemsg;
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