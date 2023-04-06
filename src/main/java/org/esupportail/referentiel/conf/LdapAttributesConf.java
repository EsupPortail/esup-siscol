package org.esupportail.referentiel.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="app.ldap.attributes")
public class LdapAttributesConf {

	private String objectClass="Person";
	
	private String dn="dn";

	private String baseDn="ou";

	private String uid="uid";

	private String supannAliasLogin="supannAliasLogin";

	private String codEtu="supannEtuId";

	private String supannEtuId="supannEtuId";

	private String sn="sn";

	private String mail="mail";

	private String supannAutreMail="supannAutreMail";

	private String cn="cn";

	private String givenName="givenName";

	private String displayName="givenName";

	private String eduPersonPrimaryAffiliation="eduPersonPrimaryAffiliation";

	private String eduPersonAffiliation="eduPersonAffiliation";

	private String supannEntiteAffectation="supannEntiteAffectation";

	private String supannEntiteAffectationPrincipale="supannEntiteAffectationPrincipale";

	private String supannCivilite="supannCivilite";

	private String telephoneNumber="telephoneNumber";

	private String supannEtuCursusAnnee="supannEtuCursusAnnee";

	private String eduPersonOrgDN="eduPersonOrgDN";

	private String supannEmpId="supannEmpId";

	private String supannRefId="supannRefId";
	
	private String supannEtuEtape="supannEtuEtape";
	
	

	public String getObjectClass() {
		return objectClass;
	}

	public void setObjectClass(String objectClass) {
		this.objectClass = objectClass;
	}

	public String getBaseDn() {
		return baseDn;
	}

	public void setBaseDn(String baseDn) {
		this.baseDn = baseDn;
	}

	public String getDn() {
		return dn;
	}

	public void setDn(String dn) {
		this.dn = dn;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
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

	public String getSupannEtuId() {
		return supannEtuId;
	}

	public void setSupannEtuId(String supannEtuId) {
		this.supannEtuId = supannEtuId;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getSupannAutreMail() {
		return supannAutreMail;
	}

	public void setSupannAutreMail(String supannAutreMail) {
		this.supannAutreMail = supannAutreMail;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEduPersonPrimaryAffiliation() {
		return eduPersonPrimaryAffiliation;
	}

	public void setEduPersonPrimaryAffiliation(String eduPersonPrimaryAffiliation) {
		this.eduPersonPrimaryAffiliation = eduPersonPrimaryAffiliation;
	}

	public String getEduPersonAffiliation() {
		return eduPersonAffiliation;
	}

	public void setEduPersonAffiliation(String eduPersonAffiliation) {
		this.eduPersonAffiliation = eduPersonAffiliation;
	}

	public String getSupannEntiteAffectation() {
		return supannEntiteAffectation;
	}

	public void setSupannEntiteAffectation(String supannEntiteAffectation) {
		this.supannEntiteAffectation = supannEntiteAffectation;
	}

	public String getSupannEntiteAffectationPrincipale() {
		return supannEntiteAffectationPrincipale;
	}

	public void setSupannEntiteAffectationPrincipale(String supannEntiteAffectationPrincipale) {
		this.supannEntiteAffectationPrincipale = supannEntiteAffectationPrincipale;
	}

	public String getSupannCivilite() {
		return supannCivilite;
	}

	public void setSupannCivilite(String supannCivilite) {
		this.supannCivilite = supannCivilite;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getSupannEtuCursusAnnee() {
		return supannEtuCursusAnnee;
	}

	public void setSupannEtuCursusAnnee(String supannEtuCursusAnnee) {
		this.supannEtuCursusAnnee = supannEtuCursusAnnee;
	}

	public String getEduPersonOrgDN() {
		return eduPersonOrgDN;
	}

	public void setEduPersonOrgDN(String eduPersonOrgDN) {
		this.eduPersonOrgDN = eduPersonOrgDN;
	}

	public String getSupannEmpId() {
		return supannEmpId;
	}

	public void setSupannEmpId(String supannEmpId) {
		this.supannEmpId = supannEmpId;
	}

	public String getSupannRefId() {
		return supannRefId;
	}

	public void setSupannRefId(String supannRefId) {
		this.supannRefId = supannRefId;
	}

	public String getSupannEtuEtape() {
		return supannEtuEtape;
	}

	public void setSupannEtuEtape(String supannEtuEtape) {
		this.supannEtuEtape = supannEtuEtape;
	}

}
