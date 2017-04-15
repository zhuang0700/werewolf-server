package com.telan.weixincenter.enums;

public enum WeiXinStatus {
//	110 待审核 120审核通过 130审核不通过 140订单已创建
	WAITING_AUDIT(110,"待审核"),
	APPROVE(120,"审核通过"),
	UNAPPROVE(130,"审核未通过"),
	CREATE_ORDER_FINISH(5,"已创建订单"),
	;
	
	private int status ;
	private String desc ;
	private WeiXinStatus(int status, String desc) {
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
