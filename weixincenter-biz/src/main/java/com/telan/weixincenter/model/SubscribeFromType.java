package com.telan.weixincenter.model;

public enum SubscribeFromType {
	SCAN(1,"scan","通过扫描二维码关注"),COMMON(0,"common","其他关注");
	
	
	private int id;
	private String code;
	private String desc;

	private SubscribeFromType(int id, String code, String desc) {
		this.id = id;
		this.code = code;
		this.desc = desc;
	}
	
	public static SubscribeFromType getSubscribeFromType(int type){
		for( SubscribeFromType subscribeFromType : SubscribeFromType.values() ){
			if( subscribeFromType.id == type ){
				return subscribeFromType ;
			}
		}
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
