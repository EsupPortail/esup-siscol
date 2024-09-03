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
 * TexteParametrable
 */
@JsonPropertyOrder({
  TexteParametrable.JSON_PROPERTY_CODE_STRUCTURE,
  TexteParametrable.JSON_PROPERTY_TYPE_TEXTE,
  TexteParametrable.JSON_PROPERTY_CONTENU
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-23T10:43:17.979817+02:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class TexteParametrable {
  public static final String JSON_PROPERTY_CODE_STRUCTURE = "codeStructure";
  private String codeStructure;

  public static final String JSON_PROPERTY_TYPE_TEXTE = "typeTexte";
  private TypeTexte typeTexte;

  public static final String JSON_PROPERTY_CONTENU = "contenu";
  private String contenu;

  public TexteParametrable() { 
  }

  public TexteParametrable codeStructure(String codeStructure) {
    this.codeStructure = codeStructure;
    return this;
  }

  /**
   * Le code de l&#39;établissement (structure)
   * @return codeStructure
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_CODE_STRUCTURE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public String getCodeStructure() {
    return codeStructure;
  }


  @JsonProperty(JSON_PROPERTY_CODE_STRUCTURE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setCodeStructure(String codeStructure) {
    this.codeStructure = codeStructure;
  }


  public TexteParametrable typeTexte(TypeTexte typeTexte) {
    this.typeTexte = typeTexte;
    return this;
  }

  /**
   * Get typeTexte
   * @return typeTexte
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_TYPE_TEXTE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public TypeTexte getTypeTexte() {
    return typeTexte;
  }


  @JsonProperty(JSON_PROPERTY_TYPE_TEXTE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setTypeTexte(TypeTexte typeTexte) {
    this.typeTexte = typeTexte;
  }


  public TexteParametrable contenu(String contenu) {
    this.contenu = contenu;
    return this;
  }

  /**
   * contenu HTML du texte paramétrable
   * @return contenu
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_CONTENU)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public String getContenu() {
    return contenu;
  }


  @JsonProperty(JSON_PROPERTY_CONTENU)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setContenu(String contenu) {
    this.contenu = contenu;
  }


  /**
   * Return true if this TexteParametrable object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TexteParametrable texteParametrable = (TexteParametrable) o;
    return Objects.equals(this.codeStructure, texteParametrable.codeStructure) &&
        Objects.equals(this.typeTexte, texteParametrable.typeTexte) &&
        Objects.equals(this.contenu, texteParametrable.contenu);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codeStructure, typeTexte, contenu);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TexteParametrable {\n");
    sb.append("    codeStructure: ").append(toIndentedString(codeStructure)).append("\n");
    sb.append("    typeTexte: ").append(toIndentedString(typeTexte)).append("\n");
    sb.append("    contenu: ").append(toIndentedString(contenu)).append("\n");
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

    // add `codeStructure` to the URL query string
    if (getCodeStructure() != null) {
      joiner.add(String.format("%scodeStructure%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCodeStructure()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `typeTexte` to the URL query string
    if (getTypeTexte() != null) {
      joiner.add(String.format("%stypeTexte%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getTypeTexte()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `contenu` to the URL query string
    if (getContenu() != null) {
      joiner.add(String.format("%scontenu%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getContenu()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    return joiner.toString();
  }
}

