package org.esupportail.referentiel.pcscol.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.esupportail.referentiel.pcscol.api.EspacesApi;
import org.esupportail.referentiel.pcscol.api.InscriptionsApi;
import org.esupportail.referentiel.pcscol.ins.model.Periode;
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

	@Autowired
	private InscriptionsApi inscriptionsApi;

	/**
	 * 
	 * @param codeStructure
	 * @return
	 */
	public List<Periode> allEespaces(String codeStructure) {

		try {

			List<Periode> periodes = inscriptionsApi.listerPeriodes(codeStructure);
			return periodes;

		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param codeStructure
	 * @param annee
	 * @return
	 */
	public List<Periode> espacesFromAnnee(String codeStructure, String annee) {
		List<Periode> espaces = allEespaces(codeStructure);
		List<Periode> filtredEsapce = new ArrayList<Periode>();
		espaces.forEach(espace -> {
			String anneeUniv = String.valueOf(espace.getAnneeUniversitaire());
			if (anneeUniv.equals(annee)) {
				filtredEsapce.add(espace);
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

		List<Periode> espaces = allEespaces(codeStructure);
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
		return espaces.getItems();

	}

	public UUID chercherEspaceFromCode(String codeStructure, String codePeriode) {
		Pageable pageable = new Pageable();
		pageable.setPage(0);
		pageable.setTaille(50);
		PagedEspaces espaces;
		try {
			espaces = espacesApi.rechercherEspaces(codeStructure, pageable, codePeriode, null, true);
			logger.debug("" + espaces);
			if (espaces.getItems() != null && !espaces.getItems().isEmpty() ){
				logger.debug("" + espaces);
				for (Espace item : espaces.getItems()) {
					if (item.getCode().equals(codePeriode)) {
						UUID idEsapce = item.getId();
						return idEsapce;
					}
				}
			}
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
