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
 * Nomenclature des départements en métropole, DOM, TOM et COM selon la codification INSEE
 */
@JsonPropertyOrder({
  Departement.JSON_PROPERTY_TYPE_NOMENCLATURE,
  Departement.JSON_PROPERTY_CODE_ACADEMIE,
  Departement.JSON_PROPERTY_CODE_REGION,
  Departement.JSON_PROPERTY_CODE_DEPARTEMENT_IMMATRICULATION,
  Departement.JSON_PROPERTY_CODE_DEPARTEMENT_INSEE2,
  Departement.JSON_PROPERTY_CODE_SISE_DEPARTEMENT
})
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-30T09:24:02.851634+02:00[Europe/Paris]", comments = "Generator version: 7.7.0")
@JsonIgnoreProperties(
  value = "typeNomenclature", // ignore manually set typeNomenclature, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the typeNomenclature to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "typeNomenclature", visible = true)

public class Departement extends Nomenclature {
  public static final String JSON_PROPERTY_TYPE_NOMENCLATURE = "typeNomenclature";
  private String typeNomenclature;

  public static final String JSON_PROPERTY_CODE_ACADEMIE = "codeAcademie";
  private String codeAcademie;

  public static final String JSON_PROPERTY_CODE_REGION = "codeRegion";
  private String codeRegion;

  public static final String JSON_PROPERTY_CODE_DEPARTEMENT_IMMATRICULATION = "codeDepartementImmatriculation";
  private String codeDepartementImmatriculation;

  public static final String JSON_PROPERTY_CODE_DEPARTEMENT_INSEE2 = "codeDepartementInsee2";
  private String codeDepartementInsee2;

  public static final String JSON_PROPERTY_CODE_SISE_DEPARTEMENT = "codeSiseDepartement";
  private String codeSiseDepartement;

  public Departement() { 
  }

  public Departement typeNomenclature(String typeNomenclature) {
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


  public Departement codeAcademie(String codeAcademie) {
    this.codeAcademie = codeAcademie;
    return this;
  }

  /**
   * Le code académie
   * @return codeAcademie
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CODE_ACADEMIE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getCodeAcademie() {
    return codeAcademie;
  }


  @JsonProperty(JSON_PROPERTY_CODE_ACADEMIE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCodeAcademie(String codeAcademie) {
    this.codeAcademie = codeAcademie;
  }


  public Departement codeRegion(String codeRegion) {
    this.codeRegion = codeRegion;
    return this;
  }

  /**
   * Le code Région
   * @return codeRegion
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CODE_REGION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getCodeRegion() {
    return codeRegion;
  }


  @JsonProperty(JSON_PROPERTY_CODE_REGION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCodeRegion(String codeRegion) {
    this.codeRegion = codeRegion;
  }


  public Departement codeDepartementImmatriculation(String codeDepartementImmatriculation) {
    this.codeDepartementImmatriculation = codeDepartementImmatriculation;
    return this;
  }

  /**
   * Le code département immatriculation
   * @return codeDepartementImmatriculation
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CODE_DEPARTEMENT_IMMATRICULATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getCodeDepartementImmatriculation() {
    return codeDepartementImmatriculation;
  }


  @JsonProperty(JSON_PROPERTY_CODE_DEPARTEMENT_IMMATRICULATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCodeDepartementImmatriculation(String codeDepartementImmatriculation) {
    this.codeDepartementImmatriculation = codeDepartementImmatriculation;
  }


  public Departement codeDepartementInsee2(String codeDepartementInsee2) {
    this.codeDepartementInsee2 = codeDepartementInsee2;
    return this;
  }

  /**
   * Le code département Insee 2
   * @return codeDepartementInsee2
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CODE_DEPARTEMENT_INSEE2)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getCodeDepartementInsee2() {
    return codeDepartementInsee2;
  }


  @JsonProperty(JSON_PROPERTY_CODE_DEPARTEMENT_INSEE2)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCodeDepartementInsee2(String codeDepartementInsee2) {
    this.codeDepartementInsee2 = codeDepartementInsee2;
  }


  public Departement codeSiseDepartement(String codeSiseDepartement) {
    this.codeSiseDepartement = codeSiseDepartement;
    return this;
  }

  /**
   * Le code SISE département
   * @return codeSiseDepartement
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CODE_SISE_DEPARTEMENT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getCodeSiseDepartement() {
    return codeSiseDepartement;
  }


  @JsonProperty(JSON_PROPERTY_CODE_SISE_DEPARTEMENT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCodeSiseDepartement(String codeSiseDepartement) {
    this.codeSiseDepartement = codeSiseDepartement;
  }


  @Override
  public Departement code(String code) {
    this.setCode(code);
    return this;
  }

  @Override
  public Departement libelleCourt(String libelleCourt) {
    this.setLibelleCourt(libelleCourt);
    return this;
  }

  @Override
  public Departement libelleLong(String libelleLong) {
    this.setLibelleLong(libelleLong);
    return this;
  }

  @Override
  public Departement libelleAffichage(String libelleAffichage) {
    this.setLibelleAffichage(libelleAffichage);
    return this;
  }

  @Override
  public Departement prioriteAffichage(Integer prioriteAffichage) {
    this.setPrioriteAffichage(prioriteAffichage);
    return this;
  }

  @Override
  public Departement dateDebutValidite(Date dateDebutValidite) {
    this.setDateDebutValidite(dateDebutValidite);
    return this;
  }

  @Override
  public Departement dateFinValidite(Date dateFinValidite) {
    this.setDateFinValidite(dateFinValidite);
    return this;
  }

  @Override
  public Departement temoinVisible(Boolean temoinVisible) {
    this.setTemoinVisible(temoinVisible);
    return this;
  }

  @Override
  public Departement temoinLivre(Boolean temoinLivre) {
    this.setTemoinLivre(temoinLivre);
    return this;
  }

  /**
   * Return true if this Departement object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Departement departement = (Departement) o;
    return Objects.equals(this.typeNomenclature, departement.typeNomenclature) &&
        Objects.equals(this.codeAcademie, departement.codeAcademie) &&
        Objects.equals(this.codeRegion, departement.codeRegion) &&
        Objects.equals(this.codeDepartementImmatriculation, departement.codeDepartementImmatriculation) &&
        Objects.equals(this.codeDepartementInsee2, departement.codeDepartementInsee2) &&
        Objects.equals(this.codeSiseDepartement, departement.codeSiseDepartement) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(typeNomenclature, codeAcademie, codeRegion, codeDepartementImmatriculation, codeDepartementInsee2, codeSiseDepartement, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Departement {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    typeNomenclature: ").append(toIndentedString(typeNomenclature)).append("\n");
    sb.append("    codeAcademie: ").append(toIndentedString(codeAcademie)).append("\n");
    sb.append("    codeRegion: ").append(toIndentedString(codeRegion)).append("\n");
    sb.append("    codeDepartementImmatriculation: ").append(toIndentedString(codeDepartementImmatriculation)).append("\n");
    sb.append("    codeDepartementInsee2: ").append(toIndentedString(codeDepartementInsee2)).append("\n");
    sb.append("    codeSiseDepartement: ").append(toIndentedString(codeSiseDepartement)).append("\n");
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

    // add `codeAcademie` to the URL query string
    if (getCodeAcademie() != null) {
      joiner.add(String.format("%scodeAcademie%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCodeAcademie()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `codeRegion` to the URL query string
    if (getCodeRegion() != null) {
      joiner.add(String.format("%scodeRegion%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCodeRegion()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `codeDepartementImmatriculation` to the URL query string
    if (getCodeDepartementImmatriculation() != null) {
      joiner.add(String.format("%scodeDepartementImmatriculation%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCodeDepartementImmatriculation()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `codeDepartementInsee2` to the URL query string
    if (getCodeDepartementInsee2() != null) {
      joiner.add(String.format("%scodeDepartementInsee2%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCodeDepartementInsee2()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `codeSiseDepartement` to the URL query string
    if (getCodeSiseDepartement() != null) {
      joiner.add(String.format("%scodeSiseDepartement%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCodeSiseDepartement()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    return joiner.toString();
  }
static {
  // Initialize and register the discriminator mappings.
  Map<String, Class<?>> mappings = new HashMap<String, Class<?>>();
  mappings.put("Departement", Departement.class);
  JSON.registerDiscriminator(Departement.class, "typeNomenclature", mappings);
}
}

