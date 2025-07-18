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

import java.util.ArrayList;
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
 * Calendrier
 */
@JsonPropertyOrder({
  Calendrier.JSON_PROPERTY_CODE,
  Calendrier.JSON_PROPERTY_CONTEXTE,
  Calendrier.JSON_PROPERTY_TEMOIN_ACTIF
})
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-03-24T10:28:25.069148496+01:00[Europe/Paris]", comments = "Generator version: 7.12.0")
@JsonIgnoreProperties(
  value = "est1", // ignore manually set est1, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the est1 to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "est1", visible = true)

public class Calendrier extends ObjetAvecDates {
  public static final String JSON_PROPERTY_CODE = "code";
  @jakarta.annotation.Nullable
  private String code;

  public static final String JSON_PROPERTY_CONTEXTE = "contexte";
  @jakarta.annotation.Nullable
  private List<ContexteInscription> contexte = new ArrayList<>();

  public static final String JSON_PROPERTY_TEMOIN_ACTIF = "temoinActif";
  @jakarta.annotation.Nullable
  private Boolean temoinActif;

  public Calendrier() { 
  }

  public Calendrier code(@jakarta.annotation.Nullable String code) {
    this.code = code;
    return this;
  }

  /**
   * Le code du calendrier
   * @return code
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getCode() {
    return code;
  }


  @JsonProperty(JSON_PROPERTY_CODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCode(@jakarta.annotation.Nullable String code) {
    this.code = code;
  }


  public Calendrier contexte(@jakarta.annotation.Nullable List<ContexteInscription> contexte) {
    this.contexte = contexte;
    return this;
  }

  public Calendrier addContexteItem(ContexteInscription contexteItem) {
    if (this.contexte == null) {
      this.contexte = new ArrayList<>();
    }
    this.contexte.add(contexteItem);
    return this;
  }

  /**
   * Get contexte
   * @return contexte
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CONTEXTE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public List<ContexteInscription> getContexte() {
    return contexte;
  }


  @JsonProperty(JSON_PROPERTY_CONTEXTE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setContexte(@jakarta.annotation.Nullable List<ContexteInscription> contexte) {
    this.contexte = contexte;
  }


  public Calendrier temoinActif(@jakarta.annotation.Nullable Boolean temoinActif) {
    this.temoinActif = temoinActif;
    return this;
  }

  /**
   * Calendrier actif ou non
   * @return temoinActif
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_TEMOIN_ACTIF)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Boolean getTemoinActif() {
    return temoinActif;
  }


  @JsonProperty(JSON_PROPERTY_TEMOIN_ACTIF)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setTemoinActif(@jakarta.annotation.Nullable Boolean temoinActif) {
    this.temoinActif = temoinActif;
  }


  /**
   * Return true if this Calendrier object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Calendrier calendrier = (Calendrier) o;
    return Objects.equals(this.code, calendrier.code) &&
        Objects.equals(this.contexte, calendrier.contexte) &&
        Objects.equals(this.temoinActif, calendrier.temoinActif) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, contexte, temoinActif, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Calendrier {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    contexte: ").append(toIndentedString(contexte)).append("\n");
    sb.append("    temoinActif: ").append(toIndentedString(temoinActif)).append("\n");
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

    // add `dateDebut` to the URL query string
    if (getDateDebut() != null) {
      joiner.add(String.format("%sdateDebut%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getDateDebut()))));
    }

    // add `dateFin` to the URL query string
    if (getDateFin() != null) {
      joiner.add(String.format("%sdateFin%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getDateFin()))));
    }

    // add `code` to the URL query string
    if (getCode() != null) {
      joiner.add(String.format("%scode%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getCode()))));
    }

    // add `contexte` to the URL query string
    if (getContexte() != null) {
      for (int i = 0; i < getContexte().size(); i++) {
        if (getContexte().get(i) != null) {
          joiner.add(String.format("%scontexte%s%s=%s", prefix, suffix,
              "".equals(suffix) ? "" : String.format("%s%d%s", containerPrefix, i, containerSuffix),
              ApiClient.urlEncode(ApiClient.valueToString(getContexte().get(i)))));
        }
      }
    }

    // add `temoinActif` to the URL query string
    if (getTemoinActif() != null) {
      joiner.add(String.format("%stemoinActif%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getTemoinActif()))));
    }

    return joiner.toString();
  }
static {
  // Initialize and register the discriminator mappings.
  Map<String, Class<?>> mappings = new HashMap<String, Class<?>>();
  mappings.put("Calendrier", Calendrier.class);
  JSON.registerDiscriminator(Calendrier.class, "est1", mappings);
}
}

