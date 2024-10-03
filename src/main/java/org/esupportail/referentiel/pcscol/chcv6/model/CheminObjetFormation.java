/*
 * CHC v6 - Choix du cursus
 * <font color='red'>***Statut***: DRAFT (brouillon/preview)</font> Ne pas utiliser cette version d'API. Elle est au statut DRAFT, donc sujette à changements sans aucune garantie de compatibilité ascendante. Liste l'ensemble des services et des opérations disponibles dans le module choix des cursus v6 ### Introduction l’API liste l'ensemble des services et des opérations disponibles dans le module CHC (Choix du Cursus). Le module CHC permet d’affecter les apprenants aux Objets maquettes qu’ils doivent suivre pour une période de mise en œuvre donnée pendant leur cursus, de leur saisir des aménagements avec différentes prises en compte et de les affecter à des groupes. ### Authentification/autorisation obligatoire Pour tout appel à une opération vous devez être authentifié/authorisé à l’aide d’un token jwt. Pour cela, chaque requête HTTP doit contenir un token valide dans le header HTTP `Authorization`. Le format est `Authorization: Bearer <token-jwt>`. Par exemple `Authorization: Bearer xxxx.yyyy.zzzz` Vous pouvez recevoir un des ces codes retours si vous n’êtes pas authentifié ou autorisé : * 401 - Unauthorized - Vous n’êtes pas authentifié     * Il n’y a pas de token passé dans le header HTTP `Authorization`     * Le token passé n’est pas au bon format (Bearer <token-jwt>) * 403 - Forbidden - Vous êtes authentifié mais pas autorisé à exécuter cette opération     * La signature du token est incorrecte / n’a pas pû être vérifiée     * Le token est expiré     * Vérifier les droits de l’utilisateur * 500 - Internal Server Error     * Il n’est pas encore actif  ### Type de données Sauf indications spécifiques données au niveau de l'opération, les types de données utilisés dans cette API sont les suivants :  * string - Chaîne de caractères encodée en UTF8 (ex : `Une chaîne de caractère`)    * Dans le cas des descripteurs de type `codeXxx`, seuls les caractères de A à Z, de 0 à 9 et le tiret(-) sont autorisés  * string($date) - Une date sous la forme d'une chaîne de caractères (ex : `2020-02-25`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))  * integer($int64) - Un entier sur 64 bits (de -9 223 372 036 854 775 808 à 9 223 372 036 854 775 807) (ex : `2542`)  * integer($int32) - Un entier sur 32 bits (de –2 147 483 648 à 2 147 483 647) (ex : `2542`)  * number($double) - un nombre à virgule flottante en double précision  * boolean - Un booléen représenté par `true` ou `false`  ### Code retour      | Code    | Description                                                                                                         |     |---------|---------------------------------------------------------------------------------------------------------------------|     | 200     | L'opération s'est déroulée avec succès                                                                              |     | 201     | L'opération a aboutie à la création d'une ressource                                                                 |     | 400     | Un ou des paramètres d'entrées sont erronées                                                                        |     |         | Une erreur fonctionnelle s'est produite                                                                             |     | 404     | La ressource demandée n'est pas trouvé                                                                              |     |         | Remarque : Dans le cas des opérations retournant une liste, on recevra un code 200 avec en résultat une liste vide  |     | 500     | Erreur technique rencontrée par le serveur                                                                          |   ## Notions métiers ### Objet Maquette (OM) Un Objet Maquette représente n'importe quel nœud d'un arbre de Formation: Formation, Objet de Formation ou Groupement. Un objet Maquette est identifié par le codeChemin (chemin pédagogique), le codePeriode et le codeStructure  ### Formation Une Formation est un arbre dont les nœuds sont des Objets de formation et dont la racine est la Formation elle-même. Pour apparaître dans le Module CHC, la formation doit être mise en œuvre, actualisée, ouverte à l’inscription et ouverte au CHC dans MOF. Il est également nécessaire qu’il y ait au moins une inscription valide sur un objet maquette de l’arbre de la formation. ### Objet formation Un objet de formation est l’un des nœuds de l’arbre de formation : année, semestre, UE, EC, enseignement, etc.(hors groupement). Pour apparaître dans le Module CHC, un objet de formation doit être ouvert au CHC dans MOF. ### Groupement Un groupement est une possibilité de structurer et d'organiser une formation.Il peut contenir des objets de formations de tous les types, être lié pour décomposer des objets pères de tous les types, être avec ou sans plage de choix. ### Plage de choix Une plage de choix permet de contraindre l’apprenant lorsque  qu’il effectue son CHC dans Pégase. Cette plage de choix est matérialisée par un nombre minimum et un nombre maximum d’objets de formation à sélectionner. La plage de choix est contrôlée au cours du CHC. ### Groupe Un Groupe est une entité permettant de diviser une population d’étudiants ou d’identifier une population spécifique d’étudiants inscrits administrativement ou pédagogiquement ### Composition Une composition est une entité permettant de rassembler des groupes. Une composition contient obligatoirement au moins un groupe.  ### Période Une période de mise en œuvre correspond à la période pendant laquelle se déroule la formation, du début des cours jusqu’à la délibération des jurys. Elle est le point d’entrée du module puisque le CHC se fait pour une période donnée. ### Apprenant Un apprenant est un usager qui suit un cursus et pour lequel le CHC va être saisi. ### Inscription L’inscription est l’ensemble des étapes de saisie des données concernant l’apprenant : état-civil, coordonnées, situation précédente, situation précédente, cursus, montant de l’inscription, mode de paiement. Cette saisie peut être faite par le gestionnaire ou l’apprenant. Elle doit être vérifiée et validée par le gestionnaire.Au 25/03/21, l’inscription doit être validée pour que l’apprenant puisse arriver dans le module CHC. ### Cursus Le cursus est l’ensemble des Objets Maquette auxquels l’apprenant va être affecté ou pour lesquels des aménagements vont être saisis, le tout pour une période donnée. Un cursus est défini par le code de l’apprenant et un objet maquette lui-même identifié par le codeChemin (chemin pédagogique), le codePeriode et le codeStructure. ### Acquis capitalisé Un acquis capitalisé est un objet de formation dont les modalités de contrôle des connaissances attendent un résultat capitalisable. Pour être identifié dans CHC comme acquis capitalisé, cet objet doit posséder un résultat positif obtenu sur une période passée, pour laquelle une délibération de jury a statué. ### Chemin pédagogique Un chemin pédagogique identifie le lien d'un Objet Maquette à un autre Objet maquette de sa descendance. **Exemple** ``` MASTER-RH>MASTER-1>SEMESTRE-1>UE-OPTIONS>ESPAGNOL ``` ### Affectation en masse (Dépréciée) L'affectation en masse permet, pour une période donnée,  d’affecter ou de désaffecter des apprenants sur un Objet Maquette ouvert au choix du cursus et éventuellement sur sa descendance obligatoire.Les affectations ne sont possibles que si le père de l'objet maquette a été affecté ou acquis => contrôle du chemin pédagogique. Les aménagements-acquis sont conservés lors de la désaffectation. ### Affectation individuelle (Dépréciée) L'affectation individuelle permet, pour une période et un apprenant donnés de saisir, modifier ou supprimer pour cet apprenant les affectations, les acquis et les aménagements aux Objets de formations souhaités. Un Objet de formation est soit affecté soit acquis : il ne peut pas être les deux en même temps. Des contrôles sont effectués pour la cohérence aménagement-acquis ou aménagement-affectation ou aménagement-aucun. Les affectations ou la saisie des aménagements ne sont possibles sur un OM que si le père a été affecté (contrôle du chemin pédagogique). ### Paramétrage Un paramétrage est une personnalisation de concepts spécifiques pour des processus métiers. Ils sont gérés dans le Référentiel ou dans chacun des modules. Ils peuvent être utilisés par les différents modules. Le Type d’aménagement est un paramétrage du module REF. ## Informations techniques Toutes les actions de cursus (affectation, désaffectation, acquis, non-acquis, type d’aménagement) de l’apprenant dans CHC seront envoyées au module COC. Toutes les actions de cursus sont en mode upsert (créer si ça n’existe pas ou modifier si ça existe). Seule la liste des types aménagements dans l’entrée sera remplacée par ses anciennes valeurs. ### Règles communes pour réaliser un choix de cursus * L’affectation peut seulement se faire s’il y a une inscription valide sur l’objet maquette ou un des objets maquette de son ascendance. Les statuts de l’inscription proviennent du module INS. * Un CHC sur un Objet Maquette peut se faire uniquement si cet objet Maquette a le témoin ouvertChoixCursus à true. * Lors de la désaffectation d’un apprenant  à un Objet Maquette, l’apprenant sera également désaffecté automatiquement à tous les Objets Maquette de sa descendance. * Un CHC sur un Objet Maquette dans un groupement à plage de choix peut être fait seulement si le nombre de CHC de l’apprenant dans ce groupement ne dépasse pas le maximum autorisé (la plageMax). On ne contrôle pas la valeur mininum de plage de choix. * L’affectation/acquis/type aménagement sur un Objet Maquette mutualisé présent plusieurs fois dans un arbre ne peut être réalisée qu’une fois, c’est-à-dire que l’Objet Maquette (avec un certain code chemin) ne peut être qu’une seule fois avec une affectation / un acquis ou un type aménagement sur le même cursus * La capacité d’accueil d’un Objet Maquette n’est pas contrôlée dans l’API car non bloquante. Cela peut donc entraîner des capacités d’accueil négatives. * Les aménagements avec prise en compte Acquis et Aucun ne sont pas décomptés de la capacité d’accueil d’un Objet Maquette. * La récupération d'un acquis capitalisé empêche son affectation et celle de sa descendance.
 *
 * The version of the OpenAPI document: 6.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.esupportail.referentiel.pcscol.chcv6.model;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import org.esupportail.referentiel.pcscol.invoker.ApiClient;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
/**
 * CheminObjetFormation
 */
@JsonPropertyOrder({
  CheminObjetFormation.JSON_PROPERTY_ID_CHEMIN_OBJET_FORMATION,
  CheminObjetFormation.JSON_PROPERTY_CODE_CHEMIN,
  CheminObjetFormation.JSON_PROPERTY_CODE_PERIODE,
  CheminObjetFormation.JSON_PROPERTY_CODE_STRUCTURE,
  CheminObjetFormation.JSON_PROPERTY_CODE_TYPE,
  CheminObjetFormation.JSON_PROPERTY_CAPACITE_ACCUEIL,
  CheminObjetFormation.JSON_PROPERTY_OBLIGATOIRE,
  CheminObjetFormation.JSON_PROPERTY_OUVERTE_CHOIX_CURSUS,
  CheminObjetFormation.JSON_PROPERTY_CREDIT_ECTS,
  CheminObjetFormation.JSON_PROPERTY_EST_PLAGE_DE_CHOIX,
  CheminObjetFormation.JSON_PROPERTY_PLAGE_MIN,
  CheminObjetFormation.JSON_PROPERTY_PLAGE_MAX,
  CheminObjetFormation.JSON_PROPERTY_FORMATION,
  CheminObjetFormation.JSON_PROPERTY_OBJET_FORMATION,
  CheminObjetFormation.JSON_PROPERTY_GROUPEMENT
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-09-26T14:04:50.284799424+02:00[Europe/Paris]", comments = "Generator version: 7.8.0")
public class CheminObjetFormation {
  public static final String JSON_PROPERTY_ID_CHEMIN_OBJET_FORMATION = "idCheminObjetFormation";
  private UUID idCheminObjetFormation;

  public static final String JSON_PROPERTY_CODE_CHEMIN = "codeChemin";
  private String codeChemin;

  public static final String JSON_PROPERTY_CODE_PERIODE = "codePeriode";
  private String codePeriode;

  public static final String JSON_PROPERTY_CODE_STRUCTURE = "codeStructure";
  private String codeStructure;

  public static final String JSON_PROPERTY_CODE_TYPE = "codeType";
  private String codeType;

  public static final String JSON_PROPERTY_CAPACITE_ACCUEIL = "capaciteAccueil";
  private Integer capaciteAccueil;

  public static final String JSON_PROPERTY_OBLIGATOIRE = "obligatoire";
  private Boolean obligatoire;

  /**
   * Gets or Sets ouverteChoixCursus
   */
  public enum OuverteChoixCursusEnum {
    OUVERT("OUVERT"),
    
    FERME("FERME"),
    
    JAMAIS("JAMAIS");

    private String value;

    OuverteChoixCursusEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static OuverteChoixCursusEnum fromValue(String value) {
      for (OuverteChoixCursusEnum b : OuverteChoixCursusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_OUVERTE_CHOIX_CURSUS = "ouverteChoixCursus";
  private OuverteChoixCursusEnum ouverteChoixCursus;

  public static final String JSON_PROPERTY_CREDIT_ECTS = "creditEcts";
  private BigDecimal creditEcts;

  public static final String JSON_PROPERTY_EST_PLAGE_DE_CHOIX = "estPlageDeChoix";
  private Boolean estPlageDeChoix;

  public static final String JSON_PROPERTY_PLAGE_MIN = "plageMin";
  private BigDecimal plageMin;

  public static final String JSON_PROPERTY_PLAGE_MAX = "plageMax";
  private BigDecimal plageMax;

  public static final String JSON_PROPERTY_FORMATION = "formation";
  private FormationMasse formation;

  public static final String JSON_PROPERTY_OBJET_FORMATION = "objetFormation";
  private ObjetFormationMasse objetFormation;

  public static final String JSON_PROPERTY_GROUPEMENT = "groupement";
  private GroupementMasse groupement;

  public CheminObjetFormation() { 
  }

  public CheminObjetFormation idCheminObjetFormation(UUID idCheminObjetFormation) {
    this.idCheminObjetFormation = idCheminObjetFormation;
    return this;
  }

  /**
   * L&#39;id technique du chemin objet formation
   * @return idCheminObjetFormation
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ID_CHEMIN_OBJET_FORMATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public UUID getIdCheminObjetFormation() {
    return idCheminObjetFormation;
  }


  @JsonProperty(JSON_PROPERTY_ID_CHEMIN_OBJET_FORMATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setIdCheminObjetFormation(UUID idCheminObjetFormation) {
    this.idCheminObjetFormation = idCheminObjetFormation;
  }


  public CheminObjetFormation codeChemin(String codeChemin) {
    this.codeChemin = codeChemin;
    return this;
  }

  /**
   * Get codeChemin
   * @return codeChemin
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CODE_CHEMIN)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getCodeChemin() {
    return codeChemin;
  }


  @JsonProperty(JSON_PROPERTY_CODE_CHEMIN)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCodeChemin(String codeChemin) {
    this.codeChemin = codeChemin;
  }


  public CheminObjetFormation codePeriode(String codePeriode) {
    this.codePeriode = codePeriode;
    return this;
  }

  /**
   * Get codePeriode
   * @return codePeriode
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CODE_PERIODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getCodePeriode() {
    return codePeriode;
  }


  @JsonProperty(JSON_PROPERTY_CODE_PERIODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCodePeriode(String codePeriode) {
    this.codePeriode = codePeriode;
  }


  public CheminObjetFormation codeStructure(String codeStructure) {
    this.codeStructure = codeStructure;
    return this;
  }

  /**
   * Le code structure  - identifiant unique
   * @return codeStructure
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CODE_STRUCTURE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getCodeStructure() {
    return codeStructure;
  }


  @JsonProperty(JSON_PROPERTY_CODE_STRUCTURE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCodeStructure(String codeStructure) {
    this.codeStructure = codeStructure;
  }


  public CheminObjetFormation codeType(String codeType) {
    this.codeType = codeType;
    return this;
  }

  /**
   * Get codeType
   * @return codeType
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CODE_TYPE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getCodeType() {
    return codeType;
  }


  @JsonProperty(JSON_PROPERTY_CODE_TYPE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCodeType(String codeType) {
    this.codeType = codeType;
  }


  public CheminObjetFormation capaciteAccueil(Integer capaciteAccueil) {
    this.capaciteAccueil = capaciteAccueil;
    return this;
  }

  /**
   * Get capaciteAccueil
   * @return capaciteAccueil
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CAPACITE_ACCUEIL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Integer getCapaciteAccueil() {
    return capaciteAccueil;
  }


  @JsonProperty(JSON_PROPERTY_CAPACITE_ACCUEIL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCapaciteAccueil(Integer capaciteAccueil) {
    this.capaciteAccueil = capaciteAccueil;
  }


  public CheminObjetFormation obligatoire(Boolean obligatoire) {
    this.obligatoire = obligatoire;
    return this;
  }

  /**
   * temoin d&#39;obligation
   * @return obligatoire
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_OBLIGATOIRE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Boolean getObligatoire() {
    return obligatoire;
  }


  @JsonProperty(JSON_PROPERTY_OBLIGATOIRE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setObligatoire(Boolean obligatoire) {
    this.obligatoire = obligatoire;
  }


  public CheminObjetFormation ouverteChoixCursus(OuverteChoixCursusEnum ouverteChoixCursus) {
    this.ouverteChoixCursus = ouverteChoixCursus;
    return this;
  }

  /**
   * Get ouverteChoixCursus
   * @return ouverteChoixCursus
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_OUVERTE_CHOIX_CURSUS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public OuverteChoixCursusEnum getOuverteChoixCursus() {
    return ouverteChoixCursus;
  }


  @JsonProperty(JSON_PROPERTY_OUVERTE_CHOIX_CURSUS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setOuverteChoixCursus(OuverteChoixCursusEnum ouverteChoixCursus) {
    this.ouverteChoixCursus = ouverteChoixCursus;
  }


  public CheminObjetFormation creditEcts(BigDecimal creditEcts) {
    this.creditEcts = creditEcts;
    return this;
  }

  /**
   * Get creditEcts
   * @return creditEcts
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CREDIT_ECTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public BigDecimal getCreditEcts() {
    return creditEcts;
  }


  @JsonProperty(JSON_PROPERTY_CREDIT_ECTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCreditEcts(BigDecimal creditEcts) {
    this.creditEcts = creditEcts;
  }


  public CheminObjetFormation estPlageDeChoix(Boolean estPlageDeChoix) {
    this.estPlageDeChoix = estPlageDeChoix;
    return this;
  }

  /**
   * Get estPlageDeChoix
   * @return estPlageDeChoix
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_EST_PLAGE_DE_CHOIX)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Boolean getEstPlageDeChoix() {
    return estPlageDeChoix;
  }


  @JsonProperty(JSON_PROPERTY_EST_PLAGE_DE_CHOIX)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setEstPlageDeChoix(Boolean estPlageDeChoix) {
    this.estPlageDeChoix = estPlageDeChoix;
  }


  public CheminObjetFormation plageMin(BigDecimal plageMin) {
    this.plageMin = plageMin;
    return this;
  }

  /**
   * Get plageMin
   * @return plageMin
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_PLAGE_MIN)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public BigDecimal getPlageMin() {
    return plageMin;
  }


  @JsonProperty(JSON_PROPERTY_PLAGE_MIN)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setPlageMin(BigDecimal plageMin) {
    this.plageMin = plageMin;
  }


  public CheminObjetFormation plageMax(BigDecimal plageMax) {
    this.plageMax = plageMax;
    return this;
  }

  /**
   * Get plageMax
   * @return plageMax
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_PLAGE_MAX)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public BigDecimal getPlageMax() {
    return plageMax;
  }


  @JsonProperty(JSON_PROPERTY_PLAGE_MAX)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setPlageMax(BigDecimal plageMax) {
    this.plageMax = plageMax;
  }


  public CheminObjetFormation formation(FormationMasse formation) {
    this.formation = formation;
    return this;
  }

  /**
   * Get formation
   * @return formation
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_FORMATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public FormationMasse getFormation() {
    return formation;
  }


  @JsonProperty(JSON_PROPERTY_FORMATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setFormation(FormationMasse formation) {
    this.formation = formation;
  }


  public CheminObjetFormation objetFormation(ObjetFormationMasse objetFormation) {
    this.objetFormation = objetFormation;
    return this;
  }

  /**
   * Get objetFormation
   * @return objetFormation
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_OBJET_FORMATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public ObjetFormationMasse getObjetFormation() {
    return objetFormation;
  }


  @JsonProperty(JSON_PROPERTY_OBJET_FORMATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setObjetFormation(ObjetFormationMasse objetFormation) {
    this.objetFormation = objetFormation;
  }


  public CheminObjetFormation groupement(GroupementMasse groupement) {
    this.groupement = groupement;
    return this;
  }

  /**
   * Get groupement
   * @return groupement
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_GROUPEMENT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public GroupementMasse getGroupement() {
    return groupement;
  }


  @JsonProperty(JSON_PROPERTY_GROUPEMENT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setGroupement(GroupementMasse groupement) {
    this.groupement = groupement;
  }


  /**
   * Return true if this CheminObjetFormation object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CheminObjetFormation cheminObjetFormation = (CheminObjetFormation) o;
    return Objects.equals(this.idCheminObjetFormation, cheminObjetFormation.idCheminObjetFormation) &&
        Objects.equals(this.codeChemin, cheminObjetFormation.codeChemin) &&
        Objects.equals(this.codePeriode, cheminObjetFormation.codePeriode) &&
        Objects.equals(this.codeStructure, cheminObjetFormation.codeStructure) &&
        Objects.equals(this.codeType, cheminObjetFormation.codeType) &&
        Objects.equals(this.capaciteAccueil, cheminObjetFormation.capaciteAccueil) &&
        Objects.equals(this.obligatoire, cheminObjetFormation.obligatoire) &&
        Objects.equals(this.ouverteChoixCursus, cheminObjetFormation.ouverteChoixCursus) &&
        Objects.equals(this.creditEcts, cheminObjetFormation.creditEcts) &&
        Objects.equals(this.estPlageDeChoix, cheminObjetFormation.estPlageDeChoix) &&
        Objects.equals(this.plageMin, cheminObjetFormation.plageMin) &&
        Objects.equals(this.plageMax, cheminObjetFormation.plageMax) &&
        Objects.equals(this.formation, cheminObjetFormation.formation) &&
        Objects.equals(this.objetFormation, cheminObjetFormation.objetFormation) &&
        Objects.equals(this.groupement, cheminObjetFormation.groupement);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idCheminObjetFormation, codeChemin, codePeriode, codeStructure, codeType, capaciteAccueil, obligatoire, ouverteChoixCursus, creditEcts, estPlageDeChoix, plageMin, plageMax, formation, objetFormation, groupement);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CheminObjetFormation {\n");
    sb.append("    idCheminObjetFormation: ").append(toIndentedString(idCheminObjetFormation)).append("\n");
    sb.append("    codeChemin: ").append(toIndentedString(codeChemin)).append("\n");
    sb.append("    codePeriode: ").append(toIndentedString(codePeriode)).append("\n");
    sb.append("    codeStructure: ").append(toIndentedString(codeStructure)).append("\n");
    sb.append("    codeType: ").append(toIndentedString(codeType)).append("\n");
    sb.append("    capaciteAccueil: ").append(toIndentedString(capaciteAccueil)).append("\n");
    sb.append("    obligatoire: ").append(toIndentedString(obligatoire)).append("\n");
    sb.append("    ouverteChoixCursus: ").append(toIndentedString(ouverteChoixCursus)).append("\n");
    sb.append("    creditEcts: ").append(toIndentedString(creditEcts)).append("\n");
    sb.append("    estPlageDeChoix: ").append(toIndentedString(estPlageDeChoix)).append("\n");
    sb.append("    plageMin: ").append(toIndentedString(plageMin)).append("\n");
    sb.append("    plageMax: ").append(toIndentedString(plageMax)).append("\n");
    sb.append("    formation: ").append(toIndentedString(formation)).append("\n");
    sb.append("    objetFormation: ").append(toIndentedString(objetFormation)).append("\n");
    sb.append("    groupement: ").append(toIndentedString(groupement)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

  /**
   * Convert the instance into URL query string.
   *
   * @return URL query string
   */
  public String toUrlQueryString() {
    return toUrlQueryString(null);
  }

  /**
   * Convert the instance into URL query string.
   *
   * @param prefix prefix of the query string
   * @return URL query string
   */
  public String toUrlQueryString(String prefix) {
    String suffix = "";
    String containerSuffix = "";
    String containerPrefix = "";
    if (prefix == null) {
      // style=form, explode=true, e.g. /pet?name=cat&type=manx
      prefix = "";
    } else {
      // deepObject style e.g. /pet?id[name]=cat&id[type]=manx
      prefix = prefix + "[";
      suffix = "]";
      containerSuffix = "]";
      containerPrefix = "[";
    }

    StringJoiner joiner = new StringJoiner("&");

    // add `idCheminObjetFormation` to the URL query string
    if (getIdCheminObjetFormation() != null) {
      joiner.add(String.format("%sidCheminObjetFormation%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getIdCheminObjetFormation()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `codeChemin` to the URL query string
    if (getCodeChemin() != null) {
      joiner.add(String.format("%scodeChemin%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCodeChemin()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `codePeriode` to the URL query string
    if (getCodePeriode() != null) {
      joiner.add(String.format("%scodePeriode%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCodePeriode()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `codeStructure` to the URL query string
    if (getCodeStructure() != null) {
      joiner.add(String.format("%scodeStructure%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCodeStructure()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `codeType` to the URL query string
    if (getCodeType() != null) {
      joiner.add(String.format("%scodeType%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCodeType()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `capaciteAccueil` to the URL query string
    if (getCapaciteAccueil() != null) {
      joiner.add(String.format("%scapaciteAccueil%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCapaciteAccueil()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `obligatoire` to the URL query string
    if (getObligatoire() != null) {
      joiner.add(String.format("%sobligatoire%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getObligatoire()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `ouverteChoixCursus` to the URL query string
    if (getOuverteChoixCursus() != null) {
      joiner.add(String.format("%souverteChoixCursus%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getOuverteChoixCursus()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `creditEcts` to the URL query string
    if (getCreditEcts() != null) {
      joiner.add(String.format("%screditEcts%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCreditEcts()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `estPlageDeChoix` to the URL query string
    if (getEstPlageDeChoix() != null) {
      joiner.add(String.format("%sestPlageDeChoix%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getEstPlageDeChoix()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `plageMin` to the URL query string
    if (getPlageMin() != null) {
      joiner.add(String.format("%splageMin%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getPlageMin()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `plageMax` to the URL query string
    if (getPlageMax() != null) {
      joiner.add(String.format("%splageMax%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getPlageMax()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `formation` to the URL query string
    if (getFormation() != null) {
      joiner.add(getFormation().toUrlQueryString(prefix + "formation" + suffix));
    }

    // add `objetFormation` to the URL query string
    if (getObjetFormation() != null) {
      joiner.add(getObjetFormation().toUrlQueryString(prefix + "objetFormation" + suffix));
    }

    // add `groupement` to the URL query string
    if (getGroupement() != null) {
      joiner.add(getGroupement().toUrlQueryString(prefix + "groupement" + suffix));
    }

    return joiner.toString();
  }
}

