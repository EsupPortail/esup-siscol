package org.esupportail.referentiel.pcscol.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.esupportail.referentiel.pcscol.api.odf.MaquettesExterneApi;
import org.esupportail.referentiel.pcscol.api.odf.ObjetsMaquetteExterneApi;
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

	private static final int DEFAULT_PAGE_SIZE = 20;
	private static final int SMALL_PAGE_SIZE = 10;

//	@Autowired
//	private EspacesApi espacesApi;

	@Autowired
	private EspaceService espaceService;

	final  Logger logger = LoggerFactory.getLogger(this.getClass());
	private Boolean mutualise = null;

	@Autowired
	private ObjetsMaquetteExterneApi objetsMaquetteApi;

	@Autowired
	private MaquettesExterneApi maquettesApi;

	@Autowired
	private PcscolService pcScoleService;

	private Boolean piaActif = null;
	private Boolean piaSeulement = false;
	private Boolean valideSeulement = true;

	/**
	 * 
	 * @param codeStructure
	 * @param idMaquette
	 * @return
	 */
	public MaquetteStructure lireMaquette(String codeStructure, String idMaquette) {
		logger.debug("lireMaquette( {} ,{} )", codeStructure, idMaquette);
		try {
			return maquettesApi.lireStructureMaquette(codeStructure, UUID.fromString(idMaquette));
		} catch (ApiException e) {
			logger.error("Erreur lors de la lecture de la maquette avec ID {} : {}", idMaquette, e.getMessage());
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
		logger.debug("recherchDescripteurMaquette( {} ,{} )", codeStructure, id);
		if (id == null || id.isEmpty()) {
			logger.error("ID maquette est vide ou null pour la structure {} ", codeStructure);
			return null;
		}
		return objetsMaquetteApi.lireObjetMaquette(codeStructure, UUID.fromString(id));

	}

	/**
	 * 
	 * @param codeStructure
	 * @param ids
	 * @return
	 */
	public List<ObjetMaquetteDetail> recherchDescripteurMaquettes(String codeStructure, List<UUID> ids) {
		final List<ObjetMaquetteDetail> listObjetMaquetteDetail = new ArrayList<>();
		if (ids != null)
			ids.forEach(id -> {
				try {
					ObjetMaquetteDetail objetMaquetteDetail = objetsMaquetteApi.lireObjetMaquette(codeStructure, id);
					listObjetMaquetteDetail.add(objetMaquetteDetail);
				} catch (ApiException e) {
					logger.error("Erreur lors de la lecture de l'objet maquette avec ID {} : {}", id, e.getMessage());
				} catch (Exception e) {
					logger.error("Erreur inconnue lors de la lecture de l'objet maquette avec ID {} : {}", id,
							e.getMessage());
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
		logger.debug("recherchDescripteurMaquettesParIdString( {} ,{} )", codeStructure, ids);
		List<UUID> uuids = new ArrayList<>();
		if (ids != null)
			ids.forEach(id -> uuids.add(UUID.fromString(id)));

		return recherchDescripteurMaquettes(codeStructure, uuids);

	}

	/**
	 * 
	 * @param codeStructure
	 * @param typeObjetMaquette
	 * @param racine
	 * @param typeObjetFormation
	 * @param espace
	 * @param newParam
	 * @return
	 * @throws ApiException
	 */
	public List<ObjetMaquetteSummary> rechercheObjetMaquetteSummary(String codeStructure,
			List<TypeObjetMaquette> typeObjetMaquette, Boolean racine, String typeObjetFormation, List<UUID> ids,
			String espace, boolean piaSeulement) throws ApiException {
		logger.debug("rechercheObjetMaquetteSummary( {} ,{} ,{},{},{} ,{} )", codeStructure, typeObjetMaquette, racine,
				typeObjetFormation, ids, espace);
		String r = null;
		logger.debug(
				"codeStructure: {},  r: {}, espace: {}, typeObjetMaquette: {}, racine: {}, typeObjetFormation: {}, ids: {}, piaSeulement: {}, piaActif: {}, valideSeulement: {},mutualise: {}",
				codeStructure, r, espace, typeObjetMaquette, racine, typeObjetFormation, ids, piaSeulement, piaActif,
				valideSeulement, mutualise);

		List<ObjetMaquetteSummary> objetMaquetteSummaries = rechercheObjetMaquetteSummaryPaginee(codeStructure, r,
				espace, typeObjetMaquette, racine, typeObjetFormation, ids, piaSeulement, DEFAULT_PAGE_SIZE);
		if (objetMaquetteSummaries.isEmpty()) {
			logger.info("rechercheObjetMaquetteSummary vide {} : {}", codeStructure, espace);
		}
		
		
		objetMaquetteSummaries = objetMaquetteSummaries.stream().distinct().toList();
		
		
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
		logger.debug("rechercheObjetMaquetteSummary( {} ,{} ,{} )", codeStructure, codeFormation, codePeriode);
		UUID idEsapce = espaceService.chercherEspaceFromCode(codeStructure, codePeriode);
		if (idEsapce == null) {
			logger.error("ID espace null pour la periode {}", codePeriode);
			return Collections.emptyList();
		}

		List<ObjetMaquetteSummary> listMaquetteSummaries = rechercheObjetMaquetteSummaryPaginee(codeStructure,
				codeFormation, idEsapce.toString(), null, null, null, null, piaSeulement, SMALL_PAGE_SIZE);

		if (listMaquetteSummaries.isEmpty()) {
			logger.info("rechercheObjetMaquetteSummary vide : {} : {}", codeFormation, codePeriode);
			return listMaquetteSummaries;
		}

		logger.debug("rechercheObjetMaquetteSummary : {} {} {}", codeStructure, codeFormation,
				listMaquetteSummaries.size());
		logger.info("rechercheObjetMaquetteSummary : {} {} {}", codeStructure, codeFormation, listMaquetteSummaries.size());
		return listMaquetteSummaries;
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
		logger.debug("rechercheObjetMaquetteSummaryParTypeObjetFormation( {} ,{} ,{} )", codeStructure,
				typeObjetFormation, espace);
		List<TypeObjetMaquette> tom = new ArrayList<>();
		return rechercheObjetMaquetteSummary(codeStructure, tom, null, typeObjetFormation, null, espace, piaSeulement);
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
		logger.debug("rechercherIdObjetMaquette( {} ,{} ,{} ,{} ,{} )", codeStructure, type, racine, typeObjetFormation,
				espace);
		List<String> listIdsObjetMaquettes = new ArrayList<>();
		List<TypeObjetMaquette> tom = new ArrayList<>();
		tom.add(type);

		List<ObjetMaquetteSummary> allObjetMaquetteDetail = rechercheObjetMaquetteSummary(codeStructure, tom, null,
				typeObjetFormation, null, espace, piaSeulement);
		allObjetMaquetteDetail.forEach(m -> listIdsObjetMaquettes.add(m.getId().toString()));
		logger.debug("rechercherIdObjetMaquette : {} {} {}", codeStructure, espace, listIdsObjetMaquettes.size());
		return listIdsObjetMaquettes;
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
			logger.error(e.getMessage());
		}
		return new ArrayList<>();
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
		List<TypeObjetMaquette> tom = new ArrayList<>();
		tom.add(type);
		List<UUID> ids = null;
		List<ObjetMaquetteSummary> allObjetMaquetteDetail = rechercheObjetMaquetteSummary(codeStructure, tom, racine,
				typeObjetFormation, ids, espace, piaSeulement);
		logger.debug("rechercherObjetMaquette : {} {} {}", codeStructure, espace, allObjetMaquetteDetail.size());
		Map<UUID, String> espaceCodeCache = new HashMap<>();
		allObjetMaquetteDetail.forEach(f -> {

			try {
				logger.debug("rechercherObjetMaquette : {} {} {}", codeStructure, espace, f.getId());
				ObjetMaquetteDetail objectMaqette = objetsMaquetteApi.lireObjetMaquette(codeStructure, f.getId());
				UUID idEspace = objectMaqette.getEspace();
				if (idEspace == null) {
					return;
				}
				String codeEspace = espaceCodeCache.get(idEspace);
				if (codeEspace == null) {
					Espace esp = espaceService.lireEspace(codeStructure, idEspace);
					if (esp == null || esp.getCode() == null) {
						return;
					}
					codeEspace = esp.getCode();
					espaceCodeCache.put(idEspace, codeEspace);
				}
				mapVDI.put(f.getCode() + ";" + codeEspace, f.getLibelle());

			} catch (ApiException e) {
				logger.error("Erreur lors de la lecture de l'objet maquette {} : {}", f.getId(), e.getMessage());
			}
		});

		return mapVDI;

	}

	/**
	 * 
	 * 
	 * @param codeStructure
	 * @param espace
	 * @return
	 * @throws ApiException
	 */
	public List<ObjetMaquetteSummary> rechercherObjetMaquetteDiplomeReferences(String codeStructure, String periode)
			throws ApiException {

		logger.debug("rechercherObjetMaquetteDiplomeReferences( {} ,{} )", codeStructure, periode);

		List<TypeObjetMaquette> typesObjetMaquette = new ArrayList<>();
		typesObjetMaquette.add(TypeObjetMaquette.FORMATION);

		boolean racine = false;
		String typeObjetFormation = null;
		List<UUID> ids = null;

		boolean piaSeulementContrainte = false;

		List<Espace> espaces = espaceService.checherPeriodeParCode(codeStructure, periode);
		if (espaces != null && !espaces.isEmpty()) {
			UUID idPeriode = null;

			for (Espace espace : espaces) {
				if (espace.getCode().equals(periode)) {
					idPeriode = espace.getId();
					logger.debug("idPeriode : {} pour la periode {}", idPeriode, periode);
				}
			}
			if (idPeriode == null) {
				logger.error("ID periode null pour la periode {}", periode);
				return Collections.emptyList();
			}
			List<ObjetMaquetteSummary> allObjetMaquetteDetail = rechercheObjetMaquetteSummary(codeStructure,
					typesObjetMaquette, racine, typeObjetFormation, ids, idPeriode.toString(), piaSeulementContrainte);
			logger.debug("rechercherObjetMaquetteDiplomeReferences : {} {} {}", codeStructure, periode,
					allObjetMaquetteDetail.size());
			
			
			return allObjetMaquetteDetail;
		} else
			return Collections.emptyList();
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
		logger.debug("rechercherObjetMaquetteObjetFormation( {} ,{} ,{} )", codeStructure, periode, piaSeulement);
		UUID idPeriode = espaceService.chercherEspaceFromCode(codeStructure, periode);
		if (idPeriode == null) {
			logger.error("ID periode null pour la periode {}", periode);
			return new HashMap<>();
		}

		// typeObjetFormation [UE, PARCOURS-TYPE, SEMESTRE, ANNEE, ENS, EC]
		// List<String> listTypeObjetFormation =
		// Arrays.asList(typeObjetFormationChargementFormations.split("[,;\\s]+"));

		Map<String, String> objetMaquettes;
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

		logger.debug("rechercherObjetMaquetteObjetFormation : {} {} {}", codeStructure, periode, objetMaquettes.size());
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

		List<ObjetMaquetteDetail> listObjetMaquetteDetail = new ArrayList<>();
		List<TypeObjetMaquette> tom = new ArrayList<>();
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
						if (Boolean.TRUE.equals(om.getStage())) {
							listObjetMaquetteDetail.add(objetMaquetteDetail);
						}
					}

				} catch (ApiException e) {
					logger.error("Erreur lors de la lecture de l'objet maquette {} : {}", omd.getId(), e.getMessage());
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
			ids.forEach(id -> uids.add(UUID.fromString(id)));
		}

		List<TypeObjetMaquette> typeObjetMaquette = new ArrayList<>();
		// typeObjetFormation [UE, PARCOURS-TYPE, SEMESTRE, ANNEE, ENS, EC]
		String typeObjetFormation = "ENS";
		Boolean racine = true;

		return rechercheObjetMaquetteSummary(codeStructure, typeObjetMaquette, racine, typeObjetFormation, uids, espace,
				piaSeulement);
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
		List<ObjetMaquetteDetail> llObjetMaquetteDetail = new ArrayList<>();
		try {
			objetMaquetteSummaries = rechercheObjetMaquetteSummary(codeStructure, codeEtape, versionEtape);
			ObjetMaquetteSummary objetMaquetteSummary = null;
			for (ObjetMaquetteSummary oms : objetMaquetteSummaries) {
				if (oms.getCode().equalsIgnoreCase(codeEtape)) {
					objetMaquetteSummary = oms;
					break;
				}
			}
			if (objetMaquetteSummary == null) {
				logger.warn("Aucun objet maquette trouvé pour le code : {}", codeEtape);
				return llObjetMaquetteDetail;
			}
			UUID id = objetMaquetteSummary.getId();
			llObjetMaquetteDetail = listeEnfantsObjectMaquetteStage(codeStructure, id.toString());
		} catch (ApiException e) {
			logger.error("Erreur lors de la recherche de l'objet maquette pour le code {} : {}", codeEtape,
					e.getMessage());
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
		if (maquetteStructure == null || maquetteStructure.getRacine() == null
				|| maquetteStructure.getRacine().getEnfants() == null) {
			return Collections.emptyList();
		}
		return listeEnfantsObjectMaquetteStage(codeStructure, maquetteStructure.getRacine().getEnfants());
	}

	/**
	 * 
	 * @param codeStructure
	 * @param enfantsStructure
	 * @return
	 */
	public List<ObjetMaquetteDetail> listeEnfantsObjectMaquetteStage(String codeStructure,
			List<EnfantsStructure> enfantsStructure) {
		List<ObjetMaquetteDetail> stagesObjectMaquette = new ArrayList<>();
		enfantsStructure.forEach(e -> listeEnfantsObjectMaquetteStage(codeStructure, e, stagesObjectMaquette));
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
		if (enfantStructure == null || enfantStructure.getObjetMaquette() == null
				|| enfantStructure.getObjetMaquette().getId() == null) {
			return;
		}

		try {

			ObjetMaquetteDetail objectMaqette = objetsMaquetteApi.lireObjetMaquette(codeStructure,
					enfantStructure.getObjetMaquette().getId());
			DescripteursObjetFormation dom = (DescripteursObjetFormation) objectMaqette.getDescripteursObjetMaquette();
			if (dom.getStage() != null && dom.getStage())
				stagesObjectMaquette.add(objectMaqette);
		} catch (ApiException e1) {

			logger.error("Erreur lors de la lecture de l'objet maquette {} : {}",
					enfantStructure.getObjetMaquette().getId(), e1.getMessage());
		} catch (ClassCastException e2) {
			logger.warn(e2.getMessage());
		}

		List<EnfantsStructure> enfants = enfantStructure.getObjetMaquette().getEnfants();
		if (enfants != null && !enfants.isEmpty()) {
			enfants.forEach(e -> listeEnfantsObjectMaquetteStage(codeStructure, e, stagesObjectMaquette));
		}

	}
	/**
	 * 
	 * @param codeStructure
	 * @param r
	 * @param espace
	 * @param typeObjetMaquette
	 * @param racine
	 * @param typeObjetFormation
	 * @param ids
	 * @param piaSeulementParam
	 * @param pageSize
	 * @return
	 * @throws ApiException
	 */
	private List<ObjetMaquetteSummary> rechercheObjetMaquetteSummaryPaginee(String codeStructure, String r,
			String espace, List<TypeObjetMaquette> typeObjetMaquette, Boolean racine, String typeObjetFormation,
			List<UUID> ids, Boolean piaSeulementParam, int pageSize) throws ApiException {
		List<ObjetMaquetteSummary> summaries = new ArrayList<>();
		Pageable pageable = new Pageable();
		pageable.setPage(0);
		pageable.setTaille(pageSize);

		Boolean protege = null;
		List<String> codesPerimetresIn = null;
		List<String> codesPerimetresOut = null;
		logger.debug(
				"codeStructure: {},  r: {}, espace: {}, typeObjetMaquette: {}, racine: {}, typeObjetFormation: {}, ids: {}, piaSeulement: {}, piaActif: {}, valideSeulement: {},mutualise: {}",
				codeStructure, r, espace, typeObjetMaquette, racine, typeObjetFormation, ids, piaSeulementParam,
				piaActif, valideSeulement, mutualise);
		PagedObjetMaquetteSummaries response = objetsMaquetteApi.rechercherObjetMaquette(codeStructure, pageable, r,
				espace, typeObjetMaquette, racine, typeObjetFormation, ids, piaSeulementParam, piaActif, valideSeulement,
				mutualise, protege, codesPerimetresIn, codesPerimetresOut);
		
		
		logger.debug("{} {}  {} ", "rechercheObjetMaquetteSummaryPaginee", " => response size",
				response);
		if (response == null || response.getItems() == null) {
			return summaries;
		}

		summaries.addAll(response.getItems());
		int totalPages =  response.getTotalPages();
		for (int i = 1; i < totalPages; i++) {
			pageable.setPage(i);
			
			logger.debug("rechercheObjetMaquetteSummaryPaginee page {}", i);
			logger.debug(
					"codeStructure: {},  r: {}, espace: {}, typeObjetMaquette: {}, racine: {}, typeObjetFormation: {}, ids: {}, piaSeulement: {}, piaActif: {}, valideSeulement: {},mutualise: {}",
					codeStructure, r, espace, typeObjetMaquette, racine, typeObjetFormation, ids, piaSeulementParam,
					piaActif, valideSeulement, mutualise);
			PagedObjetMaquetteSummaries pageResponse = objetsMaquetteApi.rechercherObjetMaquette(
					codeStructure, 
					pageable,
					r, 
					espace, 
					typeObjetMaquette, 
					racine, 
					typeObjetFormation, 
					ids, 
					piaSeulementParam, 
					piaActif,
					valideSeulement, 
					mutualise, 
					protege, 
					codesPerimetresIn, 
					codesPerimetresOut);
			logger.debug("{} {}  {} ", "rechercheObjetMaquetteSummaryPaginee", " => response size",
					pageResponse.getItems().size());
			if ( pageResponse.getItems() != null) {
				summaries.addAll(pageResponse.getItems());
			}
		}

		return summaries;
	}

	/**
	 * 
	 * @param codeStructure
	 * @param codeObjetMaquette
	 * @param codePeriode
	 * @param piaSeulement
	 * @param piaActif
	 * @return
	 */
	public PagedObjetMaquetteSummaries rechercheObjetMaquette(String codeStructure, String codeObjetMaquette,
			String codePeriode, Boolean piaSeulement, Boolean piaActif) {

		Pageable pageable = new Pageable();
		List<TypeObjetMaquette> typeObjetMaquette = new ArrayList<>();
		Boolean racine = null;
		String typeObjetFormation = null;
		List<UUID> ids = null;
		Boolean protege = null;
		List<String> codesPerimetresIn = null;
		List<String> codesPerimetresOut = null;

		try {

			return objetsMaquetteApi.rechercherObjetMaquette(codeStructure, pageable, codeObjetMaquette, codePeriode,
					typeObjetMaquette, racine, typeObjetFormation, ids, piaSeulement, piaActif, valideSeulement,
					mutualise, protege, codesPerimetresIn, codesPerimetresOut);

		} catch (ApiException e) {
			logger.error("Erreur lors de la recherche d'objet maquette : {}", e.getMessage());
			return null;
		}

	}

	public void setMutualise(Boolean mutualise) {
		this.mutualise = mutualise;
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
