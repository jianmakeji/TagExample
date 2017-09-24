package com.jianma.tagsystem.dao;

import java.util.List;

import com.jianma.tagsystem.module.Tag;

public interface TagDao {

	public int createTag(Tag tag);
	
	public void updateTag(Tag tag);
	
	public int deleteTagById(int id);
	
	public List<Tag> getTagByPage(int offset, int limit);
	
}
