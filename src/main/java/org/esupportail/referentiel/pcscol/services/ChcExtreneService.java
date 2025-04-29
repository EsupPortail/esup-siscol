package org.esupportail.referentiel.pcscol.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.esupportail.referentiel.beans.ApprenantDto;
import org.esupportail.referentiel.pcscol.api.ExtractionsApi;
import org.esupportail.referentiel.pcscol.chcExterneV1.model.ApprenantPourext;
import org.esupportail.referentiel.pcscol.chcExterneV1.model.CursusPourext;
import org.esupportail.referentiel.pcscol.invoker.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "app.mode_pegase")
public class ChcExtreneService {

	// TODO : A implementer
	private final transient Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExtractionsApi extractionsApi;

	public ChcExtreneService() {

	}

	/**
	 * 
	 * @param codeStructure
	 * @param codePeriode
	 * @param codeObjetFormation
	 * @return
	 */
	public List<CursusPourext> getCursusPourext(String codeStructure, String codePeriode, String codeObjetFormation) {
		try {
			List<CursusPourext> cursus = extractionsApi.extraireCursusObjetFormationPeriode(codeStructure, codePeriode,
					codeObjetFormation);
			return cursus;
		} catch (ApiException e) {
			logger.error(e.getMessage() + " :" + e.getMessage());
			return null;
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
				.matcher(c.getObjetFormation().getChemin()).find()).collect(Collectors.toList());
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
		cursus = cursus.stream().filter(c -> Pattern
				.compile(Pattern.quote(codeObjetFormationParent + ">") + ".*" + Pattern.quote(codeObjetFormation))
				.matcher(c.getObjetFormation().getChemin()).find()).collect(Collectors.toList());
		logger.debug("Cursus : " + cursus);
		if (cursus == null || cursus.isEmpty()) {
			return null;
		}
		if (codeApprenant != null && !codeApprenant.isEmpty()) {
			List<ApprenantPourext> apprenants = cursus.stream()
					.flatMap(cursus1 -> cursus1.getListeApprenants().stream())
					.filter(apprenant -> apprenant.getCodeApprenant().equals(codeApprenant))
					.collect(Collectors.toList());
			return apprenants;

		}

		if (nom != null && !nom.isEmpty() && prenom != null && !prenom.isEmpty()) {
			List<ApprenantPourext> apprenants = cursus.stream()
					.flatMap(cursus1 -> cursus1.getListeApprenants().stream())
					.filter(apprenant -> apprenant.getNomFamille().equals(nom) && apprenant.getPrenom().equals(prenom))
					.collect(Collectors.toList());
			return apprenants;
		}
		return cursus.stream().flatMap(cursus1 -> cursus1.getListeApprenants().stream()).collect(Collectors.toList());

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

		List<ApprenantPourext> apprenants = cursusPourext.stream()
				.flatMap(cursus -> cursus.getListeApprenants().stream()).collect(Collectors.toList());

		return apprenants;
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
		List<ApprenantPourext> apprenant = apprenants.stream()
				.filter(apprenant1 -> apprenant1.getCodeApprenant().equals(codeApprenant)).collect(Collectors.toList());
		return apprenant;
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
		List<ApprenantPourext> apprenant = apprenants.stream()
				.filter(apprenant1 -> apprenant1.getNomFamille().equals(nom) && apprenant1.getPrenom().equals(prenom))
				.collect(Collectors.toList());
		return apprenant;
	}

	/**
	 * 
	 * @param apprenants
	 * @return
	 */
	public List<ApprenantDto> mapperApprenantPourextToApprenantDto(List<ApprenantPourext> apprenants) {
//		if (apprenants == null || apprenants.isEmpty()) {
//			return null;
//		}

		List<ApprenantDto> apprenant = apprenants.stream()
				.map(apprenant1 -> new ApprenantDto(apprenant1.getCodeApprenant(), apprenant1.getNomFamille(),
						apprenant1.getPrenom(), formateDate(apprenant1.getDateNaissance()), null, null))
				.collect(Collectors.toList());
		return apprenant;
	}

	private String formateDate(Date dateNaissance) {
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
		return dateNaissance != null ? DATE_FORMAT.format(dateNaissance) : null;
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
			return null;
		}
		logger.debug("getApprenantDto : " + apprenants.size());
		return mapperApprenantPourextToApprenantDto(apprenants);

	}

	public ExtractionsApi getExtractionsApi() {
		return extractionsApi;
	}

	public void setExtractionsApi(ExtractionsApi extractionsApi) {
		this.extractionsApi = extractionsApi;
	}
}
