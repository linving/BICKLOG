package com.saniye45.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="SEHIT")
public class Sehit extends GenericModel<Integer> {

	private static final long serialVersionUID = -7752265140863778745L;

	@Column(name="DEFNEDILDIGI_SEHIR")
	private Sehir defnedildigiSehir;
	@Column(name="DEFNEDILDIGI_YER")
	private String defnedildigiYer;
	@OneToOne
	@JoinColumn(name="ASKERLIK_BILGILERI_ID")
	private AskerlikBilgileri askerlikBilgileri;
	@OneToOne
	@JoinColumn(name="KIMLIK_BILGILERI_ID")
	private KimlikBilgileri kimlikBilgileri;
	@ManyToOne
	@JoinColumn(name="OLAY_ID")
	private Olay olay;
	
	/**
	 * @return the defnedildigiSehir
	 */
	public Sehir getDefnedildigiSehir() {
		return defnedildigiSehir;
	}
	/**
	 * @param defnedildigiSehir the defnedildigiSehir to set
	 */
	public void setDefnedildigiSehir(Sehir defnedildigiSehir) {
		this.defnedildigiSehir = defnedildigiSehir;
	}
	/**
	 * @return the defnedildigiYer
	 */
	public String getDefnedildigiYer() {
		return defnedildigiYer;
	}
	/**
	 * @param defnedildigiYer the defnedildigiYer to set
	 */
	public void setDefnedildigiYer(String defnedildigiYer) {
		this.defnedildigiYer = defnedildigiYer;
	}
	/**
	 * @return the askerlikBilgileri
	 */
	public AskerlikBilgileri getAskerlikBilgileri() {
		return askerlikBilgileri;
	}
	/**
	 * @param askerlikBilgileri the askerlikBilgileri to set
	 */
	public void setAskerlikBilgileri(AskerlikBilgileri askerlikBilgileri) {
		this.askerlikBilgileri = askerlikBilgileri;
	}
	/**
	 * @return the kimlikBilgileri
	 */
	public KimlikBilgileri getKimlikBilgileri() {
		return kimlikBilgileri;
	}
	/**
	 * @param kimlikBilgileri the kimlikBilgileri to set
	 */
	public void setKimlikBilgileri(KimlikBilgileri kimlikBilgileri) {
		this.kimlikBilgileri = kimlikBilgileri;
	}
	/**
	 * @return the olay
	 */
	public Olay getOlay() {
		return olay;
	}
	/**
	 * @param olay the olay to set
	 */
	public void setOlay(Olay olay) {
		this.olay = olay;
	}

}
