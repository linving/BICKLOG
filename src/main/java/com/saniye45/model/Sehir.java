package com.saniye45.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="SEHIR")
public class Sehir extends GenericModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3494020075855549982L;
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
