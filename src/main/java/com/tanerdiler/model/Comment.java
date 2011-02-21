package com.tanerdiler.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tanerdiler.BlogConstants;
import com.tanerdiler.annotation.ToString;

@Entity
@Table(name="COMMENT")
@Inheritance (strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn (name="ENTITY_TYPE", discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("COMMENT")
public abstract class Comment extends GenericModel<Integer> {

	private static final long serialVersionUID = 7598830008533947937L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="PERSON_ID")
	private Person author;
	
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="AUTHOR_ID")
	private CommentAuthor unknownAuthor;
	
	@ToString(value=true, type=ToString.TYPE_SIMPLE)
	@Column(name="COMMENT")
	private String comment;
	
	@Column(name="CREATED_TIME")
	private Calendar createdTime;
	
	@ManyToOne
	@JoinColumn(name="PARENT_ID", referencedColumnName="ID")
	private Comment parent;
	
	@OneToMany(mappedBy="parent")
	private List<Comment> comments = new ArrayList<Comment>(); 

	@Override
	public Integer getId() {
		return this.id;
	}
	
	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getAuthorName(){
		if(this.author != null){
			return this.author.getFirstName()+" "+this.author.getLastName();
		} else if(this.unknownAuthor != null){
			return this.unknownAuthor.getFirstName()+" "+this.unknownAuthor.getLastName();
		}
		return BlogConstants.UNKNOWN_PERSON;
	}
	
	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}

	public CommentAuthor getUnknownAuthor() {
		return unknownAuthor;
	}

	public void setUnknownAuthor(CommentAuthor unknownAuthor) {
		this.unknownAuthor = unknownAuthor;
	}

	/**
	 * @return the createdTime
	 */
	public Calendar getCreatedTime() {
		return createdTime;
	}

	/**
	 * @param createdTime the createdTime to set
	 */
	public void setCreatedTime(Calendar createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * @return the parent
	 */
	public Comment getParent() {
		return parent;
	}

	/**
	 * @return the comments
	 */
	public List<Comment> getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Comment parent) {
		this.parent = parent;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public Object getEqualityCriterian() {
		return this.getId();
	}
	
}
