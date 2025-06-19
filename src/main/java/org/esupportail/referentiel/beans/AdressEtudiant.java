package org.esupportail.referentiel.beans;

import java.util.Date;

public class AdressEtudiant {

	// adresse permanente etudiant
	private String mainAddress = "";
	// code postal etudiant
	private String postalCode = "";
	// commune etudiant
	private String town = "";
	// pays etudiant
	private String country = "";
	// telephone etudiant
	private String phone = "";
	// telephone portable etudiant
	private String portablePhone = "";
	// courrier personnel etudiant
	private String mailPerso = "";
	// code pays
	private String codePays = "";
	// libelle adresse france
	private String libAd1 = "";
	private String libAd2 = "";
	private String libAd3 = "";
	// libelle adresse etrangere
	private String libAde = "";
	// code sexe etudiant
	private String codeSexe = "";
	// date Naissance etudiant
	private Date dateNais = new Date();
	public String getMainAddress() {
		return mainAddress;
	}
	public void setMainAddress(String mainAddress) {
		this.mainAddress = mainAddress;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPortablePhone() {
		return portablePhone;
	}
	public void setPortablePhone(String portablePhone) {
		this.portablePhone = portablePhone;
	}
	public String getMailPerso() {
		return mailPerso;
	}
	public void setMailPerso(String mailPerso) {
		this.mailPerso = mailPerso;
	}
	public String getCodePays() {
		return codePays;
	}
	public void setCodePays(String codePays) {
		this.codePays = codePays;
	}
	public String getLibAd1() {
		return libAd1;
	}
	public void setLibAd1(String libAd1) {
		this.libAd1 = libAd1;
	}
	public String getLibAd2() {
		return libAd2;
	}
	public void setLibAd2(String libAd2) {
		this.libAd2 = libAd2;
	}
	public String getLibAd3() {
		return libAd3;
	}
	public void setLibAd3(String libAd3) {
		this.libAd3 = libAd3;
	}
	public String getLibAde() {
		return libAde;
	}
	public void setLibAde(String libAde) {
		this.libAde = libAde;
	}
	public String getCodeSexe() {
		return codeSexe;
	}
	public void setCodeSexe(String codeSexe) {
		this.codeSexe = codeSexe;
	}
	public Date getDateNais() {
		return dateNais;
	}
	public void setDateNais(Date dateNais) {
		this.dateNais = dateNais;
	}
	
	
	
	
}
