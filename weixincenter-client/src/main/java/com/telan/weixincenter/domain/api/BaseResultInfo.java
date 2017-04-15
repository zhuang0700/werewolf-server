package com.telan.weixincenter.domain.api;

import java.io.Serializable;

public class BaseResultInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2090503828112313987L;
	private int errcode;
	private String errmsg;
	public int getErrcode() {
		return errcode;
	}
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
	
}
