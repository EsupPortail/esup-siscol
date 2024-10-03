package org.esupportail.referentiel.pcscol.mapper;

import java.util.List;

import org.esupportail.referentiel.beans.DiplomeReduitDto;
import org.esupportail.referentiel.pcscol.odf.model.ObjetMaquetteDetail;
import org.esupportail.referentiel.pcscol.odf.model.ObjetMaquetteSummary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OdfDtoMapperInterface {

	OdfDtoMapperInterface Instance = Mappers.getMapper(OdfDtoMapperInterface.class);

	@Mapping(target = "codeDiplome", source = "code")
	@Mapping(target = "libDiplome", source = "libelle")
	@Mapping(target = "versionDiplome", source = "espaceLibelle")
	public DiplomeReduitDto diplomeReduitDtoFromObjetMaquetteSummary(ObjetMaquetteSummary objetMaquetteSummary);

	public List<DiplomeReduitDto> diplomeReduitDtoFromObjetMaquetteSummary(
			List<ObjetMaquetteSummary> objetMaquetteSummary);

	@Mapping(target = "codeDiplome", source = "code")
	@Mapping(target = "libDiplome", source = "descripteursObjetMaquette.libelle")
	@Mapping(target = "versionDiplome", source = "espace")
	public DiplomeReduitDto diplomeReduitDtoFromObjetMaquette(ObjetMaquetteDetail objetMaquetteSummary);

	public List<DiplomeReduitDto> diplomeReduitDtoFromObjetMaquette(List<ObjetMaquetteDetail> objetMaquetteSummary);

}
