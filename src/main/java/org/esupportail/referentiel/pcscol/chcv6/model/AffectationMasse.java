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
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

import org.esupportail.referentiel.pcscol.invoker.ApiClient;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
/**
 * AffectationMasse
 */
@JsonPropertyOrder({
  AffectationMasse.JSON_PROPERTY_ID,
  AffectationMasse.JSON_PROPERTY_CODE_OBJET_MAQUETTE,
  AffectationMasse.JSON_PROPERTY_LIBELLE_COURT_O_M,
  AffectationMasse.JSON_PROPERTY_CODE_CHEMIN,
  AffectationMasse.JSON_PROPERTY_ETAT,
  AffectationMasse.JSON_PROPERTY_DATE_DEBUT,
  AffectationMasse.JSON_PROPERTY_DATE_FIN,
  AffectationMasse.JSON_PROPERTY_GESTIONNAIRE,
  AffectationMasse.JSON_PROPERTY_TYPE
})
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-09-26T14:04:50.284799424+02:00[Europe/Paris]", comments = "Generator version: 7.8.0")
public class AffectationMasse {
  public static final String JSON_PROPERTY_ID = "id";
  private String id;

  public static final String JSON_PROPERTY_CODE_OBJET_MAQUETTE = "codeObjetMaquette";
  private String codeObjetMaquette;

  public static final String JSON_PROPERTY_LIBELLE_COURT_O_M = "libelleCourtOM";
  private String libelleCourtOM;

  public static final String JSON_PROPERTY_CODE_CHEMIN = "codeChemin";
  private String codeChemin;

  /**
   * Gets or Sets etat
   */
  public enum EtatEnum {
    TERMINE("TERMINE"),
    
    PRIS_EN_COMPTE("PRIS_EN_COMPTE"),
    
    EN_COURS_EXECUTION("EN_COURS_EXECUTION"),
    
    ARRETE("ARRETE"),
    
    ARRET_EN_COURS("ARRET_EN_COURS"),
    
    ECHOUE("ECHOUE");

    private String value;

    EtatEnum(String value) {
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
    public static EtatEnum fromValue(String value) {
      for (EtatEnum b : EtatEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_ETAT = "etat";
  private EtatEnum etat;

  public static final String JSON_PROPERTY_DATE_DEBUT = "dateDebut";
  private Date dateDebut;

  public static final String JSON_PROPERTY_DATE_FIN = "dateFin";
  private Date dateFin;

  public static final String JSON_PROPERTY_GESTIONNAIRE = "gestionnaire";
  private String gestionnaire;

  public static final String JSON_PROPERTY_TYPE = "type";
  private String type;

  public AffectationMasse() { 
  }

  public AffectationMasse id(String id) {
    this.id = id;
    return this;
  }

  /**
   * L&#39;identifiant technique du job executé
   * @return id
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getId() {
    return id;
  }


  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setId(String id) {
    this.id = id;
  }


  public AffectationMasse codeObjetMaquette(String codeObjetMaquette) {
    this.codeObjetMaquette = codeObjetMaquette;
    return this;
  }

  /**
   * code de l&#39;objet maquette
   * @return codeObjetMaquette
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CODE_OBJET_MAQUETTE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getCodeObjetMaquette() {
    return codeObjetMaquette;
  }


  @JsonProperty(JSON_PROPERTY_CODE_OBJET_MAQUETTE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCodeObjetMaquette(String codeObjetMaquette) {
    this.codeObjetMaquette = codeObjetMaquette;
  }


  public AffectationMasse libelleCourtOM(String libelleCourtOM) {
    this.libelleCourtOM = libelleCourtOM;
    return this;
  }

  /**
   * libelle court de l&#39;objet maquette
   * @return libelleCourtOM
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_LIBELLE_COURT_O_M)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getLibelleCourtOM() {
    return libelleCourtOM;
  }


  @JsonProperty(JSON_PROPERTY_LIBELLE_COURT_O_M)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setLibelleCourtOM(String libelleCourtOM) {
    this.libelleCourtOM = libelleCourtOM;
  }


  public AffectationMasse codeChemin(String codeChemin) {
    this.codeChemin = codeChemin;
    return this;
  }

  /**
   * code chemin
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


  public AffectationMasse etat(EtatEnum etat) {
    this.etat = etat;
    return this;
  }

  /**
   * Get etat
   * @return etat
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ETAT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public EtatEnum getEtat() {
    return etat;
  }


  @JsonProperty(JSON_PROPERTY_ETAT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setEtat(EtatEnum etat) {
    this.etat = etat;
  }


  public AffectationMasse dateDebut(Date dateDebut) {
    this.dateDebut = dateDebut;
    return this;
  }

  /**
   * Date de début de traitement
   * @return dateDebut
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_DATE_DEBUT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Date getDateDebut() {
    return dateDebut;
  }


  @JsonProperty(JSON_PROPERTY_DATE_DEBUT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setDateDebut(Date dateDebut) {
    this.dateDebut = dateDebut;
  }


  public AffectationMasse dateFin(Date dateFin) {
    this.dateFin = dateFin;
    return this;
  }

  /**
   * Date de fin de traitement
   * @return dateFin
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_DATE_FIN)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Date getDateFin() {
    return dateFin;
  }


  @JsonProperty(JSON_PROPERTY_DATE_FIN)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setDateFin(Date dateFin) {
    this.dateFin = dateFin;
  }


  public AffectationMasse gestionnaire(String gestionnaire) {
    this.gestionnaire = gestionnaire;
    return this;
  }

  /**
   * gestionnaire
   * @return gestionnaire
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_GESTIONNAIRE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getGestionnaire() {
    return gestionnaire;
  }


  @JsonProperty(JSON_PROPERTY_GESTIONNAIRE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setGestionnaire(String gestionnaire) {
    this.gestionnaire = gestionnaire;
  }


  public AffectationMasse type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Le type du traitement
   * @return type
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_TYPE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getType() {
    return type;
  }


  @JsonProperty(JSON_PROPERTY_TYPE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setType(String type) {
    this.type = type;
  }


  /**
   * Return true if this AffectationMasse object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AffectationMasse affectationMasse = (AffectationMasse) o;
    return Objects.equals(this.id, affectationMasse.id) &&
        Objects.equals(this.codeObjetMaquette, affectationMasse.codeObjetMaquette) &&
        Objects.equals(this.libelleCourtOM, affectationMasse.libelleCourtOM) &&
        Objects.equals(this.codeChemin, affectationMasse.codeChemin) &&
        Objects.equals(this.etat, affectationMasse.etat) &&
        Objects.equals(this.dateDebut, affectationMasse.dateDebut) &&
        Objects.equals(this.dateFin, affectationMasse.dateFin) &&
        Objects.equals(this.gestionnaire, affectationMasse.gestionnaire) &&
        Objects.equals(this.type, affectationMasse.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, codeObjetMaquette, libelleCourtOM, codeChemin, etat, dateDebut, dateFin, gestionnaire, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AffectationMasse {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    codeObjetMaquette: ").append(toIndentedString(codeObjetMaquette)).append("\n");
    sb.append("    libelleCourtOM: ").append(toIndentedString(libelleCourtOM)).append("\n");
    sb.append("    codeChemin: ").append(toIndentedString(codeChemin)).append("\n");
    sb.append("    etat: ").append(toIndentedString(etat)).append("\n");
    sb.append("    dateDebut: ").append(toIndentedString(dateDebut)).append("\n");
    sb.append("    dateFin: ").append(toIndentedString(dateFin)).append("\n");
    sb.append("    gestionnaire: ").append(toIndentedString(gestionnaire)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

    // add `id` to the URL query string
    if (getId() != null) {
      joiner.add(String.format("%sid%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getId()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `codeObjetMaquette` to the URL query string
    if (getCodeObjetMaquette() != null) {
      joiner.add(String.format("%scodeObjetMaquette%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCodeObjetMaquette()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `libelleCourtOM` to the URL query string
    if (getLibelleCourtOM() != null) {
      joiner.add(String.format("%slibelleCourtOM%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getLibelleCourtOM()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `codeChemin` to the URL query string
    if (getCodeChemin() != null) {
      joiner.add(String.format("%scodeChemin%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCodeChemin()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `etat` to the URL query string
    if (getEtat() != null) {
      joiner.add(String.format("%setat%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getEtat()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `dateDebut` to the URL query string
    if (getDateDebut() != null) {
      joiner.add(String.format("%sdateDebut%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getDateDebut()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `dateFin` to the URL query string
    if (getDateFin() != null) {
      joiner.add(String.format("%sdateFin%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getDateFin()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `gestionnaire` to the URL query string
    if (getGestionnaire() != null) {
      joiner.add(String.format("%sgestionnaire%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getGestionnaire()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `type` to the URL query string
    if (getType() != null) {
      joiner.add(String.format("%stype%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getType()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    return joiner.toString();
  }
}

