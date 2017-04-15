package com.telan.weixincenter.domain.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : zhangchao
 * @date : 2015年7月29日 上午10:22:59
 * @Description: 回复
 */
public class WxServiceBackDO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer backType;

	private Long materialId;

	private Long wxMerchantId;

	private Date gmtCreated;

	private Date gmtModified;

	private Integer materialTypeId;

	private String reserve;

	private String backContent;

	public Integer getBackType() {
		return backType;
	}

	public void setBackType(Integer backType) {
		this.backType = backType;
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

	public Integer getMaterialTypeId() {
		return materialTypeId;
	}

	public void setMaterialTypeId(Integer materialTypeId) {
		this.materialTypeId = materialTypeId;
	}

	public String getReserve() {
		return reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve == null ? null : reserve.trim();
	}

	public String getBackContent() {
		return backContent;
	}

	public void setBackContent(String backContent) {
		this.backContent = backContent == null ? null : backContent.trim();
	}
}