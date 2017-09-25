package com.jianma.tagsystem.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.jianma.tagsystem.dao.TagCategoryDao;
import com.jianma.tagsystem.module.TagCategory;

@Repository
@Component
@Qualifier(value = "tagCategoryDaoImpl")
public class TagCategoryDaoImpl implements TagCategoryDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public int createTagCategory(TagCategory tagCategory) {
		int id = (int)sessionFactory.getCurrentSession().save(tagCategory);
		return id;
	}

	@Override
	public int deleteTagCategoryById(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " delete from TagCategory t  where t.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		query.executeUpdate();
		return id;
	}

	@Override
	public void updateTagCategory(TagCategory tagCategory) {
		sessionFactory.getCurrentSession().update(tagCategory);
	}

	@Override
	public List<TagCategory> getTagCategoryByPage(int offset, int limit) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from TagCategory t order by t.createTime desc";
		
		Query query = session.createQuery(hql);
		
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.list();
	}

	@Override
	public TagCategory getTagCategoryById(int categoryId) {
		return (TagCategory)sessionFactory.getCurrentSession().load(TagCategory.class, categoryId);
	}

	
}
