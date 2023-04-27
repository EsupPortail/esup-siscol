package org.esupportail.referentiel.rest.apogee;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.esupportail.referentiel.beans.ApogeeMap;
import org.esupportail.referentiel.beans.DiplomeReduitDto;
import org.esupportail.referentiel.beans.ElementPedagogique;
import org.esupportail.referentiel.beans.EtabRef;
import org.esupportail.referentiel.beans.EtapeInscription;
import org.esupportail.referentiel.beans.EtudiantInfoAdm;
import org.esupportail.referentiel.beans.EtudiantRef;
import org.esupportail.referentiel.beans.SignataireRef;
import org.esupportail.referentiel.services.StudentComponentRepositoryDao;
import org.esupportail.referentiel.services.impl.StudentDataRepositoryDaoWS;
import org.esupportail.referentiel.ws.services.EtudiantMetierClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gouv.education.apogee.commun.client.ws.EtudiantMetier.EtudiantDTO2;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("apogee")
public class ApogeeController { // NO_UCD (unused code)

	@Autowired
	EtudiantMetierClient etudiantMetierClient;

	@Autowired
	StudentDataRepositoryDaoWS studentDataRepositoryDaoWS;

	@Autowired
	StudentComponentRepositoryDao studentComponentRepositoryDao;

	@Value("@Value(${app.apogee.universityCode})")
	private String universityCode;

	@GetMapping("/etudiantRef")
	public ResponseEntity<EtudiantRef> getStudent(@RequestParam(value = "codEtud") String codeEtud,
			@RequestParam(value = "annee") String annee) {
		EtudiantRef e = studentDataRepositoryDaoWS.recupererEtudiantApogee(universityCode, codeEtud, annee, false);
		return new ResponseEntity<EtudiantRef>(e, HttpStatus.OK);
	}

	@Operation(summary = "Récupérer les années d'inscription d'un étudiant")
	@GetMapping("/anneesIa")
	public List<String> recupererAnneesIa(@RequestParam(value = "codEtud") String codeEtud) {
		List<String> annees = studentDataRepositoryDaoWS.recupererAnneesIa(codeEtud);
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
		ApogeeMap map = studentDataRepositoryDaoWS.recupererEtapesByEtudiantAndAnnee(codeEtud, annee, "");
		return map;
	}

	@GetMapping("/infosAdmEtu")
	public EtudiantInfoAdm InfosAdmEtuV2(@RequestParam(value = "numEtud") String numEtud) {
		EtudiantInfoAdm student = studentDataRepositoryDaoWS.recupererEtudiantInfoAdm(numEtud);
		return student;
	}

	@GetMapping("/listEtuParEtapeEtDiplome")
	public List<EtudiantDTO2> recupererListeEtuParEtpEtDiplome(String annee, String codeEtape, String versionEtape,
			String codeDiplome, String versionDiplome) {

		List<EtudiantDTO2> listeEtu = etudiantMetierClient.recupererListeEtuParEtpEtDiplome(annee, codeEtape, versionEtape,
				codeDiplome, versionDiplome);
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
		LinkedHashMap<String, String> lEtapeInscriptions = studentDataRepositoryDaoWS
				.recupererEtapeVetsParEtudiantAnnee(codeEtud, annee);
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
		List<EtapeInscription> l = studentDataRepositoryDaoWS.recupererEtapeInscriptionParEtudiantAnnee(codEtud, annee);
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
		List<ElementPedagogique> l = studentDataRepositoryDaoWS.recupererListeElpsStageParEtape(codeEtape,
				versionEtape);
		return l;
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/etablissementReference")
	public EtabRef etablissementReference() {
		EtabRef etabRef = studentComponentRepositoryDao.getEtabRef(universityCode);
		return etabRef;

	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/etapesReference")
	public Map<String, String> getEtapesRef() {
		Map<String, String> ref = studentComponentRepositoryDao.getEtapesRef(universityCode);
		return ref;
	}
	
	
	/**
	 * 
	 * @return
	 */
	@GetMapping("/diplomesReference")
	public List<DiplomeReduitDto> getDiplomesRef() {
		List<DiplomeReduitDto> ref = studentComponentRepositoryDao.getListeDiplomeDTO(universityCode);
		return ref;
	}

	

	/**
	 * 
	 * @return
	 */
	@GetMapping("/composantesPrincipalesRef")
	public Map<String, String> composantesPrincipalesRef() {
		Map<String, String> ref = studentComponentRepositoryDao.getComposantesPrincipalesRef(universityCode, null);
		return ref;

	}

	/**
	 * 
	 * @param composante
	 * @return
	 */
	@GetMapping("/composanteSignaitaireRef")
	public SignataireRef signaitaireRef(@RequestParam(value = "composante", defaultValue = "SCO") String composante) {
		SignataireRef ref = studentComponentRepositoryDao.getSigCompoRef(universityCode, composante);
		return ref;

	}

	public String getUniversityCode() {
		return universityCode;
	}

	public void setUniversityCode(String universityCode) {
		this.universityCode = universityCode;
	}

}
