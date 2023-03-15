package org.esupportail.referentiel.pcscol.mapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import org.esupportail.referentiel.beans.ApogeeMap;
import org.esupportail.referentiel.beans.ElementPedagogique;
import org.esupportail.referentiel.beans.EtudiantInfoAdm;
import org.esupportail.referentiel.beans.EtudiantRef;
import org.esupportail.referentiel.pcscol.model.formation.Formation;
import org.esupportail.referentiel.pcscol.model.sta.Apprenant;
import org.esupportail.referentiel.pcscol.model.sta.Inscription;
import org.esupportail.referentiel.pcscol.model.sta.StagesApprenant;

public class GlobalMapper {

	static final SimpleDateFormat dtForamt = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);

	public static EtudiantInfoAdm stagesApprenantToEtuInfoAdm(StagesApprenant stagesApprenant) {
		EtudiantInfoAdm etudiantInfoAdm = new EtudiantInfoAdm();
		Apprenant apprenant = stagesApprenant.getApprenant();

		// stagesApprenant.getInscriptions().get(0).getStages();

		etudiantInfoAdm.setNumEtu(apprenant.getCodeApprenant());
		etudiantInfoAdm.setNumeroINE(apprenant.getIne());
		etudiantInfoAdm.setCodInd(apprenant.getCodeApprenant());

		etudiantInfoAdm.setNomPatronymique(apprenant.getNomDeNaissance());
		etudiantInfoAdm.setNomUsuel(apprenant.getNomUsuel());
		etudiantInfoAdm.setPrenom1(apprenant.getPrenom());
		etudiantInfoAdm.setPrenom2(null);

		Date dtNaiss = apprenant.getDateDeNaissance();
		etudiantInfoAdm.setDateNaissance(dtForamt.format(dtNaiss));
		etudiantInfoAdm.setSexe(apprenant.getGenre());

		apprenant.getCodePostal();
		apprenant.getCommune();

		apprenant.getLigne1OuEtage();
		apprenant.getLigne3OuVoie();
		apprenant.getLigne4OuComplement();
		apprenant.getLigne5Etranger();
		apprenant.getPays();

		apprenant.getTelephone();

		return etudiantInfoAdm;
	}

	public LinkedHashMap<String, String> getEtapesRef(List<Formation> formations) {
		LinkedHashMap<String, String> lSI = new LinkedHashMap<String, String>();
		formations.forEach(formation -> {
			lSI.put(formation.getCode() + "," + formation.getVersion(), formation.getLibelle());

		});

		return lSI;
	}

	public static void stagesApprenantStagesToApogeeMap(StagesApprenant stagesApprenant, ApogeeMap apogeeMap,
			String annee) {
		List<Inscription> psScolIns = stagesApprenant.getInscriptions();
		List<ElementPedagogique> listElps = new ArrayList<>();
		psScolIns.forEach(ins -> {
			if (ins.getPeriode()!=null && ins.getPeriode().getAnneeUniversitaire() != null) {

				String annUNivIns = String.valueOf(ins.getPeriode().getAnneeUniversitaire());
				if (annUNivIns.equals(annee)) {
					if (ins.getStages() != null) {
						ins.getStages().forEach(stg -> {

							ElementPedagogique elp = ApprenantEtuInfoAdmMapper.Instance
									.stagesApprenantToElementPedagogique(stg);
							listElps.add(elp);
						});
					}

					listElps.forEach(elp -> {
						elp.setCodEtp(ins.getSupportInscription().getCodeChemin());
						elp.setCodVrsVet(ins.getSupportInscription().getSupportInscriptionId());

					});

					/**
					 * TODO
					 */

				}

			}
		});

		apogeeMap.setListeELPs(listElps);

	}

}
