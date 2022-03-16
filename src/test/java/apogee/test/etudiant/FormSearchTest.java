package apogee.test.etudiant;

import java.io.Serializable;

public class FormSearchTest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5955292894205654240L;

	private String id;

	private String nom;

	private String mail;

	private String prenom;

	private String primaryAffiliation;

	private String affiliation;
	
	private String supannAliasLogin;
	
	public FormSearchTest(String id, String supannAliasLogin,String nom, String mail, String prenom, String primaryAffiliation,
			String affiliation) {
		super();
		this.supannAliasLogin=supannAliasLogin;
		this.id = id;
		this.nom = nom;
		this.mail = mail;
		this.prenom = prenom;
		this.primaryAffiliation = primaryAffiliation;
		this.affiliation = affiliation;
	}

	public FormSearchTest(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPrimaryAffiliation() {
		return primaryAffiliation;
	}

	public void setPrimaryAffiliation(String primaryAffiliation) {
		this.primaryAffiliation = primaryAffiliation;
	}

	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	public String getSupannAliasLogin() {
		return supannAliasLogin;
	}

	public void setSupannAliasLogin(String supannAliasLogin) {
		this.supannAliasLogin = supannAliasLogin;
	}
	
	

}
