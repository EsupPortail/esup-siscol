package org.esupportail.referentiel.ldap.entities;

import java.io.Serializable;
import java.util.List;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entry( objectClasses = { "inetOrgPerson"})
public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9201637130740729137L;

	@JsonIgnore
	@Id
	private Name dn;

	private String uid;
	
	private String supannAliasLogin;
	
	@Attribute(name = "uid")
	private String codEtu;
	
	private String supannEtuId;
	
	private List<String> sn;
		
	@Attribute(name = "mail")
	private String mail;
	
	private String supannAutreMail;

	private String cn;

	private List<String> givenName;

	private String displayName;

	private String eduPersonPrimaryAffiliation;

	private List<String> eduPersonAffiliation;
	
	private List<String> supannEntiteAffectation;
	
	
	private String supannEntiteAffectationPrincipale;
	
	private String supannCivilite;
	
	private String telephoneNumber;

	
	
	@JsonIgnore
	private String supannEmpId;
	@JsonIgnore
	private String supannRefId;

	

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public List<String> getSn() {
		return sn;
	}

	public void setSn(List<String> sn) {
		this.sn = sn;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public List<String> getGivenName() {
		return givenName;
	}

	public void setGivenName(List<String> givenName) {
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

	public List<String> getEduPersonAffiliation() {
		return eduPersonAffiliation;
	}

	public void setEduPersonAffiliation(List<String> eduPersonAffiliation) {
		this.eduPersonAffiliation = eduPersonAffiliation;
	}

	public String getSupannEntiteAffectationPrincipale() {
		return supannEntiteAffectationPrincipale;
	}

	public void setSupannEntiteAffectationPrincipale(String supannEntiteAffectationPrincipale) {
		this.supannEntiteAffectationPrincipale = supannEntiteAffectationPrincipale;
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

	public Name getDn() {
		return dn;
	}

	public void setDn(Name dn) {
		this.dn = dn;
	}

	@Override
	public String toString() {
		return "Person [dn=" + dn + ", uid=" + uid + ", sn=" + sn + ", mail="
				+ mail + ", cn=" + cn + ", givenName=" + givenName + ", displayName=" + displayName
				+ ", eduPersonPrimaryAffiliation=" + eduPersonPrimaryAffiliation + ", eduPersonAffiliation="
				+ eduPersonAffiliation + ", supannEntiteAffectationPrincipale=" + supannEntiteAffectationPrincipale
				+ ", supannEmpId=" + supannEmpId + ", supannRefId=" + supannRefId + "]";
	}

	public String getCodEtu() {
		return codEtu;
	}

	public void setCodEtu(String codEtu) {
		this.codEtu = codEtu;
	}

	
	public String getSupannAutreMail() {
		return supannAutreMail;
	}

	public void setSupannAutreMail(String supannAutreMail) {
		this.supannAutreMail = supannAutreMail;
	}

	public String getSupannAliasLogin() {
		return supannAliasLogin;
	}

	public void setSupannAliasLogin(String supannAliasLogin) {
		this.supannAliasLogin = supannAliasLogin;
	}

	public String getSupannEtuId() {
		return supannEtuId;
	}

	public void setSupannEtuId(String supannEtuId) {
		this.supannEtuId = supannEtuId;
	}

	public List<String> getSupannEntiteAffectation() {
		return supannEntiteAffectation;
	}

	public void setSupannEntiteAffectation(List<String> supannEntiteAffectation) {
		this.supannEntiteAffectation = supannEntiteAffectation;
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
	
	
	
	

}
