package org.esupportail.referentiel.pcscol.mapper;

import java.util.List;

import org.esupportail.referentiel.beans.ElementPedagogique;
import org.esupportail.referentiel.beans.EtudiantInfoAdm;
import org.esupportail.referentiel.beans.EtudiantRef;
import org.esupportail.referentiel.pcscol.model.sta.Apprenant;
import org.esupportail.referentiel.pcscol.model.sta.Inscription;
import org.esupportail.referentiel.pcscol.model.sta.Stage;
import org.esupportail.referentiel.pcscol.model.sta.StagesApprenant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ApprenantEtuInfoAdmMapper {
	/**
	 * private String handicap;
	
	private String numEtu;
	private String codEtu;
	private String codInd;
	private String emailAnnuaire; 
	private String loginAnnuaire;
	
	private String numBoursier;
	private String numeroINE;
	
	private String nomUsuel;
	private String prenom1;
	private String prenom2;
	private String nomPatronymique;
	
	private String nationaliteDTO;
	
	private String dateNaissance;
	private String libVilleNaissance;
	private String departementNaissance;
	
	private String paysNaissance;
	
	private String sexe;
	private String situationFamiliale;
	private String situationMilitaire;
	
	private String listeBacs;
	private String listeBlocages;

	private String anneePremiereInscEnsSup;
	private String anneePremiereInscEtb;
	private String anneePremiereInscEtr;
	private String anneePremiereInscUniv;
	private String etbPremiereInscUniv;
	private String temoinDateNaissEstimee;
	private String temoinSitMilEnRegle;
	// libelle CPAM etudiant
	private String libelleCPAM = "";
	
	
	
	
  Apprenant.CODE_APPRENANT,
  Apprenant.INE,
  Apprenant.NOM_DE_NAISSANCE,
  Apprenant.NOM_USUEL,
  Apprenant.PRENOM,
  Apprenant.GENRE,
  Apprenant.DATE_DE_NAISSANCE,
  Apprenant.PAYS,
  Apprenant.LIGNE1_OU_ETAGE,
  Apprenant.LIGNE2_OU_BATIMENT,
  Apprenant.LIGNE3_OU_VOIE,
  Apprenant.LIGNE4_OU_COMPLEMENT,
  Apprenant.LIGNE5_ETRANGER,
  Apprenant.CODE_POSTAL,
  Apprenant.COMMUNE,
  Apprenant.MAIL,
  Apprenant.TELEPHONE
	
	 */
	
	
	/**
	 * 
	 * @param app
	 * @return
	 */
	
	
	ApprenantEtuInfoAdmMapper Instance=Mappers.getMapper(ApprenantEtuInfoAdmMapper.class);
	
	@Mapping(target = "nomUsuel", source = "nomUsuel")
    @Mapping(target = "prenom1", source = "prenom")
	@Mapping(target = "nomPatronymique", source="nomDeNaissance")
	@Mapping(target="dateNaissance",source = "dateDeNaissance",dateFormat = "dd-MM-yyyy")
	@Mapping(target="numEtu",source="codeApprenant")
	@Mapping(target="numeroINE",source="ine")
	@Mapping(target="codInd",source="codeApprenant")
	@Mapping(target="sexe",source="genre")
	public EtudiantInfoAdm apprenantToEtudiantInfoAdm(Apprenant app);
	
	
	

	@Mapping(target = "nommarital", source = "nomUsuel")
    @Mapping(target = "prenom", source = "prenom")
	@Mapping(target = "nompatro", source="nomDeNaissance")
	@Mapping(target="libAd1",source = "ligne1OuEtage")
	//@Mapping(target="libAd1",source = "ligne4OuComplement")
	@Mapping(target="libAd2",source = "ligne2OuBatiment")
	@Mapping(target="libAd3",source = "ligne3OuVoie")
	@Mapping(target="libAde",source="ligne5Etranger")
	@Mapping(target="postalCode",source="codePostal")
	@Mapping(target="town",source="commune")
	@Mapping(target="country",source="pays")
	@Mapping(target="codeSexe",source="genre")
	public EtudiantRef apprenantToEtudiantRef(Apprenant app);
	

	@Mapping(target = "nommarital", source = "stagesApprenant.apprenant.nomUsuel")
    @Mapping(target = "prenom", source = "stagesApprenant.apprenant.prenom")
	@Mapping(target = "nompatro", source="stagesApprenant.apprenant.nomDeNaissance")
	@Mapping(target="libAd1",source = "stagesApprenant.apprenant.ligne1OuEtage")
	//@Mapping(target="libAd1",source = "ligne4OuComplement")
	@Mapping(target="libAd2",source = "stagesApprenant.apprenant.ligne2OuBatiment")
	@Mapping(target="libAd3",source = "stagesApprenant.apprenant.ligne3OuVoie")
	@Mapping(target="libAde",source="stagesApprenant.apprenant.ligne5Etranger")
	@Mapping(target="postalCode",source="stagesApprenant.apprenant.codePostal")
	@Mapping(target="town",source="stagesApprenant.apprenant.commune")
	@Mapping(target="country",source="stagesApprenant.apprenant.pays")
	@Mapping(target="codeSexe",source="stagesApprenant.apprenant.genre")
	public EtudiantRef stagesApprenantToEtudiantRef(StagesApprenant stagesApprenant);
	
	
	
	
	/**
	 *mapp  Stage.codeChemin,
  			Stage.supportStageId,
  			Stage.libelleAffichage,
  			Stage.ects
	 * 
	 * @param stage
	 * @return
	 */
	@Mapping(target="codElp",source = "codeChemin")
	@Mapping(target="codEtp",source = "supportStageId")
	@Mapping(target="libElp",source = "libelleAffichage")
	//@Mapping(target="nbrCrdElp",source = "ects")
	public ElementPedagogique stagesApprenantToElementPedagogique(Stage stage);

}
