package com.tanerdiler.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("ARTICLE")
public class ArticleTag extends Tag {

	public ArticleTag() {
		super();
	}

	public ArticleTag(Article article, TagValue value) {
		super(value);
		this.setArticle(article);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 772835998183970861L;
	
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
	
	@Override
	public Object getEqualityCriterian() {
		return this.getValue().getEqualityCriterian()+""+this.getArticle();
	}
	
	@Override
	public int hashCode() {
		return (this.article.hashCode()+""+this.getValue().hashCode()).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false;
		if(!(obj instanceof ArticleTag)){
			isEqual = false;
		} else {
			ArticleTag __tag = (ArticleTag) obj;
			System.out.println("ARTICLETAG EQUALS : ARTICLE("+this.article.equals(__tag.getArticle())+") VALUE("+this.getValue().equals(__tag.getValue())+")");
			isEqual = this.article.equals(__tag.getArticle()) && this.getValue().equals(__tag.getValue());
		}
		System.out.println("ARTICLETAG EQUAL VALUE : "+isEqual);
		return isEqual;
	}
}
