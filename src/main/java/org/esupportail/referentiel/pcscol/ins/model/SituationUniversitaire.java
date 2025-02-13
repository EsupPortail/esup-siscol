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
import java.util.Objects;
import java.util.StringJoiner;

import org.esupportail.referentiel.pcscol.invoker.ApiClient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
/**
 * SituationUniversitaire
 */
@JsonPropertyOrder({
  SituationUniversitaire.JSON_PROPERTY_CESURE,
  SituationUniversitaire.JSON_PROPERTY_MOBILITE,
  SituationUniversitaire.JSON_PROPERTY_PROGRAMME_ECHANGE
})
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-23T10:43:17.979817+02:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class SituationUniversitaire {
  public static final String JSON_PROPERTY_CESURE = "cesure";
  private Cesure cesure;

  public static final String JSON_PROPERTY_MOBILITE = "mobilite";
  private Mobilite mobilite;

  public static final String JSON_PROPERTY_PROGRAMME_ECHANGE = "programmeEchange";
  private ProgrammeEchange programmeEchange;

  public SituationUniversitaire() { 
  }

  public SituationUniversitaire cesure(Cesure cesure) {
    this.cesure = cesure;
    return this;
  }

  /**
   * Get cesure
   * @return cesure
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CESURE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Cesure getCesure() {
    return cesure;
  }


  @JsonProperty(JSON_PROPERTY_CESURE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCesure(Cesure cesure) {
    this.cesure = cesure;
  }


  public SituationUniversitaire mobilite(Mobilite mobilite) {
    this.mobilite = mobilite;
    return this;
  }

  /**
   * Get mobilite
   * @return mobilite
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_MOBILITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Mobilite getMobilite() {
    return mobilite;
  }


  @JsonProperty(JSON_PROPERTY_MOBILITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setMobilite(Mobilite mobilite) {
    this.mobilite = mobilite;
  }


  public SituationUniversitaire programmeEchange(ProgrammeEchange programmeEchange) {
    this.programmeEchange = programmeEchange;
    return this;
  }

  /**
   * Get programmeEchange
   * @return programmeEchange
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_PROGRAMME_ECHANGE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public ProgrammeEchange getProgrammeEchange() {
    return programmeEchange;
  }


  @JsonProperty(JSON_PROPERTY_PROGRAMME_ECHANGE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setProgrammeEchange(ProgrammeEchange programmeEchange) {
    this.programmeEchange = programmeEchange;
  }


  /**
   * Return true if this SituationUniversitaire object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SituationUniversitaire situationUniversitaire = (SituationUniversitaire) o;
    return Objects.equals(this.cesure, situationUniversitaire.cesure) &&
        Objects.equals(this.mobilite, situationUniversitaire.mobilite) &&
        Objects.equals(this.programmeEchange, situationUniversitaire.programmeEchange);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cesure, mobilite, programmeEchange);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SituationUniversitaire {\n");
    sb.append("    cesure: ").append(toIndentedString(cesure)).append("\n");
    sb.append("    mobilite: ").append(toIndentedString(mobilite)).append("\n");
    sb.append("    programmeEchange: ").append(toIndentedString(programmeEchange)).append("\n");
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

    // add `cesure` to the URL query string
    if (getCesure() != null) {
      joiner.add(String.format("%scesure%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCesure()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `mobilite` to the URL query string
    if (getMobilite() != null) {
      joiner.add(String.format("%smobilite%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getMobilite()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `programmeEchange` to the URL query string
    if (getProgrammeEchange() != null) {
      joiner.add(getProgrammeEchange().toUrlQueryString(prefix + "programmeEchange" + suffix));
    }

    return joiner.toString();
  }
}

