package com.tanerdiler.dao;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tanerdiler.businessprocess.TagValueUniqueController;
import com.tanerdiler.model.Article;

@Repository
@Transactional
public class ArticleDAOImpl extends GenericDAO<Integer, Article> implements ArticleDAO {

	private static final long serialVersionUID = -766626115765042057L;
	
	@Autowired
	private TagValueUniqueController tagValueUnqProvider;
	
	@Override
	public Integer saveOrUpdate(final Article article) {
		if(article.getCreatedTime() == null){
			article.setCreatedTime(Calendar.getInstance());
		}
		tagValueUnqProvider.execute(article);
		return super.saveOrUpdate(article);
	}

}
