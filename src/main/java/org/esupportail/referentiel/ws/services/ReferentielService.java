package org.esupportail.referentiel.ws.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.esupportail.referentiel.beans.RegimeInscriptionReduit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import gouv.education.apogee.commun.client.ws.ReferentielMetier.ReferentielMetierServiceInterface;
import gouv.education.apogee.commun.client.ws.ReferentielMetier.RegimeInscDTO;
import gouv.education.apogee.commun.client.ws.ReferentielMetier.WebBaseException_Exception;
import java.util.Collections;

@ConditionalOnProperty(name = "app.mode_apogee")
@Service
public class ReferentielService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ReferentielMetierServiceInterface referentielMetierService;

	public ReferentielService(@Autowired ReferentielMetierServiceInterface referentielMetierService) {
		super();
		this.referentielMetierService = referentielMetierService;
	}

	public Map<String, String> regimesInscriptions() {
		logger.info("Appel du service de référentiel métier pour récupérer les régimes d'inscription");
		try {
			List<RegimeInscDTO> response = referentielMetierService.recupererRegimeInscription(null, null);
			response.forEach(dto -> logger.debug("Régime d'inscription récupéré : code={}, libellé={} , {}",
					dto.getCodRegIns(), dto.getLibRegIns(), dto.getLicRegIns()));

			Map<String, String> regimeInscriptionMap = response.stream()
					.collect(Collectors.toMap(RegimeInscDTO::getCodRegIns, RegimeInscDTO::getLibRegIns));
			logger.info("Régimes d'inscription récupérés : {}", regimeInscriptionMap);
			return regimeInscriptionMap;
		} catch (WebBaseException_Exception e) {

			logger.error(
					"Erreur lors de l'appel du service de référentiel métier pour récupérer les régimes d'inscription {}",
					e.getMessage());

		}
		return Collections.emptyMap();
	}

	public List<RegimeInscriptionReduit> regimesInscriptionsReduit() {
		logger.info("Appel du service de référentiel métier pour récupérer les régimes d'inscription réduits");
		try {
			List<RegimeInscDTO> response = referentielMetierService.recupererRegimeInscription(null, null);
			List<RegimeInscriptionReduit> regimeInscriptionReduitList = response.stream()
					.map(dto -> new RegimeInscriptionReduit(dto.getCodRegIns(), dto.getLibRegIns(), dto.getLicRegIns()))
					.toList();
			logger.info("Régimes d'inscription réduits récupérés : {}", regimeInscriptionReduitList);
			return regimeInscriptionReduitList;
		} catch (WebBaseException_Exception e) {

			logger.error(
					"Erreur lors de l'appel du service de référentiel métier pour récupérer les régimes d'inscription réduits {}",
					e.getMessage());

		}
		return Collections.emptyList();
	}

	public ReferentielMetierServiceInterface getReferentielMetierService() {
		return referentielMetierService;
	}

	public void setReferentielMetierService(ReferentielMetierServiceInterface referentielMetierService) {
		this.referentielMetierService = referentielMetierService;
	}

}
