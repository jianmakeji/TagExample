package com.jianma.tagsystem.service;

import java.util.List;
import java.util.Map;

import com.jianma.tagsystem.module.Article;

public interface ArticleService {
	
	public List<Article> getArticlePageByTag(Map<Integer,List<Integer>> tagMap);
	
}
