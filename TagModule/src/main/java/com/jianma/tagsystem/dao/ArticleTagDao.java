package com.jianma.tagsystem.dao;

import java.util.List;

public interface ArticleTagDao {

	public List<Integer> getArticleIdsByTag(int tagId);

}
