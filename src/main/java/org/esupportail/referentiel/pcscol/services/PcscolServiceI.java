package org.esupportail.referentiel.pcscol.services;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.esupportail.referentiel.beans.ApogeeMap;
import org.esupportail.referentiel.beans.ApprenantDto;
import org.esupportail.referentiel.beans.EtudiantInfoAdm;
import org.esupportail.referentiel.beans.EtudiantRef;
import org.esupportail.referentiel.beans.SignataireRef;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface PcscolServiceI {

	/**
	 * 
	 * @param codeStructure
	 * @param codeApprenant
	 * @return
	 */
	public EtudiantInfoAdm lireEtudiantInfoAdm(String codeStructure, String codeApprenant);

	/**
	 * 
	 * @param codeStructure
	 * @param codeApprenant
	 * @return
	 */
	public EtudiantRef lireEtudiantRef(String codeStructure, String codeApprenant, String annee);

	/**
	 * 
	 * @param codeStructure
	 * @param codePeriode
	 * @param uniquementOuvrableAInscription
	 * @param uniquementOuvrableAuChoixDuCursus
	 * @return
	 */
	public HashMap<String, String> lireMapFormations(String codeStructure, String codePeriode,
			boolean uniquementOuvrableAInscription, boolean uniquementOuvrableAuChoixDuCursus);

	/**
	 * 
	 * @param codeStructure
	 * @return
	 */
	public Map<String, String> lireMapStructures();

	/**
	 * recherche des Inscriptions Administratives et Inscriptions Pedagogiques.
	 * 
	 * @param codEtud
	 * @return ApogeeMap
	 */
	public ApogeeMap recupererIaIpParEtudiantAnnee(String codeStructure, String codeApprenant, String annee);
	
	
	public List<String> recupererAnneesIa(String codeStructure,String codeEtud);

	/**
	 * 
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
	public List<ApprenantDto> recupererListeEtuParEtpEtDiplome(String codeComposante, String annee, String codeEtape,
			String versionEtape, String codeDiplome, String versionDiplome, String codEtu, String nom, String prenom);
	
	/**
	 * 
	 * @param composante
	 * @return
	 */
	public SignataireRef signaitaireRef(String composante);

}
