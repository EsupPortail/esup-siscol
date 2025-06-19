/*
 * CHC Externe v1 - API Externe choix du cursus
 * ### Introduction L’API répertorie l'ensemble des services et des opérations disponibles dans le module CHC (Choix du Cursus).  ### Authentification/autorisation obligatoire Pour tout appel à une opération, vous devez être authentifié/autorisé à l’aide d’un token JWT. Ainsi, chaque requête HTTP doit contenir un token valide dans le header HTTP `Authorization`. #### Répertoire de partage contenant la documentation décrivant l'authentification Pégase https://share.pc-scol.fr/f/4487c726ade84022ae16/?dl=1  Le format est `Authorization: Bearer <token-jwt>`. Par exemple : `Authorization: Bearer xxxx.yyyy.zzzz`. Vous pouvez recevoir l'un de ces codes retour si vous n’êtes pas authentifié ou autorisé : * 401 - Unauthorized - Vous n’êtes pas authentifié     * Il n’y a pas de token passé dans le header HTTP `Authorization`     * Le token passé n’est pas au bon format (Bearer <token-jwt>) * 403 - Forbidden - Vous êtes authentifié mais pas autorisé à exécuter cette opération     * La signature du token est incorrecte / n’a pas pu être vérifiée     * Le token est expiré     * Vérifier les droits de l’utilisateur * 500 - Internal Server Error     * Il n’est pas encore actif  ### Type de données Sauf indications spécifiques données au niveau de l'opération, les types de données utilisés dans cette API sont les suivants :  * string - Chaîne de caractères encodée en UTF8 (ex : `Une chaîne de caractère`)    * Dans le cas des descripteurs de type `codeXxx`, seuls les caractères de A à Z, de 0 à 9 et le tiret(-) sont autorisés  * string($date) - Une date sous la forme d'une chaîne de caractères (ex : `2020-02-25`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))  * integer($int64) - Un entier sur 64 bits (de -9 223 372 036 854 775 808 à 9 223 372 036 854 775 807) (ex : `2542`)  * integer($int32) - Un entier sur 32 bits (de –2 147 483 648 à 2 147 483 647) (ex : `2542`)  * number($double) - Un nombre à virgule flottante en double précision  * boolean - Un booléen représenté par `true` ou `false`  ### Code retour      | Code    | Description                                                                                                         |     |---------|---------------------------------------------------------------------------------------------------------------------|     | 200     | L'opération s'est déroulée avec succès                                                                              |     | 201     | L'opération a abouti à la création d'une ressource                                                                  |     | 400     | Un ou des paramètres d'entrée sont erronés                                                                          |     |         | Une erreur fonctionnelle s'est produite                                                                             |     | 404     | La ressource demandée n'est pas trouvée                                                                             |     |         | Remarque : Dans le cas des opérations retournant une liste, on recevra un code 200 avec en résultat une liste vide  |     | 500     | Erreur technique rencontrée par le serveur                                                                          |   ## Notions métiers  ### Objet Maquette (OM) Un objet maquette représente n'importe quel nœud d'un arbre de formation : Formation, objet de formation ou groupement.  Un objet maquette est identifié par le codeChemin (chemin pédagogique), le codePeriode et le codeStructure.  ### Formation Une formation est un arbre dont les nœuds sont des objets de formation et dont la racine est la formation elle-même.  Pour apparaître dans le module CHC, la formation doit être validée dans ODF.  Pour l'utiliser dans les différents actes métiers, il faut que chaque noeud et sa descendance soit ouvert au choix du cursus et qu'au moins une inscription administrative soit validée sur un objet maquette de l’arbre de la formation.  ### Objet formation Un objet de formation est l’un des nœuds de l’arbre de formation : année, semestre, UE, EC, enseignement, etc. (hors groupement).  Pour apparaître dans le Module CHC, un objet de formation doit être validé dans ODF.  ### Groupement Un groupement est une possibilité de structurer et d'organiser une formation. Il peut contenir des objets de formations de tous les types, être lié pour décomposer des objets pères de tous les types, être avec ou sans plage de choix.  ### Plage de choix Une plage de choix permet de contraindre l’apprenant lorsqu’il effectue son CHC dans Pégase. Cette plage de choix est matérialisée par un nombre minimum et un nombre maximum d’objets de formation à sélectionner. La plage de choix est contrôlée au cours du CHC.  ### Groupe Un Groupe est une entité permettant de diviser une population d’étudiants ou d'identifier une population spécifique d’étudiants inscrits administrativement et pédagogiquement.  ### Composition Une composition est une entité permettant de rassembler des groupes. Une composition contient obligatoirement au moins un groupe.  ### Période Une période de mise en œuvre correspond à la période pendant laquelle se déroule la formation.  Elle est le point d’entrée pour chaque acte métier du module CHC.  ### Apprenant Un apprenant est un usager qui suit un cursus et pour lequel des choix pédagogiques devront être réalisés.  ### Inscription L’inscription est l’ensemble des étapes de saisie des données concernant l’apprenant : état-civil, coordonnées, situation précédente, cursus, montant de l’inscription, mode de paiement. Cette saisie peut être faite par le gestionnaire ou l’apprenant.  Elle doit être vérifiée et validée par le gestionnaire. Une inscription n'est prise en compte dans CHC qu'à partir du moment où elle est validée ou annulée.  ### Cursus Le cursus est un arbre (une arborescence) composé d'objets maquette pour lequel des choix pédagogiques doivent être réalisés à chaque période de mise en oeuvre.  Un cursus est défini par le code de l’apprenant, un objet maquette lui-même identifié par le codeChemin (chemin pédagogique), le codePeriode et le codeStructure. Un choix pédagogique est une association entre un objet maquette et un apprenant. On recense parmi les choix pédagogiques des affectations, des aménagements et des acquis capitalisés.  ### Acquis capitalisé Un acquis capitalisé est un objet de formation dont les modalités de contrôle des connaissances sont capitalisables et dont le résultat positif a été obtenu sur une période antérieure. L'acquis capitalisé est créé et stocké dans CHC après délibération de jury du module COC. Il est utilisable sur une période postérieure à son acquisition  ### Chemin pédagogique Un chemin pédagogique identifie le lien d'un objet maquette à un autre objet maquette de sa descendance.  **Exemple** ``` MASTER-RH>MASTER-1>SEMESTRE-1>UE-OPTIONS>ESPAGNOL ```  ### Règles communes pour réaliser un choix de cursus * L’affectation est possible à partir de l'objet maquette porteur du point d'inscription administrative et sur les objets maquette de sa descendance à condition que l'inscription administrative soit validée dans le module INS. * Un choix du cursus sur un objet maquette est réalisable si le témoin ouvertChoixCursus est  à true. * La désaffectation d’un apprenant à un objet maquette provoque sa désaffectation automatique à tous les objets maquette de la descendance. * Pour chaque apprenant, il est possible de réaliser un choix du cursus sur un objet maquette dans un groupement à plage de choix tant que le nombre maximum autorisé (de la plage de choix) n'est pas atteint. La valeur minimum de plage de choix n'est  pas contrôlée. * Un choix pédagogique sur un objet maquette présent plusieurs fois dans un arbre de formation ne peut être réalisée qu’une fois pour un même cursus. * La capacité d’accueil d’un objet maquette n’est pas contrôlée dans l'API car non bloquante. Les capacités d’accueil dépassées sont négatives. * Les aménagements avec prise en compte acquis et aucun ne sont pas décomptés de la capacité d’accueil d’un objet maquette. * L'utilisation d'un acquis capitalisé empêche son affectation et supprime la branche de sa descendance.
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.esupportail.referentiel.pcscol.chcExterneV1.model;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

import org.esupportail.referentiel.pcscol.invoker.ApiClient;
import org.openapitools.jackson.nullable.JsonNullable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
/**
 * Un objet de formation
 */
@JsonPropertyOrder({
  ObjetFormationPourext.JSON_PROPERTY_CODE,
  ObjetFormationPourext.JSON_PROPERTY_LIBELLE_COURT,
  ObjetFormationPourext.JSON_PROPERTY_LIBELLE_LONG,
  ObjetFormationPourext.JSON_PROPERTY_TYPE_OBJET_FORMATION,
  ObjetFormationPourext.JSON_PROPERTY_CARACTERE_OBLIGATOIRE,
  ObjetFormationPourext.JSON_PROPERTY_CAPACITE_ACCUEIL,
  ObjetFormationPourext.JSON_PROPERTY_CHEMIN
})
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-04-24T14:22:57.626784935+02:00[Europe/Paris]", comments = "Generator version: 7.12.0")
public class ObjetFormationPourext {
  public static final String JSON_PROPERTY_CODE = "code";
  @jakarta.annotation.Nonnull
  private String code;

  public static final String JSON_PROPERTY_LIBELLE_COURT = "libelleCourt";
  @jakarta.annotation.Nonnull
  private String libelleCourt;

  public static final String JSON_PROPERTY_LIBELLE_LONG = "libelleLong";
  @jakarta.annotation.Nonnull
  private String libelleLong;

  public static final String JSON_PROPERTY_TYPE_OBJET_FORMATION = "typeObjetFormation";
  @jakarta.annotation.Nonnull
  private TypeObjetFormation typeObjetFormation;

  public static final String JSON_PROPERTY_CARACTERE_OBLIGATOIRE = "caractereObligatoire";
  @jakarta.annotation.Nonnull
  private Boolean caractereObligatoire;

  public static final String JSON_PROPERTY_CAPACITE_ACCUEIL = "capaciteAccueil";
  private JsonNullable<Integer> capaciteAccueil = JsonNullable.<Integer>undefined();

  public static final String JSON_PROPERTY_CHEMIN = "chemin";
  @jakarta.annotation.Nonnull
  private String chemin;

  public ObjetFormationPourext() { 
  }

  public ObjetFormationPourext code(@jakarta.annotation.Nonnull String code) {
    this.code = code;
    return this;
  }

  /**
   * Le code de l&#39;objet de formation
   * @return code
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_CODE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public String getCode() {
    return code;
  }


  @JsonProperty(JSON_PROPERTY_CODE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setCode(@jakarta.annotation.Nonnull String code) {
    this.code = code;
  }


  public ObjetFormationPourext libelleCourt(@jakarta.annotation.Nonnull String libelleCourt) {
    this.libelleCourt = libelleCourt;
    return this;
  }

  /**
   * Le libellé court de l&#39;objet de formation
   * @return libelleCourt
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_LIBELLE_COURT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public String getLibelleCourt() {
    return libelleCourt;
  }


  @JsonProperty(JSON_PROPERTY_LIBELLE_COURT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setLibelleCourt(@jakarta.annotation.Nonnull String libelleCourt) {
    this.libelleCourt = libelleCourt;
  }


  public ObjetFormationPourext libelleLong(@jakarta.annotation.Nonnull String libelleLong) {
    this.libelleLong = libelleLong;
    return this;
  }

  /**
   * Le libellé long de l&#39;objet de formation
   * @return libelleLong
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_LIBELLE_LONG)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public String getLibelleLong() {
    return libelleLong;
  }


  @JsonProperty(JSON_PROPERTY_LIBELLE_LONG)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setLibelleLong(@jakarta.annotation.Nonnull String libelleLong) {
    this.libelleLong = libelleLong;
  }


  public ObjetFormationPourext typeObjetFormation(@jakarta.annotation.Nonnull TypeObjetFormation typeObjetFormation) {
    this.typeObjetFormation = typeObjetFormation;
    return this;
  }

  /**
   * Get typeObjetFormation
   * @return typeObjetFormation
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_TYPE_OBJET_FORMATION)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public TypeObjetFormation getTypeObjetFormation() {
    return typeObjetFormation;
  }


  @JsonProperty(JSON_PROPERTY_TYPE_OBJET_FORMATION)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setTypeObjetFormation(@jakarta.annotation.Nonnull TypeObjetFormation typeObjetFormation) {
    this.typeObjetFormation = typeObjetFormation;
  }


  public ObjetFormationPourext caractereObligatoire(@jakarta.annotation.Nonnull Boolean caractereObligatoire) {
    this.caractereObligatoire = caractereObligatoire;
    return this;
  }

  /**
   * L&#39;objet de formation est-il obligatoire au choix du cursus ?
   * @return caractereObligatoire
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_CARACTERE_OBLIGATOIRE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public Boolean getCaractereObligatoire() {
    return caractereObligatoire;
  }


  @JsonProperty(JSON_PROPERTY_CARACTERE_OBLIGATOIRE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setCaractereObligatoire(@jakarta.annotation.Nonnull Boolean caractereObligatoire) {
    this.caractereObligatoire = caractereObligatoire;
  }


  public ObjetFormationPourext capaciteAccueil(@jakarta.annotation.Nullable Integer capaciteAccueil) {
    this.capaciteAccueil = JsonNullable.<Integer>of(capaciteAccueil);
    return this;
  }

  /**
   * La capacité d&#39;accueil de l&#39;objet de formation
   * @return capaciteAccueil
   */
  @jakarta.annotation.Nullable
  @JsonIgnore
  public Integer getCapaciteAccueil() {
        return capaciteAccueil.orElse(null);
  }

  @JsonProperty(JSON_PROPERTY_CAPACITE_ACCUEIL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public JsonNullable<Integer> getCapaciteAccueil_JsonNullable() {
    return capaciteAccueil;
  }
  
  @JsonProperty(JSON_PROPERTY_CAPACITE_ACCUEIL)
  public void setCapaciteAccueil_JsonNullable(JsonNullable<Integer> capaciteAccueil) {
    this.capaciteAccueil = capaciteAccueil;
  }

  public void setCapaciteAccueil(@jakarta.annotation.Nullable Integer capaciteAccueil) {
    this.capaciteAccueil = JsonNullable.<Integer>of(capaciteAccueil);
  }


  public ObjetFormationPourext chemin(@jakarta.annotation.Nonnull String chemin) {
    this.chemin = chemin;
    return this;
  }

  /**
   * Le code chemin de l&#39;objet de formation
   * @return chemin
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_CHEMIN)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public String getChemin() {
    return chemin;
  }


  @JsonProperty(JSON_PROPERTY_CHEMIN)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setChemin(@jakarta.annotation.Nonnull String chemin) {
    this.chemin = chemin;
  }


  /**
   * Return true if this ObjetFormationPourext object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ObjetFormationPourext objetFormationPourext = (ObjetFormationPourext) o;
    return Objects.equals(this.code, objetFormationPourext.code) &&
        Objects.equals(this.libelleCourt, objetFormationPourext.libelleCourt) &&
        Objects.equals(this.libelleLong, objetFormationPourext.libelleLong) &&
        Objects.equals(this.typeObjetFormation, objetFormationPourext.typeObjetFormation) &&
        Objects.equals(this.caractereObligatoire, objetFormationPourext.caractereObligatoire) &&
        equalsNullable(this.capaciteAccueil, objetFormationPourext.capaciteAccueil) &&
        Objects.equals(this.chemin, objetFormationPourext.chemin);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, libelleCourt, libelleLong, typeObjetFormation, caractereObligatoire, hashCodeNullable(capaciteAccueil), chemin);
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ObjetFormationPourext {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    libelleCourt: ").append(toIndentedString(libelleCourt)).append("\n");
    sb.append("    libelleLong: ").append(toIndentedString(libelleLong)).append("\n");
    sb.append("    typeObjetFormation: ").append(toIndentedString(typeObjetFormation)).append("\n");
    sb.append("    caractereObligatoire: ").append(toIndentedString(caractereObligatoire)).append("\n");
    sb.append("    capaciteAccueil: ").append(toIndentedString(capaciteAccueil)).append("\n");
    sb.append("    chemin: ").append(toIndentedString(chemin)).append("\n");
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
      joiner.add(String.format("%scode%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getCode()))));
    }

    // add `libelleCourt` to the URL query string
    if (getLibelleCourt() != null) {
      joiner.add(String.format("%slibelleCourt%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getLibelleCourt()))));
    }

    // add `libelleLong` to the URL query string
    if (getLibelleLong() != null) {
      joiner.add(String.format("%slibelleLong%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getLibelleLong()))));
    }

    // add `typeObjetFormation` to the URL query string
    if (getTypeObjetFormation() != null) {
      joiner.add(getTypeObjetFormation().toUrlQueryString(prefix + "typeObjetFormation" + suffix));
    }

    // add `caractereObligatoire` to the URL query string
    if (getCaractereObligatoire() != null) {
      joiner.add(String.format("%scaractereObligatoire%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getCaractereObligatoire()))));
    }

    // add `capaciteAccueil` to the URL query string
    if (getCapaciteAccueil() != null) {
      joiner.add(String.format("%scapaciteAccueil%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getCapaciteAccueil()))));
    }

    // add `chemin` to the URL query string
    if (getChemin() != null) {
      joiner.add(String.format("%schemin%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getChemin()))));
    }

    return joiner.toString();
  }
}

