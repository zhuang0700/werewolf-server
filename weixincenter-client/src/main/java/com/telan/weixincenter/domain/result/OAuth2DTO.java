package com.telan.weixincenter.domain.result;

import java.io.Serializable;

/**
 * @author : zhangchao
 * @date : 2015年12月16日 下午3:31:32
 * @Description: 微信OAuth2域名授权获取的微信服务器返回的信息
 */
public class OAuth2DTO implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 778304672098163444L;

	private String accessToken;
	
	private String expiresIn;
	
	private String refreshToken;
	
	private String openid;
	
	private String scope;
	
	private String unionid;
	
	private String errcode;
	
	private String errmsg;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
}
