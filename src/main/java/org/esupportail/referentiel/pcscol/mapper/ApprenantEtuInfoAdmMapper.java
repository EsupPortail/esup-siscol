package org.esupportail.referentiel.pcscol.mapper;

import org.esupportail.referentiel.beans.ElementPedagogique;
import org.esupportail.referentiel.beans.EtapeInscription;
import org.esupportail.referentiel.beans.EtudiantInfoAdm;
import org.esupportail.referentiel.beans.EtudiantRef;
import org.esupportail.referentiel.pcscol.model.sta.Apprenant;
import org.esupportail.referentiel.pcscol.model.sta.Inscription;
import org.esupportail.referentiel.pcscol.model.sta.Stage;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ApprenantEtuInfoAdmMapper {

	/**
	 * 
	 * @param app
	 * @return
	 */

	ApprenantEtuInfoAdmMapper Instance = Mappers.getMapper(ApprenantEtuInfoAdmMapper.class);

	/**
	 * 
	 * @param app
	 * @return
	 */
	@Mapping(target = "nomUsuel", source = "nomUsuel")
	@Mapping(target = "prenom1", source = "prenom")
	@Mapping(target = "nomPatronymique", source = "nomDeNaissance")
	@Mapping(target = "dateNaissance", source = "dateDeNaissance", dateFormat = "dd-MM-yyyy")
	@Mapping(target = "numEtu", source = "codeApprenant")
	@Mapping(target = "numeroINE", source = "ine")
	@Mapping(target = "codInd", source = "codeApprenant")
	@Mapping(target = "sexe", source = "genre")
	public EtudiantInfoAdm apprenantToEtudiantInfoAdm(Apprenant app);

	/**
	 * 
	 * @param app
	 * @return
	 */
	@Mapping(target = "cod_ind", source = "codeApprenant")
	@Mapping(target = "nommarital", source = "nomUsuel")
	@Mapping(target = "nompatro", source = "nomDeNaissance")
	@Mapping(target = "prenom", source = "prenom")
	@Mapping(target = "dateNais", source = "dateDeNaissance", dateFormat = "dd-MM-yyyy")
	@Mapping(target = "libAd1", source = "ligne3OuVoie")
	@Mapping(target = "libAd2", source = "ligne4OuComplement")
	@Mapping(target = "libAde", source = "ligne5Etranger")
	@Mapping(target = "postalCode", source = "codePostal")
	@Mapping(target = "town", source = "commune")
	@Mapping(target = "country", source = "pays")
	@Mapping(target = "codeSexe", source = "genre")
	@Mapping(target = "phone", source = "telephone")
	@Mapping(target = "portablePhone", source = "telephone")
	public EtudiantRef apprenantToEtudiantRef(Apprenant app);

	@AfterMapping
	default void setMainAdress(@MappingTarget EtudiantRef etuRef, Apprenant app) {

		StringBuffer addr = new StringBuffer();
		if (app.getLigne1OuEtage() != null)
			addr.append(app.getLigne1OuEtage()+ ", ");
		if (app.getLigne2OuBatiment() != null)
			addr.append(app.getLigne2OuBatiment()+", ");
		if (app.getLigne3OuVoie() != null)
			addr.append(app.getLigne3OuVoie()+", ");
		if (app.getLigne4OuComplement() != null)
			addr.append(app.getLigne4OuComplement());

		etuRef.setMainAddress(addr.toString());

	}

	/**
	 * mapp Stage.codeChemin, Stage.supportStageId, Stage.libelleAffichage,
	 * Stage.ects
	 * 
	 * @param stage
	 * @return
	 */
	@Mapping(target = "codElp", source = "codeChemin")
	@Mapping(target = "codEtp", source = "supportStageId")
	@Mapping(target = "libElp", source = "libelleAffichage")
	@Mapping(target = "nbrCrdElp", source = "ects")
	public ElementPedagogique stagesApprenantToElementPedagogique(Stage stage);

	/**
	 * 
	 * @param inscription
	 * @return
	 */
	@Mapping(target = "codeEtp", source = "inscription.supportInscription.supportInscriptionId")
	@Mapping(target = "codeDiplome", source = "inscription.supportInscription.codeChemin")
	@Mapping(target = "libWebVet", source = "inscription.supportInscription.libelleAffichage")
	@Mapping(target = "regimeIns", source = "inscription.supportInscription.codeRegimeInscription",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "libRg", source = "inscription.supportInscription.libelleRegimeInscription",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	public EtapeInscription stagesApprenantToEtapeInscription(Inscription inscription);

}
