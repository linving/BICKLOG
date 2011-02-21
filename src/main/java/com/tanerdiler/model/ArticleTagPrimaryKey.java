package com.tanerdiler.model;

import java.io.Serializable;


public class ArticleTagPrimaryKey implements Serializable {

	private static final long serialVersionUID = 7202123649373271410L;
	
	private TagValue value;
	private Article article;
	
	public ArticleTagPrimaryKey(){
		
	}
	
	public ArticleTagPrimaryKey(TagValue value, Article article){
		this.value = value;
		this.article = article;
	}
	
	public TagValue getValue() {
		return value;
	}
	public Article getArticle() {
		return article;
	}
	
	   public boolean equals(Object o) { 
	        return ((o instanceof ArticleTagPrimaryKey) && 
	                value.equals(((ArticleTagPrimaryKey)o).getValue()) &&
	                article.equals(((ArticleTagPrimaryKey) o).getArticle()));
	    }

	    public int hashCode() { 
	        return (value.hashCode() +""+ article.hashCode()).hashCode(); 
	    }

}
