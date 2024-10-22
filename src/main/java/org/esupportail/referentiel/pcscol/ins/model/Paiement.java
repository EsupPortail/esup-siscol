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

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.StringJoiner;

import org.esupportail.referentiel.pcscol.invoker.ApiClient;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
/**
 * Paiement
 */
@JsonPropertyOrder({
  Paiement.JSON_PROPERTY_MODE,
  Paiement.JSON_PROPERTY_REFERENCE,
  Paiement.JSON_PROPERTY_MONTANT,
  Paiement.JSON_PROPERTY_MONTANT_PAYE,
  Paiement.JSON_PROPERTY_DATE_HEURE,
  Paiement.JSON_PROPERTY_PAIEMENT_EN_LIGNE_EN_COURS
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-23T10:43:17.979817+02:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class Paiement {
  public static final String JSON_PROPERTY_MODE = "mode";
  private String mode;

  public static final String JSON_PROPERTY_REFERENCE = "reference";
  private String reference;

  public static final String JSON_PROPERTY_MONTANT = "montant";
  private String montant;

  public static final String JSON_PROPERTY_MONTANT_PAYE = "montantPaye";
  private BigDecimal montantPaye;

  public static final String JSON_PROPERTY_DATE_HEURE = "dateHeure";
  private String dateHeure;

  /**
   * Gets or Sets paiementEnLigneEnCours
   */
  public enum PaiementEnLigneEnCoursEnum {
    PAYBOX("paybox");

    private String value;

    PaiementEnLigneEnCoursEnum(String value) {
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
    public static PaiementEnLigneEnCoursEnum fromValue(String value) {
      for (PaiementEnLigneEnCoursEnum b : PaiementEnLigneEnCoursEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_PAIEMENT_EN_LIGNE_EN_COURS = "paiementEnLigneEnCours";
  private PaiementEnLigneEnCoursEnum paiementEnLigneEnCours;

  public Paiement() { 
  }

  public Paiement mode(String mode) {
    this.mode = mode;
    return this;
  }

  /**
   * mode de paiement selectionné
   * @return mode
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_MODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getMode() {
    return mode;
  }


  @JsonProperty(JSON_PROPERTY_MODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setMode(String mode) {
    this.mode = mode;
  }


  public Paiement reference(String reference) {
    this.reference = reference;
    return this;
  }

  /**
   * Référence ou motif de paiement
   * @return reference
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_REFERENCE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getReference() {
    return reference;
  }


  @JsonProperty(JSON_PROPERTY_REFERENCE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setReference(String reference) {
    this.reference = reference;
  }


  public Paiement montant(String montant) {
    this.montant = montant;
    return this;
  }

  /**
   * Montant du paiement deprecated
   * @return montant
   * @deprecated
   */
  @Deprecated
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_MONTANT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getMontant() {
    return montant;
  }


  @JsonProperty(JSON_PROPERTY_MONTANT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setMontant(String montant) {
    this.montant = montant;
  }


  public Paiement montantPaye(BigDecimal montantPaye) {
    this.montantPaye = montantPaye;
    return this;
  }

  /**
   * Montant du paiement
   * @return montantPaye
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_MONTANT_PAYE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public BigDecimal getMontantPaye() {
    return montantPaye;
  }


  @JsonProperty(JSON_PROPERTY_MONTANT_PAYE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setMontantPaye(BigDecimal montantPaye) {
    this.montantPaye = montantPaye;
  }


  public Paiement dateHeure(String dateHeure) {
    this.dateHeure = dateHeure;
    return this;
  }

  /**
   * La date et l&#39;heure du paiement
   * @return dateHeure
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_DATE_HEURE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getDateHeure() {
    return dateHeure;
  }


  @JsonProperty(JSON_PROPERTY_DATE_HEURE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setDateHeure(String dateHeure) {
    this.dateHeure = dateHeure;
  }


  public Paiement paiementEnLigneEnCours(PaiementEnLigneEnCoursEnum paiementEnLigneEnCours) {
    this.paiementEnLigneEnCours = paiementEnLigneEnCours;
    return this;
  }

  /**
   * Get paiementEnLigneEnCours
   * @return paiementEnLigneEnCours
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_PAIEMENT_EN_LIGNE_EN_COURS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public PaiementEnLigneEnCoursEnum getPaiementEnLigneEnCours() {
    return paiementEnLigneEnCours;
  }


  @JsonProperty(JSON_PROPERTY_PAIEMENT_EN_LIGNE_EN_COURS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setPaiementEnLigneEnCours(PaiementEnLigneEnCoursEnum paiementEnLigneEnCours) {
    this.paiementEnLigneEnCours = paiementEnLigneEnCours;
  }


  /**
   * Return true if this Paiement object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Paiement paiement = (Paiement) o;
    return Objects.equals(this.mode, paiement.mode) &&
        Objects.equals(this.reference, paiement.reference) &&
        Objects.equals(this.montant, paiement.montant) &&
        Objects.equals(this.montantPaye, paiement.montantPaye) &&
        Objects.equals(this.dateHeure, paiement.dateHeure) &&
        Objects.equals(this.paiementEnLigneEnCours, paiement.paiementEnLigneEnCours);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mode, reference, montant, montantPaye, dateHeure, paiementEnLigneEnCours);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Paiement {\n");
    sb.append("    mode: ").append(toIndentedString(mode)).append("\n");
    sb.append("    reference: ").append(toIndentedString(reference)).append("\n");
    sb.append("    montant: ").append(toIndentedString(montant)).append("\n");
    sb.append("    montantPaye: ").append(toIndentedString(montantPaye)).append("\n");
    sb.append("    dateHeure: ").append(toIndentedString(dateHeure)).append("\n");
    sb.append("    paiementEnLigneEnCours: ").append(toIndentedString(paiementEnLigneEnCours)).append("\n");
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

    // add `mode` to the URL query string
    if (getMode() != null) {
      joiner.add(String.format("%smode%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getMode()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `reference` to the URL query string
    if (getReference() != null) {
      joiner.add(String.format("%sreference%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getReference()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `montant` to the URL query string
    if (getMontant() != null) {
      joiner.add(String.format("%smontant%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getMontant()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `montantPaye` to the URL query string
    if (getMontantPaye() != null) {
      joiner.add(String.format("%smontantPaye%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getMontantPaye()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `dateHeure` to the URL query string
    if (getDateHeure() != null) {
      joiner.add(String.format("%sdateHeure%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getDateHeure()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `paiementEnLigneEnCours` to the URL query string
    if (getPaiementEnLigneEnCours() != null) {
      joiner.add(String.format("%spaiementEnLigneEnCours%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getPaiementEnLigneEnCours()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    return joiner.toString();
  }
}
