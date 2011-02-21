package com.tanerdiler.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CATEGORY")
public class Category extends GenericModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7582097880884667762L;
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="PARENT_ID")
	private Category parent;
	
	@OneToMany(mappedBy="parent", fetch = FetchType.EAGER)
	private Set<Category> childs = new HashSet<Category>();

	@Column(name="NAME")
	private String name;

	
	@Override
	public Integer getId() {
		return this.id;
	}
	
	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the parent
	 */
	public Category getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	private void setParent(Category parent) {
		this.parent = parent;
	}

	public void addChild(Category child){
		this.childs.add(child);
		child.setParent(this);
	}
	
	public boolean hasChild(){
		return this.childs != null && this.childs.size() > 0;
	}
	
	public Set<Category> getChilds() {
		return childs;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if(this.getName() == null){
			return super.hashCode();
		} else {
			return this.getName().hashCode();
		}
	}

	@Override
	public Object getEqualityCriterian() {
		return this.getName();
	}

}
