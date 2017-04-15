package com.telan.weixincenter.domain.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : zhangchao
 * @date : 2015年7月29日 上午10:21:31
 * @Description: 素材库
 */
public class WxMaterialDO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String materialWriter;

	private String materialTitle;

	private String materialImgUrl;

	private Integer materialImgIsShow;

	private String businessSelectType;

	private String materialDigest;

	private Date gmtCreated;
	private Date gmtModified;

	private Integer materialTypeId;

	private Long materialNextId;

	private Long wxMerchantId;

	private Integer status;

	private String materialAddr;

	private String materialText;

	private String materialSourceUrl;

	private String materialLinkUrl;

	public String getMaterialWriter() {
		return materialWriter;
	}

	public void setMaterialWriter(String materialWriter) {
		this.materialWriter = materialWriter == null ? null : materialWriter.trim();
	}

	public String getMaterialTitle() {
		return materialTitle;
	}

	public String getMaterialSourceUrl() {
		return materialSourceUrl;
	}

	public void setMaterialSourceUrl(String materialSourceUrl) {
		this.materialSourceUrl = materialSourceUrl;
	}

	public void setMaterialTitle(String materialTitle) {
		this.materialTitle = materialTitle == null ? null : materialTitle.trim();
	}

	public String getMaterialImgUrl() {
		return materialImgUrl;
	}

	public void setMaterialImgUrl(String materialImgUrl) {
		this.materialImgUrl = materialImgUrl == null ? null : materialImgUrl.trim();
	}

	public Integer getMaterialImgIsShow() {
		return materialImgIsShow;
	}

	public void setMaterialImgIsShow(Integer materialImgIsShow) {
		this.materialImgIsShow = materialImgIsShow;
	}

	public String getMaterialDigest() {
		return materialDigest;
	}

	public void setMaterialDigest(String materialDigest) {
		this.materialDigest = materialDigest == null ? null : materialDigest.trim();
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

	public Integer getMaterialTypeId() {
		return materialTypeId;
	}

	public void setMaterialTypeId(Integer materialTypeId) {
		this.materialTypeId = materialTypeId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMaterialNextId() {
		return materialNextId;
	}

	public void setMaterialNextId(Long materialNextId) {
		this.materialNextId = materialNextId;
	}

	public Long getWxMerchantId() {
		return wxMerchantId;
	}

	public void setWxMerchantId(Long wxMerchantId) {
		this.wxMerchantId = wxMerchantId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMaterialAddr() {
		return materialAddr;
	}

	public void setMaterialAddr(String materialAddr) {
		this.materialAddr = materialAddr == null ? null : materialAddr.trim();
	}

	public String getMaterialText() {
		return materialText;
	}

	public void setMaterialText(String materialText) {
		this.materialText = materialText == null ? null : materialText.trim();
	}

	public String getMaterialLinkUrl() {
		return materialLinkUrl;
	}

	public void setMaterialLinkUrl(String materialLinkUrl) {
		this.materialLinkUrl = materialLinkUrl == null ? null : materialLinkUrl.trim();
	}

	public String getBusinessSelectType() {
		return businessSelectType;
	}

	public void setBusinessSelectType(String businessSelectType) {
		this.businessSelectType = businessSelectType;
	}

}