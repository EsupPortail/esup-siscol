package org.esupportail.referentiel.pcscol.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.esupportail.referentiel.pcscol.api.MaquettesApi;
import org.esupportail.referentiel.pcscol.api.ObjetsMaquetteApi;
import org.esupportail.referentiel.pcscol.invoker.ApiException;
import org.esupportail.referentiel.pcscol.odf.model.DescripteursObjetFormation;
import org.esupportail.referentiel.pcscol.odf.model.EnfantsStructure;
import org.esupportail.referentiel.pcscol.odf.model.Espace;
import org.esupportail.referentiel.pcscol.odf.model.MaquetteStructure;
import org.esupportail.referentiel.pcscol.odf.model.ObjetMaquetteDetail;
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
public class OffreFormationService {

//	@Autowired
//	private EspacesApi espacesApi;

	@Autowired
	private EspaceService espaceService;

	final transient Logger logger = LoggerFactory.getLogger(this.getClass());
	private Boolean mutualise = null;

	@Autowired
	private ObjetsMaquetteApi objetsMaquetteApi;

	@Autowired
	private MaquettesApi maquettesApi;

	@Autowired
	private PcscolService pcScoleService;

	@Value("${app.pcscol.typeObjetFormationChargementFormations}")
	String typeObjetFormationChargementFormations;

	private Boolean piaActif = null;
	private Boolean piaSeulement = false;
	private Boolean valideSeulement = true;

//	public List<Espace> checherPeriodeParCode(String codeStructure, String code) throws ApiException {
//		Pageable pageable = new Pageable();
//		pageable.setPage(0);
//		pageable.setTaille(20);
//		String r = code;
//		TypeEspace type = null;
//		// ( String codeStructure, Pageable pageable, String r, TypeEspace type, Boolean
//		// actif)
//		PagedEspaces espaces = espacesApi.rechercherEspaces(codeStructure, pageable, r, type, true);
//		logger.debug("nbr d'Espaces pour le code   " + r + " :" + espaces.getTotalElements());
//		return espaces.getItems();
//
//	}

	public MaquetteStructure lireMaquette(String codeStructure, String idMaquette) {
		logger.debug("lireMaquette( {} ,{} )", codeStructure, idMaquette);
		try {
			MaquetteStructure response = maquettesApi.lireStructureMaquette(codeStructure, UUID.fromString(idMaquette));
			return response;
		} catch (ApiException e) {
			logger.error(e.getMessage());
			return null;
		}
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
	 * @param newParam           TODO cheraga
	 * @return
	 * @throws ApiException
	 */
	public List<ObjetMaquetteSummary> rechercheObjetMaquetteSummary(String codeStructure,
			List<TypeObjetMaquette> typeObjetMaquette, Boolean racine, String typeObjetFormation, List<UUID> ids,
			String espace, boolean piaSeulement) throws ApiException {
		logger.debug("rechercheObjetMaquetteSummary( {} ,{} ,{},{},{} ,{} )", codeStructure, typeObjetMaquette, racine,
				typeObjetFormation, ids, espace);

		List<ObjetMaquetteSummary> objetMaquetteSummaries = new ArrayList<>();
		Pageable pageable = new Pageable();
		pageable.setPage(0);
		pageable.setTaille(20);
		String r = null;
		logger.debug(
				"codeStructure: {},  r: {}, espace: {}, typeObjetMaquette: {}, racine: {}, typeObjetFormation: {}, ids: {}, piaSeulement: {}, piaActif: {}, valideSeulement: {},mutualise: {}",
				codeStructure, r, espace, typeObjetMaquette, racine, typeObjetFormation, ids, piaSeulement, piaActif,
				valideSeulement, mutualise);
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
		List<ObjetMaquetteSummary> listMaquetteSummaries = new ArrayList<ObjetMaquetteSummary>();

		pageable.setPage(0);
		pageable.setTaille(10);
		UUID idEsapce = espaceService.chercherEspaceFromCode(codeStructure, codePeriode);

		PagedObjetMaquetteSummaries response = objetsMaquetteApi.rechercherObjetMaquette(codeStructure, pageable,
				codeFormation, idEsapce.toString(), null, null, null, null, piaSeulement, piaActif, valideSeulement,
				mutualise);
		listMaquetteSummaries.addAll(response.getItems());

		if (response.getTotalPages() > 1) {
			for (int i = 1; i < response.getTotalPages(); i++) {
				pageable.setPage(i);
				PagedObjetMaquetteSummaries responsePartiel = objetsMaquetteApi.rechercherObjetMaquette(codeStructure,
						pageable, codeFormation, idEsapce.toString(), null, null, null, null, piaSeulement, piaActif,
						valideSeulement, mutualise);
				listMaquetteSummaries.addAll(responsePartiel.getItems());
			}

		}
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
				espace, piaSeulement);

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
				typeObjetFormation, null, espace, piaSeulement);
		allObjetMaquetteDetail.forEach(m -> {
			listIds.add(m.getId().toString());
		});

		return listIds;
	}

	/**
	 * Recherche ID formation type annee
	 * 
	 * @param codeStructure
	 * @param espace
	 * @return
	 */
	public List<String> rechercherIDSObjetFormationAnnee(String codeStructure, String espace) {

		TypeObjetMaquette type = TypeObjetMaquette.OBJET_FORMATION;
		boolean racine = false;
		String typeObjetFormation = "ANNEE";
		try {
			return rechercherIdObjetMaquette(codeStructure, type, racine, typeObjetFormation, espace);
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
			String typeObjetFormation, String espace, boolean piaSeulement) throws ApiException {

		Map<String, String> mapVDI = new HashMap<>();
		List<TypeObjetMaquette> tom = new ArrayList<TypeObjetMaquette>();
		tom.add(type);
		List<UUID> ids = null;
		List<ObjetMaquetteSummary> allObjetMaquetteDetail = rechercheObjetMaquetteSummary(codeStructure, tom, racine,
				typeObjetFormation, ids, espace, piaSeulement);

		allObjetMaquetteDetail.forEach(f -> {

			try {

				ObjetMaquetteDetail objectMaqette = objetsMaquetteApi.lireObjetMaquette(codeStructure, f.getId());

				UUID idEspace = objectMaqette.getEspace();
				// possibilite d'avoir un espace different ???
				/**
				 * TODO gestion des espaces
				 */
				Espace esp = espaceService.getEspacesApi().lireEspace(codeStructure, idEspace);
				mapVDI.put(f.getCode() + ";" + esp.getCode(), f.getLibelle());

			} catch (ApiException e) {
				logger.error(e.getMessage() + " : " + e.getCode());
			}
		});

		return mapVDI;

	}

	/**
	 * 
	 * @param codeStructure
	 * @param type
	 * @param racine
	 * @param typeObjetFormation
	 * @param espace
	 * @return
	 * @throws ApiException
	 */
	public Map<String, String> rechercherObjetMaquettePointInscriptionAdministratif(String codeStructure,
			TypeObjetMaquette type, boolean racine, String typeObjetFormation, String espace) throws ApiException {

		Map<String, String> mapVDI = new HashMap<>();
		List<TypeObjetMaquette> tom = new ArrayList<TypeObjetMaquette>();
		tom.add(type);
		boolean piaSeulement = false;
		List<ObjetMaquetteSummary> allObjetMaquetteDetail = rechercheObjetMaquetteSummary(codeStructure, tom, null,
				typeObjetFormation, null, espace, piaSeulement);

		allObjetMaquetteDetail.forEach(f -> {

			try {

				ObjetMaquetteDetail objectMaqette = objetsMaquetteApi.lireObjetMaquette(codeStructure, f.getId());
				objectMaqette.getContextes().forEach(c -> {

					if (c.getPointInscriptionAdministrative() != null) {
						if (c.getPointInscriptionAdministrative().getActif()) {
							UUID idEspace = objectMaqette.getEspace();
							// possibilite d'avoir un espace different ???
							/**
							 * TODO gestion des espaces
							 */
							Espace esp;
							try {
								esp = espaceService.getEspacesApi().lireEspace(codeStructure, idEspace);

								mapVDI.put(f.getCode() + ";" + esp.getCode(), f.getLibelle());
							} catch (ApiException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					}

				});

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

		logger.debug("Recherche de diplomes pour la periode : " + periode);

		List<TypeObjetMaquette> typesObjetMaquette = new ArrayList<TypeObjetMaquette>();

		boolean racine = false;
		String typeObjetFormation = null;
		List<UUID> ids = null;

		List<Espace> espaces = espaceService.checherPeriodeParCode(codeStructure, periode);
		if (espaces != null && !espaces.isEmpty()) {
			UUID idPeriode = null;

			for (Espace espace : espaces) {
				if (espace.getCode().equals(periode)) {
					idPeriode = espace.getId();
					logger.debug("idPeriode : " + idPeriode);
				}
			}

			typesObjetMaquette.add(TypeObjetMaquette.FORMATION);
			boolean piaSeulement = false;
			List<ObjetMaquetteSummary> allObjetMaquetteDetail = rechercheObjetMaquetteSummary(codeStructure,
					typesObjetMaquette, racine, typeObjetFormation, ids, idPeriode.toString(), piaSeulement);
			logger.debug(periode + " nbr diplomes : " + allObjetMaquetteDetail.size());
			return allObjetMaquetteDetail;
		} else
			return new ArrayList<ObjetMaquetteSummary>();
	}

	// rechercherObjetMaquetteFormation
	/**
	 * 
	 * @param codeStructure
	 * @return
	 * @throws ApiException cheraga
	 */
	public Map<String, String> rechercherObjetMaquetteFormation(String codeStructure, String periode)
			throws ApiException {

		UUID idPeriode = espaceService.chercherEspaceFromCode(codeStructure, periode);
		return rechercherObjetMaquette(codeStructure, TypeObjetMaquette.FORMATION, true, null, idPeriode.toString(),
				piaSeulement);
	}

	/**
	 * 
	 * @param codeStructure
	 * @return
	 * @throws ApiException
	 */
	public Map<String, String> rechercherObjetMaquetteObjetFormation(String codeStructure, String periode,
			boolean piaSeulement) throws ApiException {

		UUID idPeriode = espaceService.chercherEspaceFromCode(codeStructure, periode);
		if (idPeriode == null) {

			logger.error("ID periode== null pour " + periode);
			return new HashMap<String, String>();
		}

		// typeObjetFormation [UE, PARCOURS-TYPE, SEMESTRE, ANNEE, ENS, EC]
		//List<String> listTypeObjetFormation = Arrays.asList(typeObjetFormationChargementFormations.split("[,;\\s]+"));

		Map<String, String> objetMaquettes = new HashMap<String, String>();
		boolean racine = false;
		String typeObjetFormation = null;
		objetMaquettes = rechercherObjetMaquette(codeStructure, TypeObjetMaquette.OBJET_FORMATION, racine,
				typeObjetFormation, idPeriode.toString(), piaSeulement);
//		listTypeObjetFormation.forEach(typeObjetFormation -> {
//			Map<String, String> objetMaquettesPatiel = new HashMap<String, String>();
//			
//			
//			try {
//				objetMaquettesPatiel = rechercherObjetMaquette(codeStructure, TypeObjetMaquette.OBJET_FORMATION, false,
//						typeObjetFormation, idPeriode.toString());
//				logger.debug("objetMaquettesPatiel type  {} periode {} nbr retuourne {}", typeObjetFormation, idPeriode,
//						objetMaquettesPatiel.size());
//				objetMaquettes.putAll(objetMaquettesPatiel);
//			} catch (ApiException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		});

		logger.debug(" Espace null pour la periode " + periode);
		return objetMaquettes;

	}

	/**
	 * 
	 * @param codeStructure
	 * @return
	 * @throws ApiException
	 */
	public Map<String, String> rechercherObjetMaquetteObjetFormationSEMESTRE(String codeStructure, String periode)
			throws ApiException {
		UUID idPeriode = espaceService.chercherEspaceFromCode(codeStructure, periode);

		return rechercherObjetMaquette(codeStructure, TypeObjetMaquette.OBJET_FORMATION, false, "SEMESTRE",
				idPeriode.toString(), piaSeulement);
	}

	/**
	 * 
	 * @param codeStructure
	 * @return
	 * @throws ApiException
	 */
	public List<ObjetMaquetteDetail> rechercherObjetMaquetteObjetFormationStage(String codeStructure, String periode)
			throws ApiException {
		UUID idPeriode = espaceService.chercherEspaceFromCode(codeStructure, periode);

		List<ObjetMaquetteDetail> listObjetMaquetteDetail = new ArrayList<ObjetMaquetteDetail>();
		List<TypeObjetMaquette> tom = new ArrayList<TypeObjetMaquette>();
		tom.add(TypeObjetMaquette.OBJET_FORMATION);

		List<ObjetMaquetteSummary> allObjetMaquetteDetail = rechercheObjetMaquetteSummary(codeStructure, tom, null,
				null, null, idPeriode.toString(), piaSeulement);
		if (allObjetMaquetteDetail != null) {
			allObjetMaquetteDetail.forEach(omd -> {
				try {
					ObjetMaquetteDetail objetMaquetteDetail = objetsMaquetteApi.lireObjetMaquette(codeStructure,
							omd.getId());
					if (objetMaquetteDetail.getDescripteursObjetMaquette() != null) {

						DescripteursObjetFormation om = (DescripteursObjetFormation) objetMaquetteDetail
								.getDescripteursObjetMaquette();
						if (om.getStage()) {
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
		UUID idPeriode = espaceService.chercherEspaceFromCode(codeStructure, periode);

		return rechercherObjetMaquette(codeStructure, TypeObjetMaquette.OBJET_FORMATION, false, "UE",
				idPeriode.toString(), piaSeulement);
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

		List<TypeObjetMaquette> typeObjetMaquette = new ArrayList<TypeObjetMaquette>();
		// typeObjetFormation [UE, PARCOURS-TYPE, SEMESTRE, ANNEE, ENS, EC]
		String typeObjetFormation = "ENS";
		Boolean racine = true;

		List<ObjetMaquetteSummary> allObjetMaquetteDetail = rechercheObjetMaquetteSummary(codeStructure,
				typeObjetMaquette, racine, typeObjetFormation, uids, espace, piaSeulement);

		return allObjetMaquetteDetail;
	}

	/**
	 * 
	 * @param codeStructure
	 * @param codeEtape
	 * @param versionEtape
	 * @return
	 */
	public List<ObjetMaquetteDetail> objetsMaquetteDetailFromCode(String codeStructure, String codeEtape,
			String versionEtape) {
		List<ObjetMaquetteSummary> objetMaquetteSummaries;
		List<ObjetMaquetteDetail> llObjetMaquetteDetail = new ArrayList<ObjetMaquetteDetail>();
		try {
			objetMaquetteSummaries = rechercheObjetMaquetteSummary(codeStructure, codeEtape, versionEtape);
			ObjetMaquetteSummary objetMaquetteSummary = null;
			for (ObjetMaquetteSummary oms : objetMaquetteSummaries) {
				if (oms.getCode().equalsIgnoreCase(codeEtape)) {
					objetMaquetteSummary = oms;
					break;
				}
			}
			UUID id = objetMaquetteSummary.getId();
			llObjetMaquetteDetail = listeEnfantsObjectMaquetteStage(codeStructure, id.toString());
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
		return llObjetMaquetteDetail;

	}

	/**
	 * 
	 * @param codeStructure
	 * @param idMaquetteStructure
	 * @return
	 */
	public List<ObjetMaquetteDetail> listeEnfantsObjectMaquetteStage(String codeStructure, String idMaquetteStructure) {
		MaquetteStructure maquetteStructure = lireMaquette(codeStructure, idMaquetteStructure);

		List<ObjetMaquetteDetail> listeEnfantsStage = listeEnfantsObjectMaquetteStage(codeStructure,
				maquetteStructure.getRacine().getEnfants());
		return listeEnfantsStage;
	}

	/**
	 * 
	 * @param codeStructure
	 * @param enfantsStructure
	 * @return
	 */
	public List<ObjetMaquetteDetail> listeEnfantsObjectMaquetteStage(String codeStructure,
			List<EnfantsStructure> enfantsStructure) {
		List<ObjetMaquetteDetail> stagesObjectMaquette = new ArrayList<ObjetMaquetteDetail>();
		enfantsStructure.forEach(e -> {
			listeEnfantsObjectMaquetteStage(codeStructure, e, stagesObjectMaquette);
		});
		return stagesObjectMaquette;
	}

	/**
	 * 
	 * @param codeStructure
	 * @param enfantStructure
	 * @param stagesObjectMaquette
	 */
	public void listeEnfantsObjectMaquetteStage(String codeStructure, EnfantsStructure enfantStructure,
			List<ObjetMaquetteDetail> stagesObjectMaquette) {

		try {

			ObjetMaquetteDetail objectMaqette = objetsMaquetteApi.lireObjetMaquette(codeStructure,
					enfantStructure.getObjetMaquette().getId());
			DescripteursObjetFormation dom = (DescripteursObjetFormation) objectMaqette.getDescripteursObjetMaquette();
			if (dom.getStage() != null && dom.getStage())
				stagesObjectMaquette.add(objectMaqette);
		} catch (ApiException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassCastException e2) {
			logger.warn(e2.getMessage());
		}

		List<EnfantsStructure> enfants = enfantStructure.getObjetMaquette().getEnfants();

		enfants.forEach(e -> {
			listeEnfantsObjectMaquetteStage(codeStructure, e, stagesObjectMaquette);
		});

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

	public EspaceService getEspaceService() {
		return espaceService;
	}

	public void setEspaceService(EspaceService espaceService) {
		this.espaceService = espaceService;
	}

}
