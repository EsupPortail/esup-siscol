package org.esupportail.referentiel.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Danielle Martineau : danielle.martineau@univ-rennes1.fr
 *
 */
public class EtudiantRef implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	/*
	 * *************************************************************** Proprietes
	 ****************************************************************/
	private String cod_ind = null;
	
	
	private String nompatro = "";
	private String nommarital = "";
	private String prenom = "";
	private String mail = "";
	
	private String prenomEtatCivil;
	
	private String sexEtatCivil;

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
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Europe/Paris")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Paris")
	private Date dateNais ;
	// libelle CPAM etudiant
	private String libelleCPAM = "";
	

	/**
	 * Liste des eventuelles années d'inscription en FC pour l'etudiant
	 */
	private List<String> anneesInscriptionFC;
	
	/*
	 * *************************************************************** 
	 * Getters /
	 * 	 * Setters
	 ****************************************************************/
	
	
	
	public String getCod_ind() {
		return cod_ind;
	}
	public void setCod_ind(String cod_ind) {
		this.cod_ind = cod_ind;
	}
	public String getNompatro() {
		return nompatro;
	}
	public void setNompatro(String nompatro) {
		this.nompatro = nompatro;
	}
	public String getNommarital() {
		return nommarital;
	}
	public void setNommarital(String nommarital) {
		this.nommarital = nommarital;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
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
	public String getLibelleCPAM() {
		return libelleCPAM;
	}
	public void setLibelleCPAM(String libelleCPAM) {
		this.libelleCPAM = libelleCPAM;
	}
	public List<String> getAnneesInscriptionFC() {
		return anneesInscriptionFC;
	}
	public void setAnneesInscriptionFC(List<String> anneesInscriptionFC) {
		this.anneesInscriptionFC = anneesInscriptionFC;
	}
	public String getPrenomEtatCivil() {
		return prenomEtatCivil;
	}
	public void setPrenomEtatCivil(String prenomEtatCivil) {
		this.prenomEtatCivil = prenomEtatCivil;
	}
	public String getSexEtatCivil() {
		return sexEtatCivil;
	}
	public void setSexEtatCivil(String sexEtatCivil) {
		this.sexEtatCivil = sexEtatCivil;
	}
	@Override
	public String toString() {
		return "EtudiantRef [cod_ind=" + cod_ind + ", nompatro=" + nompatro + ", nommarital=" + nommarital + ", prenom="
				+ prenom + ", mail=" + mail + ", prenomEtatCivil=" + prenomEtatCivil + ", sexEtatCivil=" + sexEtatCivil
				+ ", mainAddress=" + mainAddress + ", postalCode=" + postalCode + ", town=" + town + ", country="
				+ country + ", phone=" + phone + ", portablePhone=" + portablePhone + ", mailPerso=" + mailPerso
				+ ", codePays=" + codePays + ", libAd1=" + libAd1 + ", libAd2=" + libAd2 + ", libAd3=" + libAd3
				+ ", libAde=" + libAde + ", codeSexe=" + codeSexe + ", dateNais=" + dateNais + ", libelleCPAM="
				+ libelleCPAM + ", anneesInscriptionFC=" + anneesInscriptionFC + "]";
	}

	


	

	
}
