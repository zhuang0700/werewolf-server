package com.telan.weixincenter.enums;

public enum SubscribeStatus {
	SUBSCRIBE(1, "关注"),
	UN_SUBSCRIBE(0, "未关注")
	;
	private int status ;
	private String desc ;
	private SubscribeStatus(int status, String desc) {
		this.status = status;
		this.desc = desc;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
