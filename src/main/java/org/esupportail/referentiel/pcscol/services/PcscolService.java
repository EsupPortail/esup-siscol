package org.esupportail.referentiel.pcscol.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.esupportail.referentiel.beans.ApogeeMap;
import org.esupportail.referentiel.beans.DiplomeReduitDto;
import org.esupportail.referentiel.beans.ElementPedagogique;
import org.esupportail.referentiel.beans.EtabRef;
import org.esupportail.referentiel.beans.EtapeInscription;
import org.esupportail.referentiel.beans.EtapeReduiteDto;
import org.esupportail.referentiel.beans.EtudiantInfoAdm;
import org.esupportail.referentiel.beans.EtudiantRef;
import org.esupportail.referentiel.beans.RegimeInscription;
import org.esupportail.referentiel.beans.SignataireRef;
import org.esupportail.referentiel.cache.CacheConfig;
import org.esupportail.referentiel.pcscol.api.EspacesApi;
import org.esupportail.referentiel.pcscol.api.InscriptionsApi;
import org.esupportail.referentiel.pcscol.api.NomenclatureApi;
import org.esupportail.referentiel.pcscol.api.ObjetsMaquetteApi;
import org.esupportail.referentiel.pcscol.api.StructureApi;
import org.esupportail.referentiel.pcscol.config.CesureUtils;
import org.esupportail.referentiel.pcscol.ins.model.Apprenant;
import org.esupportail.referentiel.pcscol.ins.model.ApprenantEtInscriptions;
import org.esupportail.referentiel.pcscol.ins.model.InscriptionComplete;
import org.esupportail.referentiel.pcscol.invoker.ApiException;
import org.esupportail.referentiel.pcscol.mapper.ApprenantEtuInfoAdmMapperInterface;
import org.esupportail.referentiel.pcscol.mapper.OdfDtoMapperInterface;
import org.esupportail.referentiel.pcscol.odf.model.DescripteursObjetFormation;
import org.esupportail.referentiel.pcscol.odf.model.EnfantsStructure;
import org.esupportail.referentiel.pcscol.odf.model.Espace;
import org.esupportail.referentiel.pcscol.odf.model.MaquetteStructure;
import org.esupportail.referentiel.pcscol.odf.model.ObjetMaquetteDetail;
import org.esupportail.referentiel.pcscol.odf.model.ObjetMaquetteSummary;
import org.esupportail.referentiel.pcscol.ref_api.model.Adresse;
import org.esupportail.referentiel.pcscol.ref_api.model.Commune;
import org.esupportail.referentiel.pcscol.ref_api.model.Nomenclature;
import org.esupportail.referentiel.pcscol.ref_api.model.PaysNationalite;
import org.esupportail.referentiel.pcscol.ref_api.model.Structure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
@ConditionalOnProperty(name = "app.mode_pegase")
public class PcscolService implements PcscolServiceI {

	private  Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StructureApi structureApi;

	@Autowired
	private InscriptionsApi inscriptionsApi;

	@Autowired
	private ObjetsMaquetteApi objetsMaquetteApi;
	@Autowired
	private EspacesApi espacesApi;
	@Autowired
	private OffreFormationService offreFormationService;
	@Autowired
	private OffreFormationServicePartielEtapes offreFormationServiceRevisited;

	@Autowired
	private ChcService chcService;
	@Autowired
	private EspaceService espaceService;

	@Autowired
	private NomenclatureApi nomenclatureApi;

	@Autowired
	private CesureUtils cesureUtils;

	@Override
	public List<String> recupererAnneesIa(String codeStructure, String codeEtud) {
		logger.debug("recupererAnneesIa : {} , {}", codeStructure, codeEtud);
		try {
			List<InscriptionComplete> inscriptions = inscriptionsApi.lireInscriptions(codeStructure, codeEtud)
					.getInscriptions();
			if (inscriptions != null && !inscriptions.isEmpty()) {
				List<Integer> anneeUniv = inscriptions.stream()
						.map(ins -> ins.getCible().getPeriode().getAnneeUniversitaire()).distinct().toList();
				return anneeUniv.stream().map(String::valueOf).toList();

			}
		} catch (ApiException e) {
			logger.error("recupererAnneesIa  : {} , {} , {}  : {} ", codeStructure, codeEtud, e.getMessage(),
					e.getCode());
		}
		return Collections.emptyList();
	}

	public List<EtapeInscription> etapeInscription(String codeStructure, String codeApprenant, String codePeriode) {
		logger.debug("etapeInscription : {} , {} , {}", codeStructure, codeApprenant, codePeriode);
		ApprenantEtInscriptions app;
		List<EtapeInscription> etps = new ArrayList<EtapeInscription>();
		try {
			app = inscriptionsApi.lireInscriptions(codeStructure, codeApprenant);
			logger.debug(" Appel Apprenant {}", app.getApprenant().getEtatCivil());

			List<InscriptionComplete> inscriptions = app.getInscriptions();
			logger.debug("Inscriptions : {}", inscriptions.size());

			inscriptions.forEach(ins -> {

				if (ins.getCible().getPeriode().getCode().equalsIgnoreCase(codePeriode)) {
					EtapeInscription etpinscr = ApprenantEtuInfoAdmMapperInterface.Instance
							.stagesApprenantToEtapeInscription(ins);

					etpinscr.setCodVrsVet(codeStructure);
					etpinscr.setVersionDiplome(codeStructure);

					if (etpinscr.getLibelleCursusAmenage() != null && !etpinscr.getLibelleCursusAmenage().isEmpty()) {
						logger.debug("EtapeInscription : Cursus amenagé {}", etpinscr.getLibelleCursusAmenage());
						etpinscr.setCodeCursusAmenage(cesureUtils.getCesures().get(etpinscr.getLibelleCursusAmenage()));

					}

					logger.debug("EtapeInscription : {}", etpinscr.getCodeEtp());
					if (etpinscr.getCodeComposante() != null && !etpinscr.getCodeComposante().isEmpty())
						try {
							Structure structure = structureApi.lireStructure(etpinscr.getCodeComposante());
							etpinscr.setLibComposante(structure.getAppellationOfficielle());
						} catch (ApiException e) {
							logger.error("lireStructure : {}", e.getMessage());
						}
					etps.add(etpinscr);
				}
			});

		} catch (ApiException e) {
			logger.error("etapeInscription : {} , {} , {}  : {} ", codeStructure, codeApprenant, codePeriode,
					e.getMessage());
			e.printStackTrace();
		}

		return etps;
	}

	public Map<String, String> studentEtapeVets(String codeStructure, String codeApprenant, String annee) {

		List<EtapeInscription> etps = etapeInscription(codeStructure, codeApprenant, annee);
		LinkedHashMap<String, String> lEtapeInscriptions = new LinkedHashMap<>();
		etps.forEach(e -> lEtapeInscriptions.put(e.getCodeEtp(), e.getCodVrsVet()));

		return lEtapeInscriptions;
	}

	public ApogeeMap recupererIaIpParEtudiantAnnee(String codeStructure, String codeApprenant,
			List<String> codesPeriodes) {
		ApogeeMap apogeeMap = new ApogeeMap();
		/**
		 * TODO regime
		 */

		List<EtapeInscription> etapeInscriptions = new ArrayList<>();

		codesPeriodes.forEach(codePeriode -> {
			logger.debug("recupererIaIpParEtudiantAnnee : {} , {} , {}", codeStructure, codeApprenant, codePeriode);
			List<EtapeInscription> etapeInscriptionsPartiel = etapeInscription(codeStructure, codeApprenant,
					codePeriode);
			etapeInscriptions.addAll(etapeInscriptionsPartiel);
		});

		logger.debug("----------------------- {}", etapeInscriptions);

		List<RegimeInscription> regimesInscriptions = new ArrayList<>();
		apogeeMap.setListeEtapeInscriptions(etapeInscriptions);

		logger.debug("recupererIaIpParEtudiantAnnee : {} , {} , {}", codeStructure, codeApprenant, codesPeriodes);

		try {
			List<ElementPedagogique> lelps = chcService.lirelisteElementPedagogiqueStageApprenant(codeApprenant,
					codeStructure);
			if (lelps != null) {

				List<ElementPedagogique> filterdlElps = lelps.stream()
						.filter(e -> codesPeriodes.contains(e.getCodePeriode())).toList();
				apogeeMap.setListeELPs(filterdlElps);
			}

		} catch (ApiException e) {
			logger.error(e.getMessage());
		}

		apogeeMap.getListeEtapeInscriptions().forEach(etp -> {

			List<String> listeAnnee = espaceService.anneeUnivFromEsapces(codeStructure, codesPeriodes);
			etp.getRegimeIns();
			etp.getLibRg();
			etp.getTypeIns();
			RegimeInscription regime = new RegimeInscription();
			logger.debug("regimeInscription : {} , {} , {}", etp.getRegimeIns(), etp.getLibRg(), etp.getTypeIns());
			logger.debug("listeAnnee : {}", listeAnnee);
			if (listeAnnee != null && !listeAnnee.isEmpty()) {
				/*  */
			}
			regime.setCodRegIns(etp.getRegimeIns());
			// regime.setCodRegIns(etp.get)
			regime.setLicRegIns(etp.getRegimeIns());
			regime.setRegimeIns(etp.getRegimeIns());
			regime.setLibRg(etp.getLibRg());
			regimesInscriptions.add(regime);

		});
		apogeeMap.setRegimeInscription(regimesInscriptions);

		return apogeeMap;

	}

	@Override
	public EtudiantInfoAdm lireEtudiantInfoAdm(String codeStructure, String codeApprenant) {
		ApprenantEtInscriptions app;
		try {
			app = inscriptionsApi.lireInscriptions(codeStructure, codeApprenant);
			return ApprenantEtuInfoAdmMapperInterface.Instance.apprenantToEtudiantInfoAdm(app.getApprenant());
		} catch (ApiException e) {
			logger.error("lireEtudiantInfoAdm : {} , {}  : {} ", codeStructure, codeApprenant, e.getMessage());
		}

		return null;
	}

	@Override
	public EtudiantRef lireEtudiantRef(String codeStructure, String codeApprenant, String annee) {
		ApprenantEtInscriptions app;
		try {
			app = inscriptionsApi.lireInscriptions(codeStructure, codeApprenant);
			Apprenant appInfo = app.getApprenant();
			return ApprenantEtuInfoAdmMapperInterface.Instance.apprenantToEtudiantRef(appInfo);
		} catch (ApiException e) {
			logger.error("lireEtudiantRef : {} , {}  : {} ", codeStructure, codeApprenant, e.getMessage());
		}
		return new EtudiantRef();
	}

	/**
	 * TODO boolean uniquementOuvrableAInscription, boolean
	 * uniquementOuvrableAuChoixDuCursus
	 */
	@Override
	@Cacheable(cacheNames = CacheConfig.PERMANENT, sync = true)
	public Map<String, String> lireMapFormations(String codeStructure, String listecodesPeriode, boolean piaSeulement) {

		List<String> listCodePeriode = codePeriodeFromPeriodes(listecodesPeriode);

		Map<String, String> mapAllFormations = new HashMap<>();

		listCodePeriode.forEach(periode -> {

			UUID idPeriode = espaceService.chercherEspaceFromCode(codeStructure, periode);
			if (idPeriode != null) {

				logger.debug("lireMapFormations : {} , {} ", codeStructure, idPeriode);
				List<ObjetMaquetteSummary> maquetteSummaries = offreFormationServiceRevisited
						.rechercheObjetMaquetteSummaryParPiaSeaulement(codeStructure, idPeriode.toString());
				logger.debug("lireMapFormations : {} ", maquetteSummaries.size());
				maquetteSummaries.stream().forEach(e -> logger.debug("lireMapFormations : {}  {} =>{}", e.getCode(),
						e.getTypeObjetFormation(), e.getTypeObjetMaquette()));

				/*
				 * *
				 */
				HashMap<String, String> mapFormations = offreFormationServiceRevisited
						.objetMaquetteSummaryMap(maquetteSummaries, periode);
				logger.debug("lireMapFormations : {} ", mapFormations.size());
				mapAllFormations.putAll(mapFormations);

			} else {
				logger.error("lireMapFormations : {} , {} : espace non trouvé", codeStructure, periode);
			}
		});

		return mapAllFormations;
	}

	@Override
	@Cacheable(cacheNames = CacheConfig.PERMANENT, sync = true)
	public Map<String, String> lireMapStructures() {
		Map<String, String> mapComp = new LinkedHashMap<>();
		List<Structure> strucList = lireListeStructure();
		logger.debug("lireMapStructures : {}", strucList.size());
		if (!strucList.isEmpty())
			strucList.forEach(s -> {
				mapComp.put(s.getCode(), s.getDenominationPrincipale());
				logger.info("lireMapStructures : {} => {}", s.getCode(), s.getDenominationPrincipale());

			});
		return mapComp;
	}

	@Override
	public EtabRef lireEtabRef() {
		List<Structure> strucList = lireListeStructure();
		EtabRef etabRef = new EtabRef();
		Structure structure = strucList.stream().filter(s -> s.getEstStructureMere()).findFirst().orElse(null);
		if (structure == null) {
			logger.error("lireEtabRef : Aucune structure mère trouvée");
			return etabRef;
		}
		etabRef.setNomEtabRef(structure.getDenominationPrincipale());
		etabRef.setAdresseEtabRef(formatAdresse(structure.getAdresse()));

		return etabRef;

	}

	private String formatAdresse(Adresse adresse) {
		if (adresse == null) {
			return null;
		}
		return Arrays.asList(adresse.getAdresse1(), adresse.getAdresse2(), adresse.getAdresse3(), adresse.getAdresse4(),
				adresse.getAdresse5(), adresse.getLocaliteAcheminement(), adresse.getCodePostal()

		).stream().filter(field -> field != null && !field.isEmpty()).collect(Collectors.joining(", "));
	}

	/**
	 * 
	 * @param codeStructure
	 * @param codesPeriodesChargementFormations
	 * @return
	 */
	public List<ObjetMaquetteSummary> allObjetMaquetteSummariesFromPeriodes(String codeStructure,
			String codesPeriodesChargementFormations) {
		List<String> listCodePeriode = codePeriodeFromPeriodes(codesPeriodesChargementFormations);
		logger.debug("listCodePeriode: {}", listCodePeriode);
		final List<ObjetMaquetteSummary> objetMaquetteSummaries = new ArrayList<>();
		logger.debug("codeStructure : {} ", codeStructure);

		listCodePeriode.forEach(codePeiode -> {
			try {
				logger.debug("\t\tcodePeiode : {}", codePeiode);
				List<ObjetMaquetteSummary> diplomes = offreFormationService
						.rechercherObjetMaquetteDiplomeReferences(codeStructure, codePeiode);
				logger.debug("\t\tnbr diplomes {}", diplomes.size());

				objetMaquetteSummaries.addAll(diplomes);
			} catch (ApiException e) {
				logger.error("allObjetMaquetteSummariesFromPeriodes : {} , {} : {} ", codeStructure, codePeiode,
						e.getMessage());
			}
		});
		return objetMaquetteSummaries;
	}

	/**
	 * 
	 * @param codeStructure
	 * @param codesPeriodesChargementFormations
	 * @return
	 */
	// @Cacheable(cacheNames = CacheConfig.PERMANENT, sync = true)
	public List<DiplomeReduitDto> diplomeRef(String codeStructure, String codesPeriodesChargementFormations) {

		logger.debug("diplomeRef : {} period {}", codeStructure, codesPeriodesChargementFormations);
		final List<ObjetMaquetteSummary> objetMaquetteSummaries = allObjetMaquetteSummariesFromPeriodes(codeStructure,
				codesPeriodesChargementFormations);

		List<DiplomeReduitDto> diplomes = new ArrayList<>();
		objetMaquetteSummaries.forEach(f -> {
			try {

				ObjetMaquetteDetail objectMaqette = objetsMaquetteApi.lireObjetMaquette(codeStructure, f.getId());
				UUID idEspace = objectMaqette.getEspace();
				/**
				 * TODO gestion des espaces
				 */
				DiplomeReduitDto diplome = OdfDtoMapperInterface.Instance
						.diplomeReduitDtoFromObjetMaquette(objectMaqette);
				Espace espace = espacesApi.lireEspace(codeStructure, idEspace);
				diplome.setCodePeriode(espace.getCode());
				diplome.setVersionDiplome(codeStructure);
				diplomes.add(diplome);

				// possibilite d'avoir un espace different ???

				if (!objectMaqette.getEnfants().isEmpty()) {
					MaquetteStructure maquetteStructure = offreFormationService.lireMaquette(codeStructure,
							f.getId().toString());
					/**
					 * TODO ajouter le parent comme fils de lui meme cas ou le diplome est lui meme
					 * porteur de PIA fils/pere ?
					 */

					List<EnfantsStructure> enfants = listeEnfantsObjectMaquettePia(maquetteStructure);

					logger.debug("diplomeRef : {} , {} : enfants {}", codeStructure, f.getId(), enfants.size());

					if (enfants != null && !enfants.isEmpty()) {

						enfants.forEach(e -> {

							ObjetMaquetteDetail objectMaqette2;
							try {
								objectMaqette2 = objetsMaquetteApi.lireObjetMaquette(codeStructure,
										e.getObjetMaquette().getId());
								EtapeReduiteDto etp = new EtapeReduiteDto();

								etp.setCodeEtp(objectMaqette2.getCode());
								/**
								 * TODO recupere le codePeriode
								 */
								etp.setCodePeriode(espace.getCode());
								etp.setCodVrsVet(codeStructure);
								etp.setLibWebVet(objectMaqette2.getDescripteursObjetMaquette().getLibelle());
								diplome.getListeEtapes().add(etp);
							} catch (ApiException e1) {
								logger.error("diplomeRef : {} , {} : {} ", codeStructure, e.getId(), e1.getMessage());
							}

						});

					}
				}

			} catch (ApiException e) {
				logger.error("diplomeRef : {} , {} : {} ", codeStructure, f.getId(), e.getMessage());
			}
		});

		return diplomes;
	}

	/**
	 * 
	 * @param maquetteStructure
	 * @return
	 */
	public List<EnfantsStructure> listeEnfantsObjectMaquettePia(MaquetteStructure maquetteStructure) {
		final List<EnfantsStructure> listeEnfantPia = new ArrayList<>();
		List<EnfantsStructure> enfants = maquetteStructure.getRacine().getEnfants();
		enfants.forEach(e -> {
			if (e.getObjetMaquette().getPia() != null && e.getObjetMaquette().getValide()
					&& Boolean.TRUE.equals(e.getObjetMaquette().getPia().getActif())) {
				listeEnfantPia.add(e);
			}
		});
		return listeEnfantPia;
	}

	public List<ElementPedagogique> chercherElementPeda(String codeStructure, String codeEtape, String codePeriode) {
		List<ElementPedagogique> l = new ArrayList<>();
		try {

			List<ObjetMaquetteSummary> objetMaquetteSummaries = offreFormationService
					.rechercheObjetMaquetteSummary(codeStructure, codeEtape, codePeriode);
			if (objetMaquetteSummaries == null || objetMaquetteSummaries.isEmpty()) {
				logger.error("studentListeElpStage : {} {} {}  ", codeStructure, codeEtape, codePeriode);
				logger.error("studentListeElpStage : Aucun Objet Maquette trouvé");
				return l;
			}
			ObjetMaquetteSummary objetMaquetteSummary = objetMaquetteSummaries.get(0);
			UUID id = objetMaquetteSummary.getId();
			List<ObjetMaquetteDetail> llStage = offreFormationService.listeEnfantsObjectMaquetteStage(codeStructure,
					id.toString());
			llStage.forEach(e -> {
				ElementPedagogique elp = new ElementPedagogique();
				elp.setCodElp(e.getCode());
				elp.setLibElp(e.getDescripteursObjetMaquette().getLibelle());
				logger.debug("chercherElementPeda : DescripteursObjetFormation {}", e.getClass());

				DescripteursObjetFormation dom = (DescripteursObjetFormation) e.getDescripteursObjetMaquette();

				elp.setTemElpTypeStage(String.valueOf(dom.getStage()));
				if (dom.getNature() != null)
					elp.setLibNatureElp(dom.getNature().getType());
				elp.setCodEtp(objetMaquetteSummary.getCode());
				elp.setCodVrsVet(codeStructure);
				elp.setCodePeriode(codePeriode);
				elp.setNbrCrdElp(dom.getEcts());
				l.add(elp);

			});
		} catch (ApiException e) {
			logger.error("studentListeElpStage : {} ", e.getMessage());
		}

		return l;
	}

	public List<ElementPedagogique> studentListeElpStage(String codeStructure, String codeEtape, String versionEtape,
			String codesPeriodesChargementFormations) {

		List<ElementPedagogique> listElementPedagogique = new ArrayList<>();

		List<String> listCodePeriode = codePeriodeFromPeriodes(codesPeriodesChargementFormations);

		logger.debug("studentListeElpStage : {} , {} , {}", codeStructure, codeEtape, versionEtape);

		if (listCodePeriode == null || listCodePeriode.isEmpty()) {
			logger.error("studentListeElpStage : " + "Aucun Objet Maquette trouvé");
			return listElementPedagogique;
		}
		for (String codePeriode : listCodePeriode) {
			logger.debug("studentListeElpStage : {} , {} , {}", codeStructure, codeEtape, codePeriode);
			List<ElementPedagogique> elps = chercherElementPeda(codeStructure, codeEtape, codePeriode);
			if (elps != null && !elps.isEmpty()) {
				listElementPedagogique.addAll(elps);
			}
		}
		return listElementPedagogique;
	}

	/**
	 * 
	 * @return List<Structure>
	 */
	public List<Structure> lireListeStructure() {
		List<Structure> response = new ArrayList<>();
		try {
			response = structureApi.lireListeStructures();
		} catch (ApiException e) {
			logger.error("lireListeStructure : {} ", e.getMessage());
		}

		return response;

	}

	/**
	 * * Récupère le signataire d'une composante. * @param composante * @return
	 * SignataireRef
	 * 
	 */
	public SignataireRef signaitaireRef(String composante) {
		try {
			Structure response = structureApi.lireStructure(composante);
			SignataireRef sign = new SignataireRef();
			sign.setNomSignataireComposante(
					response.getResponsable().getNom() + " " + response.getResponsable().getPrenom());
			sign.setQualiteSignataire(response.getResponsable().getTitre());
			return sign;
		} catch (ApiException e) {
			logger.error("signaitaireRef : {} : {}", composante, e.getMessage());
		}
		return new SignataireRef();
	}

	/**
	 * * Recherche une commune par son code postal et son code commune.
	 * 
	 * @param codePostal
	 * @param codeCommune
	 * @return
	 */
	public Commune chercheCommune(String codePostal, String codeCommune) {
		logger.debug("chercheCommune : {} , {}", codePostal, codeCommune);
		try {
			List<Nomenclature> noms = nomenclatureApi.lireCommunes(codePostal);
			List<Commune> communes = new ArrayList<>();
			if (noms != null && !noms.isEmpty()) {

				for (Nomenclature nom : noms) {
					if (nom instanceof Commune com) {
						communes.add(com);
					}
				}

				Commune commune = communes.stream().filter(n -> n.getCodeInsee().equalsIgnoreCase(codeCommune))
						.findFirst().orElse(null);
				if (commune != null) {
					logger.debug("chercheCommune : {} , {} : {}", codePostal, codeCommune, commune.getLibelleLong());
					return commune;
				} else {
					logger.debug("chercheCommune : {} , {} : Pas de commune trouvée", codePostal, codeCommune);
				}
			} else {
				logger.debug("chercheCommune : {} , {} : Pas de commune trouvée", codePostal, codeCommune);
			}
		} catch (ApiException e) {
			logger.error("Erreur lors de la récupération de la commune : {} , {} : {} : {}", codePostal, codeCommune,
					e.getMessage(), e.getCode());
		}
		return null;
	}

	/**
	 * 
	 * @param codePays
	 * @return
	 */
	public PaysNationalite cherchePays(String codePays) {
		List<Nomenclature> nomenclatures;
		try {
			nomenclatures = nomenclatureApi.lireListeNomenclatures("PaysNationalite");
			Nomenclature nomenclature = nomenclatures.stream().filter(n -> n.getCode().equalsIgnoreCase(codePays))
					.findFirst().orElse(null);
			if (nomenclature == null) {
				logger.debug("cherchePays : {} : Pas de pays trouvé", codePays);
				return null;
			}
			return (PaysNationalite) nomenclature;
		} catch (ApiException e) {
			logger.error("Erreur lors de la récupération du pays : {} : {}", codePays, e.getMessage());
		}
		return null;

	}

	/**
	 * 
	 * @param codeStructure
	 * @param codes
	 * @return List<String>
	 */
	public List<String> codePeriodeFromPeriodes(String codes) {
		return Arrays.asList(codes.split("[,;\\s]+"));
	}

	public StructureApi getStructureApi() {
		return structureApi;
	}

	public void setStructureApi(StructureApi structureApi) {
		this.structureApi = structureApi;
	}

	public InscriptionsApi getInscriptionsApi() {
		return inscriptionsApi;
	}

	public void setInscriptionsApi(InscriptionsApi inscriptionsApi) {
		this.inscriptionsApi = inscriptionsApi;
	}

	public OffreFormationServicePartielEtapes getOffreFormationServiceRevisited() {
		return offreFormationServiceRevisited;
	}

	public void setOffreFormationServiceRevisited(OffreFormationServicePartielEtapes offreFormationServiceRevisited) {
		this.offreFormationServiceRevisited = offreFormationServiceRevisited;
	}

	public CesureUtils getCesureUtils() {
		return cesureUtils;
	}

	public void setCesureUtils(CesureUtils cesureUtils) {
		this.cesureUtils = cesureUtils;
	}

}
