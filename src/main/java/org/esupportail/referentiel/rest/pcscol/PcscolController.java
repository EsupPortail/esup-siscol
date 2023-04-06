package org.esupportail.referentiel.rest.pcscol;

import java.net.URISyntaxException;
import java.util.Map;

import org.esupportail.referentiel.beans.ApogeeMap;
import org.esupportail.referentiel.beans.EtudiantRef;
import org.esupportail.referentiel.pcscol.services.PcscolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("pcscol")
public class PcscolController {

	final transient Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PcscolService pcscolService;

	@Value("${app.pcscol.codeStructure}")
	String codeStructure = "ETAB00";

	@Value("${app.pcscol.codePeriode}")
	String codePeriode = "PER-2020";

	@GetMapping("/etapesReference")
	public Map<String, String> getEtapesRef() {
		Map<String, String> ref = pcscolService.lireMapFormations(codeStructure, codePeriode, false, false);
		return ref;

	}

	@GetMapping("/composantesPrincipalesRef")
	public Map<String, String> composantesPrincipalesRef() {
		Map<String, String> ref = pcscolService.lireMapStructures(codeStructure);
		return ref;

	}

	@GetMapping("/etudiantRef")
	public ResponseEntity<EtudiantRef> etudiantRef(@RequestParam(value = "codEtud") String codeApprenant,
			@RequestParam(value = "annee") String annee) throws URISyntaxException {
		logger.debug("Charger etudiant  : {} period {}", codeApprenant, annee);
		if (codeApprenant == null || annee == null || codeApprenant.isBlank() || annee.isBlank()) {
			logger.error("CodeEtu ou l'annee ne doit être null");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CodeEtu ou l'annee ne doit être null", null);
		}
		EtudiantRef result = pcscolService.lireEtudiantRef( codeStructure,  codeApprenant,  annee);
		return new ResponseEntity<EtudiantRef>(result, HttpStatus.OK);
	}

	// @GetMapping("/etudiantRef")
//	public ResponseEntity<EtudiantRef> getStudent(@RequestParam(value = "codEtud") String codeEtud,
//			@RequestParam(value = "annee") String annee){
//		
//	}
	
	/**
	 * 
	 * @param codeEtud
	 * @param annee
	 * @return
	 */
	@GetMapping("/etapesByEtudiantAndAnnee")
	public ResponseEntity<ApogeeMap> etapesByEtudiantAndAnnee(@RequestParam(value = "codEtud",defaultValue = "000000036") String codeEtud,
			@RequestParam(value = "annee",defaultValue = "2020") String annee) {
		//ApogeeMap map = studentDataRepositoryDaoWS.recupererEtapesByEtudiantAndAnnee(codeEtud, annee, "");
		ApogeeMap apogeeMap = pcscolService.recupererIaIpParEtudiantAnnee(codeStructure, codeEtud, annee);
		return new ResponseEntity<ApogeeMap>(apogeeMap, HttpStatus.OK);
	}

	public PcscolService getPcscolService() {
		return pcscolService;
	}

	public void setPcscolService(PcscolService pcscolService) {
		this.pcscolService = pcscolService;
	}

}
