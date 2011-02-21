package com.tanerdiler.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.tanerdiler.dao.IGenericDAO;
import com.tanerdiler.model.Article;
import com.tanerdiler.model.GenericModel;
import com.tanerdiler.model.Tag;

public class Filter {

	public static <ID extends Serializable, M extends GenericModel<ID>, DAO extends IGenericDAO<ID, M>> List<M> filter(DAO dao, Predicate<M> predicate){
		List<M> list = dao.getList(null);
		List<M> result = new ArrayList<M>();
		for(M model : list){
			if(predicate.apply(model)){
				result.add(model);
			}
		}
		return result;
	}
	
	public static Predicate<Article> matchTag(final String tagText){
		return new Predicate<Article>() {
			
			@Override
			public Criterion[] getCriteria() {
				List<Criterion> criteria = new ArrayList<Criterion>();
				criteria.add(Restrictions.eq("tags.value.text", tagText));
				return criteria.toArray(new Criterion[]{});
			}
			
			@Override
			public boolean apply(Article input) {
			    for(Tag tag : input.getTags()){
			    	System.out.println(tag.getValue().getText()+" --- "+tagText+" : "+((tag.getValue().getText()).equals((String)tagText)));
					if((tag.getValue().getText()).equals((String)tagText)){return true;}
				}
			    return false;
			}
		};
	}
}
