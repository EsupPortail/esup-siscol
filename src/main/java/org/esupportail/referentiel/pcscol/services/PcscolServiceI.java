package org.esupportail.referentiel.pcscol.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.esupportail.referentiel.beans.ApogeeMap;
import org.esupportail.referentiel.beans.ApprenantDto;
import org.esupportail.referentiel.beans.EtabRef;
import org.esupportail.referentiel.beans.EtudiantInfoAdm;
import org.esupportail.referentiel.beans.EtudiantRef;
import org.esupportail.referentiel.beans.SignataireRef;

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
	public Map<String, String> lireMapFormations(String codeStructure, String codePeriode,boolean uniquementOuvrableAInscription);

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
	public ApogeeMap recupererIaIpParEtudiantAnnee(String codeStructure, String codeApprenant, List<String> annee,List<String> statutInscriptionChargements);
	
	
	public List<String> recupererAnneesIa(String codeStructure,String codeEtud);

	
	/**
	 * 
	 * @param composante
	 * @return
	 */
	public SignataireRef signaitaireRef(String composante);

	EtabRef lireEtabRef();

}
