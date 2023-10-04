package org.esupportail.referentiel.services;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import org.esupportail.referentiel.beans.ApogeeMap;
import org.esupportail.referentiel.beans.EtapeInscription;
import org.esupportail.referentiel.beans.EtudiantRef;

import gouv.education.apogee.commun.client.ws.AdministratifMetier.InsAdmAnuDTO2;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.CoordonneesDTO2;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.IdentifiantsEtudiantDTO2;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.InfoAdmEtuDTO3;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.InfoAdmEtuDTO4;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.DiplomeDTO3;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.ListeElementPedagogiDTO2;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.VersionDiplomeDTO3;
import gouv.education.apogee.commun.client.ws.ReferentielMetier.ComposanteDTO3;

/**
 * @author dhouillo
 *
 */
public interface StudentDataRepositoryDao extends Serializable {
	/**
	 * @param universityCode
	 * @param id
	 * @return EtudiantRef
	 */
	public EtudiantRef getEtudiantRef(String universityCode, String id);

	/**
	 * @param universityCode
	 * @param id
	 * @return EtudiantRef
	 */
	public EtudiantRef getEtudiantRefByNum(String universityCode, String id, String annee);

	/**
	 * @param universityCode
	 * @param name
	 * @param firstName
	 * @return List<EtudiantRef>
	 */
	public List<EtudiantRef> getEtudiantsRefByName(String universityCode, String name, String firstName);

	/**
	 * @param codEtu
	 * @return le codInd pour le codEtu passé en paramètre
	 */
	public String recupererCodIndParCodEtu(String codEtu);

	/**
	 * 
	 * @param uid
	 * @return InfoAdmEtuDTO2
	 */
	public InfoAdmEtuDTO3 recupererInfosAdmEtuV3(String uid);

	/**
	 * 
	 * @param uid
	 * @return IdentifiantsEtudiantDTO2
	 */
	public IdentifiantsEtudiantDTO2 recupererIdentifiantsEtudiantDTO2(String uid);

	/**
	 * 
	 * @param CodEtu
	 * @param anneeCourante
	 * @return CoordonneesDTO2
	 */
	public CoordonneesDTO2 recupererCoordonneesDTO2(String CodEtu, String anneeCourante);

	
	/**
	 * 
	 * @param codeEtud
	 * @return List<String>
	 */
	public List<String> recupererAnneesIa(String codeEtud);

	/**
	 * 
	 * @param codeEtud
	 * @param annee
	 * @return List<InsAdmAnuDTO2>
	 */
	public List<InsAdmAnuDTO2> recupererIAAnnuellesV2(String codeEtud, String annee);

	/**
	 * 
	 * @param codeEtp
	 * @param versionEtp
	 * @return List<DiplomeDTO3>
	 */

	public List<DiplomeDTO3> recupererListDiplomeDTO3(String codeEtp, String versionEtp);

	/**
	 * Composante
	 * 
	 * @param codComposante
	 * @return ComposanteDTO3
	 */
	public ComposanteDTO3 recupererComposanteV2(String codComposante);

	/**
	 * Regime d'inscription Formation continue
	 * 
	 * @param codeEtud
	 * @param annee
	 * @return List<String>
	 */
	public List<String> recupererAnneesInscriptionFC(String codeEtud, String annee);

	/**
	 * Recuperation du regime d'inscription pour l'etudiant a partir de l'annee
	 * 
	 * @param codeEtud
	 * @param annee
	 * @return
	 */
	public String recupererRegimeInscriptionCPAM(String codeEtud, String annee);

	/**
	 * / Recuperation des etapes auxquelles l'etudiant est inscrit
	 * administrativement // (inscription admin etape en cours (E)) en fonction de
	 * l'annee en param
	 * 
	 * @param codEtud
	 * @param annee
	 * @return LinkedHashMap<String, String>
	 */
	public LinkedHashMap<String, String> recupererEtapesParEtudiantAnnee(String codEtud, String annee);

	/**
	 * / Recuperation des etapes auxquelles l'etudiant est inscrit
	 * administrativement // (inscription admin etape en cours (E)) en fonction de
	 * l'annee en param
	 * 
	 * @param codEtud
	 * @param annee
	 * @return LinkedHashMap<String, String>
	 */
	public LinkedHashMap<String, String> recupererEtapeVetsParEtudiantAnnee(String codEtud, String annee);

	/**
	 * recherche des Inscriptions Administratives payees de l'etudiant
	 * 
	 * @param codEtud
	 * @param annee
	 * @return LinkedHashMap<String, String>
	 * 
	 */
	@Deprecated
	public LinkedHashMap<String, String> recupererComposantesParEtudiantAnnee(String codEtud, String annee);

	/**
	 * recherche des Inscriptions Administratives payees de l'etudiant
	 * 
	 * @param codEtud
	 * @param annee
	 * @return LinkedHashMap<String, String>
	 */
	public List<EtapeInscription> recupererEtapeInscriptionParEtudiantAnnee(String codEtud, String annee);

	/**
	 * Map<VersionDiplomeDTO3, List<ListeElementPedagogiDTO2>>
	 * 
	 * @param codeEtp
	 * @param versionEtp
	 * @return LinkedHashMap<VersionDiplomeDTO3, List<ListeElementPedagogiDTO2>>
	 */
	public LinkedHashMap<VersionDiplomeDTO3, List<ListeElementPedagogiDTO2>> recupererElpsParEtape(String codeEtp,
			String versionEtp);

	/**
	 * recherche des Inscriptions Administratives et Inscriptions Pedagogiques.
	 * 
	 * @param codEtud
	 * @return ApogeeMap
	 */
	public ApogeeMap recupererIaIpParEtudiantAnnee(String codEtud, String annee);

	/**
	 * @param codInd
	 * @return le codEtu pour le codInd passe en parametre
	 */
	public String recupererCodeEtudiantParCodInd(final String codInd);

	/**
	 * @return String Year
	 */
	public String getYear();
	
	/**
	 * 
	 * @param uid
	 * @return
	 */
	public InfoAdmEtuDTO4 recupererInfosAdmEtuV4(String uid);


}
