package org.esupportail.referentiel.pcscol.mapper;

import java.util.ArrayList;
import java.util.List;

import org.esupportail.referentiel.beans.ApogeeMap;
import org.esupportail.referentiel.beans.EtapeInscription;
import org.esupportail.referentiel.beans.RegimeInscription;
import org.esupportail.referentiel.pcscol.ins.model.ApprenantEtInscriptions;
import org.esupportail.referentiel.pcscol.ins.model.InscriptionComplete;
import org.esupportail.referentiel.pcscol.ins.model.OccurrenceNomenclature;

public class MapperPcScolApogeeMap {

	public static ApogeeMap mapToApogge(ApprenantEtInscriptions apprenantEtInscriptions) {
		List<InscriptionComplete> insCompletes = apprenantEtInscriptions.getInscriptions();
		List<EtapeInscription> etps = ApprenantEtuInfoAdmMapperInterface.Instance
				.stagesApprenantToEtapeInscription(insCompletes);
		ApogeeMap apogeeMap = new ApogeeMap();
		apogeeMap.setListeEtapeInscriptions(etps);

		final List<RegimeInscription> regimes = new ArrayList<RegimeInscription>();

		insCompletes.forEach(ins -> {
			OccurrenceNomenclature r = ins.getRegimeInscription();
			RegimeInscription rins = ApprenantEtuInfoAdmMapperInterface.Instance.toEsupRegimeInscription(r);
			regimes.add(rins);
		});

		apogeeMap.setRegimeInscription(regimes);

		return apogeeMap;
	}

}
