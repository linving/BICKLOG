package com.saniye45.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ASKERLIK_BILGILERI")
public class AskerlikBilgileri extends GenericModel<Integer> {

	private static final long serialVersionUID = -1040428899612882751L;
	
	@Column(name="BIRLIK")
	private String birlik;
	@ManyToOne
	@JoinColumn(name="SINIF_ID")
	private Sinif sinif;
	@ManyToOne
	@JoinColumn(name="RUTBE_ID")
	private Rutbe rutbe;
	@ManyToOne
	@JoinColumn(name="TERTIP_ID")
	private Tertip tertip;
	
	
	/**
	 * @return the birlik
	 */
	public String getBirlik() {
		return birlik;
	}
	/**
	 * @param birlik the birlik to set
	 */
	public void setBirlik(String birlik) {
		this.birlik = birlik;
	}
	/**
	 * @return the sinif
	 */
	public Sinif getSinif() {
		return sinif;
	}
	/**
	 * @param sinif the sinif to set
	 */
	public void setSinif(Sinif sinif) {
		this.sinif = sinif;
	}
	/**
	 * @return the rutbe
	 */
	public Rutbe getRutbe() {
		return rutbe;
	}
	/**
	 * @param rutbe the rutbe to set
	 */
	public void setRutbe(Rutbe rutbe) {
		this.rutbe = rutbe;
	}
	/**
	 * @return the tertip
	 */
	public Tertip getTertip() {
		return tertip;
	}
	/**
	 * @param tertip the tertip to set
	 */
	public void setTertip(Tertip tertip) {
		this.tertip = tertip;
	}
}
