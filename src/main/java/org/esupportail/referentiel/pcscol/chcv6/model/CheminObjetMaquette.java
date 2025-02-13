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
 * CheminObjetMaquette
 */
@JsonPropertyOrder({
  CheminObjetMaquette.JSON_PROPERTY_ID_CHEMIN_OBJET_MAQUETTE,
  CheminObjetMaquette.JSON_PROPERTY_ID_CHEMIN_PEDAGOGIQUE,
  CheminObjetMaquette.JSON_PROPERTY_CODE_CHEMIN,
  CheminObjetMaquette.JSON_PROPERTY_CODE_PERIODE,
  CheminObjetMaquette.JSON_PROPERTY_PERIODE_EST_ACTIVE,
  CheminObjetMaquette.JSON_PROPERTY_CODE_STRUCTURE,
  CheminObjetMaquette.JSON_PROPERTY_CODE_TYPE,
  CheminObjetMaquette.JSON_PROPERTY_CAPACITE_ACCUEIL,
  CheminObjetMaquette.JSON_PROPERTY_OBLIGATOIRE,
  CheminObjetMaquette.JSON_PROPERTY_OUVERTE_CHOIX_CURSUS,
  CheminObjetMaquette.JSON_PROPERTY_CREDIT_ECTS,
  CheminObjetMaquette.JSON_PROPERTY_EST_PLAGE_DE_CHOIX,
  CheminObjetMaquette.JSON_PROPERTY_PLAGE_MIN,
  CheminObjetMaquette.JSON_PROPERTY_PLAGE_MAX,
  CheminObjetMaquette.JSON_PROPERTY_FORMATION,
  CheminObjetMaquette.JSON_PROPERTY_OBJET_FORMATION,
  CheminObjetMaquette.JSON_PROPERTY_GROUPEMENT
})
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-09-26T14:04:50.284799424+02:00[Europe/Paris]", comments = "Generator version: 7.8.0")
public class CheminObjetMaquette {
  public static final String JSON_PROPERTY_ID_CHEMIN_OBJET_MAQUETTE = "idCheminObjetMaquette";
  private UUID idCheminObjetMaquette;

  public static final String JSON_PROPERTY_ID_CHEMIN_PEDAGOGIQUE = "idCheminPedagogique";
  private UUID idCheminPedagogique;

  public static final String JSON_PROPERTY_CODE_CHEMIN = "codeChemin";
  private String codeChemin;

  public static final String JSON_PROPERTY_CODE_PERIODE = "codePeriode";
  private String codePeriode;

  public static final String JSON_PROPERTY_PERIODE_EST_ACTIVE = "periodeEstActive";
  private Boolean periodeEstActive;

  public static final String JSON_PROPERTY_CODE_STRUCTURE = "codeStructure";
  private String codeStructure;

  public static final String JSON_PROPERTY_CODE_TYPE = "codeType";
  private String codeType;

  public static final String JSON_PROPERTY_CAPACITE_ACCUEIL = "capaciteAccueil";
  private Integer capaciteAccueil;

  public static final String JSON_PROPERTY_OBLIGATOIRE = "obligatoire";
  private Boolean obligatoire;

  /**
   * ouverture au choix du cursus
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

  public CheminObjetMaquette() { 
  }

  public CheminObjetMaquette idCheminObjetMaquette(UUID idCheminObjetMaquette) {
    this.idCheminObjetMaquette = idCheminObjetMaquette;
    return this;
  }

  /**
   * L&#39;id technique du chemin objet maquette
   * @return idCheminObjetMaquette
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ID_CHEMIN_OBJET_MAQUETTE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public UUID getIdCheminObjetMaquette() {
    return idCheminObjetMaquette;
  }


  @JsonProperty(JSON_PROPERTY_ID_CHEMIN_OBJET_MAQUETTE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setIdCheminObjetMaquette(UUID idCheminObjetMaquette) {
    this.idCheminObjetMaquette = idCheminObjetMaquette;
  }


  public CheminObjetMaquette idCheminPedagogique(UUID idCheminPedagogique) {
    this.idCheminPedagogique = idCheminPedagogique;
    return this;
  }

  /**
   * L&#39;id technique du chemin pédagogique
   * @return idCheminPedagogique
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ID_CHEMIN_PEDAGOGIQUE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public UUID getIdCheminPedagogique() {
    return idCheminPedagogique;
  }


  @JsonProperty(JSON_PROPERTY_ID_CHEMIN_PEDAGOGIQUE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setIdCheminPedagogique(UUID idCheminPedagogique) {
    this.idCheminPedagogique = idCheminPedagogique;
  }


  public CheminObjetMaquette codeChemin(String codeChemin) {
    this.codeChemin = codeChemin;
    return this;
  }

  /**
   * Get codeChemin
   * @return codeChemin
   */
  @jakarta.annotation.Nullable
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


  public CheminObjetMaquette codePeriode(String codePeriode) {
    this.codePeriode = codePeriode;
    return this;
  }

  /**
   * Get codePeriode
   * @return codePeriode
   */
  @jakarta.annotation.Nullable
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


  public CheminObjetMaquette periodeEstActive(Boolean periodeEstActive) {
    this.periodeEstActive = periodeEstActive;
    return this;
  }

  /**
   * Définit si la periode est active ou pas
   * @return periodeEstActive
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_PERIODE_EST_ACTIVE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Boolean getPeriodeEstActive() {
    return periodeEstActive;
  }


  @JsonProperty(JSON_PROPERTY_PERIODE_EST_ACTIVE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setPeriodeEstActive(Boolean periodeEstActive) {
    this.periodeEstActive = periodeEstActive;
  }


  public CheminObjetMaquette codeStructure(String codeStructure) {
    this.codeStructure = codeStructure;
    return this;
  }

  /**
   * Le code structure  - identifiant unique
   * @return codeStructure
   */
  @jakarta.annotation.Nullable
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


  public CheminObjetMaquette codeType(String codeType) {
    this.codeType = codeType;
    return this;
  }

  /**
   * Get codeType
   * @return codeType
   */
  @jakarta.annotation.Nullable
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


  public CheminObjetMaquette capaciteAccueil(Integer capaciteAccueil) {
    this.capaciteAccueil = capaciteAccueil;
    return this;
  }

  /**
   * Get capaciteAccueil
   * @return capaciteAccueil
   */
  @jakarta.annotation.Nullable
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


  public CheminObjetMaquette obligatoire(Boolean obligatoire) {
    this.obligatoire = obligatoire;
    return this;
  }

  /**
   * temoin d&#39;obligation
   * @return obligatoire
   */
  @jakarta.annotation.Nullable
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


  public CheminObjetMaquette ouverteChoixCursus(OuverteChoixCursusEnum ouverteChoixCursus) {
    this.ouverteChoixCursus = ouverteChoixCursus;
    return this;
  }

  /**
   * ouverture au choix du cursus
   * @return ouverteChoixCursus
   */
  @jakarta.annotation.Nullable
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


  public CheminObjetMaquette creditEcts(BigDecimal creditEcts) {
    this.creditEcts = creditEcts;
    return this;
  }

  /**
   * Get creditEcts
   * @return creditEcts
   */
  @jakarta.annotation.Nullable
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


  public CheminObjetMaquette estPlageDeChoix(Boolean estPlageDeChoix) {
    this.estPlageDeChoix = estPlageDeChoix;
    return this;
  }

  /**
   * Get estPlageDeChoix
   * @return estPlageDeChoix
   */
  @jakarta.annotation.Nullable
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


  public CheminObjetMaquette plageMin(BigDecimal plageMin) {
    this.plageMin = plageMin;
    return this;
  }

  /**
   * Get plageMin
   * @return plageMin
   */
  @jakarta.annotation.Nullable
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


  public CheminObjetMaquette plageMax(BigDecimal plageMax) {
    this.plageMax = plageMax;
    return this;
  }

  /**
   * Get plageMax
   * @return plageMax
   */
  @jakarta.annotation.Nullable
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


  public CheminObjetMaquette formation(FormationMasse formation) {
    this.formation = formation;
    return this;
  }

  /**
   * Get formation
   * @return formation
   */
  @jakarta.annotation.Nullable
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


  public CheminObjetMaquette objetFormation(ObjetFormationMasse objetFormation) {
    this.objetFormation = objetFormation;
    return this;
  }

  /**
   * Get objetFormation
   * @return objetFormation
   */
  @jakarta.annotation.Nullable
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


  public CheminObjetMaquette groupement(GroupementMasse groupement) {
    this.groupement = groupement;
    return this;
  }

  /**
   * Get groupement
   * @return groupement
   */
  @jakarta.annotation.Nullable
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
   * Return true if this CheminObjetMaquette object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CheminObjetMaquette cheminObjetMaquette = (CheminObjetMaquette) o;
    return Objects.equals(this.idCheminObjetMaquette, cheminObjetMaquette.idCheminObjetMaquette) &&
        Objects.equals(this.idCheminPedagogique, cheminObjetMaquette.idCheminPedagogique) &&
        Objects.equals(this.codeChemin, cheminObjetMaquette.codeChemin) &&
        Objects.equals(this.codePeriode, cheminObjetMaquette.codePeriode) &&
        Objects.equals(this.periodeEstActive, cheminObjetMaquette.periodeEstActive) &&
        Objects.equals(this.codeStructure, cheminObjetMaquette.codeStructure) &&
        Objects.equals(this.codeType, cheminObjetMaquette.codeType) &&
        Objects.equals(this.capaciteAccueil, cheminObjetMaquette.capaciteAccueil) &&
        Objects.equals(this.obligatoire, cheminObjetMaquette.obligatoire) &&
        Objects.equals(this.ouverteChoixCursus, cheminObjetMaquette.ouverteChoixCursus) &&
        Objects.equals(this.creditEcts, cheminObjetMaquette.creditEcts) &&
        Objects.equals(this.estPlageDeChoix, cheminObjetMaquette.estPlageDeChoix) &&
        Objects.equals(this.plageMin, cheminObjetMaquette.plageMin) &&
        Objects.equals(this.plageMax, cheminObjetMaquette.plageMax) &&
        Objects.equals(this.formation, cheminObjetMaquette.formation) &&
        Objects.equals(this.objetFormation, cheminObjetMaquette.objetFormation) &&
        Objects.equals(this.groupement, cheminObjetMaquette.groupement);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idCheminObjetMaquette, idCheminPedagogique, codeChemin, codePeriode, periodeEstActive, codeStructure, codeType, capaciteAccueil, obligatoire, ouverteChoixCursus, creditEcts, estPlageDeChoix, plageMin, plageMax, formation, objetFormation, groupement);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CheminObjetMaquette {\n");
    sb.append("    idCheminObjetMaquette: ").append(toIndentedString(idCheminObjetMaquette)).append("\n");
    sb.append("    idCheminPedagogique: ").append(toIndentedString(idCheminPedagogique)).append("\n");
    sb.append("    codeChemin: ").append(toIndentedString(codeChemin)).append("\n");
    sb.append("    codePeriode: ").append(toIndentedString(codePeriode)).append("\n");
    sb.append("    periodeEstActive: ").append(toIndentedString(periodeEstActive)).append("\n");
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

    // add `idCheminObjetMaquette` to the URL query string
    if (getIdCheminObjetMaquette() != null) {
      joiner.add(String.format("%sidCheminObjetMaquette%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getIdCheminObjetMaquette()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `idCheminPedagogique` to the URL query string
    if (getIdCheminPedagogique() != null) {
      joiner.add(String.format("%sidCheminPedagogique%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getIdCheminPedagogique()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `codeChemin` to the URL query string
    if (getCodeChemin() != null) {
      joiner.add(String.format("%scodeChemin%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCodeChemin()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `codePeriode` to the URL query string
    if (getCodePeriode() != null) {
      joiner.add(String.format("%scodePeriode%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCodePeriode()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `periodeEstActive` to the URL query string
    if (getPeriodeEstActive() != null) {
      joiner.add(String.format("%speriodeEstActive%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getPeriodeEstActive()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
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

