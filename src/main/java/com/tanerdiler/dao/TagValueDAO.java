package com.tanerdiler.dao;

import java.util.List;

import com.tanerdiler.model.TagValue;
import com.tanerdiler.model.view.TagValueWithArticleCount;

public interface TagValueDAO extends IGenericDAO<Integer, TagValue> {

	public TagValue findByText(String text);

	public List<TagValueWithArticleCount> getTagValuesWithArticleCount();

}
