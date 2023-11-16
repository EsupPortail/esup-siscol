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

	public ResponseEntity<EtudiantRef> getStudent(@RequestParam(value = "codEtud") String codeEtud,
			@RequestParam(value = "annee") String annee);

	public List<String> recupererAnneesIa(@RequestParam(value = "codEtud") String codeEtud);

	public ApogeeMap etapesByEtudiantAndAnnee(@RequestParam(value = "codEtud") String codeEtud,
			@RequestParam(value = "annee") String annee);

	public EtudiantInfoAdm InfosAdmEtuV2(@RequestParam(value = "numEtud") String numEtud);

	public List<EtudiantDTO2Ext> recupererListeEtuParEtpEtDiplome(
			@RequestParam(value = "codeComposante", required = true) String codeComposante,
			@RequestParam(value = "annee", required = true) String annee,
			@RequestParam(value = "codeEtape", required = true) String codeEtape,
			@RequestParam(value = "versionEtape", required = true) String versionEtape,
			@RequestParam(value = "codeDiplome", required = true) String codeDiplome,
			@RequestParam(value = "versionDiplome", required = true) String versionDiplome,
			@RequestParam(value = "codEtu", required = false) String codEtu,
			@RequestParam(value = "nom", required = false) String nom,
			@RequestParam(value = "prenom", required = false) String prenom);

	public LinkedHashMap<String, String> studentEtapeVets(@RequestParam(value = "codEtud") String codeEtud,
			@RequestParam(value = "annee") String annee);

	public List<EtapeInscription> studentListeEtapesInscription(@RequestParam(value = "codEtud") String codEtud,
			@RequestParam(value = "annee") String annee);

	public List<ElementPedagogique> studentListeElpStage(@RequestParam(value = "codeEtape") String codeEtape,
			@RequestParam(value = "versionEtape") String versionEtape);

	public EtabRef etablissementReference();

	public Map<String, String> getEtapesRef();

	public List<DiplomeReduitDto> getDiplomesRef();

	public List<DiplomeReduitDto> getDiplomesRefParComposanteEtAnnee(
			@RequestParam(value = "codeComposante", required = true) String codeComposante,
			@RequestParam(value = "codeAnnee", required = true) String codeAnnee);

	public Map<String, String> composantesPrincipalesRef();

	public SignataireRef signaitaireRef(@RequestParam(value = "composante", defaultValue = "SCO") String composante);
}
