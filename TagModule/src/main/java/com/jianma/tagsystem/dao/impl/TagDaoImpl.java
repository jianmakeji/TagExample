package com.jianma.tagsystem.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.jianma.tagsystem.dao.TagDao;
import com.jianma.tagsystem.module.Tag;

@Repository
@Component
@Qualifier(value = "tagDaoImpl")
public class TagDaoImpl implements TagDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public int createTag(Tag tag) {
		return (int)sessionFactory.getCurrentSession().save(tag);
	}

	@Override
	public void updateTag(Tag tag) {
		
		sessionFactory.getCurrentSession().update(tag);
	}

	@Override
	public int deleteTagById(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " delete from Tag t  where t.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		query.executeUpdate();
		return id;
	}

	@Override
	public List<Tag> getTagByPage(int offset, int limit) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Tag t order by t.createTime desc";
		
		Query query = session.createQuery(hql);
		
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.list();
	}

}
