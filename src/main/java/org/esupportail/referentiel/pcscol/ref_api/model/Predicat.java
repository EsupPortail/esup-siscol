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
 * Un prédicat est définit par un opérateur( &#x3D;, !&#x3D;, &lt;, &gt;, &lt;&#x3D;, &gt;&#x3D;, ~) et la valeur à filtrer sous forme de chaine de carctères
 */
@JsonPropertyOrder({
  Predicat.JSON_PROPERTY_OPERATEUR,
  Predicat.JSON_PROPERTY_VALEUR
})
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-30T09:24:02.851634+02:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class Predicat {
  public static final String JSON_PROPERTY_OPERATEUR = "operateur";
  private String operateur;

  public static final String JSON_PROPERTY_VALEUR = "valeur";
  private String valeur;

  public Predicat() { 
  }

  public Predicat operateur(String operateur) {
    this.operateur = operateur;
    return this;
  }

  /**
   * Get operateur
   * @return operateur
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_OPERATEUR)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getOperateur() {
    return operateur;
  }


  @JsonProperty(JSON_PROPERTY_OPERATEUR)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setOperateur(String operateur) {
    this.operateur = operateur;
  }


  public Predicat valeur(String valeur) {
    this.valeur = valeur;
    return this;
  }

  /**
   * Get valeur
   * @return valeur
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_VALEUR)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getValeur() {
    return valeur;
  }


  @JsonProperty(JSON_PROPERTY_VALEUR)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setValeur(String valeur) {
    this.valeur = valeur;
  }


  /**
   * Return true if this Predicat object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Predicat predicat = (Predicat) o;
    return Objects.equals(this.operateur, predicat.operateur) &&
        Objects.equals(this.valeur, predicat.valeur);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operateur, valeur);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Predicat {\n");
    sb.append("    operateur: ").append(toIndentedString(operateur)).append("\n");
    sb.append("    valeur: ").append(toIndentedString(valeur)).append("\n");
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

    // add `operateur` to the URL query string
    if (getOperateur() != null) {
      joiner.add(String.format("%soperateur%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getOperateur()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `valeur` to the URL query string
    if (getValeur() != null) {
      joiner.add(String.format("%svaleur%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getValeur()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    return joiner.toString();
  }
}

