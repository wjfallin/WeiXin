package com.weixin.po;

import java.util.List;

public class NewsMessage extends BaseMessage{
	private int ArticleCount;
	private List<News> Articles;
	public int getArticlecount() {
		return ArticleCount;
	}
	public void setArticlecount(int articlecount) {
		this.ArticleCount = articlecount;
	}
	public List<News> getArticles() {
		return Articles;
	}
	public void setArticles(List<News> articles) {
		this.Articles = articles;
	}
}
