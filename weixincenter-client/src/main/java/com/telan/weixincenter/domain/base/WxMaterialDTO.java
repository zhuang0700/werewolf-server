package com.telan.weixincenter.domain.base;

import java.io.Serializable;
import java.util.List;
/**
 * @author : zhangchao
 * @date : 2015年7月29日 上午10:22:44
 * @Description: 素材公用的VO
 */
public class WxMaterialDTO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	WxMaterialDO wxMaterial;
	
	List<WxMaterialArticleDO> articles ;

	public WxMaterialDO getWxMaterial() {
		return wxMaterial;
	}

	public void setWxMaterial(WxMaterialDO wxMaterial) {
		this.wxMaterial = wxMaterial;
	}

	public List<WxMaterialArticleDO> getArticles() {
		return articles;
	}

	public void setArticles(List<WxMaterialArticleDO> articles) {
		this.articles = articles;
	}
	 
	
	
 
}