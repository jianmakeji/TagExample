package com.jianma.tagsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jianma.tagsystem.dao.TagCategoryDao;
import com.jianma.tagsystem.module.TagCategory;
import com.jianma.tagsystem.service.TagCategoryService;

@Service
@Component
@Qualifier(value = "tagCategoryServiceImpl")
@Transactional(rollbackFor=Exception.class)
public class TagCategoryServiceImpl implements TagCategoryService {

	@Autowired
	@Qualifier(value = "tagCategoryDaoImpl")
	private TagCategoryDao tagCategoryDaoImpl;
	
	@Override
	public int createTagCategory(TagCategory tagCategory) {

		return tagCategoryDaoImpl.createTagCategory(tagCategory);
	}

	@Override
	public int deleteTagCategoryById(int id) {

		return tagCategoryDaoImpl.deleteTagCategoryById(id);
	}

	@Override
	public void updateTagCategory(TagCategory tagCategory) {

		tagCategoryDaoImpl.updateTagCategory(tagCategory);
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<TagCategory> getTagCategoryByPage(int offset, int limit) {
		return tagCategoryDaoImpl.getTagCategoryByPage(offset, limit);
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public TagCategory getTagCategoryById(int categoryId) {
		return tagCategoryDaoImpl.getTagCategoryById(categoryId);
	}

}
