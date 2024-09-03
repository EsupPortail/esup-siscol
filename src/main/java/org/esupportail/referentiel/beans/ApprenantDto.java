package org.esupportail.referentiel.beans;

/**
 * 
 */
public class ApprenantDto {

	private String codEtu;
	private String nom;
	private String prenom;
	private String dateNaissance;
	private String numeroIne;
	private String mail;

	public String getCodEtu() {
		return codEtu;
	}

	public void setCodEtu(String codEtu) {
		this.codEtu = codEtu;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getNumeroIne() {
		return numeroIne;
	}

	public void setNumeroIne(String numeroIne) {
		this.numeroIne = numeroIne;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
