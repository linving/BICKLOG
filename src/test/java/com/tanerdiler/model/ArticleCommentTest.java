package com.tanerdiler.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class ArticleCommentTest {
	@Test
	public void testEquality(){
		ArticleComment comment_1 = new ArticleComment();
		ArticleComment comment_2 = new ArticleComment();
		assertNotSame(comment_1, comment_2);
		
		comment_1.setId(1);
		assertNotSame(comment_1, comment_2);
		
		comment_2.setId(2);
		assertNotSame(comment_1, comment_2);
		
		comment_1.setId(null);
		assertNotSame(comment_1, comment_2);
		
		comment_1.setId(3);
		comment_2.setId(3);
		assertEquals(comment_1, comment_2);
		
		Set<ArticleComment> articleCommentSet = new HashSet<ArticleComment>();
		articleCommentSet.add(comment_1);
		articleCommentSet.add(comment_2);
		assertTrue(articleCommentSet.size()==2);
	}
}
