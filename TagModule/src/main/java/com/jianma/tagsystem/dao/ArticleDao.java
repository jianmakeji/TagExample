package com.jianma.tagsystem.dao;

import java.util.List;

import com.jianma.tagsystem.module.Article;

public interface ArticleDao {
	
	public int createArticle(Article article);
	
	public void updateArticle(Article article);
	
	public int deleteArticle(int id);
	
	public List<Article> getArticleByPage(int offset, int limit);

	public Article getArticleDetailById(int id);
	
	public List<Article> getArticlePageByIds(List<Integer> ids);
	
}
