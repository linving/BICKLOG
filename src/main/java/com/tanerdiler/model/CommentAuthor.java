package com.tanerdiler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tanerdiler.annotation.ToString;

@Entity
@Table(name="COMMENT_AUTHOR")
public class CommentAuthor extends GenericModel<Integer> {

	private static final long serialVersionUID = 8297246390222181037L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ToString(value=true, type=ToString.TYPE_SIMPLE)
	@Column(name="FIRSTNAME")
	private String firstName;
	
	@ToString(value=true, type=ToString.TYPE_SIMPLE)
	@Column(name="LASTNAME")
	private String lastName;
	
	@ToString(value=true, type=ToString.TYPE_SIMPLE)
	@Column(name="EMAIL")
	private String email;
	
	@ToString(value=true, type=ToString.TYPE_SIMPLE)
	@Column(name="WEBSITE")
	private String webSite;
	
	@Override
	public Object getEqualityCriterian() {
		return this.getId();
	}

	@Override
	public Integer getId() {
		return this.getId();
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

}
