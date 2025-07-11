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
 * Paging
 */
@JsonPropertyOrder({
  Paging.JSON_PROPERTY_TOTAL_ELEMENTS,
  Paging.JSON_PROPERTY_TOTAL_PAGES,
  Paging.JSON_PROPERTY_TAILLE,
  Paging.JSON_PROPERTY_PAGE
})
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-03-24T10:28:25.069148496+01:00[Europe/Paris]", comments = "Generator version: 7.12.0")
public class Paging {
  public static final String JSON_PROPERTY_TOTAL_ELEMENTS = "totalElements";
  @jakarta.annotation.Nullable
  private Long totalElements;

  public static final String JSON_PROPERTY_TOTAL_PAGES = "totalPages";
  @jakarta.annotation.Nullable
  private Integer totalPages;

  public static final String JSON_PROPERTY_TAILLE = "taille";
  @jakarta.annotation.Nullable
  private Integer taille;

  public static final String JSON_PROPERTY_PAGE = "page";
  @jakarta.annotation.Nullable
  private Integer page;

  public Paging() { 
  }

  public Paging totalElements(@jakarta.annotation.Nullable Long totalElements) {
    this.totalElements = totalElements;
    return this;
  }

  /**
   * Nombre total de d&#39;enregistrements existants en base 
   * @return totalElements
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_TOTAL_ELEMENTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Long getTotalElements() {
    return totalElements;
  }


  @JsonProperty(JSON_PROPERTY_TOTAL_ELEMENTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setTotalElements(@jakarta.annotation.Nullable Long totalElements) {
    this.totalElements = totalElements;
  }


  public Paging totalPages(@jakarta.annotation.Nullable Integer totalPages) {
    this.totalPages = totalPages;
    return this;
  }

  /**
   * Nombre de pages totales existantes 
   * @return totalPages
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_TOTAL_PAGES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Integer getTotalPages() {
    return totalPages;
  }


  @JsonProperty(JSON_PROPERTY_TOTAL_PAGES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setTotalPages(@jakarta.annotation.Nullable Integer totalPages) {
    this.totalPages = totalPages;
  }


  public Paging taille(@jakarta.annotation.Nullable Integer taille) {
    this.taille = taille;
    return this;
  }

  /**
   * Nombre d&#39;enregistrements demandés par page 
   * @return taille
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_TAILLE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Integer getTaille() {
    return taille;
  }


  @JsonProperty(JSON_PROPERTY_TAILLE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setTaille(@jakarta.annotation.Nullable Integer taille) {
    this.taille = taille;
  }


  public Paging page(@jakarta.annotation.Nullable Integer page) {
    this.page = page;
    return this;
  }

  /**
   * Numéro de la page retournée (commence à 0) 
   * @return page
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_PAGE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Integer getPage() {
    return page;
  }


  @JsonProperty(JSON_PROPERTY_PAGE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setPage(@jakarta.annotation.Nullable Integer page) {
    this.page = page;
  }


  /**
   * Return true if this Paging object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Paging paging = (Paging) o;
    return Objects.equals(this.totalElements, paging.totalElements) &&
        Objects.equals(this.totalPages, paging.totalPages) &&
        Objects.equals(this.taille, paging.taille) &&
        Objects.equals(this.page, paging.page);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalElements, totalPages, taille, page);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Paging {\n");
    sb.append("    totalElements: ").append(toIndentedString(totalElements)).append("\n");
    sb.append("    totalPages: ").append(toIndentedString(totalPages)).append("\n");
    sb.append("    taille: ").append(toIndentedString(taille)).append("\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
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

    // add `totalElements` to the URL query string
    if (getTotalElements() != null) {
      joiner.add(String.format("%stotalElements%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getTotalElements()))));
    }

    // add `totalPages` to the URL query string
    if (getTotalPages() != null) {
      joiner.add(String.format("%stotalPages%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getTotalPages()))));
    }

    // add `taille` to the URL query string
    if (getTaille() != null) {
      joiner.add(String.format("%staille%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getTaille()))));
    }

    // add `page` to the URL query string
    if (getPage() != null) {
      joiner.add(String.format("%spage%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getPage()))));
    }

    return joiner.toString();
  }
}

