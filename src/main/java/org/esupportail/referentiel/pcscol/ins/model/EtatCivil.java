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
 * EtatCivil
 */
@JsonPropertyOrder({
  EtatCivil.JSON_PROPERTY_NOM_DE_NAISSANCE,
  EtatCivil.JSON_PROPERTY_NOM_USUEL,
  EtatCivil.JSON_PROPERTY_PRENOM,
  EtatCivil.JSON_PROPERTY_DEUXIEME_PRENOM,
  EtatCivil.JSON_PROPERTY_TROISIEME_PRENOM,
  EtatCivil.JSON_PROPERTY_GENRE
})
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-03-24T10:28:25.069148496+01:00[Europe/Paris]", comments = "Generator version: 7.12.0")
public class EtatCivil {
  public static final String JSON_PROPERTY_NOM_DE_NAISSANCE = "nomDeNaissance";
  @jakarta.annotation.Nullable
  private String nomDeNaissance;

  public static final String JSON_PROPERTY_NOM_USUEL = "nomUsuel";
  @jakarta.annotation.Nullable
  private String nomUsuel;

  public static final String JSON_PROPERTY_PRENOM = "prenom";
  @jakarta.annotation.Nullable
  private String prenom;

  public static final String JSON_PROPERTY_DEUXIEME_PRENOM = "deuxiemePrenom";
  @jakarta.annotation.Nullable
  private String deuxiemePrenom;

  public static final String JSON_PROPERTY_TROISIEME_PRENOM = "troisiemePrenom";
  @jakarta.annotation.Nullable
  private String troisiemePrenom;

  public static final String JSON_PROPERTY_GENRE = "genre";
  @jakarta.annotation.Nullable
  private String genre;

  public EtatCivil() { 
  }

  public EtatCivil nomDeNaissance(@jakarta.annotation.Nullable String nomDeNaissance) {
    this.nomDeNaissance = nomDeNaissance;
    return this;
  }

  /**
   * Le nom de naissance
   * @return nomDeNaissance
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_NOM_DE_NAISSANCE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getNomDeNaissance() {
    return nomDeNaissance;
  }


  @JsonProperty(JSON_PROPERTY_NOM_DE_NAISSANCE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setNomDeNaissance(@jakarta.annotation.Nullable String nomDeNaissance) {
    this.nomDeNaissance = nomDeNaissance;
  }


  public EtatCivil nomUsuel(@jakarta.annotation.Nullable String nomUsuel) {
    this.nomUsuel = nomUsuel;
    return this;
  }

  /**
   * Le nom usuel
   * @return nomUsuel
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_NOM_USUEL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getNomUsuel() {
    return nomUsuel;
  }


  @JsonProperty(JSON_PROPERTY_NOM_USUEL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setNomUsuel(@jakarta.annotation.Nullable String nomUsuel) {
    this.nomUsuel = nomUsuel;
  }


  public EtatCivil prenom(@jakarta.annotation.Nullable String prenom) {
    this.prenom = prenom;
    return this;
  }

  /**
   * Le prénom
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
  public void setPrenom(@jakarta.annotation.Nullable String prenom) {
    this.prenom = prenom;
  }


  public EtatCivil deuxiemePrenom(@jakarta.annotation.Nullable String deuxiemePrenom) {
    this.deuxiemePrenom = deuxiemePrenom;
    return this;
  }

  /**
   * Le deuxième prénom
   * @return deuxiemePrenom
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_DEUXIEME_PRENOM)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getDeuxiemePrenom() {
    return deuxiemePrenom;
  }


  @JsonProperty(JSON_PROPERTY_DEUXIEME_PRENOM)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setDeuxiemePrenom(@jakarta.annotation.Nullable String deuxiemePrenom) {
    this.deuxiemePrenom = deuxiemePrenom;
  }


  public EtatCivil troisiemePrenom(@jakarta.annotation.Nullable String troisiemePrenom) {
    this.troisiemePrenom = troisiemePrenom;
    return this;
  }

  /**
   * Le troisième prénom
   * @return troisiemePrenom
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_TROISIEME_PRENOM)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getTroisiemePrenom() {
    return troisiemePrenom;
  }


  @JsonProperty(JSON_PROPERTY_TROISIEME_PRENOM)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setTroisiemePrenom(@jakarta.annotation.Nullable String troisiemePrenom) {
    this.troisiemePrenom = troisiemePrenom;
  }


  public EtatCivil genre(@jakarta.annotation.Nullable String genre) {
    this.genre = genre;
    return this;
  }

  /**
   * Les valeurs &#x60;M&#x60; ou &#x60;F&#x60; sont acceptées
   * @return genre
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_GENRE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getGenre() {
    return genre;
  }


  @JsonProperty(JSON_PROPERTY_GENRE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setGenre(@jakarta.annotation.Nullable String genre) {
    this.genre = genre;
  }


  /**
   * Return true if this EtatCivil object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EtatCivil etatCivil = (EtatCivil) o;
    return Objects.equals(this.nomDeNaissance, etatCivil.nomDeNaissance) &&
        Objects.equals(this.nomUsuel, etatCivil.nomUsuel) &&
        Objects.equals(this.prenom, etatCivil.prenom) &&
        Objects.equals(this.deuxiemePrenom, etatCivil.deuxiemePrenom) &&
        Objects.equals(this.troisiemePrenom, etatCivil.troisiemePrenom) &&
        Objects.equals(this.genre, etatCivil.genre);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nomDeNaissance, nomUsuel, prenom, deuxiemePrenom, troisiemePrenom, genre);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EtatCivil {\n");
    sb.append("    nomDeNaissance: ").append(toIndentedString(nomDeNaissance)).append("\n");
    sb.append("    nomUsuel: ").append(toIndentedString(nomUsuel)).append("\n");
    sb.append("    prenom: ").append(toIndentedString(prenom)).append("\n");
    sb.append("    deuxiemePrenom: ").append(toIndentedString(deuxiemePrenom)).append("\n");
    sb.append("    troisiemePrenom: ").append(toIndentedString(troisiemePrenom)).append("\n");
    sb.append("    genre: ").append(toIndentedString(genre)).append("\n");
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

    // add `nomDeNaissance` to the URL query string
    if (getNomDeNaissance() != null) {
      joiner.add(String.format("%snomDeNaissance%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getNomDeNaissance()))));
    }

    // add `nomUsuel` to the URL query string
    if (getNomUsuel() != null) {
      joiner.add(String.format("%snomUsuel%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getNomUsuel()))));
    }

    // add `prenom` to the URL query string
    if (getPrenom() != null) {
      joiner.add(String.format("%sprenom%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getPrenom()))));
    }

    // add `deuxiemePrenom` to the URL query string
    if (getDeuxiemePrenom() != null) {
      joiner.add(String.format("%sdeuxiemePrenom%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getDeuxiemePrenom()))));
    }

    // add `troisiemePrenom` to the URL query string
    if (getTroisiemePrenom() != null) {
      joiner.add(String.format("%stroisiemePrenom%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getTroisiemePrenom()))));
    }

    // add `genre` to the URL query string
    if (getGenre() != null) {
      joiner.add(String.format("%sgenre%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getGenre()))));
    }

    return joiner.toString();
  }
}

