package com.jianma.tagsystem.service;

import java.util.List;

import com.jianma.tagsystem.module.TagCategory;

public interface TagCategoryService {

	public int createTagCategory(TagCategory tagCategory);
	
	public int deleteTagCategoryById(int id);
	
	public void updateTagCategory(TagCategory tagCategory);
	
	public List<TagCategory> getTagCategoryByPage(int offset, int limit);
	
	public TagCategory getTagCategoryById(int categoryId);
}
