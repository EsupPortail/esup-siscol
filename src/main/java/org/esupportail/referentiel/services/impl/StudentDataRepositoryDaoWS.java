package org.esupportail.referentiel.services.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.esupportail.referentiel.beans.AdministrationApogee;
import org.esupportail.referentiel.beans.ApogeeMap;
import org.esupportail.referentiel.beans.ElementPedagogique;
import org.esupportail.referentiel.beans.EtapeInscription;
import org.esupportail.referentiel.beans.EtudiantInfoAdm;
import org.esupportail.referentiel.beans.EtudiantRef;
import org.esupportail.referentiel.beans.RegimeInscription;
import org.esupportail.referentiel.services.StudentDataRepositoryDao;
import org.esupportail.referentiel.utils.DonneesStatic;
import org.esupportail.referentiel.utils.Utils;
import org.esupportail.referentiel.ws.services.EtudiantMetierClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import gouv.education.apogee.commun.client.ws.AdministratifMetier.InsAdmAnuDTO2;
import gouv.education.apogee.commun.client.ws.AdministratifMetier.InsAdmEtpDTO2;
import gouv.education.apogee.commun.client.ws.AdministratifMetier.InsAdmEtpDTO3;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.BlocageDTO;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.CoordonneesDTO2;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.IdentifiantsEtudiantDTO2;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.IndBacDTO;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.InfoAdmEtuDTO3;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.ComposanteCentreGestionDTO;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.DiplomeDTO3;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.ElementPedagogiDTO22;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.EtapeDTO3;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.ListeElementPedagogiDTO2;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.OffreFormationDTO3;
//import gouv.education.apogee.commun.client.ws.OffreFormationMetier.TableauListeElementPedagogiDTO3;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.TableauVersionDiplomeDTO3;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.VersionDiplomeDTO3;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.VersionEtapeDTO32;
import gouv.education.apogee.commun.client.ws.ReferentielMetier.ComposanteDTO3;

/**
 * Acces donnees etudiant
 */
@Service
public class StudentDataRepositoryDaoWS implements StudentDataRepositoryDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	final private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EtudiantMetierClient etudiantMetierClient;

//	EtudiantMetierServiceInterface etudiantMetierService;

	/**
	 * startYearDay
	 */
	@Value("${app.apogee.startYearDay}")
	private String startYearDay;
	/**
	 * startYearMonth
	 */
	@Value("${app.apogee.startYearMonth}")
	private String startYearMonth;

	/**
	 * temoinRecupAnnu;
	 */
	@Value("${app.apogee.temoinRecupAnnu}")
	private String temoinRecupAnnu = "TOUS";

	/**
	 * ldapStudentIdIsCODETU
	 */
	private boolean ldapStudentIdIsCODETU;

	/**
	 * Codes correspondants aux regimes d'inscription de la Formation Continue
	 */
	@Value("${app.apogee.codesRegimeInscriptionFC}")
	private String codesRegimeInscriptionFC = "2;4;5";

	public void cursusAmenage(final String codEtu) {
		
	}
	/**
	 * @param codEtu
	 * @return le codInd pour le codEtu passé en paramètre
	 */
	public String recupererCodIndParCodEtu(final String codEtu) {
		if (logger.isDebugEnabled()) {
			logger.debug("#getStudentCodInd# - this.temoinRecupAnnu : " + this.temoinRecupAnnu);
		}
		String codInd = codEtu;
		// Recherche du codInd de l'etudiant dans Apogee
		IdentifiantsEtudiantDTO2 etudiant = etudiantMetierClient.recupererIdentifiantsEtudiantByCodEtu(codEtu,
				this.temoinRecupAnnu);
		codInd = etudiant.getCodInd().toString();
		return codInd;
	}

	/**
	 * @param codInd
	 * @return le codEtu pour le codInd pass� en param�tre
	 */
	public String recupererCodeEtudiantParCodInd(final String codInd) {
		if (logger.isDebugEnabled()) {
			logger.debug("#getStudentCodEtu# - this.temoinRecupAnnu : " + this.temoinRecupAnnu);
		}
		String codEtu = codInd;
		// Recherche l'etudiant par le codind dans Apogee
		IdentifiantsEtudiantDTO2 etudiant = etudiantMetierClient.recupererIdentifiantsEtudiantByCodInd(codInd,
				this.temoinRecupAnnu);

		codEtu = etudiant.getCodEtu().toString();
		return codEtu;
	}

	/**
	 * 
	 * @param uid
	 * @return InfoAdmEtuDTO2
	 */
	@Override
	public InfoAdmEtuDTO3 recupererInfosAdmEtuV3(String uid) {
		InfoAdmEtuDTO3 etudiant = null;
		etudiant = etudiantMetierClient.recupererInfosAdmEtuV3(uid);
		return etudiant;

	}

	/**
	 * 
	 * @param uid
	 * @return IdentifiantsEtudiantDTO2
	 */
	@Override
	public IdentifiantsEtudiantDTO2 recupererIdentifiantsEtudiantDTO2(String uid) {
		if (logger.isDebugEnabled()) {
			logger.debug("#getStudentCodInd# - this.temoinRecupAnnu : " + this.temoinRecupAnnu);
		}
		IdentifiantsEtudiantDTO2 dentifiantsEtudiant_v2 = etudiantMetierClient
				.recupererIdentifiantsEtudiantByCodEtu(uid, this.temoinRecupAnnu);
		return dentifiantsEtudiant_v2;

	}

	/**
	 * 
	 * @param CodEtu
	 * @param anneeCourante
	 * @return CoordonneesDTO2
	 */
	public CoordonneesDTO2 recupererCoordonneesDTO2(String CodEtu, String anneeCourante) {
		// Recuperation des coordonnees de l'etudiant
		CoordonneesDTO2 coordonnees = etudiantMetierClient.recupererCoordonneesDTO2(CodEtu, anneeCourante,
				this.temoinRecupAnnu);
		return coordonnees;
	}

	/**
	 * Recuperation des etapes auxquelles l'etudiant est inscrit administrativement
	 * (inscription admin etape en cours (E)) en fonction de l'annee en param
	 * 
	 * @param cod
	 * @param annee
	 * @return List<InsAdmEtpDTO2>
	 */
	public List<InsAdmEtpDTO2> recupererIAEtapesV2(String cod, String annee) {
		List<InsAdmEtpDTO2> tabInsAdmEtp = etudiantMetierClient.recupererIAEtapesV2(cod, annee);
		return tabInsAdmEtp;
	}
	
	public List<InsAdmEtpDTO3> recupererIAEtapesV3(String cod, String annee) {
		List<InsAdmEtpDTO3> tabInsAdmEtp = etudiantMetierClient.recupererIAEtapesV3(cod, annee);
		return tabInsAdmEtp;
	}
	

	/**
	 * 
	 * @param codeEtud
	 * @return List<String>
	 */
	public List<String> recupererAnneesIa(String codeEtud) {
		List<String> annees = etudiantMetierClient.recupererAnneesIa(codeEtud);
		return (annees);
	}

	/**
	 * 
	 * @param codeEtud
	 * @param annee
	 * @return List<InsAdmAnuDTO2>
	 */
	public List<InsAdmAnuDTO2> recupererIAAnnuellesV2(String codeEtud, String annee) {
		List<InsAdmAnuDTO2> insAdmAnuDTO2 = etudiantMetierClient.recupererIAAnnuellesV2(codeEtud, annee);
		return (insAdmAnuDTO2);
	}

	/**
	 * 
	 * @param codeEtp
	 * @param versionEtp
	 * @return List<DiplomeDTO3>
	 */

	public List<DiplomeDTO3> recupererListDiplomeDTO3(String codeEtp, String versionEtp) {
		List<DiplomeDTO3> diplomeDTO = etudiantMetierClient.recupererListDiplomeDTO3(codeEtp, versionEtp);
		return (diplomeDTO);

	}

	/**
	 * Composante
	 * 
	 * @param codComposante
	 * @return ComposanteDTO3
	 */
	public ComposanteDTO3 recupererComposanteV2(String codComposante) {
		ComposanteDTO3 composante = etudiantMetierClient.recupererComposanteV2(codComposante);
		return composante;

	}

	/**
	 * Regime d'inscription Formation continue
	 * 
	 * @param codeEtud
	 * @param annee
	 * @return List<String>
	 */
	public List<String> recupererAnneesInscriptionFC(String codeEtud, String annee) {

		List<String> anneesInscriptionFC = new ArrayList<>();
		List<InsAdmAnuDTO2> iaAnnuelles = recupererIAAnnuellesV2(codeEtud, annee);
		String[] codesFC = codesRegimeInscriptionFC.split(";");

		for (InsAdmAnuDTO2 insAdmAnu : iaAnnuelles) {

			if (logger.isDebugEnabled()) {
				if (insAdmAnu.getRegimeIns() != null)
					logger.debug("#anneesInscriptionFC# - [id : " + codeEtud + ", annee  : " + annee + "\tRegimeIns :\t"
							+ insAdmAnu.getRegimeIns().getCodRgi() + " : " + insAdmAnu.getRegimeIns().getLibRgi()
							+ "]");
			}
			// Regime d'inscription
			if (insAdmAnu.getRegimeIns() != null
					&& Arrays.asList(codesFC).contains(insAdmAnu.getRegimeIns().getCodRgi())) {
				anneesInscriptionFC.add(annee);

			}
		}

		return anneesInscriptionFC;
	}

	/**
	 * Recuperation du regime d'inscription pour l'etudiant a partir de l'annee
	 * 
	 * @param codeEtud
	 * @param annee
	 * @return
	 */
	public String recupererRegimeInscriptionCPAM(String codeEtud, String annee) {

		List<InsAdmAnuDTO2> iaAnnuelles = recupererIAAnnuellesV2(codeEtud, annee);
		String libelleCPAM = "";
		if (iaAnnuelles == null || iaAnnuelles.isEmpty()) {
			return "";
		}
		for (InsAdmAnuDTO2 insAdmAnu : iaAnnuelles) {
			// Libelle CPAM
			if (libelleCPAM.isEmpty() && insAdmAnu.getCpam() != null && insAdmAnu.getCpam().getLibCpam() != null) {
				logger.debug("Libelle CPAM : " + insAdmAnu.getCpam().getLibCpam());
				libelleCPAM = insAdmAnu.getCpam().getLibCpam();
				break;
			}
		}
		return libelleCPAM;
	}

	/**
	 * 
	 * @param id
	 * @param annee
	 * @return EtudiantInfoAdm
	 */
	public EtudiantInfoAdm recupererEtudiantInfoAdm(String id) {

		EtudiantInfoAdm etudiantInfoAdm = new EtudiantInfoAdm();

		if (logger.isDebugEnabled()) {
			logger.debug("#getStudentApogee# - [id : " + id + ")]");
		}
		// Recherche l'etudiant dans Apogee
		IdentifiantsEtudiantDTO2 etudiant = null;

		etudiant = recupererIdentifiantsEtudiantDTO2(id);
		if (etudiant == null) {
			throw new RuntimeException("ÉCHEC de LE RÉCUPÉRATION DE L'ÉTUDIANT ");
		}
		InfoAdmEtuDTO3 apogeeinfosAdmDTO2 = recupererInfosAdmEtuV3(etudiant.getCodEtu().toString());

		// etudiantInfoAdm.setCodInd(etudiant.getCodInd());
		etudiantInfoAdm.setEmailAnnuaire(etudiant.getEmailAnnuaire());
		etudiantInfoAdm.setLoginAnnuaire(etudiant.getLoginAnnuaire());

		etudiantInfoAdm.setNumEtu(String.valueOf(apogeeinfosAdmDTO2.getNumEtu()));

		etudiantInfoAdm.setNumBoursier((etudiant.getNumBoursier()));
		etudiantInfoAdm.setNumeroINE((etudiant.getNumeroINE()));
		etudiantInfoAdm.setCodEtu(String.valueOf(etudiant.getCodEtu()));

		etudiantInfoAdm.setCodInd((String.valueOf(etudiant.getCodInd())));
		if (apogeeinfosAdmDTO2.getHandicap() != null) {
			etudiantInfoAdm.setHandicap((apogeeinfosAdmDTO2.getHandicap().getLibThp()));
		}

		// recuperation du nom, prenom
		if (apogeeinfosAdmDTO2.getNomPatronymique() != null) {
			etudiantInfoAdm.setNomPatronymique(apogeeinfosAdmDTO2.getNomPatronymique());
		}
		if (apogeeinfosAdmDTO2.getNomUsuel() != null) {
			etudiantInfoAdm.setNomUsuel(apogeeinfosAdmDTO2.getNomUsuel());
		}
		if (apogeeinfosAdmDTO2.getPrenom1() != null) {
			etudiantInfoAdm.setPrenom1(apogeeinfosAdmDTO2.getPrenom1());
		}
		if (apogeeinfosAdmDTO2.getPrenom2() != null) {
			etudiantInfoAdm.setPrenom2(apogeeinfosAdmDTO2.getPrenom2());
		}
		// recuperation du code sexe et date de naissance de l'etudiant
		if (apogeeinfosAdmDTO2.getSexe() != null) {
			etudiantInfoAdm.setSexe(apogeeinfosAdmDTO2.getSexe());
		}
		if (apogeeinfosAdmDTO2.getDateNaissance() != null) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			etudiantInfoAdm.setDateNaissance(
					dateFormat.format(Utils.dateFromLocalDateTime(apogeeinfosAdmDTO2.getDateNaissance())));
		}
		if (apogeeinfosAdmDTO2.getDepartementNaissance() != null) {
			etudiantInfoAdm.setDepartementNaissance(apogeeinfosAdmDTO2.getDepartementNaissance().getCodeDept());

		}
		if (apogeeinfosAdmDTO2.getPaysNaissance() != null) {
			etudiantInfoAdm.setPaysNaissance(apogeeinfosAdmDTO2.getPaysNaissance().getLibPay() + ":"
					+ apogeeinfosAdmDTO2.getPaysNaissance().getCodPay());

		}
		if (apogeeinfosAdmDTO2.getSituationMilitaire() != null) {
			etudiantInfoAdm.setSituationMilitaire(apogeeinfosAdmDTO2.getSituationMilitaire().getLibSim());
		}
		if (apogeeinfosAdmDTO2.getSituationFamiliale() != null) {
			etudiantInfoAdm.setSituationFamiliale(apogeeinfosAdmDTO2.getSituationFamiliale().getLibLongFam());
		}

		if (apogeeinfosAdmDTO2.getLibVilleNaissance() != null) {
			etudiantInfoAdm.setLibVilleNaissance(apogeeinfosAdmDTO2.getLibVilleNaissance());

		}

		if (apogeeinfosAdmDTO2.getNationaliteDTO() != null) {
			etudiantInfoAdm.setNationaliteDTO(apogeeinfosAdmDTO2.getNationaliteDTO().getCodeNationalite() + ":"
					+ apogeeinfosAdmDTO2.getNationaliteDTO().getLibNationalite());

		}

		if (apogeeinfosAdmDTO2.getAnneePremiereInscEnsSup() != null) {
			etudiantInfoAdm.setAnneePremiereInscEnsSup(apogeeinfosAdmDTO2.getAnneePremiereInscEnsSup());
		}

		if (apogeeinfosAdmDTO2.getAnneePremiereInscEtb() != null) {
			etudiantInfoAdm.setAnneePremiereInscEtb(apogeeinfosAdmDTO2.getAnneePremiereInscEtb());
		}
		if (apogeeinfosAdmDTO2.getAnneePremiereInscEtr() != null) {
			etudiantInfoAdm.setAnneePremiereInscEtr(apogeeinfosAdmDTO2.getAnneePremiereInscEtr());
		}
		if (apogeeinfosAdmDTO2.getListeBacs() != null) {
			String bacs = "";
			for (IndBacDTO bac : apogeeinfosAdmDTO2.getListeBacs().getItem()) {
				if (!bacs.isBlank())
					bacs = bacs + ";";
				bacs = bacs + bac.getCodBac() + ":" + bac.getLibelleBac() + ":" + bac.getAnneeObtentionBac();
			}
			etudiantInfoAdm.setListeBacs(bacs);
		}

		if (apogeeinfosAdmDTO2.getListeBlocages() != null) {
			String blocages = "";
			for (BlocageDTO blocage : apogeeinfosAdmDTO2.getListeBlocages().getItem()) {
				if (!blocages.isBlank())
					blocages = blocages + ";";
				blocages = blocages + blocage.getEtapeBlocage().getLibEtp() + ":"
						+ blocage.getEtapeBlocage().getCodEtp() + ":" + blocage.getCodBlocage() + ":"
						+ blocage.getLibBlocage() + ":" + blocage.getTypBlocage();
			}
			etudiantInfoAdm.setListeBlocages(blocages);
		}

		return etudiantInfoAdm;

	}

	/**
	 * @param universityCode
	 * @param codeEtu
	 * @return studentApogee
	 */
	public EtudiantRef recupererEtudiantApogee(String universityCode, String codeEtu, String annee,
			boolean temRecupAnneeAntecedente) {
		if (logger.isDebugEnabled()) {
			logger.debug("#getStudentApogee# - [id : " + codeEtu + ", universityCode  : " + universityCode
					+ ", annee  : " + annee + "]");
		}

		EtudiantRef etudiantRef = new EtudiantRef();
		// adresse permanente etudiant
		String mainAddress = "";
		// code postal etudiant

		// libelle CPAM etudiant
		String libelleCPAM = "";

		// Recherche l'etudiant dans Apogee
		IdentifiantsEtudiantDTO2 etudiant = recupererIdentifiantsEtudiantDTO2(codeEtu);

		// Recuperation des infos de l'etudiant dans Apogee
		InfoAdmEtuDTO3 infosAdmEtu = recupererInfosAdmEtuV3(etudiant.getCodEtu().toString());

		// Ajout des variables d'annee (pour permettre la modif d'anciennes conventions)
		String anneeCourante = "";
		String anneePrecedente = "";
		String anneeSuivante = "";

		if (annee != null && !annee.isEmpty()) {
			anneeCourante = annee;
			anneePrecedente = String.valueOf((Utils.convertStringToInt(annee) - 1));
			anneeSuivante = String.valueOf((Utils.convertStringToInt(annee) + 1));
		} else {
			anneeCourante = getYear();
			anneePrecedente = String.valueOf((Utils.convertStringToInt(getYear()) - 1));
			anneeSuivante = String.valueOf((Utils.convertStringToInt(getYear()) + 1));
		}
		// Recuperation des coordonnees de l'etudiant
		CoordonneesDTO2 coordonnees = new CoordonneesDTO2();
		coordonnees = recupererCoordonneesDTO2(etudiant.getCodEtu().toString(), anneeCourante);

		// BigDecimal cod_ind = null;
		Integer cod_ind = etudiant.getCodInd();

		AdministrationApogee adminApogee = new AdministrationApogee();
		adminApogee.setStatusApogee(true);
		adminApogee.setRaison("");
		etudiantRef.setCod_ind(cod_ind);

		// etudiantRef.setIdentEtudiant(String.valueOf(etudiant.getCodEtu()));

		// recuperation du nom, prenom
		if (infosAdmEtu.getNomPatronymique() != null) {
			etudiantRef.setNompatro(infosAdmEtu.getNomPatronymique());
		}
		if (infosAdmEtu.getNomUsuel() != null) {
			etudiantRef.setNommarital(infosAdmEtu.getNomUsuel());
		}

		if (infosAdmEtu.getPrenom1() != null) {
			etudiantRef.setPrenom(infosAdmEtu.getPrenom1());
		}
		// recuperation du code sexe et date de naissance de l'etudiant
		if (infosAdmEtu.getSexe() != null) {
			etudiantRef.setCodeSexe(infosAdmEtu.getSexe());
		}
		if (infosAdmEtu.getDateNaissance() != null) {
			etudiantRef.setDateNais(Utils.dateFromLocalDateTime(infosAdmEtu.getDateNaissance()));
		}

		// recherche des informations etudiant dans APOGEE
		if (cod_ind != null) {

			if (coordonnees.getAdresseFixe().getPays().getCodPay() != null) {
				etudiantRef.setCodePays(coordonnees.getAdresseFixe().getPays().getCodPay());
			}
			if (coordonnees.getAdresseFixe().getLibAd1() != null) {
				etudiantRef.setLibAd1(coordonnees.getAdresseFixe().getLibAd1());
			}
			if (coordonnees.getAdresseFixe().getLibAd2() != null) {
				etudiantRef.setLibAd2(coordonnees.getAdresseFixe().getLibAd2());
			}
			if (coordonnees.getAdresseFixe().getLibAd3() != null) {
				etudiantRef.setLibAd3(coordonnees.getAdresseFixe().getLibAd3());
			}
			if (coordonnees.getAdresseFixe().getLibAde() != null) {
				etudiantRef.setLibAde(coordonnees.getAdresseFixe().getLibAde());
			}
			if (coordonnees.getAdresseFixe().getCommune() != null) {
				if (coordonnees.getAdresseFixe().getCommune().getCodePostal() != null) {
					etudiantRef.setPostalCode(coordonnees.getAdresseFixe().getCommune().getCodePostal());
				}
			}
			if (coordonnees.getAdresseFixe().getCommune() != null) {
				if (coordonnees.getAdresseFixe().getCommune().getNomCommune() != null) {
					etudiantRef.setTown(coordonnees.getAdresseFixe().getCommune().getNomCommune());
				}
			}
			if (coordonnees.getAdresseFixe().getPays().getLibPay() != null) {
				etudiantRef.setCountry(coordonnees.getAdresseFixe().getPays().getLibPay());
			}
			if (coordonnees.getAdresseFixe().getNumTel() != null) {
				etudiantRef.setPhone(coordonnees.getAdresseFixe().getNumTel());
			}
			if (coordonnees.getNumTelPortable() != null) {
				etudiantRef.setPortablePhone(coordonnees.getNumTelPortable());
			}
			if (coordonnees.getEmail() != null) {
				etudiantRef.setMailPerso(coordonnees.getEmail());
			}
			// recuperation du mail dans l'annuaire
			if (coordonnees.getEmailAnnuaire() != null) {
				etudiantRef.setMail(coordonnees.getEmailAnnuaire());
			}

			if (etudiantRef.getCodePays() != null) {
				if (etudiantRef.getCodePays().equals("100")) {
					if (etudiantRef.getLibAd1() != null) {
						mainAddress = etudiantRef.getLibAd1() + " ";
					}
					if (etudiantRef.getLibAd2() != null) {
						mainAddress = mainAddress + etudiantRef.getLibAd2() + " ";
					}
					if (etudiantRef.getLibAd3() != null) {
						mainAddress = mainAddress + etudiantRef.getLibAd3();
					}
				} else {
					if (etudiantRef.getLibAd1() != null) {
						mainAddress = etudiantRef.getLibAd1() + " ";
					}
					if (etudiantRef.getLibAd2() != null) {
						mainAddress = mainAddress + etudiantRef.getLibAd2() + " ";
					}
					if (etudiantRef.getLibAde() != null) {
						/**
						 * TODO definir libAde au prealable
						 */
						mainAddress = mainAddress + etudiantRef.getLibAde();
					}
				}
			}
		}
		etudiantRef.setMainAddress(mainAddress);

		/*
		 * ******************************************************** Verification des
		 * inscriptions annuelles de l'étudiant
		 *********************************************************/
		if (logger.isDebugEnabled()) {
			logger.debug("Verification des inscriptions annuelles de l'étudiant");
		}

		List<String> listeAnneesUniv = new ArrayList<String>();
		// Recuperation des codes renseignées en parametre pour detecter la FC
		// String[] codesFC = this.codesRegimeInscriptionFC.split(";");
		List<String> annees = recupererAnneesIa(etudiant.getCodEtu().toString());
		List<String> anneesInscriptionFC = new ArrayList<>();

		// INSCRIPTION SUR L'ANNEE PRECEDENTE ?
		if (temRecupAnneeAntecedente) {
			if (annees.contains(anneePrecedente)) {
				logger.debug("Inscription trouvee sur l'annee precedente (" + anneePrecedente + ")");
				listeAnneesUniv.add(anneePrecedente);

				libelleCPAM = recupererRegimeInscriptionCPAM(Integer.toString(etudiant.getCodEtu()), anneeCourante);
				anneesInscriptionFC
						.addAll(recupererAnneesInscriptionFC(Integer.toString(etudiant.getCodEtu()), anneeCourante));

			} else {
				logger.debug("Pas d'inscription pour l'etudiant " + etudiant.getCodEtu().toString() + " sur l'annee "
						+ anneePrecedente);
			}
		}

		// INSCRIPTION SUR L'ANNEE COURANTE ?
		if (annees.contains(anneeCourante)) {
			logger.debug("Inscription trouvee sur l'annee courante (" + anneeCourante + ")");
			listeAnneesUniv.add(anneeCourante);

			// Recuperation du libelle CPAM et du regime d'inscription pour l'etudiant a
			// partir de l'annee
			listeAnneesUniv.add(anneeSuivante);
			libelleCPAM = recupererRegimeInscriptionCPAM(Integer.toString(etudiant.getCodEtu()), anneeCourante);
			anneesInscriptionFC
					.addAll(recupererAnneesInscriptionFC(Integer.toString(etudiant.getCodEtu()), anneeCourante));

		} else {
			logger.debug("Pas d'inscription pour l'etudiant " + etudiant.getCodEtu().toString() + " sur l'annee "
					+ anneeCourante);
		}

		// INSCRIPTION SUR L'ANNEE SUIVANTE ?
		if (annees.contains(anneeSuivante)) {
			logger.debug("Inscription trouvee sur l'annee suivante (" + anneeSuivante + ")");
			listeAnneesUniv.add(anneeSuivante);
			libelleCPAM = recupererRegimeInscriptionCPAM(Integer.toString(etudiant.getCodEtu()), anneeSuivante);
			anneesInscriptionFC
					.addAll(recupererAnneesInscriptionFC(Integer.toString(etudiant.getCodEtu()), anneeSuivante));

		} else {
			logger.debug("Pas d'inscription pour l'etudiant " + etudiant.getCodEtu().toString() + " sur l'annee "
					+ anneeSuivante);
		}

		/**
		 * TODO ???????????????????????????
		 */
		etudiantRef.setLibelleCPAM(libelleCPAM);
		// etudiantRef.setSteps(steps);

		etudiantRef.setAnneesInscriptionFC(anneesInscriptionFC);

		logger.info("anneesInscriptionFC", adminApogee);

		logger.info("anneesInscriptionFC", listeAnneesUniv);

		return etudiantRef;

	}

	/**
	 * @param cod
	 * @return ApogeeMap
	 */
	public ApogeeMap recupererEtapesByEtudiantAndAnnee(String codEtud, String anneeScolaire, String codeUniversite) {
		if (logger.isDebugEnabled()) {
			logger.debug("#getEtapesByEtudiantAndAnnee# - cod : " + codEtud);
		}

		// recherche des Inscriptions Administratives et Inscription Pedagogiques
		ApogeeMap apogeeMap = recupererIaIpParEtudiantAnnee(codEtud, anneeScolaire);

		return apogeeMap;
	}

	/**
	 * / Recuperation des etapes auxquelles l'etudiant est inscrit
	 * administrativement // (inscription admin etape en cours (E)) en fonction de
	 * l'annee en param
	 * 
	 * @param codEtud
	 * @param annee
	 * @return LinkedHashMap<String, String>
	 */
	public LinkedHashMap<String, String> recupererEtapesParEtudiantAnnee(String codEtud, String annee) {
		List<InsAdmEtpDTO2> tabInsAdmEtp = recupererIAEtapesV2(codEtud, annee);
		LinkedHashMap<String, String> lEtape = new LinkedHashMap<String, String>();
		for (InsAdmEtpDTO2 insAdmEtp : tabInsAdmEtp) {
			if (logger.isDebugEnabled()) {
				logger.debug("- Inscription Administrative -");
				logger.debug("[codeEtape : " + insAdmEtp.getEtape().getCodeEtp() + ", codeVersionEtape : "
						+ insAdmEtp.getEtape().getVersionEtp() + ", libEtape : " + insAdmEtp.getEtape().getLibWebVet()
						+ ", codeComposante : " + insAdmEtp.getComposante().getCodComposante() + ", libComposante : "
						+ insAdmEtp.getComposante().getLibComposante() + ", codeInscriptionPayee : "
						+ insAdmEtp.getCodeInscriptionPayee() + "]");
			}

			if (insAdmEtp.getCodeInscriptionPayee().equals("P")) {

				// **********************
				// POUR LES ETAPES
				// **********************
				String idl = insAdmEtp.getEtape().getCodeEtp();
				String lib = insAdmEtp.getEtape().getLibWebVet();

				// liste Etape
				lEtape.put(idl + "", lib);
			}
		}

		return lEtape;

	}

	/**
	 * / Recuperation des etapes auxquelles l'etudiant est inscrit
	 * administrativement // (inscription admin etape en cours (E)) en fonction de
	 * l'annee en param
	 * 
	 * @param codEtud
	 * @param annee
	 * @return LinkedHashMap<String, String>
	 */
	public LinkedHashMap<String, String> recupererEtapeVetsParEtudiantAnnee(String codEtud, String annee) {
		List<InsAdmEtpDTO2> tabInsAdmEtp = recupererIAEtapesV2(codEtud, annee);
		LinkedHashMap<String, String> lEtapeVet = new LinkedHashMap<String, String>();
		for (InsAdmEtpDTO2 insAdmEtp : tabInsAdmEtp) {
			if (logger.isDebugEnabled()) {
				logger.debug("- Inscription Administrative -");
				logger.debug("[codeEtape : " + insAdmEtp.getEtape().getCodeEtp() + ", codeVersionEtape : "
						+ insAdmEtp.getEtape().getVersionEtp() + ", libEtape : " + insAdmEtp.getEtape().getLibWebVet()
						+ ", codeComposante : " + insAdmEtp.getComposante().getCodComposante() + ", libComposante : "
						+ insAdmEtp.getComposante().getLibComposante() + ", codeInscriptionPayee : "
						+ insAdmEtp.getCodeInscriptionPayee() + "]");
			}

			if (insAdmEtp.getCodeInscriptionPayee().equals("P")) {

				String idl = insAdmEtp.getEtape().getCodeEtp();
				// String lib = insAdmEtp.getEtape().getLibWebVet();
				String vet = insAdmEtp.getEtape().getVersionEtp();
				lEtapeVet.put(idl + "", vet);
			}
		}

		return lEtapeVet;

	}

	/**
	 * recherche des Inscriptions Administratives payees de l'etudiant
	 * 
	 * @param codEtud
	 * @param annee
	 * @return LinkedHashMap<String, String>
	 */
	public LinkedHashMap<String, String> recupererComposantesParEtudiantAnnee(String codEtud, String annee) {
		List<InsAdmEtpDTO2> tabInsAdmEtp = recupererIAEtapesV2(codEtud, annee);
		LinkedHashMap<String, String> lComposante = new LinkedHashMap<String, String>();

		for (InsAdmEtpDTO2 insAdmEtp : tabInsAdmEtp) {
			if (logger.isDebugEnabled()) {
				logger.debug("- Inscription Administrative -");
				logger.debug("[codeEtape : " + insAdmEtp.getEtape().getCodeEtp() + ", codeVersionEtape : "
						+ insAdmEtp.getEtape().getVersionEtp() + ", libEtape : " + insAdmEtp.getEtape().getLibWebVet()
						+ ", codeComposante : " + insAdmEtp.getComposante().getCodComposante() + ", libComposante : "
						+ insAdmEtp.getComposante().getLibComposante() + ", codeInscriptionPayee : "
						+ insAdmEtp.getCodeInscriptionPayee() + "]");
			}

			if (insAdmEtp.getCodeInscriptionPayee().equals("P")) {

				// **********************
				// POUR LES COMPOSANTES
				// **********************
				String idlComp = insAdmEtp.getComposante().getCodComposante();
				String libComp = insAdmEtp.getComposante().getLibComposante();
				if (libComp == null) {
					// ComposanteDTO3[] composante;
					try {
						ComposanteDTO3 composante = recupererComposanteV2(idlComp);
						if (composante != null) {
							libComp = composante.getLibCmp();
						}
					} catch (Exception e) {
						logger.warn("Exception recupererComposante_v2 : " + e);
						continue;
					}

				}
				lComposante.put(idlComp + "", libComp);
			}

		}
		return lComposante;
	}

	/**
	 * recherche des Inscriptions Administratives payees de l'etudiant
	 * 
	 * @param codEtud
	 * @param annee
	 * @return LinkedHashMap<String, String>
	 */
	public List<EtapeInscription> recupererEtapeInscriptionParEtudiantAnnee(String codEtud, String annee) {
		List<InsAdmEtpDTO3> tabInsAdmEtp = recupererIAEtapesV3(codEtud, annee);
		List<EtapeInscription> listeEtapeInscriptions = new ArrayList<EtapeInscription>();

		for (InsAdmEtpDTO3 insAdmEtp : tabInsAdmEtp) {
			if (logger.isDebugEnabled()) {
				logger.debug("- Inscription Administrative -");
				logger.debug("[codeEtape : " + insAdmEtp.getEtape().getCodeEtp() + ", codeVersionEtape : "
						+ insAdmEtp.getEtape().getVersionEtp() + ", libEtape : " + insAdmEtp.getEtape().getLibWebVet()
						+ ", codeComposante : " + insAdmEtp.getComposante().getCodComposante() + ", libComposante : "
						+ insAdmEtp.getComposante().getLibComposante() + ", codeInscriptionPayee : "
						+ insAdmEtp.getCodeInscriptionPayee() + "]");
			}

			if (insAdmEtp.getCodeInscriptionPayee().equals("P")) {

				EtapeInscription etpins = new EtapeInscription();
				etpins.setCodeEtp(insAdmEtp.getEtape().getCodeEtp());
				etpins.setCodVrsVet(insAdmEtp.getEtape().getVersionEtp());
				etpins.setLibWebVet(insAdmEtp.getEtape().getLibWebVet());
				etpins.setCodeComposante(insAdmEtp.getComposante().getCodComposante());
				etpins.setLibComposante(insAdmEtp.getComposante().getLibComposante());
				etpins.setTypeIns(DonneesStatic.TYPE_INS_ADMIN);
				if(insAdmEtp.getCursusAmg()!=null) {
					etpins.setCodeCursusAmenage(insAdmEtp.getCursusAmg().getCodCurAmg());
					etpins.setCodeCursusAmenage(insAdmEtp.getCursusAmg().getLibCurAmg());
				}

				if (insAdmEtp.getDiplome() != null) {
					// renseignement des infos du diplome
					String codeDiplome = insAdmEtp.getDiplome().getCodeDiplome();
					etpins.setCodeDiplome(codeDiplome);
					etpins.setLibDiplome(insAdmEtp.getDiplome().getLibLongDiplome());
					String versDiplome = insAdmEtp.getDiplome().getVersionDiplome();
					etpins.setVersionDiplome(versDiplome);

					List<DiplomeDTO3> diplomeDTOs = recupererListDiplomeDTO3(insAdmEtp.getEtape().getCodeEtp(),
							insAdmEtp.getEtape().getVersionEtp());
					if (diplomeDTOs != null && !diplomeDTOs.isEmpty()) {
						for (DiplomeDTO3 diplomeDTO : diplomeDTOs) {

							try {

								TableauVersionDiplomeDTO3 versionDiplomes = diplomeDTO.getListVersionDiplome();

								for (VersionDiplomeDTO3 versionDiplome : versionDiplomes.getItem()) {
									if (versionDiplome.getCodCursusLmd() != null) {
										etpins.setCodCursusLmd(versionDiplome.getCodCursusLmd());
									}
									OffreFormationDTO3 offreFormation = versionDiplome.getOffreFormation();
									if (offreFormation != null) {

										if (offreFormation.getCodFinalite() != null) {
											etpins.setCodFinalite(offreFormation.getCodFinalite());
										}
										if (offreFormation.getLibFinalite() != null) {
											etpins.setLibFinalite(offreFormation.getLibFinalite());
										}

										// Recup volume horaire si saisi
										/**
										 * TODO vérification non faite
										 * 
										 */
//										Integer volumeHoraire = offreFormation.getListEtape()[0]
//												.getListVersionEtape()[0].getVolume();
										Integer volumeHoraire = offreFormation.getListEtape().getItem().get(0)
												.getListVersionEtape().getItem().get(0).getVolume();

										if (volumeHoraire != null) {
											etpins.setVolumeHoraire(Integer.toString(volumeHoraire));
										} else {
											etpins.setVolumeHoraire("0");
										}
									}

								}
							} catch (Exception e) {
								logger.warn("Exception recupererSE_v3 : " + e);
								if (e.toString().equals("technical.data.nullretrieve.recupererse")) {
									logger.warn("Aucune donnee en sortie pour " + codEtud + " - diplome/vers : "
											+ codeDiplome + versDiplome);
									continue;
								}
								continue;
							}

						}

					}
				}
				listeEtapeInscriptions.add(etpins);
			}

		}
		return listeEtapeInscriptions;
	}

//	/**
//	 * 
//	 * @param codeEtp
//	 * @param versionEtp
//	 * @return LinkedHashMap<VersionDiplomeDTO3, List<ListeElementPedagogiDTO2>>
//	 */
//	@Deprecated
//	public LinkedHashMap<VersionDiplomeDTO3, List<ListeElementPedagogiDTO2>> recupereListeELPsParEtape(String codeEtp,
//			String versionEtp) {
//
//		LinkedHashMap<VersionDiplomeDTO3, List<ListeElementPedagogiDTO2>> elementPedagogiques = new LinkedHashMap<VersionDiplomeDTO3, List<ListeElementPedagogiDTO2>>();
//
//		List<DiplomeDTO3> diplomeDTOs = recupererListDiplomeDTO3(codeEtp, versionEtp);
//
//		for (DiplomeDTO3 diplomeDTO : diplomeDTOs) {
//			TableauVersionDiplomeDTO3 versionDiplomes = diplomeDTO.getListVersionDiplome();
//			for (VersionDiplomeDTO3 versionDiplome : versionDiplomes.getItem()) {
//
////				ListeElementPedagogiDTO2[] elementPedagos = versionDiplome.getOffreFormation().getListEtape()[0]
////						.getListVersionEtape()[0].getListListeElementPedagogi();
//				 TableauListeElementPedagogiDTO2 elementPedagos = versionDiplome.getOffreFormation().getListEtape()
//						.getItem().get(0).getListVersionEtape().getItem().get(0).getListListeElementPedagogi();
//				elementPedagogiques.put(versionDiplome, elementPedagos.getItem());
//
//			}
//
//		}
//
//		// elementPedagogiques.put(etpins.getCodeEtp() + "", elpedago);
//		return elementPedagogiques;
//	}

	/**
	 * 
	 * @param codeEtp
	 * @param versionEtp
	 * @return
	 */
	public List<ElementPedagogique> recupererListeElpsStageParEtape(String codeEtp, String versionEtp) {

		/**
		 * TODO factoriser et supprimer le code redcode en double
		 */

		List<ElementPedagogique> listeELPs = new ArrayList<ElementPedagogique>();
		try {

			List<DiplomeDTO3> diplomeDTOs = recupererListDiplomeDTO3(codeEtp, versionEtp);
			if (diplomeDTOs != null && !diplomeDTOs.isEmpty()) {
				for (DiplomeDTO3 diplomeDTO : diplomeDTOs) {
					TableauVersionDiplomeDTO3 versionDiplomes = diplomeDTO.getListVersionDiplome();
					for (VersionDiplomeDTO3 versionDiplome : versionDiplomes.getItem()) {
						EtapeInscription etpins = new EtapeInscription();
						etpins.setCodeEtp(codeEtp);
						etpins.setCodVrsVet(versionEtp);

						if (versionDiplome != null) {
							etpins.setCodCursusLmd(versionDiplome.getCodCursusLmd());
						}
						OffreFormationDTO3 offreFormation = versionDiplome.getOffreFormation();
						if (offreFormation != null) {

							if (offreFormation.getCodFinalite() != null) {
								etpins.setCodFinalite(offreFormation.getCodFinalite());
							}
							if (offreFormation.getLibFinalite() != null) {
								etpins.setLibFinalite(offreFormation.getLibFinalite());
							}

							// Recup volume horaire si saisi
							Integer volumeHoraire = offreFormation.getListEtape().getItem().get(0).getListVersionEtape()
									.getItem().get(0).getVolume();
							if (volumeHoraire != null) {
								etpins.setVolumeHoraire(Integer.toString(volumeHoraire));
							} else {
								etpins.setVolumeHoraire("0");
							}

							// Recup éléments pédagogiques
							EtapeDTO3 etape = offreFormation.getListEtape().getItem().get(0);
							VersionEtapeDTO32 versionEtape = etape.getListVersionEtape().getItem().get(0);
							List<ListeElementPedagogiDTO2> listeelementPedagogiDTO = versionEtape
									.getListListeElementPedagogi().getItem();

//							ListeElementPedagogiDTO2[] listeelementPedagogiDTO = offreFormation
//									.getListEtape()[0].getListVersionEtape()[0]
//											.getListListeElementPedagogi();

							if (logger.isDebugEnabled()) {
								logger.debug("- listeelementPedagogiDTO -");
								if (listeelementPedagogiDTO != null)
									logger.debug("- length " + listeelementPedagogiDTO.size());
								else
									logger.debug("- listeelementPedagogiDTO NULL ");
							}
							if (listeelementPedagogiDTO != null) {
								logger.trace(versionEtape.getLibWebVet() + " : listeelementPedagogiDTO : "
										+ listeelementPedagogiDTO.size());
								/**
								 * TOD s'assurer des resultats !!
								 */
								for (ListeElementPedagogiDTO2 element : listeelementPedagogiDTO) {

//								List<ElementPedagogiDTO22> listElementPedagogiqueDTO22 = listeelementPedagogiDTO
//										.get(0).getListElementPedagogi().getItem();
									List<ElementPedagogiDTO22> listElementPedagogiqueDTO22 = element
											.getListElementPedagogi().getItem();

									for (ElementPedagogiDTO22 elementPedagogiqueDTO22 : listElementPedagogiqueDTO22) {

										// Remplissage de la table des elements pedagogiques si elp de
										// nature stage (ou avec temoin stage actif) et non suspendu
										logger.trace("trace ELP :" + "etape :[ " + etpins.getCodeEtp() + ":"
												+ etpins.getCodVrsVet() + " ]" + elementPedagogiqueDTO22.getCodElp()
												+ " " + elementPedagogiqueDTO22.getLibNatureElp() + " \tTemStage :"
												+ elementPedagogiqueDTO22.getTemStage().equals("O"));

										// .equalsIgnoreCase("stage")
										// if ("4S6LEM16".equals(elementPedagogiqueDTO22.getCodElp()))

										if ((elementPedagogiqueDTO22.getLibNatureElp().equalsIgnoreCase("stage")
												|| elementPedagogiqueDTO22.getTemStage().equals("O"))
												&& !elementPedagogiqueDTO22.getTemSusp().equals("O")) {
											ElementPedagogique elpedago = new ElementPedagogique();
											elpedago.setCodEtp(codeEtp);
											elpedago.setCodVrsVet(versionEtp);

											elpedago.setLibNatureElp(elementPedagogiqueDTO22.getLibNatureElp());
											elpedago.setCodElp(elementPedagogiqueDTO22.getCodElp());
											elpedago.setLibElp(elementPedagogiqueDTO22.getLibElp());
											elpedago.setTemElpTypeStage(elementPedagogiqueDTO22.getTemStage());
											if (elementPedagogiqueDTO22.getCredits() != null) {
												elpedago.setNbrCrdElp(elementPedagogiqueDTO22.getCredits());
											}

											// renseignement de la liste des elements pedagogiques
											if (!listeELPs.contains(elpedago)) {
												listeELPs.add(elpedago);
											}

											// }
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {

		}
		// elementPedagogiques.put(etpins.getCodeEtp() + "", elpedago);
		return listeELPs;

	}

	/**
	 * tester le chargement d'une EtapeInscription à partir de l'offre la methodes
	 * principale tire certains infos
	 * 
	 * @param codEape
	 * @param versEtape
	 * @return EtapeInscription
	 */
	public EtapeInscription recupererEtapeInscription(String codEape, String versEtape) {
		List<DiplomeDTO3> diplomeDTOs = recupererListDiplomeDTO3(codEape, versEtape);
		EtapeInscription etpins = new EtapeInscription();
		if (diplomeDTOs != null) {
			diplomeDTOs.forEach((diplomeDTO) -> {
				TableauVersionDiplomeDTO3 versionDiplomes = diplomeDTO.getListVersionDiplome();
				if (versionDiplomes != null && versionDiplomes.getItem() != null) {

					versionDiplomes.getItem().forEach(versionDiplome -> {

						OffreFormationDTO3 offreFormation = versionDiplome.getOffreFormation();
						if (offreFormation != null) {

							if (offreFormation.getCodFinalite() != null) {
								etpins.setCodFinalite(offreFormation.getCodFinalite());
							}
							if (offreFormation.getLibFinalite() != null) {
								etpins.setLibFinalite(offreFormation.getLibFinalite());
							}

							// Recup volume horaire si saisi
							Integer volumeHoraire = offreFormation.getListEtape().getItem().get(0).getListVersionEtape()
									.getItem().get(0).getVolume();
							if (volumeHoraire != null) {
								etpins.setVolumeHoraire(Integer.toString(volumeHoraire));
							} else {
								etpins.setVolumeHoraire("0");
							}

							logger.trace(
									"nbr de versions d'étapes : " + offreFormation.getListEtape().getItem().size());
							logger.trace("nbr de versions d'étapes : " + offreFormation.getListEtape().getItem().get(0)
									.getListVersionEtape().getItem().size());
							logger.trace("nbr de versions d'étapes : "
									+ offreFormation.getListEtape().getItem().get(0).getCodEtp());

							etpins.setTypeIns(DonneesStatic.TYPE_INS_ADMIN);

							EtapeDTO3 etape = offreFormation.getListEtape().getItem().get(0);
							etpins.setCodeEtp(etape.getCodEtp());

							VersionEtapeDTO32 vers = offreFormation.getListEtape().getItem().get(0)
									.getListVersionEtape().getItem().get(0);

							etpins.setCodVrsVet(String.valueOf(vers.getCodVrsVet()));
							etpins.setLibWebVet(vers.getLibWebVet());
							ComposanteCentreGestionDTO centreGestion = etape.getListComposanteCentreGestion().getItem()
									.get(0);
							etpins.setCodeComposante(centreGestion.getCodComposante());
							etpins.setLibComposante(centreGestion.getLibComposante());

							String codeDiplome = diplomeDTO.getCodDip();
							etpins.setCodeDiplome(codeDiplome);
							etpins.setLibDiplome(diplomeDTO.getLibDip());
							String versDiplome = String
									.valueOf(diplomeDTO.getListVersionDiplome().getItem().get(0).getCodVrsVdi());
							etpins.setVersionDiplome(versDiplome);

						}

					});
				}

			});
		}
		return etpins;

	}

	/**
	 * recherche des Inscriptions Administratives et Inscriptions Pedagogiques.
	 * 
	 * @param codEtud
	 * @param annee
	 * @return ApogeeMap
	 */
	public ApogeeMap recupererIaIpParEtudiantAnnee(String codEtud, String annee) {
		if (logger.isDebugEnabled()) {
			logger.debug("#getStudentIAIP# - [cod : " + codEtud + ", annee : " + annee + "]");
		}
		ApogeeMap apogeeMap = new ApogeeMap();

		// Recuperation des etapes auxquelles l'etudiant est inscrit administrativement
		// (inscription admin etape en cours (E)) en fonction de l'annee en param
		List<InsAdmEtpDTO3> tabInsAdmEtp = recupererIAEtapesV3(codEtud, annee);
		/**
		 * TODO FC
		 */
		List<RegimeInscription> regimeInscription = etudiantMetierClient.regimeInscriptionEtudiant(codEtud, annee);
		apogeeMap.setRegimeInscription(regimeInscription);

		LinkedHashMap<String, String> lEtape = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> lEtapeVet = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> lEtapeVetPedago = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> lComposante = new LinkedHashMap<String, String>();
		// Liste des etapes a l'inscription administrative
		List<EtapeInscription> listeEtapeInscriptions = new ArrayList<EtapeInscription>();
		// Pour les Elps
		LinkedHashMap<String, ElementPedagogique> elementPedagogiques = new LinkedHashMap<String, ElementPedagogique>();
		List<ElementPedagogique> listeELPs = new ArrayList<ElementPedagogique>();
		logger.debug("nbr Inscriptions Administratives : " + tabInsAdmEtp.size());
		// recherche des Inscriptions Administratives payees de l'etudiant
		for (InsAdmEtpDTO3 insAdmEtp : tabInsAdmEtp) {
			if (logger.isDebugEnabled()) {
				logger.debug("- Inscription Administrative -");
				logger.debug("[codeEtape : " + insAdmEtp.getEtape().getCodeEtp() + ", codeVersionEtape : "
						+ insAdmEtp.getEtape().getVersionEtp() + ", libEtape : " + insAdmEtp.getEtape().getLibWebVet()
						+ ", codeComposante : " + insAdmEtp.getComposante().getCodComposante() + ", libComposante : "
						+ insAdmEtp.getComposante().getLibComposante() + ", codeInscriptionPayee : "
						+ insAdmEtp.getCodeInscriptionPayee() + "]");

			}
			if (logger.isTraceEnabled()) {
				logger.trace(insAdmEtp.getAnneeIAE() + " : " + insAdmEtp.getEtape().getCodeEtp() + " :"
						+ insAdmEtp.getEtatIaa().getLibEtatIAA());
				logger.trace("" + insAdmEtp.getProfilEtudiant().getCode());
				logger.trace("" + insAdmEtp.getCge().getLibCGE());
			}

			if (insAdmEtp.getCodeInscriptionPayee().equals("P")) {

				// **********************
				// POUR LES COMPOSANTES
				// **********************
				String idlComp = insAdmEtp.getComposante().getCodComposante();
				String libComp = insAdmEtp.getComposante().getLibComposante();
				if (libComp == null) {
					// ComposanteDTO3[] composante;
					try {
						ComposanteDTO3 composante = recupererComposanteV2(idlComp);
						if (composante != null) {
							libComp = composante.getLibCmp();
						}
					} catch (Exception e) {
						logger.warn("Exception recupererComposante_v2 : " + e);
						continue;
					}
//					catch (RemoteException re){
//						logger.error(re);
//					}
				}
				lComposante.put(idlComp + "", libComp);
				apogeeMap.getStudentStudys().put(idlComp + "", libComp);

				// **********************
				// POUR LES ETAPES
				// **********************
				String idl = insAdmEtp.getEtape().getCodeEtp();

				String lib = insAdmEtp.getEtape().getLibWebVet();

				// liste Etape
				lEtape.put(idl + "", lib);

				// liste etape-versionEtape
				String vet = insAdmEtp.getEtape().getVersionEtp();
				lEtapeVet.put(idl + "", vet);

				// renseignement de la liste des etapes- version etapes - inscriptions
				EtapeInscription etpins = new EtapeInscription();
				logger.debug(
						"TypeIns pour l'etape :  " + insAdmEtp.getEtape().getCodeEtp() + " :: " + etpins.getTypeIns());
				// logger.debug("TypeIns pour l'etape : "+ insAdmEtp.getEtape().getCodeEtp() +"
				// :: " + etpins.get);

				recupererEtapeInscription(insAdmEtp.getEtape().getCodeEtp(), insAdmEtp.getEtape().getVersionEtp());

				etpins.setCodeEtp(insAdmEtp.getEtape().getCodeEtp());
				etpins.setCodVrsVet(insAdmEtp.getEtape().getVersionEtp());
				etpins.setLibWebVet(insAdmEtp.getEtape().getLibWebVet());
				etpins.setCodeComposante(insAdmEtp.getComposante().getCodComposante());
				etpins.setLibComposante(insAdmEtp.getComposante().getLibComposante());
				etpins.setTypeIns(DonneesStatic.TYPE_INS_ADMIN);
				
				if(insAdmEtp.getCursusAmg()!=null) {
					etpins.setCodeCursusAmenage(insAdmEtp.getCursusAmg().getCodCurAmg());
					etpins.setCodeCursusAmenage(insAdmEtp.getCursusAmg().getLibCurAmg());
				}

				if (insAdmEtp.getDiplome() != null) {
					// renseignement des infos du diplome
					String codeDiplome = insAdmEtp.getDiplome().getCodeDiplome();
					etpins.setCodeDiplome(codeDiplome);
					etpins.setLibDiplome(insAdmEtp.getDiplome().getLibLongDiplome());
					String versDiplome = insAdmEtp.getDiplome().getVersionDiplome();
					etpins.setVersionDiplome(versDiplome);
					List<ElementPedagogique> ll = recupererListeElpsStageParEtape(etpins.getCodeEtp(),
							etpins.getCodVrsVet());

					try {

						List<DiplomeDTO3> diplomeDTOs = recupererListDiplomeDTO3(insAdmEtp.getEtape().getCodeEtp(),
								insAdmEtp.getEtape().getVersionEtp());
						if (diplomeDTOs != null && !diplomeDTOs.isEmpty()) {
							for (DiplomeDTO3 diplomeDTO : diplomeDTOs) {
								TableauVersionDiplomeDTO3 versionDiplomes = diplomeDTO.getListVersionDiplome();
								for (VersionDiplomeDTO3 versionDiplome : versionDiplomes.getItem()) {
									if (versionDiplome != null) {
										etpins.setCodCursusLmd(versionDiplome.getCodCursusLmd());
									}
									OffreFormationDTO3 offreFormation = versionDiplome.getOffreFormation();
									if (offreFormation != null) {

										if (offreFormation.getCodFinalite() != null) {
											etpins.setCodFinalite(offreFormation.getCodFinalite());
										}
										if (offreFormation.getLibFinalite() != null) {
											etpins.setLibFinalite(offreFormation.getLibFinalite());
										}

										// Recup volume horaire si saisi
										Integer volumeHoraire = offreFormation.getListEtape().getItem().get(0)
												.getListVersionEtape().getItem().get(0).getVolume();
										if (volumeHoraire != null) {
											etpins.setVolumeHoraire(Integer.toString(volumeHoraire));
										} else {
											etpins.setVolumeHoraire("0");
										}

										// Recup éléments pédagogiques
										EtapeDTO3 etape = offreFormation.getListEtape().getItem().get(0);
										VersionEtapeDTO32 versionEtape = etape.getListVersionEtape().getItem().get(0);
										List<ListeElementPedagogiDTO2> listeelementPedagogiDTO = versionEtape
												.getListListeElementPedagogi().getItem();

//										ListeElementPedagogiDTO2[] listeelementPedagogiDTO = offreFormation
//												.getListEtape()[0].getListVersionEtape()[0]
//														.getListListeElementPedagogi();

										if (logger.isDebugEnabled()) {
											logger.debug("- listeelementPedagogiDTO -");
											if (listeelementPedagogiDTO != null)
												logger.debug("- length " + listeelementPedagogiDTO.size());
											else
												logger.debug("- listeelementPedagogiDTO NULL ");
										}
										if (listeelementPedagogiDTO != null) {
											logger.trace(versionEtape.getLibWebVet() + " : listeelementPedagogiDTO : "
													+ listeelementPedagogiDTO.size());
											/**
											 * TOD s'assurer des resultats !!
											 */
											for (ListeElementPedagogiDTO2 element : listeelementPedagogiDTO) {

//											List<ElementPedagogiDTO22> listElementPedagogiqueDTO22 = listeelementPedagogiDTO
//													.get(0).getListElementPedagogi().getItem();
												List<ElementPedagogiDTO22> listElementPedagogiqueDTO22 = element
														.getListElementPedagogi().getItem();

												for (ElementPedagogiDTO22 elementPedagogiqueDTO22 : listElementPedagogiqueDTO22) {

													// Remplissage de la table des elements pedagogiques si elp de
													// nature stage (ou avec temoin stage actif) et non suspendu
													logger.trace("trace ELP :" + "etape :[ " + etpins.getCodeEtp() + ":"
															+ etpins.getCodVrsVet() + " ]"
															+ elementPedagogiqueDTO22.getCodElp() + " "
															+ elementPedagogiqueDTO22.getLibNatureElp()
															+ " \tTemStage :"
															+ elementPedagogiqueDTO22.getTemStage().equals("O"));

													// .equalsIgnoreCase("stage")
													// if ("4S6LEM16".equals(elementPedagogiqueDTO22.getCodElp()))

													if ((elementPedagogiqueDTO22.getLibNatureElp()
															.equalsIgnoreCase("stage")
															|| elementPedagogiqueDTO22.getTemStage().equals("O"))
															&& !elementPedagogiqueDTO22.getTemSusp().equals("O")) {
														ElementPedagogique elpedago = new ElementPedagogique();
														elpedago.setCodEtp(etpins.getCodeEtp());
														elpedago.setCodVrsVet(etpins.getCodVrsVet());

														elpedago.setLibNatureElp(
																elementPedagogiqueDTO22.getLibNatureElp());
														elpedago.setCodElp(elementPedagogiqueDTO22.getCodElp());
														elpedago.setLibElp(elementPedagogiqueDTO22.getLibElp());
														elpedago.setTemElpTypeStage(
																elementPedagogiqueDTO22.getTemStage());
														if (elementPedagogiqueDTO22.getCredits() != null) {
															elpedago.setNbrCrdElp(elementPedagogiqueDTO22.getCredits());
														}
														// renseignement de la map element pedagogique
														elementPedagogiques.put(etpins.getCodeEtp() + "", elpedago);
														// renseignement de la liste des elements pedagogiques
														if (!listeELPs.contains(elpedago)) {
															listeELPs.add(elpedago);
														}

														// }
													}
												}
											}
										}
									}
								}
							}
						}
					} catch (Exception e) {
						logger.warn("Exception recupererSE_v3 : " + e);
						if (e.toString().equals("technical.data.nullretrieve.recupererse")) {
							logger.warn("Aucune donnee en sortie pour " + codEtud + " - diplome/vers : " + codeDiplome
									+ versDiplome);
							continue;
						}
						continue;
					}

				}
				listeEtapeInscriptions.add(etpins);
			}
		}

		apogeeMap.setStudentSteps(lEtape);
		apogeeMap.setStudentsEtapesVets(lEtapeVet);
		apogeeMap.setStudentsEtapesVetsPedago(lEtapeVetPedago);
		apogeeMap.setListeEtapeInscriptions(listeEtapeInscriptions);
		// apogeeMap.setStudentStudys(lComposante);
		apogeeMap.setElementPedagogiques(elementPedagogiques);
		apogeeMap.setListeELPs(listeELPs);

		if (logger.isDebugEnabled()) {
			logger.debug("- ApogeeMap -");
			logger.debug("[StudentSteps : " + lEtape + ", StudentsEtapesVets : " + lEtapeVet
					+ ", StudentsEtapesVetsPedago : " + lEtapeVetPedago + ", ListeEtapeInscriptions : "
					+ listeEtapeInscriptions + ", StudentStudys : " + lComposante + "]");
		}

		return apogeeMap;
	}

	/**
	 * @return String Year
	 */
	public String getYear() {
		String year = "";
		Date d = new Date();
		DateFormat formatY = new SimpleDateFormat("yyyy");
		DateFormat formatM = new SimpleDateFormat("MM");
		DateFormat formatD = new SimpleDateFormat("dd");
		int month = Integer.parseInt(formatM.format(d));
		int day = Integer.parseInt(formatD.format(d));
		try {
			int sYM = Integer.parseInt(this.startYearMonth);
			int sYD = Integer.parseInt(this.startYearDay);
			if (sYM < month || (sYM == month && sYD <= day)) {
				year = formatY.format(d);
			} else {
				year = Integer.parseInt(formatY.format(d)) - 1 + "";
			}
		} catch (NumberFormatException e) {
			logger.error("Les valeurs de start.year.day et de start.year.month doivent etre des entiers", e);
			return null;
		}
		return year.trim();
	}

	/**
	 * Getters/Setters
	 */

	/**
	 * @return the startYearDay
	 */
	public String getStartYearDay() {
		return startYearDay;
	}

	/**
	 * @param startYearDay the startYearDay to set
	 */
	public void setStartYearDay(String startYearDay) {
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
	public void setStartYearMonth(String startYearMonth) {
		this.startYearMonth = startYearMonth;
	}

	/**
	 * @return the temoinRecupAnnu
	 */
	public String getTemoinRecupAnnu() {
		return temoinRecupAnnu;
	}

	/**
	 * @param temoinRecupAnnu the temoinRecupAnnu to set
	 */
	public void setTemoinRecupAnnu(String temoinRecupAnnu) {
		this.temoinRecupAnnu = temoinRecupAnnu;
	}

	/**
	 * @return the ldapStudentIdIsCODETU
	 */
	public boolean isLdapStudentIdIsCODETU() {
		return ldapStudentIdIsCODETU;
	}

	/**
	 * @param ldapStudentIdIsCODETU the ldapStudentIdIsCODETU to set
	 */
	public void setLdapStudentIdIsCODETU(boolean ldapStudentIdIsCODETU) {
		this.ldapStudentIdIsCODETU = ldapStudentIdIsCODETU;
	}

	/**
	 * @return the ldapUserService
	 */

	public String getCodesRegimeInscriptionFC() {
		return codesRegimeInscriptionFC;
	}

	public void setCodesRegimeInscriptionFC(String codesRegimeInscriptionFC) {
		this.codesRegimeInscriptionFC = codesRegimeInscriptionFC;
	}

	@Override
	public EtudiantRef getEtudiantRef(String universityCode, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EtudiantRef getEtudiantRefByNum(String universityCode, String id, String annee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EtudiantRef> getEtudiantsRefByName(String universityCode, String name, String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedHashMap<VersionDiplomeDTO3, List<ListeElementPedagogiDTO2>> recupererElpsParEtape(String codeEtp,
			String versionEtp) {
		// TODO Auto-generated method stub
		return null;
	}

}
