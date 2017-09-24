package com.jianma.tagsystem.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.jianma.tagsystem.dao.ArticleDao;
import com.jianma.tagsystem.module.Article;

@Repository
@Component
@Qualifier(value = "articleDaoImpl")
public class ArticleDaoImpl implements ArticleDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public int createArticle(Article article) {
		return (int)sessionFactory.getCurrentSession().save(article);
	}

	@Override
	public void updateArticle(Article article) {
		sessionFactory.getCurrentSession().update(article);
	}

	@Override
	public int deleteArticle(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " delete from Article a  where a.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		query.executeUpdate();
		return id;
	}

	@Override
	public List<Article> getArticleByPage(int offset, int limit) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Article a order by a.createTime desc";
		Query query = session.createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.list();
	}

	@Override
	public Article getArticleDetailById(int id) {
		return (Article)sessionFactory.getCurrentSession().load(Article.class, id);
	}

	@Override
	public List<Article> getArticlePageByIds(List<Integer> ids) {
		String hql="FROM Article a WHERE a.id IN (:alist)";  
		Query query = sessionFactory.getCurrentSession().createQuery(hql);  
		query.setParameterList("alist", ids); 
		return query.list();
	}

}
