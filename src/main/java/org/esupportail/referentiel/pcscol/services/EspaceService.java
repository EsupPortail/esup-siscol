package org.esupportail.referentiel.pcscol.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.esupportail.referentiel.pcscol.api.EspacesApi;
import org.esupportail.referentiel.pcscol.odf.model.Periode;
import org.esupportail.referentiel.pcscol.invoker.ApiException;
import org.esupportail.referentiel.pcscol.odf.model.Espace;
import org.esupportail.referentiel.pcscol.odf.model.Pageable;
import org.esupportail.referentiel.pcscol.odf.model.PagedEspaces;
import org.esupportail.referentiel.pcscol.odf.model.TypeEspace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "app.mode_pegase")
public class EspaceService {

	final transient Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EspacesApi espacesApi;

	/**
	 * 
	 * @param codeStructure
	 * @return
	 */
	public List<Espace> allEspaces(String codeStructure) {

		List<Espace> espaces = new ArrayList<Espace>();

		try {

			Pageable pageable = new Pageable();
			pageable.setPage(0);
			pageable.setTaille(50);
			String r = null;
			TypeEspace type = null;
			Boolean actif = true;
			logger.debug("code structure : {} ", codeStructure);
			PagedEspaces pagedEspaces = espacesApi.rechercherEspaces(codeStructure, pageable, r, type, actif);

			if (pagedEspaces.getItems() != null && !pagedEspaces.getItems().isEmpty()) {
				espaces.addAll(pagedEspaces.getItems());
			}
			int nbrPage = pagedEspaces.getTotalPages();
			if (nbrPage > 1) {
				for (int i = 1; i < nbrPage; i++) {
					pageable.setPage(i);
					PagedEspaces pagedEspaces2 = espacesApi.rechercherEspaces(codeStructure, pageable, r, type, actif);
					if (pagedEspaces2.getItems() != null && !pagedEspaces2.getItems().isEmpty()) {
						espaces.addAll(pagedEspaces2.getItems());
					}
				}
			}

		} catch (ApiException e) {
			logger.error("Erreur lors de la récupération des périodes : " + e.getMessage());
		}
		return espaces;
	}

	/**
	 * 
	 * @param codeStructure
	 * @param annee
	 * @return
	 */
	public List<Periode> espacesFromAnnee(String codeStructure, String annee) {
		List<Espace> espaces = allEspaces(codeStructure);
		List<Periode> filtredEsapce = new ArrayList<Periode>();
		espaces.forEach(espace -> {
			if (espace instanceof Periode) {

				Periode periode = (Periode) espace;
				String anneeUniv = String.valueOf(periode.getAnneeUniversitaire());
				if (anneeUniv.equals(annee)) {
					filtredEsapce.add(periode);
				}
			}
		});
		return filtredEsapce;
	}

	/**
	 * 
	 * @param espaces
	 * @return
	 */
	public List<String> anneeUnivFromEsapces(List<Periode> espaces) {
		List<String> anneesUniv = new ArrayList<String>();
		if (espaces != null) {
			espaces.forEach(e -> {
				String anneeUniv = String.valueOf(e.getAnneeUniversitaire());
				anneesUniv.add(anneeUniv);
			});
		}
		return anneesUniv;

	}

	/**
	 * 
	 * @param espaces
	 * @param codesPeriode
	 * @return
	 */
	public List<String> anneeUnivFromEsapces(List<Periode> espaces, List<String> codesPeriode) {
		List<String> anneesUniv = new ArrayList<String>();
		if (espaces != null) {
			espaces.forEach(e -> {
				if (codesPeriode.contains(e.getCode())) {
					String anneeUniv = String.valueOf(e.getAnneeUniversitaire());
					anneesUniv.add(anneeUniv);
				}
			});
		}
		return anneesUniv;

	}

	/**
	 * 
	 * @param codeStructure
	 * @param codesPeriode
	 * @return
	 */

	public List<String> anneeUnivFromEsapces(String codeStructure, List<String> codesPeriode) {
		
		/**
		 * TODO
		 * 
		 */
		logger.debug("Recherche des années universitaires pour les codes de période : {}", codesPeriode);
		List<Espace> espaces = allEspaces(codeStructure);
		List<String> anneesUniv = new ArrayList<String>();
		if (espaces != null) {
			espaces.forEach(e -> {
				if (e instanceof Periode) {
					Periode periode = (Periode) e;
					String anneeUniv = String.valueOf(periode.getAnneeUniversitaire());
					anneesUniv.add(anneeUniv);
				}
				
			});
		}
		logger.debug("Années universitaires trouvées : {}", anneesUniv);
		return anneesUniv;

	}

	public List<Espace> checherPeriodeParCode(String codeStructure, String code) throws ApiException {
		Pageable pageable = new Pageable();
		pageable.setPage(0);
		pageable.setTaille(50);
		String r = code;
		TypeEspace type = null;
		// ( String codeStructure, Pageable pageable, String r, TypeEspace type, Boolean
		// actif)
		PagedEspaces espaces = espacesApi.rechercherEspaces(codeStructure, pageable, r, type, true);
		logger.debug("nbr d'Espaces pour le code   " + r + " :" + espaces.getTotalElements());
		return espaces.getItems().stream().filter(e -> e.getCode().equals(code)).toList();

	}

	public UUID chercherEspaceFromCode(String codeStructure, String codePeriode) {
		Pageable pageable = new Pageable();
		pageable.setPage(0);
		pageable.setTaille(50);
		PagedEspaces espaces;
		try {
			espaces = espacesApi.rechercherEspaces(codeStructure, pageable, codePeriode, null, null);
			logger.debug("" + espaces);
			if (espaces.getItems() != null && !espaces.getItems().isEmpty()) {
				logger.debug("" + espaces);
				for (Espace item : espaces.getItems()) {
					if (item.getCode().equals(codePeriode)) {
						UUID idEsapce = item.getId();
						return idEsapce;
					}
				}
			}
		} catch (ApiException e) {
			logger.error("Erreur lors de la récupération des espaces : " + e.getMessage());
		}

		return null;

	}

	public EspacesApi getEspacesApi() {
		return espacesApi;
	}

	public void setEspacesApi(EspacesApi espacesApi) {
		this.espacesApi = espacesApi;
	}
}
