package org.esupportail.referentiel.pcscol.jdbc.repository;

import java.util.List;
import java.util.UUID;

import org.esupportail.referentiel.pcscol.jdbc.model.schema_odf.Espace;

public interface EspaceRepository extends OdfRepository<Espace, UUID>{
	
	public Espace findOne(UUID id);
	
	public List<Espace> findAll(Boolean temoin_active);
	
	public List<Espace> findByCode(String code);
	
	public List<Espace> findByCodeStructure(String code_structure);
	
	public List<Espace> findByAnneeUniversitaire(Integer annee_universitaire);
	
	
	
}
