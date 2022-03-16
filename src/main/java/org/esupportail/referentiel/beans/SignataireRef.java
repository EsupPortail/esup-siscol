package org.esupportail.referentiel.beans;

/**
 * @author dhouillo
 *
 */
public class SignataireRef {

	/**
	 * 
	 */
	public SignataireRef() {
		super();

	}

	/**
	 * nom du signataire de la composante
	 */
	private String nomSignataireComposante;
	/**
	 * qualite du signataire
	 */
	private String qualiteSignataire;
	/**
	 * @return the nomSignataireComposante
	 */
	public String getNomSignataireComposante() {
		return nomSignataireComposante;
	}
	/**
	 * @param nomSignataireComposante the nomSignataireComposante to set
	 */
	public void setNomSignataireComposante(String nomSignataireComposante) {
		this.nomSignataireComposante = nomSignataireComposante;
	}
	/**
	 * @return the qualiteSignataire
	 */
	public String getQualiteSignataire() {
		return qualiteSignataire;
	}
	/**
	 * @param qualiteSignataire the qualiteSignataire to set
	 */
	public void setQualiteSignataire(String qualiteSignataire) {
		this.qualiteSignataire = qualiteSignataire;
	}

	
	
}
