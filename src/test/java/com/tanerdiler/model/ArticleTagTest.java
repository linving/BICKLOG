package com.tanerdiler.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;


public class ArticleTagTest {
	
	@Test
	public void testEquality(){
		Article article_1 = new Article();
		Article article_2 = new Article();
		TagValue tagValue_1 = new TagValue("XXX");
		TagValue tagValue_2 = new TagValue("YYY");
		ArticleTag articleTag_1 = new ArticleTag();
		ArticleTag articleTag_2 = new ArticleTag();
		articleTag_1.setArticle(article_1);
		articleTag_1.setValue(tagValue_1);
		articleTag_2.setArticle(article_2);
		articleTag_2.setValue(tagValue_2);
		article_1.setId(1);
		article_2.setId(2);
		assertNotSame(articleTag_1, articleTag_2);
		
		articleTag_1.setArticle(article_1);
		articleTag_2.setArticle(article_2);
		articleTag_1.setValue(tagValue_1);
		articleTag_2.setValue(tagValue_1);
		assertNotSame(articleTag_1, articleTag_2);
		
		articleTag_1.setArticle(article_1);
		articleTag_2.setArticle(article_1);
		articleTag_1.setValue(tagValue_1);
		articleTag_2.setValue(tagValue_2);
		assertNotSame(articleTag_1, articleTag_2);
		
		articleTag_1.setArticle(article_1);
		articleTag_2.setArticle(article_1);
		articleTag_1.setValue(tagValue_1);
		articleTag_2.setValue(tagValue_1);
		assertEquals(articleTag_1, articleTag_2);
		
		Set<ArticleTag> articleTagSet = new HashSet<ArticleTag>();
		articleTag_1.setArticle(article_1);
		articleTag_2.setArticle(article_1);
		articleTag_1.setValue(tagValue_1);
		articleTag_2.setValue(tagValue_1);
		articleTagSet.add(articleTag_1);
		articleTagSet.add(articleTag_2);
		assertTrue(articleTagSet.size() == 1);
		
		articleTagSet =  new HashSet<ArticleTag>();
		articleTag_1.setArticle(article_1);
		articleTag_2.setArticle(article_1);
		articleTag_1.setValue(tagValue_1);
		articleTag_2.setValue(tagValue_2);
		articleTagSet.add(articleTag_1);
		articleTagSet.add(articleTag_2);
		assertTrue(articleTagSet.size() == 2);
		
	}
}
