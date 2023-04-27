package org.esupportail.referentiel.services;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.esupportail.referentiel.beans.DiplomeReduitDto;
import org.esupportail.referentiel.beans.EtabRef;
import org.esupportail.referentiel.beans.EtapeInscription;
import org.esupportail.referentiel.beans.SignataireRef;


/**
 * @author dhouillo
 *
 */
public interface StudentComponentRepositoryDao extends Serializable{


	 /**
	 * @param universityCode
	 * @return etablissementReference
	 */
	public EtabRef getEtabRef(String universityCode);
	/**
	 * @param universityCode
	 * @param Composante
	 * @return signataire de la composante
	 */
	public SignataireRef getSigCompoRef(String universityCode, String Composante);
	/**
	 * Retourne les codes etapes et leurs libelles
	 * @param universityCode
	 * @return a LinkedHashMap<String, String>, codes etapes et leurs libelles
	 */
	public Map<String, String> getEtapesRef(String universityCode);
	
	/**
	 * Retourne les codes/libelle des   composantes principales d'etudes (exemple tous les  ufrs)
	 * 
	 * @param universityCode 
	 * @return Map<String,String>
	 */
	public Map<String,String> getComposantesPrincipalesRef(String universityCode,Map<String,String> lesComposantes);
	
	/**
	 * 
	 * @param universityCode
	 * @return
	 */
	List<DiplomeReduitDto> getListeDiplomeDTO(String universityCode);
	
	

}
