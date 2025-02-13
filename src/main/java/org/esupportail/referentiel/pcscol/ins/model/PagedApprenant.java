/*
 * INS Gestion V5
 * Il s'agit de l'API v5 de gestion - INS  __Apprenant :__ une personne qui a au moins une inscription validée dans Pegase.  __Inscription :__ se définit par une cible sur une période de mise en œuvre pour un apprenant. Une inscription peut prendre deux états : soit validée, soit annulée.  __Actualisation :__ permet de modifier les données liées à l’apprenant ou à l’inscription alors que la piste a déjà été payée ou validée.   __Gestion des erreurs :__   __200, 201 :__ opération effectuée   __400 :__ erreur de données sur les formats   __403 :__ accès refusé   __404 :__ contenu introuvable   __409 :__ donnée déjà existante   __500 :__ erreur serveur  # Changement majeur/cassant par rapport à V4  1. Suppression de `Inscription.noCandidat`.  1. Ajout de `VoeuBase.noCandidat` et `InscriptionComplete.noCandidat`.  1. Ajout de `VoeuBase.choisi`. 
 *
 * The version of the OpenAPI document: 24.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.esupportail.referentiel.pcscol.ins.model;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

import org.esupportail.referentiel.pcscol.invoker.ApiClient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
/**
 * PagedApprenant
 */
@JsonPropertyOrder({
  PagedApprenant.JSON_PROPERTY_ITEMS,
  PagedApprenant.JSON_PROPERTY_TOTAL_ELEMENTS,
  PagedApprenant.JSON_PROPERTY_TOTAL_PAGES,
  PagedApprenant.JSON_PROPERTY_TAILLE,
  PagedApprenant.JSON_PROPERTY_PAGE
})
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-23T10:43:17.979817+02:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class PagedApprenant {
  public static final String JSON_PROPERTY_ITEMS = "items";
  private List<Apprenant> items = new ArrayList<>();

  public static final String JSON_PROPERTY_TOTAL_ELEMENTS = "totalElements";
  private Long totalElements;

  public static final String JSON_PROPERTY_TOTAL_PAGES = "totalPages";
  private Integer totalPages;

  public static final String JSON_PROPERTY_TAILLE = "taille";
  private Integer taille;

  public static final String JSON_PROPERTY_PAGE = "page";
  private Integer page;

  public PagedApprenant() { 
  }

  public PagedApprenant items(List<Apprenant> items) {
    this.items = items;
    return this;
  }

  public PagedApprenant addItemsItem(Apprenant itemsItem) {
    if (this.items == null) {
      this.items = new ArrayList<>();
    }
    this.items.add(itemsItem);
    return this;
  }

  /**
   * Get items
   * @return items
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ITEMS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public List<Apprenant> getItems() {
    return items;
  }


  @JsonProperty(JSON_PROPERTY_ITEMS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setItems(List<Apprenant> items) {
    this.items = items;
  }


  public PagedApprenant totalElements(Long totalElements) {
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
  public void setTotalElements(Long totalElements) {
    this.totalElements = totalElements;
  }


  public PagedApprenant totalPages(Integer totalPages) {
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
  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }


  public PagedApprenant taille(Integer taille) {
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
  public void setTaille(Integer taille) {
    this.taille = taille;
  }


  public PagedApprenant page(Integer page) {
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
  public void setPage(Integer page) {
    this.page = page;
  }


  /**
   * Return true if this PagedApprenant object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PagedApprenant pagedApprenant = (PagedApprenant) o;
    return Objects.equals(this.items, pagedApprenant.items) &&
        Objects.equals(this.totalElements, pagedApprenant.totalElements) &&
        Objects.equals(this.totalPages, pagedApprenant.totalPages) &&
        Objects.equals(this.taille, pagedApprenant.taille) &&
        Objects.equals(this.page, pagedApprenant.page);
  }

  @Override
  public int hashCode() {
    return Objects.hash(items, totalElements, totalPages, taille, page);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PagedApprenant {\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
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

    // add `items` to the URL query string
    if (getItems() != null) {
      for (int i = 0; i < getItems().size(); i++) {
        if (getItems().get(i) != null) {
          joiner.add(getItems().get(i).toUrlQueryString(String.format("%sitems%s%s", prefix, suffix,
          "".equals(suffix) ? "" : String.format("%s%d%s", containerPrefix, i, containerSuffix))));
        }
      }
    }

    // add `totalElements` to the URL query string
    if (getTotalElements() != null) {
      joiner.add(String.format("%stotalElements%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getTotalElements()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `totalPages` to the URL query string
    if (getTotalPages() != null) {
      joiner.add(String.format("%stotalPages%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getTotalPages()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `taille` to the URL query string
    if (getTaille() != null) {
      joiner.add(String.format("%staille%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getTaille()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `page` to the URL query string
    if (getPage() != null) {
      joiner.add(String.format("%spage%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getPage()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    return joiner.toString();
  }
}

