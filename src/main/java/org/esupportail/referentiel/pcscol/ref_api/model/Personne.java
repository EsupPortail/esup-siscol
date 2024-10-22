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
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

import org.esupportail.referentiel.pcscol.invoker.ApiClient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
/**
 * Personne
 */
@JsonPropertyOrder({
  Personne.JSON_PROPERTY_CIVILITE,
  Personne.JSON_PROPERTY_SEXE,
  Personne.JSON_PROPERTY_NOM_USAGE,
  Personne.JSON_PROPERTY_NOM_FAMILLE,
  Personne.JSON_PROPERTY_PRENOM1,
  Personne.JSON_PROPERTY_PRENOM2,
  Personne.JSON_PROPERTY_PRENOM3,
  Personne.JSON_PROPERTY_DATE_NAISSANCE,
  Personne.JSON_PROPERTY_DATE_DEBUT_VALIDITE,
  Personne.JSON_PROPERTY_DATE_FIN_VALIDITE,
  Personne.JSON_PROPERTY_PERSONNE_UTILISATEUR_LST,
  Personne.JSON_PROPERTY_SOURCE_PERSONNE_LST,
  Personne.JSON_PROPERTY_PERSONNE_TYPE_PERSONNE_LST
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-30T09:24:02.851634+02:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class Personne {
  public static final String JSON_PROPERTY_CIVILITE = "civilite";
  private String civilite;

  public static final String JSON_PROPERTY_SEXE = "sexe";
  private String sexe;

  public static final String JSON_PROPERTY_NOM_USAGE = "nomUsage";
  private String nomUsage;

  public static final String JSON_PROPERTY_NOM_FAMILLE = "nomFamille";
  private String nomFamille;

  public static final String JSON_PROPERTY_PRENOM1 = "prenom1";
  private String prenom1;

  public static final String JSON_PROPERTY_PRENOM2 = "prenom2";
  private String prenom2;

  public static final String JSON_PROPERTY_PRENOM3 = "prenom3";
  private String prenom3;

  public static final String JSON_PROPERTY_DATE_NAISSANCE = "dateNaissance";
  private Date dateNaissance;

  public static final String JSON_PROPERTY_DATE_DEBUT_VALIDITE = "dateDebutValidite";
  private Date dateDebutValidite;

  public static final String JSON_PROPERTY_DATE_FIN_VALIDITE = "dateFinValidite";
  private Date dateFinValidite;

  public static final String JSON_PROPERTY_PERSONNE_UTILISATEUR_LST = "personneUtilisateurLst";
  private List<PersonneUtilisateur> personneUtilisateurLst = new ArrayList<>();

  public static final String JSON_PROPERTY_SOURCE_PERSONNE_LST = "sourcePersonneLst";
  private List<SourcePersonne> sourcePersonneLst = new ArrayList<>();

  public static final String JSON_PROPERTY_PERSONNE_TYPE_PERSONNE_LST = "personneTypePersonneLst";
  private List<PersonneTypePersonne> personneTypePersonneLst = new ArrayList<>();

  public Personne() { 
  }

  public Personne civilite(String civilite) {
    this.civilite = civilite;
    return this;
  }

  /**
   * Civilté de la personne
   * @return civilite
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CIVILITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getCivilite() {
    return civilite;
  }


  @JsonProperty(JSON_PROPERTY_CIVILITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCivilite(String civilite) {
    this.civilite = civilite;
  }


  public Personne sexe(String sexe) {
    this.sexe = sexe;
    return this;
  }

  /**
   * Sexe de la personne
   * @return sexe
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_SEXE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getSexe() {
    return sexe;
  }


  @JsonProperty(JSON_PROPERTY_SEXE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSexe(String sexe) {
    this.sexe = sexe;
  }


  public Personne nomUsage(String nomUsage) {
    this.nomUsage = nomUsage;
    return this;
  }

  /**
   * Nom d&#39;usage de la personne
   * @return nomUsage
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_NOM_USAGE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getNomUsage() {
    return nomUsage;
  }


  @JsonProperty(JSON_PROPERTY_NOM_USAGE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setNomUsage(String nomUsage) {
    this.nomUsage = nomUsage;
  }


  public Personne nomFamille(String nomFamille) {
    this.nomFamille = nomFamille;
    return this;
  }

  /**
   * Nom patronymique de la personne
   * @return nomFamille
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_NOM_FAMILLE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getNomFamille() {
    return nomFamille;
  }


  @JsonProperty(JSON_PROPERTY_NOM_FAMILLE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setNomFamille(String nomFamille) {
    this.nomFamille = nomFamille;
  }


  public Personne prenom1(String prenom1) {
    this.prenom1 = prenom1;
    return this;
  }

  /**
   * Prénom principal de la personne
   * @return prenom1
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_PRENOM1)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getPrenom1() {
    return prenom1;
  }


  @JsonProperty(JSON_PROPERTY_PRENOM1)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setPrenom1(String prenom1) {
    this.prenom1 = prenom1;
  }


  public Personne prenom2(String prenom2) {
    this.prenom2 = prenom2;
    return this;
  }

  /**
   * Prénom secondaire de la personne
   * @return prenom2
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_PRENOM2)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getPrenom2() {
    return prenom2;
  }


  @JsonProperty(JSON_PROPERTY_PRENOM2)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setPrenom2(String prenom2) {
    this.prenom2 = prenom2;
  }


  public Personne prenom3(String prenom3) {
    this.prenom3 = prenom3;
    return this;
  }

  /**
   * Troisième prénom de la personne
   * @return prenom3
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_PRENOM3)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getPrenom3() {
    return prenom3;
  }


  @JsonProperty(JSON_PROPERTY_PRENOM3)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setPrenom3(String prenom3) {
    this.prenom3 = prenom3;
  }


  public Personne dateNaissance(Date dateNaissance) {
    this.dateNaissance = dateNaissance;
    return this;
  }

  /**
   * Date de naissance de la personne
   * @return dateNaissance
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_DATE_NAISSANCE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Date getDateNaissance() {
    return dateNaissance;
  }


  @JsonProperty(JSON_PROPERTY_DATE_NAISSANCE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setDateNaissance(Date dateNaissance) {
    this.dateNaissance = dateNaissance;
  }


  public Personne dateDebutValidite(Date dateDebutValidite) {
    this.dateDebutValidite = dateDebutValidite;
    return this;
  }

  /**
   * Date du début de la validité de la personne
   * @return dateDebutValidite
   */
  @javax.annotation.Nullable
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


  public Personne dateFinValidite(Date dateFinValidite) {
    this.dateFinValidite = dateFinValidite;
    return this;
  }

  /**
   * Date de la fin de la validité de la personne
   * @return dateFinValidite
   */
  @javax.annotation.Nullable
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


  public Personne personneUtilisateurLst(List<PersonneUtilisateur> personneUtilisateurLst) {
    this.personneUtilisateurLst = personneUtilisateurLst;
    return this;
  }

  public Personne addPersonneUtilisateurLstItem(PersonneUtilisateur personneUtilisateurLstItem) {
    if (this.personneUtilisateurLst == null) {
      this.personneUtilisateurLst = new ArrayList<>();
    }
    this.personneUtilisateurLst.add(personneUtilisateurLstItem);
    return this;
  }

  /**
   * Association entre personne et utilisateur
   * @return personneUtilisateurLst
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_PERSONNE_UTILISATEUR_LST)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public List<PersonneUtilisateur> getPersonneUtilisateurLst() {
    return personneUtilisateurLst;
  }


  @JsonProperty(JSON_PROPERTY_PERSONNE_UTILISATEUR_LST)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setPersonneUtilisateurLst(List<PersonneUtilisateur> personneUtilisateurLst) {
    this.personneUtilisateurLst = personneUtilisateurLst;
  }


  public Personne sourcePersonneLst(List<SourcePersonne> sourcePersonneLst) {
    this.sourcePersonneLst = sourcePersonneLst;
    return this;
  }

  public Personne addSourcePersonneLstItem(SourcePersonne sourcePersonneLstItem) {
    if (this.sourcePersonneLst == null) {
      this.sourcePersonneLst = new ArrayList<>();
    }
    this.sourcePersonneLst.add(sourcePersonneLstItem);
    return this;
  }

  /**
   * Source d&#39;alimentation de personne (GRHUM, SINAPS, SIRH etablissement, ...)
   * @return sourcePersonneLst
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_SOURCE_PERSONNE_LST)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public List<SourcePersonne> getSourcePersonneLst() {
    return sourcePersonneLst;
  }


  @JsonProperty(JSON_PROPERTY_SOURCE_PERSONNE_LST)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSourcePersonneLst(List<SourcePersonne> sourcePersonneLst) {
    this.sourcePersonneLst = sourcePersonneLst;
  }


  public Personne personneTypePersonneLst(List<PersonneTypePersonne> personneTypePersonneLst) {
    this.personneTypePersonneLst = personneTypePersonneLst;
    return this;
  }

  public Personne addPersonneTypePersonneLstItem(PersonneTypePersonne personneTypePersonneLstItem) {
    if (this.personneTypePersonneLst == null) {
      this.personneTypePersonneLst = new ArrayList<>();
    }
    this.personneTypePersonneLst.add(personneTypePersonneLstItem);
    return this;
  }

  /**
   * Association entre personne et type personne
   * @return personneTypePersonneLst
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_PERSONNE_TYPE_PERSONNE_LST)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public List<PersonneTypePersonne> getPersonneTypePersonneLst() {
    return personneTypePersonneLst;
  }


  @JsonProperty(JSON_PROPERTY_PERSONNE_TYPE_PERSONNE_LST)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setPersonneTypePersonneLst(List<PersonneTypePersonne> personneTypePersonneLst) {
    this.personneTypePersonneLst = personneTypePersonneLst;
  }


  /**
   * Return true if this Personne object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Personne personne = (Personne) o;
    return Objects.equals(this.civilite, personne.civilite) &&
        Objects.equals(this.sexe, personne.sexe) &&
        Objects.equals(this.nomUsage, personne.nomUsage) &&
        Objects.equals(this.nomFamille, personne.nomFamille) &&
        Objects.equals(this.prenom1, personne.prenom1) &&
        Objects.equals(this.prenom2, personne.prenom2) &&
        Objects.equals(this.prenom3, personne.prenom3) &&
        Objects.equals(this.dateNaissance, personne.dateNaissance) &&
        Objects.equals(this.dateDebutValidite, personne.dateDebutValidite) &&
        Objects.equals(this.dateFinValidite, personne.dateFinValidite) &&
        Objects.equals(this.personneUtilisateurLst, personne.personneUtilisateurLst) &&
        Objects.equals(this.sourcePersonneLst, personne.sourcePersonneLst) &&
        Objects.equals(this.personneTypePersonneLst, personne.personneTypePersonneLst);
  }

  @Override
  public int hashCode() {
    return Objects.hash(civilite, sexe, nomUsage, nomFamille, prenom1, prenom2, prenom3, dateNaissance, dateDebutValidite, dateFinValidite, personneUtilisateurLst, sourcePersonneLst, personneTypePersonneLst);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Personne {\n");
    sb.append("    civilite: ").append(toIndentedString(civilite)).append("\n");
    sb.append("    sexe: ").append(toIndentedString(sexe)).append("\n");
    sb.append("    nomUsage: ").append(toIndentedString(nomUsage)).append("\n");
    sb.append("    nomFamille: ").append(toIndentedString(nomFamille)).append("\n");
    sb.append("    prenom1: ").append(toIndentedString(prenom1)).append("\n");
    sb.append("    prenom2: ").append(toIndentedString(prenom2)).append("\n");
    sb.append("    prenom3: ").append(toIndentedString(prenom3)).append("\n");
    sb.append("    dateNaissance: ").append(toIndentedString(dateNaissance)).append("\n");
    sb.append("    dateDebutValidite: ").append(toIndentedString(dateDebutValidite)).append("\n");
    sb.append("    dateFinValidite: ").append(toIndentedString(dateFinValidite)).append("\n");
    sb.append("    personneUtilisateurLst: ").append(toIndentedString(personneUtilisateurLst)).append("\n");
    sb.append("    sourcePersonneLst: ").append(toIndentedString(sourcePersonneLst)).append("\n");
    sb.append("    personneTypePersonneLst: ").append(toIndentedString(personneTypePersonneLst)).append("\n");
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

    // add `civilite` to the URL query string
    if (getCivilite() != null) {
      joiner.add(String.format("%scivilite%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCivilite()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `sexe` to the URL query string
    if (getSexe() != null) {
      joiner.add(String.format("%ssexe%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getSexe()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `nomUsage` to the URL query string
    if (getNomUsage() != null) {
      joiner.add(String.format("%snomUsage%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getNomUsage()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `nomFamille` to the URL query string
    if (getNomFamille() != null) {
      joiner.add(String.format("%snomFamille%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getNomFamille()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `prenom1` to the URL query string
    if (getPrenom1() != null) {
      joiner.add(String.format("%sprenom1%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getPrenom1()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `prenom2` to the URL query string
    if (getPrenom2() != null) {
      joiner.add(String.format("%sprenom2%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getPrenom2()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `prenom3` to the URL query string
    if (getPrenom3() != null) {
      joiner.add(String.format("%sprenom3%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getPrenom3()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `dateNaissance` to the URL query string
    if (getDateNaissance() != null) {
      joiner.add(String.format("%sdateNaissance%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getDateNaissance()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `dateDebutValidite` to the URL query string
    if (getDateDebutValidite() != null) {
      joiner.add(String.format("%sdateDebutValidite%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getDateDebutValidite()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `dateFinValidite` to the URL query string
    if (getDateFinValidite() != null) {
      joiner.add(String.format("%sdateFinValidite%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getDateFinValidite()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `personneUtilisateurLst` to the URL query string
    if (getPersonneUtilisateurLst() != null) {
      for (int i = 0; i < getPersonneUtilisateurLst().size(); i++) {
        if (getPersonneUtilisateurLst().get(i) != null) {
          joiner.add(getPersonneUtilisateurLst().get(i).toUrlQueryString(String.format("%spersonneUtilisateurLst%s%s", prefix, suffix,
          "".equals(suffix) ? "" : String.format("%s%d%s", containerPrefix, i, containerSuffix))));
        }
      }
    }

    // add `sourcePersonneLst` to the URL query string
    if (getSourcePersonneLst() != null) {
      for (int i = 0; i < getSourcePersonneLst().size(); i++) {
        if (getSourcePersonneLst().get(i) != null) {
          joiner.add(getSourcePersonneLst().get(i).toUrlQueryString(String.format("%ssourcePersonneLst%s%s", prefix, suffix,
          "".equals(suffix) ? "" : String.format("%s%d%s", containerPrefix, i, containerSuffix))));
        }
      }
    }

    // add `personneTypePersonneLst` to the URL query string
    if (getPersonneTypePersonneLst() != null) {
      for (int i = 0; i < getPersonneTypePersonneLst().size(); i++) {
        if (getPersonneTypePersonneLst().get(i) != null) {
          joiner.add(getPersonneTypePersonneLst().get(i).toUrlQueryString(String.format("%spersonneTypePersonneLst%s%s", prefix, suffix,
          "".equals(suffix) ? "" : String.format("%s%d%s", containerPrefix, i, containerSuffix))));
        }
      }
    }

    return joiner.toString();
  }
}
