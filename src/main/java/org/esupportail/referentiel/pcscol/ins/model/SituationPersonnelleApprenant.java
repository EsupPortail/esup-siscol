/*
 * INS Gestion V5
 * Il s'agit de l'API v5 de gestion - INS  __Apprenant :__ une personne qui a au moins une inscription validée dans Pegase.  __Inscription :__ se définit par une cible sur une période de mise en œuvre pour un apprenant. Une inscription peut prendre deux états : soit validée, soit annulée.  __Actualisation :__ permet de modifier les données liées à l’apprenant ou à l’inscription alors que la piste a déjà été payée ou validée.   __Gestion des erreurs :__   __200, 201 :__ opération effectuée   __400 :__ erreur de données sur les formats   __403 :__ accès refusé   __404 :__ contenu introuvable   __409 :__ donnée déjà existante   __500 :__ erreur serveur  # Changement majeur/cassant par rapport à V4  1. Suppression de `Inscription.noCandidat`.  1. Ajout de `VoeuBase.noCandidat` et `InscriptionComplete.noCandidat`.  1. Ajout de `VoeuBase.choisi`. 
 *
 * The version of the OpenAPI document: 27.0.1
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.esupportail.referentiel.pcscol.ins.model;

import java.util.Objects;
import java.util.StringJoiner;

import org.esupportail.referentiel.pcscol.invoker.ApiClient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
/**
 * SituationPersonnelleApprenant
 */
@JsonPropertyOrder({
  SituationPersonnelleApprenant.JSON_PROPERTY_SITUATION_FAMILIALE,
  SituationPersonnelleApprenant.JSON_PROPERTY_ENFANTS,
  SituationPersonnelleApprenant.JSON_PROPERTY_SITUATION_MILITAIRE
})
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-03-24T10:28:25.069148496+01:00[Europe/Paris]", comments = "Generator version: 7.12.0")
public class SituationPersonnelleApprenant {
  public static final String JSON_PROPERTY_SITUATION_FAMILIALE = "situationFamiliale";
  @jakarta.annotation.Nullable
  private String situationFamiliale;

  public static final String JSON_PROPERTY_ENFANTS = "enfants";
  @jakarta.annotation.Nullable
  private Integer enfants;

  public static final String JSON_PROPERTY_SITUATION_MILITAIRE = "situationMilitaire";
  @jakarta.annotation.Nullable
  private String situationMilitaire;

  public SituationPersonnelleApprenant() { 
  }

  public SituationPersonnelleApprenant situationFamiliale(@jakarta.annotation.Nullable String situationFamiliale) {
    this.situationFamiliale = situationFamiliale;
    return this;
  }

  /**
   * Le code de la situation familiale de l&#39;étudiant issu de la nomenclature Situations familiales  Codes de la nomenclature sur 6 caractères. Préfixe des codes : SIF 
   * @return situationFamiliale
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_SITUATION_FAMILIALE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getSituationFamiliale() {
    return situationFamiliale;
  }


  @JsonProperty(JSON_PROPERTY_SITUATION_FAMILIALE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSituationFamiliale(@jakarta.annotation.Nullable String situationFamiliale) {
    this.situationFamiliale = situationFamiliale;
  }


  public SituationPersonnelleApprenant enfants(@jakarta.annotation.Nullable Integer enfants) {
    this.enfants = enfants;
    return this;
  }

  /**
   * Le nombre d&#39;enfants à charge
   * @return enfants
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ENFANTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Integer getEnfants() {
    return enfants;
  }


  @JsonProperty(JSON_PROPERTY_ENFANTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setEnfants(@jakarta.annotation.Nullable Integer enfants) {
    this.enfants = enfants;
  }


  public SituationPersonnelleApprenant situationMilitaire(@jakarta.annotation.Nullable String situationMilitaire) {
    this.situationMilitaire = situationMilitaire;
    return this;
  }

  /**
   * Le code de la situation militaire de l&#39;étudiant issu de la nomenclature Situations militaires  Codes de la nomenclature sur 6 caractères. Préfixe des codes : SIM. 
   * @return situationMilitaire
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_SITUATION_MILITAIRE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getSituationMilitaire() {
    return situationMilitaire;
  }


  @JsonProperty(JSON_PROPERTY_SITUATION_MILITAIRE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSituationMilitaire(@jakarta.annotation.Nullable String situationMilitaire) {
    this.situationMilitaire = situationMilitaire;
  }


  /**
   * Return true if this SituationPersonnelleApprenant object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SituationPersonnelleApprenant situationPersonnelleApprenant = (SituationPersonnelleApprenant) o;
    return Objects.equals(this.situationFamiliale, situationPersonnelleApprenant.situationFamiliale) &&
        Objects.equals(this.enfants, situationPersonnelleApprenant.enfants) &&
        Objects.equals(this.situationMilitaire, situationPersonnelleApprenant.situationMilitaire);
  }

  @Override
  public int hashCode() {
    return Objects.hash(situationFamiliale, enfants, situationMilitaire);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SituationPersonnelleApprenant {\n");
    sb.append("    situationFamiliale: ").append(toIndentedString(situationFamiliale)).append("\n");
    sb.append("    enfants: ").append(toIndentedString(enfants)).append("\n");
    sb.append("    situationMilitaire: ").append(toIndentedString(situationMilitaire)).append("\n");
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

    // add `situationFamiliale` to the URL query string
    if (getSituationFamiliale() != null) {
      joiner.add(String.format("%ssituationFamiliale%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getSituationFamiliale()))));
    }

    // add `enfants` to the URL query string
    if (getEnfants() != null) {
      joiner.add(String.format("%senfants%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getEnfants()))));
    }

    // add `situationMilitaire` to the URL query string
    if (getSituationMilitaire() != null) {
      joiner.add(String.format("%ssituationMilitaire%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getSituationMilitaire()))));
    }

    return joiner.toString();
  }
}

