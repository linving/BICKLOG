package com.tanerdiler.model;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;


public class ArticleTest {
	
	@Test
	public void testEquality(){
		Article article_1 = new Article();
		Article article_2 = new Article();
		assertNotSame(article_1, article_2);
		
		article_1.setId(1);
		assertNotSame(article_1, article_2);
		
		article_2.setId(2);
		assertNotSame(article_1, article_2);
		
		article_1.setId(null);
		assertNotSame(article_1, article_2);
		
		article_1.setId(3);
		article_2.setId(3);
		assertEquals(article_1, article_2);
		
		Set<Article> articleSet = new HashSet<Article>();
		articleSet.add(article_1);
		articleSet.add(article_2);
		assertTrue(articleSet.size()==2);
		
	}
}
