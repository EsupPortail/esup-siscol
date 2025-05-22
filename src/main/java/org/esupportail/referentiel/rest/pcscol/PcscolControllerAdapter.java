package org.esupportail.referentiel.rest.pcscol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.esupportail.referentiel.beans.ApogeeMap;
import org.esupportail.referentiel.beans.ApprenantDto;
import org.esupportail.referentiel.beans.DiplomeReduitDto;
import org.esupportail.referentiel.beans.EtapeInscription;
import org.esupportail.referentiel.beans.EtudiantRef;
import org.esupportail.referentiel.ldap.services.interfaces.LdapServiceInterface;
import org.esupportail.referentiel.pcscol.odf.model.Periode;
import org.esupportail.referentiel.pcscol.services.ChcExterneService;
import org.esupportail.referentiel.pcscol.services.EspaceService;
import org.esupportail.referentiel.pcscol.services.PcscolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@ConditionalOnProperty(name = "app.mode_pegase")
public class PcscolControllerAdapter {

	final transient Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PcscolService pcscolService;
	@Autowired
	private ChcExterneService chcExterneService;

	@Autowired
	@Qualifier("personServiceMapperMethod")
	LdapServiceInterface personService;

	@Value("${app.pcscol.codeStructure}")
	String codeStructure = "ETAB00";

	@Value("${app.pcscol.codePeriode}")
	String codePeriode = "PER-2020";

	@Value("${app.pcscol.codesPeriodesChargementFormations}")
	String codesPeriodesChargementFormations;

	@Autowired
	private EspaceService espaceService;

	/**
	 * 
	 * @param codeApprenant
	 * @param annee
	 * @return
	 */
	public ResponseEntity<EtudiantRef> getEtudiantRef(String codeApprenant, String annee) {

		// generique
		List<Periode> espaces = espaceService.espacesFromAnnee(codeStructure, annee);
		logger.debug("{} {}  {}", "getEtudiantRef", " => nbr d'esapces ", espaces.size());
		/**
		 * TODO relation annee periode ?? Attention ceci est une POF doit etre valide
		 */

		if (espaces != null && !espaces.isEmpty()) {
			String espacesFictif = espaces.get(0).getCode();

			EtudiantRef result = pcscolService.lireEtudiantRef(codeStructure, codeApprenant, espacesFictif);
			return new ResponseEntity<EtudiantRef>(result, HttpStatus.OK);
		} else
			return ResponseEntity.badRequest().build();
	}

	/**
	 * 
	 * @param codeEtud
	 * @param annee
	 * @return
	 */

	public ResponseEntity<ApogeeMap> etapesByEtudiantAndAnnee(String codeEtud, String annee) {
		// generique
		List<Periode> espaces = espaceService.espacesFromAnnee(codeStructure, annee);
		logger.debug("{} {}  {}", "etapesByEtudiantAndAnnee", " => nbr d'esapces ", espaces.size());

		/**
		 * TODO relation annee periode ??
		 */
		if (espaces != null && !espaces.isEmpty()) {

			List<String> code_espaces = new ArrayList<String>();
			espaces.forEach(e -> {
				code_espaces.add(e.getCode());
			});

			ApogeeMap apogeeMap = pcscolService.recupererIaIpParEtudiantAnnee(codeStructure, codeEtud, code_espaces);
			return new ResponseEntity<ApogeeMap>(apogeeMap, HttpStatus.OK);
		}
		return ResponseEntity.badRequest().build();
	}

	/**
	 * 
	 * @param codeEtud
	 * @return
	 */
	public ResponseEntity<List<String>> recupererAnneesIa(String codeEtud) {
		List<String> list = pcscolService.recupererAnneesIa(codeStructure, codeEtud);

		logger.debug("recherche des annees ins {} {} {}", codeStructure, codeEtud, list);

		List<String> listAnnee = espaceService.anneeUnivFromEsapces(codeStructure, list);

		return new ResponseEntity<>(listAnnee, HttpStatus.OK);
	}

	/**
	 * 
	 * @param codeComposante
	 * @param annee
	 * @param codeEtape
	 * @param versionEtape
	 * @param codeDiplome
	 * @param versionDiplome
	 * @param codEtu
	 * @param nom
	 * @param prenom
	 * @return
	 */
	public ResponseEntity<List<ApprenantDto>> recupererListeEtuParEtpEtDiplome(String codeComposante, String annee,
			String codeEtape, String versionEtape, String codeDiplome, String versionDiplome, String codEtu, String nom,
			String prenom) {

		// TODO
		List<Periode> periodes = espaceService.espacesFromAnnee(codeStructure, annee);

		List<ApprenantDto> apprenantDtos = new ArrayList<ApprenantDto>();

		if (periodes != null && !periodes.isEmpty()) {
			periodes.forEach(p -> {

				// (String codeStructure, String codePeriode, String codeObjetFormation,String
				// codeObjetFormationParent, String codeApprenant, String nom, String prenom)
				List<ApprenantDto> apperants = chcExterneService.getApprenantDto(codeComposante, versionEtape,
						codeEtape, codeDiplome, codEtu, nom, prenom);
				
				/**
				 * TODO
				 * A supprimer après confirmation
				 * l'appel inscriptionsValidees sera supprimé 
				 */
//				List<ApprenantDto> apperants = pcscolService.recupererListeEtuParEtpEtDiplome(codeComposante, p.getCode(), codeEtape,
//						versionEtape, codeDiplome, versionDiplome, codEtu, nom, prenom);
//				
				logger.debug("recupererListeEtuParEtpEtDiplome : " + apperants);
				if (apperants != null && !apperants.isEmpty()) {
					apprenantDtos.addAll(apperants);
				}
			});
		}

//		apprenantDtos.forEach(e -> {
//			try {
//				Person person = personService.findByCodEtu(e.getCodEtu());
//				if (person != null) {
//					e.setMail(person.getMail());
//				}
//			} catch (Exception e1) {
//				logger.error("Erreur lors de la recherche de l'étudiant dans LDAP : " + e1.getMessage());
//			}
//
//		});

		return new ResponseEntity<>(apprenantDtos, HttpStatus.OK);

	}

	/**
	 * 
	 * @param codeEtud
	 * @param annee
	 * @return
	 */

	public ResponseEntity<Map<String, String>> studentEtapeVets(String codeEtud, String annee) {
		// TODO
		List<Periode> espaces = espaceService.espacesFromAnnee(codeStructure, annee);
		Map<String, String> lEtapeInscriptions = new HashMap<String, String>();

		if (espaces != null && !espaces.isEmpty()) {
			espaces.forEach(e -> {
				LinkedHashMap<String, String> partiel = pcscolService.studentEtapeVets(codeStructure, codeEtud,
						e.getCode());
				lEtapeInscriptions.putAll(partiel);
			});

		}

		return new ResponseEntity<>(lEtapeInscriptions, HttpStatus.OK);
	}

	/**
	 * 
	 * @param codEtud
	 * @param annee
	 * @return List<EtapeInscription>
	 */
	public ResponseEntity<List<EtapeInscription>> studentListeEtapesInscription(String codEtud, String annee) {
		// TODO
		List<EtapeInscription> etapeInscriptions = new ArrayList<EtapeInscription>();
		List<Periode> espaces = espaceService.espacesFromAnnee(codeStructure, annee);
		if (espaces != null && !espaces.isEmpty()) {
			espaces.forEach(espace -> {
			
				List<EtapeInscription> etps = pcscolService.etapeInscription(codeStructure, codEtud, espace.getCode());
				etapeInscriptions.addAll(etps);
			});

		}

		// 22
		// List<EtapeInscription> l = pcscolService.etapeInscription(codeStructure,
		// codEtud, annee);
		return new ResponseEntity<>(etapeInscriptions, HttpStatus.OK);
	}

	/**
	 * 
	 * @param codeComposante
	 * @param annee
	 * @return
	 */
	public ResponseEntity<List<DiplomeReduitDto>> getDiplomesRefParComposanteEtAnnee(String codeComposante,
			String annee) {

		List<DiplomeReduitDto> diplomeReduitDtos = new ArrayList<DiplomeReduitDto>();

		List<Periode> espaces = espaceService.espacesFromAnnee(codeStructure, annee);

		if (espaces != null && !espaces.isEmpty()) {

			espaces.forEach(e -> {
				List<DiplomeReduitDto> ref = pcscolService.diplomeRef(codeComposante, e.getCode());
				if (ref != null)
					diplomeReduitDtos.addAll(ref);

			});

		}

		return new ResponseEntity<>(diplomeReduitDtos, HttpStatus.OK);
	}

	public PcscolService getPcscolService() {
		return pcscolService;
	}

	public void setPcscolService(PcscolService pcscolService) {
		this.pcscolService = pcscolService;
	}

}
