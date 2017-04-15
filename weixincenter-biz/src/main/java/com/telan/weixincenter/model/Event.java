package com.telan.weixincenter.model;

public enum Event {
	SUBSCRIBE(1,"订阅事件", "subscribe"), UNSUBSCRIBE(2,"取消订阅事件", "unsubscribe"), SCAN(3,"扫码事件", "SCAN"), CLICK(4,"菜单点击事件",
			"CLICK"), VIEW(5,"view跳转", "VIEW"), LOCATION(6,"上报地理位置事件", "LOCATION");
	private int id;
	private String desc;
	private String code;

	private Event(int id,String desc, String code) {
		this.id = id;
		this.desc = desc;
		this.code = code;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public static Event valueOfCode(String code){
		if( code == null || "".equals(code.trim()) ){
			return null;
		}
		for( Event event : Event.values() ){
			if( event.code.equals(code) ){
				return event ;
			}
		}
		return null;
	}
}
