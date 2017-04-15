package com.telan.weixincenter.domain.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : zhangchao
 * @date : 2015年7月29日 上午10:23:21
 * @Description: 关键词
 */
public class WxServiceKeywordDO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String keywordName;

	private String keywordContent;

	private Long ruleId;

	private Date gmtCreated;

	private Date gmtModified;

	private String reserve;

	public String getKeywordName() {
		return keywordName;
	}

	public void setKeywordName(String keywordName) {
		this.keywordName = keywordName == null ? null : keywordName.trim();
	}

	public String getKeywordContent() {
		return keywordContent;
	}

	public void setKeywordContent(String keywordContent) {
		this.keywordContent = keywordContent == null ? null : keywordContent.trim();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
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

	public String getReserve() {
		return reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve == null ? null : reserve.trim();
	}
}