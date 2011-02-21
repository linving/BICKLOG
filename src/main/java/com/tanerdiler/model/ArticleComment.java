package com.tanerdiler.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("ARTICLE")
public class ArticleComment extends Comment {

	private static final long serialVersionUID = -3324262107381315979L;
	
	@ManyToOne
	@JoinColumn(name="ENTITY_ID", referencedColumnName="ID")
	private Article article;

	/**
	 * @return the article
	 */
	public Article getArticle() {
		return article;
	}

	/**
	 * @param article the article to set
	 */
	public void setArticle(Article article) {
		this.article = article;
	}

}