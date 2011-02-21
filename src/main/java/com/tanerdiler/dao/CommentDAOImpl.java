package com.tanerdiler.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tanerdiler.model.Comment;

@Repository
@Transactional
public class CommentDAOImpl extends GenericDAO<Integer, Comment> implements
		CommentDAO {

	private static final long serialVersionUID = -8504950934656495807L;

}
