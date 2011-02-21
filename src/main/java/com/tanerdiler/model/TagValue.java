package com.tanerdiler.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tanerdiler.annotation.ToString;

@Entity
@Table(name="TAG_VALUE")
public class TagValue extends GenericModel<Integer> {

	private static final long serialVersionUID = -1489229554470925466L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ToString(value=true, type="simple")
	@Column(name="TEXT")
	private String text;
	
	@OneToMany(mappedBy="value")
	private List<Tag> tags = new ArrayList<Tag>();

	public TagValue() {
	}
	
	public TagValue(String text) {
		super();
		this.text = text;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @return the tags
	 */
	public List<Tag> getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if(this.getText() == null){
			return super.hashCode();
		} else {
			return this.getText().hashCode();
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}
		if(obj instanceof GenericModel<?>){
			GenericModel<?> __obj = (GenericModel<?>) obj;
			if(this.getEqualityCriterian() != null && this.getEqualityCriterian().equals(__obj.getEqualityCriterian())){
				return true;
			}
		}
		return false;
	}

	@Override
	public Object getEqualityCriterian() {
		return this.getText();
	}
}
