package com.telan.weixincenter.model.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.telan.weixincenter.event.parser.XStreamCDATA;

@XStreamAlias("xml")
public class ImageTextMessageResponse extends BaseRspMsg {
	@XStreamCDATA
	@XStreamAlias("ArticleCount")
	private Integer articleCount;
	
	@XStreamAlias("Articles")
	private ImageTextArticles articles;

	public Integer getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}

	public ImageTextArticles getArticles() {
		return articles;
	}

	public void setArticles(ImageTextArticles articles) {
		this.articles = articles;
	}

}
