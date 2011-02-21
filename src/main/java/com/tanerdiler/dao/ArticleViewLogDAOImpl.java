package com.tanerdiler.dao;

import org.springframework.stereotype.Repository;

import com.tanerdiler.model.ArticleViewLog;

@Repository
public class ArticleViewLogDAOImpl extends GenericDAO<Integer, ArticleViewLog>
		implements ArticleViewLogDAO {

	private static final long serialVersionUID = -1316850283492989924L;

}
