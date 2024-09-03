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
 * Naissance
 */
@JsonPropertyOrder({
  Naissance.JSON_PROPERTY_DATE_DE_NAISSANCE,
  Naissance.JSON_PROPERTY_PAYS_DE_NAISSANCE,
  Naissance.JSON_PROPERTY_LIBELLE_PAYS_DE_NAISSANCE,
  Naissance.JSON_PROPERTY_COMMUNE_DE_NAISSANCE,
  Naissance.JSON_PROPERTY_LIBELLE_COMMUNE_DE_NAISSANCE,
  Naissance.JSON_PROPERTY_COMMUNE_DE_NAISSANCE_ETRANGER,
  Naissance.JSON_PROPERTY_NATIONALITE,
  Naissance.JSON_PROPERTY_LIBELLE_NATIONALITE,
  Naissance.JSON_PROPERTY_DEUXIEME_NATIONALITE,
  Naissance.JSON_PROPERTY_LIBELLE_DEUXIEME_NATIONALITE,
  Naissance.JSON_PROPERTY_DATE_D_OBTENTION_DE_LA_DEUXIEME_NATIONALITE
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-23T10:43:17.979817+02:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class Naissance {
  public static final String JSON_PROPERTY_DATE_DE_NAISSANCE = "dateDeNaissance";
  private String dateDeNaissance;

  public static final String JSON_PROPERTY_PAYS_DE_NAISSANCE = "paysDeNaissance";
  private String paysDeNaissance;

  public static final String JSON_PROPERTY_LIBELLE_PAYS_DE_NAISSANCE = "libellePaysDeNaissance";
  private String libellePaysDeNaissance;

  public static final String JSON_PROPERTY_COMMUNE_DE_NAISSANCE = "communeDeNaissance";
  private String communeDeNaissance;

  public static final String JSON_PROPERTY_LIBELLE_COMMUNE_DE_NAISSANCE = "libelleCommuneDeNaissance";
  private String libelleCommuneDeNaissance;

  public static final String JSON_PROPERTY_COMMUNE_DE_NAISSANCE_ETRANGER = "communeDeNaissanceEtranger";
  private String communeDeNaissanceEtranger;

  public static final String JSON_PROPERTY_NATIONALITE = "nationalite";
  private String nationalite;

  public static final String JSON_PROPERTY_LIBELLE_NATIONALITE = "libelleNationalite";
  private String libelleNationalite;

  public static final String JSON_PROPERTY_DEUXIEME_NATIONALITE = "deuxiemeNationalite";
  private String deuxiemeNationalite;

  public static final String JSON_PROPERTY_LIBELLE_DEUXIEME_NATIONALITE = "libelleDeuxiemeNationalite";
  private String libelleDeuxiemeNationalite;

  public static final String JSON_PROPERTY_DATE_D_OBTENTION_DE_LA_DEUXIEME_NATIONALITE = "dateDObtentionDeLaDeuxiemeNationalite";
  private String dateDObtentionDeLaDeuxiemeNationalite;

  public Naissance() { 
  }

  public Naissance dateDeNaissance(String dateDeNaissance) {
    this.dateDeNaissance = dateDeNaissance;
    return this;
  }

  /**
   * La date de naissance au format AAAA-MM-JJ
   * @return dateDeNaissance
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_DATE_DE_NAISSANCE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getDateDeNaissance() {
    return dateDeNaissance;
  }


  @JsonProperty(JSON_PROPERTY_DATE_DE_NAISSANCE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setDateDeNaissance(String dateDeNaissance) {
    this.dateDeNaissance = dateDeNaissance;
  }


  public Naissance paysDeNaissance(String paysDeNaissance) {
    this.paysDeNaissance = paysDeNaissance;
    return this;
  }

  /**
   * Le code pays du pays de naissance issu de la nomenclature Pays et Nationalités Ex : 100 &#x3D; France 
   * @return paysDeNaissance
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_PAYS_DE_NAISSANCE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getPaysDeNaissance() {
    return paysDeNaissance;
  }


  @JsonProperty(JSON_PROPERTY_PAYS_DE_NAISSANCE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setPaysDeNaissance(String paysDeNaissance) {
    this.paysDeNaissance = paysDeNaissance;
  }


  public Naissance libellePaysDeNaissance(String libellePaysDeNaissance) {
    this.libellePaysDeNaissance = libellePaysDeNaissance;
    return this;
  }

  /**
   * Le libelle du pays de naissance issu de la nomenclature Pays et Nationalités Ex : 100 &#x3D; France 
   * @return libellePaysDeNaissance
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_LIBELLE_PAYS_DE_NAISSANCE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getLibellePaysDeNaissance() {
    return libellePaysDeNaissance;
  }


  @JsonProperty(JSON_PROPERTY_LIBELLE_PAYS_DE_NAISSANCE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setLibellePaysDeNaissance(String libellePaysDeNaissance) {
    this.libellePaysDeNaissance = libellePaysDeNaissance;
  }


  public Naissance communeDeNaissance(String communeDeNaissance) {
    this.communeDeNaissance = communeDeNaissance;
    return this;
  }

  /**
   * Le code INSEE de la commune de naissance en France Ex : 67482 &#x3D; Strasbourg 
   * @return communeDeNaissance
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_COMMUNE_DE_NAISSANCE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getCommuneDeNaissance() {
    return communeDeNaissance;
  }


  @JsonProperty(JSON_PROPERTY_COMMUNE_DE_NAISSANCE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCommuneDeNaissance(String communeDeNaissance) {
    this.communeDeNaissance = communeDeNaissance;
  }


  public Naissance libelleCommuneDeNaissance(String libelleCommuneDeNaissance) {
    this.libelleCommuneDeNaissance = libelleCommuneDeNaissance;
    return this;
  }

  /**
   * Le code INSEE de la commune de naissance en France Ex : 67482 &#x3D; Strasbourg 
   * @return libelleCommuneDeNaissance
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_LIBELLE_COMMUNE_DE_NAISSANCE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getLibelleCommuneDeNaissance() {
    return libelleCommuneDeNaissance;
  }


  @JsonProperty(JSON_PROPERTY_LIBELLE_COMMUNE_DE_NAISSANCE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setLibelleCommuneDeNaissance(String libelleCommuneDeNaissance) {
    this.libelleCommuneDeNaissance = libelleCommuneDeNaissance;
  }


  public Naissance communeDeNaissanceEtranger(String communeDeNaissanceEtranger) {
    this.communeDeNaissanceEtranger = communeDeNaissanceEtranger;
    return this;
  }

  /**
   * La commune de naissance à l&#39;étranger
   * @return communeDeNaissanceEtranger
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_COMMUNE_DE_NAISSANCE_ETRANGER)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getCommuneDeNaissanceEtranger() {
    return communeDeNaissanceEtranger;
  }


  @JsonProperty(JSON_PROPERTY_COMMUNE_DE_NAISSANCE_ETRANGER)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCommuneDeNaissanceEtranger(String communeDeNaissanceEtranger) {
    this.communeDeNaissanceEtranger = communeDeNaissanceEtranger;
  }


  public Naissance nationalite(String nationalite) {
    this.nationalite = nationalite;
    return this;
  }

  /**
   * Le code pays associé à la nationalité issu de la nomenclature Pays et Nationalités
   * @return nationalite
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_NATIONALITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getNationalite() {
    return nationalite;
  }


  @JsonProperty(JSON_PROPERTY_NATIONALITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setNationalite(String nationalite) {
    this.nationalite = nationalite;
  }


  public Naissance libelleNationalite(String libelleNationalite) {
    this.libelleNationalite = libelleNationalite;
    return this;
  }

  /**
   * Le libelle pays associé à la nationalité issu de la nomenclature Pays et Nationalités
   * @return libelleNationalite
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_LIBELLE_NATIONALITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getLibelleNationalite() {
    return libelleNationalite;
  }


  @JsonProperty(JSON_PROPERTY_LIBELLE_NATIONALITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setLibelleNationalite(String libelleNationalite) {
    this.libelleNationalite = libelleNationalite;
  }


  public Naissance deuxiemeNationalite(String deuxiemeNationalite) {
    this.deuxiemeNationalite = deuxiemeNationalite;
    return this;
  }

  /**
   * Le code pays associé à la deuxième nationalité issu de la nomenclature Pays et Nationalités
   * @return deuxiemeNationalite
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_DEUXIEME_NATIONALITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getDeuxiemeNationalite() {
    return deuxiemeNationalite;
  }


  @JsonProperty(JSON_PROPERTY_DEUXIEME_NATIONALITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setDeuxiemeNationalite(String deuxiemeNationalite) {
    this.deuxiemeNationalite = deuxiemeNationalite;
  }


  public Naissance libelleDeuxiemeNationalite(String libelleDeuxiemeNationalite) {
    this.libelleDeuxiemeNationalite = libelleDeuxiemeNationalite;
    return this;
  }

  /**
   * Le libelle pays associé à la deuxième nationalité issu de la nomenclature Pays et Nationalités
   * @return libelleDeuxiemeNationalite
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_LIBELLE_DEUXIEME_NATIONALITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getLibelleDeuxiemeNationalite() {
    return libelleDeuxiemeNationalite;
  }


  @JsonProperty(JSON_PROPERTY_LIBELLE_DEUXIEME_NATIONALITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setLibelleDeuxiemeNationalite(String libelleDeuxiemeNationalite) {
    this.libelleDeuxiemeNationalite = libelleDeuxiemeNationalite;
  }


  public Naissance dateDObtentionDeLaDeuxiemeNationalite(String dateDObtentionDeLaDeuxiemeNationalite) {
    this.dateDObtentionDeLaDeuxiemeNationalite = dateDObtentionDeLaDeuxiemeNationalite;
    return this;
  }

  /**
   * La date d&#39;obtention de la deuxième nationalité au format AAAA-MM-JJ
   * @return dateDObtentionDeLaDeuxiemeNationalite
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_DATE_D_OBTENTION_DE_LA_DEUXIEME_NATIONALITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getDateDObtentionDeLaDeuxiemeNationalite() {
    return dateDObtentionDeLaDeuxiemeNationalite;
  }


  @JsonProperty(JSON_PROPERTY_DATE_D_OBTENTION_DE_LA_DEUXIEME_NATIONALITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setDateDObtentionDeLaDeuxiemeNationalite(String dateDObtentionDeLaDeuxiemeNationalite) {
    this.dateDObtentionDeLaDeuxiemeNationalite = dateDObtentionDeLaDeuxiemeNationalite;
  }


  /**
   * Return true if this Naissance object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Naissance naissance = (Naissance) o;
    return Objects.equals(this.dateDeNaissance, naissance.dateDeNaissance) &&
        Objects.equals(this.paysDeNaissance, naissance.paysDeNaissance) &&
        Objects.equals(this.libellePaysDeNaissance, naissance.libellePaysDeNaissance) &&
        Objects.equals(this.communeDeNaissance, naissance.communeDeNaissance) &&
        Objects.equals(this.libelleCommuneDeNaissance, naissance.libelleCommuneDeNaissance) &&
        Objects.equals(this.communeDeNaissanceEtranger, naissance.communeDeNaissanceEtranger) &&
        Objects.equals(this.nationalite, naissance.nationalite) &&
        Objects.equals(this.libelleNationalite, naissance.libelleNationalite) &&
        Objects.equals(this.deuxiemeNationalite, naissance.deuxiemeNationalite) &&
        Objects.equals(this.libelleDeuxiemeNationalite, naissance.libelleDeuxiemeNationalite) &&
        Objects.equals(this.dateDObtentionDeLaDeuxiemeNationalite, naissance.dateDObtentionDeLaDeuxiemeNationalite);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dateDeNaissance, paysDeNaissance, libellePaysDeNaissance, communeDeNaissance, libelleCommuneDeNaissance, communeDeNaissanceEtranger, nationalite, libelleNationalite, deuxiemeNationalite, libelleDeuxiemeNationalite, dateDObtentionDeLaDeuxiemeNationalite);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Naissance {\n");
    sb.append("    dateDeNaissance: ").append(toIndentedString(dateDeNaissance)).append("\n");
    sb.append("    paysDeNaissance: ").append(toIndentedString(paysDeNaissance)).append("\n");
    sb.append("    libellePaysDeNaissance: ").append(toIndentedString(libellePaysDeNaissance)).append("\n");
    sb.append("    communeDeNaissance: ").append(toIndentedString(communeDeNaissance)).append("\n");
    sb.append("    libelleCommuneDeNaissance: ").append(toIndentedString(libelleCommuneDeNaissance)).append("\n");
    sb.append("    communeDeNaissanceEtranger: ").append(toIndentedString(communeDeNaissanceEtranger)).append("\n");
    sb.append("    nationalite: ").append(toIndentedString(nationalite)).append("\n");
    sb.append("    libelleNationalite: ").append(toIndentedString(libelleNationalite)).append("\n");
    sb.append("    deuxiemeNationalite: ").append(toIndentedString(deuxiemeNationalite)).append("\n");
    sb.append("    libelleDeuxiemeNationalite: ").append(toIndentedString(libelleDeuxiemeNationalite)).append("\n");
    sb.append("    dateDObtentionDeLaDeuxiemeNationalite: ").append(toIndentedString(dateDObtentionDeLaDeuxiemeNationalite)).append("\n");
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

    // add `dateDeNaissance` to the URL query string
    if (getDateDeNaissance() != null) {
      joiner.add(String.format("%sdateDeNaissance%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getDateDeNaissance()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `paysDeNaissance` to the URL query string
    if (getPaysDeNaissance() != null) {
      joiner.add(String.format("%spaysDeNaissance%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getPaysDeNaissance()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `libellePaysDeNaissance` to the URL query string
    if (getLibellePaysDeNaissance() != null) {
      joiner.add(String.format("%slibellePaysDeNaissance%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getLibellePaysDeNaissance()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `communeDeNaissance` to the URL query string
    if (getCommuneDeNaissance() != null) {
      joiner.add(String.format("%scommuneDeNaissance%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCommuneDeNaissance()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `libelleCommuneDeNaissance` to the URL query string
    if (getLibelleCommuneDeNaissance() != null) {
      joiner.add(String.format("%slibelleCommuneDeNaissance%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getLibelleCommuneDeNaissance()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `communeDeNaissanceEtranger` to the URL query string
    if (getCommuneDeNaissanceEtranger() != null) {
      joiner.add(String.format("%scommuneDeNaissanceEtranger%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCommuneDeNaissanceEtranger()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `nationalite` to the URL query string
    if (getNationalite() != null) {
      joiner.add(String.format("%snationalite%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getNationalite()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `libelleNationalite` to the URL query string
    if (getLibelleNationalite() != null) {
      joiner.add(String.format("%slibelleNationalite%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getLibelleNationalite()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `deuxiemeNationalite` to the URL query string
    if (getDeuxiemeNationalite() != null) {
      joiner.add(String.format("%sdeuxiemeNationalite%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getDeuxiemeNationalite()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `libelleDeuxiemeNationalite` to the URL query string
    if (getLibelleDeuxiemeNationalite() != null) {
      joiner.add(String.format("%slibelleDeuxiemeNationalite%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getLibelleDeuxiemeNationalite()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `dateDObtentionDeLaDeuxiemeNationalite` to the URL query string
    if (getDateDObtentionDeLaDeuxiemeNationalite() != null) {
      joiner.add(String.format("%sdateDObtentionDeLaDeuxiemeNationalite%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getDateDObtentionDeLaDeuxiemeNationalite()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    return joiner.toString();
  }
}

