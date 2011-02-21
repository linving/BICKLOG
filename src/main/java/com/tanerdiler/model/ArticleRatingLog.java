package com.tanerdiler.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="LOG_ARTICLE_RATINGS")
public class ArticleRatingLog extends GenericModel<Integer> {

	private static final long serialVersionUID = -162084259918001896L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="VOTE_VALUE")
	private Integer value;

	@Column(name="VOTE_TIME")
	private Calendar time;

	@ManyToOne
	@JoinColumn(name="VOTED_BY")
	private Person person;
	
	@Column(name="IP")
	private String ip;
	
	@Column(name="SESSION_ID")
	private String sessionId;

	@ManyToOne
	@JoinColumn(name="ARTICLE_ID")
	private Article article;
	
	
	public ArticleRatingLog() {
		// DO NOTHING
		init(null, null, null);
	}

	public ArticleRatingLog(Person person, Article article) {
		init(person, article, null);
	}

	public ArticleRatingLog(Person person, Article article, Integer voteValue) {
		init(person, article, voteValue);
	}
	
	private void init(Person person, Article article, Integer voteValue){
		this.article = article;
		this.person = person;
		this.time = Calendar.getInstance();
		this.value = voteValue;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getValue() {
		return value;
	}
	
	public void setValue(Integer value) {
		this.value = value;
	}

	public Calendar getTime() {
		return time;
	}

	public void setTime(Calendar time) {
		this.time = time;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public Object getEqualityCriterian() {
		return this.id;
	}
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
