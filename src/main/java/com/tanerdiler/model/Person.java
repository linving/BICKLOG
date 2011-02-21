package com.tanerdiler.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PERSON")
public class Person extends GenericModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1372614559356586082L;
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="NICKNAME")
	private String nickname;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="FIRSTNAME")
	private String firstName;
	
	@Column(name="LASTNAME")
	private String lastName;
	
	@Column(name="REGISTERED")
	private Boolean registered;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="CREATED_TIME")
	private Calendar createdTime;
	
	@Column(name="CREATED_VIA")
	@Enumerated(EnumType.STRING)
	private PersonCreatedVia createdVia;

	@Column(name="WEBSITE")
	private String webSite;

	@Override
	public Integer getId() {
		return this.id;
	}
	
	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the registered
	 */
	public Boolean getRegistered() {
		return registered;
	}

	/**
	 * @param registered the registered to set
	 */
	public void setRegistered(Boolean registered) {
		this.registered = registered;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the createdVia
	 */
	public PersonCreatedVia getCreatedVia() {
		return createdVia;
	}

	/**
	 * @param createdVia the createdVia to set
	 */
	public void setCreatedVia(PersonCreatedVia createdVia) {
		this.createdVia = createdVia;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if(this.getEmail() == null){
			return super.hashCode();
		} else {
			return this.getEmail().hashCode();
		}
	}

	@Override
	public Object getEqualityCriterian() {
		return this.getEmail();
	}

	public String getWebSite() {
		return this.webSite;
	}
	
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

}
