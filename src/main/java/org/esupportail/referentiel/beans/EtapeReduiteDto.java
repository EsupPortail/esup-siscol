package org.esupportail.referentiel.beans;

import java.util.Objects;

public class EtapeReduiteDto {

	
	/**
	 * code etape.
	 */
	private String codeEtp = "";
	/**
	 * code version etape.
	 */
	private String codVrsVet = "";
	/**
	 * libelle Web Vet.
	 */
	private String LibWebVet = "";
	public String getCodeEtp() {
		return codeEtp;
	}
	public void setCodeEtp(String codeEtp) {
		this.codeEtp = codeEtp;
	}
	public String getCodVrsVet() {
		return codVrsVet;
	}
	public void setCodVrsVet(String codVrsVet) {
		this.codVrsVet = codVrsVet;
	}
	public String getLibWebVet() {
		return LibWebVet;
	}
	public void setLibWebVet(String libWebVet) {
		LibWebVet = libWebVet;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(codVrsVet, codeEtp);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EtapeReduiteDto other = (EtapeReduiteDto) obj;
		return Objects.equals(codVrsVet, other.codVrsVet) && Objects.equals(codeEtp, other.codeEtp);
	}
	
	
}
