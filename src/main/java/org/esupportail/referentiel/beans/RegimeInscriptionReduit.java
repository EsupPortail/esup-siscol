package org.esupportail.referentiel.beans;

public class RegimeInscriptionReduit {
	
	private String codeRegimeInscription;
	
	private String libelleRegimeInscription;
	
	private String libelleRegimeInscriptionCourt;
	
	public RegimeInscriptionReduit() {
		super();
	}
	
	public RegimeInscriptionReduit(String codeRegimeInscription, String libelleRegimeInscription,
			String libelleRegimeInscriptionCourt) {
		super();
		this.codeRegimeInscription = codeRegimeInscription;
		this.libelleRegimeInscription = libelleRegimeInscription;
		this.libelleRegimeInscriptionCourt = libelleRegimeInscriptionCourt;
	}
	
	public String getCodeRegimeInscription() {
		return codeRegimeInscription;
	}

	public void setCodeRegimeInscription(String codeRegimeInscription) {
		this.codeRegimeInscription = codeRegimeInscription;
	}
	
	public String getLibelleRegimeInscription() {
		return libelleRegimeInscription;
	}
	
	public void setLibelleRegimeInscription(String libelleRegimeInscription) {
		this.libelleRegimeInscription = libelleRegimeInscription;
	}
	
	public String getLibelleRegimeInscriptionCourt() {
		return libelleRegimeInscriptionCourt;
	}
	
	public void setLibelleRegimeInscriptionCourt(String libelleRegimeInscriptionCourt) {
		this.libelleRegimeInscriptionCourt = libelleRegimeInscriptionCourt;
	}
	
	
}
