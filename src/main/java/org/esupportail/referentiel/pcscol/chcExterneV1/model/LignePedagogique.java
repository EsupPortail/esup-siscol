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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

import org.esupportail.referentiel.pcscol.invoker.ApiClient;
import org.openapitools.jackson.nullable.JsonNullable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
/**
 * Informations pédagogiques pour un code chemin
 */
@JsonPropertyOrder({
  LignePedagogique.JSON_PROPERTY_CODE_OBJET_MAQUETTE,
  LignePedagogique.JSON_PROPERTY_LIBELLE_LONG_OBJET_MAQUETTE,
  LignePedagogique.JSON_PROPERTY_LIBELLE_AFFICHAGE_TYPE_OBJET_FORMATION,
  LignePedagogique.JSON_PROPERTY_EST_OBLIGATOIRE,
  LignePedagogique.JSON_PROPERTY_CREDIT_ECTS,
  LignePedagogique.JSON_PROPERTY_AMENAGEMENTS,
  LignePedagogique.JSON_PROPERTY_ACQUIS_CAPITALISE,
  LignePedagogique.JSON_PROPERTY_TYPE_CHOIX_PEDAGOGIQUE,
  LignePedagogique.JSON_PROPERTY_ENFANTS
})
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-04-24T14:22:57.626784935+02:00[Europe/Paris]", comments = "Generator version: 7.12.0")
public class LignePedagogique {
  public static final String JSON_PROPERTY_CODE_OBJET_MAQUETTE = "codeObjetMaquette";
  @jakarta.annotation.Nonnull
  private String codeObjetMaquette;

  public static final String JSON_PROPERTY_LIBELLE_LONG_OBJET_MAQUETTE = "libelleLongObjetMaquette";
  @jakarta.annotation.Nonnull
  private String libelleLongObjetMaquette;

  public static final String JSON_PROPERTY_LIBELLE_AFFICHAGE_TYPE_OBJET_FORMATION = "libelleAffichageTypeObjetFormation";
  @jakarta.annotation.Nonnull
  private String libelleAffichageTypeObjetFormation;

  public static final String JSON_PROPERTY_EST_OBLIGATOIRE = "estObligatoire";
  @jakarta.annotation.Nonnull
  private Boolean estObligatoire;

  public static final String JSON_PROPERTY_CREDIT_ECTS = "creditEcts";
  private JsonNullable<Double> creditEcts = JsonNullable.<Double>undefined();

  public static final String JSON_PROPERTY_AMENAGEMENTS = "amenagements";
  @jakarta.annotation.Nonnull
  private List<Amenagement> amenagements = new ArrayList<>();

  public static final String JSON_PROPERTY_ACQUIS_CAPITALISE = "acquisCapitalise";
  private JsonNullable<AcquisCapitalise> acquisCapitalise = JsonNullable.<AcquisCapitalise>undefined();

  /**
   * Enumération des différents types de choix pédagogique
   */
  public enum TypeChoixPedagogiqueEnum {
    PAS_DE_CHC(String.valueOf("PAS_DE_CHC")),
    
    DISPENSE_CHOISIE(String.valueOf("DISPENSE_CHOISIE")),
    
    AFFECTATION_CHOISIE(String.valueOf("AFFECTATION_CHOISIE")),
    
    ACQUIS(String.valueOf("ACQUIS")),
    
    CAPITALISE_CHOISI(String.valueOf("CAPITALISE_CHOISI")),
    
    RENONCIATION_CHOISIE(String.valueOf("RENONCIATION_CHOISIE"));

    private String value;

    TypeChoixPedagogiqueEnum(String value) {
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
    public static TypeChoixPedagogiqueEnum fromValue(String value) {
      for (TypeChoixPedagogiqueEnum b : TypeChoixPedagogiqueEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_TYPE_CHOIX_PEDAGOGIQUE = "typeChoixPedagogique";
  @jakarta.annotation.Nonnull
  private TypeChoixPedagogiqueEnum typeChoixPedagogique;

  public static final String JSON_PROPERTY_ENFANTS = "enfants";
  @jakarta.annotation.Nonnull
  private List<LignePedagogique> enfants = new ArrayList<>();

  public LignePedagogique() { 
  }

  public LignePedagogique codeObjetMaquette(@jakarta.annotation.Nonnull String codeObjetMaquette) {
    this.codeObjetMaquette = codeObjetMaquette;
    return this;
  }

  /**
   * Le code de l&#39;objet maquette (formation, objet formation ou groupement)
   * @return codeObjetMaquette
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_CODE_OBJET_MAQUETTE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public String getCodeObjetMaquette() {
    return codeObjetMaquette;
  }


  @JsonProperty(JSON_PROPERTY_CODE_OBJET_MAQUETTE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setCodeObjetMaquette(@jakarta.annotation.Nonnull String codeObjetMaquette) {
    this.codeObjetMaquette = codeObjetMaquette;
  }


  public LignePedagogique libelleLongObjetMaquette(@jakarta.annotation.Nonnull String libelleLongObjetMaquette) {
    this.libelleLongObjetMaquette = libelleLongObjetMaquette;
    return this;
  }

  /**
   * Le libellé long de l&#39;objet maquette (formation, objet formation ou groupement)
   * @return libelleLongObjetMaquette
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_LIBELLE_LONG_OBJET_MAQUETTE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public String getLibelleLongObjetMaquette() {
    return libelleLongObjetMaquette;
  }


  @JsonProperty(JSON_PROPERTY_LIBELLE_LONG_OBJET_MAQUETTE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setLibelleLongObjetMaquette(@jakarta.annotation.Nonnull String libelleLongObjetMaquette) {
    this.libelleLongObjetMaquette = libelleLongObjetMaquette;
  }


  public LignePedagogique libelleAffichageTypeObjetFormation(@jakarta.annotation.Nonnull String libelleAffichageTypeObjetFormation) {
    this.libelleAffichageTypeObjetFormation = libelleAffichageTypeObjetFormation;
    return this;
  }

  /**
   * Le libellé d&#39;affichage du type d&#39;objet de formation
   * @return libelleAffichageTypeObjetFormation
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_LIBELLE_AFFICHAGE_TYPE_OBJET_FORMATION)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public String getLibelleAffichageTypeObjetFormation() {
    return libelleAffichageTypeObjetFormation;
  }


  @JsonProperty(JSON_PROPERTY_LIBELLE_AFFICHAGE_TYPE_OBJET_FORMATION)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setLibelleAffichageTypeObjetFormation(@jakarta.annotation.Nonnull String libelleAffichageTypeObjetFormation) {
    this.libelleAffichageTypeObjetFormation = libelleAffichageTypeObjetFormation;
  }


  public LignePedagogique estObligatoire(@jakarta.annotation.Nonnull Boolean estObligatoire) {
    this.estObligatoire = estObligatoire;
    return this;
  }

  /**
   * Si l&#39;objet maquette est obligatoire pour ce code chemin
   * @return estObligatoire
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_EST_OBLIGATOIRE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public Boolean getEstObligatoire() {
    return estObligatoire;
  }


  @JsonProperty(JSON_PROPERTY_EST_OBLIGATOIRE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setEstObligatoire(@jakarta.annotation.Nonnull Boolean estObligatoire) {
    this.estObligatoire = estObligatoire;
  }


  public LignePedagogique creditEcts(@jakarta.annotation.Nullable Double creditEcts) {
    this.creditEcts = JsonNullable.<Double>of(creditEcts);
    return this;
  }

  /**
   * Le nombre de crédits ECTS de l&#39;objet maquette pour ce code chemin
   * @return creditEcts
   */
  @jakarta.annotation.Nullable
  @JsonIgnore
  public Double getCreditEcts() {
        return creditEcts.orElse(null);
  }

  @JsonProperty(JSON_PROPERTY_CREDIT_ECTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public JsonNullable<Double> getCreditEcts_JsonNullable() {
    return creditEcts;
  }
  
  @JsonProperty(JSON_PROPERTY_CREDIT_ECTS)
  public void setCreditEcts_JsonNullable(JsonNullable<Double> creditEcts) {
    this.creditEcts = creditEcts;
  }

  public void setCreditEcts(@jakarta.annotation.Nullable Double creditEcts) {
    this.creditEcts = JsonNullable.<Double>of(creditEcts);
  }


  public LignePedagogique amenagements(@jakarta.annotation.Nonnull List<Amenagement> amenagements) {
    this.amenagements = amenagements;
    return this;
  }

  public LignePedagogique addAmenagementsItem(Amenagement amenagementsItem) {
    if (this.amenagements == null) {
      this.amenagements = new ArrayList<>();
    }
    this.amenagements.add(amenagementsItem);
    return this;
  }

  /**
   * La liste des aménagements du choix pédagogique
   * @return amenagements
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_AMENAGEMENTS)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public List<Amenagement> getAmenagements() {
    return amenagements;
  }


  @JsonProperty(JSON_PROPERTY_AMENAGEMENTS)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setAmenagements(@jakarta.annotation.Nonnull List<Amenagement> amenagements) {
    this.amenagements = amenagements;
  }


  public LignePedagogique acquisCapitalise(@jakarta.annotation.Nullable AcquisCapitalise acquisCapitalise) {
    this.acquisCapitalise = JsonNullable.<AcquisCapitalise>of(acquisCapitalise);
    return this;
  }

  /**
   * Get acquisCapitalise
   * @return acquisCapitalise
   */
  @jakarta.annotation.Nullable
  @JsonIgnore
  public AcquisCapitalise getAcquisCapitalise() {
        return acquisCapitalise.orElse(null);
  }

  @JsonProperty(JSON_PROPERTY_ACQUIS_CAPITALISE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public JsonNullable<AcquisCapitalise> getAcquisCapitalise_JsonNullable() {
    return acquisCapitalise;
  }
  
  @JsonProperty(JSON_PROPERTY_ACQUIS_CAPITALISE)
  public void setAcquisCapitalise_JsonNullable(JsonNullable<AcquisCapitalise> acquisCapitalise) {
    this.acquisCapitalise = acquisCapitalise;
  }

  public void setAcquisCapitalise(@jakarta.annotation.Nullable AcquisCapitalise acquisCapitalise) {
    this.acquisCapitalise = JsonNullable.<AcquisCapitalise>of(acquisCapitalise);
  }


  public LignePedagogique typeChoixPedagogique(@jakarta.annotation.Nonnull TypeChoixPedagogiqueEnum typeChoixPedagogique) {
    this.typeChoixPedagogique = typeChoixPedagogique;
    return this;
  }

  /**
   * Enumération des différents types de choix pédagogique
   * @return typeChoixPedagogique
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_TYPE_CHOIX_PEDAGOGIQUE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public TypeChoixPedagogiqueEnum getTypeChoixPedagogique() {
    return typeChoixPedagogique;
  }


  @JsonProperty(JSON_PROPERTY_TYPE_CHOIX_PEDAGOGIQUE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setTypeChoixPedagogique(@jakarta.annotation.Nonnull TypeChoixPedagogiqueEnum typeChoixPedagogique) {
    this.typeChoixPedagogique = typeChoixPedagogique;
  }


  public LignePedagogique enfants(@jakarta.annotation.Nonnull List<LignePedagogique> enfants) {
    this.enfants = enfants;
    return this;
  }

  public LignePedagogique addEnfantsItem(LignePedagogique enfantsItem) {
    if (this.enfants == null) {
      this.enfants = new ArrayList<>();
    }
    this.enfants.add(enfantsItem);
    return this;
  }

  /**
   * Les branches pédagogiques dans l&#39;arbre
   * @return enfants
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_ENFANTS)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public List<LignePedagogique> getEnfants() {
    return enfants;
  }


  @JsonProperty(JSON_PROPERTY_ENFANTS)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setEnfants(@jakarta.annotation.Nonnull List<LignePedagogique> enfants) {
    this.enfants = enfants;
  }


  /**
   * Return true if this LignePedagogique object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LignePedagogique lignePedagogique = (LignePedagogique) o;
    return Objects.equals(this.codeObjetMaquette, lignePedagogique.codeObjetMaquette) &&
        Objects.equals(this.libelleLongObjetMaquette, lignePedagogique.libelleLongObjetMaquette) &&
        Objects.equals(this.libelleAffichageTypeObjetFormation, lignePedagogique.libelleAffichageTypeObjetFormation) &&
        Objects.equals(this.estObligatoire, lignePedagogique.estObligatoire) &&
        equalsNullable(this.creditEcts, lignePedagogique.creditEcts) &&
        Objects.equals(this.amenagements, lignePedagogique.amenagements) &&
        equalsNullable(this.acquisCapitalise, lignePedagogique.acquisCapitalise) &&
        Objects.equals(this.typeChoixPedagogique, lignePedagogique.typeChoixPedagogique) &&
        Objects.equals(this.enfants, lignePedagogique.enfants);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(codeObjetMaquette, libelleLongObjetMaquette, libelleAffichageTypeObjetFormation, estObligatoire, hashCodeNullable(creditEcts), amenagements, hashCodeNullable(acquisCapitalise), typeChoixPedagogique, enfants);
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
    sb.append("class LignePedagogique {\n");
    sb.append("    codeObjetMaquette: ").append(toIndentedString(codeObjetMaquette)).append("\n");
    sb.append("    libelleLongObjetMaquette: ").append(toIndentedString(libelleLongObjetMaquette)).append("\n");
    sb.append("    libelleAffichageTypeObjetFormation: ").append(toIndentedString(libelleAffichageTypeObjetFormation)).append("\n");
    sb.append("    estObligatoire: ").append(toIndentedString(estObligatoire)).append("\n");
    sb.append("    creditEcts: ").append(toIndentedString(creditEcts)).append("\n");
    sb.append("    amenagements: ").append(toIndentedString(amenagements)).append("\n");
    sb.append("    acquisCapitalise: ").append(toIndentedString(acquisCapitalise)).append("\n");
    sb.append("    typeChoixPedagogique: ").append(toIndentedString(typeChoixPedagogique)).append("\n");
    sb.append("    enfants: ").append(toIndentedString(enfants)).append("\n");
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

    // add `codeObjetMaquette` to the URL query string
    if (getCodeObjetMaquette() != null) {
      joiner.add(String.format("%scodeObjetMaquette%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getCodeObjetMaquette()))));
    }

    // add `libelleLongObjetMaquette` to the URL query string
    if (getLibelleLongObjetMaquette() != null) {
      joiner.add(String.format("%slibelleLongObjetMaquette%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getLibelleLongObjetMaquette()))));
    }

    // add `libelleAffichageTypeObjetFormation` to the URL query string
    if (getLibelleAffichageTypeObjetFormation() != null) {
      joiner.add(String.format("%slibelleAffichageTypeObjetFormation%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getLibelleAffichageTypeObjetFormation()))));
    }

    // add `estObligatoire` to the URL query string
    if (getEstObligatoire() != null) {
      joiner.add(String.format("%sestObligatoire%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getEstObligatoire()))));
    }

    // add `creditEcts` to the URL query string
    if (getCreditEcts() != null) {
      joiner.add(String.format("%screditEcts%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getCreditEcts()))));
    }

    // add `amenagements` to the URL query string
    if (getAmenagements() != null) {
      for (int i = 0; i < getAmenagements().size(); i++) {
        if (getAmenagements().get(i) != null) {
          joiner.add(getAmenagements().get(i).toUrlQueryString(String.format("%samenagements%s%s", prefix, suffix,
          "".equals(suffix) ? "" : String.format("%s%d%s", containerPrefix, i, containerSuffix))));
        }
      }
    }

    // add `acquisCapitalise` to the URL query string
    if (getAcquisCapitalise() != null) {
      joiner.add(getAcquisCapitalise().toUrlQueryString(prefix + "acquisCapitalise" + suffix));
    }

    // add `typeChoixPedagogique` to the URL query string
    if (getTypeChoixPedagogique() != null) {
      joiner.add(String.format("%stypeChoixPedagogique%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getTypeChoixPedagogique()))));
    }

    // add `enfants` to the URL query string
    if (getEnfants() != null) {
      for (int i = 0; i < getEnfants().size(); i++) {
        if (getEnfants().get(i) != null) {
          joiner.add(getEnfants().get(i).toUrlQueryString(String.format("%senfants%s%s", prefix, suffix,
          "".equals(suffix) ? "" : String.format("%s%d%s", containerPrefix, i, containerSuffix))));
        }
      }
    }

    return joiner.toString();
  }
}

