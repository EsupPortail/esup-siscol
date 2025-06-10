package org.esupportail.referentiel.beans;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * @author dhouillo
 *
 */
public class ElementPedagogique implements Serializable{

	/**
	 * 
	 */
	public ElementPedagogique() {
		super();

	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * code etape.
	 */
	private String codEtp = "";
	
	
	private String codePeriode = "";
	/**
	 * code version etape.
	 */
	private String codVrsVet = "";
	/**
	 * code element pedagogique
	 */
	private String codElp = "";
	
	/**
	 * libelle elp.
	 */
	private String libElp = "";
	/**
	 * temoin element pedagogique.
	 */
	private String temElpTypeStage = "";
	/**
	 * nombre de credit elp.
	 */
	private BigDecimal nbrCrdElp = new BigDecimal(0);
	
	
	private String LibNatureElp;
	/**
	 * @return the codEtp
	 */
	public String getCodEtp() {
		return codEtp;
	}
	/**
	 * @param codEtp the codEtp to set
	 */
	public void setCodEtp(String codEtp) {
		this.codEtp = codEtp;
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
	public void setCodVrsVet(String codVrsVet) {
		this.codVrsVet = codVrsVet;
	}
	/**
	 * @return the codElp
	 */
	public String getCodElp() {
		return codElp;
	}
	/**
	 * @param codElp the codElp to set
	 */
	public void setCodElp(String codElp) {
		this.codElp = codElp;
	}
	/**
	 * @return the libElp
	 */
	public String getLibElp() {
		return libElp;
	}
	/**
	 * @param libElp the libElp to set
	 */
	public void setLibElp(String libElp) {
		this.libElp = libElp;
	}
	/**
	 * @return the temElpTypeStage
	 */
	public String getTemElpTypeStage() {
		return temElpTypeStage;
	}
	/**
	 * @param temElpTypeStage the temElpTypeStage to set
	 */
	public void setTemElpTypeStage(String temElpTypeStage) {
		this.temElpTypeStage = temElpTypeStage;
	}
	/**
	 * @return the nbrCrdElp
	 */
	public BigDecimal getNbrCrdElp() {
		return nbrCrdElp;
	}
	/**
	 * @param nbrCrdElp the nbrCrdElp to set
	 */
	public void setNbrCrdElp(BigDecimal nbrCrdElp) {
		this.nbrCrdElp = nbrCrdElp;
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
		result = prime * result + ((codElp == null) ? 0 : codElp.hashCode());
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
		ElementPedagogique other = (ElementPedagogique) obj;
		if (codElp == null) {
			if (other.codElp != null)
				return false;
		} else if (!codElp.equals(other.codElp))
			return false;
		return true;
	}
	public String getLibNatureElp() {
		return LibNatureElp;
	}
	public void setLibNatureElp(String libNatureElp) {
		LibNatureElp = libNatureElp;
	}
	@Override
	public String toString() {
		return "ElementPedagogique [codEtp=" + codEtp + ", codVrsVet=" + codVrsVet + ", codElp=" + codElp + ", libElp="
				+ libElp + ", temElpTypeStage=" + temElpTypeStage + ", nbrCrdElp=" + nbrCrdElp + ", LibNatureElp="
				+ LibNatureElp + "]";
	}
	public String getCodePeriode() {
		return codePeriode;
	}
	public void setCodePeriode(String codePeriode) {
		this.codePeriode = codePeriode;
	}

	
	

}
