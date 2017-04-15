package com.telan.weixincenter.event.parser;

public enum ContentFormat {
	JSON(1, "json格式"), XML(2, "xml格式");

	private int id;
	private String desc;

	private ContentFormat(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
