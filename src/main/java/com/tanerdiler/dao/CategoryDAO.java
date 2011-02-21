package com.tanerdiler.dao;

import java.util.List;

import com.tanerdiler.model.Category;


public interface CategoryDAO extends IGenericDAO<Integer, Category> {
	
	public List<Category> getParents();
	
}
