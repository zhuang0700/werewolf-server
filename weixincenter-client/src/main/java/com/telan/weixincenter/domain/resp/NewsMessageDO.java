package com.telan.weixincenter.domain.resp;

import java.util.List;

/**
 * @author : zhangchao
 * @date : 2015年7月16日 上午10:14:56
 * @Description: 文本消息 
 */
public class NewsMessageDO extends BaseMessageDO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	// 图文消息个数，限制为10条以内  
    private int ArticleCount;  
    // 多条图文消息信息，默认第一个item为大图  
    private List<ArticleDO> Articles;
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}   
	public List<ArticleDO> getArticles() {
		return Articles;
	}
	public void setArticles(List<ArticleDO> articles) {
		Articles = articles;
	} 

}
