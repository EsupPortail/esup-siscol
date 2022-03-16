package org.esupportail.referentiel.beans;

import java.io.Serializable;

public class RegimeInscription implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6666184934815282825L;

	private String regimeIns;
	
	private String libRg;
	
	private String annee;
	
	private String codRegIns;
	
	private String licRegIns;

	public RegimeInscription() {
		super();
	}

	public RegimeInscription(String regimeIns, String libRg) {
		super();
		this.regimeIns = regimeIns;
		this.libRg = libRg;
	}
	
	

	public RegimeInscription(String regimeIns, String libRg, String annee) {
		super();
		this.regimeIns = regimeIns;
		this.libRg = libRg;
		this.annee = annee;
	}

	public String getLibRg() {
		return libRg;
	}

	public void setLibRg(String libRg) {
		this.libRg = libRg;
	}

	public String getRegimeIns() {
		return regimeIns;
	}

	public void setRegimeIns(String regimeIns) {
		this.regimeIns = regimeIns;
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public String getCodRegIns() {
		return codRegIns;
	}

	public void setCodRegIns(String codRegIns) {
		this.codRegIns = codRegIns;
	}

	public String getLicRegIns() {
		return licRegIns;
	}

	public void setLicRegIns(String licRegIns) {
		this.licRegIns = licRegIns;
	}
	
	
	
	
	
	

}
