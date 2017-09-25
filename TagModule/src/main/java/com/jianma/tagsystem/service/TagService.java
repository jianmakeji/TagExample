package com.jianma.tagsystem.service;

import java.util.List;

import com.jianma.tagsystem.module.Tag;

public interface TagService {

	public int createTag(Tag tag);
	
	public void updateTag(Tag tag);
	
	public int deleteTagById(int id);
	
	public List<Tag> getTagByPage(int offset, int limit);
	
	public List<Tag> getListTagByCategory(int id);
}
