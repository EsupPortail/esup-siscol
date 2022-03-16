package org.esupportail.referentiel.beans;

import java.io.Serializable;


/**
 * @author Danielle Martineau : danielle.martineau@univ-rennes1.fr
 *
 */
public class AdministrationApogee implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	/* ***************************************************************
	 * PropriÃ©tÃ©s
	 ****************************************************************/
	/**
	 * statusApogee
	 */
	private boolean statusApogee;
	
	/**
	 * raison
	 */
	private String raison;

		
	
	/* **
	 * Objets
	 */
	


	
	/**
	 * Constructeur
	 */
	public AdministrationApogee(){
		super();
	}


	
	/* ***************************************************************
	 * Getters / Setters
	 ****************************************************************/	


	/**
	 * @return the raison
	 */
	public String getRaison() {
		return raison;
	}



	/**
	 * @return the statusApogee
	 */
	public boolean isStatusApogee() {
		return statusApogee;
	}



	/**
	 * @param statusApogee the statusApogee to set
	 */
	public void setStatusApogee(boolean statusApogee) {
		this.statusApogee = statusApogee;
	}



	/**
	 * @param raison the raison to set
	 */
	public void setRaison(String raison) {
		this.raison = raison;
	}
	


	

}
