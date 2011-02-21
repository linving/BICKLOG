package com.saniye45.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="SINIF")
public class Sinif extends GenericModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1062851254757194758L;
	
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
