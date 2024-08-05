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
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

import org.esupportail.referentiel.pcscol.invoker.ApiClient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
/**
 * PersonneTypePersonne
 */
@JsonPropertyOrder({
  PersonneTypePersonne.JSON_PROPERTY_TYPE_PERSONNE,
  PersonneTypePersonne.JSON_PROPERTY_DATE_DEBUT_VALIDITE,
  PersonneTypePersonne.JSON_PROPERTY_DATE_FIN_VALIDITE
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-30T09:24:02.851634+02:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class PersonneTypePersonne {
  public static final String JSON_PROPERTY_TYPE_PERSONNE = "typePersonne";
  private TypePersonne typePersonne;

  public static final String JSON_PROPERTY_DATE_DEBUT_VALIDITE = "dateDebutValidite";
  private Date dateDebutValidite;

  public static final String JSON_PROPERTY_DATE_FIN_VALIDITE = "dateFinValidite";
  private Date dateFinValidite;

  public PersonneTypePersonne() { 
  }

  public PersonneTypePersonne typePersonne(TypePersonne typePersonne) {
    this.typePersonne = typePersonne;
    return this;
  }

  /**
   * Get typePersonne
   * @return typePersonne
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_TYPE_PERSONNE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public TypePersonne getTypePersonne() {
    return typePersonne;
  }


  @JsonProperty(JSON_PROPERTY_TYPE_PERSONNE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setTypePersonne(TypePersonne typePersonne) {
    this.typePersonne = typePersonne;
  }


  public PersonneTypePersonne dateDebutValidite(Date dateDebutValidite) {
    this.dateDebutValidite = dateDebutValidite;
    return this;
  }

  /**
   * Date du début de la validité
   * @return dateDebutValidite
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_DATE_DEBUT_VALIDITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Date getDateDebutValidite() {
    return dateDebutValidite;
  }


  @JsonProperty(JSON_PROPERTY_DATE_DEBUT_VALIDITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setDateDebutValidite(Date dateDebutValidite) {
    this.dateDebutValidite = dateDebutValidite;
  }


  public PersonneTypePersonne dateFinValidite(Date dateFinValidite) {
    this.dateFinValidite = dateFinValidite;
    return this;
  }

  /**
   * Date de la fin de la validité
   * @return dateFinValidite
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_DATE_FIN_VALIDITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Date getDateFinValidite() {
    return dateFinValidite;
  }


  @JsonProperty(JSON_PROPERTY_DATE_FIN_VALIDITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setDateFinValidite(Date dateFinValidite) {
    this.dateFinValidite = dateFinValidite;
  }


  /**
   * Return true if this PersonneTypePersonne object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PersonneTypePersonne personneTypePersonne = (PersonneTypePersonne) o;
    return Objects.equals(this.typePersonne, personneTypePersonne.typePersonne) &&
        Objects.equals(this.dateDebutValidite, personneTypePersonne.dateDebutValidite) &&
        Objects.equals(this.dateFinValidite, personneTypePersonne.dateFinValidite);
  }

  @Override
  public int hashCode() {
    return Objects.hash(typePersonne, dateDebutValidite, dateFinValidite);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PersonneTypePersonne {\n");
    sb.append("    typePersonne: ").append(toIndentedString(typePersonne)).append("\n");
    sb.append("    dateDebutValidite: ").append(toIndentedString(dateDebutValidite)).append("\n");
    sb.append("    dateFinValidite: ").append(toIndentedString(dateFinValidite)).append("\n");
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

    // add `typePersonne` to the URL query string
    if (getTypePersonne() != null) {
      joiner.add(getTypePersonne().toUrlQueryString(prefix + "typePersonne" + suffix));
    }

    // add `dateDebutValidite` to the URL query string
    if (getDateDebutValidite() != null) {
      joiner.add(String.format("%sdateDebutValidite%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getDateDebutValidite()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `dateFinValidite` to the URL query string
    if (getDateFinValidite() != null) {
      joiner.add(String.format("%sdateFinValidite%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getDateFinValidite()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    return joiner.toString();
  }
}

