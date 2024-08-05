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
 * Nomenclature des séries du baccalauréat et équivalences
 */
@JsonPropertyOrder({
  SerieBac.JSON_PROPERTY_TYPE_NOMENCLATURE,
  SerieBac.JSON_PROPERTY_CODE_BCN,
  SerieBac.JSON_PROPERTY_TITRE_ACCES,
  SerieBac.JSON_PROPERTY_TYPE_BAC
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-30T09:24:02.851634+02:00[Europe/Paris]", comments = "Generator version: 7.7.0")
@JsonIgnoreProperties(
  value = "typeNomenclature", // ignore manually set typeNomenclature, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the typeNomenclature to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "typeNomenclature", visible = true)

public class SerieBac extends Nomenclature {
  public static final String JSON_PROPERTY_TYPE_NOMENCLATURE = "typeNomenclature";
  private String typeNomenclature;

  public static final String JSON_PROPERTY_CODE_BCN = "codeBcn";
  private String codeBcn;

  public static final String JSON_PROPERTY_TITRE_ACCES = "titreAcces";
  private TitreAcces titreAcces;

  public static final String JSON_PROPERTY_TYPE_BAC = "typeBac";
  private TypeBac typeBac;

  public SerieBac() { 
  }

  public SerieBac typeNomenclature(String typeNomenclature) {
    this.typeNomenclature = typeNomenclature;
    return this;
  }

  /**
   * Discriminant
   * @return typeNomenclature
   */
  @javax.annotation.Nullable
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


  public SerieBac codeBcn(String codeBcn) {
    this.codeBcn = codeBcn;
    return this;
  }

  /**
   * Le code BCN
   * @return codeBcn
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CODE_BCN)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getCodeBcn() {
    return codeBcn;
  }


  @JsonProperty(JSON_PROPERTY_CODE_BCN)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCodeBcn(String codeBcn) {
    this.codeBcn = codeBcn;
  }


  public SerieBac titreAcces(TitreAcces titreAcces) {
    this.titreAcces = titreAcces;
    return this;
  }

  /**
   * Get titreAcces
   * @return titreAcces
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_TITRE_ACCES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public TitreAcces getTitreAcces() {
    return titreAcces;
  }


  @JsonProperty(JSON_PROPERTY_TITRE_ACCES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setTitreAcces(TitreAcces titreAcces) {
    this.titreAcces = titreAcces;
  }


  public SerieBac typeBac(TypeBac typeBac) {
    this.typeBac = typeBac;
    return this;
  }

  /**
   * Get typeBac
   * @return typeBac
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_TYPE_BAC)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public TypeBac getTypeBac() {
    return typeBac;
  }


  @JsonProperty(JSON_PROPERTY_TYPE_BAC)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setTypeBac(TypeBac typeBac) {
    this.typeBac = typeBac;
  }


  @Override
  public SerieBac code(String code) {
    this.setCode(code);
    return this;
  }

  @Override
  public SerieBac libelleCourt(String libelleCourt) {
    this.setLibelleCourt(libelleCourt);
    return this;
  }

  @Override
  public SerieBac libelleLong(String libelleLong) {
    this.setLibelleLong(libelleLong);
    return this;
  }

  @Override
  public SerieBac libelleAffichage(String libelleAffichage) {
    this.setLibelleAffichage(libelleAffichage);
    return this;
  }

  @Override
  public SerieBac prioriteAffichage(Integer prioriteAffichage) {
    this.setPrioriteAffichage(prioriteAffichage);
    return this;
  }

  @Override
  public SerieBac dateDebutValidite(Date dateDebutValidite) {
    this.setDateDebutValidite(dateDebutValidite);
    return this;
  }

  @Override
  public SerieBac dateFinValidite(Date dateFinValidite) {
    this.setDateFinValidite(dateFinValidite);
    return this;
  }

  @Override
  public SerieBac temoinVisible(Boolean temoinVisible) {
    this.setTemoinVisible(temoinVisible);
    return this;
  }

  @Override
  public SerieBac temoinLivre(Boolean temoinLivre) {
    this.setTemoinLivre(temoinLivre);
    return this;
  }

  /**
   * Return true if this SerieBac object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SerieBac serieBac = (SerieBac) o;
    return Objects.equals(this.typeNomenclature, serieBac.typeNomenclature) &&
        Objects.equals(this.codeBcn, serieBac.codeBcn) &&
        Objects.equals(this.titreAcces, serieBac.titreAcces) &&
        Objects.equals(this.typeBac, serieBac.typeBac) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(typeNomenclature, codeBcn, titreAcces, typeBac, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SerieBac {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    typeNomenclature: ").append(toIndentedString(typeNomenclature)).append("\n");
    sb.append("    codeBcn: ").append(toIndentedString(codeBcn)).append("\n");
    sb.append("    titreAcces: ").append(toIndentedString(titreAcces)).append("\n");
    sb.append("    typeBac: ").append(toIndentedString(typeBac)).append("\n");
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

    // add `codeBcn` to the URL query string
    if (getCodeBcn() != null) {
      joiner.add(String.format("%scodeBcn%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCodeBcn()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `titreAcces` to the URL query string
    if (getTitreAcces() != null) {
      joiner.add(getTitreAcces().toUrlQueryString(prefix + "titreAcces" + suffix));
    }

    // add `typeBac` to the URL query string
    if (getTypeBac() != null) {
      joiner.add(getTypeBac().toUrlQueryString(prefix + "typeBac" + suffix));
    }

    return joiner.toString();
  }
static {
  // Initialize and register the discriminator mappings.
  Map<String, Class<?>> mappings = new HashMap<String, Class<?>>();
  mappings.put("SerieBac", SerieBac.class);
  JSON.registerDiscriminator(SerieBac.class, "typeNomenclature", mappings);
}
}

