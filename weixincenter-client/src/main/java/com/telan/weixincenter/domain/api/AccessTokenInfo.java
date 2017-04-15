package com.telan.weixincenter.domain.api;

import java.io.Serializable;

public class AccessTokenInfo extends BaseResultInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3593990590669272617L;
	private String access_token;
	private int expires_in;
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	
	
}
