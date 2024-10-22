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
 * Nomenclature des régimes d&#39;inscription possibles
 */
@JsonPropertyOrder({
  RegimeInscription.JSON_PROPERTY_TYPE_NOMENCLATURE,
  RegimeInscription.JSON_PROPERTY_CODE_S_I_S_E_INS,
  RegimeInscription.JSON_PROPERTY_CODE_S_I_S_E_RSLT,
  RegimeInscription.JSON_PROPERTY_TEMOIN_C_V_E_C,
  RegimeInscription.JSON_PROPERTY_FINANCEMENT_POSSIBLE,
  RegimeInscription.JSON_PROPERTY_DROIT_A_BOURSE,
  RegimeInscription.JSON_PROPERTY_CODE_BCN
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-30T09:24:02.851634+02:00[Europe/Paris]", comments = "Generator version: 7.7.0")
@JsonIgnoreProperties(
  value = "typeNomenclature", // ignore manually set typeNomenclature, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the typeNomenclature to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "typeNomenclature", visible = true)

public class RegimeInscription extends Nomenclature {
  public static final String JSON_PROPERTY_TYPE_NOMENCLATURE = "typeNomenclature";
  private String typeNomenclature;

  public static final String JSON_PROPERTY_CODE_S_I_S_E_INS = "codeSISEIns";
  private Integer codeSISEIns;

  public static final String JSON_PROPERTY_CODE_S_I_S_E_RSLT = "codeSISERslt";
  private Integer codeSISERslt;

  public static final String JSON_PROPERTY_TEMOIN_C_V_E_C = "temoinCVEC";
  private Boolean temoinCVEC;

  public static final String JSON_PROPERTY_FINANCEMENT_POSSIBLE = "financementPossible";
  private Boolean financementPossible;

  public static final String JSON_PROPERTY_DROIT_A_BOURSE = "droitABourse";
  private Boolean droitABourse;

  public static final String JSON_PROPERTY_CODE_BCN = "codeBcn";
  private String codeBcn;

  public RegimeInscription() { 
  }

  public RegimeInscription typeNomenclature(String typeNomenclature) {
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


  public RegimeInscription codeSISEIns(Integer codeSISEIns) {
    this.codeSISEIns = codeSISEIns;
    return this;
  }

  /**
   * Code du régime d&#39;inscription à exporter dans l&#39;enquête SISE Inscription. Si il est renseigné, il doit être égal à 10, 11, 12, 20 ou 22. Si il n&#39;est pas renseigné, les inscriptions sur ce régime ne seront pas exportées
   * @return codeSISEIns
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CODE_S_I_S_E_INS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Integer getCodeSISEIns() {
    return codeSISEIns;
  }


  @JsonProperty(JSON_PROPERTY_CODE_S_I_S_E_INS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCodeSISEIns(Integer codeSISEIns) {
    this.codeSISEIns = codeSISEIns;
  }


  public RegimeInscription codeSISERslt(Integer codeSISERslt) {
    this.codeSISERslt = codeSISERslt;
    return this;
  }

  /**
   * Code SISE de l&#39;état d&#39;inscription lors de la remontée Résultats. S&#39;il est renseigné, il doit prendre les valeurs: 11, 12, 20, 22 ou 23. Ce champ doit être obligatoirement renseigné si un code SISE ins est saisi.
   * @return codeSISERslt
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CODE_S_I_S_E_RSLT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Integer getCodeSISERslt() {
    return codeSISERslt;
  }


  @JsonProperty(JSON_PROPERTY_CODE_S_I_S_E_RSLT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCodeSISERslt(Integer codeSISERslt) {
    this.codeSISERslt = codeSISERslt;
  }


  public RegimeInscription temoinCVEC(Boolean temoinCVEC) {
    this.temoinCVEC = temoinCVEC;
    return this;
  }

  /**
   * Ce témoin rend obligatoire la saisie du numéro de certificat CVEC à l&#39;inscription
   * @return temoinCVEC
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_TEMOIN_C_V_E_C)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Boolean getTemoinCVEC() {
    return temoinCVEC;
  }


  @JsonProperty(JSON_PROPERTY_TEMOIN_C_V_E_C)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setTemoinCVEC(Boolean temoinCVEC) {
    this.temoinCVEC = temoinCVEC;
  }


  public RegimeInscription financementPossible(Boolean financementPossible) {
    this.financementPossible = financementPossible;
    return this;
  }

  /**
   * Indique si les frais d&#39;inscription avec ce régime peuvent être pris en charge par un tiers
   * @return financementPossible
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_FINANCEMENT_POSSIBLE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Boolean getFinancementPossible() {
    return financementPossible;
  }


  @JsonProperty(JSON_PROPERTY_FINANCEMENT_POSSIBLE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setFinancementPossible(Boolean financementPossible) {
    this.financementPossible = financementPossible;
  }


  public RegimeInscription droitABourse(Boolean droitABourse) {
    this.droitABourse = droitABourse;
    return this;
  }

  /**
   * Indique si le régime est ouvert à la bourse sur critère sociaux (Crous-Aglaé)
   * @return droitABourse
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_DROIT_A_BOURSE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Boolean getDroitABourse() {
    return droitABourse;
  }


  @JsonProperty(JSON_PROPERTY_DROIT_A_BOURSE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setDroitABourse(Boolean droitABourse) {
    this.droitABourse = droitABourse;
  }


  public RegimeInscription codeBcn(String codeBcn) {
    this.codeBcn = codeBcn;
    return this;
  }

  /**
   * Un code unique de l&#39;occurence dans la base BCN
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


  @Override
  public RegimeInscription code(String code) {
    this.setCode(code);
    return this;
  }

  @Override
  public RegimeInscription libelleCourt(String libelleCourt) {
    this.setLibelleCourt(libelleCourt);
    return this;
  }

  @Override
  public RegimeInscription libelleLong(String libelleLong) {
    this.setLibelleLong(libelleLong);
    return this;
  }

  @Override
  public RegimeInscription libelleAffichage(String libelleAffichage) {
    this.setLibelleAffichage(libelleAffichage);
    return this;
  }

  @Override
  public RegimeInscription prioriteAffichage(Integer prioriteAffichage) {
    this.setPrioriteAffichage(prioriteAffichage);
    return this;
  }

  @Override
  public RegimeInscription dateDebutValidite(Date dateDebutValidite) {
    this.setDateDebutValidite(dateDebutValidite);
    return this;
  }

  @Override
  public RegimeInscription dateFinValidite(Date dateFinValidite) {
    this.setDateFinValidite(dateFinValidite);
    return this;
  }

  @Override
  public RegimeInscription temoinVisible(Boolean temoinVisible) {
    this.setTemoinVisible(temoinVisible);
    return this;
  }

  @Override
  public RegimeInscription temoinLivre(Boolean temoinLivre) {
    this.setTemoinLivre(temoinLivre);
    return this;
  }

  /**
   * Return true if this RegimeInscription object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegimeInscription regimeInscription = (RegimeInscription) o;
    return Objects.equals(this.typeNomenclature, regimeInscription.typeNomenclature) &&
        Objects.equals(this.codeSISEIns, regimeInscription.codeSISEIns) &&
        Objects.equals(this.codeSISERslt, regimeInscription.codeSISERslt) &&
        Objects.equals(this.temoinCVEC, regimeInscription.temoinCVEC) &&
        Objects.equals(this.financementPossible, regimeInscription.financementPossible) &&
        Objects.equals(this.droitABourse, regimeInscription.droitABourse) &&
        Objects.equals(this.codeBcn, regimeInscription.codeBcn) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(typeNomenclature, codeSISEIns, codeSISERslt, temoinCVEC, financementPossible, droitABourse, codeBcn, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegimeInscription {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    typeNomenclature: ").append(toIndentedString(typeNomenclature)).append("\n");
    sb.append("    codeSISEIns: ").append(toIndentedString(codeSISEIns)).append("\n");
    sb.append("    codeSISERslt: ").append(toIndentedString(codeSISERslt)).append("\n");
    sb.append("    temoinCVEC: ").append(toIndentedString(temoinCVEC)).append("\n");
    sb.append("    financementPossible: ").append(toIndentedString(financementPossible)).append("\n");
    sb.append("    droitABourse: ").append(toIndentedString(droitABourse)).append("\n");
    sb.append("    codeBcn: ").append(toIndentedString(codeBcn)).append("\n");
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

    // add `codeSISEIns` to the URL query string
    if (getCodeSISEIns() != null) {
      joiner.add(String.format("%scodeSISEIns%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCodeSISEIns()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `codeSISERslt` to the URL query string
    if (getCodeSISERslt() != null) {
      joiner.add(String.format("%scodeSISERslt%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCodeSISERslt()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `temoinCVEC` to the URL query string
    if (getTemoinCVEC() != null) {
      joiner.add(String.format("%stemoinCVEC%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getTemoinCVEC()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `financementPossible` to the URL query string
    if (getFinancementPossible() != null) {
      joiner.add(String.format("%sfinancementPossible%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getFinancementPossible()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `droitABourse` to the URL query string
    if (getDroitABourse() != null) {
      joiner.add(String.format("%sdroitABourse%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getDroitABourse()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `codeBcn` to the URL query string
    if (getCodeBcn() != null) {
      joiner.add(String.format("%scodeBcn%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCodeBcn()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    return joiner.toString();
  }
static {
  // Initialize and register the discriminator mappings.
  Map<String, Class<?>> mappings = new HashMap<String, Class<?>>();
  mappings.put("RegimeInscription", RegimeInscription.class);
  JSON.registerDiscriminator(RegimeInscription.class, "typeNomenclature", mappings);
}
}
