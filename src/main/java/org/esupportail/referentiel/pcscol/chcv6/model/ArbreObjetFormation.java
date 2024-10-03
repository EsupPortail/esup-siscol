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

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

import org.esupportail.referentiel.pcscol.invoker.ApiClient;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
/**
 * ArbreObjetFormation
 */
@JsonPropertyOrder({
  ArbreObjetFormation.JSON_PROPERTY_CODE,
  ArbreObjetFormation.JSON_PROPERTY_LIBELLE_COURT,
  ArbreObjetFormation.JSON_PROPERTY_LIBELLE_LONG,
  ArbreObjetFormation.JSON_PROPERTY_DESCRIPTION,
  ArbreObjetFormation.JSON_PROPERTY_CATEGORIE,
  ArbreObjetFormation.JSON_PROPERTY_TYPE,
  ArbreObjetFormation.JSON_PROPERTY_MUTUALISE,
  ArbreObjetFormation.JSON_PROPERTY_OUVERTURE_AU_CHOIX_DU_CURSUS,
  ArbreObjetFormation.JSON_PROPERTY_ID_CHEMIN_PEDAGOGIQUE,
  ArbreObjetFormation.JSON_PROPERTY_CARACTERE_OBLIGATOIRE,
  ArbreObjetFormation.JSON_PROPERTY_STAGE,
  ArbreObjetFormation.JSON_PROPERTY_CREDIT_ECTS,
  ArbreObjetFormation.JSON_PROPERTY_LISTE_OBJET_FORMATION,
  ArbreObjetFormation.JSON_PROPERTY_LISTE_GROUPEMENT
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-09-26T14:04:50.284799424+02:00[Europe/Paris]", comments = "Generator version: 7.8.0")
public class ArbreObjetFormation {
  public static final String JSON_PROPERTY_CODE = "code";
  private String code;

  public static final String JSON_PROPERTY_LIBELLE_COURT = "libelleCourt";
  private String libelleCourt;

  public static final String JSON_PROPERTY_LIBELLE_LONG = "libelleLong";
  private String libelleLong;

  public static final String JSON_PROPERTY_DESCRIPTION = "description";
  private String description;

  public static final String JSON_PROPERTY_CATEGORIE = "categorie";
  private ObjetLibelle categorie;

  public static final String JSON_PROPERTY_TYPE = "type";
  private ObjetLibelle type;

  public static final String JSON_PROPERTY_MUTUALISE = "mutualise";
  private Boolean mutualise;

  /**
   * Gets or Sets ouvertureAuChoixDuCursus
   */
  public enum OuvertureAuChoixDuCursusEnum {
    OUVERT("OUVERT"),
    
    FERME("FERME"),
    
    JAMAIS("JAMAIS");

    private String value;

    OuvertureAuChoixDuCursusEnum(String value) {
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
    public static OuvertureAuChoixDuCursusEnum fromValue(String value) {
      for (OuvertureAuChoixDuCursusEnum b : OuvertureAuChoixDuCursusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_OUVERTURE_AU_CHOIX_DU_CURSUS = "ouvertureAuChoixDuCursus";
  private OuvertureAuChoixDuCursusEnum ouvertureAuChoixDuCursus;

  public static final String JSON_PROPERTY_ID_CHEMIN_PEDAGOGIQUE = "idCheminPedagogique";
  private String idCheminPedagogique;

  public static final String JSON_PROPERTY_CARACTERE_OBLIGATOIRE = "caractereObligatoire";
  private Boolean caractereObligatoire;

  public static final String JSON_PROPERTY_STAGE = "stage";
  private Boolean stage;

  public static final String JSON_PROPERTY_CREDIT_ECTS = "creditEcts";
  private String creditEcts;

  public static final String JSON_PROPERTY_LISTE_OBJET_FORMATION = "listeObjetFormation";
  private List<ArbreObjetFormation> listeObjetFormation = new ArrayList<>();

  public static final String JSON_PROPERTY_LISTE_GROUPEMENT = "listeGroupement";
  private List<ArbreGroupement> listeGroupement = new ArrayList<>();

  public ArbreObjetFormation() { 
  }

  public ArbreObjetFormation code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Get code
   * @return code
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_CODE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public String getCode() {
    return code;
  }


  @JsonProperty(JSON_PROPERTY_CODE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setCode(String code) {
    this.code = code;
  }


  public ArbreObjetFormation libelleCourt(String libelleCourt) {
    this.libelleCourt = libelleCourt;
    return this;
  }

  /**
   * Get libelleCourt
   * @return libelleCourt
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_LIBELLE_COURT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public String getLibelleCourt() {
    return libelleCourt;
  }


  @JsonProperty(JSON_PROPERTY_LIBELLE_COURT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setLibelleCourt(String libelleCourt) {
    this.libelleCourt = libelleCourt;
  }


  public ArbreObjetFormation libelleLong(String libelleLong) {
    this.libelleLong = libelleLong;
    return this;
  }

  /**
   * Get libelleLong
   * @return libelleLong
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_LIBELLE_LONG)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public String getLibelleLong() {
    return libelleLong;
  }


  @JsonProperty(JSON_PROPERTY_LIBELLE_LONG)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setLibelleLong(String libelleLong) {
    this.libelleLong = libelleLong;
  }


  public ArbreObjetFormation description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_DESCRIPTION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getDescription() {
    return description;
  }


  @JsonProperty(JSON_PROPERTY_DESCRIPTION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setDescription(String description) {
    this.description = description;
  }


  public ArbreObjetFormation categorie(ObjetLibelle categorie) {
    this.categorie = categorie;
    return this;
  }

  /**
   * Get categorie
   * @return categorie
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_CATEGORIE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public ObjetLibelle getCategorie() {
    return categorie;
  }


  @JsonProperty(JSON_PROPERTY_CATEGORIE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setCategorie(ObjetLibelle categorie) {
    this.categorie = categorie;
  }


  public ArbreObjetFormation type(ObjetLibelle type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_TYPE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public ObjetLibelle getType() {
    return type;
  }


  @JsonProperty(JSON_PROPERTY_TYPE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setType(ObjetLibelle type) {
    this.type = type;
  }


  public ArbreObjetFormation mutualise(Boolean mutualise) {
    this.mutualise = mutualise;
    return this;
  }

  /**
   * Get mutualise
   * @return mutualise
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_MUTUALISE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public Boolean getMutualise() {
    return mutualise;
  }


  @JsonProperty(JSON_PROPERTY_MUTUALISE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setMutualise(Boolean mutualise) {
    this.mutualise = mutualise;
  }


  public ArbreObjetFormation ouvertureAuChoixDuCursus(OuvertureAuChoixDuCursusEnum ouvertureAuChoixDuCursus) {
    this.ouvertureAuChoixDuCursus = ouvertureAuChoixDuCursus;
    return this;
  }

  /**
   * Get ouvertureAuChoixDuCursus
   * @return ouvertureAuChoixDuCursus
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_OUVERTURE_AU_CHOIX_DU_CURSUS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public OuvertureAuChoixDuCursusEnum getOuvertureAuChoixDuCursus() {
    return ouvertureAuChoixDuCursus;
  }


  @JsonProperty(JSON_PROPERTY_OUVERTURE_AU_CHOIX_DU_CURSUS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setOuvertureAuChoixDuCursus(OuvertureAuChoixDuCursusEnum ouvertureAuChoixDuCursus) {
    this.ouvertureAuChoixDuCursus = ouvertureAuChoixDuCursus;
  }


  public ArbreObjetFormation idCheminPedagogique(String idCheminPedagogique) {
    this.idCheminPedagogique = idCheminPedagogique;
    return this;
  }

  /**
   * 2c1e120f-4799-414e-90d8-bbda2b869704
   * @return idCheminPedagogique
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ID_CHEMIN_PEDAGOGIQUE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getIdCheminPedagogique() {
    return idCheminPedagogique;
  }


  @JsonProperty(JSON_PROPERTY_ID_CHEMIN_PEDAGOGIQUE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setIdCheminPedagogique(String idCheminPedagogique) {
    this.idCheminPedagogique = idCheminPedagogique;
  }


  public ArbreObjetFormation caractereObligatoire(Boolean caractereObligatoire) {
    this.caractereObligatoire = caractereObligatoire;
    return this;
  }

  /**
   * est-ce que l&#39;objet de formation est obligatoire ou facultatif ?
   * @return caractereObligatoire
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_CARACTERE_OBLIGATOIRE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public Boolean getCaractereObligatoire() {
    return caractereObligatoire;
  }


  @JsonProperty(JSON_PROPERTY_CARACTERE_OBLIGATOIRE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setCaractereObligatoire(Boolean caractereObligatoire) {
    this.caractereObligatoire = caractereObligatoire;
  }


  public ArbreObjetFormation stage(Boolean stage) {
    this.stage = stage;
    return this;
  }

  /**
   * est-ce que l&#39;objet de formation est un stage ?
   * @return stage
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_STAGE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Boolean getStage() {
    return stage;
  }


  @JsonProperty(JSON_PROPERTY_STAGE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setStage(Boolean stage) {
    this.stage = stage;
  }


  public ArbreObjetFormation creditEcts(String creditEcts) {
    this.creditEcts = creditEcts;
    return this;
  }

  /**
   * les crédits E.C.T.S. positionnés sur l&#39;objet de formation
   * @return creditEcts
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CREDIT_ECTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getCreditEcts() {
    return creditEcts;
  }


  @JsonProperty(JSON_PROPERTY_CREDIT_ECTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCreditEcts(String creditEcts) {
    this.creditEcts = creditEcts;
  }


  public ArbreObjetFormation listeObjetFormation(List<ArbreObjetFormation> listeObjetFormation) {
    this.listeObjetFormation = listeObjetFormation;
    return this;
  }

  public ArbreObjetFormation addListeObjetFormationItem(ArbreObjetFormation listeObjetFormationItem) {
    if (this.listeObjetFormation == null) {
      this.listeObjetFormation = new ArrayList<>();
    }
    this.listeObjetFormation.add(listeObjetFormationItem);
    return this;
  }

  /**
   * Get listeObjetFormation
   * @return listeObjetFormation
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_LISTE_OBJET_FORMATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public List<ArbreObjetFormation> getListeObjetFormation() {
    return listeObjetFormation;
  }


  @JsonProperty(JSON_PROPERTY_LISTE_OBJET_FORMATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setListeObjetFormation(List<ArbreObjetFormation> listeObjetFormation) {
    this.listeObjetFormation = listeObjetFormation;
  }


  public ArbreObjetFormation listeGroupement(List<ArbreGroupement> listeGroupement) {
    this.listeGroupement = listeGroupement;
    return this;
  }

  public ArbreObjetFormation addListeGroupementItem(ArbreGroupement listeGroupementItem) {
    if (this.listeGroupement == null) {
      this.listeGroupement = new ArrayList<>();
    }
    this.listeGroupement.add(listeGroupementItem);
    return this;
  }

  /**
   * Get listeGroupement
   * @return listeGroupement
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_LISTE_GROUPEMENT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public List<ArbreGroupement> getListeGroupement() {
    return listeGroupement;
  }


  @JsonProperty(JSON_PROPERTY_LISTE_GROUPEMENT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setListeGroupement(List<ArbreGroupement> listeGroupement) {
    this.listeGroupement = listeGroupement;
  }


  /**
   * Return true if this ArbreObjetFormation object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ArbreObjetFormation arbreObjetFormation = (ArbreObjetFormation) o;
    return Objects.equals(this.code, arbreObjetFormation.code) &&
        Objects.equals(this.libelleCourt, arbreObjetFormation.libelleCourt) &&
        Objects.equals(this.libelleLong, arbreObjetFormation.libelleLong) &&
        Objects.equals(this.description, arbreObjetFormation.description) &&
        Objects.equals(this.categorie, arbreObjetFormation.categorie) &&
        Objects.equals(this.type, arbreObjetFormation.type) &&
        Objects.equals(this.mutualise, arbreObjetFormation.mutualise) &&
        Objects.equals(this.ouvertureAuChoixDuCursus, arbreObjetFormation.ouvertureAuChoixDuCursus) &&
        Objects.equals(this.idCheminPedagogique, arbreObjetFormation.idCheminPedagogique) &&
        Objects.equals(this.caractereObligatoire, arbreObjetFormation.caractereObligatoire) &&
        Objects.equals(this.stage, arbreObjetFormation.stage) &&
        Objects.equals(this.creditEcts, arbreObjetFormation.creditEcts) &&
        Objects.equals(this.listeObjetFormation, arbreObjetFormation.listeObjetFormation) &&
        Objects.equals(this.listeGroupement, arbreObjetFormation.listeGroupement);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, libelleCourt, libelleLong, description, categorie, type, mutualise, ouvertureAuChoixDuCursus, idCheminPedagogique, caractereObligatoire, stage, creditEcts, listeObjetFormation, listeGroupement);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ArbreObjetFormation {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    libelleCourt: ").append(toIndentedString(libelleCourt)).append("\n");
    sb.append("    libelleLong: ").append(toIndentedString(libelleLong)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    categorie: ").append(toIndentedString(categorie)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    mutualise: ").append(toIndentedString(mutualise)).append("\n");
    sb.append("    ouvertureAuChoixDuCursus: ").append(toIndentedString(ouvertureAuChoixDuCursus)).append("\n");
    sb.append("    idCheminPedagogique: ").append(toIndentedString(idCheminPedagogique)).append("\n");
    sb.append("    caractereObligatoire: ").append(toIndentedString(caractereObligatoire)).append("\n");
    sb.append("    stage: ").append(toIndentedString(stage)).append("\n");
    sb.append("    creditEcts: ").append(toIndentedString(creditEcts)).append("\n");
    sb.append("    listeObjetFormation: ").append(toIndentedString(listeObjetFormation)).append("\n");
    sb.append("    listeGroupement: ").append(toIndentedString(listeGroupement)).append("\n");
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

    // add `code` to the URL query string
    if (getCode() != null) {
      joiner.add(String.format("%scode%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCode()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `libelleCourt` to the URL query string
    if (getLibelleCourt() != null) {
      joiner.add(String.format("%slibelleCourt%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getLibelleCourt()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `libelleLong` to the URL query string
    if (getLibelleLong() != null) {
      joiner.add(String.format("%slibelleLong%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getLibelleLong()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `description` to the URL query string
    if (getDescription() != null) {
      joiner.add(String.format("%sdescription%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getDescription()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `categorie` to the URL query string
    if (getCategorie() != null) {
      joiner.add(getCategorie().toUrlQueryString(prefix + "categorie" + suffix));
    }

    // add `type` to the URL query string
    if (getType() != null) {
      joiner.add(getType().toUrlQueryString(prefix + "type" + suffix));
    }

    // add `mutualise` to the URL query string
    if (getMutualise() != null) {
      joiner.add(String.format("%smutualise%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getMutualise()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `ouvertureAuChoixDuCursus` to the URL query string
    if (getOuvertureAuChoixDuCursus() != null) {
      joiner.add(String.format("%souvertureAuChoixDuCursus%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getOuvertureAuChoixDuCursus()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `idCheminPedagogique` to the URL query string
    if (getIdCheminPedagogique() != null) {
      joiner.add(String.format("%sidCheminPedagogique%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getIdCheminPedagogique()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `caractereObligatoire` to the URL query string
    if (getCaractereObligatoire() != null) {
      joiner.add(String.format("%scaractereObligatoire%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCaractereObligatoire()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `stage` to the URL query string
    if (getStage() != null) {
      joiner.add(String.format("%sstage%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getStage()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `creditEcts` to the URL query string
    if (getCreditEcts() != null) {
      joiner.add(String.format("%screditEcts%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCreditEcts()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `listeObjetFormation` to the URL query string
    if (getListeObjetFormation() != null) {
      for (int i = 0; i < getListeObjetFormation().size(); i++) {
        if (getListeObjetFormation().get(i) != null) {
          joiner.add(getListeObjetFormation().get(i).toUrlQueryString(String.format("%slisteObjetFormation%s%s", prefix, suffix,
          "".equals(suffix) ? "" : String.format("%s%d%s", containerPrefix, i, containerSuffix))));
        }
      }
    }

    // add `listeGroupement` to the URL query string
    if (getListeGroupement() != null) {
      for (int i = 0; i < getListeGroupement().size(); i++) {
        if (getListeGroupement().get(i) != null) {
          joiner.add(getListeGroupement().get(i).toUrlQueryString(String.format("%slisteGroupement%s%s", prefix, suffix,
          "".equals(suffix) ? "" : String.format("%s%d%s", containerPrefix, i, containerSuffix))));
        }
      }
    }

    return joiner.toString();
  }
}

