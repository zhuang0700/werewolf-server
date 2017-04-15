package com.telan.weixincenter.topic;

public enum WeixinTopic {
	SUBSCRIBE_RECORD(WeixinTopic.TOPIC, "SUBSCRIBE_RECORD", "用户关注、取消关注操作，微信回调时发送的消息"), 
	SUBSCRIBE_UNSUBSCRIBE(WeixinTopic.TOPIC, "SUBSCRIBE_UNSUBSCRIBE", "微信用户关注、取消关注的消息"), 
	SUBSCRIBE(WeixinTopic.TOPIC, "SUBSCRIBE", "微信用户关注"), 
	UNSUBSCRIBE(WeixinTopic.TOPIC, "UNSUBSCRIBE", "微信用户取消关注");

	public static final String TOPIC = "weixincenter";
	/**
	 * 主题
	 */
	private String topic;

	/**
	 * 标签
	 */
	private String tags;

	/**
	 * 描述
	 */
	private String desc;

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	private WeixinTopic(String topic, String tags, String desc) {
		this.topic = topic;
		this.tags = tags;
		this.desc = desc;
	}
}
