package org.esupportail.referentiel.pcscol.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.esupportail.referentiel.beans.ApogeeMap;
import org.esupportail.referentiel.beans.EtudiantInfoAdm;
import org.esupportail.referentiel.beans.EtudiantRef;
import org.esupportail.referentiel.pcscol.api.StructureApi;
import org.esupportail.referentiel.pcscol.invoker.ApiException;
import org.esupportail.referentiel.pcscol.mapper.ApprenantEtuInfoAdmMapper;
import org.esupportail.referentiel.pcscol.model.formation.Formation;
import org.esupportail.referentiel.pcscol.model.sta.Apprenant;
import org.esupportail.referentiel.pcscol.model.sta.StagesApprenant;
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

	@Override
	public ApogeeMap recupererIaIpParEtudiantAnnee(String codeStructure, String codeApprenant, String annee) {

		return new ApogeeMap();

	}

	public StagesApprenant lireStagesApprenant(String codeStructure, String codeApprenant) {
		return new StagesApprenant();
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
		List<Formation> response = new ArrayList<Formation>();
		return response;

	}

	@Override
	public LinkedHashMap<String, String> lireMapFormations(String codeStructure, String codePeriode,
			boolean uniquementOuvrableAInscription, boolean uniquementOuvrableAuChoixDuCursus) {
		Map<String, String> mapStruct = lireMapStructures();
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

	public List<Structure> lireListeStructure() {
		List<Structure> response = new ArrayList<>();
		try {
			response=structureApi.lireListeStructures();
		} catch (ApiException e) {
			logger.error("structureApi.lireListeStructures : "+ e.getMessage());
		}

		return response;

	}

	public StructureApi getStructureApi() {
		return structureApi;
	}

	public void setStructureApi(StructureApi structureApi) {
		this.structureApi = structureApi;
	}

}
