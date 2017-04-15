package com.telan.weixincenter.domain.result;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class FanListDTO implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 3476211197166006022L;

	//	{
//		   "total":23000,
//		   "count":10000,
//		   "data":{
//		     "openid":[
//		       "OPENID10001",
//		       "OPENID10002",
//		       ...,
//		       "OPENID20000"
//		     ]
//		   },
//		   "next_openid":"OPENID20000"
//		}
//	
	private int total ;
	
	private int count;
	
	private Map<String,List<String>> data;
	
	private String nexOpenId;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Map<String, List<String>> getData() {
		return data;
	}

	public void setData(Map<String, List<String>> data) {
		this.data = data;
	}

	public String getNexOpenId() {
		return nexOpenId;
	}

	public void setNexOpenId(String nexOpenId) {
		this.nexOpenId = nexOpenId;
	}
	
}
