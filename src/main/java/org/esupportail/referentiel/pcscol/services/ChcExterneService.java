package org.esupportail.referentiel.pcscol.services;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.esupportail.referentiel.beans.ApprenantDto;
import org.esupportail.referentiel.pcscol.api.CursusApi;
import org.esupportail.referentiel.pcscol.api.ExtractionsApi;
import org.esupportail.referentiel.pcscol.chcExterneV1.model.ApprenantPourext;
import org.esupportail.referentiel.pcscol.chcExterneV1.model.Cursus;
import org.esupportail.referentiel.pcscol.chcExterneV1.model.CursusPourext;
import org.esupportail.referentiel.pcscol.invoker.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "app.mode_pegase")
public class ChcExterneService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExtractionsApi extractionsApi;

	@Autowired
	private CursusApi cursusApi;

	

	/**
	 * 
	 * @param codeStructure
	 * @param codePeriode
	 * @param codeObjetFormation
	 * @return
	 */
	public List<CursusPourext> getCursusPourext(String codeStructure, String codePeriode, String codeObjetFormation) {
		try {
			return extractionsApi.extraireCursusObjetFormationPeriode(codeStructure, codePeriode, codeObjetFormation);
		} catch (ApiException e) {
			logger.error(
					"Erreur lors de l'extraction des cursus pour la structure {}, la période {} et l'objet de formation {} : {}",
					codeStructure, codePeriode, codeObjetFormation, e.getMessage());
			return Collections.emptyList();
		}
	}

	/**
	 * 
	 * @param codeStructure
	 * @param codePeriode
	 * @param codeObjetFormation
	 * @param codeObjetFormationParent
	 * @return
	 */
	public List<CursusPourext> getCursusPourext(String codeStructure, String codePeriode, String codeObjetFormation,
			String codeObjetFormationParent) {
		List<CursusPourext> cursus = getCursusPourext(codeStructure, codePeriode, codeObjetFormation);
		cursus = cursus.stream().filter(c -> Pattern
				.compile(Pattern.quote(codeObjetFormationParent + ">") + ".*" + Pattern.quote(codeObjetFormation))
				.matcher(c.getObjetFormation().getChemin()).find()).toList();
		return cursus;
	}

	/**
	 * 
	 * @param codeStructure
	 * @param codePeriode
	 * @param codeObjetFormation
	 * @param codeObjetFormationParent
	 * @param nom
	 * @param prenom
	 * @return
	 */
	public List<ApprenantPourext> getCursusPourext(String codeStructure, String codePeriode, String codeObjetFormation,
			String codeObjetFormationParent, String codeApprenant, String nom, String prenom) {
		logger.debug("codeStructure {} codePeriode {} codeObjetFormation {}", codeStructure, codePeriode,
				codeObjetFormation);
		List<CursusPourext> cursus = getCursusPourext(codeStructure, codePeriode, codeObjetFormation);
		/*
		 * Verification de objet formation parent si le chemin de l'objet formation
		 * contient le codeObjetFormationParent cas ou le codeObjetFormationParent est
		 * PIA coincide avec le codeObjetFormation==codeObjetFormationParent dans ce cas
		 * on ne filtre pas mais il faut s'assurer que le codeObjetFormationParent est
		 * PIA TODO : A verifier et à implementer
		 */

		if (cursus == null || cursus.isEmpty()) {
			logger.error("getCursusPourext : Aucun cursus trouvé pour les paramètres donnés {} {} {}.", codeStructure,
					codePeriode, codeObjetFormation);
			logger.debug("Aucun cursus trouvé pour les paramètres donnés.");
			return Collections.emptyList();
		}
		cursus = cursus.stream().filter(c -> Pattern
				.compile(Pattern.quote(codeObjetFormationParent + ">") + ".*" + Pattern.quote(codeObjetFormation))
				.matcher(c.getObjetFormation().getChemin()).find()).toList();
		logger.debug("getCursusPourext : {} cursus trouvés pour les paramètres donnés {} {} {}.", cursus.size(),
				codeStructure, codePeriode, codeObjetFormation);
		if (cursus.isEmpty()) {
			return Collections.emptyList();
		}
		/*
		 * si le codeApprenant est null ou vide on verifie que le nom est prenom renvoie
		 * tous les apprenants si les trois parametres sont null ou vide
		 */
		if (codeApprenant != null && !codeApprenant.isEmpty()) {
			return cursus.stream().flatMap(cursus1 -> cursus1.getListeApprenants().stream())
					.filter(apprenant -> apprenant.getCodeApprenant().equals(codeApprenant)).toList();

		}

		if (nom != null && !nom.isEmpty() && prenom != null && !prenom.isEmpty()) {
			return cursus.stream().flatMap(cursus1 -> cursus1.getListeApprenants().stream())
					.filter(apprenant -> apprenant.getNomFamille().equals(nom) && apprenant.getPrenom().equals(prenom))
					.toList();
		}
		return cursus.stream().flatMap(cursus1 -> cursus1.getListeApprenants().stream()).toList();

	}

	/**
	 * 
	 * @param codeStructure
	 * @param codePeriode
	 * @param codeObjetFormation
	 * @return
	 */
	public List<ApprenantPourext> rechercheListApprenantPourCursusPourext(String codeStructure, String codePeriode,
			String codeObjetFormation) {
		List<CursusPourext> cursusPourext = getCursusPourext(codeStructure, codePeriode, codeObjetFormation);

		return cursusPourext.stream().flatMap(cursus -> cursus.getListeApprenants().stream()).toList();
	}

	/**
	 * 
	 * @param codeStructure
	 * @param codePeriode
	 * @param codeObjetFormation
	 * @param codeApprenant
	 * @return
	 */
	public List<ApprenantPourext> getApprenantPourextCodeApprenant(String codeStructure, String codePeriode,
			String codeObjetFormation, String codeApprenant) {
		List<ApprenantPourext> apprenants = rechercheListApprenantPourCursusPourext(codeStructure, codePeriode,
				codeObjetFormation);
		return apprenants.stream().filter(apprenant1 -> apprenant1.getCodeApprenant().equals(codeApprenant)).toList();
	}

	/**
	 * 
	 * @param codeStructure
	 * @param codePeriode
	 * @param codeObjetFormation
	 * @param nom
	 * @param prenom
	 * @return
	 */
	public List<ApprenantPourext> getApprenantPourextNomPrenom(String codeStructure, String codePeriode,
			String codeObjetFormation, String nom, String prenom) {
		List<ApprenantPourext> apprenants = rechercheListApprenantPourCursusPourext(codeStructure, codePeriode,
				codeObjetFormation);
		return apprenants.stream()
				.filter(apprenant1 -> apprenant1.getNomFamille().equals(nom) && apprenant1.getPrenom().equals(prenom))
				.toList();
	}

	/**
	 * 
	 * @param apprenants
	 * @return
	 */
	public List<ApprenantDto> mapperApprenantPourextToApprenantDto(List<ApprenantPourext> apprenants) {
		if (apprenants == null || apprenants.isEmpty()) {
			logger.error("mapperApprenantPourextToApprenantDto : Aucun apprenant trouvé.");
			return Collections.emptyList();
		}
		return apprenants.stream()
				.map(apprenant1 -> new ApprenantDto(apprenant1.getCodeApprenant(), apprenant1.getNomFamille(),
						apprenant1.getPrenom(), formateDate(apprenant1.getDateNaissance()), null, null))
				.toList();
	}

	private String formateDate(Date dateNaissance) {
		SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
		return dateNaissance != null ? dateformat.format(dateNaissance) : null;
	}

	/**
	 * 
	 * @param codeStructure
	 * @param codePeriode
	 * @param codeObjetFormation
	 * @param codeObjetFormationParent
	 * @param codeApprenant
	 * @param nom
	 * @param prenom
	 * @return
	 */

	public List<ApprenantDto> getApprenantDto(String codeStructure, String codePeriode, String codeObjetFormation,
			String codeObjetFormationParent, String codeApprenant, String nom, String prenom) {
		List<ApprenantPourext> apprenants = getCursusPourext(codeStructure, codePeriode, codeObjetFormation,
				codeObjetFormationParent, codeApprenant, nom, prenom);
		if (apprenants == null || apprenants.isEmpty()) {
			return Collections.emptyList();
		}
		logger.debug("getApprenantDto : {} apprenants trouvés pour les paramètres donnés {} {} {} {} {}.",
				apprenants.size(), codeStructure, codePeriode, codeObjetFormation, codeObjetFormationParent,
				codeApprenant);
		return mapperApprenantPourextToApprenantDto(apprenants);

	}

	public List<Cursus> chercherCursusApprenant(String codeAppreanant) {
		logger.debug("CursusExterneService : chercherCursusApprenant : {} ", codeAppreanant);
		try {
			return cursusApi.lireCursusApprenant(codeAppreanant);
		} catch (Exception e) {
			logger.error("Erreur lors de la recherche des cursus pour l'apprenant {} : {}", codeAppreanant,
					e.getMessage());
			return Collections.emptyList();
		}

	}

	/**
	 * 
	 * @param codeAppreanant
	 * @param codePeriode
	 * @return
	 */
	public List<Cursus> chercherCursusApprenant(String codeAppreanant, String codePeriode) {
		logger.debug("CursusExterneService : chercherCursusApprenant : {}  {}", codeAppreanant, codePeriode);
		List<Cursus> cursus = chercherCursusApprenant(codeAppreanant);
		return cursus.stream().filter(c -> c.getPeriode().getCode().equals(codePeriode)).toList();
	}

	/**
	 * 
	 * @param codeAppreanant
	 * @param codePeriode
	 * @return
	 */

	public Map<String, String> chercherEtapeApprenant(String codeAppreanant, String codePeriode) {
		logger.debug(" CursusExterneService : chercherEtapeApprenant : {}  {}", codeAppreanant, codePeriode);
		List<Cursus> cursus = chercherCursusApprenant(codeAppreanant, codePeriode);
		Map<String, String> map = new HashMap<>();
		for (Cursus c : cursus) {
			String codeObjetMaquette = c.getRacinePedagogique().getCodeObjetMaquette();
			String libelle = c.getRacinePedagogique().getLibelleLongObjetMaquette();

			map.put(codeObjetMaquette + ";" + codePeriode, libelle);
		}
		return map;
	}

	public ExtractionsApi getExtractionsApi() {
		return extractionsApi;
	}

	public void setExtractionsApi(ExtractionsApi extractionsApi) {
		this.extractionsApi = extractionsApi;
	}

	public CursusApi getCursusApi() {
		return cursusApi;
	}

	public void setCursusApi(CursusApi cursusApi) {
		this.cursusApi = cursusApi;
	}
}
