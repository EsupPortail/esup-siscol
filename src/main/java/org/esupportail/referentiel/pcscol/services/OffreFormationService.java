package org.esupportail.referentiel.pcscol.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.esupportail.referentiel.pcscol.api.EspacesApi;
import org.esupportail.referentiel.pcscol.api.ObjetsMaquetteApi;
import org.esupportail.referentiel.pcscol.invoker.ApiException;
import org.esupportail.referentiel.pcscol.odf.model.DescripteursObjetFormation;
import org.esupportail.referentiel.pcscol.odf.model.Espace;
import org.esupportail.referentiel.pcscol.odf.model.ObjetFormation;
import org.esupportail.referentiel.pcscol.odf.model.ObjetMaquetteDetail;
import org.esupportail.referentiel.pcscol.odf.model.ObjetMaquetteSummary;
import org.esupportail.referentiel.pcscol.odf.model.Pageable;
import org.esupportail.referentiel.pcscol.odf.model.PagedEspaces;
import org.esupportail.referentiel.pcscol.odf.model.PagedObjetMaquetteSummaries;
import org.esupportail.referentiel.pcscol.odf.model.TypeEspace;
import org.esupportail.referentiel.pcscol.odf.model.TypeObjetMaquette;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OffreFormationService {

	@Autowired
	private EspacesApi espacesApi;

	final transient Logger logger = LoggerFactory.getLogger(this.getClass());
	private Boolean mutualise = null;

	@Autowired
	private ObjetsMaquetteApi objetsMaquetteApi;

	@Autowired
	private PcscolService pcScoleService;
	private Boolean piaActif = null;
	private Boolean piaSeulement = null;
	private Boolean valideSeulement = true;

	public List<Espace> checherPeriodeParCode(String codeStructure, String code) throws ApiException {
		Pageable pageable = new Pageable();
		pageable.setPage(0);
		pageable.setTaille(20);
		String r = code;
		TypeEspace type = null;
		// ( String codeStructure, Pageable pageable, String r, TypeEspace type, Boolean
		// actif)
		PagedEspaces espaces = espacesApi.rechercherEspaces(codeStructure, pageable, r, type, true);
		logger.debug("Espaces :  " + espaces);
		return espaces.getItems();

	}


	/**
	 * 
	 * @param codeStructure
	 * @param id
	 * @return
	 * @throws ApiException
	 */
	public ObjetMaquetteDetail recherchDescripteurMaquette(String codeStructure, String id) throws ApiException {
		ObjetMaquetteDetail objetMaquetteDetail = objetsMaquetteApi.lireObjetMaquette(codeStructure,
				UUID.fromString(id));
		return objetMaquetteDetail;

	}

	/**
	 * 
	 * @param codeStructure
	 * @param ids
	 * @return
	 */
	public List<ObjetMaquetteDetail> recherchDescripteurMaquettes(String codeStructure, List<UUID> ids) {
		final List<ObjetMaquetteDetail> listObjetMaquetteDetail = new ArrayList<ObjetMaquetteDetail>();
		if (ids != null)
			ids.forEach(id -> {
				try {
					ObjetMaquetteDetail objetMaquetteDetail = objetsMaquetteApi.lireObjetMaquette(codeStructure, id);
					listObjetMaquetteDetail.add(objetMaquetteDetail);
				} catch (ApiException e) {
					logger.error(e.getMessage() + " -> code : " + e.getCode());
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			});

		return listObjetMaquetteDetail;

	}

	/**
	 * 
	 * @param codeStructure
	 * @param ids
	 * @return
	 */
	public List<ObjetMaquetteDetail> recherchDescripteurMaquettesParIdString(String codeStructure, List<String> ids) {
		List<UUID> uuids = new ArrayList<UUID>();
		if (ids != null)
			ids.forEach(id -> {
				uuids.add(UUID.fromString(id));
			});

		return recherchDescripteurMaquettes(codeStructure, uuids);

	}

	/**
	 * 
	 * @param codeStructure
	 * @param typeObjetMaquette
	 * @param racine
	 * @param typeObjetFormation
	 * @param espace
	 * @param newParam           TODO
	 * @return
	 * @throws ApiException
	 */
	public List<ObjetMaquetteSummary> rechercheObjetMaquetteSummary(String codeStructure,
			List<TypeObjetMaquette> typeObjetMaquette, Boolean racine, String typeObjetFormation, List<UUID> ids,
			String espace) throws ApiException {
		List<ObjetMaquetteSummary> objetMaquetteSummaries = new ArrayList<>();
		Pageable pageable = new Pageable();
		pageable.setPage(0);
		pageable.setTaille(20);
		String r = null;
		PagedObjetMaquetteSummaries response = objetsMaquetteApi.rechercherObjetMaquette(codeStructure, pageable, r,
				espace, typeObjetMaquette, racine, typeObjetFormation, ids, piaSeulement, piaActif, valideSeulement,
				mutualise);

		if (response == null || response.getItems() == null) {
			logger.info("rechercheObjetMaquetteSummary vide : " + codeStructure + " : " + espace);
			return objetMaquetteSummaries;
		}
		objetMaquetteSummaries.addAll(response.getItems());
		Integer nbr_page = response.getTotalPages();
		if (nbr_page > 1) {
			for (int i = 1; i < nbr_page; i++) {
				pageable.setPage(1);
				PagedObjetMaquetteSummaries responsePartial = objetsMaquetteApi.rechercherObjetMaquette(codeStructure,
						pageable, r, espace, typeObjetMaquette, racine, typeObjetFormation, ids, piaSeulement, piaActif,
						valideSeulement, mutualise);
				objetMaquetteSummaries.addAll(responsePartial.getItems());

			}
		}

		return objetMaquetteSummaries;
	}
	
	/**
	 * 
	 * @param codeStructure
	 * @param codeFormation
	 * @param codePeriode
	 * @return
	 * @throws ApiException
	 */
	public List<ObjetMaquetteSummary> rechercheObjetMaquetteSummary(String codeStructure, String codeFormation,
			String codePeriode) throws ApiException {
		Pageable pageable = new Pageable();
		pageable.setPage(0);
		pageable.setTaille(20);
		PagedEspaces espaces = espacesApi.rechercherEspaces(codeStructure, pageable, codePeriode, null, true);
		UUID idEsapce = espaces.getItems().get(0).getId();
		PagedObjetMaquetteSummaries response = objetsMaquetteApi.rechercherObjetMaquette(codeStructure, pageable,
				codeFormation, idEsapce.toString(), null, null, null, null, piaSeulement, piaActif, valideSeulement,
				mutualise);
		logger.info("rechercheObjetMaquetteSummary : " + codeFormation + " :" + codePeriode);
		logger.info("rechercheObjetMaquetteSummary : " + response);
		return response.getItems();
	}

	/**
	 * 
	 * @param codeStructure
	 * @param typeObjetFormation
	 * @param espace
	 * @return
	 * @throws ApiException
	 */
	public List<ObjetMaquetteSummary> rechercheObjetMaquetteSummaryParTypeObjetFormation(String codeStructure,
			String typeObjetFormation, String espace) throws ApiException {
		List<ObjetMaquetteSummary> objetMaquetteSummaries = new ArrayList<>();
		List<TypeObjetMaquette> tom = new ArrayList<TypeObjetMaquette>();
		objetMaquetteSummaries = rechercheObjetMaquetteSummary(codeStructure, tom, null, typeObjetFormation, null,
				espace);

		return objetMaquetteSummaries;
	}

	/**
	 * typeObjetFormation [UE, PARCOURS-TYPE, SEMESTRE, ANNEE, ENS, EC]
	 * TypeObjetMaquette FORMATION("FORMATION"), OBJET_FORMATION("OBJET-FORMATION"),
	 * GROUPEMENT("GROUPEMENT");
	 * 
	 * @param codeStructure
	 * @param type
	 * @param racine
	 * @param espace
	 * @return
	 * @throws ApiException
	 */
	public List<String> rechercherIdObjetMaquette(String codeStructure, TypeObjetMaquette type, boolean racine,
			String typeObjetFormation, String espace) throws ApiException {

		List<String> listIds = new ArrayList<String>();
		List<TypeObjetMaquette> tom = new ArrayList<TypeObjetMaquette>();
		tom.add(type);
		List<ObjetMaquetteSummary> allObjetMaquetteDetail = rechercheObjetMaquetteSummary(codeStructure, tom, null,
				typeObjetFormation, null, espace);
		allObjetMaquetteDetail.forEach(m -> {
			listIds.add(m.getId().toString());
		});

		return listIds;
	}

	/**
	 * 
	 * @param codeStructure
	 * @param espace
	 * @return
	 */
	public List<String> rechercherIDSObjetFormationAnnee(String codeStructure,String espace){
		
		TypeObjetMaquette type=TypeObjetMaquette.OBJET_FORMATION;
		boolean racine=false;
		String typeObjetFormation="ANNEE";
		try {
			return rechercherIdObjetMaquette( codeStructure,  type,  racine,
					 typeObjetFormation,  espace);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<String>();
	}

	/**
	 * typeObjetFormation [UE, PARCOURS-TYPE, SEMESTRE, ANNEE, ENS, EC]
	 * 
	 * @param codeStructure
	 * @param type
	 * @param racine
	 * @param espace
	 * @return
	 * @throws ApiException
	 */
	public Map<String, String> rechercherObjetMaquette(String codeStructure, TypeObjetMaquette type, boolean racine,
			String typeObjetFormation, String espace) throws ApiException {

		Map<String, String> mapVDI = new HashMap<>();
		List<TypeObjetMaquette> tom = new ArrayList<TypeObjetMaquette>();
		tom.add(type);
		List<ObjetMaquetteSummary> allObjetMaquetteDetail = rechercheObjetMaquetteSummary(codeStructure, tom, null,
				typeObjetFormation, null, espace);

		allObjetMaquetteDetail.forEach(f -> {

			try {
				System.out.println(f.getId());
				ObjetMaquetteDetail objectMaqette = objetsMaquetteApi.lireObjetMaquette(codeStructure, f.getId());
				UUID idEspace = objectMaqette.getEspace();
				// possibilite d'avoir un espace different ???
				/**
				 * TODO gestion des espaces
				 */
				Espace esp = espacesApi.lireEspace(codeStructure, idEspace);
				System.out.println(objectMaqette.getCode() + ":" + f.getTypeObjetFormation());
				mapVDI.put(f.getCode() + "," + esp.getCode(), f.getLibelle());

			} catch (ApiException e) {
				logger.error(e.getMessage() + " : " + e.getCode());
			}
		});

		return mapVDI;

	}

	/**
	 * TODO
	 * 
	 * @param codeStructure
	 * @param espace
	 * @return
	 * @throws ApiException
	 */
	public List<ObjetMaquetteSummary> rechercherObjetMaquetteDiplomeReferences(String codeStructure, String periode)
			throws ApiException {
		List<TypeObjetMaquette> tom = new ArrayList<TypeObjetMaquette>();

		UUID idPeriode = checherPeriodeParCode(codeStructure, periode).get(0).getId();
		tom.add(TypeObjetMaquette.FORMATION);
		List<ObjetMaquetteSummary> allObjetMaquetteDetail = rechercheObjetMaquetteSummary(codeStructure, tom, true,
				null, null, idPeriode.toString());
		return allObjetMaquetteDetail;
	}

	// rechercherObjetMaquetteFormation
	/**
	 * 
	 * @param codeStructure
	 * @return
	 * @throws ApiException
	 */
	public Map<String, String> rechercherObjetMaquetteFormation(String codeStructure, String periode)
			throws ApiException {

		UUID idPeriode = checherPeriodeParCode(codeStructure, periode).get(0).getId();

		return rechercherObjetMaquette(codeStructure, TypeObjetMaquette.FORMATION, true, null, idPeriode.toString());
	}

	/**
	 * 
	 * @param codeStructure
	 * @return
	 * @throws ApiException
	 */
	public Map<String, String> rechercherObjetMaquetteObjetFormation(String codeStructure, String periode)
			throws ApiException {
		UUID idPeriode = checherPeriodeParCode(codeStructure, periode).get(0).getId();

		return rechercherObjetMaquette(codeStructure, TypeObjetMaquette.OBJET_FORMATION, false, "ANNEE",
				idPeriode.toString());
	}

	/**
	 * 
	 * @param codeStructure
	 * @return
	 * @throws ApiException
	 */
	public Map<String, String> rechercherObjetMaquetteObjetFormationSEMESTRE(String codeStructure, String periode)
			throws ApiException {
		UUID idPeriode = checherPeriodeParCode(codeStructure, periode).get(0).getId();

		return rechercherObjetMaquette(codeStructure, TypeObjetMaquette.OBJET_FORMATION, false, "SEMESTRE",
				idPeriode.toString());
	}

	/**
	 * 
	 * @param codeStructure
	 * @return
	 * @throws ApiException
	 */
	public List<ObjetMaquetteDetail> rechercherObjetMaquetteObjetFormationStage(String codeStructure, String periode)
			throws ApiException {
		UUID idPeriode = checherPeriodeParCode(codeStructure, periode).get(0).getId();

		List<ObjetMaquetteDetail> listObjetMaquetteDetail = new ArrayList<ObjetMaquetteDetail>();
		List<TypeObjetMaquette> tom = new ArrayList<TypeObjetMaquette>();
		tom.add(TypeObjetMaquette.OBJET_FORMATION);

		List<ObjetMaquetteSummary> allObjetMaquetteDetail = rechercheObjetMaquetteSummary(codeStructure, tom, null,
				null, null, idPeriode.toString());
		if (allObjetMaquetteDetail != null) {
			allObjetMaquetteDetail.forEach(omd -> {
				try {
					ObjetMaquetteDetail objetMaquetteDetail = objetsMaquetteApi.lireObjetMaquette(codeStructure,
							omd.getId());
					if (objetMaquetteDetail.getDescripteursObjetMaquette() != null) {

						DescripteursObjetFormation om = (DescripteursObjetFormation) objetMaquetteDetail
								.getDescripteursObjetMaquette();
						if (!om.getStage()) {
							listObjetMaquetteDetail.add(objetMaquetteDetail);
						}
					}

				} catch (ApiException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}

		return listObjetMaquetteDetail;
	}

	/**
	 * 
	 * @param codeStructure
	 * @return
	 * @throws ApiException
	 */
	public Map<String, String> rechercherObjetMaquetteObjetFormationUE(String codeStructure, String periode)
			throws ApiException {
		UUID idPeriode = checherPeriodeParCode(codeStructure, periode).get(0).getId();

		return rechercherObjetMaquette(codeStructure, TypeObjetMaquette.OBJET_FORMATION, false, "UE",
				idPeriode.toString());
	}

	/**
	 * 
	 * @param codeStructure
	 * @param ids
	 * @return
	 * @throws ApiException
	 */
	public List<ObjetMaquetteSummary> rechercherObjetMaquetteParUUIDS(String codeStructure, List<String> ids,
			String espace) throws ApiException {
		final List<UUID> uids = new ArrayList<>();

		if (ids != null) {
			ids.forEach(id -> {
				uids.add(UUID.fromString(id));
			});
		}
		System.out.println(ids);
		List<TypeObjetMaquette> typeObjetMaquette = new ArrayList<TypeObjetMaquette>();
		String typeObjetFormation = null;
		Boolean racine = true;

		// ids.add(UUID.fromString("0aeb0a77-9e60-413c-966c-023f722467a7"));

		List<ObjetMaquetteSummary> allObjetMaquetteDetail = rechercheObjetMaquetteSummary(codeStructure,
				typeObjetMaquette, racine, typeObjetFormation, uids, espace);

		return allObjetMaquetteDetail;
	}

	public void setEspacesApi(EspacesApi espacesApi) {
		this.espacesApi = espacesApi;
	}

	public void setMutualise(Boolean mutualise) {
		this.mutualise = mutualise;
	}

	public void setObjetsMaquetteApi(ObjetsMaquetteApi objetsMaquetteApi) {
		this.objetsMaquetteApi = objetsMaquetteApi;
	}

	public void setPcScoleService(PcscolService pcScoleService) {
		this.pcScoleService = pcScoleService;
	}

	public void setPiaActif(Boolean piaActif) {
		this.piaActif = piaActif;
	}

	public void setPiaSeulement(Boolean piaSeulement) {
		this.piaSeulement = piaSeulement;
	}

	public void setValideSeulement(Boolean valideSeulement) {
		this.valideSeulement = valideSeulement;
	}
	
	public EspacesApi getEspacesApi() {
		return espacesApi;
	}

	public Boolean getMutualise() {
		return mutualise;
	}

	public ObjetsMaquetteApi getObjetsMaquetteApi() {
		return objetsMaquetteApi;
	}

	public PcscolService getPcScoleService() {
		return pcScoleService;
	}

	public Boolean getPiaActif() {
		return piaActif;
	}

	public Boolean getPiaSeulement() {
		return piaSeulement;
	}

	public Boolean getValideSeulement() {
		return valideSeulement;
	}


}
