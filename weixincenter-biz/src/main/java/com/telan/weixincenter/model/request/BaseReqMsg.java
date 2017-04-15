package com.telan.weixincenter.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.telan.weixincenter.model.MsgType;

import java.io.Serializable;
import java.util.Date;

public class BaseReqMsg implements Serializable{
	private static final long serialVersionUID = 1L;
	// 开发者微信号
	@XStreamAlias("ToUserName")
	private String toUserName;
	// 发送方帐号（一个OpenID）
	@XStreamAlias("FromUserName")
	private String fromUserName;
	// 消息创建时间
	@XStreamAlias("CreateTime")
	private Long createTime;
	// 消息类型
	@XStreamAlias("MsgType")
	private MsgType msgType;
	

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Date getCreateDate(){
		if( createTime == null ){
			return null;
		}else{
			return new Date(createTime * 1000);
		}
	}
	
	public MsgType getMsgType() {
		return msgType;
	}

	public void setMsgType(MsgType msgType) {
		this.msgType = msgType;
	}

}
