package com.telan.weixincenter.model;

public enum MaterialType {
	SINGLE_IMAGE_TEXT(1, "单图文"), MULTI_IMAGE_TEXT(2, "多图文"), VIDEO(3, "视频"), AUDIO(4, "音频"), LOCATION(5, "地理位置"), TEXT(6,
			"文本"), IMAGE(7, "图片"),UNDEFINED(-9999,"未定义");

	private String desc;
	private int id;

	private MaterialType(int id, String desc) {
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
	
	public static MaterialType valueOfId(int id){
		for( MaterialType type : MaterialType.values() ){
			if( type.id == id ){
				return type ;
			}
		}
		return UNDEFINED;
	}
}
