package org.esupportail.referentiel.beans;

/**
 * @author dhouillo
 *
 */
public enum JoursHebdo {
	
	/**
	 * 
	 */
	/*
	 *************************** ENUM VALUES ******************************** */
	/**
	 * 
	 */
	jours6("6","6"),
	/**
	 * 
	 */
	jours5_5("5.5","5.5"),
	/**
	 * 
	 */
	jours5("5","5"),
	/**
	 * 
	 */
	jours4_5("4.5","4.5"),
	/**
	 * 
	 */
	jours4("4","4"),
	/**
	 * 
	 */
	jours3_5("3.5","3.5"),
	/**
	 * 
	 */
	jours3("3","3"),
	/**
	 * 
	 */
	jours2_5("2.5","2.5"),
	/**
	 * 
	 */
	jours2("2","2"),
	/**
	 * 
	 */
	jours1_5("1.5","1.5"),
	/**
	 * 
	 */
	jours1("1","1"),
	/**
	 * 
	 */
	jours0_5("0.5","0.5");
	
	/*
	 *************************** PROPERTIES ******************************** */

	/**
	 * valeur
	 */
	private String valeur;
	
	/**
	 * libelle
	 */
	private String libelle;
	
	/*
	 *************************** INIT ************************************** */

	/**
	 * @param libelle
	 * @param valeur
	 */
	JoursHebdo(String libelle, String valeur) {
		this.libelle = libelle;
		this.valeur = valeur;
	}

	/*
	 *************************** ACCESSORS ********************************* */

	/**
	 * @return the valeur
	 */
	public String getValeur() {
		return valeur;
	}

	/**
	 * @param valeur the valeur to set
	 */
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	
	
}
