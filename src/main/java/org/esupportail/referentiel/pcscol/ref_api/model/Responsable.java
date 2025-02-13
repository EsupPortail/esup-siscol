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
import java.util.Objects;
import java.util.StringJoiner;

import org.esupportail.referentiel.pcscol.invoker.ApiClient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
/**
 * Responsable
 */
@JsonPropertyOrder({
  Responsable.JSON_PROPERTY_NOM,
  Responsable.JSON_PROPERTY_PRENOM,
  Responsable.JSON_PROPERTY_TITRE,
  Responsable.JSON_PROPERTY_DATE_DEBUT_VALIDITE,
  Responsable.JSON_PROPERTY_DATE_FIN_VALIDITE
})
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-30T09:24:02.851634+02:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class Responsable {
  public static final String JSON_PROPERTY_NOM = "nom";
  private String nom;

  public static final String JSON_PROPERTY_PRENOM = "prenom";
  private String prenom;

  public static final String JSON_PROPERTY_TITRE = "titre";
  private String titre;

  public static final String JSON_PROPERTY_DATE_DEBUT_VALIDITE = "dateDebutValidite";
  private Date dateDebutValidite;

  public static final String JSON_PROPERTY_DATE_FIN_VALIDITE = "dateFinValidite";
  private Date dateFinValidite;

  public Responsable() { 
  }

  public Responsable nom(String nom) {
    this.nom = nom;
    return this;
  }

  /**
   * nom
   * @return nom
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_NOM)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getNom() {
    return nom;
  }


  @JsonProperty(JSON_PROPERTY_NOM)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setNom(String nom) {
    this.nom = nom;
  }


  public Responsable prenom(String prenom) {
    this.prenom = prenom;
    return this;
  }

  /**
   * prenom
   * @return prenom
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_PRENOM)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getPrenom() {
    return prenom;
  }


  @JsonProperty(JSON_PROPERTY_PRENOM)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }


  public Responsable titre(String titre) {
    this.titre = titre;
    return this;
  }

  /**
   * titre
   * @return titre
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_TITRE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getTitre() {
    return titre;
  }


  @JsonProperty(JSON_PROPERTY_TITRE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setTitre(String titre) {
    this.titre = titre;
  }


  public Responsable dateDebutValidite(Date dateDebutValidite) {
    this.dateDebutValidite = dateDebutValidite;
    return this;
  }

  /**
   * Date du début de la validité de responsable
   * @return dateDebutValidite
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_DATE_DEBUT_VALIDITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Date getDateDebutValidite() {
    return dateDebutValidite;
  }


  @JsonProperty(JSON_PROPERTY_DATE_DEBUT_VALIDITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setDateDebutValidite(Date dateDebutValidite) {
    this.dateDebutValidite = dateDebutValidite;
  }


  public Responsable dateFinValidite(Date dateFinValidite) {
    this.dateFinValidite = dateFinValidite;
    return this;
  }

  /**
   * Date de la fin de la validité de responsable
   * @return dateFinValidite
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_DATE_FIN_VALIDITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Date getDateFinValidite() {
    return dateFinValidite;
  }


  @JsonProperty(JSON_PROPERTY_DATE_FIN_VALIDITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setDateFinValidite(Date dateFinValidite) {
    this.dateFinValidite = dateFinValidite;
  }


  /**
   * Return true if this Responsable object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Responsable responsable = (Responsable) o;
    return Objects.equals(this.nom, responsable.nom) &&
        Objects.equals(this.prenom, responsable.prenom) &&
        Objects.equals(this.titre, responsable.titre) &&
        Objects.equals(this.dateDebutValidite, responsable.dateDebutValidite) &&
        Objects.equals(this.dateFinValidite, responsable.dateFinValidite);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nom, prenom, titre, dateDebutValidite, dateFinValidite);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Responsable {\n");
    sb.append("    nom: ").append(toIndentedString(nom)).append("\n");
    sb.append("    prenom: ").append(toIndentedString(prenom)).append("\n");
    sb.append("    titre: ").append(toIndentedString(titre)).append("\n");
    sb.append("    dateDebutValidite: ").append(toIndentedString(dateDebutValidite)).append("\n");
    sb.append("    dateFinValidite: ").append(toIndentedString(dateFinValidite)).append("\n");
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

    // add `nom` to the URL query string
    if (getNom() != null) {
      joiner.add(String.format("%snom%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getNom()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `prenom` to the URL query string
    if (getPrenom() != null) {
      joiner.add(String.format("%sprenom%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getPrenom()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `titre` to the URL query string
    if (getTitre() != null) {
      joiner.add(String.format("%stitre%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getTitre()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `dateDebutValidite` to the URL query string
    if (getDateDebutValidite() != null) {
      joiner.add(String.format("%sdateDebutValidite%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getDateDebutValidite()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `dateFinValidite` to the URL query string
    if (getDateFinValidite() != null) {
      joiner.add(String.format("%sdateFinValidite%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getDateFinValidite()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    return joiner.toString();
  }
}

