package com.tanerdiler.model.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tanerdiler.model.GenericModel;

@Entity
@Table(name="TAG_VALUE_WITH_ARTICLE_COUNT")
public class TagValueWithArticleCount extends GenericModel<Integer>{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID", updatable=false)
	private Integer id;
	
	@Column(name="TEXT", updatable=false)
	private String text;
	
	@Column(name="COUNT", updatable=false)
	private Integer count;
	
	@Transient
	public String getLabel(){
		return this.text+"("+this.count+")";
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public Object getEqualityCriterian() {
		return this.id;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

}
