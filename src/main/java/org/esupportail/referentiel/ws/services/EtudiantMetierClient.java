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
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.esupportail.referentiel.beans.EtudiantDTO2Ext;
import org.esupportail.referentiel.beans.RegimeInscription;
import org.esupportail.referentiel.mappers.ApogeeEtudiantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import gouv.education.apogee.commun.client.ws.EtudiantMetier.InfoAdmEtuDTO3;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.InfoAdmEtuDTO4;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.TableauDiplomes;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.TableauEtapes;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.TypeHebergementDTO;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.WebBaseException_Exception;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.DiplomeDTO3;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.OffreFormationMetierServiceInterface;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.SECritereDTO2;
import gouv.education.apogee.commun.client.ws.ReferentielMetier.ComposanteDTO3;
import gouv.education.apogee.commun.client.ws.ReferentielMetier.ReferentielMetierServiceInterface;
import gouv.education.apogee.commun.client.ws.ReferentielMetier.RegimeInscDTO;

/**
 * @author abdelhamid
 *
 */
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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Erreur de récupérer le détail regimeInscription" + e.getMessage());
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
			IdentifiantsEtudiantDTO2 etudiant = etudiantMetierService.recupererIdentifiantsEtudiantV2(codEtu, codInd,
					numINE, numBoursier, codOPI, nom, prenom, dateNaiss, temoinRecupAnnu);
			return etudiant;
		} catch (WebBaseException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @param codEtu
	 * @param temoinRecupAnnu
	 * @return IdentifiantsEtudiantDTO2
	 */
	public IdentifiantsEtudiantDTO2 recupererIdentifiantsEtudiantByCodEtu(String codEtu, String temoinRecupAnnu) {
		IdentifiantsEtudiantDTO2 e = recupererIdentifiantsEtudiant(codEtu, null, null, null, null, null, null, null,
				null, temoinRecupAnnu);
		return e;
	}

	/**
	 * 
	 * @param codInd
	 * @param temoinRecupAnnu
	 * @return IdentifiantsEtudiantDTO2
	 */
	public IdentifiantsEtudiantDTO2 recupererIdentifiantsEtudiantByCodInd(String codInd, String temoinRecupAnnu) {
		IdentifiantsEtudiantDTO2 e = recupererIdentifiantsEtudiant(null, codInd, null, null, null, null, null, null,
				null, temoinRecupAnnu);
		return e;
	}

	/**
	 * 
	 * @param CodEtu
	 * @param anneeCourante
	 * @return CoordonneesDTO2
	 */
	public CoordonneesDTO2 recupererCoordonneesDTO2(String CodEtu, String anneeCourante, String recupAnnu) {
		// Recuperation des coordonnees de l'etudiant
		CoordonneesDTO2 coordonnees = new CoordonneesDTO2();
		try {

			coordonnees = etudiantMetierService.recupererAdressesEtudiantV2(CodEtu, null, recupAnnu);
		} catch (Exception wb) {
			try {
				coordonnees = etudiantMetierService.recupererAdressesEtudiantV2(CodEtu, anneeCourante, recupAnnu);
			} catch (WebBaseException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		List<InsAdmEtpDTO3> tabInsAdmEtp = null;
		try {
			tabInsAdmEtp = serviceAdministratif.recupererIAEtapesV3(cod, annee, "E", "E");
		} catch (gouv.education.apogee.commun.client.ws.AdministratifMetier.WebBaseException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tabInsAdmEtp;
	}

	/**
	 * 
	 * @param codeEtud
	 * @return List<String>
	 */
	public List<String> recupererAnneesIa(String codeEtud) {
		List<String> annees = null;

		try {
			annees = serviceAdministratif.recupererAnneesIa(codeEtud, "E");
		} catch (gouv.education.apogee.commun.client.ws.AdministratifMetier.WebBaseException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (annees);
	}

	/**
	 * 
	 * @param codeEtud
	 * @param annee
	 * @return List<InsAdmAnuDTO2>
	 */
	public List<InsAdmAnuDTO2> recupererIAAnnuellesV2(String codeEtud, String annee) {
		List<InsAdmAnuDTO2> insAdmAnuDTO2 = null;

		try {
			insAdmAnuDTO2 = serviceAdministratif.recupererIAAnnuellesV2(codeEtud, annee, "E");
		} catch (gouv.education.apogee.commun.client.ws.AdministratifMetier.WebBaseException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (insAdmAnuDTO2);
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
			return typeHebergementDTO;
		} catch (WebBaseException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.upond.apogeews.dao.referentiel.services.interfaces.
	 * EtudiantMetierServiceInterfaceCustom#recupererInfosAdmEtu(java.lang. String)
	 */

	public InfoAdmEtuDTO3 recupererInfosAdmEtuV3(String codEtu) {
		try {
			return etudiantMetierService.recupererInfosAdmEtuV3(codEtu);
		} catch (WebBaseException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.upond.apogeews.dao.referentiel.services.interfaces.
	 * EtudiantMetierServiceInterfaceCustom#recupererListeEtudiants(gouv.
	 * education.apogee.commun.transverse.dto.etudiant.EtudiantCritereDTO)
	 */

	public List<EtudiantDTO2> recupererListeEtudiants(EtudiantCritereDTO parametres) {
		// TODO Auto-generated method stub
		try {
			return etudiantMetierService.recupererListeEtudiants(parametres);
		} catch (WebBaseException_Exception e) {
			logger.error(e.getMessage());
		}
		return null;
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

		List<EtudiantDTO2> etudiantsFiltres = null;

		if (StringUtils.hasText(codEtu)) {
			logger.debug("recupererListeEtuParEtpEtDiplome filtre par codeEtu : " + codEtu);
			Predicate<EtudiantDTO2> byNumEtu = etudiant -> etudiant.getCodEtu().equals(codEtu);
			Stream<EtudiantDTO2> result = etudiants.stream().filter(byNumEtu);
			List<EtudiantDTO2> listEtudiantDto2 = result.collect(Collectors.toList());
			List<EtudiantDTO2Ext> listEtudiantDto2ext = ApogeeEtudiantMapper.Instance
					.etudiantDTO2ToEtudiantDTO2Ext(listEtudiantDto2);
			etudiantsFiltres = result.collect(Collectors.toList());
		}

		else if (StringUtils.hasText(nom)) {
			logger.debug("recupererListeEtuParEtpEtDiplome filtre par Nom : " + nom);
			Predicate<EtudiantDTO2> byName = etudiant -> etudiant.getNom().toUpperCase().contains(nom.toUpperCase());
			Stream<EtudiantDTO2> result = etudiants.stream().filter(byName);
			if (StringUtils.hasText(prenom)) {
				logger.debug("recupererListeEtuParEtpEtDiplome filtre par nom  +prenom : " + prenom);
				Predicate<EtudiantDTO2> byPrenom = etudiant -> !etudiant.getPrenom().toUpperCase()
						.contains(prenom.toUpperCase());
				Stream<EtudiantDTO2> resultByPrenom = result.dropWhile(byPrenom);
				etudiantsFiltres = resultByPrenom.collect(Collectors.toList());
			} else {
				etudiantsFiltres = result.collect(Collectors.toList());
			}
		} else if (StringUtils.hasText(prenom)) {
			logger.debug("recupererListeEtuParEtpEtDiplome filtre par prenom : " + prenom);
			Predicate<EtudiantDTO2> byPrenom = etudiant -> etudiant.getPrenom().toUpperCase()
					.contains(prenom.toUpperCase());
			Stream<EtudiantDTO2> resultParPrenom = etudiants.stream().filter(byPrenom);
			etudiantsFiltres = resultParPrenom.collect(Collectors.toList());
		}

		if (etudiantsFiltres != null) {
			return ApogeeEtudiantMapper.Instance.etudiantDTO2ToEtudiantDTO2Ext(etudiantsFiltres);
		} else
			return ApogeeEtudiantMapper.Instance.etudiantDTO2ToEtudiantDTO2Ext(etudiants);
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

		criteres.getListComposante().add(codeComposante);

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
		List<EtudiantDTO2> resultat = recupererListeEtudiants(criteres);
		return resultat;
	}

	/**
	 * 
	 * @param codeEtp
	 * @param versionEtp
	 * @return List<DiplomeDTO3>
	 */

	public List<DiplomeDTO3> recupererListDiplomeDTO3(String codeEtp, String versionEtp) {

		// recherche du cursus LMD, codFinalite
		SECritereDTO2 seCritereDTO = new SECritereDTO2();
//			seCritereDTO.setCodAnu(annee);
		seCritereDTO.setCodDip("aucun");
		seCritereDTO.setCodVrsVdi("aucun");
//			seCritereDTO.setCodEtp("aucun");
		seCritereDTO.setCodEtp(codeEtp);
//			seCritereDTO.setCodVrsVet("aucun");
		seCritereDTO.setCodVrsVet(versionEtp);
		seCritereDTO.setCodElp("tous");
//			seCritereDTO.setCodNatureElp("stag");

		List<DiplomeDTO3> diplomeDTO = null;

		try {
			diplomeDTO = offreFormationMetierService.recupererSEV3(seCritereDTO);
		} catch (gouv.education.apogee.commun.client.ws.OffreFormationMetier.WebBaseException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (diplomeDTO);

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
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		List<RegimeInscription> regimeInscriptions = new ArrayList<>();
		List<InsAdmAnuDTO2> iaAnnuelles = recupererIAAnnuellesV2(codeEtud, annee);
		logger.debug("iaAnnuelles : " + iaAnnuelles.size());
		for (InsAdmAnuDTO2 insAdmAnu : iaAnnuelles) {
			if (logger.isDebugEnabled()) {
				if (insAdmAnu.getRegimeIns() != null)
					logger.debug("#anneesInscriptionFC# - [id : " + codeEtud + ", annee  : " + annee + "\tRegimeIns :\t"
							+ insAdmAnu.getRegimeIns().getCodRgi() + " : " + insAdmAnu.getRegimeIns().getLibRgi()
							+ "]");
			}
			// Regime d'inscription
			if (insAdmAnu.getRegimeIns() != null) {
				RegimeInscDTO regimeDetail = recuperereRegimeInscription(insAdmAnu.getRegimeIns().getCodRgi(), "O");

				RegimeInscription regime = new RegimeInscription(insAdmAnu.getRegimeIns().getCodRgi(),
						insAdmAnu.getRegimeIns().getLibRgi(), annee);

				if (regimeDetail != null) {
					regime.setLicRegIns(regimeDetail.getLicRegIns());
					regime.setCodRegIns(regimeDetail.getCodRegIns());
				}

				regimeInscriptions.add(regime);

			}
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
