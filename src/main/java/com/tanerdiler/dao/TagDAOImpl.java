package com.tanerdiler.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tanerdiler.model.ArticleTag;
import com.tanerdiler.model.Tag;

@Repository
@Transactional
public class TagDAOImpl extends GenericDAO<Integer, Tag> implements TagDAO {

	@Override
	public void remove(Tag model) {
		if(model instanceof ArticleTag){
			ArticleTag tag = (ArticleTag) model;
			//tag.getValue().getTags().remove(tag);
			tag.setValue(null);
			tag.setArticle(null);
		}
		super.remove(model);
	}

	private static final long serialVersionUID = -2652289819395866432L;

}
