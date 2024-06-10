package org.esupportail.referentiel.pcscol.jdbc.model.schema_odf;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Espace implements Serializable {
	
	private static final long serialVersionUID = -6186118429467552740L;

	private UUID id;
	
	private String type_espace;
	
	private String code_structure;
	
	private String code;
	
	private String libelle_court;
	
	private  String libelle_affichage;

	private  String libelle_long;
	
	private Integer version;
	
	private Integer annee_universitaire;
	
	private Boolean temoin_active;
	
	private Date date_debut_validite;
	
	private Date date_fin_validite;
	
	private Date date_consommation_nomenclatures;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getType_espace() {
		return type_espace;
	}

	public void setType_espace(String type_espace) {
		this.type_espace = type_espace;
	}

	public String getCode_structure() {
		return code_structure;
	}

	public void setCode_structure(String code_structure) {
		this.code_structure = code_structure;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle_court() {
		return libelle_court;
	}

	public void setLibelle_court(String libelle_court) {
		this.libelle_court = libelle_court;
	}

	public String getLibelle_affichage() {
		return libelle_affichage;
	}

	public void setLibelle_affichage(String libelle_affichage) {
		this.libelle_affichage = libelle_affichage;
	}

	public String getLibelle_long() {
		return libelle_long;
	}

	public void setLibelle_long(String libelle_long) {
		this.libelle_long = libelle_long;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getAnnee_universitaire() {
		return annee_universitaire;
	}

	public void setAnnee_universitaire(Integer annee_universitaire) {
		this.annee_universitaire = annee_universitaire;
	}

	public Boolean getTemoin_active() {
		return temoin_active;
	}

	public void setTemoin_active(Boolean temoin_active) {
		this.temoin_active = temoin_active;
	}

	public Date getDate_debut_validite() {
		return date_debut_validite;
	}

	public void setDate_debut_validite(Date date_debut_validite) {
		this.date_debut_validite = date_debut_validite;
	}

	public Date getDate_fin_validite() {
		return date_fin_validite;
	}

	public void setDate_fin_validite(Date date_fin_validite) {
		this.date_fin_validite = date_fin_validite;
	}

	public Date getDate_consommation_nomenclatures() {
		return date_consommation_nomenclatures;
	}

	public void setDate_consommation_nomenclatures(Date date_consommation_nomenclatures) {
		this.date_consommation_nomenclatures = date_consommation_nomenclatures;
	}
	
	
	
}
