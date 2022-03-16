package org.esupportail.referentiel.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Danielle Martineau : danielle.martineau@univ-rennes1.fr
 *
 */
public class EtudiantRef implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	/*
	 * *************************************************************** Proprietes
	 ****************************************************************/
	private Integer cod_ind = null;
	private String nompatro = "";
	private String nommarital = "";
	private String prenom = "";
	private String mail = "";

	// adresse permanente etudiant
	private String mainAddress = "";
	// code postal etudiant
	private String postalCode = "";
	// commune etudiant
	private String town = "";
	// pays etudiant
	private String country = "";
	// telephone etudiant
	private String phone = "";
	// telephone portable etudiant
	private String portablePhone = "";
	// courrier personnel etudiant
	private String mailPerso = "";
	// code pays
	private String codePays = "";
	// libelle adresse france
	private String libAd1 = "";
	private String libAd2 = "";
	private String libAd3 = "";
	// libelle adresse etrangere
	private String libAde = "";
	// code sexe etudiant
	private String codeSexe = "";
	
	// date Naissance etudiant
	@JsonFormat(shape = JsonFormat.Shape.STRING, locale = "fr_FR",timezone = "Europe/Paris")
	private Date dateNais = new Date();
	// libelle CPAM etudiant
	private String libelleCPAM = "";

	/**
	 * theUfr
	 */
	private String theUfr = "";
	/**
	 * thecodeUFR
	 */
	private String thecodeUFR = "";
	/**
	 * theEtape
	 */
	private String theEtape = "";
	/**
	 * theCodeEtape
	 */
	private String theCodeEtape = "";
	/**
	 * theCodeVersionEtape
	 */
	private String theCodeVersionEtape = "";

	/**
	 * Map element pedagogique
	 */
	private Map<String, ElementPedagogique> elementPedagogiques = new HashMap<String, ElementPedagogique>();

	/**
	 * liste des Elps.
	 */
	private List<ElementPedagogique> listeELPs = new ArrayList<ElementPedagogique>();

	/**
	 * le code Elp
	 */
	private String theCodeElp = "";
	/**
	 * le libelle de Elp
	 */
	private String theLibElp = "";
	/**
	 * le nombre de Credit ECTS
	 */
	private BigDecimal theCreditECTS = new BigDecimal(0);

	/**
	 * steps (etape etude)
	 */
	private Map<String, String> steps = new HashMap<String, String>();
	/**
	 * studys (ufr)
	 */
	private Map<String, String> studys = new HashMap<String, String>();

	/**
	 * liste etape - version etape inscriptions
	 */
	private List<EtapeInscription> listeEtapeInscriptions;

	/**
	 * Affiliation a la securite sociale
	 */
	private String theAssurance;

	/**
	 * Caisse d'assurance maladie
	 */
	private String theCaisseRegime;

	/**
	 * administrationApogee
	 */
	private AdministrationApogee administrationApogee;

	/**
	 * liste des annees d'inscription disponibles
	 */
	private List<String> listeAnneesUniv;

	/**
	 * volumeHoraire de l'etape d'inscription choisie
	 */
	private String volumeHoraireFormation = "";

	/**
	 * Liste des eventuelles années d'inscription en FC pour l'etudiant
	 */
	private List<String> anneesInscriptionFC;

	/**
	 * Constructeur
	 */
	public EtudiantRef() {
		super();
	}

	/*
	 * *************************************************************** Methodes
	 ****************************************************************/

	/**
	 * @return List<String>
	 */
	public List<String> getStepsKey() {
		List<String> listeStepsKey = new ArrayList<String>();
		if (this.steps != null && (!this.steps.isEmpty())) {
			String clef = null;
			Iterator<String> i = this.steps.keySet().iterator();
			while (i.hasNext()) {
				clef = (String) i.next();
				if (clef != null) {
					listeStepsKey.add(clef);

				}

			}
		}

		return listeStepsKey;
	}

	/**
	 * @return List<String>
	 */
	public List<String> getStudysKey() {
		List<String> listeStudysKey = new ArrayList<String>();
		if (this.studys != null && (!this.studys.isEmpty())) {
			String clef = null;
			Iterator<String> i = this.studys.keySet().iterator();
			while (i.hasNext()) {
				clef = (String) i.next();
				if (clef != null) {
					listeStudysKey.add(clef);

				}
			}
		}
		return listeStudysKey;
	}

	/*
	 * *************************************************************** Getters /
	 * Setters
	 ****************************************************************/
	
	

	/**
	 * @return the elementPedagogiques
	 */
	public Map<String, ElementPedagogique> getElementPedagogiques() {
		return elementPedagogiques;
	}

	public Integer getCod_ind() {
		return cod_ind;
	}

	public void setCod_ind(Integer cod_ind) {
		this.cod_ind = cod_ind;
	}

	public String getNompatro() {
		return nompatro;
	}

	public void setNompatro(String nompatro) {
		this.nompatro = nompatro;
	}

	public String getNommarital() {
		return nommarital;
	}

	public void setNommarital(String nommarital) {
		this.nommarital = nommarital;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCodePays() {
		return codePays;
	}

	public void setCodePays(String codePays) {
		this.codePays = codePays;
	}

	public String getLibAd1() {
		return libAd1;
	}

	public void setLibAd1(String libAd1) {
		this.libAd1 = libAd1;
	}

	public String getLibAd2() {
		return libAd2;
	}

	public void setLibAd2(String libAd2) {
		this.libAd2 = libAd2;
	}

	public String getLibAd3() {
		return libAd3;
	}

	public void setLibAd3(String libAd3) {
		this.libAd3 = libAd3;
	}

	public String getLibAde() {
		return libAde;
	}

	public void setLibAde(String libAde) {
		this.libAde = libAde;
	}

	public String getCodeSexe() {
		return codeSexe;
	}

	public void setCodeSexe(String codeSexe) {
		this.codeSexe = codeSexe;
	}

	public Date getDateNais() {
		return dateNais;
	}

	public void setDateNais(Date dateNais) {
		this.dateNais = dateNais;
	}

	/**
	 * @param elementPedagogiques the elementPedagogiques to set
	 */
	public void setElementPedagogiques(Map<String, ElementPedagogique> elementPedagogiques) {
		this.elementPedagogiques = elementPedagogiques;
	}

	/**
	 * @return the listeELPs
	 */
	public List<ElementPedagogique> getListeELPs() {
		return listeELPs;
	}

	/**
	 * @param listeELPs the listeELPs to set
	 */
	public void setListeELPs(List<ElementPedagogique> listeELPs) {
		this.listeELPs = listeELPs;
	}

	/**
	 * @return the theCodeElp
	 */
	public String getTheCodeElp() {
		return theCodeElp;
	}

	/**
	 * @param theCodeElp the theCodeElp to set
	 */
	public void setTheCodeElp(String theCodeElp) {
		this.theCodeElp = theCodeElp;
	}

	/**
	 * @return the theLibElp
	 */
	public String getTheLibElp() {
		return theLibElp;
	}

	/**
	 * @param theLibElp the theLibElp to set
	 */
	public void setTheLibElp(String theLibElp) {
		this.theLibElp = theLibElp;
	}

	/**
	 * @return the theCreditECTS
	 */
	public BigDecimal getTheCreditECTS() {
		return theCreditECTS;
	}

	/**
	 * @param theCreditECTS the theCreditECTS to set
	 */
	public void setTheCreditECTS(BigDecimal theCreditECTS) {
		this.theCreditECTS = theCreditECTS;
	}

	/**
	 * @return the libelleCPAM
	 */
	public String getLibelleCPAM() {
		return libelleCPAM;
	}

	/**
	 * @param libelleCPAM the libelleCPAM to set
	 */
	public void setLibelleCPAM(String libelleCPAM) {
		this.libelleCPAM = libelleCPAM;
	}

	/**
	 * @return the steps
	 */
	public Map<String, String> getSteps() {
		return steps;
	}

	/**
	 * @param steps the steps to set
	 */
	public void setSteps(Map<String, String> steps) {
		this.steps = steps;
	}

	/**
	 * @return the studys
	 */
	public Map<String, String> getStudys() {
		return studys;
	}

	/**
	 * @param studys the studys to set
	 */
	public void setStudys(Map<String, String> studys) {
		this.studys = studys;
	}

	/**
	 * @return the mainAddress
	 */
	public String getMainAddress() {
		return mainAddress;
	}

	/**
	 * @param mainAddress the mainAddress to set
	 */
	public void setMainAddress(String mainAddress) {
		this.mainAddress = mainAddress;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the town
	 */
	public String getTown() {
		return town;
	}

	/**
	 * @param town the town to set
	 */
	public void setTown(String town) {
		this.town = town;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the portablePhone
	 */
	public String getPortablePhone() {
		return portablePhone;
	}

	/**
	 * @param portablePhone the portablePhone to set
	 */
	public void setPortablePhone(String portablePhone) {
		this.portablePhone = portablePhone;
	}

	/**
	 * @return the mailPerso
	 */
	public String getMailPerso() {
		return mailPerso;
	}

	/**
	 * @param mailPerso the mailPerso to set
	 */
	public void setMailPerso(String mailPerso) {
		this.mailPerso = mailPerso;
	}

	/**
	 * @return the theUfr
	 */
	public String getTheUfr() {
		return theUfr;
	}

	/**
	 * @param theUfr the theUfr to set
	 */
	public void setTheUfr(String theUfr) {
		this.theUfr = theUfr;
	}

	/**
	 * @return the thecodeUFR
	 */
	public String getThecodeUFR() {
		return thecodeUFR;
	}

	/**
	 * @param thecodeUFR the thecodeUFR to set
	 */
	public void setThecodeUFR(String thecodeUFR) {
		this.thecodeUFR = thecodeUFR;
	}

	/**
	 * @return the theEtape
	 */
	public String getTheEtape() {
		return theEtape;
	}

	/**
	 * @param theEtape the theEtape to set
	 */
	public void setTheEtape(String theEtape) {
		this.theEtape = theEtape;
	}

	/**
	 * @return the theCodeEtape
	 */
	public String getTheCodeEtape() {
		return theCodeEtape;
	}

	/**
	 * @param theCodeEtape the theCodeEtape to set
	 */
	public void setTheCodeEtape(String theCodeEtape) {
		this.theCodeEtape = theCodeEtape;
	}

	/**
	 * @return the listeEtapeInscriptions
	 */
	public List<EtapeInscription> getListeEtapeInscriptions() {
		return listeEtapeInscriptions;
	}

	/**
	 * @param listeEtapeInscriptions the listeEtapeInscriptions to set
	 */
	public void setListeEtapeInscriptions(List<EtapeInscription> listeEtapeInscriptions) {
		this.listeEtapeInscriptions = listeEtapeInscriptions;
	}

	/**
	 * @return the theAssurance
	 */
	public String getTheAssurance() {
		return theAssurance;
	}

	/**
	 * @param theAssurance the theAssurance to set
	 */
	public void setTheAssurance(String theAssurance) {
		this.theAssurance = theAssurance;
	}

	/**
	 * @return the theCaisseRegime
	 */
	public String getTheCaisseRegime() {
		return theCaisseRegime;
	}

	/**
	 * @param theCaisseRegime the theCaisseRegime to set
	 */
	public void setTheCaisseRegime(String theCaisseRegime) {
		this.theCaisseRegime = theCaisseRegime;
	}

	/**
	 * @return the administrationApogee
	 */
	public AdministrationApogee getAdministrationApogee() {
		return administrationApogee;
	}

	/**
	 * @param administrationApogee the administrationApogee to set
	 */
	public void setAdministrationApogee(AdministrationApogee administrationApogee) {
		this.administrationApogee = administrationApogee;
	}

	/**
	 * @return the theCodeVersionEtape
	 */
	public String getTheCodeVersionEtape() {
		return theCodeVersionEtape;
	}

	/**
	 * @param theCodeVersionEtape the theCodeVersionEtape to set
	 */
	public void setTheCodeVersionEtape(String theCodeVersionEtape) {
		this.theCodeVersionEtape = theCodeVersionEtape;
	}

	public List<String> getListeAnneesUniv() {
		return listeAnneesUniv;
	}

	public void setListeAnneesUniv(List<String> listeAnneesUniv) {
		this.listeAnneesUniv = listeAnneesUniv;
	}

	public String getVolumeHoraireFormation() {
		return volumeHoraireFormation;
	}

	public void setVolumeHoraireFormation(String volumeHoraireFormation) {
		this.volumeHoraireFormation = volumeHoraireFormation;
	}

	public List<String> getAnneesInscriptionFC() {
		return anneesInscriptionFC;
	}

	public void setAnneesInscriptionFC(List<String> anneesInscriptionFC) {
		this.anneesInscriptionFC = anneesInscriptionFC;
	}
}
