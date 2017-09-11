package com.telan.werewolf.enums;

import java.io.Serializable;

/**
 * code 范围：[17000000, 18000000)
 * @author weiwenliang
 *
 */
public class WeErrorCode implements Serializable{
	private static final long serialVersionUID = 524294081722965665L;
	public static final WeErrorCode SYSTEM_ERROR = new WeErrorCode(17000000,"系统错误");


	public static final WeErrorCode DB_ERROR = new WeErrorCode(17000001,"数据库错误");
	public static final WeErrorCode READ_DB_ERROR = new WeErrorCode(17000002,"读数据库错误");
	public static final WeErrorCode WRITE_DB_ERROR = new WeErrorCode(17000003,"写数据库错误");
	public static final WeErrorCode PARAM_ERROR=new WeErrorCode(17000004,"参数异常");
	public static final WeErrorCode TAIR_ERROR = new WeErrorCode(17000005,"tair操作异常");

	public static final WeErrorCode WEIXIN_LOGIN_ERROR = new WeErrorCode(8000016,"微信账号未登录");

	public static final WeErrorCode UNSUPPORT_ACTION = new WeErrorCode(18000001,"不支持的操作");
	public static final WeErrorCode DUPLICATE_ACTION = new WeErrorCode(18000002,"已执行过该行动");
	public static final WeErrorCode DEAD_ACTION = new WeErrorCode(18000003,"死亡玩家无法行动");
	public static final WeErrorCode WRONG_STAGE_ACTION = new WeErrorCode(18000004,"回合未开始或已经结束");
	public static final WeErrorCode WRONG_GAME = new WeErrorCode(18000005,"游戏已结束");
	public static final WeErrorCode NO_ACTIVE_GAME = new WeErrorCode(18000006,"没有正在进行中的游戏");
	public static final WeErrorCode HAS_ACTIVE_GAME = new WeErrorCode(18000007,"其他游戏进行中");
	public static final WeErrorCode MAX_PLAYER_ACHIVED = new WeErrorCode(18000008,"游戏人数已满");
	public static final WeErrorCode GAME_INIT = new WeErrorCode(18000009,"游戏已开局");
	public static final WeErrorCode WRONG_ACTION_TARGET = new WeErrorCode(18000010,"错误的操作对象");

	private int errorCode;
	
	private String errorMsg;
	
	public WeErrorCode(int errorCode, String errorMsg){
		this.errorCode = errorCode;
		this.errorMsg  = errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}


}
