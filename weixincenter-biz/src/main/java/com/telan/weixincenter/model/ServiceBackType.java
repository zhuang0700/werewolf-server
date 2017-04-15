package com.telan.weixincenter.model;

public enum ServiceBackType {
	TEXT(0,"文本"), SINGLE_IMAGE_TEXT(1, "单图文"), MULTI_IMAGE_TEXT(2, "多图文"), LINK(3, "链接"),UNDEFINED(-9999,"未定义");

	private String desc;
	private int id;

	private ServiceBackType(int id, String desc) {
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
	
	public static ServiceBackType valueOfId(int id){
		for( ServiceBackType type : ServiceBackType.values() ){
			if( type.id == id ){
				return type ;
			}
		}
		return UNDEFINED;
	}
}
