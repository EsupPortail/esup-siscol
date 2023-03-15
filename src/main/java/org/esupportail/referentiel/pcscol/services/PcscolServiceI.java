package org.esupportail.referentiel.pcscol.services;

import java.util.LinkedHashMap;
import java.util.Map;

import org.esupportail.referentiel.beans.ApogeeMap;
import org.esupportail.referentiel.beans.EtudiantInfoAdm;
import org.esupportail.referentiel.beans.EtudiantRef;

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
	public EtudiantRef lireEtudiantRef(String codeStructure, String codeApprenant);

	/**
	 * 
	 * @param codeStructure
	 * @param codePeriode
	 * @param uniquementOuvrableAInscription
	 * @param uniquementOuvrableAuChoixDuCursus
	 * @return
	 */
	public LinkedHashMap<String, String> lireMapFormations(String codeStructure, String codePeriode,
			boolean uniquementOuvrableAInscription, boolean uniquementOuvrableAuChoixDuCursus);

	/**
	 * 
	 * @param codeStructure
	 * @return
	 */
	public Map<String, String> lireMapStructures(String codeStructure);
	
	/**
	 * recherche des Inscriptions Administratives et Inscriptions Pedagogiques.
	 * 
	 * @param codEtud
	 * @return ApogeeMap
	 */
	public ApogeeMap recupererIaIpParEtudiantAnnee(String codeStructure, String codeApprenant, String annee);

}
