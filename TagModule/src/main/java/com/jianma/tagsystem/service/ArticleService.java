package com.jianma.tagsystem.service;

import java.util.List;
import java.util.Map;

import com.jianma.tagsystem.module.Article;

public interface ArticleService {
	
	public int createArticle(Article article);
	
	public void updateArticle(Article article);
	
	public int deleteArticle(int id);
	
	public List<Article> getArticleByPage(int offset, int limit);

	public Article getArticleDetailById(int id);
	
	public List<Article> getArticlePageByTag(Map<Integer,List<Integer>> tagMap);
	
}
