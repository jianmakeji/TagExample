package com.jianma.tagsystem.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jianma.tagsystem.dao.ArticleDao;
import com.jianma.tagsystem.dao.TagCategoryDao;
import com.jianma.tagsystem.module.Article;
import com.jianma.tagsystem.service.ArticleService;

@Service
@Component
@Qualifier(value = "articleServiceImpl")
@Transactional
//@Scope(value = "prototype")
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	@Qualifier(value = "articleDaoImpl")
	private ArticleDao articleDaoImpl;
	
	@Override
	public int createArticle(Article article) {

		return articleDaoImpl.createArticle(article);
	}

	@Override
	public void updateArticle(Article article) {
		articleDaoImpl.updateArticle(article);
	}

	@Override
	public int deleteArticle(int id) {

		return articleDaoImpl.deleteArticle(id);
	}

	@Override
	public List<Article> getArticleByPage(int offset, int limit) {

		return articleDaoImpl.getArticleByPage(offset, limit);
	}

	@Override
	public Article getArticleDetailById(int id) {

		return articleDaoImpl.getArticleDetailById(id);
	}

	@Override
	public List<Article> getArticlePageByTag(Map<Integer, List<Integer>> tagMap) {

		return null;
	}

}
