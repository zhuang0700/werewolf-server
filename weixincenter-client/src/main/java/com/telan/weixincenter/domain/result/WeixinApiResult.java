package com.telan.weixincenter.domain.result;

import java.io.Serializable;

public class WeixinApiResult<T> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7606863421862918877L;
	
	private int errcode;
	private String errmsg;
	private T value;
	private boolean isSucceeded = false;
	
	public WeixinApiResult() {
		super();
	}

	public WeixinApiResult<T> buildSuccess(T value){
		this.value = value;
		this.isSucceeded  = true;
		
		return this;
	}
	
	public WeixinApiResult<T> buildFail(int errcode,String errmsg){
		this.errcode = errcode;
		this.errmsg = errmsg;
		
		return this;
	}

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

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public boolean isSucceeded() {
		return isSucceeded;
	}

	public void setSucceeded(boolean isSucceeded) {
		this.isSucceeded = isSucceeded;
	}

}
