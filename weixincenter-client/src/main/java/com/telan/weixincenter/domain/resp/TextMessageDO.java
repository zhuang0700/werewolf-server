package com.telan.weixincenter.domain.resp;
/**
 * @author :张超
 * @date :Dec 3, 2013 2:06:38 PM
 * @Description: 文本消息 
 */
public class TextMessageDO extends BaseMessageDO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	// 回复的消息内容  
    private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}


}    
