package org.esupportail.referentiel.pcscol.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.esupportail.referentiel.beans.ApprenantDto;
import org.esupportail.referentiel.pcscol.api.ApprenantsApi;
import org.esupportail.referentiel.pcscol.api.CheminApi;
import org.esupportail.referentiel.pcscol.api.InscriptionV115Api;
import org.esupportail.referentiel.pcscol.insv115.model.Apprenant;
import org.esupportail.referentiel.pcscol.insv115.model.Chemin;
import org.esupportail.referentiel.pcscol.insv115.model.InscriptionApprenantPeriodeEtChemin;
import org.esupportail.referentiel.pcscol.insv115.model.Pageable;
import org.esupportail.referentiel.pcscol.insv115.model.PagedInscriptionsApprenantsPeriodesEtChemins;
import org.esupportail.referentiel.pcscol.insv115.model.RechercheInscriptionRequest;
import org.esupportail.referentiel.pcscol.insv115.model.StatutInscription;
import org.esupportail.referentiel.pcscol.invoker.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class InscriptionsInterneServices {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private InscriptionV115Api inscriptionV115Api;

	private CheminApi cheminApi;

	private ApprenantsApi apprenantsApi;

	public InscriptionsInterneServices(@Autowired InscriptionV115Api inscriptionV115Api, @Autowired CheminApi cheminApi,
			@Autowired ApprenantsApi apprenantsApi) {
		super();
		this.inscriptionV115Api = inscriptionV115Api;
		this.cheminApi = cheminApi;
		this.apprenantsApi = apprenantsApi;
	}

	/**
	 * Lister les apprenants (tableau de suivi) et conversion en ApprenantDto.
	 * 
	 * @param codeStructure
	 * @param codePeriode
	 * @param codeMaquettePia
	 * @param codeMaquetteOdf
	 * @param codeEtu
	 * @param nomNaissance
	 * @param prenom
	 * @return
	 */
	public List<ApprenantDto> listApprenanDtotPeriodeEtChemin(String codeStructure, String codePeriode,
			String codeMaquettePia, String codeMaquetteOdf, String codeEtu, String nomNaissance, String prenom) {
		List<Apprenant> apprenants = listApprenantPeriodeEtChemin(codeStructure, codePeriode, codeMaquettePia,
				codeMaquetteOdf, codeEtu, nomNaissance, prenom);
		return convertirApprenants(apprenants);
	}

	/**
	 * convertir une liste d'Apprenant en liste d'ApprenantDto.
	 * 
	 * @param apprenants
	 * @return
	 */
	List<ApprenantDto> convertirApprenants(List<Apprenant> apprenants) {
		if (apprenants != null && !apprenants.isEmpty()) {
			return apprenants.stream().map(a -> {
				ApprenantDto apprenantDto = new ApprenantDto();
				apprenantDto.setCodEtu(a.getIne());
				apprenantDto.setNom(a.getNomNaissance());
				apprenantDto.setPrenom(a.getPrenom());
				apprenantDto.setDateNaissance(a.getDateNaissance());
				apprenantDto.setMail(a.getCourriel());
				return apprenantDto;
			}).toList();
		}

		return Collections.emptyList();
	}

	/**
	 * Lister les apprenants (tableau de suivi).
	 * 
	 * @param codeStructure
	 * @param codePeriode
	 * @param codeMaquettePia
	 * @param codeMaquetteOdf
	 * @param codeEtu
	 * @param nomNaissance
	 * @param prenom
	 * @return
	 */
	public List<Apprenant> listApprenantPeriodeEtChemin(String codeStructure, String codePeriode,
			String codeMaquettePia, String codeMaquetteOdf, String codeEtu, String nomNaissance, String prenom) {
		List<InscriptionApprenantPeriodeEtChemin> listerInscriptions = listerInscriptions(codeStructure, codePeriode,
				codeMaquettePia, codeMaquetteOdf, codeEtu, nomNaissance, prenom);
		if (listerInscriptions != null && !listerInscriptions.isEmpty()) {
			return listerInscriptions.stream().map(InscriptionApprenantPeriodeEtChemin::getApprenant).toList();
		}
		return Collections.emptyList();
	}

	/**
	 * Lister les inscriptions (tableau de suivi).
	 * 
	 * @param codeStructure
	 * @param codePeriode
	 * @param codeMaquettePia
	 * @param codeMaquetteOdf
	 * @param codeEtu
	 * @param nomNaissance
	 * @param prenom
	 * @return
	 **/
	public List<InscriptionApprenantPeriodeEtChemin> listerInscriptions(String codeStructure, String codePeriode,
			String codeMaquettePia, String codeMaquetteOdf, String codeEtu, String nomNaissance, String prenom) {

		logger.debug("Lister les inscriptions pour la structure {} et la période {}", codeStructure, codePeriode);
		RechercheInscriptionRequest rechercheInscription = new RechercheInscriptionRequest();
		rechercheInscription.setCodePeriode(codePeriode);

		rechercheInscription.addStatutsInscriptionItem(StatutInscription.VALIDEE);
		rechercheInscription.addStatutsInscriptionItem(StatutInscription.TERMINEE);

		// récupération des chemins d'inscription pour la structure et la période
		List<Chemin> filtrerCheminsParMaquettePia = filtrerCheminsParMaquettePia(
				cheminInscriptionParPeriode(codeStructure, codePeriode), codeMaquettePia, codeMaquetteOdf);
		List<UUID> ids = filtrerCheminsParMaquettePia.stream().map(Chemin::getIdOdf).toList();

		// Si pas de chemin, on met un UUID au hasard pour ne rien récupérer
		if (ids.isEmpty()) {
			logger.info("Aucun chemin d'inscription pour la maquette PIA {} et ODF {}", codeMaquettePia,
					codeMaquetteOdf);
			return Collections.emptyList();
		}

		if (codeEtu != null && !codeEtu.isBlank()) {
			rechercheInscription.setIne(codeEtu);
		}
		if (nomNaissance != null && !nomNaissance.isBlank()) {
			rechercheInscription.setNomNaissance(nomNaissance);
		}
		if (prenom != null && !prenom.isBlank()) {
			rechercheInscription.setPrenom(prenom);
		}
		rechercheInscription.setIdsOdf(ids);
		Pageable pageable = new Pageable();
		pageable.setTaille(10);
		pageable.setPage(0);
		List<InscriptionApprenantPeriodeEtChemin> inscriptionApprenantPeriodeEtChemin = new ArrayList<>();

		try {
			logger.debug("Recherche inscriptions...{}", rechercheInscription);
			PagedInscriptionsApprenantsPeriodesEtChemins response;
			response = inscriptionV115Api.listerInscriptions(codeStructure, rechercheInscription, pageable);
			logger.debug("Nombre total d'inscriptions : {}", response.getTotalElements());
			inscriptionApprenantPeriodeEtChemin.addAll(response.getItems());
			if (response.getTotalPages() > 1) {
				for (int i = 1; i < response.getTotalPages(); i++) {
					logger.debug("Lecture de la page {} sur {}", i + 1, response.getTotalPages());
					pageable.setPage(i);
					response = inscriptionV115Api.listerInscriptions(codeStructure, rechercheInscription, pageable);
					inscriptionApprenantPeriodeEtChemin.addAll(response.getItems());
				}
			}

			return inscriptionApprenantPeriodeEtChemin;
		} catch (ApiException e) {
			logger.error("Erreur lors de l'appel à l'API Inscription : {} {}", e.getResponseBody(), e);
		}
		return Collections.emptyList();
	}

	/**
	 * Récupération des chemins d'inscription pour une structure et une période
	 * données.
	 * 
	 * @param codeStructure
	 * @param codePeriode
	 * @return
	 */
	public List<Chemin> cheminInscriptionParPeriode(String codeStructure, String codePeriode) {
		try {
			return cheminApi.recupererChemins(codeStructure, codePeriode, false);

		} catch (ApiException e) {
			logger.error("Erreur lors de l'appel à l'API Chemin : {} {}", e.getResponseBody(), e);
			return Collections.emptyList();
		}
	}

	/**
	 * Filtrage des chemins par maquette PIA et ODF.
	 * 
	 * @param chemins
	 * @param codeMaquettePiad
	 * @param codeMaquetteOdf
	 * @return
	 */
	public List<Chemin> filtrerCheminsParMaquettePia(List<Chemin> chemins, String codeMaquettePiad,
			String codeMaquetteOdf) {
		List<Chemin> cheminsTemp = chemins.stream().filter(c -> c.getCodeChemin().contains(codeMaquettePiad)).toList();
		if (!cheminsTemp.isEmpty() && codeMaquetteOdf != null && !codeMaquetteOdf.isBlank()) {
			// tentative de filtrage par maquette ODF
			cheminsTemp = chemins.stream().filter(c -> c.getCodeChemin().contains(codeMaquetteOdf)).toList();
		}
		return cheminsTemp;
	}

	public String chercherCodeEtudParIne(String codeStructure, String ine) {
		try {
			return apprenantsApi.lireApprenantParIne(codeStructure, ine).getCode();
		} catch (ApiException e) {
			logger.error("Erreur lors de l'appel à l'API Apprenant : {} {}", e.getResponseBody(), e);
			return null;
		}
	}
	/**
	 * Chercher l'INE d'un étudiant à partir de son code étudiant.
	 * @param codeEtud
	 * @return
	 */
	public String chercherIneParCodeEtud(String codeStructure,String codeEtud) {
		try {
			return apprenantsApi.lireApprenant(codeStructure, codeEtud).getCode();
		} catch (ApiException e) {
			logger.error("Erreur lors de l'appel à l'API Apprenant : {} {}", e.getResponseBody(), e);
			return null;
		}
	}
}