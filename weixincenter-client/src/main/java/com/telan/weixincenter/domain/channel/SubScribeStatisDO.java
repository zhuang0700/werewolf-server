package com.telan.weixincenter.domain.channel;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : zhangchao
 * @date : 2015年12月2日 下午7:39:17
 * @Description:渠道粉丝信息统计
 */
public class SubScribeStatisDO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8585809752977489164L;

	private Long id;

	private Date day;

	private Long channelId;

	private Long wxMerchantId;

	private String channelName;

	private Integer subscribeNum;

	private Integer cancelSubscribeNum;

	private Date gmtCreated;

	private Date gmtModified;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Integer getSubscribeNum() {
		return subscribeNum;
	}

	public void setSubscribeNum(Integer subscribeNum) {
		this.subscribeNum = subscribeNum;
	}

	public Integer getCancelSubscribeNum() {
		return cancelSubscribeNum;
	}

	public void setCancelSubscribeNum(Integer cancelSubscribeNum) {
		this.cancelSubscribeNum = cancelSubscribeNum;
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

	public Long getWxMerchantId() {
		return wxMerchantId;
	}

	public void setWxMerchantId(Long wxMerchantId) {
		this.wxMerchantId = wxMerchantId;
	}

}