package com.telan.weixincenter.domain.templates;
// default package

import java.util.Date;

/**
 * @author : zhangchao
 * @date : 2015年9月6日 下午5:25:18
 * @Description: 消息模板
 */
public class WxTemplatesDO implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long templateId;
	private Long wxMerchantId;
	private Integer tipstype;
	private String tipstitle;
	private String tempid;
	private Integer hreftype;
	private String customlink;
	private String paymentTipsCon;
	private Integer status;
	private Date gmtCreated;
	private Date gmtModified;

	// Constructors

	// /** default constructor */
	// public WxTemplatesDO() {
	// }
	//
	// /** minimal constructor */
	// public WxTemplatesDO(Long wxMerchantId, String tempid, Date inserttime) {
	// this.userId = userId;
	// this.tempid = tempid;
	// this.inserttime = inserttime;
	// }
	//
	// /** full constructor */
	// public WxTemplatesDO(Integer userId, Integer tipstype, String tipstitle,
	// String tempid, Integer hreftype, String customlink,
	// String paymentTipsCon, Integer status, Date inserttime) {
	// this.userId = userId;
	// this.tipstype = tipstype;
	// this.tipstitle = tipstitle;
	// this.tempid = tempid;
	// this.hreftype = hreftype;
	// this.customlink = customlink;
	// this.paymentTipsCon = paymentTipsCon;
	// this.status = status;
	// this.inserttime = inserttime;
	// }

	// Property accessors

	public Long getTemplateId() {
		return this.templateId;
	}
	
	public Integer getTipstype() {
		return this.tipstype;
	}

	public void setTipstype(Integer tipstype) {
		this.tipstype = tipstype;
	}

	public String getTipstitle() {
		return this.tipstitle;
	}

	public void setTipstitle(String tipstitle) {
		this.tipstitle = tipstitle;
	}

	public String getTempid() {
		return this.tempid;
	}

	public void setTempid(String tempid) {
		this.tempid = tempid;
	}

	public Integer getHreftype() {
		return this.hreftype;
	}

	public void setHreftype(Integer hreftype) {
		this.hreftype = hreftype;
	}

	public String getCustomlink() {
		return this.customlink;
	}

	public void setCustomlink(String customlink) {
		this.customlink = customlink;
	}

	public String getPaymentTipsCon() {
		return this.paymentTipsCon;
	}

	public void setPaymentTipsCon(String paymentTipsCon) {
		this.paymentTipsCon = paymentTipsCon;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

}