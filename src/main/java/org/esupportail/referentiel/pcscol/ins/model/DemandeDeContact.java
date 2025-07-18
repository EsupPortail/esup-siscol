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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

import org.esupportail.referentiel.pcscol.invoker.ApiClient;
import org.esupportail.referentiel.pcscol.invoker.JSON;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
/**
 * DemandeDeContact
 */
@JsonPropertyOrder({
  DemandeDeContact.JSON_PROPERTY_DATE_DEBUT_VALIDITE,
  DemandeDeContact.JSON_PROPERTY_DATE_FIN_VALIDITE,
  DemandeDeContact.JSON_PROPERTY_PRIORITE_AFFICHAGE,
  DemandeDeContact.JSON_PROPERTY_TEMOIN_LIVRE
})
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-03-24T10:28:25.069148496+01:00[Europe/Paris]", comments = "Generator version: 7.12.0")
@JsonIgnoreProperties(
  value = "est1", // ignore manually set est1, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the est1 to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "est1", visible = true)

public class DemandeDeContact extends DemandeDeContactSimple {
  public static final String JSON_PROPERTY_DATE_DEBUT_VALIDITE = "dateDebutValidite";
  @jakarta.annotation.Nonnull
  private String dateDebutValidite;

  public static final String JSON_PROPERTY_DATE_FIN_VALIDITE = "dateFinValidite";
  @jakarta.annotation.Nullable
  private String dateFinValidite;

  public static final String JSON_PROPERTY_PRIORITE_AFFICHAGE = "prioriteAffichage";
  @jakarta.annotation.Nonnull
  private Integer prioriteAffichage;

  public static final String JSON_PROPERTY_TEMOIN_LIVRE = "temoinLivre";
  @jakarta.annotation.Nonnull
  private Boolean temoinLivre;

  public DemandeDeContact() { 
  }

  public DemandeDeContact dateDebutValidite(@jakarta.annotation.Nonnull String dateDebutValidite) {
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
  public void setDateDebutValidite(@jakarta.annotation.Nonnull String dateDebutValidite) {
    this.dateDebutValidite = dateDebutValidite;
  }


  public DemandeDeContact dateFinValidite(@jakarta.annotation.Nullable String dateFinValidite) {
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
  public void setDateFinValidite(@jakarta.annotation.Nullable String dateFinValidite) {
    this.dateFinValidite = dateFinValidite;
  }


  public DemandeDeContact prioriteAffichage(@jakarta.annotation.Nonnull Integer prioriteAffichage) {
    this.prioriteAffichage = prioriteAffichage;
    return this;
  }

  /**
   * La priorité d&#39;affichage
   * @return prioriteAffichage
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_PRIORITE_AFFICHAGE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public Integer getPrioriteAffichage() {
    return prioriteAffichage;
  }


  @JsonProperty(JSON_PROPERTY_PRIORITE_AFFICHAGE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setPrioriteAffichage(@jakarta.annotation.Nonnull Integer prioriteAffichage) {
    this.prioriteAffichage = prioriteAffichage;
  }


  public DemandeDeContact temoinLivre(@jakarta.annotation.Nonnull Boolean temoinLivre) {
    this.temoinLivre = temoinLivre;
    return this;
  }

  /**
   * Le témoin livré
   * @return temoinLivre
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_TEMOIN_LIVRE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public Boolean getTemoinLivre() {
    return temoinLivre;
  }


  @JsonProperty(JSON_PROPERTY_TEMOIN_LIVRE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setTemoinLivre(@jakarta.annotation.Nonnull Boolean temoinLivre) {
    this.temoinLivre = temoinLivre;
  }


  @Override
  public DemandeDeContact est1(@jakarta.annotation.Nonnull String est1) {
    this.setEst1(est1);
    return this;
  }

  @Override
  public DemandeDeContact code(@jakarta.annotation.Nonnull String code) {
    this.setCode(code);
    return this;
  }

  @Override
  public DemandeDeContact codeStructure(@jakarta.annotation.Nonnull String codeStructure) {
    this.setCodeStructure(codeStructure);
    return this;
  }

  @Override
  public DemandeDeContact libelleAffichage(@jakarta.annotation.Nonnull String libelleAffichage) {
    this.setLibelleAffichage(libelleAffichage);
    return this;
  }

  @Override
  public DemandeDeContact canalCommunication(@jakarta.annotation.Nonnull CanalCommunicationEnum canalCommunication) {
    this.setCanalCommunication(canalCommunication);
    return this;
  }

  @Override
  public DemandeDeContact temoinSaisie(@jakarta.annotation.Nonnull TemoinSaisie temoinSaisie) {
    this.setTemoinSaisie(temoinSaisie);
    return this;
  }

  @Override
  public DemandeDeContact temoinSaisieProprietaire(@jakarta.annotation.Nonnull TemoinSaisie temoinSaisieProprietaire) {
    this.setTemoinSaisieProprietaire(temoinSaisieProprietaire);
    return this;
  }

  /**
   * Return true if this DemandeDeContact object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DemandeDeContact demandeDeContact = (DemandeDeContact) o;
    return Objects.equals(this.dateDebutValidite, demandeDeContact.dateDebutValidite) &&
        Objects.equals(this.dateFinValidite, demandeDeContact.dateFinValidite) &&
        Objects.equals(this.prioriteAffichage, demandeDeContact.prioriteAffichage) &&
        Objects.equals(this.temoinLivre, demandeDeContact.temoinLivre) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dateDebutValidite, dateFinValidite, prioriteAffichage, temoinLivre, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DemandeDeContact {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    dateDebutValidite: ").append(toIndentedString(dateDebutValidite)).append("\n");
    sb.append("    dateFinValidite: ").append(toIndentedString(dateFinValidite)).append("\n");
    sb.append("    prioriteAffichage: ").append(toIndentedString(prioriteAffichage)).append("\n");
    sb.append("    temoinLivre: ").append(toIndentedString(temoinLivre)).append("\n");
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

    // add `est1` to the URL query string
    if (getEst1() != null) {
      joiner.add(String.format("%sest1%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getEst1()))));
    }

    // add `code` to the URL query string
    if (getCode() != null) {
      joiner.add(String.format("%scode%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getCode()))));
    }

    // add `codeStructure` to the URL query string
    if (getCodeStructure() != null) {
      joiner.add(String.format("%scodeStructure%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getCodeStructure()))));
    }

    // add `libelleAffichage` to the URL query string
    if (getLibelleAffichage() != null) {
      joiner.add(String.format("%slibelleAffichage%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getLibelleAffichage()))));
    }

    // add `canalCommunication` to the URL query string
    if (getCanalCommunication() != null) {
      joiner.add(String.format("%scanalCommunication%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getCanalCommunication()))));
    }

    // add `temoinSaisie` to the URL query string
    if (getTemoinSaisie() != null) {
      joiner.add(String.format("%stemoinSaisie%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getTemoinSaisie()))));
    }

    // add `temoinSaisieProprietaire` to the URL query string
    if (getTemoinSaisieProprietaire() != null) {
      joiner.add(String.format("%stemoinSaisieProprietaire%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getTemoinSaisieProprietaire()))));
    }

    // add `dateDebutValidite` to the URL query string
    if (getDateDebutValidite() != null) {
      joiner.add(String.format("%sdateDebutValidite%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getDateDebutValidite()))));
    }

    // add `dateFinValidite` to the URL query string
    if (getDateFinValidite() != null) {
      joiner.add(String.format("%sdateFinValidite%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getDateFinValidite()))));
    }

    // add `prioriteAffichage` to the URL query string
    if (getPrioriteAffichage() != null) {
      joiner.add(String.format("%sprioriteAffichage%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getPrioriteAffichage()))));
    }

    // add `temoinLivre` to the URL query string
    if (getTemoinLivre() != null) {
      joiner.add(String.format("%stemoinLivre%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getTemoinLivre()))));
    }

    return joiner.toString();
  }
static {
  // Initialize and register the discriminator mappings.
  Map<String, Class<?>> mappings = new HashMap<String, Class<?>>();
  mappings.put("DemandeDeContact", DemandeDeContact.class);
  JSON.registerDiscriminator(DemandeDeContact.class, "est1", mappings);
}
}

