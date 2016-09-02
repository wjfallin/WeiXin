package com.weixin.po;

import java.util.List;

public class NewsMessage extends BaseMessage{
	private int articlecount;
	private List<News> articles;
	public int getArticlecount() {
		return articlecount;
	}
	public void setArticlecount(int articlecount) {
		this.articlecount = articlecount;
	}
	public List<News> getArticles() {
		return articles;
	}
	public void setArticles(List<News> articles) {
		this.articles = articles;
	}
}
