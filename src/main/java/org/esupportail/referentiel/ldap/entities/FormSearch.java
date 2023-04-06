package org.esupportail.referentiel.ldap.entities;

import java.io.Serializable;

import javax.persistence.Transient;

import org.esupportail.referentiel.conf.LdapAttributesConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.ldap.filter.WhitespaceWildcardsFilter;

public class FormSearch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3312963806949481413L;

	private String id;

	private String codEtu;

	private String supannAliasLogin;

	private String nom;

	private String mail;

	private String prenom;

	private String primaryAffiliation;

	private String affiliation;

	private String supannEtuEtape;

	private String supannEntiteAffectation;

	public FormSearch() {
		super();

	}

	public FormSearch(String id, String supannAliasLogin, String nom, String mail, String prenom,
			String primaryAffiliation, String affiliation, String supannEtuEtape, String supannEntiteAffectation ) {
		super();
		this.supannAliasLogin = supannAliasLogin;
		this.id = id;
		this.nom = nom;
		this.mail = mail;
		this.prenom = prenom;
		this.primaryAffiliation = primaryAffiliation;
		this.affiliation = affiliation;
		this.supannEtuEtape=supannEtuEtape;
		this.supannEntiteAffectation=supannEntiteAffectation;
		

	}

	public FormSearch(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;

	}

	

	public Filter formAsFliter(LdapAttributesConf ldapAtributes) {
		AndFilter filter = new AndFilter();

		if (id != null && !id.isEmpty()) {
			Filter query = new EqualsFilter(ldapAtributes.getUid(), id);
			filter.and(query);
		}
		if (codEtu != null && !codEtu.isEmpty()) {
			Filter query = new EqualsFilter(ldapAtributes.getCodEtu(), codEtu);
			filter.and(query);
		}

		if (supannAliasLogin != null && !supannAliasLogin.isEmpty()) {
			Filter query = new EqualsFilter(ldapAtributes.getSupannAliasLogin(), supannAliasLogin);
			filter.and(query);
		}

		if (nom != null && !nom.isEmpty()) {
			Filter query = new WhitespaceWildcardsFilter(ldapAtributes.getSn(), nom);
			filter.and(query);
		}
		if (mail != null && !mail.isEmpty()) {
			Filter query = new WhitespaceWildcardsFilter(ldapAtributes.getMail(), mail);
			filter.and(query);
		}
		if (prenom != null && !prenom.isEmpty()) {
			Filter query = new WhitespaceWildcardsFilter(ldapAtributes.getGivenName(), prenom);
			filter.and(query);
		}
		if (primaryAffiliation != null && !primaryAffiliation.isEmpty()) {
			Filter query = new EqualsFilter(ldapAtributes.getEduPersonPrimaryAffiliation(), primaryAffiliation);
			filter.and(query);
		}
		if (affiliation != null && !affiliation.isEmpty()) {
			Filter query = new EqualsFilter(ldapAtributes.getEduPersonAffiliation(), affiliation);
			filter.and(query);
		}

		if (supannEtuEtape != null && !supannEtuEtape.isEmpty()) {
			Filter query = new EqualsFilter(ldapAtributes.getSupannEtuEtape(), supannEtuEtape);
			filter.and(query);
		}
		if (supannEntiteAffectation != null && !supannEntiteAffectation.isEmpty()) {
			Filter query = new EqualsFilter(ldapAtributes.getSupannEntiteAffectation(), supannEntiteAffectation);
			filter.and(query);
		}

		return filter;
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

	public String getCodEtu() {
		return codEtu;
	}

	public void setCodEtu(String codEtu) {
		this.codEtu = codEtu;
	}

	public String getSupannEtuEtape() {
		return supannEtuEtape;
	}

	public void setSupannEtuEtape(String supannEtuEtape) {
		this.supannEtuEtape = supannEtuEtape;
	}

	public String getSupannEntiteAffectation() {
		return supannEntiteAffectation;
	}

	public void setSupannEntiteAffectation(String supannEntiteAffectation) {
		this.supannEntiteAffectation = supannEntiteAffectation;
	}

	@Override
	public String toString() {
		return "FormSearch [id=" + id + ", codEtu=" + codEtu + ", supannAliasLogin=" + supannAliasLogin + ", nom=" + nom
				+ ", mail=" + mail + ", prenom=" + prenom + ", primaryAffiliation=" + primaryAffiliation
				+ ", affiliation=" + affiliation + ", supannEtuEtape=" + supannEtuEtape + ", supannEntiteAffectation="
				+ supannEntiteAffectation + "]";
	}

}
