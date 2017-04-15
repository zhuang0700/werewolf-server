package com.telan.weixincenter.domain.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : zhangchao
 * @date : 2015年7月15日 下午3:37:40
 * @Description: 微信公众号PO
 */
public class WxMerchantDO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8552368739385546952L;

	/**
	 * 
	 */

	private Long id;

	private String infoName;

	private String infoLogo;

	private String weixinId;

	private Integer accountType;

	private Integer certification;

	private String appId;

	private String appSecret;

	private Integer appState;

	private Date gmtCreated;

	private Date gmtModified;

	private Long employeeId;

	private String openId;

	private Integer state;

	private Integer pcenabledkef;

	private long version;
	
	private long merchantId;
	
	private long domainId;
	
	private long domainAppId;

	private long userId;

	public String getInfoName() {
		return infoName;
	}

	public void setInfoName(String infoName) {
		this.infoName = infoName == null ? null : infoName.trim();
	}

	public String getInfoLogo() {
		return infoLogo;
	}

	public void setInfoLogo(String infoLogo) {
		this.infoLogo = infoLogo == null ? null : infoLogo.trim();
	}

	public String getWeixinId() {
		return weixinId;
	}

	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId == null ? null : weixinId.trim();
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Integer getCertification() {
		return certification;
	}

	public void setCertification(Integer certification) {
		this.certification = certification;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId == null ? null : appId.trim();
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret == null ? null : appSecret.trim();
	}

	public Integer getAppState() {
		return appState;
	}

	public void setAppState(Integer appState) {
		this.appState = appState;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId == null ? null : openId.trim();
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getPcenabledkef() {
		return pcenabledkef;
	}

	public void setPcenabledkef(Integer pcenabledkef) {
		this.pcenabledkef = pcenabledkef;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	public long getDomainId() {
		return domainId;
	}

	public void setDomainId(long domainId) {
		this.domainId = domainId;
	}

	public long getDomainAppId() {
		return domainAppId;
	}

	public void setDomainAppId(long domainAppId) {
		this.domainAppId = domainAppId;
	}

	public long getUserId(){
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}


}