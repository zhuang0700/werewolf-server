package com.telan.weixincenter.domain.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : zhangchao
 * @date : 2015年7月29日 上午10:23:46
 * @Description: 规则
 */
public class WxServiceRuleDO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String ruleName;

	private Long materialId;

	private Long wxMerchantId;

	private Date gmtCreated;

	private Date gmtModified;

	private Integer reserve;

	private Integer materialTypeId;

	private String ruleContent;

	public Integer getMaterialTypeId() {
		return materialTypeId;
	}

	public void setMaterialTypeId(Integer materialTypeId) {
		this.materialTypeId = materialTypeId;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName == null ? null : ruleName.trim();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
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

	public Integer getReserve() {
		return reserve;
	}

	public void setReserve(Integer reserve) {
		this.reserve = reserve;
	}

	public String getRuleContent() {
		return ruleContent;
	}

	public void setRuleContent(String ruleContent) {
		this.ruleContent = ruleContent == null ? null : ruleContent.trim();
	}
}