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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

import org.esupportail.referentiel.pcscol.invoker.ApiClient;
import org.esupportail.referentiel.pcscol.invoker.JSON;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonValue;
/**
 * ObjetFormationOuGroupement
 */
@JsonPropertyOrder({
  ObjetFormationOuGroupement.JSON_PROPERTY_NATURE
})
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-03-24T10:28:25.069148496+01:00[Europe/Paris]", comments = "Generator version: 7.12.0")
@JsonIgnoreProperties(
  value = "est1", // ignore manually set est1, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the est1 to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "est1", visible = true)

public class ObjetFormationOuGroupement extends ObjetAvecLibelle {
  /**
   * le type d&#39;objet
   */
  public enum NatureEnum {
    OBJET_FORMATION(String.valueOf("ObjetFormation")),
    
    GROUPEMENT(String.valueOf("Groupement"));

    private String value;

    NatureEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static NatureEnum fromValue(String value) {
      for (NatureEnum b : NatureEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_NATURE = "nature";
  @jakarta.annotation.Nonnull
  private NatureEnum nature;

  public ObjetFormationOuGroupement() { 
  }

  public ObjetFormationOuGroupement nature(@jakarta.annotation.Nonnull NatureEnum nature) {
    this.nature = nature;
    return this;
  }

  /**
   * le type d&#39;objet
   * @return nature
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_NATURE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public NatureEnum getNature() {
    return nature;
  }


  @JsonProperty(JSON_PROPERTY_NATURE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setNature(@jakarta.annotation.Nonnull NatureEnum nature) {
    this.nature = nature;
  }


  @Override
  public ObjetFormationOuGroupement code(@jakarta.annotation.Nullable String code) {
    this.setCode(code);
    return this;
  }

  @Override
  public ObjetFormationOuGroupement libelleCourt(@jakarta.annotation.Nullable String libelleCourt) {
    this.setLibelleCourt(libelleCourt);
    return this;
  }

  @Override
  public ObjetFormationOuGroupement libelleLong(@jakarta.annotation.Nullable String libelleLong) {
    this.setLibelleLong(libelleLong);
    return this;
  }

  /**
   * Return true if this ObjetFormationOuGroupement object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ObjetFormationOuGroupement objetFormationOuGroupement = (ObjetFormationOuGroupement) o;
    return Objects.equals(this.nature, objetFormationOuGroupement.nature) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nature, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ObjetFormationOuGroupement {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    nature: ").append(toIndentedString(nature)).append("\n");
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

    // add `est1` to the URL query string
    if (getEst1() != null) {
      joiner.add(String.format("%sest1%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getEst1()))));
    }

    // add `code` to the URL query string
    if (getCode() != null) {
      joiner.add(String.format("%scode%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getCode()))));
    }

    // add `libelleCourt` to the URL query string
    if (getLibelleCourt() != null) {
      joiner.add(String.format("%slibelleCourt%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getLibelleCourt()))));
    }

    // add `libelleLong` to the URL query string
    if (getLibelleLong() != null) {
      joiner.add(String.format("%slibelleLong%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getLibelleLong()))));
    }

    // add `nature` to the URL query string
    if (getNature() != null) {
      joiner.add(String.format("%snature%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getNature()))));
    }

    return joiner.toString();
  }
static {
  // Initialize and register the discriminator mappings.
  Map<String, Class<?>> mappings = new HashMap<String, Class<?>>();
  mappings.put("ObjetFormationOuGroupement", ObjetFormationOuGroupement.class);
  JSON.registerDiscriminator(ObjetFormationOuGroupement.class, "est1", mappings);
}
}

