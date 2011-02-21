package com.saniye45.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="OLAY")
public class Olay extends GenericModel<Integer> {

	private static final long serialVersionUID = 2361981861082989614L;
	
	@Column(name = "BASLIK")
	private String baslik;
	@Column(name = "ACIKLAMA")
	private String aciklama;
	@Column(name = "ZAMAN")
	private Calendar zaman;
	@ManyToOne
	@JoinColumn(name = "SEHIR_ID")
	private Sehir sehir;
	@Column(name = "YER")
	private String yer;
	@OneToMany
	private List<Sehit> sehitler;
	
	/**
	 * @return the sehitler
	 */
	public List<Sehit> getSehitler() {
		return sehitler;
	}
	/**
	 * @param sehitler the sehitler to set
	 */
	public void setSehitler(List<Sehit> sehitler) {
		this.sehitler = sehitler;
	}
	/**
	 * @return the baslik
	 */
	public String getBaslik() {
		return baslik;
	}
	/**
	 * @param baslik the baslik to set
	 */
	public void setBaslik(String baslik) {
		this.baslik = baslik;
	}
	/**
	 * @return the aciklama
	 */
	public String getAciklama() {
		return aciklama;
	}
	/**
	 * @param aciklama the aciklama to set
	 */
	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}
	/**
	 * @return the zaman
	 */
	public Calendar getZaman() {
		return zaman;
	}
	/**
	 * @param zaman the zaman to set
	 */
	public void setZaman(Calendar zaman) {
		this.zaman = zaman;
	}
	/**
	 * @return the sehir
	 */
	public Sehir getSehir() {
		return sehir;
	}
	/**
	 * @param sehir the sehir to set
	 */
	public void setSehir(Sehir sehir) {
		this.sehir = sehir;
	}
	/**
	 * @return the yer
	 */
	public String getYer() {
		return yer;
	}
	/**
	 * @param yer the yer to set
	 */
	public void setYer(String yer) {
		this.yer = yer;
	}
}
