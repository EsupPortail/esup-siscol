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
 * L&#39;état d&#39;un accord et sa référence
 */
@JsonPropertyOrder({
  AccordComplet.JSON_PROPERTY_DATE_VALIDATION,
  AccordComplet.JSON_PROPERTY_EST_ACCEPTE,
  AccordComplet.JSON_PROPERTY_DOCUMENT_A_APPROUVER
})
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-23T10:43:17.979817+02:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class AccordComplet {
  public static final String JSON_PROPERTY_DATE_VALIDATION = "dateValidation";
  private String dateValidation;

  public static final String JSON_PROPERTY_EST_ACCEPTE = "estAccepte";
  private Boolean estAccepte;

  public static final String JSON_PROPERTY_DOCUMENT_A_APPROUVER = "documentAApprouver";
  private DocumentAApprouver documentAApprouver;

  public AccordComplet() { 
  }

  public AccordComplet dateValidation(String dateValidation) {
    this.dateValidation = dateValidation;
    return this;
  }

  /**
   * Date de validation de l&#39;accord
   * @return dateValidation
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_DATE_VALIDATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getDateValidation() {
    return dateValidation;
  }


  @JsonProperty(JSON_PROPERTY_DATE_VALIDATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setDateValidation(String dateValidation) {
    this.dateValidation = dateValidation;
  }


  public AccordComplet estAccepte(Boolean estAccepte) {
    this.estAccepte = estAccepte;
    return this;
  }

  /**
   * Témoin pour savoir si le document a été accepté ou refusé
   * @return estAccepte
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_EST_ACCEPTE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Boolean getEstAccepte() {
    return estAccepte;
  }


  @JsonProperty(JSON_PROPERTY_EST_ACCEPTE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setEstAccepte(Boolean estAccepte) {
    this.estAccepte = estAccepte;
  }


  public AccordComplet documentAApprouver(DocumentAApprouver documentAApprouver) {
    this.documentAApprouver = documentAApprouver;
    return this;
  }

  /**
   * Get documentAApprouver
   * @return documentAApprouver
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_DOCUMENT_A_APPROUVER)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public DocumentAApprouver getDocumentAApprouver() {
    return documentAApprouver;
  }


  @JsonProperty(JSON_PROPERTY_DOCUMENT_A_APPROUVER)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setDocumentAApprouver(DocumentAApprouver documentAApprouver) {
    this.documentAApprouver = documentAApprouver;
  }


  /**
   * Return true if this AccordComplet object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccordComplet accordComplet = (AccordComplet) o;
    return Objects.equals(this.dateValidation, accordComplet.dateValidation) &&
        Objects.equals(this.estAccepte, accordComplet.estAccepte) &&
        Objects.equals(this.documentAApprouver, accordComplet.documentAApprouver);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dateValidation, estAccepte, documentAApprouver);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccordComplet {\n");
    sb.append("    dateValidation: ").append(toIndentedString(dateValidation)).append("\n");
    sb.append("    estAccepte: ").append(toIndentedString(estAccepte)).append("\n");
    sb.append("    documentAApprouver: ").append(toIndentedString(documentAApprouver)).append("\n");
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

    // add `dateValidation` to the URL query string
    if (getDateValidation() != null) {
      joiner.add(String.format("%sdateValidation%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getDateValidation()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `estAccepte` to the URL query string
    if (getEstAccepte() != null) {
      joiner.add(String.format("%sestAccepte%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getEstAccepte()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `documentAApprouver` to the URL query string
    if (getDocumentAApprouver() != null) {
      joiner.add(getDocumentAApprouver().toUrlQueryString(prefix + "documentAApprouver" + suffix));
    }

    return joiner.toString();
  }
}

