package com.tanerdiler.businessprocess;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tanerdiler.BlogConstants;
import com.tanerdiler.dao.TagDAO;
import com.tanerdiler.dao.TagValueDAO;
import com.tanerdiler.model.Article;
import com.tanerdiler.model.ArticleTag;
import com.tanerdiler.model.TagValue;

@Component
public class TagValueUniqueController {
	
	@Autowired
	private TagValueDAO tagValueDAO;
	
	@Autowired
	private TagDAO tagDAO;
	
	public void execute(Article article){
		//TODO also control articletag
		Set<ArticleTag> tmpArticleTags = new HashSet<ArticleTag>();
		List<ArticleTag> articleTags = new ArrayList<ArticleTag>(article.getTags());
		
		// Yeni eklenecekleri ve var olanlari silip, bu listede sadece bir sonraki 
		// adımda silinecekler tagle belirleniyor
		separateDeletableTags(article, tmpArticleTags,
				articleTags);
		// belirlenen silinecek tagler siliniyor
		deleteUnnecessaryTags(articleTags);
		// varolan ve eklenecek olan tagler yükleniyor
		article.setTags(new HashSet<ArticleTag>(tmpArticleTags));
		// varolan taglere id'leri yükleniyor
		setIdentifierValueOfPersistedTag(article);
	}

	private void setIdentifierValueOfPersistedTag(Article article) {
		List<TagValue> persistedTags = tagValueDAO.getList(null);
		for(ArticleTag tag : article.getTags()){
			TagValue value = tag.getValue();
			if(persistedTags.contains(value)){
				value.setId(persistedTags.get(persistedTags.indexOf(value)).getId());
			}
			System.out.println("DEBUG : TagValueUniqueController TAGVALUE {"+value.getId()+" - "+value.getText()+"}");
		}
	}

	private void deleteUnnecessaryTags(List<ArticleTag> articleTags) {
		// break the links
		if(articleTags.size() > 0){
			Iterator<ArticleTag> iter = articleTags.iterator();
			while(iter.hasNext()){
				ArticleTag tag = iter.next();
				iter.remove();
				tagDAO.remove(tag);
			}
		}
	}

	private void separateDeletableTags(Article article,
			Set<ArticleTag> tmpArticleTags, List<ArticleTag> articleTags) {
		String tagString = article.getTagString();
		if(tagString != null){
			String[] tags = tagString.split(",");
			for(String tagValue : tags){
				tagValue = tagValue.replaceAll(BlogConstants.RGX_TAG_COUNT, "").trim();
				ArticleTag tmpTag = new ArticleTag(article, new TagValue(tagValue));
				ArticleTag newTag = null;
				
				if(articleTags.contains(tmpTag)){
					// do nothing protect concurency
					newTag = articleTags.get(articleTags.indexOf(tmpTag));
					tmpTag.setValue(null);
					tmpTag.setArticle(null);
					articleTags.remove(newTag);
				} else {
					newTag = tmpTag;
				}
				System.out.println("DEBUG : "+tmpArticleTags.contains(newTag));
				if(!tmpArticleTags.contains(newTag)){
					tmpArticleTags.add(newTag);
				}
			}
		}
	}

}
