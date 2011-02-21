package com.tanerdiler.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ENTITY_TAG")
@Inheritance (strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn (name="ENTITY_TYPE", discriminatorType=DiscriminatorType.STRING)
public abstract class Tag extends GenericModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -110560322779439583L;
	
	public Tag(){
		// DO NOTHING FOR HIBERNATE
	}
	
	public Tag(TagValue value){
		this.setValue(value);
	}
	

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="TAG_ID", referencedColumnName="ID")
	private TagValue value;


	/**
	 * @return the value
	 */
	public TagValue getValue() {
		return value;
	}


	/**
	 * @param value the value to set
	 */
	public void setValue(TagValue value) {
		this.value = value;
		if(value != null){
			this.value.getTags().add(this);
		}
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
	

}
