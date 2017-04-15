package com.telan.weixincenter.domain.resp;

/**
 * @author : zhangchao
 * @date : 2015年7月16日 上午10:10:42
 * @Description: 图片消息
 */
public class ImageMessageDO extends BaseMessageDO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	// 图片链接  
    private String PicUrl;
    // 图片消息媒体id
    private long MediaId;
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public long getMediaId() {
		return MediaId;
	}
	public void setMediaId(long mediaId) {
		MediaId = mediaId;
	}

    
    
	 

	 

    
	
}
