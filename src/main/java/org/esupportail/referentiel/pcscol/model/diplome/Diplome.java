/*
 * MOF Application v1 - Mise en œuvre de l'offre de formation - Bloc «application»
 * Liste l'ensemble des services et des opérations utilisées par l'application front du module MOF (Mise en œuvre de l'offre de formation)  ### Authentification/autorisation obligatoire  Pour tout appel à une opération vous devez être authentifié/authorisé (voir le paragraphe [Authentification](#section/Authentication) pour les détails).  ### Type de données  Sauf indications spécifiques données au niveau de l'opération, les types de données utilisés dans cette API sont les suivants :  * string - Chaîne de caractères encodée en UTF8 (ex : `Une chaîne de caractère`)    * Dans le cas des descripteurs de type `codeXxx`, seuls les caractères de A à Z, de 0 à 9 et le tiret(-) sont autorisés    * Dans le cas des descripteurs de type montant ou nombre avec une partie décimale, seuls les caractères de 0 à 9 et le point(.) sont autorisés (ex : `12525.99`)  * string($date) - Une date sous la forme d'une chaîne de caractères (ex : `2020-02-25`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))  * string($date-time) - Une date et heure avec fuseau horaire sous la forme d'une chaîne de caractères (ex : `2020-02-25T18:36:22+02:00`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))  * integer($int64) - Un entier sur 64 bits (de -9 223 372 036 854 775 808 à 9 223 372 036 854 775 807) (ex : `2542`)  * integer($int32) - Un entier sur 32 bits (de –2 147 483 648 à 2 147 483 647) (ex : `2542`)  * boolean - Un booléen représenté par `true` ou `false`  ### Code retour   * 200 - Ok : L'opération s'est déroulée avec succès  * 201 - Created : L'opération a aboutie à la création d'une ressource  * 400 - Bad request :    * Un ou des paramètres d'entrées sont erronées    * Une erreur fonctionnelle s'est produite  * 404 - Not Found : La ressource demandée n'est pas trouvé    * Remarque : Dans le cas des opérations retournant une liste, on recevra un code 200 avec en résultat une liste vide  * 500 - Internal server error : Erreur inattendue et non gérés 
 *
 * The version of the OpenAPI document: 2.6.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.esupportail.referentiel.pcscol.model.diplome;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModelProperty;


/**
 * Diplome
 */
@JsonPropertyOrder({
  Diplome.JSON_PROPERTY_CODE,
  Diplome.JSON_PROPERTY_TYPE,
  Diplome.JSON_PROPERTY_VERSION,
  Diplome.JSON_PROPERTY_LIBELLE_COURT,
  Diplome.JSON_PROPERTY_DENOMINATION,
  Diplome.JSON_PROPERTY_VALIDITE,
  Diplome.JSON_PROPERTY_ACTIF,
  Diplome.JSON_PROPERTY_VERSION_ACTIVE
})
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-02-16T08:54:45.925006+01:00[Europe/Paris]")
public class Diplome {
  public static final String JSON_PROPERTY_CODE = "code";
  private String code;

  public static final String JSON_PROPERTY_TYPE = "type";
  private ObjetLibelle type;

  public static final String JSON_PROPERTY_VERSION = "version";
  private Integer version;

  public static final String JSON_PROPERTY_LIBELLE_COURT = "libelleCourt";
  private String libelleCourt;

  public static final String JSON_PROPERTY_DENOMINATION = "denomination";
  private String denomination;

  public static final String JSON_PROPERTY_VALIDITE = "validite";
  private String validite;

  public static final String JSON_PROPERTY_ACTIF = "actif";
  private Boolean actif;

  public static final String JSON_PROPERTY_VERSION_ACTIVE = "versionActive";
  private Integer versionActive;

  public Diplome() { 
  }

  public Diplome code(String code) {
    this.code = code;
    return this;
  }

   /**
   * Code du diplome ou certificat ou autre
   * @return code
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Code du diplome ou certificat ou autre")
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


  public Diplome type(ObjetLibelle type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
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


  public Diplome version(Integer version) {
    this.version = version;
    return this;
  }

   /**
   * La version du diplôme, certificat ou autre
   * @return version
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "La version du diplôme, certificat ou autre")
  @JsonProperty(JSON_PROPERTY_VERSION)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Integer getVersion() {
    return version;
  }


  @JsonProperty(JSON_PROPERTY_VERSION)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setVersion(Integer version) {
    this.version = version;
  }


  public Diplome libelleCourt(String libelleCourt) {
    this.libelleCourt = libelleCourt;
    return this;
  }

   /**
   * Le libellé court du diplôme, certificat ou autre
   * @return libelleCourt
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Le libellé court du diplôme, certificat ou autre")
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


  public Diplome denomination(String denomination) {
    this.denomination = denomination;
    return this;
  }

   /**
   * La dénomination officielle du diplôme, certificat ou autre. Libellé utilisé dans l&#39;attestation de réussite, dans l&#39;édition du diplôme et dans le supplément au diplôme.
   * @return denomination
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "La dénomination officielle du diplôme, certificat ou autre. Libellé utilisé dans l'attestation de réussite, dans l'édition du diplôme et dans le supplément au diplôme.")
  @JsonProperty(JSON_PROPERTY_DENOMINATION)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getDenomination() {
    return denomination;
  }


  @JsonProperty(JSON_PROPERTY_DENOMINATION)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setDenomination(String denomination) {
    this.denomination = denomination;
  }


  public Diplome validite(String validite) {
    this.validite = validite;
    return this;
  }

   /**
   * La durée de validité du diplôme certificat ou autre
   * @return validite
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "La durée de validité du diplôme certificat ou autre")
  @JsonProperty(JSON_PROPERTY_VALIDITE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getValidite() {
    return validite;
  }


  @JsonProperty(JSON_PROPERTY_VALIDITE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setValidite(String validite) {
    this.validite = validite;
  }


  public Diplome actif(Boolean actif) {
    this.actif = actif;
    return this;
  }

   /**
   * La version du diplôme est-elle active
   * @return actif
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "La version du diplôme est-elle active")
  @JsonProperty(JSON_PROPERTY_ACTIF)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Boolean getActif() {
    return actif;
  }


  @JsonProperty(JSON_PROPERTY_ACTIF)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setActif(Boolean actif) {
    this.actif = actif;
  }


  public Diplome versionActive(Integer versionActive) {
    this.versionActive = versionActive;
    return this;
  }

   /**
   * Le numéro de la version active
   * @return versionActive
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Le numéro de la version active")
  @JsonProperty(JSON_PROPERTY_VERSION_ACTIVE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Integer getVersionActive() {
    return versionActive;
  }


  @JsonProperty(JSON_PROPERTY_VERSION_ACTIVE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setVersionActive(Integer versionActive) {
    this.versionActive = versionActive;
  }


  /**
   * Return true if this Diplome object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Diplome diplome = (Diplome) o;
    return Objects.equals(this.code, diplome.code) &&
        Objects.equals(this.type, diplome.type) &&
        Objects.equals(this.version, diplome.version) &&
        Objects.equals(this.libelleCourt, diplome.libelleCourt) &&
        Objects.equals(this.denomination, diplome.denomination) &&
        Objects.equals(this.validite, diplome.validite) &&
        Objects.equals(this.actif, diplome.actif) &&
        Objects.equals(this.versionActive, diplome.versionActive);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, type, version, libelleCourt, denomination, validite, actif, versionActive);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Diplome {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    libelleCourt: ").append(toIndentedString(libelleCourt)).append("\n");
    sb.append("    denomination: ").append(toIndentedString(denomination)).append("\n");
    sb.append("    validite: ").append(toIndentedString(validite)).append("\n");
    sb.append("    actif: ").append(toIndentedString(actif)).append("\n");
    sb.append("    versionActive: ").append(toIndentedString(versionActive)).append("\n");
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

}
