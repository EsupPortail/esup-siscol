package org.esupportail.referentiel.rest.apogee;

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
import org.esupportail.referentiel.ldap.services.interfaces.LdapServiceInterface;
import org.esupportail.referentiel.mappers.ApoggeeLdapEtudiant;
import org.esupportail.referentiel.services.StudentComponentRepositoryDao;
import org.esupportail.referentiel.services.impl.StudentDataRepositoryDaoWS;
import org.esupportail.referentiel.ws.services.EtudiantMetierClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;

import io.swagger.v3.oas.annotations.Operation;

@ConditionalOnProperty(name = "app.mode_apogee")
@RestController
@RequestMapping("apogee")
public class ApogeeController { // NO_UCD (unused code)

	final private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EtudiantMetierClient etudiantMetierClient;

	@Autowired
	private StudentDataRepositoryDaoWS studentDataRepositoryDaoWS;

	@Autowired
	private StudentComponentRepositoryDao studentComponentRepositoryDao;

	@Autowired
	@Qualifier("personServiceMapperMethod")
	private LdapServiceInterface personService;

	@Value("${app.apogee.universityCode}")
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
		return studentDataRepositoryDaoWS.recupererAnneesIa(codeEtud);
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
		try {
			return new ResponseEntity<>(
					studentDataRepositoryDaoWS.recupererEtapesByEtudiantAndAnnee(codeEtud, annee, ""), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("erreur lors de la récupération etapesByEtudiant");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/infosAdmEtu")
	public ResponseEntity<EtudiantInfoAdm> InfosAdmEtuV2(@RequestParam(value = "numEtud") String numEtud) {
		try {
			return new ResponseEntity<>(studentDataRepositoryDaoWS.recupererEtudiantInfoAdm(numEtud), HttpStatus.OK);

		} catch (Exception e) {
			logger.error("erreur lors de la récupération InfosAdmEtuV2 pour : " + numEtud + " -> " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/listEtuParEtapeEtDiplome")
	public ResponseEntity<List<EtudiantDTO2Ext>> recupererListeEtuParEtpEtDiplome(
			@RequestParam(value = "codeComposante", required = true) String codeComposante,
			@RequestParam(value = "annee", required = true) String annee,
			@RequestParam(value = "codeEtape", required = true) String codeEtape,
			@RequestParam(value = "versionEtape", required = true) String versionEtape,
			@RequestParam(value = "codeDiplome", required = true) String codeDiplome,
			@RequestParam(value = "versionDiplome", required = true) String versionDiplome,
			@RequestParam(value = "codEtu", required = false) String codEtu,
			@RequestParam(value = "nom", required = false) String nom,
			@RequestParam(value = "prenom", required = false) String prenom) {
		try {
			List<EtudiantDTO2Ext> listeEtu = etudiantMetierClient.recupererListeEtuParEtpEtDiplome(codeComposante,
					annee, codeEtape, versionEtape, codeDiplome, versionDiplome, codEtu, nom, prenom);
			ApoggeeLdapEtudiant apoggeeLdapEtudiant = new ApoggeeLdapEtudiant(personService);
			apoggeeLdapEtudiant.MappMailEtudiant(listeEtu);
			return new ResponseEntity<>(listeEtu, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("erreur lors de la récupération listeEtuParEtpEtDiplome pour : " + codeComposante + " -> "
					+ e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
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
		try {
			return new ResponseEntity<>(studentDataRepositoryDaoWS.recupererEtapeVetsParEtudiantAnnee(codeEtud, annee),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error(
					"erreur lors de la récupération studentEtapeVets pour : " + codeEtud + " -> " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

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
		try {
			return new ResponseEntity<>(
					studentDataRepositoryDaoWS.recupererEtapeInscriptionParEtudiantAnnee(codEtud, annee),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error("erreur lors de la récupération studentListeEtapeInscription pour : " + codEtud + " -> "
					+ e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
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
		try {
			return new ResponseEntity<>(
					studentDataRepositoryDaoWS.recupererListeElpsStageParEtape(codeEtape, versionEtape), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("erreur lors de la récupération studentListeElpStage pour : " + codeEtape + " -> "
					+ e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// return studentDataRepositoryDaoWS.recupererListeElpsStageParEtape(codeEtape,
		// versionEtape);
	}

	/**
	 *
	 * @return
	 */
	@GetMapping("/etablissementReference")
	public ResponseEntity<EtabRef> etablissementReference() {
		try {
			return new ResponseEntity<>(studentComponentRepositoryDao.getEtabRef(universityCode), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("erreur lors de la récupération etablissementReference pour : " + universityCode + " -> "
					+ e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 *
	 * @return
	 */
	@GetMapping("/etapesReference")
	public ResponseEntity<Map<String, String>> getEtapesRef() {
		try {
			return new ResponseEntity<>(studentComponentRepositoryDao.getEtapesRef(universityCode), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("erreur lors de la récupération etapesReference pour : " + universityCode + " -> "
					+ e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 *
	 * @return
	 */
	@GetMapping("/diplomesReference")
	public ResponseEntity<List<DiplomeReduitDto>> getDiplomesRef() {
		try {
			return new ResponseEntity<>(studentComponentRepositoryDao.getListeDiplomeDTO(universityCode),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error("erreur lors de la récupération diplomesReference pour : " + universityCode + " -> "
					+ e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 *
	 * @return
	 */
	@GetMapping("/diplomesReferenceParComposanteEtAnnee")
	public ResponseEntity<List<DiplomeReduitDto>> getDiplomesRefParComposanteEtAnnee(
			@RequestParam(value = "codeComposante", required = true) String codeComposante,
			@RequestParam(value = "codeAnnee", required = true) String codeAnnee) {
		try {
			return new ResponseEntity<>(studentComponentRepositoryDao.getListeDiplomeDTO(codeComposante, codeAnnee),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error("erreur lors de la récupération diplomesReferenceParComposanteEtAnnee pour : " + codeComposante
					+ " -> " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 *
	 * @return
	 */
	@GetMapping("/composantesPrincipalesRef")
	public ResponseEntity<Map<String, String>> composantesPrincipalesRef() {
		try {
			return new ResponseEntity<>(
					studentComponentRepositoryDao.getComposantesPrincipalesRef(universityCode, null), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("erreur lors de la récupération composantesPrincipalesRef pour : " + universityCode , e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 *
	 * @param composante
	 * @return
	 */
	@GetMapping("/composanteSignaitaireRef")
	public ResponseEntity<SignataireRef> signaitaireRef(
			@RequestParam(value = "composante", defaultValue = "SCO") String composante) {
		try {
			return new ResponseEntity<>(studentComponentRepositoryDao.getSigCompoRef(universityCode, composante),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error(
					"erreur lors de la récupération signaitaireRef pour : " + universityCode , e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public String getUniversityCode() {
		return universityCode;
	}

	public void setUniversityCode(String universityCode) {
		this.universityCode = universityCode;
	}
}
