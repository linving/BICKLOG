package com.saniye45.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="RUTBE")
public class Rutbe extends GenericModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1013519081184124639L;
	
	@Column(name="ISIM")
	private String isim;
	
	/**
	 * @return the isim
	 */
	public String getIsim() {
		return isim;
	}
	/**
	 * @param isim the isim to set
	 */
	public void setIsim(String isim) {
		this.isim = isim;
	}
	

}
