package com.telan.weixincenter.plugins;

import java.io.Serializable;

/**
 * @author :张超
 * @date :2015年7月16日 上午9:59:03
 * @Description: 结果对 
 */ 
public class ResultPair implements Serializable{  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 原文     
    private String src;  
    // 译文  
    private String dst;  
  
    public String getSrc() {  
        return src;  
    }  
  
    public void setSrc(String src) {  
        this.src = src;  
    }  
  
    public String getDst() {  
        return dst;  
    }  
  
    public void setDst(String dst) {  
        this.dst = dst;  
    }  
}  
