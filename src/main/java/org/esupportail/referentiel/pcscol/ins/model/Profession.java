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

import java.util.Objects;
import java.util.StringJoiner;

import org.esupportail.referentiel.pcscol.invoker.ApiClient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
/**
 * Profession
 */
@JsonPropertyOrder({
  Profession.JSON_PROPERTY_ETUDIANT,
  Profession.JSON_PROPERTY_PARENT1,
  Profession.JSON_PROPERTY_PARENT2,
  Profession.JSON_PROPERTY_QUOTITE
})
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-03-24T10:28:25.069148496+01:00[Europe/Paris]", comments = "Generator version: 7.12.0")
public class Profession {
  public static final String JSON_PROPERTY_ETUDIANT = "etudiant";
  @jakarta.annotation.Nullable
  private String etudiant;

  public static final String JSON_PROPERTY_PARENT1 = "parent1";
  @jakarta.annotation.Nullable
  private String parent1;

  public static final String JSON_PROPERTY_PARENT2 = "parent2";
  @jakarta.annotation.Nullable
  private String parent2;

  public static final String JSON_PROPERTY_QUOTITE = "quotite";
  @jakarta.annotation.Nullable
  private String quotite;

  public Profession() { 
  }

  public Profession etudiant(@jakarta.annotation.Nullable String etudiant) {
    this.etudiant = etudiant;
    return this;
  }

  /**
   * Le code profession de l&#39;étudiant issu de la nomenclature des Professions et catégories socioprofessionnelles.  Codes de la nomenclature sur 6 caractères. Préfixe des codes : PCS 
   * @return etudiant
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ETUDIANT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getEtudiant() {
    return etudiant;
  }


  @JsonProperty(JSON_PROPERTY_ETUDIANT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setEtudiant(@jakarta.annotation.Nullable String etudiant) {
    this.etudiant = etudiant;
  }


  public Profession parent1(@jakarta.annotation.Nullable String parent1) {
    this.parent1 = parent1;
    return this;
  }

  /**
   * Le code profession du parent 1 issu de la nomenclature des Professions et catégories socioprofessionnelles  Codes de la nomenclature sur 6 caractères. Préfixe des codes : PCS 
   * @return parent1
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_PARENT1)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getParent1() {
    return parent1;
  }


  @JsonProperty(JSON_PROPERTY_PARENT1)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setParent1(@jakarta.annotation.Nullable String parent1) {
    this.parent1 = parent1;
  }


  public Profession parent2(@jakarta.annotation.Nullable String parent2) {
    this.parent2 = parent2;
    return this;
  }

  /**
   * Le code profession du parent 2 issu de la nomenclature des Professions et catégories socioprofessionnelles  Codes de la nomenclature sur 6 caractères. Préfixe des codes : PCS 
   * @return parent2
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_PARENT2)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getParent2() {
    return parent2;
  }


  @JsonProperty(JSON_PROPERTY_PARENT2)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setParent2(@jakarta.annotation.Nullable String parent2) {
    this.parent2 = parent2;
  }


  public Profession quotite(@jakarta.annotation.Nullable String quotite) {
    this.quotite = quotite;
    return this;
  }

  /**
   * Le code de la quotité d&#39;activité de l&#39;étudiant issu de la nomenclature des Quotité d&#39;activités  Codes de la nomenclature sur 6 caractères. Préfixe des codes : QUA 
   * @return quotite
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_QUOTITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getQuotite() {
    return quotite;
  }


  @JsonProperty(JSON_PROPERTY_QUOTITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setQuotite(@jakarta.annotation.Nullable String quotite) {
    this.quotite = quotite;
  }


  /**
   * Return true if this Profession object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Profession profession = (Profession) o;
    return Objects.equals(this.etudiant, profession.etudiant) &&
        Objects.equals(this.parent1, profession.parent1) &&
        Objects.equals(this.parent2, profession.parent2) &&
        Objects.equals(this.quotite, profession.quotite);
  }

  @Override
  public int hashCode() {
    return Objects.hash(etudiant, parent1, parent2, quotite);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Profession {\n");
    sb.append("    etudiant: ").append(toIndentedString(etudiant)).append("\n");
    sb.append("    parent1: ").append(toIndentedString(parent1)).append("\n");
    sb.append("    parent2: ").append(toIndentedString(parent2)).append("\n");
    sb.append("    quotite: ").append(toIndentedString(quotite)).append("\n");
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

    // add `etudiant` to the URL query string
    if (getEtudiant() != null) {
      joiner.add(String.format("%setudiant%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getEtudiant()))));
    }

    // add `parent1` to the URL query string
    if (getParent1() != null) {
      joiner.add(String.format("%sparent1%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getParent1()))));
    }

    // add `parent2` to the URL query string
    if (getParent2() != null) {
      joiner.add(String.format("%sparent2%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getParent2()))));
    }

    // add `quotite` to the URL query string
    if (getQuotite() != null) {
      joiner.add(String.format("%squotite%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getQuotite()))));
    }

    return joiner.toString();
  }
}

