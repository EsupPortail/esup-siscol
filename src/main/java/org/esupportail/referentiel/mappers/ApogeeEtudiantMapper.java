package org.esupportail.referentiel.mappers;

import java.util.List;

import org.esupportail.referentiel.beans.EtudiantDTO2Ext;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import gouv.education.apogee.commun.client.ws.EtudiantMetier.EtudiantDTO2;

@Mapper
public interface ApogeeEtudiantMapper {
	ApogeeEtudiantMapper Instance =Mappers.getMapper(ApogeeEtudiantMapper.class);
	
	public EtudiantDTO2Ext etudiantDTO2ToEtudiantDTO2Ext(EtudiantDTO2 etudiant);
	
	public List<EtudiantDTO2Ext> etudiantDTO2ToEtudiantDTO2Ext(List<EtudiantDTO2> etudiants);

}
