package org.esupportail.referentiel.pcscol.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.esupportail.referentiel.beans.ApogeeMap;
import org.esupportail.referentiel.beans.EtudiantInfoAdm;
import org.esupportail.referentiel.beans.EtudiantRef;
import org.esupportail.referentiel.pcscol.api.FormationsApi;
import org.esupportail.referentiel.pcscol.api.StagesApi;
import org.esupportail.referentiel.pcscol.api.StructuresApi;
import org.esupportail.referentiel.pcscol.invoker.ApiException;
import org.esupportail.referentiel.pcscol.mapper.ApprenantEtuInfoAdmMapper;
import org.esupportail.referentiel.pcscol.mapper.GlobalMapper;
import org.esupportail.referentiel.pcscol.model.formation.Formation;
import org.esupportail.referentiel.pcscol.model.formation.Structure;
import org.esupportail.referentiel.pcscol.model.sta.Apprenant;
import org.esupportail.referentiel.pcscol.model.sta.StagesApprenant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class PcscolService implements PcscolServiceI {

	final transient Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StagesApi stagesApi;

	@Autowired
	private FormationsApi formationsApi;

	@Autowired
	private StructuresApi structuresApi;

	
	
	
	@Override
	public ApogeeMap recupererIaIpParEtudiantAnnee(String codeStructure, String codeApprenant, String annee) {
		StagesApprenant response;
		try {
			response = stagesApi.listerInscriptionsAvecStages(codeStructure, codeApprenant);
			System.out.println(response.getCodeStructure());
			ApogeeMap apogeeMap = new ApogeeMap();
			GlobalMapper.stagesApprenantStagesToApogeeMap(response, apogeeMap,annee);
			return apogeeMap;
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			logger.error(codeStructure + " : " + codeApprenant + " :" + e.getMessage());
			return new ApogeeMap();
		}

	}

	public StagesApprenant lireStagesApprenant(String codeStructure, String codeApprenant) {
		try {
			StagesApprenant stageApp = stagesApi.listerInscriptionsAvecStages(codeStructure, codeApprenant);
			return stageApp;
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			logger.error("lireStagesApprenant ERRORS :" + e.getMessage() + " : " + e.getCode());
			return new StagesApprenant();
		}
	}

	@Override
	public EtudiantInfoAdm lireEtudiantInfoAdm(String codeStructure, String codeApprenant) {
		StagesApprenant stgApp = lireStagesApprenant(codeStructure, codeApprenant);
		return lireEtudiantInfoAdm(stgApp);
	}

	@Override
	public EtudiantRef lireEtudiantRef(String codeStructure, String codeApprenant, String annee) {
		StagesApprenant stgApp = lireStagesApprenant(codeStructure, codeApprenant);
		return lireEtudiantRef(stgApp);
	}

	private EtudiantRef lireEtudiantRef(StagesApprenant stgApp) {

		Apprenant app = stgApp.getApprenant();
		EtudiantRef etu = ApprenantEtuInfoAdmMapper.Instance.apprenantToEtudiantRef(app);
		return etu;
	}

	public EtudiantInfoAdm lireEtudiantInfoAdm(StagesApprenant stgApp) {
		if (stgApp.getApprenant() == null) {
			return null;
		}
		Apprenant app = stgApp.getApprenant();
		EtudiantInfoAdm etu = ApprenantEtuInfoAdmMapper.Instance.apprenantToEtudiantInfoAdm(app);
		return etu;

	}

	public List<Formation> listeFormations(String codeStructure, String codePeriode,
			boolean uniquementOuvrableAInscription, boolean uniquementOuvrableAuChoixDuCursus) {
		List<Formation> response;

		try {
			response = formationsApi.lireListe(codeStructure, codePeriode, uniquementOuvrableAInscription,
					uniquementOuvrableAuChoixDuCursus);
			return response;
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			logger.error("listeFormations ERRORS :" + e.getMessage() + " : " + e.getCode());

			return null;
		}

	}

	@Override
	public LinkedHashMap<String, String> lireMapFormations(String codeStructure, String codePeriode,
			boolean uniquementOuvrableAInscription, boolean uniquementOuvrableAuChoixDuCursus) {
		Map<String, String> mapStruct = lireMapStructures(codeStructure);
		LinkedHashMap<String, String> lSI = new LinkedHashMap<String, String>();
		for (String struct : mapStruct.keySet()) {
			List<Formation> formations = listeFormations(struct, codePeriode, uniquementOuvrableAInscription,
					uniquementOuvrableAuChoixDuCursus);
			if (formations == null) {
				logger.error("formations: vide pour  " + struct);
			} else {
				formations.forEach(formation -> {
					lSI.put(formation.getCode() + ";" + formation.getVersion(), formation.getLibelle());

				});
			}

		}

		return lSI;
	}

	public void peuplerArbreStructures(Map<String, String> mapComp, String codeStructure) {
		List<Structure> strucList = lireListeStructure(codeStructure);
		if (!strucList.isEmpty())
			strucList.forEach(s -> {
				mapComp.put(s.getCode(), s.getDenominationPrincipale());
				peuplerArbreStructures(mapComp, s.getCode());
			});

	}

	@Override
	public Map<String, String> lireMapStructures(String codeStructure) {
		Map<String, String> mapComp = new LinkedHashMap<String, String>();
		List<Structure> strucList = lireListeStructure(codeStructure);

		if (!strucList.isEmpty())
			strucList.forEach(s -> {
				mapComp.put(s.getCode(), s.getDenominationPrincipale());
				logger.info("lireMapStructures ::" + s.getCode());
				List<Structure> en2 = lireListeStructure(s.getCode());

				/**
				 * TODO l'arborescence des structures
				 */
				en2.forEach(s2 -> {
					System.out.println("\t" + s2.getCode());
				});

				// peuplerArbreStructures(mapComp, s.getCode());
			});
		return mapComp;
	}

	public List<Structure> lireListeStructure(String codeStructure) {
		List<Structure> response = new ArrayList<>();
		try {
			response = structuresApi.lireListeStructure(codeStructure);
			return response;
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			logger.error("lireListeStructure ERRORS :" + e.getMessage() + " : " + e.getCode());
			return response;
		}

	}

	public StagesApi getStagesApi() {
		return stagesApi;
	}

	public void setStagesApi(StagesApi stagesApi) {
		this.stagesApi = stagesApi;
	}

	public FormationsApi getFormationsApi() {
		return formationsApi;
	}

	public void setFormationsApi(FormationsApi formationsApi) {
		this.formationsApi = formationsApi;
	}

	public StructuresApi getStructuresApi() {
		return structuresApi;
	}

	public void setStructuresApi(StructuresApi structuresApi) {
		this.structuresApi = structuresApi;
	}

}
