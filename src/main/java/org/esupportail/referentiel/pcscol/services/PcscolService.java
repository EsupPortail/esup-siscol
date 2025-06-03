package org.esupportail.referentiel.pcscol.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.esupportail.referentiel.beans.ApogeeMap;
import org.esupportail.referentiel.beans.ApprenantDto;
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
import org.esupportail.referentiel.pcscol.ins.model.Apprenant;
import org.esupportail.referentiel.pcscol.ins.model.ApprenantEtInscriptions;
import org.esupportail.referentiel.pcscol.ins.model.Inscription;
import org.esupportail.referentiel.pcscol.ins.model.InscriptionComplete;
import org.esupportail.referentiel.pcscol.ins.model.Inscriptions;
import org.esupportail.referentiel.pcscol.ins.model.StatutGlobalPiece;
import org.esupportail.referentiel.pcscol.ins.model.StatutIne;
import org.esupportail.referentiel.pcscol.ins.model.StatutInscriptionVoeu;
import org.esupportail.referentiel.pcscol.ins.model.StatutPaiementVoeu;
import org.esupportail.referentiel.pcscol.ins.model.TriInscription;
import org.esupportail.referentiel.pcscol.invoker.ApiException;
import org.esupportail.referentiel.pcscol.mapper.ApprenantEtuInfoAdmMapperInterface;
import org.esupportail.referentiel.pcscol.mapper.OdfDtoMapperInterface;
import org.esupportail.referentiel.pcscol.odf.model.DescripteursObjetFormation;
import org.esupportail.referentiel.pcscol.odf.model.EnfantsStructure;
import org.esupportail.referentiel.pcscol.odf.model.Espace;
import org.esupportail.referentiel.pcscol.odf.model.MaquetteStructure;
import org.esupportail.referentiel.pcscol.odf.model.ObjetMaquetteDetail;
import org.esupportail.referentiel.pcscol.odf.model.ObjetMaquetteSummary;
import org.esupportail.referentiel.pcscol.odf.model.Periode;
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

	final transient Logger logger = LoggerFactory.getLogger(this.getClass());

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
				logger.debug("Inscription : {} ?=={}", ins.getCible().getPeriode().getCode(), codePeriode);
				if (ins.getCible().getPeriode().getCode().equalsIgnoreCase(codePeriode)) {
					EtapeInscription etpinscr = ApprenantEtuInfoAdmMapperInterface.Instance
							.stagesApprenantToEtapeInscription(ins);

					logger.debug("EtapeInscription : {}", etpinscr.getCodeEtp());
					if (etpinscr.getCodeComposante() != null && !etpinscr.getCodeComposante().isEmpty())
						try {
							Structure structure = structureApi.lireStructure(etpinscr.getCodeComposante());
							etpinscr.setLibComposante(structure.getAppellationOfficielle());
						} catch (ApiException e) {
							logger.error("lireStructure : " + e.getMessage());
						}
					etps.add(etpinscr);
				}
			});

		} catch (ApiException e) {
			logger.error("EtapeInscription : " + codeApprenant + " , " + codePeriode + " : " + e.getMessage() + " : "
					+ e.getCode());
		}

		return etps;
	}

	public LinkedHashMap<String, String> studentEtapeVets(String codeStructure, String codeApprenant, String annee) {

		List<EtapeInscription> etps = etapeInscription(codeStructure, codeApprenant, annee);
		LinkedHashMap<String, String> lEtapeInscriptions = new LinkedHashMap<String, String>();
		etps.forEach(e -> {
			lEtapeInscriptions.put(e.getCodeEtp(), e.getCodVrsVet());
		});

		return lEtapeInscriptions;
	}

	public ApogeeMap recupererIaIpParEtudiantAnnee(String codeStructure, String codeApprenant,
			List<String> codesPeriodes) {
		ApogeeMap apogeeMap = new ApogeeMap();
		/**
		 * TODO regime
		 */

		List<EtapeInscription> etapeInscriptions = new ArrayList<EtapeInscription>();

		codesPeriodes.forEach(codePeriode -> {
			logger.debug("recupererIaIpParEtudiantAnnee : {} , {} , {}", codeStructure, codeApprenant, codePeriode);
			List<EtapeInscription> etapeInscriptionsPartiel = etapeInscription(codeStructure, codeApprenant,
					codePeriode);
			etapeInscriptions.addAll(etapeInscriptionsPartiel);
		});

		logger.debug("----------------------- {}", etapeInscriptions);

		List<RegimeInscription> regimesInscriptions = new ArrayList<RegimeInscription>();
		apogeeMap.setListeEtapeInscriptions(etapeInscriptions);

		logger.debug("recupererIaIpParEtudiantAnnee : {} , {} , {}", codeStructure, codeApprenant, codesPeriodes);

		try {
			List<ElementPedagogique> lelps = chcService.lirelisteElementPedagogiqueStageApprenant(codeApprenant,
					codeStructure);
			if (lelps != null) {

				List<ElementPedagogique> filterdlElps = lelps.stream()
						.filter(e -> codesPeriodes.contains(e.getCodVrsVet())).collect(Collectors.toList());
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
				/**
				 * * TODO annee
				 * 
				 * on devrait traiter les regimes d'inscription par etapes et non de façon
				 * isolée héritage apogge
				 */

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
			EtudiantInfoAdm info = ApprenantEtuInfoAdmMapperInterface.Instance
					.apprenantToEtudiantInfoAdm(app.getApprenant());
			return info;
		} catch (ApiException e) {
			logger.error(" lireEtudiantInfoAdm : ", codeStructure, codeApprenant, e.getMessage(), e.getCode());
		}

		return null;
	}

	@Override
	public EtudiantRef lireEtudiantRef(String codeStructure, String codeApprenant, String annee) {
		ApprenantEtInscriptions app;
		try {
			app = inscriptionsApi.lireInscriptions(codeStructure, codeApprenant);
			Apprenant appInfo = app.getApprenant();
			EtudiantRef etudref = ApprenantEtuInfoAdmMapperInterface.Instance.apprenantToEtudiantRef(appInfo);
			return etudref;
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new EtudiantRef();
	}

	/**
	 * TODO boolean uniquementOuvrableAInscription, boolean
	 * uniquementOuvrableAuChoixDuCursus
	 */
	@Override
	@Cacheable(cacheNames = CacheConfig.PERMANENT, sync = true)
	public Map<String, String> lireMapFormations(String codeStructure, String codePeriode, boolean piaSeulement) {

		List<String> listCodePeriode = codePeriodeFromPeriodes(codeStructure, codePeriode);

		Map<String, String> mapAllFormations = new HashMap<String, String>();

		listCodePeriode.forEach(periode -> {

			UUID idPeriode = espaceService.chercherEspaceFromCode(codeStructure, periode);
			if (idPeriode != null) {

				logger.debug("lireMapFormations : {} ", idPeriode);
				List<ObjetMaquetteSummary> maquetteSummaries = offreFormationServiceRevisited
						.rechercheObjetMaquetteSummaryParPiaSeaulement(codeStructure, idPeriode.toString());
				logger.debug("lireMapFormations : {} ", maquetteSummaries.size());
				maquetteSummaries.stream().forEach(e -> {
					logger.debug("lireMapFormations : {}  {} =>{}", e.getCode(), e.getTypeObjetFormation(),
							e.getTypeObjetMaquette());
				});
				HashMap<String, String> mapFormations = offreFormationServiceRevisited
						.objetMaquetteSummaryMap(maquetteSummaries, periode);
				logger.debug("lireMapFormations : {} ", mapFormations.size());
				mapAllFormations.putAll(mapFormations);

			}

//			
//			
//			
//			
//			try {
//				Map<String, String> formations = (HashMap<String, String>) offreFormationService
//						.rechercherObjetMaquetteObjetFormation(codeStructure, periode,piaSeulement);
//				mapAllFormations.putAll(formations);
//			} catch (ApiException e) {
//				logger.error("lireMapFormations : " + codePeriode + " : " + e.getMessage() + " : " + e.getCode());
//			}

		});

		return mapAllFormations;
	}

	@Override
	@Cacheable(cacheNames = CacheConfig.PERMANENT, sync = true)
	public Map<String, String> lireMapStructures() {
		Map<String, String> mapComp = new LinkedHashMap<String, String>();
		List<Structure> strucList = lireListeStructure();
		logger.debug("lireMapStructures : {}", strucList.size());
		if (!strucList.isEmpty())
			strucList.forEach(s -> {
				mapComp.put(s.getCode(), s.getDenominationPrincipale());
				logger.info("lireMapStructures ::" + s.getCode());
				// peuplerArbreStructures(mapComp, s.getCode());
			});
		return mapComp;
	}

	@Override
	public EtabRef lireEtabRef() {
		List<Structure> strucList = lireListeStructure();
		EtabRef etabRef = new EtabRef();
		Structure structure = strucList.stream().filter(s -> s.getEstStructureMere()).findFirst().orElse(null);
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
		List<String> listCodePeriode = codePeriodeFromPeriodes(codeStructure, codesPeriodesChargementFormations);
		logger.debug("listCodePeriode: {}", listCodePeriode);
		final List<ObjetMaquetteSummary> objetMaquetteSummaries = new ArrayList<ObjetMaquetteSummary>();
		logger.debug("codeStructure : {} ", codeStructure);

		listCodePeriode.forEach(codePeiode -> {
			try {
				logger.debug("\t\tcodePeiode : {}", codePeiode);
				List<ObjetMaquetteSummary> diplomes = offreFormationService
						.rechercherObjetMaquetteDiplomeReferences(codeStructure, codePeiode);
				logger.debug("\t\tnbr diplomes {}", diplomes.size());

				objetMaquetteSummaries.addAll(diplomes);
			} catch (ApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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

		List<DiplomeReduitDto> diplomes = new ArrayList<DiplomeReduitDto>();
		objetMaquetteSummaries.forEach(f -> {
			try {

				ObjetMaquetteDetail objectMaqette = objetsMaquetteApi.lireObjetMaquette(codeStructure, f.getId());
				UUID idEspace = objectMaqette.getEspace();
				/**
				 * TODO gestion des espaces
				 */
				DiplomeReduitDto diplome = OdfDtoMapperInterface.Instance
						.diplomeReduitDtoFromObjetMaquette(objectMaqette);
				Espace esp = espacesApi.lireEspace(codeStructure, idEspace);
				diplome.setVersionDiplome(esp.getCode());
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

					// Enfant enfant = objectMaqette.getEnfants().get(0);

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
								etp.setCodVrsVet(esp.getCode());
								etp.setLibWebVet(objectMaqette2.getDescripteursObjetMaquette().getLibelle());
								diplome.getListeEtapes().add(etp);
							} catch (ApiException e1) {
								logger.error("{}", e1.getMessage());
							}

						});

					}
				}

			} catch (ApiException e) {
				logger.error(e.getMessage() + " : " + e.getCode());
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
		final List<EnfantsStructure> listeEnfantPia = new ArrayList<EnfantsStructure>();
		List<EnfantsStructure> enfants = maquetteStructure.getRacine().getEnfants();
		enfants.forEach(e -> {
			if (e.getObjetMaquette().getPia() != null && e.getObjetMaquette().getValide() == true
					&& e.getObjetMaquette().getPia().getActif() == true) {
				listeEnfantPia.add(e);
			}
		});
		return listeEnfantPia;
	}

	public List<ElementPedagogique> studentListeElpStage(String codeStructure, String codeEtape, String versionEtape) {

		List<ElementPedagogique> l = new ArrayList<ElementPedagogique>();
		try {
			List<ObjetMaquetteSummary> objetMaquetteSummaries = offreFormationService
					.rechercheObjetMaquetteSummary(codeStructure, codeEtape, versionEtape);
			if (objetMaquetteSummaries == null || objetMaquetteSummaries.isEmpty()) {
				logger.error("studentListeElpStage : " + codeStructure + " , " + codeEtape + " , " + versionEtape);
				logger.error("studentListeElpStage : " + "Aucun Objet Maquette trouvé");
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

				DescripteursObjetFormation dom = (DescripteursObjetFormation) e.getDescripteursObjetMaquette();

				elp.setTemElpTypeStage(String.valueOf(dom.getStage()));
				if (dom.getNature() != null)
					elp.setLibNatureElp(dom.getNature().getType());
				elp.setCodEtp(objetMaquetteSummary.getCode());
				elp.setCodVrsVet(versionEtape);
				elp.setNbrCrdElp(dom.getEcts());
				l.add(elp);
			});
		} catch (ApiException e) {
			logger.error("studentListeElpStage : " + e.getMessage());
		}

		return l;
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
			logger.error("structureApi.lireListeStructures : " + e.getMessage());
		}

		return response;

	}

	public List<ApprenantDto> recupererListeEtuParEtpEtDiplome(String codeComposante, String codePeriode,
			String codeEtape, String versionEtape, String codeDiplome, String versionDiplome, String codEtu, String nom,
			String prenom) {
		/**
		 * TODO versionEtape codeEtape
		 */

		return lireApprenantDtoFromInscriptions(codeComposante, codeEtape, codePeriode, nom, prenom, codEtu);
	}

	/**
	 * 
	 * @param codeStructure
	 * @param objetMaquette
	 * @param periode
	 * @param nomDeNaissance
	 * @param prenom
	 * @param codeApprenant
	 * @return
	 */
	public List<ApprenantDto> lireApprenantDtoFromInscriptions(String codeStructure, String objetMaquette,
			String periode, String nomDeNaissance, String prenom, String codeApprenant) {
		List<Inscription> ins = lireInscriptions(codeStructure, objetMaquette, periode, nomDeNaissance, prenom,
				codeApprenant);
		return ApprenantEtuInfoAdmMapperInterface.Instance.inscrptionToApprenantDto(ins);

	}

	public List<Inscription> lireInscriptionsByAnnee(String codeStructure, String objetMaquette, String annee,
			String nomDeNaissance, String prenom, String codeApprenant) {
		List<Periode> periodes = espaceService.espacesFromAnnee(codeStructure, annee);
		List<Inscription> inscriptions = new ArrayList<Inscription>();
		if (periodes != null && !periodes.isEmpty()) {
			periodes.forEach(p -> {
				List<Inscription> ins = lireInscriptions(codeStructure, objetMaquette, p.getCode(), nomDeNaissance,
						prenom, codeApprenant);
				if (ins != null && !ins.isEmpty()) {
					inscriptions.addAll(ins);
				}
			});
		}
		return inscriptions;
	}

	/**
	 * 
	 * @param codeStructure
	 * @param objetMaquette
	 * @param periode
	 * @param nomDeNaissance
	 * @param prenom
	 * @param codeApprenant
	 * @return List<Inscription> lireInscriptions
	 */

	public List<Inscription> lireInscriptions(String codeStructure, String objetMaquette, String periode,
			String nomDeNaissance, String prenom, String codeApprenant) {

		logger.debug(
				"codeStructure : {},  objetMaquette: {}, periode : {}, nomDeNaissance : {},  prenom : {},  codeApprenant : {}",
				codeStructure, objetMaquette, periode, nomDeNaissance, prenom, codeApprenant);

		if (codeApprenant != null && !codeApprenant.isBlank()) {
			nomDeNaissance = null;
			prenom = null;
		}

		/**
		 * StatutInscriptionVoeu
		 */
		List<StatutInscriptionVoeu> statutsInscription = new ArrayList<StatutInscriptionVoeu>();
		// statutsInscription.add(StatutInscriptionVoeu.VALIDE);
		// statutsInscription.add(StatutInscriptionVoeu.ANNULEE);
		List<StatutGlobalPiece> statutsPieces = new ArrayList<StatutGlobalPiece>();
		List<StatutPaiementVoeu> statutsPaiement = new ArrayList<StatutPaiementVoeu>();
		List<TriInscription> tri = null;
		String rechercheIne = null;
		String recherche = null;
		String nomOuPrenom = null;
		String ine = null;
		List<StatutIne> statutsIne = null;
		Integer limit = 50;

		try {
			/**
			 * TODO afin de passer à ins extrene remplacer listerInscriptionsValidees qui
			 * n'est pas disponible
			 */

			Inscriptions listInscriptions = inscriptionsApi.listerInscriptionsValidees(codeStructure,
					statutsInscription, statutsPieces, statutsPaiement, tri, rechercheIne, recherche, periode,
					objetMaquette, nomOuPrenom, nomDeNaissance, prenom, codeApprenant, ine, statutsIne, limit);
			/**
			 * TODO
			 */
			logger.debug("TotalElements : {}", listInscriptions.getTotalElements());
			List<Inscription> resultats = listInscriptions.getResultats();
			return resultats;
		} catch (ApiException e) {
			logger.error("" + codeStructure, statutsInscription, periode, objetMaquette, nomDeNaissance, prenom,
					codeApprenant);
		}
		return new ArrayList<Inscription>();

	}

	public List<String> recupererAnneesIa(String codeStructure, String codeEtud) {
		List<String> annaeeIa = new ArrayList<String>();
		List<Inscription> ins = lireInscriptions(codeStructure, null, null, null, null, codeEtud);
		ins.forEach(i -> {
			annaeeIa.add(i.getVoeu().getCible().getPeriode().getCode());
		});
		return annaeeIa;
	}

	public SignataireRef signaitaireRef(String composante) {
		try {
			Structure response = structureApi.lireStructure(composante);
			SignataireRef sign = new SignataireRef();
			sign.setNomSignataireComposante(
					response.getResponsable().getNom() + " " + response.getResponsable().getPrenom());
			sign.setQualiteSignataire(response.getResponsable().getTitre());
			return sign;
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			List<Commune> communes = new ArrayList<Commune>();
			for (Nomenclature nom : noms) {
				if (nom instanceof Commune) {
					communes.add((Commune) nom);
				}
			}
			if (noms != null && !noms.isEmpty()) {
				Commune commune = communes.stream().filter(n -> n.getCodeInsee().equalsIgnoreCase(codeCommune)).findFirst()
						.orElse(null);
				if (commune != null) {
					logger.debug("chercheCommune : {} , {} : {}", codePostal, codeCommune, commune.getLibelleLong());
					return (Commune) commune;
				} else {
					logger.debug("chercheCommune : {} , {} : Pas de commune trouvée", codePostal, codeCommune);
				}
			} else {
				logger.debug("chercheCommune : {} , {} : Pas de commune trouvée", codePostal, codeCommune);
			}
		} catch (ApiException e) {
			logger.error("chercheCommune : " + codePostal + " , " + codeCommune + " : " + e.getMessage() + " : "
					+ e.getCode());
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
			logger.error("Erreur lors de la récupération Pays : " + codePays + " : " + e.getMessage() + " : " + e.getCode());
		}
		return null;

	}

	/**
	 * 
	 * @param codeStructure
	 * @param codes
	 * @return List<String>
	 */
	public List<String> codePeriodeFromPeriodes(String codeStructure, String codes) {
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

}
