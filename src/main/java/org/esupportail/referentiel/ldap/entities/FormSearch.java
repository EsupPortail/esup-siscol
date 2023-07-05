package org.esupportail.referentiel.ldap.entities;

import java.io.Serializable;

import javax.persistence.Transient;

import org.esupportail.referentiel.conf.LdapAttributesConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.ldap.filter.LikeFilter;
import org.springframework.ldap.filter.WhitespaceWildcardsFilter;

import com.fasterxml.jackson.annotation.JsonIgnore;


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
	
	private String supannEtuEtapeVersion;
	
	@JsonIgnore
	private String supannEtuEtapePrefix;

	@JsonIgnore
	private String supannEtuEtapeSepVetVersion;
	
	@JsonIgnore
	private boolean supannEtuEtapeExisteVetVersion;
	
	
	
	

	private String supannEntiteAffectation;

	private String supannEtuAnneeInscription;

	private String supannEtuInscription;

	public FormSearch() {
		super();

	}

	public FormSearch(String id, String supannAliasLogin, String nom, String mail, String prenom,
			String primaryAffiliation, String affiliation, String supannEtuEtape, String supannEntiteAffectation) {
		super();
		this.supannAliasLogin = supannAliasLogin;
		this.id = id;
		this.nom = nom;
		this.mail = mail;
		this.prenom = prenom;
		this.primaryAffiliation = primaryAffiliation;
		this.affiliation = affiliation;
		this.supannEtuEtape = supannEtuEtape;
		this.supannEntiteAffectation = supannEntiteAffectation;

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
			
			
			String codeEtapeLdap = supannEtuEtape;
			
			
			if (supannEtuEtapeExisteVetVersion) {
				if (supannEtuEtapeVersion != null && !supannEtuEtapeVersion.isEmpty() && !supannEtuEtapeSepVetVersion.isBlank()) {
					if (this.supannEtuEtapeSepVetVersion != null && !this.supannEtuEtapeSepVetVersion.isEmpty()
							&& !this.supannEtuEtapeSepVetVersion.isBlank()) {
						codeEtapeLdap = codeEtapeLdap + this.supannEtuEtapeSepVetVersion;
					}
					
					codeEtapeLdap = codeEtapeLdap + supannEtuEtapeVersion;
				}
				
			}
			if (this.supannEtuEtapePrefix != null && !this.supannEtuEtapePrefix.isEmpty()
					&& !this.supannEtuEtapePrefix.isBlank()) {
				if (this.supannEtuEtapePrefix.equals("*")) {
					Filter query = new LikeFilter(ldapAtributes.getSupannEtuEtape(), "*"+codeEtapeLdap);
					filter.and(query);
				}
					
			} else {
				codeEtapeLdap=this.supannEtuEtapePrefix+codeEtapeLdap;
				
				Filter query = new EqualsFilter(ldapAtributes.getSupannEtuEtape(), codeEtapeLdap);
				filter.and(query);
			}
		
			
		}
		if (supannEntiteAffectation != null && !supannEntiteAffectation.isEmpty()) {
			Filter query = new EqualsFilter(ldapAtributes.getSupannEntiteAffectation(), supannEntiteAffectation);
			filter.and(query);
		}
		if (supannEtuAnneeInscription != null && !supannEtuAnneeInscription.isEmpty()) {
			Filter query = new EqualsFilter(ldapAtributes.getSupannEtuAnneeInscription(), supannEtuAnneeInscription);
			filter.and(query);
		}

		if (supannEtuInscription != null && !supannEtuInscription.isEmpty()) {
			Filter query = new EqualsFilter(ldapAtributes.getSupannEtuInscription(), supannEtuInscription);
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

	public String getSupannEtuInscription() {
		return supannEtuInscription;
	}

	public void setSupannEtuInscription(String supannEtuInscription) {
		this.supannEtuInscription = supannEtuInscription;
	}

	public String getSupannEtuAnneeInscription() {
		return supannEtuAnneeInscription;
	}

	public void setSupannEtuAnneeInscription(String supannEtuAnneeInscription) {
		this.supannEtuAnneeInscription = supannEtuAnneeInscription;
	}

	public String getSupannEtuEtapeVersion() {
		return supannEtuEtapeVersion;
	}

	public void setSupannEtuEtapeVersion(String supannEtuEtapeVersion) {
		this.supannEtuEtapeVersion = supannEtuEtapeVersion;
	}

	public String getSupannEtuEtapePrefix() {
		return supannEtuEtapePrefix;
	}

	public void setSupannEtuEtapePrefix(String supannEtuEtapePrefix) {
		this.supannEtuEtapePrefix = supannEtuEtapePrefix;
	}

	public boolean isSupannEtuEtapeExisteVetVersion() {
		return supannEtuEtapeExisteVetVersion;
	}

	public void setSupannEtuEtapeExisteVetVersion(boolean supannEtuEtapeExisteVetVersion) {
		this.supannEtuEtapeExisteVetVersion = supannEtuEtapeExisteVetVersion;
	}

	public String getSupannEtuEtapeSepVetVersion() {
		return supannEtuEtapeSepVetVersion;
	}

	public void setSupannEtuEtapeSepVetVersion(String supannEtuEtapeSepVetVersion) {
		this.supannEtuEtapeSepVetVersion = supannEtuEtapeSepVetVersion;
	}

	@Override
	public String toString() {
		return "FormSearch [id=" + id + ", codEtu=" + codEtu + ", supannAliasLogin=" + supannAliasLogin + ", nom=" + nom
				+ ", mail=" + mail + ", prenom=" + prenom + ", primaryAffiliation=" + primaryAffiliation
				+ ", affiliation=" + affiliation + ", supannEtuEtape=" + supannEtuEtape + ", supannEtuEtapeVersion="
				+ supannEtuEtapeVersion + ", supannEtuEtapePrefix=" + supannEtuEtapePrefix
				+ ", supannEtuEtapeSepVetVersion=" + supannEtuEtapeSepVetVersion + ", supannEtuEtapeExisteVetVersion="
				+ supannEtuEtapeExisteVetVersion + ", supannEntiteAffectation=" + supannEntiteAffectation
				+ ", supannEtuAnneeInscription=" + supannEtuAnneeInscription + ", supannEtuInscription="
				+ supannEtuInscription + "]";
	}
	
}
