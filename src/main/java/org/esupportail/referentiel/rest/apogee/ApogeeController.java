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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

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
	public ApogeeMap etapesByEtudiantAndAnnee(@RequestParam(value = "codEtud") String codeEtud,
			@RequestParam(value = "annee") String annee) {
		return studentDataRepositoryDaoWS.recupererEtapesByEtudiantAndAnnee(codeEtud, annee, "");
	}

	@GetMapping("/infosAdmEtu")
	public EtudiantInfoAdm InfosAdmEtuV2(@RequestParam(value = "numEtud") String numEtud) {
		try {
			return studentDataRepositoryDaoWS.recupererEtudiantInfoAdm(numEtud);
		} catch (Exception e) {
			logger.error("erreur lors de la récupération InfosAdmEtuV2 pour : " + numEtud + " -> "+ e.getMessage());
			return null;
		}
	}

	@GetMapping("/listEtuParEtapeEtDiplome")
	public List<EtudiantDTO2Ext> recupererListeEtuParEtpEtDiplome(
			@RequestParam(value = "codeComposante",required = false) String codeComposante,
			@RequestParam(value = "annee", required = true) String annee,
			@RequestParam(value = "codeEtape", required = true) String codeEtape,
			@RequestParam(value = "versionEtape", required = true) String versionEtape,
			@RequestParam(value = "codeDiplome", required = true) String codeDiplome,
			@RequestParam(value = "versionDiplome", required = true) String versionDiplome,
			@RequestParam(value = "codEtu", required = false) String codEtu,
			@RequestParam(value = "nom", required = false) String nom,
			@RequestParam(value = "prenom", required = false) String prenom) {

		List<EtudiantDTO2Ext> listeEtu = etudiantMetierClient.recupererListeEtuParEtpEtDiplome(codeComposante, annee,
				codeEtape, versionEtape, codeDiplome, versionDiplome, codEtu, nom, prenom);
		ApoggeeLdapEtudiant apoggeeLdapEtudiant = new ApoggeeLdapEtudiant(personService);
		apoggeeLdapEtudiant.MappMailEtudiant(listeEtu);
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
		return studentDataRepositoryDaoWS.recupererEtapeVetsParEtudiantAnnee(codeEtud, annee);
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
		return studentDataRepositoryDaoWS.recupererEtapeInscriptionParEtudiantAnnee(codEtud, annee);
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
		return studentDataRepositoryDaoWS.recupererListeElpsStageParEtape(codeEtape, versionEtape);
	}

	/**
	 *
	 * @return
	 */
	@GetMapping("/etablissementReference")
	public EtabRef etablissementReference() {
		return studentComponentRepositoryDao.getEtabRef(universityCode);

	}

	/**
	 *
	 * @return
	 */
	@GetMapping("/etapesReference")
	public Map<String, String> getEtapesRef() {
		return studentComponentRepositoryDao.getEtapesRef(universityCode);
	}

	/**
	 *
	 * @return
	 */
	@GetMapping("/diplomesReference")
	public List<DiplomeReduitDto> getDiplomesRef() {
		return studentComponentRepositoryDao.getListeDiplomeDTO(universityCode);
	}

	/**
	 *
	 * @return
	 */
	@GetMapping("/diplomesReferenceParComposanteEtAnnee")
	public List<DiplomeReduitDto> getDiplomesRefParComposanteEtAnnee(
			@RequestParam(value = "codeComposante", required = true) String codeComposante,
			@RequestParam(value = "codeAnnee", required = true) String codeAnnee) {
		return studentComponentRepositoryDao.getListeDiplomeDTO(codeComposante, codeAnnee);
	}

	/**
	 *
	 * @return
	 */
	@GetMapping("/composantesPrincipalesRef")
	public Map<String, String> composantesPrincipalesRef() {
		return studentComponentRepositoryDao.getComposantesPrincipalesRef(universityCode, null);
	}

	/**
	 *
	 * @param composante
	 * @return
	 */
	@GetMapping("/composanteSignaitaireRef")
	public SignataireRef signaitaireRef(@RequestParam(value = "composante", defaultValue = "SCO") String composante) {
		return studentComponentRepositoryDao.getSigCompoRef(universityCode, composante);
	}

	public String getUniversityCode() {
		return universityCode;
	}

	public void setUniversityCode(String universityCode) {
		this.universityCode = universityCode;
	}
}
