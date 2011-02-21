package com.tanerdiler.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tanerdiler.model.ArticleRatingLog;

@Repository
@Transactional
public class ArticleRatingLogDAOImpl extends
		GenericDAO<Integer, ArticleRatingLog> implements ArticleRatingLogDAO {

	private static final long serialVersionUID = 4995753235652388864L;

}
