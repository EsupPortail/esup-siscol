package org.esupportail.referentiel.rest.pcscol;

import java.util.List;
import java.util.Map;

import org.esupportail.referentiel.beans.ApogeeMap;
import org.esupportail.referentiel.beans.ApprenantDto;
import org.esupportail.referentiel.beans.DiplomeReduitDto;
import org.esupportail.referentiel.beans.ElementPedagogique;
import org.esupportail.referentiel.beans.EtabRef;
import org.esupportail.referentiel.beans.EtapeInscription;
import org.esupportail.referentiel.beans.EtudiantInfoAdm;
import org.esupportail.referentiel.beans.EtudiantRef;
import org.esupportail.referentiel.beans.SignataireRef;
import org.esupportail.referentiel.pcscol.services.PcscolService;
import org.esupportail.referentiel.rest.generiqueSI.GeneriqueSIControllerInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("pcscol")
@ConditionalOnProperty(name = "app.mode_pegase")
public class PcscolController implements GeneriqueSIControllerInterface {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PcscolService pcscolService;

	@Value("${app.pcscol.codeStructure}")
	String codeStructure = "ETAB00";

	@Value("${app.pcscol.codePeriode}")
	String codePeriode;

	@Value("${app.pcscol.codesPeriodesChargementFormations}")
	String codesPeriodesChargementFormations;

	@Autowired
	PcscolControllerAdapter pcscolControllerAdapter;

	@GetMapping("/etapesReference")
	public ResponseEntity<Map<String, String>> getEtapesRef() {
		boolean pointInscriptionAdministratif = true;
		Map<String, String> ref = pcscolService.lireMapFormations(codeStructure, codesPeriodesChargementFormations,pointInscriptionAdministratif);
		return new ResponseEntity<>(ref, HttpStatus.OK);

	}

	

	/**
	 * 
	 */
	@GetMapping("/etudiantRef")
	public ResponseEntity<EtudiantRef> getEtudiantRef(@RequestParam(value = "codEtud") String codeApprenant,
			@RequestParam(value = "annee") String annee) {
		logger.debug("getEtudiantRef  : {} period {}", codeApprenant, annee);
		if (codeApprenant == null || annee == null || codeApprenant.isBlank() || annee.isBlank()) {
			logger.error("CodeEtu ou l'annee ne doit être null");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CodeEtu ou l'annee ne doit être null", null);
		}
		return pcscolControllerAdapter.getEtudiantRef(codeApprenant, annee);

	}

	/**
	 * 
	 * @param codeEtud
	 * @param annee
	 * @return
	 */
	@GetMapping("/etapesByEtudiantAndAnnee")
	public ResponseEntity<ApogeeMap> etapesByEtudiantAndAnnee(
			@RequestParam(value = "codEtud", defaultValue = "000000036") String codeEtud,
			@RequestParam(defaultValue = "2020") String annee) {
		return pcscolControllerAdapter.etapesByEtudiantAndAnnee(codeEtud, annee);
	}

	/**
	 * 
	 */
	@Override
	@Operation(summary = "Récupérer les années d'inscription d'un étudiant")
	@GetMapping("/anneesIa")
	public ResponseEntity<List<String>> recupererAnneesIa(String codeEtud) {
		ResponseEntity<List<String>> result = pcscolControllerAdapter.recupererAnneesIa(codeEtud);
		if (result.getBody() == null) {
			logger.error("Aucune année d'inscription trouvée pour l'étudiant {}", codeEtud);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Aucune année d'inscription trouvée pour l'étudiant " + codeEtud, null);
		}
		return result;
	}

	/**
	 * 
	 */
	@GetMapping("/infosAdmEtu")
	public ResponseEntity<EtudiantInfoAdm> InfosAdmEtuV2(@RequestParam(value = "numEtud") String numEtud) {
		EtudiantInfoAdm student = pcscolControllerAdapter.lireEtudiantInfoAdm(codeStructure, numEtud);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	/**
	 * 
	 */
	@GetMapping("/listEtuParEtapeEtDiplome")
	public ResponseEntity<List<ApprenantDto>> recupererListeEtuParEtpEtDiplome(
			@RequestParam(value = "codeComposante", required = false, defaultValue = "ETAB00") String codeComposante,
			@RequestParam(value = "annee", required = true, defaultValue = "2021") String annee,
			@RequestParam(value = "codeEtape", required = true, defaultValue = "MST-CIEA-A1") String codeEtape,
			@RequestParam(value = "versionEtape", required = true, defaultValue = "ETAB00") String versionEtape,
			@RequestParam(value = "codeDiplome", required = true, defaultValue = "MST-CIEA") String codeDiplome,
			@RequestParam(value = "versionDiplome", required = true, defaultValue = "ETAB00") String versionDiplome,
			@RequestParam(value = "codEtu", required = false) String codEtu,
			@RequestParam(value = "nom", required = false) String nom,
			@RequestParam(value = "prenom", required = false) String prenom) {
		ResponseEntity<List<ApprenantDto>> listeDtos = pcscolControllerAdapter.recupererListeEtuParEtpEtDiplome(
				codeComposante, annee, codeEtape, versionEtape, codeDiplome, versionDiplome, codEtu, nom, prenom);
		return listeDtos;
	}

	/**
	 * 
	 * @param codeEtud
	 * @param annee
	 * @return
	 */
	@GetMapping("/studentEtapeVets")
	public ResponseEntity<Map<String, String>> studentEtapeVets(String codeEtud, String annee) {
		ResponseEntity<Map<String, String>> lEtapeInscriptions = pcscolControllerAdapter
				.studentEtapeVets(codeEtud, annee);
		return lEtapeInscriptions;

	}

	/**
	 * 
	 * @param codEtud
	 * @param annee
	 * @return List<EtapeInscription>
	 */
	@GetMapping("/studentListeEtapeInscription")
	public ResponseEntity<List<EtapeInscription>> studentListeEtapesInscription(
			@RequestParam(value = "codEtud", defaultValue = "000000001") String codEtud,
			@RequestParam(value = "annee", defaultValue = "2020") String annee) {
		ResponseEntity<List<EtapeInscription>> result = pcscolControllerAdapter.studentListeEtapesInscription(codEtud,
				annee);
		return result;
	}

	/**
	 * 
	 */
	@GetMapping("/studentListeElpStage")
	public ResponseEntity<List<ElementPedagogique>> studentListeElpStage(
			@RequestParam(value = "codeEtape") String codeEtape,
			@RequestParam(value = "versionEtape") String versionEtape) {
		List<ElementPedagogique> l = pcscolService.studentListeElpStage(codeStructure, codeEtape, versionEtape,codesPeriodesChargementFormations);
		return new ResponseEntity<>(l, HttpStatus.OK);
	}

	
	/**
	 * 
	 * @return
	 */
	@GetMapping("/diplomesReference")
	public ResponseEntity<List<DiplomeReduitDto>> getDiplomesRef() {
		logger.debug("getDiplomesRef  : {} period {}", codeStructure, codePeriode);
		List<DiplomeReduitDto> ref = pcscolService.diplomeRef(codeStructure, codesPeriodesChargementFormations);
		return new ResponseEntity<>(ref, HttpStatus.OK);
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/diplomesReferenceParComposanteEtAnnee")
	public ResponseEntity<List<DiplomeReduitDto>> getDiplomesRefParComposanteEtAnnee(
			@RequestParam(value = "codeComposante", required = true) String codeComposante,
			@RequestParam(value = "codeAnnee", required = true) String codeAnnee) {

		return pcscolControllerAdapter
				.getDiplomesRefParComposanteEtAnnee(codeComposante, codeAnnee);
	}
	
	/**
	 * 
	 * @return
	 */
	@GetMapping("/etablissementReference")
	public ResponseEntity<EtabRef> etablissementReference() {
		EtabRef etabRef = pcscolService.lireEtabRef();
		return new ResponseEntity<>(etabRef, HttpStatus.OK);

	}

	/**
	 * 
	 */
	@GetMapping("/composantesPrincipalesRef")
	public ResponseEntity<Map<String, String>> composantesPrincipalesRef() {
		Map<String, String> ref = pcscolService.lireMapStructures();
		return new ResponseEntity<Map<String, String>>(ref, HttpStatus.OK);

	}
	/**
	 * 
	 * @param composante
	 * @return
	 */
	@GetMapping("/composanteSignaitaireRef")
	public ResponseEntity<SignataireRef> signaitaireRef(
			@RequestParam(value = "composante", defaultValue = "ETAB00") String composante) {
		SignataireRef ref = pcscolService.signaitaireRef(composante);
		return new ResponseEntity<>(ref, HttpStatus.OK);

	}

	public PcscolService getPcscolService() {
		return pcscolService;
	}

	public void setPcscolService(PcscolService pcscolService) {
		this.pcscolService = pcscolService;
	}

}
