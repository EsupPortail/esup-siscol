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
 * ModaliteDePaiement
 */
@JsonPropertyOrder({
  ModaliteDePaiement.JSON_PROPERTY_CODE,
  ModaliteDePaiement.JSON_PROPERTY_CODE_STRUCTURE,
  ModaliteDePaiement.JSON_PROPERTY_LIBELLE_AFFICHAGE,
  ModaliteDePaiement.JSON_PROPERTY_CODE_MODE_DE_PAIEMENT,
  ModaliteDePaiement.JSON_PROPERTY_TEMOIN_VISIBLE_ETUDIANT,
  ModaliteDePaiement.JSON_PROPERTY_STATUT_PAIEMENT,
  ModaliteDePaiement.JSON_PROPERTY_TEMOIN_PRIMO,
  ModaliteDePaiement.JSON_PROPERTY_TEMOIN_REINS,
  ModaliteDePaiement.JSON_PROPERTY_NOMBRE_DE_PAIEMENTS,
  ModaliteDePaiement.JSON_PROPERTY_MONTANT_MINIMUM,
  ModaliteDePaiement.JSON_PROPERTY_MONTANT_MAXIMUM,
  ModaliteDePaiement.JSON_PROPERTY_TEMOIN_SAISIE_REFERENCE,
  ModaliteDePaiement.JSON_PROPERTY_TEMOIN_PAIEMENT_EN_LIGNE,
  ModaliteDePaiement.JSON_PROPERTY_DATE_DEBUT_VALIDITE,
  ModaliteDePaiement.JSON_PROPERTY_DATE_FIN_VALIDITE,
  ModaliteDePaiement.JSON_PROPERTY_PRIORITE_AFFICHAGE,
  ModaliteDePaiement.JSON_PROPERTY_NOTICE
})
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-23T10:43:17.979817+02:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class ModaliteDePaiement {
  public static final String JSON_PROPERTY_CODE = "code";
  private String code;

  public static final String JSON_PROPERTY_CODE_STRUCTURE = "codeStructure";
  private String codeStructure;

  public static final String JSON_PROPERTY_LIBELLE_AFFICHAGE = "libelleAffichage";
  private String libelleAffichage;

  public static final String JSON_PROPERTY_CODE_MODE_DE_PAIEMENT = "codeModeDePaiement";
  private String codeModeDePaiement;

  public static final String JSON_PROPERTY_TEMOIN_VISIBLE_ETUDIANT = "temoinVisibleEtudiant";
  private Boolean temoinVisibleEtudiant;

  /**
   * Statut du paiement généré à la confirmation du paiement
   */
  public enum StatutPaiementEnum {
    NON_PAYE("non_paye"),
    
    EN_ATTENTE("en_attente"),
    
    PAYE("paye"),
    
    VALIDE("valide");

    private String value;

    StatutPaiementEnum(String value) {
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
    public static StatutPaiementEnum fromValue(String value) {
      for (StatutPaiementEnum b : StatutPaiementEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_STATUT_PAIEMENT = "statutPaiement";
  private StatutPaiementEnum statutPaiement;

  public static final String JSON_PROPERTY_TEMOIN_PRIMO = "temoinPrimo";
  private Boolean temoinPrimo;

  public static final String JSON_PROPERTY_TEMOIN_REINS = "temoinReins";
  private Boolean temoinReins;

  public static final String JSON_PROPERTY_NOMBRE_DE_PAIEMENTS = "nombreDePaiements";
  private Integer nombreDePaiements = 1;

  public static final String JSON_PROPERTY_MONTANT_MINIMUM = "montantMinimum";
  private BigDecimal montantMinimum;

  public static final String JSON_PROPERTY_MONTANT_MAXIMUM = "montantMaximum";
  private BigDecimal montantMaximum;

  public static final String JSON_PROPERTY_TEMOIN_SAISIE_REFERENCE = "temoinSaisieReference";
  private Boolean temoinSaisieReference;

  public static final String JSON_PROPERTY_TEMOIN_PAIEMENT_EN_LIGNE = "temoinPaiementEnLigne";
  private Boolean temoinPaiementEnLigne;

  public static final String JSON_PROPERTY_DATE_DEBUT_VALIDITE = "dateDebutValidite";
  private String dateDebutValidite;

  public static final String JSON_PROPERTY_DATE_FIN_VALIDITE = "dateFinValidite";
  private String dateFinValidite;

  public static final String JSON_PROPERTY_PRIORITE_AFFICHAGE = "prioriteAffichage";
  private Integer prioriteAffichage = 0;

  public static final String JSON_PROPERTY_NOTICE = "notice";
  private String notice;

  public ModaliteDePaiement() { 
  }

  @JsonCreator
  public ModaliteDePaiement(
    @JsonProperty(JSON_PROPERTY_TEMOIN_PAIEMENT_EN_LIGNE) Boolean temoinPaiementEnLigne
  ) {
  this();
    this.temoinPaiementEnLigne = temoinPaiementEnLigne;
  }

  public ModaliteDePaiement code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Le code metier en saisie libre
   * @return code
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_CODE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public String getCode() {
    return code;
  }


  @JsonProperty(JSON_PROPERTY_CODE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setCode(String code) {
    this.code = code;
  }


  public ModaliteDePaiement codeStructure(String codeStructure) {
    this.codeStructure = codeStructure;
    return this;
  }

  /**
   * Le code interne de l&#39;établissement pour lequel la modalité de paiement sera créée
   * @return codeStructure
   */
  @jakarta.annotation.Nonnull
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


  public ModaliteDePaiement libelleAffichage(String libelleAffichage) {
    this.libelleAffichage = libelleAffichage;
    return this;
  }

  /**
   * Le libellé d&#39;affichage
   * @return libelleAffichage
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_LIBELLE_AFFICHAGE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public String getLibelleAffichage() {
    return libelleAffichage;
  }


  @JsonProperty(JSON_PROPERTY_LIBELLE_AFFICHAGE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setLibelleAffichage(String libelleAffichage) {
    this.libelleAffichage = libelleAffichage;
  }


  public ModaliteDePaiement codeModeDePaiement(String codeModeDePaiement) {
    this.codeModeDePaiement = codeModeDePaiement;
    return this;
  }

  /**
   * Code issu de la nomenclature mode de paiement du référentiel
   * @return codeModeDePaiement
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_CODE_MODE_DE_PAIEMENT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public String getCodeModeDePaiement() {
    return codeModeDePaiement;
  }


  @JsonProperty(JSON_PROPERTY_CODE_MODE_DE_PAIEMENT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setCodeModeDePaiement(String codeModeDePaiement) {
    this.codeModeDePaiement = codeModeDePaiement;
  }


  public ModaliteDePaiement temoinVisibleEtudiant(Boolean temoinVisibleEtudiant) {
    this.temoinVisibleEtudiant = temoinVisibleEtudiant;
    return this;
  }

  /**
   * Le témoin indiquant que cette modalité de paiement est proposée aux étudiants
   * @return temoinVisibleEtudiant
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_TEMOIN_VISIBLE_ETUDIANT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public Boolean getTemoinVisibleEtudiant() {
    return temoinVisibleEtudiant;
  }


  @JsonProperty(JSON_PROPERTY_TEMOIN_VISIBLE_ETUDIANT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setTemoinVisibleEtudiant(Boolean temoinVisibleEtudiant) {
    this.temoinVisibleEtudiant = temoinVisibleEtudiant;
  }


  public ModaliteDePaiement statutPaiement(StatutPaiementEnum statutPaiement) {
    this.statutPaiement = statutPaiement;
    return this;
  }

  /**
   * Statut du paiement généré à la confirmation du paiement
   * @return statutPaiement
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_STATUT_PAIEMENT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public StatutPaiementEnum getStatutPaiement() {
    return statutPaiement;
  }


  @JsonProperty(JSON_PROPERTY_STATUT_PAIEMENT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setStatutPaiement(StatutPaiementEnum statutPaiement) {
    this.statutPaiement = statutPaiement;
  }


  public ModaliteDePaiement temoinPrimo(Boolean temoinPrimo) {
    this.temoinPrimo = temoinPrimo;
    return this;
  }

  /**
   * Le témoin modalité de paiement proposée aux primo-entrants
   * @return temoinPrimo
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_TEMOIN_PRIMO)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public Boolean getTemoinPrimo() {
    return temoinPrimo;
  }


  @JsonProperty(JSON_PROPERTY_TEMOIN_PRIMO)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setTemoinPrimo(Boolean temoinPrimo) {
    this.temoinPrimo = temoinPrimo;
  }


  public ModaliteDePaiement temoinReins(Boolean temoinReins) {
    this.temoinReins = temoinReins;
    return this;
  }

  /**
   * Le témoin modalité de paiement proposée lors de la réinscription
   * @return temoinReins
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_TEMOIN_REINS)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public Boolean getTemoinReins() {
    return temoinReins;
  }


  @JsonProperty(JSON_PROPERTY_TEMOIN_REINS)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setTemoinReins(Boolean temoinReins) {
    this.temoinReins = temoinReins;
  }


  public ModaliteDePaiement nombreDePaiements(Integer nombreDePaiements) {
    this.nombreDePaiements = nombreDePaiements;
    return this;
  }

  /**
   * Le nombre de paiements
   * @return nombreDePaiements
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_NOMBRE_DE_PAIEMENTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Integer getNombreDePaiements() {
    return nombreDePaiements;
  }


  @JsonProperty(JSON_PROPERTY_NOMBRE_DE_PAIEMENTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setNombreDePaiements(Integer nombreDePaiements) {
    this.nombreDePaiements = nombreDePaiements;
  }


  public ModaliteDePaiement montantMinimum(BigDecimal montantMinimum) {
    this.montantMinimum = montantMinimum;
    return this;
  }

  /**
   * Le montant minimum pour proposer la modalité de paiement
   * @return montantMinimum
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_MONTANT_MINIMUM)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public BigDecimal getMontantMinimum() {
    return montantMinimum;
  }


  @JsonProperty(JSON_PROPERTY_MONTANT_MINIMUM)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setMontantMinimum(BigDecimal montantMinimum) {
    this.montantMinimum = montantMinimum;
  }


  public ModaliteDePaiement montantMaximum(BigDecimal montantMaximum) {
    this.montantMaximum = montantMaximum;
    return this;
  }

  /**
   * Le montant maximum pour proposer la modalité de paiement
   * @return montantMaximum
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_MONTANT_MAXIMUM)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public BigDecimal getMontantMaximum() {
    return montantMaximum;
  }


  @JsonProperty(JSON_PROPERTY_MONTANT_MAXIMUM)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setMontantMaximum(BigDecimal montantMaximum) {
    this.montantMaximum = montantMaximum;
  }


  public ModaliteDePaiement temoinSaisieReference(Boolean temoinSaisieReference) {
    this.temoinSaisieReference = temoinSaisieReference;
    return this;
  }

  /**
   * Le témoin de saisie d&#39;un motif ou d&#39;une référence
   * @return temoinSaisieReference
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_TEMOIN_SAISIE_REFERENCE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public Boolean getTemoinSaisieReference() {
    return temoinSaisieReference;
  }


  @JsonProperty(JSON_PROPERTY_TEMOIN_SAISIE_REFERENCE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setTemoinSaisieReference(Boolean temoinSaisieReference) {
    this.temoinSaisieReference = temoinSaisieReference;
  }


  /**
   * Le témoin de paiement en ligne − ne sera pas pris en compte à la création ou la modification
   * @return temoinPaiementEnLigne
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_TEMOIN_PAIEMENT_EN_LIGNE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Boolean getTemoinPaiementEnLigne() {
    return temoinPaiementEnLigne;
  }




  public ModaliteDePaiement dateDebutValidite(String dateDebutValidite) {
    this.dateDebutValidite = dateDebutValidite;
    return this;
  }

  /**
   * La date de début de validité
   * @return dateDebutValidite
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_DATE_DEBUT_VALIDITE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public String getDateDebutValidite() {
    return dateDebutValidite;
  }


  @JsonProperty(JSON_PROPERTY_DATE_DEBUT_VALIDITE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setDateDebutValidite(String dateDebutValidite) {
    this.dateDebutValidite = dateDebutValidite;
  }


  public ModaliteDePaiement dateFinValidite(String dateFinValidite) {
    this.dateFinValidite = dateFinValidite;
    return this;
  }

  /**
   * La date de fin de validité
   * @return dateFinValidite
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_DATE_FIN_VALIDITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getDateFinValidite() {
    return dateFinValidite;
  }


  @JsonProperty(JSON_PROPERTY_DATE_FIN_VALIDITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setDateFinValidite(String dateFinValidite) {
    this.dateFinValidite = dateFinValidite;
  }


  public ModaliteDePaiement prioriteAffichage(Integer prioriteAffichage) {
    this.prioriteAffichage = prioriteAffichage;
    return this;
  }

  /**
   * La priorité d&#39;affichage
   * @return prioriteAffichage
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_PRIORITE_AFFICHAGE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Integer getPrioriteAffichage() {
    return prioriteAffichage;
  }


  @JsonProperty(JSON_PROPERTY_PRIORITE_AFFICHAGE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setPrioriteAffichage(Integer prioriteAffichage) {
    this.prioriteAffichage = prioriteAffichage;
  }


  public ModaliteDePaiement notice(String notice) {
    this.notice = notice;
    return this;
  }

  /**
   * Notice de la modalité de paiement
   * @return notice
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_NOTICE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public String getNotice() {
    return notice;
  }


  @JsonProperty(JSON_PROPERTY_NOTICE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setNotice(String notice) {
    this.notice = notice;
  }


  /**
   * Return true if this ModaliteDePaiement object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModaliteDePaiement modaliteDePaiement = (ModaliteDePaiement) o;
    return Objects.equals(this.code, modaliteDePaiement.code) &&
        Objects.equals(this.codeStructure, modaliteDePaiement.codeStructure) &&
        Objects.equals(this.libelleAffichage, modaliteDePaiement.libelleAffichage) &&
        Objects.equals(this.codeModeDePaiement, modaliteDePaiement.codeModeDePaiement) &&
        Objects.equals(this.temoinVisibleEtudiant, modaliteDePaiement.temoinVisibleEtudiant) &&
        Objects.equals(this.statutPaiement, modaliteDePaiement.statutPaiement) &&
        Objects.equals(this.temoinPrimo, modaliteDePaiement.temoinPrimo) &&
        Objects.equals(this.temoinReins, modaliteDePaiement.temoinReins) &&
        Objects.equals(this.nombreDePaiements, modaliteDePaiement.nombreDePaiements) &&
        Objects.equals(this.montantMinimum, modaliteDePaiement.montantMinimum) &&
        Objects.equals(this.montantMaximum, modaliteDePaiement.montantMaximum) &&
        Objects.equals(this.temoinSaisieReference, modaliteDePaiement.temoinSaisieReference) &&
        Objects.equals(this.temoinPaiementEnLigne, modaliteDePaiement.temoinPaiementEnLigne) &&
        Objects.equals(this.dateDebutValidite, modaliteDePaiement.dateDebutValidite) &&
        Objects.equals(this.dateFinValidite, modaliteDePaiement.dateFinValidite) &&
        Objects.equals(this.prioriteAffichage, modaliteDePaiement.prioriteAffichage) &&
        Objects.equals(this.notice, modaliteDePaiement.notice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, codeStructure, libelleAffichage, codeModeDePaiement, temoinVisibleEtudiant, statutPaiement, temoinPrimo, temoinReins, nombreDePaiements, montantMinimum, montantMaximum, temoinSaisieReference, temoinPaiementEnLigne, dateDebutValidite, dateFinValidite, prioriteAffichage, notice);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModaliteDePaiement {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    codeStructure: ").append(toIndentedString(codeStructure)).append("\n");
    sb.append("    libelleAffichage: ").append(toIndentedString(libelleAffichage)).append("\n");
    sb.append("    codeModeDePaiement: ").append(toIndentedString(codeModeDePaiement)).append("\n");
    sb.append("    temoinVisibleEtudiant: ").append(toIndentedString(temoinVisibleEtudiant)).append("\n");
    sb.append("    statutPaiement: ").append(toIndentedString(statutPaiement)).append("\n");
    sb.append("    temoinPrimo: ").append(toIndentedString(temoinPrimo)).append("\n");
    sb.append("    temoinReins: ").append(toIndentedString(temoinReins)).append("\n");
    sb.append("    nombreDePaiements: ").append(toIndentedString(nombreDePaiements)).append("\n");
    sb.append("    montantMinimum: ").append(toIndentedString(montantMinimum)).append("\n");
    sb.append("    montantMaximum: ").append(toIndentedString(montantMaximum)).append("\n");
    sb.append("    temoinSaisieReference: ").append(toIndentedString(temoinSaisieReference)).append("\n");
    sb.append("    temoinPaiementEnLigne: ").append(toIndentedString(temoinPaiementEnLigne)).append("\n");
    sb.append("    dateDebutValidite: ").append(toIndentedString(dateDebutValidite)).append("\n");
    sb.append("    dateFinValidite: ").append(toIndentedString(dateFinValidite)).append("\n");
    sb.append("    prioriteAffichage: ").append(toIndentedString(prioriteAffichage)).append("\n");
    sb.append("    notice: ").append(toIndentedString(notice)).append("\n");
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

    // add `code` to the URL query string
    if (getCode() != null) {
      joiner.add(String.format("%scode%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCode()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `codeStructure` to the URL query string
    if (getCodeStructure() != null) {
      joiner.add(String.format("%scodeStructure%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCodeStructure()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `libelleAffichage` to the URL query string
    if (getLibelleAffichage() != null) {
      joiner.add(String.format("%slibelleAffichage%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getLibelleAffichage()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `codeModeDePaiement` to the URL query string
    if (getCodeModeDePaiement() != null) {
      joiner.add(String.format("%scodeModeDePaiement%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCodeModeDePaiement()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `temoinVisibleEtudiant` to the URL query string
    if (getTemoinVisibleEtudiant() != null) {
      joiner.add(String.format("%stemoinVisibleEtudiant%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getTemoinVisibleEtudiant()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `statutPaiement` to the URL query string
    if (getStatutPaiement() != null) {
      joiner.add(String.format("%sstatutPaiement%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getStatutPaiement()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `temoinPrimo` to the URL query string
    if (getTemoinPrimo() != null) {
      joiner.add(String.format("%stemoinPrimo%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getTemoinPrimo()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `temoinReins` to the URL query string
    if (getTemoinReins() != null) {
      joiner.add(String.format("%stemoinReins%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getTemoinReins()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `nombreDePaiements` to the URL query string
    if (getNombreDePaiements() != null) {
      joiner.add(String.format("%snombreDePaiements%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getNombreDePaiements()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `montantMinimum` to the URL query string
    if (getMontantMinimum() != null) {
      joiner.add(String.format("%smontantMinimum%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getMontantMinimum()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `montantMaximum` to the URL query string
    if (getMontantMaximum() != null) {
      joiner.add(String.format("%smontantMaximum%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getMontantMaximum()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `temoinSaisieReference` to the URL query string
    if (getTemoinSaisieReference() != null) {
      joiner.add(String.format("%stemoinSaisieReference%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getTemoinSaisieReference()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `temoinPaiementEnLigne` to the URL query string
    if (getTemoinPaiementEnLigne() != null) {
      joiner.add(String.format("%stemoinPaiementEnLigne%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getTemoinPaiementEnLigne()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `dateDebutValidite` to the URL query string
    if (getDateDebutValidite() != null) {
      joiner.add(String.format("%sdateDebutValidite%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getDateDebutValidite()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `dateFinValidite` to the URL query string
    if (getDateFinValidite() != null) {
      joiner.add(String.format("%sdateFinValidite%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getDateFinValidite()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `prioriteAffichage` to the URL query string
    if (getPrioriteAffichage() != null) {
      joiner.add(String.format("%sprioriteAffichage%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getPrioriteAffichage()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `notice` to the URL query string
    if (getNotice() != null) {
      joiner.add(String.format("%snotice%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getNotice()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    return joiner.toString();
  }
}

