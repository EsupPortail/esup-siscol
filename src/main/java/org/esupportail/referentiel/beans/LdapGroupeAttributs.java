package org.esupportail.referentiel.beans;
/**
 * Composition de filtres, motifs sur les recherches des composantes de l'annuaire.
 *Acces aux attributs ldap 
 */
public interface LdapGroupeAttributs {

	public static String   sperateurValeurLdap = ",";
	/**
	 * @return the motif
	 */
	public String getMotif();
	public String getLdapComposanteCode();
	public String getLdapComposanteLibelle();
	
	/**
	 * element representant le code d'afectation d'un etudiant.
	 * @return
	 */
	public String getLdapStudentAffectation() ;
	/**
	 * Code  ldap  representatant les principales formations de l'etablissement
	 * @return 
	 */
	public String getLdapCodePrincipalesFormations();
	/**
	 * 
	 * @return les valeurs du LdapCodePrincipalesFormations  separees  par des virgules 
	 */
	 
	public String getLdapValCodeFormationsPrincipales();
}