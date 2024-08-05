package org.esupportail.referentiel.rest.generiqueSI;

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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface GeneriqueSIControllerInterface {
	/**
	 * 
	 * @param codeEtud
	 * @param annee
	 * @return
	 */
	public ResponseEntity<EtudiantRef> getEtudiantRef(@RequestParam(value = "codEtud") String codeEtud,
			@RequestParam(value = "annee") String annee);

	/**
	 * 
	 * @param codeEtud
	 * @return
	 */
	public ResponseEntity<List<String>> recupererAnneesIa(@RequestParam(value = "codEtud") String codeEtud);

	/**
	 * 
	 * @param codeEtud
	 * @param annee
	 * @return
	 */
	public  ResponseEntity<ApogeeMap> etapesByEtudiantAndAnnee(@RequestParam(value = "codEtud") String codeEtud,
			@RequestParam(value = "annee") String annee);
	/**
	 * 
	 * @param numEtud
	 * @return
	 */
	public ResponseEntity<EtudiantInfoAdm> InfosAdmEtuV2(@RequestParam(value = "numEtud") String numEtud);
	/**
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
	public ResponseEntity<List<EtudiantDTO2Ext>> recupererListeEtuParEtpEtDiplome(
			@RequestParam(value = "codeComposante", required = true) String codeComposante,
			@RequestParam(value = "annee", required = true) String annee,
			@RequestParam(value = "codeEtape", required = true) String codeEtape,
			@RequestParam(value = "versionEtape", required = true) String versionEtape,
			@RequestParam(value = "codeDiplome", required = true) String codeDiplome,
			@RequestParam(value = "versionDiplome", required = true) String versionDiplome,
			@RequestParam(value = "codEtu", required = false) String codEtu,
			@RequestParam(value = "nom", required = false) String nom,
			@RequestParam(value = "prenom", required = false) String prenom);
	/**
	 * 
	 * @param codeEtud
	 * @param annee
	 * @return
	 */
	public ResponseEntity<LinkedHashMap<String, String>> studentEtapeVets(@RequestParam(value = "codEtud") String codeEtud,
			@RequestParam(value = "annee") String annee);
	/**
	 * 
	 * @param codEtud
	 * @param annee
	 * @return
	 */
	public ResponseEntity<List<EtapeInscription>> studentListeEtapesInscription(@RequestParam(value = "codEtud") String codEtud,
			@RequestParam(value = "annee") String annee);
	/**
	 * 
	 * @param codeEtape
	 * @param versionEtape
	 * @return
	 */
	public ResponseEntity<List<ElementPedagogique>> studentListeElpStage(@RequestParam(value = "codeEtape") String codeEtape,
			@RequestParam(value = "versionEtape") String versionEtape);
	/**
	 * 
	 * @return
	 */
	public ResponseEntity<EtabRef> etablissementReference();
	/**
	 * 
	 * @return
	 */
	public ResponseEntity<Map<String, String>> getEtapesRef();
	/**
	 * 
	 * @return
	 */
	public ResponseEntity<List<DiplomeReduitDto>> getDiplomesRef();
	/**
	 * 
	 * @param codeComposante
	 * @param codeAnnee
	 * @return
	 */
	public ResponseEntity<List<DiplomeReduitDto>> getDiplomesRefParComposanteEtAnnee(
			@RequestParam(value = "codeComposante", required = true) String codeComposante,
			@RequestParam(value = "codeAnnee", required = true) String codeAnnee);
	/**
	 * 
	 * @return
	 */
	public ResponseEntity<Map<String, String>> composantesPrincipalesRef();
	/**
	 * 
	 * @param composante
	 * @return
	 */
	public ResponseEntity<SignataireRef >signaitaireRef(@RequestParam(value = "composante", defaultValue = "SCO") String composante);
}
