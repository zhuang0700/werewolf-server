package com.telan.weixincenter.domain.resp;

import java.io.Serializable;

/**
 * @author : zhangchao
 * @date : 2015年7月16日 上午10:14:18
 * @Description: 音乐model
 */
public class MusicDO implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	// 音乐名称  
    private String Title;  
    // 音乐描述  
    private String Description;  
    // 音乐链接  
    private String MusicUrl;   
    // 高质量音乐链接，WIFI环境优先使用该链接播放音乐  
    private String HQMusicUrl;
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getMusicUrl() {
		return MusicUrl;
	}
	public void setMusicUrl(String musicUrl) {   
		MusicUrl = musicUrl;
	}
	public String getHQMusicUrl() {
		return HQMusicUrl;
	}
	public void setHQMusicUrl(String musicUrl) {
		HQMusicUrl = musicUrl;
	} 

}
