package org.esupportail.referentiel.rest.pcscol;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.esupportail.referentiel.beans.ApogeeMap;
import org.esupportail.referentiel.beans.ApprenantDto;
import org.esupportail.referentiel.beans.DiplomeReduitDto;
import org.esupportail.referentiel.beans.ElementPedagogique;
import org.esupportail.referentiel.beans.EtabRef;
import org.esupportail.referentiel.beans.EtapeInscription;
import org.esupportail.referentiel.beans.EtudiantInfoAdm;
import org.esupportail.referentiel.beans.EtudiantRef;
import org.esupportail.referentiel.beans.SignataireRef;
import org.esupportail.referentiel.pcscol.ins.model.Periode;
import org.esupportail.referentiel.pcscol.services.EspaceService;
import org.esupportail.referentiel.pcscol.services.PcscolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class PcscolControllerAdapter {

	final transient Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PcscolService pcscolService;

	@Value("${app.pcscol.codeStructure}")
	String codeStructure = "ETAB00";

	@Value("${app.pcscol.codePeriode}")
	String codePeriode = "PER-2020";

	@Value("${app.pcscol.codesPeriodesChargementFormations}")
	String codesPeriodesChargementFormations = "PER-2022";

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
		logger.debug(annee, " =>", espaces);
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
		logger.debug("{} {}  {}","etapesByEtudiantAndAnnee", " => nbr d'esapces ", espaces.size());

		/**
		 * TODO relation annee periode ??
		 */
		if (espaces != null && !espaces.isEmpty()) {
			String espacesFictif = espaces.get(0).getCode();
			ApogeeMap apogeeMap = pcscolService.recupererIaIpParEtudiantAnnee(codeStructure, codeEtud, espacesFictif);
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
	 */

	public ResponseEntity<EtudiantInfoAdm> InfosAdmEtuV2(String numEtud) {
		EtudiantInfoAdm student = pcscolService.lireEtudiantInfoAdm(codeStructure, numEtud);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	/**
	 * 
	 */

	public ResponseEntity<List<ApprenantDto>> recupererListeEtuParEtpEtDiplome(String codeComposante, String annee,
			String codeEtape, String versionEtape, String codeDiplome, String versionDiplome, String codEtu, String nom,
			String prenom) {

		// TODO
		List<ApprenantDto> listeEtu = pcscolService.recupererListeEtuParEtpEtDiplome(codeComposante, annee, codeEtape,
				versionEtape, codeDiplome, versionDiplome, codEtu, nom, prenom);
		return new ResponseEntity<>(listeEtu, HttpStatus.OK);

	}

	/**
	 * 
	 * @param codeEtud
	 * @param annee
	 * @return
	 */

	public ResponseEntity<LinkedHashMap<String, String>> studentEtapeVets(String codeEtud, String annee) {
		// TODO
		List<Periode> espaces = espaceService.espacesFromAnnee(codeStructure, annee);
		LinkedHashMap<String, String> lEtapeInscriptions = new LinkedHashMap<String, String>();

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
		List<EtapeInscription> l = pcscolService.etapeInscription(codeStructure, codEtud, annee);
		return new ResponseEntity<>(l, HttpStatus.OK);
	}

	/**
	 * 
	 * @return
	 */
	public ResponseEntity<List<DiplomeReduitDto>> getDiplomesRefParComposanteEtAnnee(String codeComposante,
			String codeAnnee) {
		List<DiplomeReduitDto> ref = pcscolService.diplomeRef(codeComposante, codeAnnee);
		return new ResponseEntity<>(ref, HttpStatus.OK);
	}

	/**
	 * 
	 * @param composante
	 * @return
	 */
	public ResponseEntity<SignataireRef> signaitaireRef(String composante) {
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
