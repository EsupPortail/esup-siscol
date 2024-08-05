package org.esupportail.referentiel.pcscol.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.esupportail.referentiel.pcscol.api.EspacesApi;
import org.esupportail.referentiel.pcscol.api.ObjetsMaquetteApi;
import org.esupportail.referentiel.pcscol.invoker.ApiException;
import org.esupportail.referentiel.pcscol.odf.model.DescripteursFormation;
import org.esupportail.referentiel.pcscol.odf.model.DescripteursObjetFormation;
import org.esupportail.referentiel.pcscol.odf.model.Espace;
import org.esupportail.referentiel.pcscol.odf.model.ObjetMaquetteDetail;
import org.esupportail.referentiel.pcscol.odf.model.ObjetMaquetteSummary;
import org.esupportail.referentiel.pcscol.odf.model.Pageable;
import org.esupportail.referentiel.pcscol.odf.model.PagedEspaces;
import org.esupportail.referentiel.pcscol.odf.model.PagedObjetMaquetteSummaries;
import org.esupportail.referentiel.pcscol.odf.model.TypeEspace;
import org.esupportail.referentiel.pcscol.odf.model.TypeObjetMaquette;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OffreFormationService {

	final transient Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ObjetsMaquetteApi objetsMaquetteApi;
	@Autowired
	EspacesApi espacesApi;

	@Autowired
	PcscolService pcScoleService;

	private Boolean piaSeulement = null;
	private Boolean piaActif = null;
	private Boolean valideSeulement = true;
	private Boolean mutualise = null;

	public List<Espace> checherPeriodeParCode(String codeStructure, String code) throws ApiException {
		Pageable pageable = new Pageable();
		pageable.setPage(0);
		pageable.setTaille(20);
		String r = code;
		TypeEspace type;
		// ( String codeStructure, Pageable pageable, String r, TypeEspace type, Boolean
		// actif)
		PagedEspaces espaces = espacesApi.rechercherEspaces(codeStructure, pageable, code, null, true);
		logger.debug("Espaces :  " + espaces);
		return espaces.getItems();

	}

	// rechercherObjetMaquetteFormation
	/**
	 * 
	 * @param codeStructure
	 * @return
	 * @throws ApiException
	 */
	public Map<String, String> rechercherObjetMaquetteFormation(String codeStructure, String periode)
			throws ApiException {

		UUID idPeriode = checherPeriodeParCode(codeStructure, periode).get(0).getId();

		return rechercherObjetMaquette(codeStructure, TypeObjetMaquette.FORMATION, true, null, idPeriode.toString());
	}

	/**
	 * 
	 * @param codeStructure
	 * @return
	 * @throws ApiException
	 */
	public Map<String, String> rechercherObjetMaquetteObjetFormation(String codeStructure, String periode)
			throws ApiException {
		UUID idPeriode = checherPeriodeParCode(codeStructure, periode).get(0).getId();

		return rechercherObjetMaquette(codeStructure, TypeObjetMaquette.OBJET_FORMATION, false, "ANNEE",
				idPeriode.toString());
	}

	/**
	 * 
	 * @param codeStructure
	 * @param id
	 * @return
	 * @throws ApiException
	 */
	public ObjetMaquetteDetail recherchDescripteurMaquette(String codeStructure, String id) throws ApiException {
		ObjetMaquetteDetail objetMaquetteDetail = objetsMaquetteApi.lireObjetMaquette(codeStructure,
				UUID.fromString(id));
		return objetMaquetteDetail;

	}

	/**
	 * 
	 * @param codeStructure
	 * @param type
	 * @param racine
	 * @param espace
	 * @return
	 * @throws ApiException
	 */
	public Map<String, String> rechercherObjetMaquette(String codeStructure, TypeObjetMaquette type, boolean racine,
			String typeObjetFormation, String espace) throws ApiException {

		Map<String, String> mapVDI = new HashMap<>();
		List<TypeObjetMaquette> tom = new ArrayList<TypeObjetMaquette>();
		tom.add(type);
		List<ObjetMaquetteSummary> allObjetMaquetteDetail = rechercheObjetMaquetteSummary(codeStructure, tom, null,
				typeObjetFormation, null, espace);

		allObjetMaquetteDetail.forEach(f -> {
			try {

				ObjetMaquetteDetail objectMaqette = objetsMaquetteApi.lireObjetMaquette(codeStructure, f.getId());
				UUID idEspace = objectMaqette.getEspace();
				Espace esp = espacesApi.lireEspace(codeStructure, idEspace);

				if (type.equals(TypeObjetMaquette.FORMATION)) {
					DescripteursFormation om = (DescripteursFormation) objectMaqette.getDescripteursObjetMaquette();
					/**
					 * TODO type de fromation 0 ou 1 voir la signification
					 */
					mapVDI.put(f.getCode() + "," + esp.getCode(), f.getLibelle());

				} else if (type.equals(TypeObjetMaquette.OBJET_FORMATION)) {
					DescripteursObjetFormation om = (DescripteursObjetFormation) objectMaqette
							.getDescripteursObjetMaquette();
					if (om.getType() == null) {
						// System.out.println(f.getCode() + " : " + f.getLibelle() + "->" +
						// om.getType());

					} else {

						// System.out.println(f.getCode() + " : " + f.getLibelle() + "->" +
						// om.getType().getCode());

					}

					if (om.getType().getCode().equalsIgnoreCase("ANNEE")
							|| om.getType().getCode().equalsIgnoreCase("SEMESTRE")) {

						mapVDI.put(f.getCode() + "," + esp.getCode(), f.getLibelle());
					}

				}

			} catch (ApiException e) {
				logger.error(e.getMessage() + " : " + e.getCode());
			}
		});

		return mapVDI;

	}

	/**
	 * 
	 * @param codeStructure
	 * @param typeObjetFormation
	 * @param espace
	 * @return
	 * @throws ApiException
	 */
	public List<ObjetMaquetteSummary> rechercheObjetMaquetteSummaryParTypeObjetFormation(String codeStructure,
			String typeObjetFormation, String espace) throws ApiException {
		List<ObjetMaquetteSummary> objetMaquetteSummaries = new ArrayList<>();
		List<TypeObjetMaquette> tom = new ArrayList<TypeObjetMaquette>();
		objetMaquetteSummaries = rechercheObjetMaquetteSummary(codeStructure, tom, null, typeObjetFormation, null,
				espace);

		return objetMaquetteSummaries;
	}

	/**
	 * 
	 * @param codeStructure
	 * @param ids
	 * @return
	 * @throws ApiException
	 */
	public List<ObjetMaquetteSummary> rechercherObjetMaquetteParUUIDS(String codeStructure, List<String> ids,
			String espace) throws ApiException {
		final List<UUID> uids = new ArrayList<>();

		if (ids != null) {
			ids.forEach(id -> {
				uids.add(UUID.fromString(id));
			});
		}
		List<TypeObjetMaquette> typeObjetMaquette = new ArrayList<TypeObjetMaquette>();
		String typeObjetFormation = null;
		Boolean racine = null;

		// ids.add(UUID.fromString("0aeb0a77-9e60-413c-966c-023f722467a7"));

		List<ObjetMaquetteSummary> allObjetMaquetteDetail = rechercheObjetMaquetteSummary(codeStructure,
				typeObjetMaquette, racine, typeObjetFormation, uids, espace);

		return allObjetMaquetteDetail;
	}

	/**
	 * 
	 * @param codeStructure
	 * @param codeFormation
	 * @param codePeriode
	 * @return
	 * @throws ApiException
	 */
	public List<ObjetMaquetteSummary> rechercheObjetMaquetteSummary(String codeStructure, String codeFormation,
			String codePeriode) throws ApiException {
		Pageable pageable = new Pageable();
		pageable.setPage(0);
		pageable.setTaille(20);
		PagedEspaces espaces = espacesApi.rechercherEspaces(codeStructure, pageable, codePeriode, null, true);
		UUID idEsapce = espaces.getItems().get(0).getId();
		PagedObjetMaquetteSummaries response = objetsMaquetteApi.rechercherObjetMaquette(codeStructure, pageable,
				codeFormation, idEsapce.toString(), null, null, null, null, piaSeulement, piaActif, valideSeulement, mutualise);
		return response.getItems();
	}

	/**
	 * 
	 * @param codeStructure
	 * @param typeObjetMaquette
	 * @param racine
	 * @param typeObjetFormation
	 * @param espace
	 * @param newParam           TODO
	 * @return
	 * @throws ApiException
	 */
	public List<ObjetMaquetteSummary> rechercheObjetMaquetteSummary(String codeStructure,
			List<TypeObjetMaquette> typeObjetMaquette, Boolean racine, String typeObjetFormation, List<UUID> ids,
			String espace) throws ApiException {
		List<ObjetMaquetteSummary> objetMaquetteSummaries = new ArrayList<>();
		Pageable pageable = new Pageable();
		pageable.setPage(0);
		pageable.setTaille(20);
		String r = null;
		PagedObjetMaquetteSummaries response = objetsMaquetteApi.rechercherObjetMaquette(codeStructure, pageable, r,
				espace, typeObjetMaquette, racine, typeObjetFormation, ids, piaSeulement, piaActif, valideSeulement,
				mutualise);
		objetMaquetteSummaries.addAll(response.getItems());
		Integer nbr_page = response.getTotalPages();
		if (nbr_page > 1) {
			for (int i = 1; i < nbr_page; i++) {
				pageable.setPage(1);
				PagedObjetMaquetteSummaries responsePartial = objetsMaquetteApi.rechercherObjetMaquette(codeStructure,
						pageable, r, espace, typeObjetMaquette, racine, typeObjetFormation, ids, piaSeulement, piaActif,
						valideSeulement, mutualise);
				objetMaquetteSummaries.addAll(responsePartial.getItems());

			}
		}

		return objetMaquetteSummaries;
	}

	public Boolean getPiaSeulement() {
		return piaSeulement;
	}

	public void setPiaSeulement(Boolean piaSeulement) {
		this.piaSeulement = piaSeulement;
	}

	public Boolean getPiaActif() {
		return piaActif;
	}

	public void setPiaActif(Boolean piaActif) {
		this.piaActif = piaActif;
	}

	public Boolean getValideSeulement() {
		return valideSeulement;
	}

	public void setValideSeulement(Boolean valideSeulement) {
		this.valideSeulement = valideSeulement;
	}

	public Boolean getMutualise() {
		return mutualise;
	}

	public void setMutualise(Boolean mutualise) {
		this.mutualise = mutualise;
	}

}
