package org.esupportail.referentiel.rest.generiqueSI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("generiqueSI")
public class GeneriqueSIController implements GeneriqueSIControllerInterface {

	@Value("@Value(${app.apogee.universityCode})")
	private String universityCode;

	@GetMapping("/etudiantRef")
	public ResponseEntity<EtudiantRef> getEtudiantRef(@RequestParam(value = "codEtud") String codeEtud,
			@RequestParam(value = "annee") String annee) {
		EtudiantRef e = new EtudiantRef();
		return new ResponseEntity<EtudiantRef>(e, HttpStatus.OK);
	}

	@Operation(summary = "Récupérer les années d'inscription d'un étudiant")
	@GetMapping("/anneesIa")
	public ResponseEntity<List<String>> recupererAnneesIa(@RequestParam(value = "codEtud") String codeEtud) {
		List<String> annees = new ArrayList<String>();
		return new ResponseEntity<>(annees, HttpStatus.OK);
	}

	/**
	 * 
	 * @param codeEtud
	 * @param annee
	 * @return
	 */
	@GetMapping("/etapesByEtudiantAndAnnee")
	public ResponseEntity<ApogeeMap> etapesByEtudiantAndAnnee(@RequestParam(value = "codEtud") String codeEtud,
			@RequestParam(value = "annee") String annee) {
		ApogeeMap map = new ApogeeMap();
		return new ResponseEntity<ApogeeMap>(map, HttpStatus.OK);
	}

	@GetMapping("/infosAdmEtu")
	public ResponseEntity<EtudiantInfoAdm> InfosAdmEtuV2(@RequestParam(value = "numEtud") String numEtud) {
		EtudiantInfoAdm student = new EtudiantInfoAdm();
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@GetMapping("/listEtuParEtapeEtDiplome")
	public ResponseEntity<List<ApprenantDto>> recupererListeEtuParEtpEtDiplome(
			@RequestParam(value = "codeComposante", required = true) String codeComposante,
			@RequestParam(value = "annee", required = true) String annee,
			@RequestParam(value = "codeEtape", required = true) String codeEtape,
			@RequestParam(value = "versionEtape", required = true) String versionEtape,
			@RequestParam(value = "codeDiplome", required = true) String codeDiplome,
			@RequestParam(value = "versionDiplome", required = true) String versionDiplome,
			@RequestParam(value = "codEtu", required = false) String codEtu,
			@RequestParam(value = "nom", required = false) String nom,
			@RequestParam(value = "prenom", required = false) String prenom) {

		List<ApprenantDto> listeEtu = new ArrayList<ApprenantDto>();
		return new ResponseEntity<>(listeEtu, HttpStatus.OK);

	}

	/**
	 * 
	 * @param codeEtud
	 * @param annee
	 * @return
	 */
	@GetMapping("/studentEtapeVets")
	public ResponseEntity<LinkedHashMap<String, String>> studentEtapeVets(
			@RequestParam(value = "codEtud") String codeEtud, @RequestParam(value = "annee") String annee) {
		LinkedHashMap<String, String> lEtapeInscriptions = new LinkedHashMap<String, String>();
		return new ResponseEntity<>(lEtapeInscriptions, HttpStatus.OK);
	}

	/**
	 * 
	 * @param codEtud
	 * @param annee
	 * @return List<EtapeInscription>
	 */
	@GetMapping("/studentListeEtapeInscription")
	public ResponseEntity<List<EtapeInscription>> studentListeEtapesInscription(
			@RequestParam(value = "codEtud") String codEtud, @RequestParam(value = "annee") String annee) {
		List<EtapeInscription> l = new ArrayList<EtapeInscription>();
		return new ResponseEntity<>(l, HttpStatus.OK);
	}

	/**
	 * 
	 * @param codeEtape
	 * @param versionEtape
	 * @return List<ElementPedagogique>
	 */
	@GetMapping("/studentListeElpStage")
	public ResponseEntity<List<ElementPedagogique>> studentListeElpStage(
			@RequestParam(value = "codeEtape") String codeEtape,
			@RequestParam(value = "versionEtape") String versionEtape) {
		List<ElementPedagogique> l = new ArrayList<ElementPedagogique>();
		return new ResponseEntity<>(l, HttpStatus.OK);
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/etablissementReference")
	public ResponseEntity<EtabRef> etablissementReference() {
		EtabRef etabRef = new EtabRef();
		return new ResponseEntity<>(etabRef, HttpStatus.OK);

	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/etapesReference")
	public ResponseEntity<Map<String, String>> getEtapesRef() {
		Map<String, String> ref = new HashMap<String, String>();
		return new ResponseEntity<Map<String, String>>(ref, HttpStatus.OK);
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/diplomesReference")
	public ResponseEntity<List<DiplomeReduitDto>> getDiplomesRef() {
		List<DiplomeReduitDto> ref = new ArrayList<DiplomeReduitDto>();
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
		List<DiplomeReduitDto> ref = new ArrayList<DiplomeReduitDto>();
		return new ResponseEntity<>(ref, HttpStatus.OK);
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/composantesPrincipalesRef")
	public ResponseEntity<Map<String, String>> composantesPrincipalesRef() {
		Map<String, String> ref = new HashMap<String, String>();
		return new ResponseEntity<Map<String, String>>(ref, HttpStatus.OK);

	}

	/**
	 * 
	 * @param composante
	 * @return
	 */
	@GetMapping("/composanteSignaitaireRef")
	public ResponseEntity<SignataireRef> signaitaireRef(
			@RequestParam(value = "composante", defaultValue = "SCO") String composante) {
		SignataireRef ref = new SignataireRef();
		return new ResponseEntity<>(ref, HttpStatus.OK);

	}

	public String getUniversityCode() {
		return universityCode;
	}

	public void setUniversityCode(String universityCode) {
		this.universityCode = universityCode;
	}

}
