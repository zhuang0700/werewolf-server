package com.telan.weixincenter.model.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.telan.weixincenter.event.parser.XStreamCDATA;
import com.telan.weixincenter.model.MsgType;

public class BaseRspMsg {
	// 开发者微信号
	@XStreamCDATA
	@XStreamAlias("ToUserName") 
	private String toUserName;
	// 发送方帐号（一个OpenID）
	@XStreamCDATA
	@XStreamAlias("FromUserName") 
	private String fromUserName;
	// 消息创建时间
	@XStreamAlias("CreateTime") 
	private Long createTime;
	// 消息类型
	@XStreamCDATA
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

	public MsgType getMsgType() {
		return msgType;
	}

	public void setMsgType(MsgType msgType) {
		this.msgType = msgType;
	}

}
