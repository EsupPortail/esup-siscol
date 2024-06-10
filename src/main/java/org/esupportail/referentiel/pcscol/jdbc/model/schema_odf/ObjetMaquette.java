package org.esupportail.referentiel.pcscol.jdbc.model.schema_odf;

import java.util.UUID;

public class ObjetMaquette {

	private UUID id;// uuid NOT NULL,
	
	private String code;// varchar(30) NOT NULL,
	
	private UUID id_espace;

	private String type_objet_maquette;// varchar(6) NOT NULL, O F

	private String code_structure;// varchar(6) NOT NULL,

	private String code_structure_principale;// varchar(6) NULL,
	
	private String description;// varchar(2000) NULL,

	private String libelle_court;// varchar(50) NOT NULL,

	private String libelle_long;// varchar(150) NOT NULL,

	private Boolean temoin_stage;// bool NULL,

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public UUID getId_espace() {
		return id_espace;
	}

	public void setId_espace(UUID id_espace) {
		this.id_espace = id_espace;
	}

	public String getType_objet_maquette() {
		return type_objet_maquette;
	}

	public void setType_objet_maquette(String type_objet_maquette) {
		this.type_objet_maquette = type_objet_maquette;
	}

	public String getCode_structure() {
		return code_structure;
	}

	public void setCode_structure(String code_structure) {
		this.code_structure = code_structure;
	}

	public String getCode_structure_principale() {
		return code_structure_principale;
	}

	public void setCode_structure_principale(String code_structure_principale) {
		this.code_structure_principale = code_structure_principale;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLibelle_court() {
		return libelle_court;
	}

	public void setLibelle_court(String libelle_court) {
		this.libelle_court = libelle_court;
	}

	public String getLibelle_long() {
		return libelle_long;
	}

	public void setLibelle_long(String libelle_long) {
		this.libelle_long = libelle_long;
	}

	public Boolean getTemoin_stage() {
		return temoin_stage;
	}

	public void setTemoin_stage(Boolean temoin_stage) {
		this.temoin_stage = temoin_stage;
	}
	
	
	
	
	
	
	
	
	

}
