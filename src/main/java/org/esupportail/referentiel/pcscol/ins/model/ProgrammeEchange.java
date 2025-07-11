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

import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

import org.esupportail.referentiel.pcscol.invoker.ApiClient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
/**
 * ProgrammeEchange
 */
@JsonPropertyOrder({
  ProgrammeEchange.JSON_PROPERTY_CODE,
  ProgrammeEchange.JSON_PROPERTY_CODE_PAYS,
  ProgrammeEchange.JSON_PROPERTY_CONTEXTE_CONSOMMATION
})
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-03-24T10:28:25.069148496+01:00[Europe/Paris]", comments = "Generator version: 7.12.0")
public class ProgrammeEchange {
  public static final String JSON_PROPERTY_CODE = "code";
  @jakarta.annotation.Nullable
  private String code;

  public static final String JSON_PROPERTY_CODE_PAYS = "codePays";
  @jakarta.annotation.Nullable
  private String codePays;

  public static final String JSON_PROPERTY_CONTEXTE_CONSOMMATION = "contexteConsommation";
  @jakarta.annotation.Nullable
  private Date contexteConsommation;

  public ProgrammeEchange() { 
  }

  public ProgrammeEchange code(@jakarta.annotation.Nullable String code) {
    this.code = code;
    return this;
  }

  /**
   * code de la nomenclature programme d&#39;échange
   * @return code
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getCode() {
    return code;
  }


  @JsonProperty(JSON_PROPERTY_CODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCode(@jakarta.annotation.Nullable String code) {
    this.code = code;
  }


  public ProgrammeEchange codePays(@jakarta.annotation.Nullable String codePays) {
    this.codePays = codePays;
    return this;
  }

  /**
   * code de la nomenclature du pays du programme d&#39;échange
   * @return codePays
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CODE_PAYS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getCodePays() {
    return codePays;
  }


  @JsonProperty(JSON_PROPERTY_CODE_PAYS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCodePays(@jakarta.annotation.Nullable String codePays) {
    this.codePays = codePays;
  }


  public ProgrammeEchange contexteConsommation(@jakarta.annotation.Nullable Date contexteConsommation) {
    this.contexteConsommation = contexteConsommation;
    return this;
  }

  /**
   * Get contexteConsommation
   * @return contexteConsommation
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CONTEXTE_CONSOMMATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Date getContexteConsommation() {
    return contexteConsommation;
  }


  @JsonProperty(JSON_PROPERTY_CONTEXTE_CONSOMMATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setContexteConsommation(@jakarta.annotation.Nullable Date contexteConsommation) {
    this.contexteConsommation = contexteConsommation;
  }


  /**
   * Return true if this ProgrammeEchange object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProgrammeEchange programmeEchange = (ProgrammeEchange) o;
    return Objects.equals(this.code, programmeEchange.code) &&
        Objects.equals(this.codePays, programmeEchange.codePays) &&
        Objects.equals(this.contexteConsommation, programmeEchange.contexteConsommation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, codePays, contexteConsommation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProgrammeEchange {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    codePays: ").append(toIndentedString(codePays)).append("\n");
    sb.append("    contexteConsommation: ").append(toIndentedString(contexteConsommation)).append("\n");
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

    // add `codePays` to the URL query string
    if (getCodePays() != null) {
      joiner.add(String.format("%scodePays%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getCodePays()))));
    }

    // add `contexteConsommation` to the URL query string
    if (getContexteConsommation() != null) {
      joiner.add(String.format("%scontexteConsommation%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getContexteConsommation()))));
    }

    return joiner.toString();
  }
}

