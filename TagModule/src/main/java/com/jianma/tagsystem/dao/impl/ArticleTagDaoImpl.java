package com.jianma.tagsystem.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.jianma.tagsystem.dao.ArticleTagDao;

@Repository
@Component
@Qualifier(value = "articleTagDaoImpl")
public class ArticleTagDaoImpl implements ArticleTagDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public List<Integer> getArticleIdsByTag(int tagId) {
		// TODO Auto-generated method stub
		return null;
	}

}
