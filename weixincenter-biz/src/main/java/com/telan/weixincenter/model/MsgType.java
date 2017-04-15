package com.telan.weixincenter.model;

public enum MsgType {
	EVENT(1, "事件消息", "event"), TEXT(2, "文本消息", "text"), IMAGE(3, "图片消息", "image"), LOCATION(4, "地理位置消息",
			"location"), LINK(5, "链接消息", "link"), VOICE(6, "音频消息", "voice"),NEWS(7, "news", ""),VIDEO(8,"video","视频"),UNDEFINED(-9999,"undefined","未定义");
	
//	private static  Map<String,MsgType> DATAS = new HashMap<String,MsgType>();
//	
//	static {
//		DATAS.put("event", EVENT);
//		DATAS.put("text", TEXT);
//		DATAS.put("image", IMAGE);
//		DATAS.put("location", LOCATION);
//		DATAS.put("link", LINK);
//		DATAS.put("voice", VOICE);
//		DATAS.put("news", NEWS);
//	}
//	
	private String desc;
	private String code;
	private int id;

	private MsgType(int id, String desc, String code) {
		this.id = id;
		this.desc = desc;
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	/**
//	 * 使用getByCode方法
//	 * @param code
//	 * @return
//	 */
//	@Deprecated
//	public static MsgType  valueOfCode(String code){
//		return DATAS.get(code);
//	}
	public static MsgType valueOfCode(String code){
		if( code == null || "".equals(code.trim()) ){
			return null;
		}
		for( MsgType type : MsgType.values() ){
			if( type.code.equals(code) ){
				return type ;
			}
		}
		
		return UNDEFINED;
	}
	
	public String toString(){
		return code;
	}
}
