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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
 * Nomenclature des professions et catégories socio-professionnelles
 */
@JsonPropertyOrder({
  CategorieSocioprofessionnelle.JSON_PROPERTY_TYPE_NOMENCLATURE,
  CategorieSocioprofessionnelle.JSON_PROPERTY_CODE_BCN,
  CategorieSocioprofessionnelle.JSON_PROPERTY_CATEGORIE_SOCIOPROFESSIONNELLE8,
  CategorieSocioprofessionnelle.JSON_PROPERTY_SITUATION_EMPLOI_LST
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-30T09:24:02.851634+02:00[Europe/Paris]", comments = "Generator version: 7.7.0")
@JsonIgnoreProperties(
  value = "typeNomenclature", // ignore manually set typeNomenclature, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the typeNomenclature to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "typeNomenclature", visible = true)

public class CategorieSocioprofessionnelle extends Nomenclature {
  public static final String JSON_PROPERTY_TYPE_NOMENCLATURE = "typeNomenclature";
  private String typeNomenclature;

  public static final String JSON_PROPERTY_CODE_BCN = "codeBcn";
  private String codeBcn;

  public static final String JSON_PROPERTY_CATEGORIE_SOCIOPROFESSIONNELLE8 = "categorieSocioprofessionnelle8";
  private CategorieSocioprofessionnelle8 categorieSocioprofessionnelle8;

  public static final String JSON_PROPERTY_SITUATION_EMPLOI_LST = "situationEmploiLst";
  private List<SituationEmploi> situationEmploiLst = new ArrayList<>();

  public CategorieSocioprofessionnelle() { 
  }

  public CategorieSocioprofessionnelle typeNomenclature(String typeNomenclature) {
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


  public CategorieSocioprofessionnelle codeBcn(String codeBcn) {
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


  public CategorieSocioprofessionnelle categorieSocioprofessionnelle8(CategorieSocioprofessionnelle8 categorieSocioprofessionnelle8) {
    this.categorieSocioprofessionnelle8 = categorieSocioprofessionnelle8;
    return this;
  }

  /**
   * Get categorieSocioprofessionnelle8
   * @return categorieSocioprofessionnelle8
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CATEGORIE_SOCIOPROFESSIONNELLE8)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public CategorieSocioprofessionnelle8 getCategorieSocioprofessionnelle8() {
    return categorieSocioprofessionnelle8;
  }


  @JsonProperty(JSON_PROPERTY_CATEGORIE_SOCIOPROFESSIONNELLE8)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCategorieSocioprofessionnelle8(CategorieSocioprofessionnelle8 categorieSocioprofessionnelle8) {
    this.categorieSocioprofessionnelle8 = categorieSocioprofessionnelle8;
  }


  public CategorieSocioprofessionnelle situationEmploiLst(List<SituationEmploi> situationEmploiLst) {
    this.situationEmploiLst = situationEmploiLst;
    return this;
  }

  public CategorieSocioprofessionnelle addSituationEmploiLstItem(SituationEmploi situationEmploiLstItem) {
    if (this.situationEmploiLst == null) {
      this.situationEmploiLst = new ArrayList<>();
    }
    this.situationEmploiLst.add(situationEmploiLstItem);
    return this;
  }

  /**
   * Get situationEmploiLst
   * @return situationEmploiLst
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_SITUATION_EMPLOI_LST)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public List<SituationEmploi> getSituationEmploiLst() {
    return situationEmploiLst;
  }


  @JsonProperty(JSON_PROPERTY_SITUATION_EMPLOI_LST)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSituationEmploiLst(List<SituationEmploi> situationEmploiLst) {
    this.situationEmploiLst = situationEmploiLst;
  }


  @Override
  public CategorieSocioprofessionnelle code(String code) {
    this.setCode(code);
    return this;
  }

  @Override
  public CategorieSocioprofessionnelle libelleCourt(String libelleCourt) {
    this.setLibelleCourt(libelleCourt);
    return this;
  }

  @Override
  public CategorieSocioprofessionnelle libelleLong(String libelleLong) {
    this.setLibelleLong(libelleLong);
    return this;
  }

  @Override
  public CategorieSocioprofessionnelle libelleAffichage(String libelleAffichage) {
    this.setLibelleAffichage(libelleAffichage);
    return this;
  }

  @Override
  public CategorieSocioprofessionnelle prioriteAffichage(Integer prioriteAffichage) {
    this.setPrioriteAffichage(prioriteAffichage);
    return this;
  }

  @Override
  public CategorieSocioprofessionnelle dateDebutValidite(Date dateDebutValidite) {
    this.setDateDebutValidite(dateDebutValidite);
    return this;
  }

  @Override
  public CategorieSocioprofessionnelle dateFinValidite(Date dateFinValidite) {
    this.setDateFinValidite(dateFinValidite);
    return this;
  }

  @Override
  public CategorieSocioprofessionnelle temoinVisible(Boolean temoinVisible) {
    this.setTemoinVisible(temoinVisible);
    return this;
  }

  @Override
  public CategorieSocioprofessionnelle temoinLivre(Boolean temoinLivre) {
    this.setTemoinLivre(temoinLivre);
    return this;
  }

  /**
   * Return true if this CategorieSocioprofessionnelle object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CategorieSocioprofessionnelle categorieSocioprofessionnelle = (CategorieSocioprofessionnelle) o;
    return Objects.equals(this.typeNomenclature, categorieSocioprofessionnelle.typeNomenclature) &&
        Objects.equals(this.codeBcn, categorieSocioprofessionnelle.codeBcn) &&
        Objects.equals(this.categorieSocioprofessionnelle8, categorieSocioprofessionnelle.categorieSocioprofessionnelle8) &&
        Objects.equals(this.situationEmploiLst, categorieSocioprofessionnelle.situationEmploiLst) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(typeNomenclature, codeBcn, categorieSocioprofessionnelle8, situationEmploiLst, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CategorieSocioprofessionnelle {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    typeNomenclature: ").append(toIndentedString(typeNomenclature)).append("\n");
    sb.append("    codeBcn: ").append(toIndentedString(codeBcn)).append("\n");
    sb.append("    categorieSocioprofessionnelle8: ").append(toIndentedString(categorieSocioprofessionnelle8)).append("\n");
    sb.append("    situationEmploiLst: ").append(toIndentedString(situationEmploiLst)).append("\n");
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

    // add `categorieSocioprofessionnelle8` to the URL query string
    if (getCategorieSocioprofessionnelle8() != null) {
      joiner.add(getCategorieSocioprofessionnelle8().toUrlQueryString(prefix + "categorieSocioprofessionnelle8" + suffix));
    }

    // add `situationEmploiLst` to the URL query string
    if (getSituationEmploiLst() != null) {
      for (int i = 0; i < getSituationEmploiLst().size(); i++) {
        if (getSituationEmploiLst().get(i) != null) {
          joiner.add(getSituationEmploiLst().get(i).toUrlQueryString(String.format("%ssituationEmploiLst%s%s", prefix, suffix,
          "".equals(suffix) ? "" : String.format("%s%d%s", containerPrefix, i, containerSuffix))));
        }
      }
    }

    return joiner.toString();
  }
static {
  // Initialize and register the discriminator mappings.
  Map<String, Class<?>> mappings = new HashMap<String, Class<?>>();
  mappings.put("CategorieSocioprofessionnelle", CategorieSocioprofessionnelle.class);
  JSON.registerDiscriminator(CategorieSocioprofessionnelle.class, "typeNomenclature", mappings);
}
}

