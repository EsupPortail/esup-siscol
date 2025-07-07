package org.esupportail.referentiel.pcscol.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.esupportail.referentiel.pcscol.api.MaquettesApi;
import org.esupportail.referentiel.pcscol.api.ObjetsMaquetteApi;
import org.esupportail.referentiel.pcscol.invoker.ApiException;
import org.esupportail.referentiel.pcscol.odf.model.ObjetMaquetteSummary;
import org.esupportail.referentiel.pcscol.odf.model.Pageable;
import org.esupportail.referentiel.pcscol.odf.model.PagedObjetMaquetteSummaries;
import org.esupportail.referentiel.pcscol.odf.model.TypeObjetMaquette;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "app.mode_pegase")
public class OffreFormationServicePartielEtapes {

	@Autowired
	private EspaceService espaceService;

	final transient Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ObjetsMaquetteApi objetsMaquetteApi;

	@Autowired
	private MaquettesApi maquettesApi;

	@Autowired
	private PcscolService pcScoleService;

	@Value("${app.pcscol.typeObjetFormationChargementFormations:null}")
	String typeObjetFormationChargementFormations;
	
	@Value("${app.pcscol.codeStructure}")
	String codeStructure = "ETAB00";

	public EspaceService getEspaceService() {
		return espaceService;
	}
	/**
	 * 
	 * @param codeStructure
	 * @param espace
	 * @param typeObjetMaquette
	 * @param racine
	 * @param typeObjetFormation
	 * @param ids
	 * @param piaSeulement
	 * @param piaActif
	 * @param valideSeulement
	 * @param mutualis
	 * @return
	 */
	public List<ObjetMaquetteSummary> rechercheObjetMaquetteSummary(String codeStructure, String espaceUUID,
			List<TypeObjetMaquette> typeObjetMaquette, Boolean racine, String typeObjetFormation, List<UUID> ids,
			Boolean piaSeulement, Boolean piaActif, Boolean valideSeulement, Boolean mutualis) {
		Pageable pageable = new Pageable();
		pageable.setPage(0);
		pageable.setTaille(10);
		String r = null;

		List<ObjetMaquetteSummary> result = new ArrayList<>();
		;
		logger.debug(
				"rechercheObjetMaquetteSummary codeStructure {} espaceUUID {} typeObjetMaquette {} racine {} typeObjetFormation {} ids {} piaSeulement {} piaActif {} valideSeulement {} mutualis {}",
				codeStructure, espaceUUID, typeObjetMaquette, racine, typeObjetFormation, ids, piaSeulement, piaActif,
				valideSeulement, mutualis);
		try {
			PagedObjetMaquetteSummaries response = objetsMaquetteApi.rechercherObjetMaquette(codeStructure, pageable, r,
					espaceUUID, typeObjetMaquette, racine, typeObjetFormation, ids, piaSeulement, piaActif, valideSeulement,
					mutualis);
			logger.debug("{} {}  {} ", "rechercheObjetMaquetteSummary", " => response size",
					response.getItems().size());
			
			if ( response.getItems() != null) {
				result.addAll(response.getItems());
				if (response.getTotalPages() > 1) {
					for (int i = 1; i < response.getTotalPages(); i++) {
						pageable.setPage(i);
						logger.debug("rechercheObjetMaquetteSummary page {}", i);
						response = objetsMaquetteApi.rechercherObjetMaquette(codeStructure, pageable, r, espaceUUID,
								typeObjetMaquette, racine, typeObjetFormation, ids, piaSeulement, piaActif,
								valideSeulement, mutualis);
						if (response != null && response.getItems() != null) {
							logger.debug("{} {}  {} ", "rechercheObjetMaquetteSummary", " => response size", response.getItems().size());
							result.addAll(response.getItems());
						}
					}

				}
			}

			return result;
		} catch (ApiException e) {
			logger.error("Error in rechercheObjetMaquetteSummary", e);
			return null;
		}

	}
	/**
	 * 
	 * @param codeStructure
	 * @param espace
	 * @param piaSeulement
	 * @param piaActif
	 * @param valideSeulement
	 * @return
	 */
	public List<ObjetMaquetteSummary> rechercheObjetMaquetteSummaryParPia(String codeStructure, String espaceUUID,
			Boolean piaSeulement, Boolean piaActif, Boolean valideSeulement) {

		List<TypeObjetMaquette> typeObjetMaquette = new ArrayList<>();
		Boolean racine = null;
		List<UUID> ids = null;
		String typeObjetFormation;
		typeObjetFormation = null;
		return rechercheObjetMaquetteSummary(codeStructure, espaceUUID, typeObjetMaquette, racine, typeObjetFormation, ids,
				piaSeulement, piaActif, valideSeulement, null);
	}

	/**
	 * 
	 * @param codeStructure
	 * @param espaceUUID
	 * @return
	 * [UE, PARCOURS-TYPE, SEMESTRE, ANNEE, ENS, EC]
	 */
	public List<ObjetMaquetteSummary> rechercheObjetMaquetteSummaryParPiaSeaulement(String codeStructure,
			String espaceUUID) {
		boolean racine = false;
		List<UUID> ids = new ArrayList<UUID>();
		String typeObjetFormation = null;
		List<TypeObjetMaquette> typeObjetMaquette = new ArrayList<TypeObjetMaquette>();
		//typeObjetMaquette.add(TypeObjetMaquette.OBJET_FORMATION);
		//typeObjetMaquette.add(TypeObjetMaquette.FORMATION);
		
		Boolean piaSeulement = true;
		Boolean piaActif = true;
		Boolean valideSeulement = true;
		logger.debug(
				"rechercheObjetMaquetteSummaryParPiaSeaulement codeStructure {} espaceUUID {} piaSeulement {} piaActif {} valideSeulement {}",
				codeStructure, espaceUUID, piaSeulement, piaActif, valideSeulement);
		return rechercheObjetMaquetteSummary(codeStructure, espaceUUID, typeObjetMaquette, racine, typeObjetFormation, ids,
				piaSeulement, piaActif, valideSeulement, null);
	}
	
	
	/**
	 * 
	 * @param objetMaquetteSummaries
	 * @param espaceCode
	 * @return
	 */
	public HashMap<String, String> objetMaquetteSummaryMap(List<ObjetMaquetteSummary> objetMaquetteSummaries,
			String espaceCode) {
		HashMap<String, String> map = new HashMap<>();
		for (ObjetMaquetteSummary obj : objetMaquetteSummaries) {
			//map.put(obj.getCode() + ";" + espaceCode, obj.getLibelle());
			try {
				map.put(obj.getCode() + ";" + codeStructure, obj.getLibelle());
			} catch (Exception e) {
				logger.error("Error in objetMaquetteSummaryMap for code {} and espaceCode {}: {}", obj.getCode(),
						espaceCode, e.getMessage());
			}
		}
		return map;

	}

	public void setEspaceService(EspaceService espaceService) {
		this.espaceService = espaceService;
	}

	public ObjetsMaquetteApi getObjetsMaquetteApi() {
		return objetsMaquetteApi;
	}

	public void setObjetsMaquetteApi(ObjetsMaquetteApi objetsMaquetteApi) {
		this.objetsMaquetteApi = objetsMaquetteApi;
	}

	public MaquettesApi getMaquettesApi() {
		return maquettesApi;
	}

	public void setMaquettesApi(MaquettesApi maquettesApi) {
		this.maquettesApi = maquettesApi;
	}

	public PcscolService getPcScoleService() {
		return pcScoleService;
	}

	public void setPcScoleService(PcscolService pcScoleService) {
		this.pcScoleService = pcScoleService;
	}

	public String getTypeObjetFormationChargementFormations() {
		return typeObjetFormationChargementFormations;
	}

	public void setTypeObjetFormationChargementFormations(String typeObjetFormationChargementFormations) {
		this.typeObjetFormationChargementFormations = typeObjetFormationChargementFormations;
	}

}
