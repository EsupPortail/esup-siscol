package org.esupportail.referentiel.pcscol.mapper;

import java.util.ArrayList;
import java.util.List;

import org.esupportail.referentiel.beans.ApogeeMap;
import org.esupportail.referentiel.beans.ApprenantDto;
import org.esupportail.referentiel.beans.EtapeInscription;
import org.esupportail.referentiel.beans.EtudiantInfoAdm;
import org.esupportail.referentiel.beans.EtudiantRef;
import org.esupportail.referentiel.beans.RegimeInscription;
import org.esupportail.referentiel.pcscol.ins.model.Apprenant;
import org.esupportail.referentiel.pcscol.ins.model.ApprenantEtInscriptions;
import org.esupportail.referentiel.pcscol.ins.model.ContactAdresseComplet;
import org.esupportail.referentiel.pcscol.ins.model.ContactComplet;
import org.esupportail.referentiel.pcscol.ins.model.ContactMelComplet;
import org.esupportail.referentiel.pcscol.ins.model.ContactTelephoneComplet;
import org.esupportail.referentiel.pcscol.ins.model.Inscription;
import org.esupportail.referentiel.pcscol.ins.model.InscriptionComplete;
import org.esupportail.referentiel.pcscol.ins.model.OccurrenceNomenclature;
import org.esupportail.referentiel.utils.DateMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mapper(uses = DateMapper.class, componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface ApprenantEtuInfoAdmMapperInterface {

	/**
	 * 
	 * @param app
	 * @return
	 */

	public ApprenantEtuInfoAdmMapperInterface Instance = Mappers.getMapper(ApprenantEtuInfoAdmMapperInterface.class);

	/**
	 * 
	 * @param inscription
	 * @return
	 */

	@Mapping(target = "nom", source = "etatCivil.nomDeNaissance")
	@Mapping(target = "prenom", source = "etatCivil.prenom")
	@Mapping(target = "codEtu", source = "meta.codeApprenant")
	@Mapping(target = "dateNaissance", source = "naissance.dateDeNaissance")
	@Mapping(target = "numeroIne", source = "bac.ine")
	public ApprenantDto inscrptionToApprenantDto(Inscription inscription);

	public List<ApprenantDto> inscrptionToApprenantDto(List<Inscription> inscription);

	/**
	 * 
	 * @param app
	 * @return
	 */
	@Mapping(target = "nomUsuel", source = "etatCivil.nomUsuel")
	@Mapping(target = "prenom1", source = "etatCivil.prenom")
	@Mapping(target = "prenom2", source = "etatCivil.deuxiemePrenom")
	@Mapping(target = "sexe", source = "etatCivil.genre")
	@Mapping(target = "nomPatronymique", source = "etatCivil.nomDeNaissance")
	@Mapping(target = "dateNaissance", source = "naissance.dateDeNaissance")
	@Mapping(target = "libVilleNaissance", expression = "java(app.getNaissance().getLibelleCommuneDeNaissance()!=null?app.getNaissance().getLibelleCommuneDeNaissance():app.getNaissance().getCommuneDeNaissanceEtranger())")
	@Mapping(target = "departementNaissance", source = "naissance.communeDeNaissance")
	@Mapping(target = "paysNaissance", source = "naissance.libellePaysDeNaissance")

	@Mapping(target = "nationaliteDTO", source = "naissance.libelleNationalite")
	@Mapping(target = "codEtu", source = "code")
	@Mapping(target = "numeroINE", source = "bac.ine")
	@Mapping(target = "codInd", source = "code")
	@Mapping(target = "numEtu", source = "code")
	// premieresInscriptions
	@Mapping(target = "anneePremiereInscEnsSup", source = "premieresInscriptions.anneeEnseignementSuperieur")
	@Mapping(target = "anneePremiereInscEtb", source = "premieresInscriptions.anneeEtablissement")
	// situationPersonnelle
	@Mapping(target = "situationFamiliale", source = "situationPersonnelle.situationFamiliale")
	public EtudiantInfoAdm apprenantToEtudiantInfoAdm(Apprenant app);

	/**
	 * 
	 * @param app
	 * @return
	 */
	@Mapping(target = "cod_ind", source = "code")
	@Mapping(target = "nommarital", source = "etatCivil.nomUsuel")
	@Mapping(target = "nompatro", source = "etatCivil.nomDeNaissance")
	@Mapping(target = "prenom", source = "etatCivil.prenom")
	@Mapping(target = "dateNais", source = "naissance.dateDeNaissance", dateFormat = "yyyy-MM-dd")
	@Mapping(target = "codeSexe", source = "etatCivil.genre")
	@Mapping(target = "sexEtatCivil", source = "etatCivil.genre")
	public EtudiantRef apprenantToEtudiantRef(Apprenant app);

	@AfterMapping
	default void setMainAdress(@MappingTarget EtudiantRef etuRef, Apprenant app) {

		StringBuffer addr = new StringBuffer();

		List<ContactComplet> contacts = app.getContacts();

		contacts.forEach(c -> {

			if (c.getCanalCommunication().getValue().equalsIgnoreCase("contactAdresseComplet")) {
				ContactAdresseComplet contactAdresseComplet = (ContactAdresseComplet) c;
				System.out.println("contactAdresseComplet: " + contactAdresseComplet);
				if (contactAdresseComplet.getCommune() != null) {
					etuRef.setTown(contactAdresseComplet.getCommune());
				}

				if (contactAdresseComplet.getLigne1OuEtage() != null
						&& !contactAdresseComplet.getLigne1OuEtage().isEmpty()) {
					addr.append(contactAdresseComplet.getLigne1OuEtage());
				}

				if (contactAdresseComplet.getLigne2OuBatiment() != null
						&& !contactAdresseComplet.getLigne2OuBatiment().isEmpty()) {
					if (addr.length() > 0) {
						addr.append(", ");
					}
					addr.append(contactAdresseComplet.getLigne2OuBatiment());
				}

				if (contactAdresseComplet.getLigne3OuVoie() != null
						&& !contactAdresseComplet.getLigne3OuVoie().isEmpty()) {
					if (addr.length() > 0) {
						addr.append(", ");
					}

					addr.append(contactAdresseComplet.getLigne3OuVoie());
					etuRef.setLibAd1(contactAdresseComplet.getLigne3OuVoie());
				}
				if (contactAdresseComplet.getLigne4OuComplement() != null && !contactAdresseComplet.getLigne4OuComplement().isEmpty()) {
					if (addr.length() > 0) {
						addr.append(", ");
					}
					addr.append(contactAdresseComplet.getLigne4OuComplement());
					etuRef.setLibAd2(contactAdresseComplet.getLigne4OuComplement());
				}
				etuRef.setLibAde(contactAdresseComplet.getLigne5Etranger());
				etuRef.setPostalCode(contactAdresseComplet.getCodePostal());
				etuRef.setTown(contactAdresseComplet.getLibelleCommune());
				etuRef.setCountry(contactAdresseComplet.getPays());
			}
			if (c.getCanalCommunication().getValue().equalsIgnoreCase("ContactTelephoneComplet")) {
				ContactTelephoneComplet contactTelephoneComplet = (ContactTelephoneComplet) c;
				etuRef.setPortablePhone(contactTelephoneComplet.getTelephone());
			}
			if (c.getCanalCommunication().getValue().equalsIgnoreCase("ContactMelComplet")) {
				ContactMelComplet contactMelComplet = (ContactMelComplet) c;
				etuRef.setMail(contactMelComplet.getMail());
				etuRef.setMailPerso(contactMelComplet.getMail());
			}

		});
		
		String adresse = addr.toString().trim().replaceAll(",+$", ""); // remove trailing comma if exists");
		etuRef.setMainAddress(adresse); 
	}

	/**
	 * 
	 * @param inscription
	 * @return
	 */
	@Mapping(target = "codeEtp", source = "cible.code")
	@Mapping(target = "codVrsVet", source = "cible.periode.code")
	@Mapping(target = "libWebVet", source = "cible.libelleCourt")
	@Mapping(target = "codeDiplome", source = "cible.formation.code")
	@Mapping(target = "versionDiplome", source = "cible.periode.code")
	@Mapping(target = "libDiplome", source = "cible.formation.libelleCourt")
	@Mapping(target = "codeComposante", source = "cible.codeStructure")
	@Mapping(target = "regimeIns", source = "regimeInscription.code", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "libRg", source = "regimeInscription.libelle", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	// @Mapping(target = "codeCursusAmenage", expression =
	// "java(String.join(\",\",inscription.getSituationPersonnelleInscription().getAmenagementsSpecifiques()))")
	@Mapping(target = "libelleCursusAmenage", expression = "java("
			+ "\"cesure=\"+inscription.getSituationUniversitaire().getCesure()"
			+ "+\",mobilite=\"+inscription.getSituationUniversitaire().getMobilite()"
			+ "+\",programmeEchange= \"+inscription.getSituationUniversitaire().getProgrammeEchange())")
	@Mapping(target = "typeIns", source = "contexteInscription")
	@Mapping(target = "statutInscription", source = "statutInscription")
	public EtapeInscription stagesApprenantToEtapeInscription(InscriptionComplete inscription);

	public List<EtapeInscription> stagesApprenantToEtapeInscription(List<InscriptionComplete> inscriptions);

	@Mapping(target = "codRegIns", source = "code")
	@Mapping(target = "libRg", source = "libelle")
	@Mapping(target = "annee", source = "contexteConsommation")
	public RegimeInscription toEsupRegimeInscription(OccurrenceNomenclature rins);

	public List<RegimeInscription> toEsupRegimeInscription(List<OccurrenceNomenclature> rins);

	default public ApogeeMap mapToApogge(ApprenantEtInscriptions apprenantEtInscriptions) {
		List<InscriptionComplete> insCompletes = apprenantEtInscriptions.getInscriptions();
		List<EtapeInscription> etps = ApprenantEtuInfoAdmMapperInterface.Instance
				.stagesApprenantToEtapeInscription(insCompletes);
		ApogeeMap apogeeMap = new ApogeeMap();
		apogeeMap.setListeEtapeInscriptions(etps);

		final List<RegimeInscription> regimes = new ArrayList<RegimeInscription>();

		insCompletes.forEach(ins -> {
			OccurrenceNomenclature r = ins.getRegimeInscription();
			RegimeInscription rins = ApprenantEtuInfoAdmMapperInterface.Instance.toEsupRegimeInscription(r);
			regimes.add(rins);
		});

		apogeeMap.setRegimeInscription(regimes);

		return apogeeMap;
	}
	

}
