package com.telan.weixincenter.domain.resp;

public class MusicMessageDO extends BaseMessageDO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	// 音乐  
    private MusicDO Music;

	public MusicDO getMusic() {
		return Music;
	}

	public void setMusic(MusicDO music) {
		Music = music;
	} 

}
   