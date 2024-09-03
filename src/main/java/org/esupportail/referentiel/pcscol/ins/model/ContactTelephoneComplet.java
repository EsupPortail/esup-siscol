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
 * ContactTelephoneComplet
 */
@JsonPropertyOrder({
  ContactTelephoneComplet.JSON_PROPERTY_TELEPHONE
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-23T10:43:17.979817+02:00[Europe/Paris]", comments = "Generator version: 7.7.0")
@JsonIgnoreProperties(
  value = "canalCommunication", // ignore manually set canalCommunication, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the canalCommunication to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "canalCommunication", visible = true)

public class ContactTelephoneComplet extends ContactComplet {
  public static final String JSON_PROPERTY_TELEPHONE = "telephone";
  private String telephone;

  public ContactTelephoneComplet() { 
  }

  public ContactTelephoneComplet telephone(String telephone) {
    this.telephone = telephone;
    return this;
  }

  /**
   * Numéro de téléphone
   * @return telephone
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_TELEPHONE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public String getTelephone() {
    return telephone;
  }


  @JsonProperty(JSON_PROPERTY_TELEPHONE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }


  @Override
  public ContactTelephoneComplet canalCommunication(CanalCommunicationEnum canalCommunication) {
    this.setCanalCommunication(canalCommunication);
    return this;
  }

  @Override
  public ContactTelephoneComplet demandeDeContact(DemandeDeContactSimple demandeDeContact) {
    this.setDemandeDeContact(demandeDeContact);
    return this;
  }

  @Override
  public ContactTelephoneComplet proprietaire(String proprietaire) {
    this.setProprietaire(proprietaire);
    return this;
  }

  /**
   * Return true if this ContactTelephoneComplet object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ContactTelephoneComplet contactTelephoneComplet = (ContactTelephoneComplet) o;
    return Objects.equals(this.telephone, contactTelephoneComplet.telephone) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(telephone, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ContactTelephoneComplet {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    telephone: ").append(toIndentedString(telephone)).append("\n");
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

    // add `canalCommunication` to the URL query string
    if (getCanalCommunication() != null) {
      joiner.add(String.format("%scanalCommunication%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCanalCommunication()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `demandeDeContact` to the URL query string
    if (getDemandeDeContact() != null) {
      joiner.add(getDemandeDeContact().toUrlQueryString(prefix + "demandeDeContact" + suffix));
    }

    // add `proprietaire` to the URL query string
    if (getProprietaire() != null) {
      joiner.add(String.format("%sproprietaire%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getProprietaire()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `telephone` to the URL query string
    if (getTelephone() != null) {
      joiner.add(String.format("%stelephone%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getTelephone()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    return joiner.toString();
  }
static {
  // Initialize and register the discriminator mappings.
  Map<String, Class<?>> mappings = new HashMap<String, Class<?>>();
  mappings.put("ContactTelephoneComplet", ContactTelephoneComplet.class);
  JSON.registerDiscriminator(ContactTelephoneComplet.class, "canalCommunication", mappings);
}
}

