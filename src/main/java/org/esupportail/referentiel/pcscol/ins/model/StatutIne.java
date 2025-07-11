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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Statut du numéro INE dans Admission  Valeurs actuellement possibles:   * CONFIRME: le numéro a été confirmé par INES  * A_VERIFIER: le numéro INE n&#39;a pas été vérifié par INES  * DONNEES_INCOMPLETES: Une donnée personnelle est manquante pour faire appel à INES  * DONNEES_INVALIDES: Si les données transmises pour vérifier l&#39;INE sont invalides  * ERR_VERIF: Erreur lors de la vérification INES  * LITIGE: le numéro INE est en cours de litige  * INCONNU: l&#39;admis est inconnu d&#39;INES  * ERR_IMAT: Erreur lors de l&#39;immatriculation INES 
 */
public enum StatutIne {
  
  CONFIRME("CONFIRME"),
  
  A_VERIFIER("A_VERIFIER"),
  
  DONNEES_INCOMPLETES("DONNEES_INCOMPLETES"),
  
  DONNEES_INVALIDES("DONNEES_INVALIDES"),
  
  LITIGE("LITIGE"),
  
  INCONNU("INCONNU"),
  
  ERR_IMAT("ERR_IMAT"),
  
  ERR_VERIF("ERR_VERIF");

  private String value;

  StatutIne(String value) {
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
  public static StatutIne fromValue(String value) {
    for (StatutIne b : StatutIne.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }

  /**
   * Convert the instance into URL query string.
   *
   * @param prefix prefix of the query string
   * @return URL query string
   */
  public String toUrlQueryString(String prefix) {
    if (prefix == null) {
      prefix = "";
    }

    return String.format("%s=%s", prefix, this.toString());
  }

}

