package org.esupportail.referentiel.pcscol.services;

import java.util.List;

import org.esupportail.referentiel.pcscol.api.identite.ApprenantExterneApi;
import org.esupportail.referentiel.pcscol.identite.model.IdentiteApprenantSummary;
import org.esupportail.referentiel.pcscol.identite.model.Pageable;
import org.esupportail.referentiel.pcscol.identite.model.PagedIdentiteApprenantSummaries;
import org.esupportail.referentiel.pcscol.identite.model.StatutIne;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
@ConditionalOnProperty(name = "app.mode_pegase")
public class IdentiteService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	ApprenantExterneApi apprenantExterneApi;

	public IdentiteService(@Autowired ApprenantExterneApi apprenantExterneApi) {
		super();
		this.apprenantExterneApi = apprenantExterneApi;
	}

	public IdentiteApprenantSummary getIdentiteByCodeEtu(String codeStructure, String codeEtu) {
		return getIdentiteByCodeEtuEtIne(codeStructure, codeEtu, null);
	}

	public IdentiteApprenantSummary getIdentiteByIne(String codeStructure, String ine) {
		return getIdentiteByCodeEtuEtIne(codeStructure, null, ine);
	}

	public IdentiteApprenantSummary getIdentiteByCodeEtuEtIne(String codeStructure, String codeEtu, String ine) {
		logger.info("getIdentiteByCodeEtuEtIne : codeStructure={}, codeEtu={}, ine={}", codeStructure, codeEtu, ine);
		try {

			String codeApprenant = codeEtu;

			Pageable pageable = new Pageable();
			pageable.setPage(0);
			pageable.setTaille(10);
			String prenom = null;
			String nom = null;

			Boolean actif = false;
			Boolean exact = true;
			Boolean doublonPotentiel = false;
			StatutIne etatIneMaitre = null;
			PagedIdentiteApprenantSummaries apprenant = apprenantExterneApi.rechercherIdentiteApprenant(codeStructure,
					pageable, prenom, nom, codeApprenant, ine, actif, exact, doublonPotentiel, etatIneMaitre);
			List<IdentiteApprenantSummary> items = apprenant.getItems();
			logger.debug("getIdentiteByCodeEtuEtIne : items={}", items.size());
			if (items.size()>1) {
				logger.warn("getIdentiteByCodeEtuEtIne : plusieurs identités trouvées pour codeStructure={}, codeEtu={}, ine={}",
						codeStructure, codeEtu, ine);
			}
			return items.get(0);

		} catch (Exception e) {
			logger.error("getIdentiteByCodeEtu : {}", e.getMessage());
			return null;
		}
	}
}
