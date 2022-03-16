package org.esupportail.referentiel.utils;

/**
 * @author dhouillo
 *
 */
public class DonneesStatic {
	/**
	 * valeur pour Centre Gestion ETABLISSEMENT.
	 */
	public static final String CG_ETAB = "ETABLISSEMENT";
	/**
	 * valeur pour Centre Gestion ENTREPRISE.
	 */
	public static final String CG_ENTREPRISE = "ENTREPRISE";
	/**
	 * valeur pour Centre Gestion ETAPE.
	 */
	public static final String CG_ETAPE = "ETAPE";
	/**
	 * valeur pour Centre Gestion UFR.
	 */
	public static final String CG_UFR = "UFR";
	/**
	 * valeur pour le Critere de Gestion MIXTE.
	 */
	public static final String CG_MIXTE = "MIXTE";

	/**
	 * valeur pour la confidentialité nulle
	 */
	public static final String CODE_CONFIDENTIALITE_NULLE = "0";
	/**
	 * valeur pour la confidentialité totale
	 */
	public static final String CODE_CONFIDENTIALITE_TOTALE = "1";
	/**
	 * valeur pour la confidentialité libre
	 */
	public static final String CODE_CONFIDENTIALITE_LIBRE = "2";

	/**
	 * valeur pour les droits Admin
	 */
	public static final String LIBELLE_DROIT_ADMIN = "ADMIN";
	
	/**
	 * valeur pour les droits en écriture
	 */
	public static final String LIBELLE_DROIT_ECRITURE = "ECRITURE";
	
	/**
	 * valeur pour les droits en lecture
	 */
	public static final String LIBELLE_DROIT_LECTURE = "LECTURE";

	/**
	 * valeur pour les droits en écriture avant validation pédagogique
	 */
	public static final String LIBELLE_DROIT_ECRITURE_AVANT_VALP = "ECRITURE AVANT VALP";

	/**
	 * valeur pour les droits en écriture sans validation de convention
	 */
	public static final String LIBELLE_DROIT_ECRITURE_SANS_VALIDATION = "ECRITURE SANS VALIDATION";
	
	/**
	 * valeur de la civilite Monsieur dans le ldap
	 */
	public static final String CIVILITE_MR_LDAP = "M.";
	/**
	 * valeur de la civilite Madame dans le ldap
	 */
	public static final String CIVILITE_MME_LDAP = "Mme";
	/**
	 * valeur de la civilite Madame dans le ldap
	 */
	public static final String CIVILITE_MLLE_LDAP = "Mlle";
	/**
	 * id de la civilite Mademoiselle dans la base
	 */
	public static final int ID_CIVILITE_MLLE = 1;	
	/**
	 * id de la civilite Madame dans la base
	 */
	public static final int ID_CIVILITE_MME = 2;
	/**
	 * id de la civilite Monsieur dans la base
	 */
	public static final int ID_CIVILITE_MR = 3;
	/**
	 * id de la civilite Vide dans la base
	 */
	public static final int ID_CIVILITE_VIDE = 4;

	
	/*
	 * Contrôles Données Apogée
	 */
	/**
	 * source du resultat Apogee.
	 */
	public static final String ws_sourceRes_Apogee = "Apogee";	
	/**
	 * valeur du COD_VAP  adresse 2 EtabRef dans APOGEE pour Table VARIABLE_APPLI.
	 */
	public static final String COD_VAP_ETB_AD2 = "ETB_AD1";
	
	/**
	 * valeur du COD_VAP Adresse 3 EtabRef dans APOGEE pour Table VARIABLE_APPLI.
	 */
	public static final String COD_VAP_ETB_AD3 = "ETB_AD3";
	
	/**
	 * valeur du COD_VAP Commune  EtabRef dans APOGEE pour Table VARIABLE_APPLI.
	 */
	public static final String COD_VAP_ETB_COM = "ETB_COM";
	
	/**
	 * valeur du COD_VAP Code Postal  EtabRef dans APOGEE pour Table VARIABLE_APPLI.
	 */
	public static final String COD_VAP_ETB_CPO = "ETB_CPO";
	
	/**
	 * valeur du COD_VAP Nom EtabRef dans APOGEE pour Table VARIABLE_APPLI.
	 */
	public static final String COD_VAP_ETB_LIB = "ETB_LIB";
	
	/**
	 * nombre de reponse maxi au retour des requetes.
	 */
	public static final int NB_RESPONSE_MAXI = 500;

	/**
	 * code language "fr".
	 */
	public static final String LANG_FR = "fr";
	/**
	 * code language etranger.
	 */
	public static final String LANG_ETRANGER = "en";
	/**
	 * code language francophone.
	 */
	public static final String LANG_FRANCOPHONE = "fo";	
	/**
	 * pays FRANCE.
	 */
	public static final String PAYS_FR = "FRANCE";
	
	/**
	 * Type inscription administrative.
	 */
	public static final String TYPE_INS_ADMIN = "A";
	
	/**
	 * Type inscription pedagogique.
	 */
	public static final String TYPE_INS_PEDAGO = "P";
	/*
	 * FIN Contrôles Données Apogée
	 */
	/**
	 * valeur du code pour les conventions en formation initiale pour les controles de la table typeConvention.
	 */
	public static final String TYPE_CONVENTION_CODE_CTRL_FI = "FI";
	
	/**
	 * valeur du code pour les conventions en formation continue pour les controles de la table typeConvention.
	 */
	public static final String TYPE_CONVENTION_CODE_CTRL_FC = "FC";
	/*
	 * Codes controles des données TempsTravail
	 */
	/**
	 * valeur du code (travail temps complet) pour les controles de la table temps de travail.
	 */
	public static final String TEMPS_TRAVAIL_CODE_CTRL_COMPLET = "TCOMP";
	
	/**
	 * valeur du code (travail temps partiel) pour les controles de la table temps de travail.
	 */
	public static final String TEMPS_TRAVAIL_CODE_CTRL_PARTIEL = "TPART";
	/*
	 * Codes controles des données ModeCandidature
	 */
	/**
	 * Code Courrier.
	 */
	public static final String MODE_CANDIDATURE_CODE_CTRL_COUR = "COUR";
	/**
	 * Code Mail.
	 */
	public static final String MODE_CANDIDATURE_CODE_CTRL_MAIL = "MAIL";
	/**
	 * Code Téléphone.
	 */
	public static final String MODE_CANDIDATURE_CODE_CTRL_TEL = "TEL";
	/*
	 * Codes controles des données TypeOffre
	 */
	/**
	 * Code Stage.
	 */
	public static final String TYPE_OFFRE_CODE_CTRL_STAGE = "STAGE";
	/**
	 * Code Emploi.
	 */
	public static final String TYPE_OFFRE_CODE_CTRL_EMPLOI = "EMPLOI";
	/**
	 * Code Alternance.
	 */
	public static final String TYPE_OFFRE_CODE_CTRL_ALTERNANCE = "ALTERN";
	/**
	 * Code VIEVIA.
	 */
	public static final String TYPE_OFFRE_CODE_CTRL_VIEVIA = "VIEVIA";
	/*
	 * Codes controles des données ContratOffre
	 */
	/**
	 * Code CDD.
	 */
	public static final String CONTRAT_OFFRE_CODE_CTRL_CDD = "CDD";
	/**
	 * Code CDI.
	 */
	public static final String CONTRAT_OFFRE_CODE_CTRL_CDI = "CDI";
	/**
	 * Code Interim.
	 */
	public static final String CONTRAT_OFFRE_CODE_CTRL_INTERIM = "INTERIM";
	/**
	 * Code Contrat d'apprentissage.
	 */
	public static final String CONTRAT_OFFRE_CODE_CTRL_CONTRAT_APPRENTISSAGE = "CTAPPREN";
	/**
	 * Code Contrat Pro.
	 */
	public static final String CONTRAT_OFFRE_CODE_CTRL_CONTRAT_PRO = "CTPRO";
	/*
	 * Codes contrôles PAYS
	 */
	/**
	 * ISO2 de la FRANCE.
	 */
	public static final String FRANCE_ISO2 = "FR";
	/**
	 * COG de la FRANCE.
	 */
	public static final int FRANCE_COG = 99100;
	/**
	 * CRPAY des territoires de la FRANCE.
	 */
	public static final int FRANCE_TERRITOIRE_CRPAY = 99100;

	/**
	 * valeur oui.
	 */
	public static final String OUI = "Oui";
	
	/** for file pdf.*/
	public static final String HTTP_TYPE_PDF = "application/pdf";
	
	/**
	 * nombre de reponse maxi au retour recherche etudiant ldap.
	 */
	public static final int NB_RESPONSE_ETU_LDAP_MAXI = 20;
	
	/**
	 * valeur du temoin en Service.
	 */
	public static final String TEM_EN_SVE_O = "O";
	
	/*
	 * Codes controles des données Assurance (affiliation SS)
	 */
	/**
	 * AYANTDROIT.
	 */
	public static final String ASS_CODE_CTRL_AYANTDROIT = "ASSAYANTDROIT";
	
	/**
	 * ETUDIANT.
	 */
	public static final String ASS_CODE_CTRL_ETUDIANT = "ASSETUDIANT";
	
	/**
	 * VOLONTAIRE.
	 */
	public static final String ASS_CODE_CTRL_VOLONTAIRE = "ASSVOLONTAIRE";
	
	/**
	 * ETRANGER.
	 */
	public static final String ASS_CODE_CTRL_ETRANGER = "ASSETRANGER";
	
	/**
	 * nombre de reponse maxi pour export
	 */
	private static int nb_response_export_maxi;
	
	/**
	 * nbRechercheMaxi
	 */
	private static int nb_recherche_maxi;
	
	/**
	 * constructeur de donnees statiques.
	 */
	public DonneesStatic() {
		// pour donnee static
		
	}

	public static int getNb_response_export_maxi() {
		return nb_response_export_maxi;
	}

	public void setNb_response_export_maxi(int nb_response_export_maxi) {
		DonneesStatic.nb_response_export_maxi = nb_response_export_maxi;
	}

	public static int getNb_recherche_maxi() {
		return nb_recherche_maxi;
	}

	public void setNb_recherche_maxi(int nb_recherche_maxi) {
		DonneesStatic.nb_recherche_maxi = nb_recherche_maxi;
	}

}
