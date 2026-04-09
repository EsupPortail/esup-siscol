
package org.esupportail.referentiel.rest.generiqueSI;

import java.util.*;
import org.esupportail.referentiel.beans.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

/**
 * Contrôleur REST pour l'accès aux données génériques du SI (étudiants, diplômes, étapes, etc.).
 */
@RestController
@RequestMapping("generiqueSI")
public class GeneriqueSIController implements GeneriqueSIControllerInterface {

    @Value("${app.apogee.universityCode}")
    private String universityCode;

    /**
     * Récupère les informations de référence d'un étudiant.
     */
    @Operation(summary = "Récupérer les informations de référence d'un étudiant")
    @GetMapping("/etudiantRef")
    public ResponseEntity<EtudiantRef> getEtudiantRef(
            @RequestParam("codEtud") String codeEtud,
            @RequestParam("annee") String annee) {
        return new ResponseEntity<>(new EtudiantRef(), HttpStatus.OK);
    }

    /**
     * Récupère les années d'inscription d'un étudiant.
     */
    @Operation(summary = "Récupérer les années d'inscription d'un étudiant")
    @GetMapping("/anneesIa")
    public ResponseEntity<List<String>> recupererAnneesIa(@RequestParam("codEtud") String codeEtud) {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    /**
     * Récupère les étapes d'un étudiant pour une année donnée.
     */
    @Operation(summary = "Récupérer les étapes d'un étudiant pour une année donnée")
    @GetMapping("/etapesByEtudiantAndAnnee")
    public ResponseEntity<ApogeeMap> etapesByEtudiantAndAnnee(
            @RequestParam("codEtud") String codeEtud,
            @RequestParam("annee") String annee) {
        return new ResponseEntity<>(new ApogeeMap(), HttpStatus.OK);
    }

    /**
     * Récupère les informations administratives d'un étudiant.
     */
    @Operation(summary = "Récupérer les informations administratives d'un étudiant")
    @GetMapping("/infosAdmEtu")
    public ResponseEntity<EtudiantInfoAdm> infosAdmEtuV2(@RequestParam("numEtud") String numEtud) {
        return new ResponseEntity<>(new EtudiantInfoAdm(), HttpStatus.OK);
    }

    /**
     * Récupère la liste des étudiants par étape et diplôme.
     */
    @Operation(summary = "Récupérer la liste des étudiants par étape et diplôme")
    @GetMapping("/listEtuParEtapeEtDiplome")
    public ResponseEntity<List<ApprenantDto>> recupererListeEtuParEtpEtDiplome(
            @RequestParam("codeComposante") String codeComposante,
            @RequestParam("annee") String annee,
            @RequestParam("codeEtape") String codeEtape,
            @RequestParam("versionEtape") String versionEtape,
            @RequestParam("codeDiplome") String codeDiplome,
            @RequestParam("versionDiplome") String versionDiplome,
            @RequestParam(value = "codEtu", required = false) String codEtu,
            @RequestParam(value = "nom", required = false) String nom,
            @RequestParam(value = "prenom", required = false) String prenom) {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    /**
     * Récupère les étapes d'inscription d'un étudiant pour une année.
     */
    @Operation(summary = "Récupérer les étapes d'inscription d'un étudiant pour une année")
    @GetMapping("/studentEtapeVets")
    public ResponseEntity<Map<String, String>> studentEtapeVets(
            @RequestParam("codEtud") String codeEtud,
            @RequestParam("annee") String annee) {
        return new ResponseEntity<>(new LinkedHashMap<>(), HttpStatus.OK);
    }

    /**
     * Récupère la liste des étapes d'inscription d'un étudiant.
     */
    @Operation(summary = "Récupérer la liste des étapes d'inscription d'un étudiant")
    @GetMapping("/studentListeEtapeInscription")
    public ResponseEntity<List<EtapeInscription>> studentListeEtapesInscription(
            @RequestParam("codEtud") String codEtud,
            @RequestParam("annee") String annee) {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    /**
     * Récupère la liste des éléments pédagogiques de stage pour une étape.
     */
    @Operation(summary = "Récupérer la liste des éléments pédagogiques de stage pour une étape")
    @GetMapping("/studentListeElpStage")
    public ResponseEntity<List<ElementPedagogique>> studentListeElpStage(
            @RequestParam("codeEtape") String codeEtape,
            @RequestParam("versionEtape") String versionEtape) {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    /**
     * Récupère la référence de l'établissement.
     */
    @Operation(summary = "Récupérer la référence de l'établissement")
    @GetMapping("/etablissementReference")
    public ResponseEntity<EtabRef> etablissementReference() {
        return new ResponseEntity<>(new EtabRef(), HttpStatus.OK);
    }

    /**
     * Récupère la liste des étapes de référence.
     */
    @Operation(summary = "Récupérer la liste des étapes de référence")
    @GetMapping("/etapesReference")
    public ResponseEntity<Map<String, String>> getEtapesRef() {
        return new ResponseEntity<>(new HashMap<>(), HttpStatus.OK);
    }

    /**
     * Récupère la liste des diplômes de référence.
     */
    @Operation(summary = "Récupérer la liste des diplômes de référence")
    @GetMapping("/diplomesReference")
    public ResponseEntity<List<DiplomeReduitDto>> getDiplomesRef() {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    /**
     * Récupère la liste des diplômes de référence par composante et année.
     */
    @Operation(summary = "Récupérer la liste des diplômes de référence par composante et année")
    @GetMapping("/diplomesReferenceParComposanteEtAnnee")
    public ResponseEntity<List<DiplomeReduitDto>> getDiplomesRefParComposanteEtAnnee(
            @RequestParam("codeComposante") String codeComposante,
            @RequestParam("codeAnnee") String codeAnnee) {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    /**
     * Récupère la liste des composantes principales de référence.
     */
    @Operation(summary = "Récupérer la liste des composantes principales de référence")
    @GetMapping("/composantesPrincipalesRef")
    public ResponseEntity<Map<String, String>> composantesPrincipalesRef() {
        return new ResponseEntity<>(new HashMap<>(), HttpStatus.OK);
    }

    /**
     * Récupère le signataire de la composante.
     */
    @Operation(summary = "Récupérer le signataire de la composante")
    @GetMapping("/composanteSignaitaireRef")
    public ResponseEntity<SignataireRef> signataireRef(
            @RequestParam(value = "composante", defaultValue = "SCO") String composante) {
        return new ResponseEntity<>(new SignataireRef(), HttpStatus.OK);
    }
    

    @Operation(summary = "Récupérer regimesInscriptions")
    @GetMapping("/regimesInscriptions")
	public ResponseEntity<Map<String, String>> regimesInscriptions() {
		// TODO Auto-generated method stub
		return null;
	}

    public String getUniversityCode() {
        return universityCode;
    }

    public void setUniversityCode(String universityCode) {
        this.universityCode = universityCode;
    }

	@Override
	public ResponseEntity<EtudiantInfoAdm> InfosAdmEtuV2(String numEtud) {
		// TODO Auto-generated method stub
		return new ResponseEntity<EtudiantInfoAdm>(new EtudiantInfoAdm(), null);
	}

	@Override
	public ResponseEntity<SignataireRef> signaitaireRef(String composante) {
		// TODO Auto-generated method stub
		return new ResponseEntity<SignataireRef>(new SignataireRef(), null);
	}

}
