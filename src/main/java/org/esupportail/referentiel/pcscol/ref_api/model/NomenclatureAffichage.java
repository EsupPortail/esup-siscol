/*
 * REF v1 - Référentiel
 * Liste l'ensemble des services et des opérations disponibles dans le module Referentiel
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.esupportail.referentiel.pcscol.ref_api.model;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.StringJoiner;

import org.esupportail.referentiel.pcscol.invoker.ApiClient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
/**
 * NomenclatureAffichage
 */
@JsonPropertyOrder({
  NomenclatureAffichage.JSON_PROPERTY_CODE_NOMENCLATURE,
  NomenclatureAffichage.JSON_PROPERTY_NOM_SINGULIER,
  NomenclatureAffichage.JSON_PROPERTY_NOM_PLURIEL,
  NomenclatureAffichage.JSON_PROPERTY_URL,
  NomenclatureAffichage.JSON_PROPERTY_EST_NOMENCLATURE_STANDARD
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-30T09:24:02.851634+02:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class NomenclatureAffichage {
  public static final String JSON_PROPERTY_CODE_NOMENCLATURE = "codeNomenclature";
  private String codeNomenclature;

  public static final String JSON_PROPERTY_NOM_SINGULIER = "nomSingulier";
  private String nomSingulier;

  public static final String JSON_PROPERTY_NOM_PLURIEL = "nomPluriel";
  private String nomPluriel;

  public static final String JSON_PROPERTY_URL = "url";
  private String url;

  public static final String JSON_PROPERTY_EST_NOMENCLATURE_STANDARD = "estNomenclatureStandard";
  private Boolean estNomenclatureStandard;

  public NomenclatureAffichage() { 
  }

  public NomenclatureAffichage codeNomenclature(String codeNomenclature) {
    this.codeNomenclature = codeNomenclature;
    return this;
  }

  /**
   * Code de la nomenclature
   * @return codeNomenclature
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CODE_NOMENCLATURE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getCodeNomenclature() {
    return codeNomenclature;
  }


  @JsonProperty(JSON_PROPERTY_CODE_NOMENCLATURE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCodeNomenclature(String codeNomenclature) {
    this.codeNomenclature = codeNomenclature;
  }


  public NomenclatureAffichage nomSingulier(String nomSingulier) {
    this.nomSingulier = nomSingulier;
    return this;
  }

  /**
   * nom singulier de la nomenclature
   * @return nomSingulier
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_NOM_SINGULIER)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getNomSingulier() {
    return nomSingulier;
  }


  @JsonProperty(JSON_PROPERTY_NOM_SINGULIER)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setNomSingulier(String nomSingulier) {
    this.nomSingulier = nomSingulier;
  }


  public NomenclatureAffichage nomPluriel(String nomPluriel) {
    this.nomPluriel = nomPluriel;
    return this;
  }

  /**
   * nom pluriel de la nomenclature
   * @return nomPluriel
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_NOM_PLURIEL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getNomPluriel() {
    return nomPluriel;
  }


  @JsonProperty(JSON_PROPERTY_NOM_PLURIEL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setNomPluriel(String nomPluriel) {
    this.nomPluriel = nomPluriel;
  }


  public NomenclatureAffichage url(String url) {
    this.url = url;
    return this;
  }

  /**
   * url de la nomenclature
   * @return url
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_URL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getUrl() {
    return url;
  }


  @JsonProperty(JSON_PROPERTY_URL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setUrl(String url) {
    this.url = url;
  }


  public NomenclatureAffichage estNomenclatureStandard(Boolean estNomenclatureStandard) {
    this.estNomenclatureStandard = estNomenclatureStandard;
    return this;
  }

  /**
   * retourne true si c&#39;est une nomenclature standard
   * @return estNomenclatureStandard
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_EST_NOMENCLATURE_STANDARD)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Boolean getEstNomenclatureStandard() {
    return estNomenclatureStandard;
  }


  @JsonProperty(JSON_PROPERTY_EST_NOMENCLATURE_STANDARD)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setEstNomenclatureStandard(Boolean estNomenclatureStandard) {
    this.estNomenclatureStandard = estNomenclatureStandard;
  }


  /**
   * Return true if this NomenclatureAffichage object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NomenclatureAffichage nomenclatureAffichage = (NomenclatureAffichage) o;
    return Objects.equals(this.codeNomenclature, nomenclatureAffichage.codeNomenclature) &&
        Objects.equals(this.nomSingulier, nomenclatureAffichage.nomSingulier) &&
        Objects.equals(this.nomPluriel, nomenclatureAffichage.nomPluriel) &&
        Objects.equals(this.url, nomenclatureAffichage.url) &&
        Objects.equals(this.estNomenclatureStandard, nomenclatureAffichage.estNomenclatureStandard);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codeNomenclature, nomSingulier, nomPluriel, url, estNomenclatureStandard);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NomenclatureAffichage {\n");
    sb.append("    codeNomenclature: ").append(toIndentedString(codeNomenclature)).append("\n");
    sb.append("    nomSingulier: ").append(toIndentedString(nomSingulier)).append("\n");
    sb.append("    nomPluriel: ").append(toIndentedString(nomPluriel)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    estNomenclatureStandard: ").append(toIndentedString(estNomenclatureStandard)).append("\n");
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

    // add `codeNomenclature` to the URL query string
    if (getCodeNomenclature() != null) {
      joiner.add(String.format("%scodeNomenclature%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCodeNomenclature()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `nomSingulier` to the URL query string
    if (getNomSingulier() != null) {
      joiner.add(String.format("%snomSingulier%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getNomSingulier()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `nomPluriel` to the URL query string
    if (getNomPluriel() != null) {
      joiner.add(String.format("%snomPluriel%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getNomPluriel()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `url` to the URL query string
    if (getUrl() != null) {
      joiner.add(String.format("%surl%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getUrl()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `estNomenclatureStandard` to the URL query string
    if (getEstNomenclatureStandard() != null) {
      joiner.add(String.format("%sestNomenclatureStandard%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getEstNomenclatureStandard()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    return joiner.toString();
  }
}

