package com.telan.weixincenter.result;

/**
 * @author : zhangchao
 * @date : 2015年11月20日 下午5:40:09
 * @Description: 声明错误码范围  8000000 --- 9000000
 */
public class WXReturnCode {
	private int code;
	private String desc ;
	public WXReturnCode(int code, String desc) {
		super();
		this.code = code;
		this.desc = desc;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**************************************基础********************************************/
	public static final int SYSTEM_ERROR_C = 8000000;
	public static final WXReturnCode SYSTEM_ERROR = new WXReturnCode(SYSTEM_ERROR_C, "系统错误") ;
	
	public static final int DB_WRITE_FAILED_C = 8000001;
	public static final WXReturnCode DB_WRITE_FAILED = new WXReturnCode(DB_WRITE_FAILED_C, "写数据库失败") ;
	
	public static final int DB_READ_FAILED_C = 8000002;
	public static final WXReturnCode DB_READ_FAILED = new WXReturnCode(DB_READ_FAILED_C, "读数据库失败") ;
	
	public static final int PARAMTER_ERROR_C = 8000003;
	public static final WXReturnCode PARAMTER_ERROR = new WXReturnCode(PARAMTER_ERROR_C, "参数错误") ;
	
	
	public static final int SEND_MQ_FAILED_C = 8000004;
	public static final WXReturnCode SEND_MQ_FAILED = new WXReturnCode(SEND_MQ_FAILED_C, "发送mq消息失败") ;
	
	public static final int JSON_PARSE_ERROR_C = 8000005;
	public static final WXReturnCode JSON_PARSE_ERROR = new WXReturnCode(JSON_PARSE_ERROR_C, "json解析失败") ;
	
	
	
	/**************************************OAuth2域名授权  粉丝信息********************************************/
	public static final int PARAMTER_OPENID_ERROR_C = 8100000;
	public static final WXReturnCode PARAMTER_OPENID_ERROR = new WXReturnCode(PARAMTER_OPENID_ERROR_C, "获取用户openID异常!") ;
	
	public static final int PARAMTER_SUBSCRIBE_ERROR_C = 8010001;
	public static final WXReturnCode PARAMTER_SUBSCRIBE_ERROR = new WXReturnCode(PARAMTER_SUBSCRIBE_ERROR_C, "获取关注粉丝的相关信息异常!") ;
	
	public static final int TOKEN_ERROR_C = 8010002;
	public static final WXReturnCode TOKEN_ERROR = new WXReturnCode(TOKEN_ERROR_C, "TOKEN错误") ;
	
	public static final int NEED_TOKEN_ERROR_C = 8010003;
	public static final WXReturnCode NEED_TOKEN_ERROR = new WXReturnCode(NEED_TOKEN_ERROR_C, "缺少TOKEN") ;
	
	public static final int OAUTH2_FAILED_C= 8010004;
	public static final WXReturnCode OAUTH2_FAILED = new WXReturnCode(OAUTH2_FAILED_C, "OAuth2操作失败") ;

	public static final int JSAPITICKET_FAILED_C= 8010005;
	public static final WXReturnCode JSAPITICKET_FAILED = new WXReturnCode(JSAPITICKET_FAILED_C, "获取ticket失败");

	public static final int WEIXIN_LOGIN_C = 8010006;
	public static final WXReturnCode WEIXIN_LOGIN_ERROR = new WXReturnCode(WEIXIN_LOGIN_C, "微信登陆失败") ;
	public static final int WEIXIN_GET_WX_USERINFO_C = 8010007;
	public static final WXReturnCode WEIXIN_GET_WX_USERINFO_ERROR = new WXReturnCode(WEIXIN_GET_WX_USERINFO_C, "获取用户信息失败") ;
	public static final int WEIXIN_GET_USER_C = 8010008;
	public static final WXReturnCode WEIXIN_GET_USER_ERROR = new WXReturnCode(WEIXIN_GET_USER_C, "获取用户的信息失败") ;
	public static final int WEIXIN_REGISTER_C = 8010009;
	public static final WXReturnCode WEIXIN_REGISTER_ERROR = new WXReturnCode(WEIXIN_REGISTER_C, "微信注册失败") ;
	public static final int WEIXIN_ADD_USER_C = 8010010;
	public static final WXReturnCode WEIXIN_ADD_USER_ERROR = new WXReturnCode(WEIXIN_ADD_USER_C, "添加用户失败") ;
	public static final int USER_NICKNAME_EXISTED_C = 8010011;
	public static final WXReturnCode USER_NICKNAME_EXISTED = new WXReturnCode(USER_NICKNAME_EXISTED_C, "添加用户失败") ;
	public static final int USER_EXISTED_C = 8010012;
	public static final WXReturnCode USER_EXISTED = new WXReturnCode(USER_EXISTED_C, "用户已经存在");


	/****************************************************************************************************/
	public static final int WEIXIN_INVOKE_ERROR_C = 8020000;
	public static final WXReturnCode WEIXIN_INVOKE_ERROR = new WXReturnCode(WEIXIN_INVOKE_ERROR_C, "微信返回错误") ;
	
	
	public static final int DOWNLOAD_MEDIA_ERROR_C = 8020001;
	public static final WXReturnCode DOWNLOAD_MEDIA_ERROR = new WXReturnCode(DOWNLOAD_MEDIA_ERROR_C, "下载媒体文件失败") ;
	
	
	public static final int INSERT_SUBSCRIBE_ING_C = 8020002;
	public static final WXReturnCode INSERT_SUBSCRIBE_ING = new WXReturnCode(INSERT_SUBSCRIBE_ING_C, "正在向数据库中写入关注记录") ;
	
	
	public static final int GET_TOKEN_ERROR_C = 8100000;
	public static final WXReturnCode GET_TOKEN_ERROR = new WXReturnCode(GET_TOKEN_ERROR_C, "获取TOKEN失败") ;
	
	
	
    public final static int        C_USERNAME_OR_PASSWORD_ERROR = 8200000;
    public final static WXReturnCode USERNAME_OR_PASSWORD_ERROR   = new WXReturnCode(C_USERNAME_OR_PASSWORD_ERROR,"用户名或密码错误");
    
    public final static int        C_LOGIN_FAILED_TO_MANY_TIMES = 8200001;
    public final static WXReturnCode LOGIN_FAILED_TO_MANY_TIMES   = new WXReturnCode( C_LOGIN_FAILED_TO_MANY_TIMES,"登陆失败次数过多");
    
    public final static int        C_NICK_LOGIN_NICK_NOT_EXIST = 8200002;
    public final static WXReturnCode NICK_LOGIN_NICK_NOT_EXIST   = new WXReturnCode(C_NICK_LOGIN_NICK_NOT_EXIST,"用户名不正确，请重新输入");
    
    public static final int                      C_SYSTEM_EXCEPTION_CODE = 8200003;
    public static final WXReturnCode SYSTEM_EXCEPTION_CODE = new WXReturnCode(C_SYSTEM_EXCEPTION_CODE,"服务器开小差了，请刷新后重试！");
}
