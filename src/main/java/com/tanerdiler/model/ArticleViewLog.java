package com.tanerdiler.model;

import java.util.Calendar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("ARTICLE")
public class ArticleViewLog extends ViewLog {

	private static final long serialVersionUID = 8976408461319444877L;

	@ManyToOne
	@JoinColumn(name="ENTITY_ID")
	private Article article;
	
	public ArticleViewLog() {
		// DO NOTHING
	}

	public ArticleViewLog(Person person, Article article) {
		this.setArticle(article);
		this.setPerson(person);
		this.setTime(Calendar.getInstance());
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

}
