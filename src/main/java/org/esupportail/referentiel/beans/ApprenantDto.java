package org.esupportail.referentiel.beans;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(codEtu, dateNaissance, mail, nom, numeroIne, prenom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApprenantDto other = (ApprenantDto) obj;
		return Objects.equals(codEtu, other.codEtu) && Objects.equals(dateNaissance, other.dateNaissance)
				&& Objects.equals(mail, other.mail) && Objects.equals(nom, other.nom)
				&& Objects.equals(numeroIne, other.numeroIne) && Objects.equals(prenom, other.prenom);
	}
	
	

}
