package com.telan.weixincenter.domain.menu;

import java.io.Serializable;

/**
 * @author : zhangchao
 * @date : 2015年7月16日 上午9:58:17
 * @Description: 微信通用接口凭证
 * 微信服务器会返回json格式的数据：{"access_token":"ACCESS_TOKEN","expires_in":7200}，我们将其封装为一个AccessToken对象，对象有二个属性：token和expiresIn
 */
public class AccessTokenDO implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 获取到的凭证   
    private String token;  
    // 凭证有效时间，单位：秒      
    private int expiresIn;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

}
