package org.esupportail.referentiel.services.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.esupportail.referentiel.beans.DiplomeReduitDto;
import org.esupportail.referentiel.beans.EtabRef;
import org.esupportail.referentiel.beans.EtapeReduiteDto;
import org.esupportail.referentiel.beans.SignataireRef;
import org.esupportail.referentiel.cache.CacheConfig;
import org.esupportail.referentiel.services.StudentComponentRepositoryDao;
import org.esupportail.referentiel.utils.DonneesStatic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import gouv.education.apogee.commun.client.ws.AdministratifMetier.AdministratifMetierServiceInterface;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.EtudiantMetierServiceInterface;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.DiplomeDTO3;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.EtapeDTO3;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.OffreFormationMetierServiceInterface;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.SECritereDTO2;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.TableauEtapeDTO3;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.TableauVersionDiplomeDTO3;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.TableauVersionEtapeDTO3;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.VersionDiplomeDTO3;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.VersionEtapeDTO32;
import gouv.education.apogee.commun.client.ws.ReferentielMetier.ComposanteDTO3;
import gouv.education.apogee.commun.client.ws.ReferentielMetier.ReferentielMetierServiceInterface;
import gouv.education.apogee.commun.client.ws.ReferentielMetier.SignataireWSSignataireDTO;
import gouv.education.apogee.commun.client.ws.ReferentielMetier.VariableAppliWSEtabRefDTO;

/**
 *
 * Acces au composantes du personnel personnalise.
 *
 */
@ConditionalOnProperty(name = "app.mode_apogee")
@Service
public class StudentComponentRepositoryDaoWS implements StudentComponentRepositoryDao {
	private static final long serialVersionUID = 949811640680344536L;
	/**
	 *
	 */
	@Autowired
	ReferentielMetierServiceInterface referentielMetierService;
	/**
	 *
	 */
	@Autowired
	EtudiantMetierServiceInterface etudiantMetierService;
	/**
	 *
	 */
	@Autowired
	AdministratifMetierServiceInterface serviceAdministratif;

	/**
	 *
	 */
	@Autowired
	OffreFormationMetierServiceInterface offreFormationMetierService;

	/**
	 *
	 */
	final transient Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Can read the education Apogee.
	 */
	// private ReadEnseignement remoteCriApogeeEns;

	/**
	 * startYearDay.
	 */
	protected String startYearDay;
	/**
	 * startYearMonth.
	 */
	protected String startYearMonth;

	/**
	 * mapComp.
	 */
	public LinkedHashMap<String, String> mapComp = new LinkedHashMap<String, String>();

	/**
	 *
	 * @param diplomeDTO3
	 * @return
	 */
	private List<DiplomeReduitDto> mapDiplomeReduitDto(List<DiplomeDTO3> diplomeDTO3) {
		if (logger.isDebugEnabled()) {
			logger.debug("getListeDiplomeDTO - nbr Dipolme diplomeDTO3 = " + diplomeDTO3.size());
		}
		List<DiplomeReduitDto> diplomes = new ArrayList<>();
		for (DiplomeDTO3 ld : diplomeDTO3) {

			TableauVersionDiplomeDTO3 listVldi = ld.getListVersionDiplome();

			for (VersionDiplomeDTO3 lvd : listVldi.getItem()) {

				DiplomeReduitDto diplomeRd = new DiplomeReduitDto();
				diplomeRd.setCodeDiplome(ld.getCodDip());
				diplomeRd.setVersionDiplome(String.valueOf(lvd.getCodVrsVdi()));
				diplomeRd.setLibDiplome(lvd.getLibWebVdi());
				diplomes.add(diplomeRd);
				TableauEtapeDTO3 letapes = lvd.getOffreFormation().getListEtape();
				for (EtapeDTO3 etp : letapes.getItem()) {

					etp.getListComposanteCentreGestion();

					TableauVersionEtapeDTO3 lverionEtps = etp.getListVersionEtape();

					for (VersionEtapeDTO32 lverionEtp : lverionEtps.getItem()) {
						EtapeReduiteDto erdto = new EtapeReduiteDto();
						erdto.setCodeEtp(etp.getCodEtp());
						erdto.setCodVrsVet(String.valueOf(lverionEtp.getCodVrsVet()));
						erdto.setLibWebVet(lverionEtp.getLibWebVet());
						diplomeRd.getListeEtapes().add(erdto);
					}

				}
			}

		}
		return diplomes;
	}

	/**
	 * retourne tous les diplomes avec les vets sans les elp
	 *
	 * @param universityCode
	 * @return
	 */
	private List<DiplomeDTO3> getListeListDiplomeVetAllDTO3(String universityCode) {
		// Recuperation des etapes depuis Apogee, cod et lib
		if (logger.isDebugEnabled()) {
			logger.debug("getEtapesRef - universityCode = " + universityCode);
		}

		SECritereDTO2 param = new SECritereDTO2();

		// Retrait du filtre sur l'annee pour permettre de rattacher les codes etape des
		// annees autres que celle en cours
		param.setTemOuvertRecrutement("O");
		param.setCodEtp("tous");
		param.setCodVrsVet("tous");
		param.setCodDip("tous");
		param.setCodVrsVdi("tous");
		param.setCodElp("aucun");
		List<DiplomeDTO3> diplomeDTO3 = null;
		try {
			diplomeDTO3 = offreFormationMetierService.recupererSEV3(param);
			return diplomeDTO3;
		} catch (gouv.education.apogee.commun.client.ws.OffreFormationMetier.WebBaseException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * retourne tous les diplomes avec les vets sans les elp
	 *
	 * @param universityCode
	 * @return
	 */
	private List<DiplomeDTO3> getListeListDiplomeVetAllDTO3(String codComposante, String codeAnu) {
		// Recuperation des etapes depuis Apogee, cod et lib
		if (logger.isDebugEnabled()) {
			logger.debug("getListeListDiplomeVetAllDTO3 - codComposante = " + codComposante);
		}

		SECritereDTO2 param = new SECritereDTO2();

		// Retrait du filtre sur l'annee pour permettre de rattacher les codes etape des
		// annees autres que celle en cours
		param.setTemOuvertRecrutement("O");
		param.setCodEtp("tous");
		param.setCodVrsVet("tous");
		param.setCodDip("tous");
		param.setCodVrsVdi("tous");
		param.setCodElp("aucun");

		param.setCodComposanteVdi(codComposante);
		param.setCodAnu(codeAnu);

		List<DiplomeDTO3> diplomeDTO3 = null;
		try {
			diplomeDTO3 = offreFormationMetierService.recupererSEV3(param);
			return diplomeDTO3;
		} catch (gouv.education.apogee.commun.client.ws.OffreFormationMetier.WebBaseException_Exception e) {
			logger.error("getListeListDiplomeVetAllDTO3 - codComposante = " + codComposante +  "\t annee = "+  codeAnu+ "  ->"+  e.getMessage());
			return null;
		}

	}

	/**
	 * @see org.esupportail.pstage.dao.referentiel.StudentComponentRepositoryDao#getEtabRef(java.lang.String)
	 */
	@Override
	public EtabRef getEtabRef(String universityCode) {
		String nomEtabRef = "";
		String adresseEtabRef = "";
		String ad2 = "";
		String ad3 = "";
		String cpo = "";
		String com = "";

		// recuperer le nom de l'etablissement
		List<VariableAppliWSEtabRefDTO> variableAppli = null;
		EtabRef etabRef = new EtabRef();
		try {
			variableAppli = referentielMetierService.recupererInformationEtabRef(DonneesStatic.COD_VAP_ETB_LIB);
		} catch (gouv.education.apogee.commun.client.ws.ReferentielMetier.WebBaseException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (variableAppli != null) {
			for (VariableAppliWSEtabRefDTO vapp : variableAppli) {
				if (vapp.getParVap() != null) {
					nomEtabRef = vapp.getParVap();
				}
			}
		}
		// recuperer adresse 2
		List<VariableAppliWSEtabRefDTO> variableAppliAd2 = null;
		try {
			variableAppliAd2 = referentielMetierService.recupererInformationEtabRef(DonneesStatic.COD_VAP_ETB_AD2);
		} catch (gouv.education.apogee.commun.client.ws.ReferentielMetier.WebBaseException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (variableAppliAd2 != null) {
			for (VariableAppliWSEtabRefDTO varApp : variableAppliAd2) {
				if (varApp.getParVap() != null) {
					ad2 = varApp.getParVap();
				}
			}
		}
		// recuperer adresse 3
		List<VariableAppliWSEtabRefDTO> variableAppliAd3 = null;
		try {
			variableAppliAd3 = referentielMetierService.recupererInformationEtabRef(DonneesStatic.COD_VAP_ETB_AD3);
		} catch (gouv.education.apogee.commun.client.ws.ReferentielMetier.WebBaseException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (variableAppliAd3 != null) {
			for (VariableAppliWSEtabRefDTO varApp : variableAppliAd3) {
				if (varApp.getParVap() != null) {
					ad3 = varApp.getParVap();
				}
			}
		}
		// recuperer code postal
		List<VariableAppliWSEtabRefDTO> variableAppliCpo = null;
		try {
			variableAppliCpo = referentielMetierService.recupererInformationEtabRef(DonneesStatic.COD_VAP_ETB_CPO);
		} catch (gouv.education.apogee.commun.client.ws.ReferentielMetier.WebBaseException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (variableAppliCpo != null) {
			for (VariableAppliWSEtabRefDTO varApp : variableAppliCpo) {
				if (varApp.getParVap() != null) {
					cpo = varApp.getParVap();
				}
			}
		}
		// recuperer commune
		List<VariableAppliWSEtabRefDTO> variableAppliCom = null;
		try {
			variableAppliCom = referentielMetierService.recupererInformationEtabRef(DonneesStatic.COD_VAP_ETB_COM);
		} catch (gouv.education.apogee.commun.client.ws.ReferentielMetier.WebBaseException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (variableAppliCom != null) {
			for (VariableAppliWSEtabRefDTO varApp : variableAppliCom) {
				if (varApp.getParVap() != null) {
					com = varApp.getParVap();
				}
			}
		}
		adresseEtabRef = ad2 + " " + cpo + " " + com;
		etabRef.setNomEtabRef(nomEtabRef);
		etabRef.setAdresseEtabRef(adresseEtabRef);
		return etabRef;

	}

	@Override
	@Cacheable(cacheNames = CacheConfig.PERMANENT, sync = true)
	public List<DiplomeReduitDto> getListeDiplomeDTO(String codComposante, String codeAnu) {
		if (logger.isDebugEnabled()) {
			logger.debug("getListeDiplomeDTO - universityCode + codeAnu = " + codComposante + " : " + codeAnu);
		}
		List<DiplomeDTO3> diplomeDTO3 = getListeListDiplomeVetAllDTO3(codComposante, codeAnu);
		if (diplomeDTO3==null) {
			logger.error("ECHEC DE LA RÉCUPERATIO DES DIPLOME DE LA  "+codComposante + " annee "+codeAnu );
			return null;
		}
		List<DiplomeReduitDto> dtos = mapDiplomeReduitDto(diplomeDTO3);
		return dtos;
	}

	@Override
	@Cacheable(cacheNames = CacheConfig.PERMANENT, sync = true)
	public List<DiplomeReduitDto> getListeDiplomeDTO(String universityCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("getListeDiplomeDTO - universityCode = " + universityCode);
		}
		List<DiplomeDTO3> diplomeDTO3 = getListeListDiplomeVetAllDTO3(universityCode);
		List<DiplomeReduitDto> dtos = mapDiplomeReduitDto(diplomeDTO3);
		return dtos;

	}

	/**
	 * @see org.esupportail.pstage.dao.referentiel.StudentComponentRepositoryDao#getEtapesRef(java.lang.String)
	 */
	@Override
	@Cacheable(cacheNames = CacheConfig.PERMANENT, sync = true)
	public LinkedHashMap<String, String> getEtapesRef(String universityCode) {
		// Recuperation des etapes depuis Apogee, cod et lib
		if (logger.isDebugEnabled()) {
			logger.debug("getEtapesRef - universityCode = " + universityCode);
		}
		LinkedHashMap<String, String> lSI = new LinkedHashMap<String, String>();

		Object idl = new Object();
		String lib = "";

		SECritereDTO2 param = new SECritereDTO2();

		// Retrait du filtre sur l'annee pour permettre de rattacher les codes etape des
		// annees autres que celle en cours
		param.setTemOuvertRecrutement("O");
		param.setCodEtp("tous");
		param.setCodVrsVet("tous");
		param.setCodDip("aucun");
		param.setCodVrsVdi("aucun");
		param.setCodElp("aucun");
		List<DiplomeDTO3> diplomeDTO3 = null;
		try {
			diplomeDTO3 = offreFormationMetierService.recupererSEV3(param);
		} catch (gouv.education.apogee.commun.client.ws.OffreFormationMetier.WebBaseException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		for (DiplomeDTO3 ld : diplomeDTO3) {
			TableauVersionDiplomeDTO3 versionDiplomeDTO3 = ld.getListVersionDiplome();
			for (VersionDiplomeDTO3 lvd : versionDiplomeDTO3.getItem()) {
				TableauEtapeDTO3 etapeDTO3 = lvd.getOffreFormation().getListEtape();
				for (EtapeDTO3 le : etapeDTO3.getItem()) {
					TableauVersionEtapeDTO3 versionEtapeDTO3 = le.getListVersionEtape();
					for (VersionEtapeDTO32 ve : versionEtapeDTO3.getItem()) {
						idl = le.getCodEtp();
						lib = ve.getLibWebVet();
						lSI.put(idl + ";" + ve.getCodVrsVet(), lib);
					}
				}
			}
		}

		return lSI;

	}

	/**
	 * @see org.esupportail.pstage.dao.referentiel.StudentComponentRepositoryDao#getSigCompoRef(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public SignataireRef getSigCompoRef(String universityCode, String composante) {
		SignataireRef sigRef = new SignataireRef();

		// recuperer le code signataire de la composante
		List<ComposanteDTO3> lcomposante = null;

		try {
			lcomposante = referentielMetierService.recupererComposanteV2(composante, null);
		} catch (gouv.education.apogee.commun.client.ws.ReferentielMetier.WebBaseException_Exception e1) {
			logger.error(e1.getMessage() + " ++++" + composante);
			e1.printStackTrace();
		}

		if (lcomposante != null) {
			for (ComposanteDTO3 compos : lcomposante) {
				if (compos.getCodCmp().equals(composante)) {
					// recherche du signataire de la composante
					if (compos.getCodSig() != null) {
						List<SignataireWSSignataireDTO> lsignataire = null;

						try {
							lsignataire = referentielMetierService.recupererSignataire(compos.getCodSig(),
									DonneesStatic.TEM_EN_SVE_O);
						} catch (gouv.education.apogee.commun.client.ws.ReferentielMetier.WebBaseException_Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						if (lsignataire != null) {
							for (SignataireWSSignataireDTO signataire : lsignataire) {
								sigRef.setNomSignataireComposante(signataire.getNomSig());
								sigRef.setQualiteSignataire(signataire.getQuaSig());
							}
						}
					}
				}
			}
		}
		return sigRef;

	}

	/**
	 * @see org.esupportail.pstage.dao.referentiel.StudentComponentRepositoryDao#getComposantesPrincipalesRef(java.lang.String,
	 *      java.util.Map)
	 */
	@Override
	@Cacheable(cacheNames = CacheConfig.PERMANENT, sync = true)
	public Map<String, String> getComposantesPrincipalesRef(String universityCode, Map<String, String> lesComposantes) {

		if (logger.isDebugEnabled()) {
			logger.debug("StudentComponentRepositoryDaoWS:: getComposantesPrincipalesRef . universityCode  = "
					+ universityCode);
		}
		mapComp = new LinkedHashMap<String, String>();

		// recuperer la liste des composantes
		List<ComposanteDTO3> composante;
		try {
			composante = referentielMetierService.recupererComposanteV2(null, null);
			if (composante != null) {

				if (logger.isDebugEnabled()) {
					logger.debug("StudentComponentRepositoryDaoWS:: getComposantesPrincipalesRef. composante  = "
							+ composante.size());
				}

				recupComposantes(composante);

			}

			return mapComp;
		} catch (gouv.education.apogee.commun.client.ws.ReferentielMetier.WebBaseException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public void recupComposantes(List<ComposanteDTO3> composantes) {

		for (ComposanteDTO3 composante : composantes) {
			Object idl = composante.getCodCmp();
			String lib = composante.getLibCmp();
			if (composante.getTemEnSveCmp().equals("O")) {
				mapComp.put(idl + "", lib);
			}
			// Si la composante en cours de recuperation contient une liste de composantes
			// filles
			// On la parcours egalement
			if (composante.getListeComposanteFils() != null
					&& !composante.getListeComposanteFils().getComposante().isEmpty()) {
				recupComposantes(composante.getListeComposanteFils().getComposante());
			}
		}
	}

	/**
	 * @return the startYearDay
	 */
	public String getStartYearDay() {
		return startYearDay;
	}

	/**
	 * @param startYearDay the startYearDay to set
	 */
	public void setStartYearDay(final String startYearDay) {
		this.startYearDay = startYearDay;
	}

	/**
	 * @return the startYearMonth
	 */
	public String getStartYearMonth() {
		return startYearMonth;
	}

	/**
	 * @param startYearMonth the startYearMonth to set
	 */
	public void setStartYearMonth(final String startYearMonth) {
		this.startYearMonth = startYearMonth;
	}

}
