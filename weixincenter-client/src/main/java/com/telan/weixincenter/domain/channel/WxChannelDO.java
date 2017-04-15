package com.telan.weixincenter.domain.channel;

import java.util.Date;

public class WxChannelDO implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long wxMerchantId;

	private String channelName;

	private Integer channelType;

	private String channelP;

	private String channelC;

	private String channelA;

	private String channelAddress;

	private Integer channelClassId;

	private String channelCodeImg;

	private String channelLogo;

	private String channelUrl;

	private String channelbeizhu;

	private Integer totalNums;

	private Integer totalCancelNum;

	private Integer deleteType;

	private Date gmtCreated;

	private Date gmtModified;

	private String channelRemark;

	private String keyword;
	private WxChannelClassDO wxChannelClass;

	public WxChannelClassDO getWxChannelClass() {
		return wxChannelClass;
	}

	public void setWxChannelClass(WxChannelClassDO wxChannelClass) {
		this.wxChannelClass = wxChannelClass;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName == null ? null : channelName.trim();
	}

	public Integer getChannelType() {
		return channelType;
	}

	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}

	public String getChannelP() {
		return channelP;
	}

	public void setChannelP(String channelP) {
		this.channelP = channelP == null ? null : channelP.trim();
	}

	public String getChannelC() {
		return channelC;
	}

	public void setChannelC(String channelC) {
		this.channelC = channelC == null ? null : channelC.trim();
	}

	public String getChannelA() {
		return channelA;
	}

	public void setChannelA(String channelA) {
		this.channelA = channelA == null ? null : channelA.trim();
	}

	public String getChannelAddress() {
		return channelAddress;
	}

	public void setChannelAddress(String channelAddress) {
		this.channelAddress = channelAddress == null ? null : channelAddress.trim();
	}

	public Integer getChannelClassId() {
		return channelClassId;
	}

	public void setChannelClassId(Integer channelClassId) {
		this.channelClassId = channelClassId;
	}

	public String getChannelCodeImg() {
		return channelCodeImg;
	}

	public void setChannelCodeImg(String channelCodeImg) {
		this.channelCodeImg = channelCodeImg == null ? null : channelCodeImg.trim();
	}

	public String getChannelLogo() {
		return channelLogo;
	}

	public void setChannelLogo(String channelLogo) {
		this.channelLogo = channelLogo == null ? null : channelLogo.trim();
	}

	public String getChannelUrl() {
		return channelUrl;
	}

	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl == null ? null : channelUrl.trim();
	}

	public String getChannelbeizhu() {
		return channelbeizhu;
	}

	public void setChannelbeizhu(String channelbeizhu) {
		this.channelbeizhu = channelbeizhu == null ? null : channelbeizhu.trim();
	}

	public Integer getTotalNums() {
		return totalNums;
	}

	public void setTotalNums(Integer totalNums) {
		this.totalNums = totalNums;
	}

	public String getChannelRemark() {
		return channelRemark;
	}

	public void setChannelRemark(String channelRemark) {
		this.channelRemark = channelRemark == null ? null : channelRemark.trim();
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getTotalCancelNum() {
		return totalCancelNum;
	}

	public void setTotalCancelNum(Integer totalCancelNum) {
		this.totalCancelNum = totalCancelNum;
	}

	public Integer getDeleteType() {
		return deleteType;
	}

	public void setDeleteType(Integer deleteType) {
		this.deleteType = deleteType;
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

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

}