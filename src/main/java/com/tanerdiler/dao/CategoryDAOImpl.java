package com.tanerdiler.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tanerdiler.model.Category;

@Repository
@Transactional
public class CategoryDAOImpl extends GenericDAO<Integer, Category> implements
		CategoryDAO {

	private static final long serialVersionUID = 2847400097956321528L;
	
	public List<Category> getParents(){
		return this.getList(new Criterion[]{Restrictions.isNull("parent")});
	}
}
