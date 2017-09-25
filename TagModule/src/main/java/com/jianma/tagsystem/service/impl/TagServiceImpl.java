package com.jianma.tagsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jianma.tagsystem.dao.TagDao;
import com.jianma.tagsystem.module.Tag;
import com.jianma.tagsystem.service.TagService;

@Service
@Component
@Qualifier(value = "tagServiceImpl")
@Transactional
public class TagServiceImpl implements TagService {

	@Autowired
	@Qualifier(value = "tagDaoImpl")
	private TagDao tagDaoImpl;
	
	@Override
	public int createTag(Tag tag) {
		return tagDaoImpl.createTag(tag);
	}

	@Override
	public void updateTag(Tag tag) {
		tagDaoImpl.updateTag(tag);
	}

	@Override
	public int deleteTagById(int id) {
		return tagDaoImpl.deleteTagById(id);
	}

	@Override
	public List<Tag> getTagByPage(int offset, int limit) {


		return tagDaoImpl.getTagByPage(offset, limit);
	}

	@Override
	public List<Tag> getListTagByCategory(int id) {
		return tagDaoImpl.getListTagByCategory(id);
	}

}
