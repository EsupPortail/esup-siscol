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

import java.util.Objects;
import java.util.StringJoiner;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
/**
 * Objet contenant l&#39;objet nature type d&#39;objet de formation d&#39;origine et l&#39;objet nature de type de formation
 */
@JsonPropertyOrder({
  Parametrages.JSON_PROPERTY_NATURE_TYPE_OBJET_FORMATION_ORIGINE,
  Parametrages.JSON_PROPERTY_NATURE_TYPE_OBJET_FORMATION_MODIFIE
})
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-30T09:24:02.851634+02:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class Parametrages {
  public static final String JSON_PROPERTY_NATURE_TYPE_OBJET_FORMATION_ORIGINE = "natureTypeObjetFormationOrigine";
  private NatureTypeObjetFormation natureTypeObjetFormationOrigine;

  public static final String JSON_PROPERTY_NATURE_TYPE_OBJET_FORMATION_MODIFIE = "natureTypeObjetFormationModifie";
  private NatureTypeObjetFormation natureTypeObjetFormationModifie;

  public Parametrages() { 
  }

  public Parametrages natureTypeObjetFormationOrigine(NatureTypeObjetFormation natureTypeObjetFormationOrigine) {
    this.natureTypeObjetFormationOrigine = natureTypeObjetFormationOrigine;
    return this;
  }

  /**
   * Get natureTypeObjetFormationOrigine
   * @return natureTypeObjetFormationOrigine
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_NATURE_TYPE_OBJET_FORMATION_ORIGINE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public NatureTypeObjetFormation getNatureTypeObjetFormationOrigine() {
    return natureTypeObjetFormationOrigine;
  }


  @JsonProperty(JSON_PROPERTY_NATURE_TYPE_OBJET_FORMATION_ORIGINE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setNatureTypeObjetFormationOrigine(NatureTypeObjetFormation natureTypeObjetFormationOrigine) {
    this.natureTypeObjetFormationOrigine = natureTypeObjetFormationOrigine;
  }


  public Parametrages natureTypeObjetFormationModifie(NatureTypeObjetFormation natureTypeObjetFormationModifie) {
    this.natureTypeObjetFormationModifie = natureTypeObjetFormationModifie;
    return this;
  }

  /**
   * Get natureTypeObjetFormationModifie
   * @return natureTypeObjetFormationModifie
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_NATURE_TYPE_OBJET_FORMATION_MODIFIE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public NatureTypeObjetFormation getNatureTypeObjetFormationModifie() {
    return natureTypeObjetFormationModifie;
  }


  @JsonProperty(JSON_PROPERTY_NATURE_TYPE_OBJET_FORMATION_MODIFIE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setNatureTypeObjetFormationModifie(NatureTypeObjetFormation natureTypeObjetFormationModifie) {
    this.natureTypeObjetFormationModifie = natureTypeObjetFormationModifie;
  }


  /**
   * Return true if this Parametrages object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Parametrages parametrages = (Parametrages) o;
    return Objects.equals(this.natureTypeObjetFormationOrigine, parametrages.natureTypeObjetFormationOrigine) &&
        Objects.equals(this.natureTypeObjetFormationModifie, parametrages.natureTypeObjetFormationModifie);
  }

  @Override
  public int hashCode() {
    return Objects.hash(natureTypeObjetFormationOrigine, natureTypeObjetFormationModifie);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Parametrages {\n");
    sb.append("    natureTypeObjetFormationOrigine: ").append(toIndentedString(natureTypeObjetFormationOrigine)).append("\n");
    sb.append("    natureTypeObjetFormationModifie: ").append(toIndentedString(natureTypeObjetFormationModifie)).append("\n");
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

    // add `natureTypeObjetFormationOrigine` to the URL query string
    if (getNatureTypeObjetFormationOrigine() != null) {
      joiner.add(getNatureTypeObjetFormationOrigine().toUrlQueryString(prefix + "natureTypeObjetFormationOrigine" + suffix));
    }

    // add `natureTypeObjetFormationModifie` to the URL query string
    if (getNatureTypeObjetFormationModifie() != null) {
      joiner.add(getNatureTypeObjetFormationModifie().toUrlQueryString(prefix + "natureTypeObjetFormationModifie" + suffix));
    }

    return joiner.toString();
  }
}

