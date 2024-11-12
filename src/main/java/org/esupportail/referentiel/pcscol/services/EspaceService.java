package org.esupportail.referentiel.pcscol.services;

import java.util.ArrayList;
import java.util.List;

import org.esupportail.referentiel.pcscol.api.EspacesApi;
import org.esupportail.referentiel.pcscol.api.InscriptionsApi;
import org.esupportail.referentiel.pcscol.ins.model.Periode;
import org.esupportail.referentiel.pcscol.invoker.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "app.mode_pegase")
public class EspaceService {

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
	
	
	

	public EspacesApi getEspacesApi() {
		return espacesApi;
	}

	public void setEspacesApi(EspacesApi espacesApi) {
		this.espacesApi = espacesApi;
	}
}
