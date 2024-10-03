package org.esupportail.referentiel.pcscol.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.esupportail.referentiel.beans.ElementPedagogique;
import org.esupportail.referentiel.pcscol.api.CursusDcaApi;
import org.esupportail.referentiel.pcscol.api.EspacesApi;
import org.esupportail.referentiel.pcscol.api.ObjetsMaquetteApi;
import org.esupportail.referentiel.pcscol.chcv6.model.CursusDCA;
import org.esupportail.referentiel.pcscol.chcv6.model.LignePedagogiqueDCA;
import org.esupportail.referentiel.pcscol.invoker.ApiException;
import org.esupportail.referentiel.pcscol.odf.model.DescripteursObjetFormation;
import org.esupportail.referentiel.pcscol.odf.model.Espace;
import org.esupportail.referentiel.pcscol.odf.model.ObjetMaquetteDetail;
import org.esupportail.referentiel.pcscol.odf.model.ObjetMaquetteSummary;
import org.esupportail.referentiel.pcscol.odf.model.Pageable;
import org.esupportail.referentiel.pcscol.odf.model.PagedEspaces;
import org.esupportail.referentiel.pcscol.odf.model.PagedObjetMaquetteSummaries;
import org.esupportail.referentiel.pcscol.odf.model.TypeObjetMaquette;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChcService {
	
	final transient Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CursusDcaApi api;
	@Autowired
	private ObjetsMaquetteApi amApi;
	@Autowired
	private EspacesApi espacesApi;

	@Autowired
	private OffreFormationService offreFormationService;

	// String codeStructure = "ETAB00";
	Pageable pageable = new Pageable();
	String r = null;
	String espace = null;
	List<TypeObjetMaquette> typeObjetMaquette = new ArrayList<TypeObjetMaquette>();
	Boolean racine = null;
	String typeObjetFormation = null;
	List<UUID> ids = null;
	Boolean piaSeulement = null;
	Boolean piaActif = null;
	Boolean valideSeulement = false;
	Boolean mutualise = null;

	public List<ElementPedagogique> lirelisteElementPedagogiqueStageApprenant(String codeApprenant,
			String codeStructure) throws ApiException {
		List<CursusDCA> response = api.lireCusrsuApprenant(codeApprenant);
		
		//System.out.println(response);
		
		List<ElementPedagogique> listeElementPedagogique = new ArrayList<ElementPedagogique>();
		response.forEach(cursus -> {
			List<ElementPedagogique> lep = listeElementPedagogiqueStageFromCursus(cursus, codeStructure);
			listeElementPedagogique.addAll(lep);
		});
		return listeElementPedagogique;

	}

	/**
	 * 
	 * @param cursus
	 * @return
	 */
	public List<ElementPedagogique> listeElementPedagogiqueFromCursus(CursusDCA cursus, String codeStructure) {
		List<ElementPedagogique> listeElementPedagogique = new ArrayList<ElementPedagogique>();
		List<ObjetMaquetteDetail> lomd;
		try {
			lomd = lireCursus(cursus);
			lomd.forEach(objetMaquette -> {
				
				try {
					ElementPedagogique elemePeda = new ElementPedagogique();
					
					DescripteursObjetFormation dem = (DescripteursObjetFormation) objetMaquette
							.getDescripteursObjetMaquette();
					
					elemePeda.setCodElp(objetMaquette.getCode());
					/**
					 * TODO diplome formation
					 */
					//elemePeda.setCodEtp(cursus.getFormation().getCode());
					elemePeda.setCodEtp(cursus.getRacinePedagogique().getCodeObjetMaquette());
					elemePeda.setCodVrsVet(cursus.getPeriode().getCode());
					elemePeda.setLibElp(objetMaquette.getDescripteursObjetMaquette().getLibelle());
					elemePeda.setTemElpTypeStage(String.valueOf(dem.getStage()));
					elemePeda.setNbrCrdElp(dem.getEcts());
					if (dem.getType() != null)
						elemePeda.setLibNatureElp(dem.getType().getCode());
					listeElementPedagogique.add(elemePeda);

				} catch (Exception e) {
					e.printStackTrace();
				}

			});
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listeElementPedagogique;
	}

	/**
	 * 
	 * @param cursus
	 * @return
	 */
	public List<ElementPedagogique> listeElementPedagogiqueStageFromCursus(CursusDCA cursus, String codeStructure) {
		List<ElementPedagogique> listeElementPedagogique = listeElementPedagogiqueFromCursus(cursus, codeStructure);

		try {
			Stream<ElementPedagogique> resultFiltre = listeElementPedagogique.stream()
					.filter(e -> e.getTemElpTypeStage().equalsIgnoreCase("true"));

			listeElementPedagogique = resultFiltre.collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeElementPedagogique;
	}

	/**
	 * 
	 * @param cursus
	 * @return
	 * @throws ApiException
	 */
	public List<ObjetMaquetteDetail> lireCursus(CursusDCA cursus) throws ApiException {

		PagedEspaces espaceDCA = espacesApi.rechercherEspaces(cursus.getCodeStructure(), pageable, cursus.getPeriode().getCode(),
				null, null);
		logger.debug("++++++lireCursus : {} {}", cursus.getCodeStructure());
		UUID espaceUUID = null;

		for (Espace e : espaceDCA.getItems()) {
			if (e.getCode().equals(cursus.getPeriode().getCode())) {
				espaceUUID = e.getId();
				break;
			}
		}

		LignePedagogiqueDCA racinePeda = cursus.getRacinePedagogique();
		List<String> lignes = new ArrayList<String>();
		List<ObjetMaquetteSummary> itemsObjetMaquetteSummaries = new ArrayList<>();
		lignePedagogiqueDCARec(cursus.getCodeStructure(), racinePeda, lignes, itemsObjetMaquetteSummaries, espaceUUID.toString());
		 System.out.println(itemsObjetMaquetteSummaries);
		
		List<UUID> uuids = new ArrayList<UUID>();
		itemsObjetMaquetteSummaries.forEach(e -> {
			uuids.add(e.getId());
		});

		List<ObjetMaquetteDetail> listDOM = offreFormationService.recherchDescripteurMaquettes(cursus.getCodeStructure(), uuids);
		return listDOM;
	}

	/**
	 * 
	 * @param lignesPeda
	 * @param lignes
	 * @param itemsObjetMaquetteSummaries
	 * @param espace
	 */
	public void lignePedagogiqueDCARec(String codeStructure, LignePedagogiqueDCA lignesPeda, List<String> lignes,
			List<ObjetMaquetteSummary> itemsObjetMaquetteSummaries, String espace) {
		lignes.add(lignesPeda.getCodeObjetMaquette());
		if (lignesPeda.getEnfants() != null && !lignesPeda.getEnfants().isEmpty()) {
			lignesPeda.getEnfants().forEach(e -> {
				try {
					PagedObjetMaquetteSummaries om = amApi.rechercherObjetMaquette(codeStructure, pageable,
							e.getCodeObjetMaquette(), espace, typeObjetMaquette, racine, typeObjetFormation, ids,
							piaSeulement, piaActif, valideSeulement, mutualise);
					if (om.getItems() != null)
						itemsObjetMaquetteSummaries.addAll(om.getItems());
				} catch (ApiException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lignePedagogiqueDCARec(codeStructure, e, lignes, itemsObjetMaquetteSummaries, espace);
			});

			// lignePedagogiqueDCARec(LignePedagogiqueDCA lignesPeda,List<String> lignes)
		}

	}

}
