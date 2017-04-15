package com.telan.weixincenter.plugins;

import java.io.Serializable;
import java.util.List;

/**
 * @author :张超
 * @date :2015年7月16日 上午9:59:03
 * @Description: 调用百度翻译api查询结果
 */
public class TranslateResult implements Serializable{  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	// 实际采用的源语言
	private String from;
	// 实际采用的目标语言
	private String to;
	// 结果体
	private List<ResultPair> trans_result;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public List<ResultPair> getTrans_result() {
		return trans_result;
	}

	public void setTrans_result(List<ResultPair> trans_result) {
		this.trans_result = trans_result;
	}
}