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
 * AcquisCapitalise
 */
@JsonPropertyOrder({
  AcquisCapitalise.JSON_PROPERTY_LIBELLE_AFFICHAGE_PERIODE_ACQUISITION,
  AcquisCapitalise.JSON_PROPERTY_CODE_OBJET_FORMATION_ACQUISITION,
  AcquisCapitalise.JSON_PROPERTY_TROUVE_VIA_LIEN_CORRESPONDANCE_POUR_CALCUL,
  AcquisCapitalise.JSON_PROPERTY_NOTE_FINALE,
  AcquisCapitalise.JSON_PROPERTY_BAREME,
  AcquisCapitalise.JSON_PROPERTY_LIBELLE_RESULTAT_FINAL
})
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-04-24T14:22:57.626784935+02:00[Europe/Paris]", comments = "Generator version: 7.12.0")
public class AcquisCapitalise {
  public static final String JSON_PROPERTY_LIBELLE_AFFICHAGE_PERIODE_ACQUISITION = "libelleAffichagePeriodeAcquisition";
  @jakarta.annotation.Nonnull
  private String libelleAffichagePeriodeAcquisition;

  public static final String JSON_PROPERTY_CODE_OBJET_FORMATION_ACQUISITION = "codeObjetFormationAcquisition";
  @jakarta.annotation.Nonnull
  private String codeObjetFormationAcquisition;

  public static final String JSON_PROPERTY_TROUVE_VIA_LIEN_CORRESPONDANCE_POUR_CALCUL = "trouveViaLienCorrespondancePourCalcul";
  @jakarta.annotation.Nonnull
  private Boolean trouveViaLienCorrespondancePourCalcul;

  public static final String JSON_PROPERTY_NOTE_FINALE = "noteFinale";
  private JsonNullable<String> noteFinale = JsonNullable.<String>undefined();

  public static final String JSON_PROPERTY_BAREME = "bareme";
  private JsonNullable<Integer> bareme = JsonNullable.<Integer>undefined();

  public static final String JSON_PROPERTY_LIBELLE_RESULTAT_FINAL = "libelleResultatFinal";
  @jakarta.annotation.Nonnull
  private String libelleResultatFinal;

  public AcquisCapitalise() { 
  }

  public AcquisCapitalise libelleAffichagePeriodeAcquisition(@jakarta.annotation.Nonnull String libelleAffichagePeriodeAcquisition) {
    this.libelleAffichagePeriodeAcquisition = libelleAffichagePeriodeAcquisition;
    return this;
  }

  /**
   * Code de la période à laquelle il y a eu l&#39;acquisition
   * @return libelleAffichagePeriodeAcquisition
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_LIBELLE_AFFICHAGE_PERIODE_ACQUISITION)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public String getLibelleAffichagePeriodeAcquisition() {
    return libelleAffichagePeriodeAcquisition;
  }


  @JsonProperty(JSON_PROPERTY_LIBELLE_AFFICHAGE_PERIODE_ACQUISITION)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setLibelleAffichagePeriodeAcquisition(@jakarta.annotation.Nonnull String libelleAffichagePeriodeAcquisition) {
    this.libelleAffichagePeriodeAcquisition = libelleAffichagePeriodeAcquisition;
  }


  public AcquisCapitalise codeObjetFormationAcquisition(@jakarta.annotation.Nonnull String codeObjetFormationAcquisition) {
    this.codeObjetFormationAcquisition = codeObjetFormationAcquisition;
    return this;
  }

  /**
   * L&#39;objet sur lequel il y a eu l&#39;acquisition (différent de l&#39;objet du chemin dans le cas des LCC par exemple)
   * @return codeObjetFormationAcquisition
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_CODE_OBJET_FORMATION_ACQUISITION)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public String getCodeObjetFormationAcquisition() {
    return codeObjetFormationAcquisition;
  }


  @JsonProperty(JSON_PROPERTY_CODE_OBJET_FORMATION_ACQUISITION)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setCodeObjetFormationAcquisition(@jakarta.annotation.Nonnull String codeObjetFormationAcquisition) {
    this.codeObjetFormationAcquisition = codeObjetFormationAcquisition;
  }


  public AcquisCapitalise trouveViaLienCorrespondancePourCalcul(@jakarta.annotation.Nonnull Boolean trouveViaLienCorrespondancePourCalcul) {
    this.trouveViaLienCorrespondancePourCalcul = trouveViaLienCorrespondancePourCalcul;
    return this;
  }

  /**
   * Vrai si le code de l&#39;OF acquis est différent du code OM
   * @return trouveViaLienCorrespondancePourCalcul
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_TROUVE_VIA_LIEN_CORRESPONDANCE_POUR_CALCUL)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public Boolean getTrouveViaLienCorrespondancePourCalcul() {
    return trouveViaLienCorrespondancePourCalcul;
  }


  @JsonProperty(JSON_PROPERTY_TROUVE_VIA_LIEN_CORRESPONDANCE_POUR_CALCUL)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setTrouveViaLienCorrespondancePourCalcul(@jakarta.annotation.Nonnull Boolean trouveViaLienCorrespondancePourCalcul) {
    this.trouveViaLienCorrespondancePourCalcul = trouveViaLienCorrespondancePourCalcul;
  }


  public AcquisCapitalise noteFinale(@jakarta.annotation.Nullable String noteFinale) {
    this.noteFinale = JsonNullable.<String>of(noteFinale);
    return this;
  }

  /**
   * La note finale
   * @return noteFinale
   */
  @jakarta.annotation.Nullable
  @JsonIgnore
  public String getNoteFinale() {
        return noteFinale.orElse(null);
  }

  @JsonProperty(JSON_PROPERTY_NOTE_FINALE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public JsonNullable<String> getNoteFinale_JsonNullable() {
    return noteFinale;
  }
  
  @JsonProperty(JSON_PROPERTY_NOTE_FINALE)
  public void setNoteFinale_JsonNullable(JsonNullable<String> noteFinale) {
    this.noteFinale = noteFinale;
  }

  public void setNoteFinale(@jakarta.annotation.Nullable String noteFinale) {
    this.noteFinale = JsonNullable.<String>of(noteFinale);
  }


  public AcquisCapitalise bareme(@jakarta.annotation.Nullable Integer bareme) {
    this.bareme = JsonNullable.<Integer>of(bareme);
    return this;
  }

  /**
   * Le barème (le maximum) de la note Obligatoirement renseigné si la note finale est présente.
   * @return bareme
   */
  @jakarta.annotation.Nullable
  @JsonIgnore
  public Integer getBareme() {
        return bareme.orElse(null);
  }

  @JsonProperty(JSON_PROPERTY_BAREME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public JsonNullable<Integer> getBareme_JsonNullable() {
    return bareme;
  }
  
  @JsonProperty(JSON_PROPERTY_BAREME)
  public void setBareme_JsonNullable(JsonNullable<Integer> bareme) {
    this.bareme = bareme;
  }

  public void setBareme(@jakarta.annotation.Nullable Integer bareme) {
    this.bareme = JsonNullable.<Integer>of(bareme);
  }


  public AcquisCapitalise libelleResultatFinal(@jakarta.annotation.Nonnull String libelleResultatFinal) {
    this.libelleResultatFinal = libelleResultatFinal;
    return this;
  }

  /**
   * Le libellé long du résultat final
   * @return libelleResultatFinal
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_LIBELLE_RESULTAT_FINAL)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public String getLibelleResultatFinal() {
    return libelleResultatFinal;
  }


  @JsonProperty(JSON_PROPERTY_LIBELLE_RESULTAT_FINAL)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setLibelleResultatFinal(@jakarta.annotation.Nonnull String libelleResultatFinal) {
    this.libelleResultatFinal = libelleResultatFinal;
  }


  /**
   * Return true if this AcquisCapitalise object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AcquisCapitalise acquisCapitalise = (AcquisCapitalise) o;
    return Objects.equals(this.libelleAffichagePeriodeAcquisition, acquisCapitalise.libelleAffichagePeriodeAcquisition) &&
        Objects.equals(this.codeObjetFormationAcquisition, acquisCapitalise.codeObjetFormationAcquisition) &&
        Objects.equals(this.trouveViaLienCorrespondancePourCalcul, acquisCapitalise.trouveViaLienCorrespondancePourCalcul) &&
        equalsNullable(this.noteFinale, acquisCapitalise.noteFinale) &&
        equalsNullable(this.bareme, acquisCapitalise.bareme) &&
        Objects.equals(this.libelleResultatFinal, acquisCapitalise.libelleResultatFinal);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(libelleAffichagePeriodeAcquisition, codeObjetFormationAcquisition, trouveViaLienCorrespondancePourCalcul, hashCodeNullable(noteFinale), hashCodeNullable(bareme), libelleResultatFinal);
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
    sb.append("class AcquisCapitalise {\n");
    sb.append("    libelleAffichagePeriodeAcquisition: ").append(toIndentedString(libelleAffichagePeriodeAcquisition)).append("\n");
    sb.append("    codeObjetFormationAcquisition: ").append(toIndentedString(codeObjetFormationAcquisition)).append("\n");
    sb.append("    trouveViaLienCorrespondancePourCalcul: ").append(toIndentedString(trouveViaLienCorrespondancePourCalcul)).append("\n");
    sb.append("    noteFinale: ").append(toIndentedString(noteFinale)).append("\n");
    sb.append("    bareme: ").append(toIndentedString(bareme)).append("\n");
    sb.append("    libelleResultatFinal: ").append(toIndentedString(libelleResultatFinal)).append("\n");
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

    // add `libelleAffichagePeriodeAcquisition` to the URL query string
    if (getLibelleAffichagePeriodeAcquisition() != null) {
      joiner.add(String.format("%slibelleAffichagePeriodeAcquisition%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getLibelleAffichagePeriodeAcquisition()))));
    }

    // add `codeObjetFormationAcquisition` to the URL query string
    if (getCodeObjetFormationAcquisition() != null) {
      joiner.add(String.format("%scodeObjetFormationAcquisition%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getCodeObjetFormationAcquisition()))));
    }

    // add `trouveViaLienCorrespondancePourCalcul` to the URL query string
    if (getTrouveViaLienCorrespondancePourCalcul() != null) {
      joiner.add(String.format("%strouveViaLienCorrespondancePourCalcul%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getTrouveViaLienCorrespondancePourCalcul()))));
    }

    // add `noteFinale` to the URL query string
    if (getNoteFinale() != null) {
      joiner.add(String.format("%snoteFinale%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getNoteFinale()))));
    }

    // add `bareme` to the URL query string
    if (getBareme() != null) {
      joiner.add(String.format("%sbareme%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getBareme()))));
    }

    // add `libelleResultatFinal` to the URL query string
    if (getLibelleResultatFinal() != null) {
      joiner.add(String.format("%slibelleResultatFinal%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getLibelleResultatFinal()))));
    }

    return joiner.toString();
  }
}

