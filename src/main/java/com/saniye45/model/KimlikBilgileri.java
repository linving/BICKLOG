package com.saniye45.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name="KIMLIK_BILGILERI")
public class KimlikBilgileri extends GenericModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1170986206076319438L;
	@Column(name="AD")
	private String ad;
	@Column(name="AD_2")
	private String ad2;
	@Column(name="SOYAD")
	private String soyad;
	@Column(name="BABA_ADI")
	private String babaAdi;
	@Column(name="ANA_ADI")
	private String anaAdi;
	@Column(name="DOGUM_YERI")
	private Sehir dogumYeri;
	@Column(name="DOGUM_TARIHI")
	private Calendar dogumTarihi;
	@Column(name="MEDENI_HALI")
	@Enumerated(EnumType.STRING)
	private MedeniHal medeniHali;
	@Column(name="KAYIT_IL")
	private Sehir kayitliOlduguSehir;
	@Column(name="KAYIT_ILCE")
	private String kayitliOlduguIlce;
	@Column(name="KAYIT_KOY")
	private String kayitliOlduguKoy;
	@Column(name="KAYIT_CILTNO")
	private String ciltNo;
	@Column(name="KAYIT_AILESIRANO")
	private String aileSiraNo;
	@Column(name="KAYIT_SIRANO")
	private String siraNo;
	/**
	 * @return the ad
	 */
	public String getAd() {
		return ad;
	}
	/**
	 * @param ad the ad to set
	 */
	public void setAd(String ad) {
		this.ad = ad;
	}
	/**
	 * @return the ad2
	 */
	public String getAd2() {
		return ad2;
	}
	/**
	 * @param ad2 the ad2 to set
	 */
	public void setAd2(String ad2) {
		this.ad2 = ad2;
	}
	/**
	 * @return the soyad
	 */
	public String getSoyad() {
		return soyad;
	}
	/**
	 * @param soyad the soyad to set
	 */
	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}
	/**
	 * @return the babaAdi
	 */
	public String getBabaAdi() {
		return babaAdi;
	}
	/**
	 * @param babaAdi the babaAdi to set
	 */
	public void setBabaAdi(String babaAdi) {
		this.babaAdi = babaAdi;
	}
	/**
	 * @return the anaAdi
	 */
	public String getAnaAdi() {
		return anaAdi;
	}
	/**
	 * @param anaAdi the anaAdi to set
	 */
	public void setAnaAdi(String anaAdi) {
		this.anaAdi = anaAdi;
	}
	/**
	 * @return the dogumYeri
	 */
	public Sehir getDogumYeri() {
		return dogumYeri;
	}
	/**
	 * @param dogumYeri the dogumYeri to set
	 */
	public void setDogumYeri(Sehir dogumYeri) {
		this.dogumYeri = dogumYeri;
	}
	/**
	 * @return the dogumTarihi
	 */
	public Calendar getDogumTarihi() {
		return dogumTarihi;
	}
	/**
	 * @param dogumTarihi the dogumTarihi to set
	 */
	public void setDogumTarihi(Calendar dogumTarihi) {
		this.dogumTarihi = dogumTarihi;
	}
	/**
	 * @return the medeniHali
	 */
	public MedeniHal getMedeniHali() {
		return medeniHali;
	}
	/**
	 * @param medeniHali the medeniHali to set
	 */
	public void setMedeniHali(MedeniHal medeniHali) {
		this.medeniHali = medeniHali;
	}
	/**
	 * @return the kayitliOlduguSehir
	 */
	public Sehir getKayitliOlduguSehir() {
		return kayitliOlduguSehir;
	}
	/**
	 * @param kayitliOlduguSehir the kayitliOlduguSehir to set
	 */
	public void setKayitliOlduguSehir(Sehir kayitliOlduguSehir) {
		this.kayitliOlduguSehir = kayitliOlduguSehir;
	}
	/**
	 * @return the kayitliOlduguIlce
	 */
	public String getKayitliOlduguIlce() {
		return kayitliOlduguIlce;
	}
	/**
	 * @param kayitliOlduguIlce the kayitliOlduguIlce to set
	 */
	public void setKayitliOlduguIlce(String kayitliOlduguIlce) {
		this.kayitliOlduguIlce = kayitliOlduguIlce;
	}
	/**
	 * @return the kayitliOlduguKoy
	 */
	public String getKayitliOlduguKoy() {
		return kayitliOlduguKoy;
	}
	/**
	 * @param kayitliOlduguKoy the kayitliOlduguKoy to set
	 */
	public void setKayitliOlduguKoy(String kayitliOlduguKoy) {
		this.kayitliOlduguKoy = kayitliOlduguKoy;
	}
	/**
	 * @return the ciltNo
	 */
	public String getCiltNo() {
		return ciltNo;
	}
	/**
	 * @param ciltNo the ciltNo to set
	 */
	public void setCiltNo(String ciltNo) {
		this.ciltNo = ciltNo;
	}
	/**
	 * @return the aileSiraNo
	 */
	public String getAileSiraNo() {
		return aileSiraNo;
	}
	/**
	 * @param aileSiraNo the aileSiraNo to set
	 */
	public void setAileSiraNo(String aileSiraNo) {
		this.aileSiraNo = aileSiraNo;
	}
	/**
	 * @return the siraNo
	 */
	public String getSiraNo() {
		return siraNo;
	}
	/**
	 * @param siraNo the siraNo to set
	 */
	public void setSiraNo(String siraNo) {
		this.siraNo = siraNo;
	}
}
