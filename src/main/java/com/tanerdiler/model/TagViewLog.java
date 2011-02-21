package com.tanerdiler.model;

import java.util.Calendar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("TAG")
public class TagViewLog extends ViewLog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6584248553745981432L;
	
	@ManyToOne
	@JoinColumn(name="ENTITY_ID")
	private Tag tag;
	
	public TagViewLog() {
		// DO NOTHING
	}

	public TagViewLog(Person person, Tag tag) {
		this.setTag(tag);
		this.setPerson(person);
		this.setTime(Calendar.getInstance());
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

}
