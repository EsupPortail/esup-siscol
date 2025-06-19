package org.esupportail.referentiel.beans;

import java.io.Serializable;
import java.util.Objects;

public class EtapeReduiteDto implements Serializable{

	
	private static final long serialVersionUID = 3416544090904851306L;
	/**
	 * code etape.
	 */
	private String codeEtp = "";
	/**
	 * code version etape.
	 */
	private String codVrsVet = "";
	
	private String codePeriode = "";
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
	public String getCodePeriode() {
		return codePeriode;
	}
	public void setCodePeriode(String codePeriode) {
		this.codePeriode = codePeriode;
	}
	
	
}
