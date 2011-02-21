package com.tanerdiler.ormlistener;

import java.io.Serializable;
import java.util.Set;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.event.SaveOrUpdateEvent;
import org.hibernate.event.def.DefaultSaveOrUpdateEventListener;
import org.hibernate.exception.ConstraintViolationException;

import com.tanerdiler.model.Article;
import com.tanerdiler.model.ArticleTag;
import com.tanerdiler.model.TagValue;

public class EntityCRUDListener extends DefaultSaveOrUpdateEventListener
{
	@Override
	protected Serializable performSaveOrUpdate(SaveOrUpdateEvent event) {
		Object entity = event.getObject();
		try{
			if(entity instanceof Article){
				Article article = (Article) entity;
				Set<ArticleTag> tags = article.getTags();
				for(ArticleTag tag : tags){
					TagValue value = tag.getValue();
					System.out.println("DEBUG : ONSAVEORUPDATE TAGVALUE {"+value.getId()+" - "+value.getText()+"}");
				}
			}
			return super.performSaveOrUpdate(event);
		} catch(NonUniqueObjectException ex){
			if(entity instanceof TagValue){
				return null;
			} else {
				throw ex;
			}
		} catch(ConstraintViolationException ex){
			if(entity instanceof TagValue){
				System.out.println("DUPLICAT TAGVALUE {"+((TagValue)entity).getId()+" - "+((TagValue)entity).getText()+"}");
			}			
			throw ex;
		}
	}

	private static final long serialVersionUID = 9142028840147480706L;

}
