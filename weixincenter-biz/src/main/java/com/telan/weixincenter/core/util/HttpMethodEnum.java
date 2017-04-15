package com.telan.weixincenter.core.util;

public enum HttpMethodEnum {
	POST("POST", "post请求"), GET("GET", "get请求");

	private String code;

	private String desc;

	private HttpMethodEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
