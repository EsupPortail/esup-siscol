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
import org.esupportail.referentiel.beans.EtapeInscription;
import org.esupportail.referentiel.beans.EtudiantInfoAdm;
import org.esupportail.referentiel.beans.EtudiantRef;
import org.esupportail.referentiel.beans.RegimeInscription;
import org.esupportail.referentiel.beans.SignataireRef;
import org.esupportail.referentiel.pcscol.api.EspacesApi;
import org.esupportail.referentiel.pcscol.api.InscriptionsApi;
import org.esupportail.referentiel.pcscol.api.MaquettesApi;
import org.esupportail.referentiel.pcscol.api.ObjetsMaquetteApi;
import org.esupportail.referentiel.pcscol.api.StructureApi;
import org.esupportail.referentiel.pcscol.ins.model.Apprenant;
import org.esupportail.referentiel.pcscol.ins.model.ApprenantEtInscriptions;
import org.esupportail.referentiel.pcscol.ins.model.Inscription;
import org.esupportail.referentiel.pcscol.ins.model.InscriptionComplete;
import org.esupportail.referentiel.pcscol.ins.model.Inscriptions;
import org.esupportail.referentiel.pcscol.ins.model.Periode;
import org.esupportail.referentiel.pcscol.ins.model.StatutIne;
import org.esupportail.referentiel.pcscol.ins.model.StatutInscriptionVoeu;
import org.esupportail.referentiel.pcscol.ins.model.StatutPaiementVoeu;
import org.esupportail.referentiel.pcscol.ins.model.StatutPiecesVoeu;
import org.esupportail.referentiel.pcscol.ins.model.TriInscription;
import org.esupportail.referentiel.pcscol.invoker.ApiException;
import org.esupportail.referentiel.pcscol.mapper.ApprenantEtuInfoAdmMapperInterface;
import org.esupportail.referentiel.pcscol.mapper.OdfDtoMapperInterface;
import org.esupportail.referentiel.pcscol.odf.model.DescripteursObjetFormation;
import org.esupportail.referentiel.pcscol.odf.model.DescripteursObjetMaquette;
import org.esupportail.referentiel.pcscol.odf.model.Enfant;
import org.esupportail.referentiel.pcscol.odf.model.EnfantsStructure;
import org.esupportail.referentiel.pcscol.odf.model.Espace;
import org.esupportail.referentiel.pcscol.odf.model.MaquetteStructure;
import org.esupportail.referentiel.pcscol.odf.model.ObjetMaquetteDetail;
import org.esupportail.referentiel.pcscol.odf.model.ObjetMaquetteSummary;
import org.esupportail.referentiel.pcscol.ref_api.model.Structure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
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
	private ChcService chcService;
	@Autowired
	private EspaceService espaceService;

	public List<EtapeInscription> etapeInscription(String codeStructure, String codeApprenant, String annee) {

		ApprenantEtInscriptions app;
		List<EtapeInscription> etps = new ArrayList<EtapeInscription>();
		try {
			app = inscriptionsApi.lireInscriptions(codeStructure, codeApprenant);

			List<InscriptionComplete> inscriptions = app.getInscriptions();

			inscriptions.forEach(ins -> {
				if (ins.getCible().getPeriode().getCode().equalsIgnoreCase(annee)) {
					EtapeInscription etpinscr = ApprenantEtuInfoAdmMapperInterface.Instance
							.stagesApprenantToEtapeInscription(ins);
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
			logger.error("EtapeInscription : " + codeApprenant + " , " + annee + " : " + e.getMessage() + " : "
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

	@Override
	public ApogeeMap recupererIaIpParEtudiantAnnee(String codeStructure, String codeApprenant, String codePeriode) {
		ApogeeMap apogeeMap = new ApogeeMap();
		/**
		 * TODO regime
		 */

		List<String> codesPeriodes = new ArrayList<String>();
		codesPeriodes.add(codePeriode);
		List<EtapeInscription> etapeInscriptions = etapeInscription(codeStructure, codeApprenant, codePeriode);
		List<RegimeInscription> regimesInscriptions = new ArrayList<RegimeInscription>();
		apogeeMap.setListeEtapeInscriptions(etapeInscriptions);
		try {
			List<ElementPedagogique> lelps = chcService.lirelisteElementPedagogiqueStageApprenant(codeApprenant,
					codeStructure);
			if (lelps != null) {
				List<ElementPedagogique> filterdlElps = lelps.stream()
						.filter(e -> e.getCodVrsVet().equalsIgnoreCase(codePeriode)).collect(Collectors.toList());
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
			if (listeAnnee != null && !listeAnnee.isEmpty())
				regime.setAnnee(listeAnnee.get(0));
			regime.setCodRegIns(etp.getRegimeIns());
			//regime.setCodRegIns(etp.get)
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
	public HashMap<String, String> lireMapFormations(String codeStructure, String codePeriode,
			boolean uniquementOuvrableAInscription, boolean uniquementOuvrableAuChoixDuCursus) {

		List<String> listCodePeriode = codePeriodeFromPeriodes(codeStructure, codePeriode);

		HashMap<String, String> mapAllFormations = new HashMap<String, String>();

		listCodePeriode.forEach(periode -> {

			try {
				HashMap<String, String> formations = (HashMap<String, String>) offreFormationService
						.rechercherObjetMaquetteObjetFormation(codeStructure, periode);
				mapAllFormations.putAll(formations);
			} catch (ApiException e) {
				logger.error("lireMapFormations : " + codePeriode + " : " + e.getMessage() + " : " + e.getCode());
			}

		});

		return mapAllFormations;
	}

	@Override
	public Map<String, String> lireMapStructures() {
		Map<String, String> mapComp = new LinkedHashMap<String, String>();
		List<Structure> strucList = lireListeStructure();

		if (!strucList.isEmpty())
			strucList.forEach(s -> {
				mapComp.put(s.getCode(), s.getDenominationPrincipale());
				logger.info("lireMapStructures ::" + s.getCode());
				// peuplerArbreStructures(mapComp, s.getCode());
			});
		return mapComp;
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

		listCodePeriode.forEach(codePeiode -> {
			try {

				List<ObjetMaquetteSummary> diplomes = offreFormationService
						.rechercherObjetMaquetteDiplomeReferences(codeStructure, codePeiode);
				logger.debug("nbr diplomes {}", diplomes.size());

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
	public List<DiplomeReduitDto> diplomeRef(String codeStructure, String codesPeriodesChargementFormations) {
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
				System.out.println("-------------------------------");
				if (!objectMaqette.getEnfants().isEmpty()) {
					// System.out.println(objectMaqette.getEnfants() + "+++++++++++++++++++++");
					MaquetteStructure maquetteStructure = offreFormationService.lireMaquette(codeStructure,
							f.getId().toString());

					// System.out.println(maquetteStructure);
					List<EnfantsStructure> enfants = listeEnfantsObjectMaquettePia(maquetteStructure);

					// Enfant enfant = objectMaqette.getEnfants().get(0);
					ObjetMaquetteDetail objectMaqette2 = objetsMaquetteApi.lireObjetMaquette(codeStructure,
							enfants.get(0).getObjetMaquette().getId());
					System.out.println(objectMaqette2.getCode() + "-----------------");
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
			if (e.getObjetMaquette().getPia() != null) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public List<ApprenantDto> recupererListeEtuParEtpEtDiplome(String codeComposante, String annee, String codeEtape,
			String versionEtape, String codeDiplome, String versionDiplome, String codEtu, String nom, String prenom) {
		/**
		 * TODO versionEtape codeEtape
		 */

		return lireApprenantDtoFromInscriptions(codeComposante, codeDiplome, annee, nom, prenom, codEtu);
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
		List<StatutPiecesVoeu> statutsPieces = new ArrayList<StatutPiecesVoeu>();
		List<StatutPaiementVoeu> statutsPaiement = new ArrayList<StatutPaiementVoeu>();
		List<TriInscription> tri = null;
		String rechercheIne = null;
		String recherche = null;
		String nomOuPrenom = null;
		String ine = null;
		List<StatutIne> statutsIne = null;
		Integer limit = 50;

		try {
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
			sign.setNomSignataireComposante(response.getResponsable().getNom());
			sign.setQualiteSignataire(response.getResponsable().getTitre());
			return sign;
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new SignataireRef();
	}

	/**
	 * 
	 * @param codeStructure
	 * @param codes
	 * @return List<String>
	 */
	public List<String> codePeriodeFromPeriodes(String codeStructure, String codes) {

		List<String> listCodePeriode = new ArrayList<>();
		if (codes == null || codes.isEmpty()) {
			try {
				List<Periode> listPeriodes = inscriptionsApi.listerPeriodes(codeStructure);

				for (Periode p : listPeriodes) {
					listCodePeriode.add(p.getCode());
				}

			} catch (ApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		} else {
			listCodePeriode = Arrays.asList(codes.split("[,;\\s]+"));
		}
		return listCodePeriode;

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

}
