package com.telan.weixincenter.domain.channel;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : zhangchao
 * @date : 2015年12月2日 下午7:38:37
 * @Description: 渠道粉丝信息流水
 */
public class SubscribeRecordDO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8656202164064364793L;

	private long id;

	private long channelId;

	private String openId;

	private boolean subscribe;

	private int fromType;

	private Date day;

	private long wxMerchantId;

	private Date gmtModified;

	private Date gmtCreated;

	private long userId;
	
	private Date recordTime;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getChannelId() {
		return channelId;
	}

	public void setChannelId(long channelId) {
		this.channelId = channelId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public int getFromType() {
		return fromType;
	}

	public void setFromType(int fromType) {
		this.fromType = fromType;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public long getWxMerchantId() {
		return wxMerchantId;
	}

	public void setWxMerchantId(long wxMerchantId) {
		this.wxMerchantId = wxMerchantId;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Date getGmtCreated() {
		return gmtCreated;
	}

	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}
	
	public boolean isSubscribe() {
		return subscribe;
	}

	public void setSubscribe(boolean subscribe) {
		this.subscribe = subscribe;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
}