package com.telan.weixincenter.model;

public enum BackType {
	SUBSCRIBE_REPLY(0, "被关注时回复"), AUTO_REPLY(1, "自动回复");

	private String desc;
	private int id;

	private BackType(int id, String desc) {
		this.desc = desc;
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
