package org.esupportail.referentiel.beans;

import java.io.Serializable;


/**
 * @author dhouillo
 *
 */
public class EtapeInscription implements Serializable {

	/**
	 * 
	 */
	public EtapeInscription() {
		super();

	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * codeComposante.
	 */
	private String codeComposante = "";
	
	/**
	 * LibComposante.
	 */
	private String libComposante = "";
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
	
	/**
	 * typeIns (A = administrative, P = pedagogie).
	 */
	private String typeIns = "";
	
	/**
	 * codeDiplome.
	 */
	private String codeDiplome = "";
	
	/**
	 * versionDiplome.
	 */
	private String versionDiplome = "";
	
	/**
	 * libDiplome
	 */
	private String libDiplome = "";
	
	/**
	 * codCursusLmd.
	 */
	private String codCursusLmd = "";
	
	/**
	 * codFinalite.
	 */
	private String codFinalite = "";
	
	/**
	 * libFinalite.
	 */
	private String libFinalite = "";

	/**
	 * volumeHoraire.
	 */
	private String volumeHoraire = "";
	
	private String codeCursusAmenage="";
	
	private String libelleCursusAmenage="";
	
	private String regimeIns;
	
	private String libRg;

	/**
	 * @return the codeEtp
	 */
	public String getCodeEtp() {
		return codeEtp;
	}
	/**
	 * @param codeEtp the codeEtp to set
	 */
	public void setCodeEtp(final String codeEtp) {
		this.codeEtp = codeEtp;
	}
	/**
	 * @return the codVrsVet
	 */
	public String getCodVrsVet() {
		return codVrsVet;
	}
	/**
	 * @param codVrsVet the codVrsVet to set
	 */
	public void setCodVrsVet(final String codVrsVet) {
		this.codVrsVet = codVrsVet;
	}
	/**
	 * @return the libWebVet
	 */
	public String getLibWebVet() {
		return LibWebVet;
	}
	/**
	 * @param libWebVet the libWebVet to set
	 */
	public void setLibWebVet(final String libWebVet) {
		LibWebVet = libWebVet;
	}
	/**
	 * @return the codeComposante
	 */
	public String getCodeComposante() {
		return codeComposante;
	}
	/**
	 * @param codeComposante the codeComposante to set
	 */
	public void setCodeComposante(final String codeComposante) {
		this.codeComposante = codeComposante;
	}
	/**
	 * @return the typeIns
	 */
	public String getTypeIns() {
		return typeIns;
	}
	/**
	 * @param typeIns the typeIns to set
	 */
	public void setTypeIns(final String typeIns) {
		this.typeIns = typeIns;
	}
	/**
	 * @return the libComposante
	 */
	public String getLibComposante() {
		return libComposante;
	}
	/**
	 * @param libComposante the libComposante to set
	 */
	public void setLibComposante(final String libComposante) {
		this.libComposante = libComposante;
	}
	/**
	 * @return the codeDiplome
	 */
	public String getCodeDiplome() {
		return codeDiplome;
	}
	/**
	 * @param codeDiplome the codeDiplome to set
	 */
	public void setCodeDiplome(final String codeDiplome) {
		this.codeDiplome = codeDiplome;
	}
	/**
	 * @return the versionDiplome
	 */
	public String getVersionDiplome() {
		return versionDiplome;
	}
	/**
	 * @param versionDiplome the versionDiplome to set
	 */
	public void setVersionDiplome(final String versionDiplome) {
		this.versionDiplome = versionDiplome;
	}
	/**
	 * @return the libDiplome
	 */
	public String getLibDiplome() {
		return libDiplome;
	}
	/**
	 * @param libDiplome the libDiplome to set
	 */
	public void setLibDiplome(final String libDiplome) {
		this.libDiplome = libDiplome;
	}
	/**
	 * @return the codCursusLmd
	 */
	public String getCodCursusLmd() {
		return codCursusLmd;
	}
	/**
	 * @param codCursusLmd the codCursusLmd to set
	 */
	public void setCodCursusLmd(String codCursusLmd) {
		this.codCursusLmd = codCursusLmd;
	}
	/**
	 * @return the codFinalite
	 */
	public String getCodFinalite() {
		return codFinalite;
	}
	/**
	 * @param codFinalite the codFinalite to set
	 */
	public void setCodFinalite(String codFinalite) {
		this.codFinalite = codFinalite;
	}
	/**
	 * @return the libFinalite
	 */
	public String getLibFinalite() {
		return libFinalite;
	}
	/**
	 * @param libFinalite the libFinalite to set
	 */
	public void setLibFinalite(String libFinalite) {
		this.libFinalite = libFinalite;
	}

	public String getVolumeHoraire() {
		return volumeHoraire;
	}

	public void setVolumeHoraire(String volumeHoraire) {
		this.volumeHoraire = volumeHoraire;
	}
	/* ***************************************************************
	 * MÃ©thodes
	 ****************************************************************/
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codeEtp == null) ? 0 : codeEtp.hashCode());
		result = prime * result + ((codVrsVet == null) ? 0 : codVrsVet.hashCode());
		return result;
	}
	

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EtapeInscription other = (EtapeInscription) obj;
		if (codeEtp == null) {
			if (other.codeEtp != null)
				return false;
		} else if (!codeEtp.equals(other.codeEtp))
			return false;
		if (codVrsVet == null) {
			if (other.codVrsVet != null)
				return false;
		} else if (!codVrsVet.equals(other.codVrsVet))
			return false;
		return true;
	}
	public String getLibelleCursusAmenage() {
		return libelleCursusAmenage;
	}
	public void setLibelleCursusAmenage(String libelleCursusAmenage) {
		this.libelleCursusAmenage = libelleCursusAmenage;
	}
	public String getCodeCursusAmenage() {
		return codeCursusAmenage;
	}
	public void setCodeCursusAmenage(String codeCursusAmenage) {
		this.codeCursusAmenage = codeCursusAmenage;
	}
	public String getRegimeIns() {
		return regimeIns;
	}
	public void setRegimeIns(String regimeIns) {
		this.regimeIns = regimeIns;
	}
	public String getLibRg() {
		return libRg;
	}
	public void setLibRg(String libRg) {
		this.libRg = libRg;
	}
	
	

}
