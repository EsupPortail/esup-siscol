/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.esupportail.referentiel.ws.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;

import org.esupportail.referentiel.beans.EtudiantDTO2Ext;
import org.esupportail.referentiel.beans.RegimeInscription;
import org.esupportail.referentiel.mappers.ApogeeEtudiantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import gouv.education.apogee.commun.client.ws.AdministratifMetier.AdministratifMetierServiceInterface;
import gouv.education.apogee.commun.client.ws.AdministratifMetier.InsAdmAnuDTO2;
import gouv.education.apogee.commun.client.ws.AdministratifMetier.InsAdmEtpDTO3;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.CoordonneesDTO2;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.EtudiantCritereDTO;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.EtudiantCritereListeDTO;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.EtudiantDTO2;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.EtudiantMetierServiceInterface;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.IdentifiantsEtudiantDTO2;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.InfoAdmEtuDTO4;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.TableauDiplomes;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.TableauEtapes;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.TypeHebergementDTO;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.WebBaseException_Exception;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.DiplomeDTO4;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.OffreFormationMetierServiceInterface;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.SECritereDTO2;
import gouv.education.apogee.commun.client.ws.ReferentielMetier.ComposanteDTO3;
import gouv.education.apogee.commun.client.ws.ReferentielMetier.ReferentielMetierServiceInterface;
import gouv.education.apogee.commun.client.ws.ReferentielMetier.RegimeInscDTO;

/**
 * @author abdelhamid
 *
 */
@ConditionalOnProperty(name = "app.mode_apogee")
@Service
public class EtudiantMetierClient {

	final private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EtudiantMetierServiceInterface etudiantMetierService;

	/**
	 * 
	 */
	@Autowired
	private OffreFormationMetierServiceInterface offreFormationMetierService;

	/**
	 * 
	 */
	@Autowired
	private AdministratifMetierServiceInterface serviceAdministratif;

	/**
	 * 
	 */
	@Autowired
	private ReferentielMetierServiceInterface referentielMetierService;

	public void test(String cod, String annee) {
		try {
			List<InsAdmEtpDTO3> IAEtapesV3 = serviceAdministratif.recupererIAEtapesV3(cod, annee, "E", "E");
			IAEtapesV3.get(0).getCursusAmg();
		} catch (gouv.education.apogee.commun.client.ws.AdministratifMetier.WebBaseException_Exception e) {
			logger.error("Erreur test recupererIAEtapesV3 cod={} annee={}", cod, annee, e);
		}
	}

	/**
	 * 
	 * @param code
	 * @param temoin
	 * @return
	 */
	public RegimeInscDTO recuperereRegimeInscription(String code, String temoin) {
		try {
			List<RegimeInscDTO> regimes = referentielMetierService.recupererRegimeInscription(code, temoin);
			if (regimes != null && !regimes.isEmpty()) {
				return regimes.get(0);
			}

		} catch (gouv.education.apogee.commun.client.ws.ReferentielMetier.WebBaseException_Exception e) {
			logger.error("Erreur recuperereRegimeInscription code={} temoin={}", code, temoin, e);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.upond.apogeews.dao.referentiel.services.interfaces.
	 * EtudiantMetierServiceInterfaceCustom#recupererIdentifiantsEtudiant(java.
	 * lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */

	public IdentifiantsEtudiantDTO2 recupererIdentifiantsEtudiant(String codEtu, String codInd, String numINE,
			String cleINE, String numBoursier, String codOPI, String nom, String prenom, String dateNaiss,
			String temoinRecupAnnu) {
		try {
			return etudiantMetierService.recupererIdentifiantsEtudiantV2(codEtu, codInd,
					numINE, numBoursier, codOPI, nom, prenom, dateNaiss, temoinRecupAnnu);
		} catch (WebBaseException_Exception e) {
			logger.error("recupererIdentifiantsEtudiantV2 :  {} -> {}", codEtu , e.getMessage());
			return null;
		}

	}

	/**
	 * 
	 * @param codEtu
	 * @param temoinRecupAnnu
	 * @return IdentifiantsEtudiantDTO2
	 */
	public IdentifiantsEtudiantDTO2 recupererIdentifiantsEtudiantByCodEtu(String codEtu, String temoinRecupAnnu) {
		return recupererIdentifiantsEtudiant(codEtu, null, null, null, null, null, null, null,
				null, temoinRecupAnnu);
	}

	/**
	 * 
	 * @param codInd
	 * @param temoinRecupAnnu
	 * @return IdentifiantsEtudiantDTO2
	 */
	public IdentifiantsEtudiantDTO2 recupererIdentifiantsEtudiantByCodInd(String codInd, String temoinRecupAnnu) {
		return recupererIdentifiantsEtudiant(null, codInd, null, null, null, null, null, null,
				null, temoinRecupAnnu);
	}

	/**
	 * 
	 * @param codEtu
	 * @param anneeCourante
	 * @return CoordonneesDTO2
	 */
	public CoordonneesDTO2 recupererCoordonneesDTO2(String codEtu, String anneeCourante, String recupAnnu) {
		// Recuperation des coordonnees de l'etudiant
		CoordonneesDTO2 coordonnees = new CoordonneesDTO2();
		try {

			coordonnees = etudiantMetierService.recupererAdressesEtudiantV2(codEtu, null, recupAnnu);
		} catch (Exception wb) {
			logger.warn("Echec recupererAdressesEtudiantV2 sans annee codEtu={}", codEtu, wb);
			try {
				coordonnees = etudiantMetierService.recupererAdressesEtudiantV2(codEtu, anneeCourante, recupAnnu);
			} catch (WebBaseException_Exception e) {
				logger.error("Echec recupererAdressesEtudiantV2 avec annee codEtu={} annee={}", codEtu, anneeCourante, e);
			}
		}
		return coordonnees;
	}

	/**
	 * 
	 * @param cod
	 * @param annee
	 * @return List<InsAdmEtpDTO3>
	 */
	public List<InsAdmEtpDTO3> recupererIAEtapesV3(String cod, String annee) {
		try {
			List<InsAdmEtpDTO3> tabInsAdmEtp = serviceAdministratif.recupererIAEtapesV3(cod, annee, "E", "E");
			return tabInsAdmEtp == null ? Collections.emptyList() : tabInsAdmEtp;
		} catch (gouv.education.apogee.commun.client.ws.AdministratifMetier.WebBaseException_Exception e) {
			logger.error("recupererIAEtapesV3 code={} annee={}", cod, annee, e);
			return Collections.emptyList();
		}
	}

	/**
	 * 
	 * @param codeEtud
	 * @return List<String>
	 */
	public List<String> recupererAnneesIa(String codeEtud) {
		try {
			List<String> annees = serviceAdministratif.recupererAnneesIa(codeEtud, "E");
			return annees == null ? Collections.emptyList() : annees;
		} catch (gouv.education.apogee.commun.client.ws.AdministratifMetier.WebBaseException_Exception e) {
			logger.error("Erreur recupererAnneesIa codeEtud={}", codeEtud, e);
			return Collections.emptyList();
		}
	}

	/**
	 * 
	 * @param codeEtud
	 * @param annee
	 * @return List<InsAdmAnuDTO2>
	 */
	public List<InsAdmAnuDTO2> recupererIAAnnuellesV2(String codeEtud, String annee) {
		try {
			List<InsAdmAnuDTO2> insAdmAnuDTO2 = serviceAdministratif.recupererIAAnnuellesV2(codeEtud, annee, "E");
			return insAdmAnuDTO2 == null ? Collections.emptyList() : insAdmAnuDTO2;
		} catch (gouv.education.apogee.commun.client.ws.AdministratifMetier.WebBaseException_Exception e) {
			logger.error("Erreur recupererIAAnnuellesV2 codeEtud={} annee={}", codeEtud, annee, e);
			return Collections.emptyList();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.upond.apogeews.dao.referentiel.services.interfaces.
	 * EtudiantMetierServiceInterfaceCustom#recupererTypeHebergement(java.lang.
	 * String, java.lang.String, java.lang.String)
	 */
	public List<TypeHebergementDTO> recupererTypeHebergement(String code, String temoinService, String temoinAD) {
		try {
			List<TypeHebergementDTO> typeHebergementDTO = etudiantMetierService.recupererTypeHebergement(code,
					temoinService, temoinAD);
			return typeHebergementDTO == null ? Collections.emptyList() : typeHebergementDTO;
		} catch (WebBaseException_Exception e) {
			logger.error("Erreur recupererTypeHebergement code={} temoinService={} temoinAD={}", code, temoinService,
					temoinAD, e);
			return Collections.emptyList();
		}
	}

	/**
	 * 
	 * @param codEtu
	 * @return
	 */
	public InfoAdmEtuDTO4 recupererInfosAdmEtuV4(String codEtu) {
		try {
			return etudiantMetierService.recupererInfosAdmEtuV4(codEtu);
		} catch (WebBaseException_Exception e) {
			logger.error("ECHEC DE LA RECUERATION DE L'ETUDIANT : " + codEtu + " ->" + e.getMessage());
			// e.printStackTrace();
			throw new RuntimeException("ECHEC DE LA RECUERATION DE L'ETUDIANT : " + codEtu + " ->" + e.getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.upond.apogeews.dao.referentiel.services.interfaces.
	 * EtudiantMetierServiceInterfaceCustom#recupererListeEtudiants(gouv.
	 * education.apogee.commun.transverse.dto.etudiant.EtudiantCritereDTO)
	 */

	public List<EtudiantDTO2> recupererListeEtudiants(EtudiantCritereDTO parametres) {
		try {
			return etudiantMetierService.recupererListeEtudiants(parametres);
		} catch (WebBaseException_Exception e) {
			logger.error(e.getMessage());
		}
		return Collections.emptyList();
	}

	/**
	 * 
	 * @param codeComposante
	 * @param annee
	 * @param codeEtp
	 * @param versionEtp
	 * @param codeDipl
	 * @param verDipl
	 * @param nom
	 * @param prenom
	 * @param codEtu
	 * @return
	 */
	public List<EtudiantDTO2Ext> recupererListeEtuParEtpEtDiplome(String codeComposante, String annee, String codeEtp,
			String versionEtp, String codeDipl, String verDipl, String codEtu, String nom, String prenom) {
		List<EtudiantDTO2> etudiants = recupererListeEtuParEtpEtDiplome(codeComposante, annee, codeEtp, versionEtp,
				codeDipl, verDipl);
		logger.debug("recupererListeEtuParEtpEtDiplome : {} {} {} {}  {} {}  ", codeComposante, annee, codeEtp,
				versionEtp, codeDipl, verDipl);

		if (etudiants == null || etudiants.isEmpty()) {
			return new ArrayList<>();
		}

		Stream<EtudiantDTO2> stream = etudiants.stream();

		if (StringUtils.hasText(codEtu)) {
			logger.debug("recupererListeEtuParEtpEtDiplome filtre par codEtu : {}", codEtu);
			stream = stream.filter(etudiant -> codEtu.equals(etudiant.getCodEtu()));
		}
		if (StringUtils.hasText(nom)) {
			logger.debug("recupererListeEtuParEtpEtDiplome filtre par Nom : {}", nom);
			String nomUpper = nom.toUpperCase(Locale.ROOT);
			stream = stream.filter(etudiant -> etudiant.getNom() != null
					&& etudiant.getNom().toUpperCase(Locale.ROOT).contains(nomUpper));
		}
		if (StringUtils.hasText(prenom)) {
			logger.debug("recupererListeEtuParEtpEtDiplome filtre par Prenom : {}", prenom);
			String prenomUpper = prenom.toUpperCase(Locale.ROOT);
			stream = stream.filter(etudiant -> etudiant.getPrenom() != null
					&& etudiant.getPrenom().toUpperCase(Locale.ROOT).contains(prenomUpper));
		}

		return ApogeeEtudiantMapper.Instance.etudiantDTO2ToEtudiantDTO2Ext(stream.toList());
	}

	/**
	 * 
	 * @param codeComposante
	 * @param annee
	 * @param codeEtp
	 * @param versionEtp
	 * @param codeDipl
	 * @param verDipl
	 * @return
	 */
	public List<EtudiantDTO2> recupererListeEtuParEtpEtDiplome(String codeComposante, String annee, String codeEtp,
			String versionEtp, String codeDipl, String verDipl) {

		TableauEtapes etps = new TableauEtapes();
		EtudiantCritereListeDTO dto = new EtudiantCritereListeDTO();
		dto.setCode(codeEtp);
		dto.getListVersion().add(versionEtp);
		etps.getItem().add(dto);

		TableauDiplomes diplomes = new TableauDiplomes();
		EtudiantCritereListeDTO dtoDip = new EtudiantCritereListeDTO();
		dtoDip.setCode(codeDipl);
		dtoDip.getListVersion().add(verDipl);
		diplomes.getItem().add(dtoDip);

		EtudiantCritereDTO criteres = new EtudiantCritereDTO();
		criteres.setAnnee(annee);
		criteres.setListDiplomes(diplomes);
		criteres.setListEtapes(etps);

		/**
		 * ON vérifie si la composante existe avant de l'ajouter dans les critères sinon
		 * on ignore
		 */
		if (codeComposante != null) {
			ComposanteDTO3 composante = recupererComposanteV2(codeComposante);
			if (composante != null) {
				criteres.getListComposante().add(codeComposante);
			}
		}

		criteres.setCodeCollectionELP(null);
		criteres.setCodeCollectionVET(null);
		criteres.setCodeEchInter(null);
		criteres.setCodeGroupeELP(null);
		criteres.setCodeGroupeVET(null);
		criteres.setCodeRegimeInscrETP(null);
		criteres.setEtatThese(null);
		criteres.setMaintienInscrAutreEtab(null);
		criteres.setObjetResultat(null);
		criteres.setSensEchInter(null);
		criteres.setTemPartInternational(null);
		criteres.setTemValAcquisXP(null);
		criteres.setTypeResultat(null);

		logger.info("annee : {}, codeEtape : {}, versionEtape : {} ,codeDiplome : {}, versionDiplome : {}", annee,
				codeEtp, versionEtp, codeDipl, verDipl);
		return recupererListeEtudiants(criteres);
	}

	/**
	 * 
	 * @param codeEtp
	 * @param versionEtp
	 * @return List<DiplomeDTO3>
	 */

	public List<DiplomeDTO4> recupererListDiplomeDTO4(String codeEtp, String versionEtp) {

		// recherche du cursus LMD, codFinalite
		SECritereDTO2 seCritereDTO = new SECritereDTO2();
		seCritereDTO.setCodDip("aucun");
		seCritereDTO.setCodVrsVdi("aucun");
		seCritereDTO.setCodEtp(codeEtp);
		seCritereDTO.setCodVrsVet(versionEtp);
		seCritereDTO.setCodElp("tous");

		try {
			List<DiplomeDTO4> diplomeDTO = offreFormationMetierService.recupererSEV4(seCritereDTO);
			return diplomeDTO == null ? Collections.emptyList() : diplomeDTO;
		} catch (gouv.education.apogee.commun.client.ws.OffreFormationMetier.WebBaseException_Exception e) {
			logger.error("Erreur recupererListDiplomeDTO4 codeEtp={} versionEtp={}", codeEtp, versionEtp, e);
			return Collections.emptyList();
		}

	}

	/**
	 * Composante
	 * 
	 * @param codComposante
	 * @return ComposanteDTO3
	 */
	public ComposanteDTO3 recupererComposanteV2(String codComposante) {
		List<ComposanteDTO3> composante;
		try {
			composante = referentielMetierService.recupererComposanteV2(codComposante, null);
			if (composante != null && !composante.isEmpty()) {
				return composante.get(0);
			}
		} catch (gouv.education.apogee.commun.client.ws.ReferentielMetier.WebBaseException_Exception e) {
			logger.error("Erreur de récupérer la composante : {}   -> {}", codComposante, e.getMessage());
		}
		return null;

	}

	/**
	 * 
	 * @param codeEtud
	 * @param annee
	 * @return List<RegimeInscription>
	 */
	public List<RegimeInscription> regimeInscriptionEtudiant(String codeEtud, String annee) {
		List<InsAdmAnuDTO2> iaAnnuelles = recupererIAAnnuellesV2(codeEtud, annee);
		if (iaAnnuelles == null || iaAnnuelles.isEmpty()) {
			return Collections.emptyList();
		}

		List<RegimeInscription> regimeInscriptions = new ArrayList<>();
		Map<String, RegimeInscDTO> regimeCache = new HashMap<>();

		logger.debug("iaAnnuelles : {}", iaAnnuelles.size());

		for (InsAdmAnuDTO2 insAdmAnu : iaAnnuelles) {
			if (insAdmAnu == null || insAdmAnu.getRegimeIns() == null) {
				continue;
			}

			String codRgi = insAdmAnu.getRegimeIns().getCodRgi();

			if (logger.isDebugEnabled()) {
				logger.debug("#anneesInscriptionFC# - [id : {}, annee : {}, RegimeIns : {} : {}]",
						codeEtud, annee, codRgi, insAdmAnu.getRegimeIns().getLibRgi());
			}

			RegimeInscDTO regimeDetail = regimeCache.get(codRgi);
			if (!regimeCache.containsKey(codRgi)) {
				regimeDetail = recuperereRegimeInscription(codRgi, "O");
				regimeCache.put(codRgi, regimeDetail);
			}

			RegimeInscription regime = new RegimeInscription(codRgi, insAdmAnu.getRegimeIns().getLibRgi(), annee);

			if (regimeDetail != null) {
				regime.setLicRegIns(regimeDetail.getLicRegIns());
				regime.setCodRegIns(regimeDetail.getCodRegIns());
			}

			regimeInscriptions.add(regime);
		}

		return regimeInscriptions;
	}

	public EtudiantMetierServiceInterface getEtudiantMetierService() {
		return etudiantMetierService;
	}

	public void setEtudiantMetierService(EtudiantMetierServiceInterface etudiantMetierService) {
		this.etudiantMetierService = etudiantMetierService;
	}

	public OffreFormationMetierServiceInterface getOffreFormationMetierService() {
		return offreFormationMetierService;
	}

	public void setOffreFormationMetierService(OffreFormationMetierServiceInterface offreFormationMetierService) {
		this.offreFormationMetierService = offreFormationMetierService;
	}

	public AdministratifMetierServiceInterface getServiceAdministratif() {
		return serviceAdministratif;
	}

	public void setServiceAdministratif(AdministratifMetierServiceInterface serviceAdministratif) {
		this.serviceAdministratif = serviceAdministratif;
	}

	public ReferentielMetierServiceInterface getReferentielMetierService() {
		return referentielMetierService;
	}

	public void setReferentielMetierService(ReferentielMetierServiceInterface referentielMetierService) {
		this.referentielMetierService = referentielMetierService;
	}

}
