package com.telan.werewolf.enums;

public enum SocketMsgType {
	PING(0, "连接测试", "ping"),
	START_GAME(1, "开始游戏", "start_game"),
	PLAYER_ACTION(2, "玩家行动", "player_action"),
	QUIT_GAME(3, "退出游戏", "quit_game"),
	GAME_INFO(4, "获取游戏信息", "game_info"),
	UNKNOWN(9999, "未知类型", "unknown")
	;



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

	private SocketMsgType(int id, String desc, String code) {
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
	public static SocketMsgType valueOfCode(String code){
		if( code == null || "".equals(code.trim()) ){
			return null;
		}
		for( SocketMsgType type : SocketMsgType.values() ){
			if( type.code.equals(code) ){
				return type ;
			}
		}
		
		return UNKNOWN;
	}
	
	public String toString(){
		return code;
	}
}
