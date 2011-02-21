package com.tanerdiler.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tanerdiler.model.TagValue;
import com.tanerdiler.model.view.TagValueWithArticleCount;

@Repository
@Transactional
public class TagValueDAOImpl extends GenericDAO<Integer, TagValue> implements TagValueDAO {

	private static final long serialVersionUID = -4321258343319528466L;
	
	public TagValue findByText(String text){
		String sql = "from "+TagValue.class.getSimpleName()+" where lower(text)=:textParam";
		Query query = getSession().createQuery(sql);
		query.setParameter("textParam", text);
		TagValue value = (TagValue) query.uniqueResult();
		return value;
	}
	
	@SuppressWarnings("unchecked")
	public List<TagValueWithArticleCount> getTagValuesWithArticleCount(){
		Criteria criteria = getSession().createCriteria(TagValueWithArticleCount.class);
		return (List<TagValueWithArticleCount>) criteria.list();
	}
	
	public void evict(TagValue tagValue){
		getSession().evict(tagValue);
	}
	
}
