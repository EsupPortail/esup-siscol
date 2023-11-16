package org.esupportail.referentiel.rest.generiqueSI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.esupportail.referentiel.beans.ApogeeMap;
import org.esupportail.referentiel.beans.DiplomeReduitDto;
import org.esupportail.referentiel.beans.ElementPedagogique;
import org.esupportail.referentiel.beans.EtabRef;
import org.esupportail.referentiel.beans.EtapeInscription;
import org.esupportail.referentiel.beans.EtudiantDTO2Ext;
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
public class GeneriqueSIController implements GeneriqueSIControllerInterface{

	@Value("@Value(${app.apogee.universityCode})")
	private String universityCode;

	@GetMapping("/etudiantRef")
	public ResponseEntity<EtudiantRef> getStudent(@RequestParam(value = "codEtud") String codeEtud,
			@RequestParam(value = "annee") String annee) {
		EtudiantRef e = new EtudiantRef();
		return new ResponseEntity<EtudiantRef>(e, HttpStatus.OK);
	}

	@Operation(summary = "Récupérer les années d'inscription d'un étudiant")
	@GetMapping("/anneesIa")
	public List<String> recupererAnneesIa(@RequestParam(value = "codEtud") String codeEtud) {
		List<String> annees = new ArrayList<String>();
		return annees;
	}

	/**
	 * 
	 * @param codeEtud
	 * @param annee
	 * @return
	 */
	@GetMapping("/etapesByEtudiantAndAnnee")
	public ApogeeMap etapesByEtudiantAndAnnee(@RequestParam(value = "codEtud") String codeEtud,
			@RequestParam(value = "annee") String annee) {
		ApogeeMap map = new ApogeeMap();
		return map;
	}

	@GetMapping("/infosAdmEtu")
	public EtudiantInfoAdm InfosAdmEtuV2(@RequestParam(value = "numEtud") String numEtud) {
		EtudiantInfoAdm student = new EtudiantInfoAdm();
		return student;
	}

	@GetMapping("/listEtuParEtapeEtDiplome")
	public List<EtudiantDTO2Ext> recupererListeEtuParEtpEtDiplome(
			@RequestParam(value = "codeComposante", required = true) String codeComposante,
			@RequestParam(value = "annee", required = true) String annee,
			@RequestParam(value = "codeEtape", required = true) String codeEtape,
			@RequestParam(value = "versionEtape", required = true) String versionEtape,
			@RequestParam(value = "codeDiplome", required = true) String codeDiplome,
			@RequestParam(value = "versionDiplome", required = true) String versionDiplome,
			@RequestParam(value = "codEtu", required = false) String codEtu,
			@RequestParam(value = "nom", required = false) String nom,
			@RequestParam(value = "prenom", required = false) String prenom) {

		List<EtudiantDTO2Ext> listeEtu = new ArrayList<EtudiantDTO2Ext>();
		return listeEtu;

	}

	/**
	 * 
	 * @param codeEtud
	 * @param annee
	 * @return
	 */
	@GetMapping("/studentEtapeVets")
	public LinkedHashMap<String, String> studentEtapeVets(@RequestParam(value = "codEtud") String codeEtud,
			@RequestParam(value = "annee") String annee) {
		LinkedHashMap<String, String> lEtapeInscriptions = new LinkedHashMap<String, String>();
		return lEtapeInscriptions;
	}

	/**
	 * 
	 * @param codEtud
	 * @param annee
	 * @return List<EtapeInscription>
	 */
	@GetMapping("/studentListeEtapeInscription")
	public List<EtapeInscription> studentListeEtapesInscription(@RequestParam(value = "codEtud") String codEtud,
			@RequestParam(value = "annee") String annee) {
		List<EtapeInscription> l = new ArrayList<EtapeInscription>();
		return l;
	}

	/**
	 * 
	 * @param codeEtape
	 * @param versionEtape
	 * @return List<ElementPedagogique>
	 */
	@GetMapping("/studentListeElpStage")
	public List<ElementPedagogique> studentListeElpStage(@RequestParam(value = "codeEtape") String codeEtape,
			@RequestParam(value = "versionEtape") String versionEtape) {
		List<ElementPedagogique> l = new ArrayList<ElementPedagogique>();
		return l;
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/etablissementReference")
	public EtabRef etablissementReference() {
		EtabRef etabRef = new EtabRef();
		return etabRef;

	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/etapesReference")
	public Map<String, String> getEtapesRef() {
		Map<String, String> ref = new HashMap<String, String>();
		return ref;
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/diplomesReference")
	public List<DiplomeReduitDto> getDiplomesRef() {
		List<DiplomeReduitDto> ref = new ArrayList<DiplomeReduitDto>();
		return ref;
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/diplomesReferenceParComposanteEtAnnee")
	public List<DiplomeReduitDto> getDiplomesRefParComposanteEtAnnee(
			@RequestParam(value = "codeComposante", required = true) String codeComposante,
			@RequestParam(value = "codeAnnee", required = true) String codeAnnee) {
		List<DiplomeReduitDto> ref = new ArrayList<DiplomeReduitDto>();
		return ref;
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/composantesPrincipalesRef")
	public Map<String, String> composantesPrincipalesRef() {
		Map<String, String> ref = new HashMap<String, String>();
		return ref;

	}

	/**
	 * 
	 * @param composante
	 * @return
	 */
	@GetMapping("/composanteSignaitaireRef")
	public SignataireRef signaitaireRef(@RequestParam(value = "composante", defaultValue = "SCO") String composante) {
		SignataireRef ref = new SignataireRef();
		return ref;

	}

	public String getUniversityCode() {
		return universityCode;
	}

	public void setUniversityCode(String universityCode) {
		this.universityCode = universityCode;
	}

}
