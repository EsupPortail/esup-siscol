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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

import org.esupportail.referentiel.pcscol.invoker.ApiClient;
import org.esupportail.referentiel.pcscol.invoker.JSON;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
/**
 * Nomenclature des spécificités UAI
 */
@JsonPropertyOrder({
  SpecificiteUai.JSON_PROPERTY_TYPE_NOMENCLATURE,
  SpecificiteUai.JSON_PROPERTY_NUMERO_UAI,
  SpecificiteUai.JSON_PROPERTY_SPECIFICITE_UAI,
  SpecificiteUai.JSON_PROPERTY_SPECIFICITE_UAI_LIBE
})
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-30T09:24:02.851634+02:00[Europe/Paris]", comments = "Generator version: 7.7.0")
@JsonIgnoreProperties(
  value = "typeNomenclature", // ignore manually set typeNomenclature, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the typeNomenclature to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "typeNomenclature", visible = true)

public class SpecificiteUai extends Nomenclature {
  public static final String JSON_PROPERTY_TYPE_NOMENCLATURE = "typeNomenclature";
  private String typeNomenclature;

  public static final String JSON_PROPERTY_NUMERO_UAI = "numeroUai";
  private String numeroUai;

  public static final String JSON_PROPERTY_SPECIFICITE_UAI = "specificiteUai";
  private String specificiteUai;

  public static final String JSON_PROPERTY_SPECIFICITE_UAI_LIBE = "specificiteUaiLibe";
  private String specificiteUaiLibe;

  public SpecificiteUai() { 
  }

  public SpecificiteUai typeNomenclature(String typeNomenclature) {
    this.typeNomenclature = typeNomenclature;
    return this;
  }

  /**
   * Discriminant
   * @return typeNomenclature
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_TYPE_NOMENCLATURE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getTypeNomenclature() {
    return typeNomenclature;
  }


  @JsonProperty(JSON_PROPERTY_TYPE_NOMENCLATURE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setTypeNomenclature(String typeNomenclature) {
    this.typeNomenclature = typeNomenclature;
  }


  public SpecificiteUai numeroUai(String numeroUai) {
    this.numeroUai = numeroUai;
    return this;
  }

  /**
   * Le numéro UAI
   * @return numeroUai
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_NUMERO_UAI)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getNumeroUai() {
    return numeroUai;
  }


  @JsonProperty(JSON_PROPERTY_NUMERO_UAI)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setNumeroUai(String numeroUai) {
    this.numeroUai = numeroUai;
  }


  public SpecificiteUai specificiteUai(String specificiteUai) {
    this.specificiteUai = specificiteUai;
    return this;
  }

  /**
   * La spécificité UAI
   * @return specificiteUai
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_SPECIFICITE_UAI)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getSpecificiteUai() {
    return specificiteUai;
  }


  @JsonProperty(JSON_PROPERTY_SPECIFICITE_UAI)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSpecificiteUai(String specificiteUai) {
    this.specificiteUai = specificiteUai;
  }


  public SpecificiteUai specificiteUaiLibe(String specificiteUaiLibe) {
    this.specificiteUaiLibe = specificiteUaiLibe;
    return this;
  }

  /**
   * Le libellé de la spécificité UAI
   * @return specificiteUaiLibe
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_SPECIFICITE_UAI_LIBE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getSpecificiteUaiLibe() {
    return specificiteUaiLibe;
  }


  @JsonProperty(JSON_PROPERTY_SPECIFICITE_UAI_LIBE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSpecificiteUaiLibe(String specificiteUaiLibe) {
    this.specificiteUaiLibe = specificiteUaiLibe;
  }


  @Override
  public SpecificiteUai code(String code) {
    this.setCode(code);
    return this;
  }

  @Override
  public SpecificiteUai libelleCourt(String libelleCourt) {
    this.setLibelleCourt(libelleCourt);
    return this;
  }

  @Override
  public SpecificiteUai libelleLong(String libelleLong) {
    this.setLibelleLong(libelleLong);
    return this;
  }

  @Override
  public SpecificiteUai libelleAffichage(String libelleAffichage) {
    this.setLibelleAffichage(libelleAffichage);
    return this;
  }

  @Override
  public SpecificiteUai prioriteAffichage(Integer prioriteAffichage) {
    this.setPrioriteAffichage(prioriteAffichage);
    return this;
  }

  @Override
  public SpecificiteUai dateDebutValidite(Date dateDebutValidite) {
    this.setDateDebutValidite(dateDebutValidite);
    return this;
  }

  @Override
  public SpecificiteUai dateFinValidite(Date dateFinValidite) {
    this.setDateFinValidite(dateFinValidite);
    return this;
  }

  @Override
  public SpecificiteUai temoinVisible(Boolean temoinVisible) {
    this.setTemoinVisible(temoinVisible);
    return this;
  }

  @Override
  public SpecificiteUai temoinLivre(Boolean temoinLivre) {
    this.setTemoinLivre(temoinLivre);
    return this;
  }

  /**
   * Return true if this SpecificiteUai object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SpecificiteUai specificiteUai = (SpecificiteUai) o;
    return Objects.equals(this.typeNomenclature, specificiteUai.typeNomenclature) &&
        Objects.equals(this.numeroUai, specificiteUai.numeroUai) &&
        Objects.equals(this.specificiteUai, specificiteUai.specificiteUai) &&
        Objects.equals(this.specificiteUaiLibe, specificiteUai.specificiteUaiLibe) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(typeNomenclature, numeroUai, specificiteUai, specificiteUaiLibe, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SpecificiteUai {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    typeNomenclature: ").append(toIndentedString(typeNomenclature)).append("\n");
    sb.append("    numeroUai: ").append(toIndentedString(numeroUai)).append("\n");
    sb.append("    specificiteUai: ").append(toIndentedString(specificiteUai)).append("\n");
    sb.append("    specificiteUaiLibe: ").append(toIndentedString(specificiteUaiLibe)).append("\n");
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

    // add `typeNomenclature` to the URL query string
    if (getTypeNomenclature() != null) {
      joiner.add(String.format("%stypeNomenclature%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getTypeNomenclature()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `code` to the URL query string
    if (getCode() != null) {
      joiner.add(String.format("%scode%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCode()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `libelleCourt` to the URL query string
    if (getLibelleCourt() != null) {
      joiner.add(String.format("%slibelleCourt%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getLibelleCourt()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `libelleLong` to the URL query string
    if (getLibelleLong() != null) {
      joiner.add(String.format("%slibelleLong%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getLibelleLong()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `libelleAffichage` to the URL query string
    if (getLibelleAffichage() != null) {
      joiner.add(String.format("%slibelleAffichage%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getLibelleAffichage()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `prioriteAffichage` to the URL query string
    if (getPrioriteAffichage() != null) {
      joiner.add(String.format("%sprioriteAffichage%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getPrioriteAffichage()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `dateDebutValidite` to the URL query string
    if (getDateDebutValidite() != null) {
      joiner.add(String.format("%sdateDebutValidite%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getDateDebutValidite()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `dateFinValidite` to the URL query string
    if (getDateFinValidite() != null) {
      joiner.add(String.format("%sdateFinValidite%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getDateFinValidite()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `temoinVisible` to the URL query string
    if (getTemoinVisible() != null) {
      joiner.add(String.format("%stemoinVisible%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getTemoinVisible()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `temoinLivre` to the URL query string
    if (getTemoinLivre() != null) {
      joiner.add(String.format("%stemoinLivre%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getTemoinLivre()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `numeroUai` to the URL query string
    if (getNumeroUai() != null) {
      joiner.add(String.format("%snumeroUai%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getNumeroUai()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `specificiteUai` to the URL query string
    if (getSpecificiteUai() != null) {
      joiner.add(String.format("%sspecificiteUai%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getSpecificiteUai()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `specificiteUaiLibe` to the URL query string
    if (getSpecificiteUaiLibe() != null) {
      joiner.add(String.format("%sspecificiteUaiLibe%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getSpecificiteUaiLibe()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    return joiner.toString();
  }
static {
  // Initialize and register the discriminator mappings.
  Map<String, Class<?>> mappings = new HashMap<String, Class<?>>();
  mappings.put("SpecificiteUai", SpecificiteUai.class);
  JSON.registerDiscriminator(SpecificiteUai.class, "typeNomenclature", mappings);
}
}

