/*
 * STA Externe V1 -  Contrat Api pour mise à disposition pour les SI de gestion de stage (Esup-stage, ...)
 *  # Introduction Liste l'ensemble des services et des opérations disponibles dans le service stage  Le service stage a pour vocation de mettre à disposition l'ensemble des éléments nécessaires aux systèmes d'informations de gestion des stages  # Authentification/autorisation obligatoire Pour tout appel à une opération vous devez être authentifié/autorisé à l’aide d’un token jwt. Pour cela, chaque requête HTTP doit contenir un token valide dans le header HTTP Authorization.  # Notions métiers  ## Apprenant Tout étudiant en formation initiale (dont apprentis) ou stagiaire de la formation continue inscrit administrativement ou pédagogiquement sur une formation ou un objet de formation.  ## Inscription Objet maquette (Formation ou Objet de formation) sur lesquels est inscrit administrativement l'apprenant.  ## Stage Objet de formation taggué stage qui fait partie de la descendance de l'objet maquette sur lequel est inscrit l'apprenant  # Gestion des erreurs  ## Codes d'erreurs  <exemple-status-code>  | Code    | Description                                | |---------|--------------------------------------------| | 200     | Opération effectuée                        | | 201     | Ressource créée                            | | 400     | Données envoyées par le client invalides   | | 404     | Ressource inexistante                      | | 500     | Erreur technique rencontrée par le serveur |  <exemple-erreurs>  ## Contenu d'une erreur métier (statusCode: 400 ou 404)  Lorsqu'une erreur 400 ou 404 (ressource métier non existante) est générée, le corps HTTP de la réponse contient:  ``` {     \"correlationId\": \"1ace2ef3-b00a-49d1-a45e-6b10783c6038\",     \"timestamp\": \"2020-06-24T15:17:48.95941+02:00\",     \"version\": \"1.1.0\",     \"path\": \"/api/cof/v2-draft/etablissements/ETAB00/formations\",     \"statusCode\": \"400\",     \"errors\": [         {             \"code\": \"FormatDeCodeValide\",             \"message\": \"Le code \\\"BLA43????\\\" ne doit être constitué que de lettres majuscules (de A à Z), de chiffres (de 0 à 9) et de tirets (-).\",             \"champ\": \"formation.code\",             \"messageDetails\": {                 \"arguments\": {}             }         }     ] } ```  Avec:  * correlationId: Identifiant de corrélation * timestamp: Timestamp auquel est survenu l'erreur * version: version de l'application * path: URI du module COF sur laquelle est survenue l'erreur. * statusCode: code statut HTTP * errors: Une liste d'erreur (taille minimum = 1) avec: ** code: code de l'erreur ** message: message de l'erreur ** champ: propriété de la ressource sur lequel se rapporte l'erreur (vide dans le cas où il s'agit d'une erreur    globale à la ressource ou sur plusieurs champs et non spécifique à un seul champs) ** messageDetails.arguments: contient des détails sur l'erreur  ## Contenu de l'erreur technique  Lorsqu'une erreur est générée, le corps HTTP de la réponse contient:  ``` {     \"timestamp\": \"2020-06-08T11:15:42.831+0000\",     \"status\": 500,     \"error\": \"Internal Server Error\",     \"message\": \"Un message d'erreur intelligible\",     \"path\": \"/api/cof/v2-draft/etablissements/ETAB00/objets-maquette/candidats\" } ```  Avec:  * timestamp: Timestamp auquel est survenu l'erreur * status: Code statut HTTP * error: Libellé correspondant au code Statut (exemple: Internal Server Error) * message: Message d'erreur. * path: URI du module COF sur laquelle est survenue l'erreur. 
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.esupportail.referentiel.pcscol.model.sta;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * Période de mise en oeuvre
 */
@ApiModel(description = "Période de mise en oeuvre")
@JsonPropertyOrder({
  Periode.JSON_PROPERTY_CODE_PERIODE,
  Periode.JSON_PROPERTY_LIBELLE_AFFICHAGE,
  Periode.JSON_PROPERTY_ANNEE_UNIVERSITAIRE,
  Periode.JSON_PROPERTY_DATE_DEBUT,
  Periode.JSON_PROPERTY_DATE_FIN
})
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-02-15T13:35:24.917377+01:00[Europe/Paris]")
public class Periode {
  public static final String JSON_PROPERTY_CODE_PERIODE = "codePeriode";
  private String codePeriode;

  public static final String JSON_PROPERTY_LIBELLE_AFFICHAGE = "libelleAffichage";
  private String libelleAffichage;

  public static final String JSON_PROPERTY_ANNEE_UNIVERSITAIRE = "anneeUniversitaire";
  private Integer anneeUniversitaire;

  public static final String JSON_PROPERTY_DATE_DEBUT = "dateDebut";
  private Date dateDebut;

  public static final String JSON_PROPERTY_DATE_FIN = "dateFin";
  private Date dateFin;

  public Periode() { 
  }

  public Periode codePeriode(String codePeriode) {
    this.codePeriode = codePeriode;
    return this;
  }

   /**
   * Le code de la période de mise en oeuvre
   * @return codePeriode
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code de la période de mise en oeuvre")
  @JsonProperty(JSON_PROPERTY_CODE_PERIODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCodePeriode() {
    return codePeriode;
  }


  @JsonProperty(JSON_PROPERTY_CODE_PERIODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCodePeriode(String codePeriode) {
    this.codePeriode = codePeriode;
  }


  public Periode libelleAffichage(String libelleAffichage) {
    this.libelleAffichage = libelleAffichage;
    return this;
  }

   /**
   * Le libellé d&#39;affichage de la période
   * @return libelleAffichage
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le libellé d'affichage de la période")
  @JsonProperty(JSON_PROPERTY_LIBELLE_AFFICHAGE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getLibelleAffichage() {
    return libelleAffichage;
  }


  @JsonProperty(JSON_PROPERTY_LIBELLE_AFFICHAGE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setLibelleAffichage(String libelleAffichage) {
    this.libelleAffichage = libelleAffichage;
  }


  public Periode anneeUniversitaire(Integer anneeUniversitaire) {
    this.anneeUniversitaire = anneeUniversitaire;
    return this;
  }

   /**
   * Année universitaire
   * @return anneeUniversitaire
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Année universitaire")
  @JsonProperty(JSON_PROPERTY_ANNEE_UNIVERSITAIRE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getAnneeUniversitaire() {
    return anneeUniversitaire;
  }


  @JsonProperty(JSON_PROPERTY_ANNEE_UNIVERSITAIRE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setAnneeUniversitaire(Integer anneeUniversitaire) {
    this.anneeUniversitaire = anneeUniversitaire;
  }


  public Periode dateDebut(Date dateDebut) {
    this.dateDebut = dateDebut;
    return this;
  }

   /**
   * La date de début de la période
   * @return dateDebut
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "La date de début de la période")
  @JsonProperty(JSON_PROPERTY_DATE_DEBUT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Date getDateDebut() {
    return dateDebut;
  }


  @JsonProperty(JSON_PROPERTY_DATE_DEBUT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setDateDebut(Date dateDebut) {
    this.dateDebut = dateDebut;
  }


  public Periode dateFin(Date dateFin) {
    this.dateFin = dateFin;
    return this;
  }

   /**
   * La date de fin de la période
   * @return dateFin
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "La date de fin de la période")
  @JsonProperty(JSON_PROPERTY_DATE_FIN)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Date getDateFin() {
    return dateFin;
  }


  @JsonProperty(JSON_PROPERTY_DATE_FIN)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setDateFin(Date dateFin) {
    this.dateFin = dateFin;
  }


  /**
   * Return true if this Periode object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Periode periode = (Periode) o;
    return Objects.equals(this.codePeriode, periode.codePeriode) &&
        Objects.equals(this.libelleAffichage, periode.libelleAffichage) &&
        Objects.equals(this.anneeUniversitaire, periode.anneeUniversitaire) &&
        Objects.equals(this.dateDebut, periode.dateDebut) &&
        Objects.equals(this.dateFin, periode.dateFin);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codePeriode, libelleAffichage, anneeUniversitaire, dateDebut, dateFin);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Periode {\n");
    sb.append("    codePeriode: ").append(toIndentedString(codePeriode)).append("\n");
    sb.append("    libelleAffichage: ").append(toIndentedString(libelleAffichage)).append("\n");
    sb.append("    anneeUniversitaire: ").append(toIndentedString(anneeUniversitaire)).append("\n");
    sb.append("    dateDebut: ").append(toIndentedString(dateDebut)).append("\n");
    sb.append("    dateFin: ").append(toIndentedString(dateFin)).append("\n");
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

}
