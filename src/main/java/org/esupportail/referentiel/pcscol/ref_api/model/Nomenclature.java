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
import java.util.Date;
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
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
/**
 * Nomenclature
 */
@JsonPropertyOrder({
  Nomenclature.JSON_PROPERTY_TYPE_NOMENCLATURE,
  Nomenclature.JSON_PROPERTY_CODE,
  Nomenclature.JSON_PROPERTY_LIBELLE_COURT,
  Nomenclature.JSON_PROPERTY_LIBELLE_LONG,
  Nomenclature.JSON_PROPERTY_LIBELLE_AFFICHAGE,
  Nomenclature.JSON_PROPERTY_PRIORITE_AFFICHAGE,
  Nomenclature.JSON_PROPERTY_DATE_DEBUT_VALIDITE,
  Nomenclature.JSON_PROPERTY_DATE_FIN_VALIDITE,
  Nomenclature.JSON_PROPERTY_TEMOIN_VISIBLE,
  Nomenclature.JSON_PROPERTY_TEMOIN_LIVRE
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-30T09:24:02.851634+02:00[Europe/Paris]", comments = "Generator version: 7.7.0")
@JsonIgnoreProperties(
  value = "typeNomenclature", // ignore manually set typeNomenclature, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the typeNomenclature to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "typeNomenclature", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = BourseAideFinanciere.class, name = "BourseAideFinanciere"),
  @JsonSubTypes.Type(value = CanalCommunication.class, name = "CanalCommunication"),
  @JsonSubTypes.Type(value = CategorieJuridique.class, name = "CategorieJuridique"),
  @JsonSubTypes.Type(value = CategorieSocioprofessionnelle.class, name = "CategorieSocioprofessionnelle"),
  @JsonSubTypes.Type(value = CategorieSocioprofessionnelle8.class, name = "CategorieSocioprofessionnelle8"),
  @JsonSubTypes.Type(value = ChampsFormation.class, name = "ChampsFormation"),
  @JsonSubTypes.Type(value = Commune.class, name = "Commune"),
  @JsonSubTypes.Type(value = CommuneNaissance.class, name = "CommuneNaissance"),
  @JsonSubTypes.Type(value = ConcoursAdmission.class, name = "ConcoursAdmission"),
  @JsonSubTypes.Type(value = CursusFormation.class, name = "CursusFormation"),
  @JsonSubTypes.Type(value = CursusParallele.class, name = "CursusParallele"),
  @JsonSubTypes.Type(value = Departement.class, name = "Departement"),
  @JsonSubTypes.Type(value = DiplomeSise.class, name = "DiplomeSise"),
  @JsonSubTypes.Type(value = DomaineFormation.class, name = "DomaineFormation"),
  @JsonSubTypes.Type(value = EcoleDoctorale.class, name = "EcoleDoctorale"),
  @JsonSubTypes.Type(value = EtablissementEtranger.class, name = "EtablissementEtranger"),
  @JsonSubTypes.Type(value = EtablissementFrancais.class, name = "EtablissementFrancais"),
  @JsonSubTypes.Type(value = FinaliteFormation.class, name = "FinaliteFormation"),
  @JsonSubTypes.Type(value = GradePointAverage.class, name = "GradePointAverage"),
  @JsonSubTypes.Type(value = MentionBac.class, name = "MentionBac"),
  @JsonSubTypes.Type(value = MentionDiplome.class, name = "MentionDiplome"),
  @JsonSubTypes.Type(value = MentionHonorifique.class, name = "MentionHonorifique"),
  @JsonSubTypes.Type(value = ModaliteEnseignement.class, name = "ModaliteEnseignement"),
  @JsonSubTypes.Type(value = NatureDiplome.class, name = "NatureDiplome"),
  @JsonSubTypes.Type(value = NatureGroupement.class, name = "NatureGroupement"),
  @JsonSubTypes.Type(value = NatureTypeObjetFormation.class, name = "NatureTypeObjetFormation"),
  @JsonSubTypes.Type(value = NiveauDiplome.class, name = "NiveauDiplome"),
  @JsonSubTypes.Type(value = NiveauFormation.class, name = "NiveauFormation"),
  @JsonSubTypes.Type(value = NotationEcts.class, name = "NotationEcts"),
  @JsonSubTypes.Type(value = ParcoursTypeSise.class, name = "ParcoursTypeSise"),
  @JsonSubTypes.Type(value = PaysNationalite.class, name = "PaysNationalite"),
  @JsonSubTypes.Type(value = PieceFournir.class, name = "PieceFournir"),
  @JsonSubTypes.Type(value = ProfilExonerant.class, name = "ProfilExonerant"),
  @JsonSubTypes.Type(value = ProgrammeEchange.class, name = "ProgrammeEchange"),
  @JsonSubTypes.Type(value = QuotiteActivite.class, name = "QuotiteActivite"),
  @JsonSubTypes.Type(value = RegimeInscription.class, name = "RegimeInscription"),
  @JsonSubTypes.Type(value = RegimeSpecialEtudes.class, name = "RegimeSpecialEtudes"),
  @JsonSubTypes.Type(value = SerieBac.class, name = "SerieBac"),
  @JsonSubTypes.Type(value = SituationAnneePrecedente.class, name = "SituationAnneePrecedente"),
  @JsonSubTypes.Type(value = SituationEmploi.class, name = "SituationEmploi"),
  @JsonSubTypes.Type(value = SituationFamiliale.class, name = "SituationFamiliale"),
  @JsonSubTypes.Type(value = SituationMilitaire.class, name = "SituationMilitaire"),
  @JsonSubTypes.Type(value = SpecialitesBacGeneral.class, name = "SpecialitesBacGeneral"),
  @JsonSubTypes.Type(value = SpecificiteUai.class, name = "SpecificiteUai"),
  @JsonSubTypes.Type(value = TitreAcces.class, name = "TitreAcces"),
  @JsonSubTypes.Type(value = TypeAmenagement.class, name = "TypeAmenagement"),
  @JsonSubTypes.Type(value = TypeBac.class, name = "TypeBac"),
  @JsonSubTypes.Type(value = TypeClassePreparatoire.class, name = "TypeClassePreparatoire"),
  @JsonSubTypes.Type(value = TypeControle.class, name = "TypeControle"),
  @JsonSubTypes.Type(value = TypeDernierDiplomeObtenu.class, name = "TypeDernierDiplomeObtenu"),
  @JsonSubTypes.Type(value = TypeDiplome.class, name = "TypeDiplome"),
  @JsonSubTypes.Type(value = TypeFormation.class, name = "TypeFormation"),
  @JsonSubTypes.Type(value = TypeGroupe.class, name = "TypeGroupe"),
  @JsonSubTypes.Type(value = TypeHeure.class, name = "TypeHeure"),
  @JsonSubTypes.Type(value = TypeNotation.class, name = "TypeNotation"),
  @JsonSubTypes.Type(value = TypeObjetFormation.class, name = "TypeObjetFormation"),
  @JsonSubTypes.Type(value = TypeResultat.class, name = "TypeResultat"),
  @JsonSubTypes.Type(value = TypeUai.class, name = "TypeUai"),
})

public class Nomenclature {
  public static final String JSON_PROPERTY_TYPE_NOMENCLATURE = "typeNomenclature";
  private String typeNomenclature;

  public static final String JSON_PROPERTY_CODE = "code";
  private String code;

  public static final String JSON_PROPERTY_LIBELLE_COURT = "libelleCourt";
  private String libelleCourt;

  public static final String JSON_PROPERTY_LIBELLE_LONG = "libelleLong";
  private String libelleLong;

  public static final String JSON_PROPERTY_LIBELLE_AFFICHAGE = "libelleAffichage";
  private String libelleAffichage;

  public static final String JSON_PROPERTY_PRIORITE_AFFICHAGE = "prioriteAffichage";
  private Integer prioriteAffichage;

  public static final String JSON_PROPERTY_DATE_DEBUT_VALIDITE = "dateDebutValidite";
  private Date dateDebutValidite;

  public static final String JSON_PROPERTY_DATE_FIN_VALIDITE = "dateFinValidite";
  private Date dateFinValidite;

  public static final String JSON_PROPERTY_TEMOIN_VISIBLE = "temoinVisible";
  private Boolean temoinVisible;

  public static final String JSON_PROPERTY_TEMOIN_LIVRE = "temoinLivre";
  private Boolean temoinLivre;

  public Nomenclature() { 
  }

  public Nomenclature typeNomenclature(String typeNomenclature) {
    this.typeNomenclature = typeNomenclature;
    return this;
  }

  /**
   * Discriminant
   * @return typeNomenclature
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_TYPE_NOMENCLATURE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getTypeNomenclature() {
    return typeNomenclature;
  }


  @JsonProperty(JSON_PROPERTY_TYPE_NOMENCLATURE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setTypeNomenclature(String typeNomenclature) {
    this.typeNomenclature = typeNomenclature;
  }


  public Nomenclature code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Code interne de la nomenclature choisi par l&#39;établissement
   * @return code
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getCode() {
    return code;
  }


  @JsonProperty(JSON_PROPERTY_CODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCode(String code) {
    this.code = code;
  }


  public Nomenclature libelleCourt(String libelleCourt) {
    this.libelleCourt = libelleCourt;
    return this;
  }

  /**
   * Libelle court de la nomenclature
   * @return libelleCourt
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_LIBELLE_COURT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getLibelleCourt() {
    return libelleCourt;
  }


  @JsonProperty(JSON_PROPERTY_LIBELLE_COURT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setLibelleCourt(String libelleCourt) {
    this.libelleCourt = libelleCourt;
  }


  public Nomenclature libelleLong(String libelleLong) {
    this.libelleLong = libelleLong;
    return this;
  }

  /**
   * Nom complet de la nomenclature
   * @return libelleLong
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_LIBELLE_LONG)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getLibelleLong() {
    return libelleLong;
  }


  @JsonProperty(JSON_PROPERTY_LIBELLE_LONG)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setLibelleLong(String libelleLong) {
    this.libelleLong = libelleLong;
  }


  public Nomenclature libelleAffichage(String libelleAffichage) {
    this.libelleAffichage = libelleAffichage;
    return this;
  }

  /**
   * Libellé qui sera affiché aux usagers pour les fonctionnalités à distance
   * @return libelleAffichage
   */
  @javax.annotation.Nullable
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


  public Nomenclature prioriteAffichage(Integer prioriteAffichage) {
    this.prioriteAffichage = prioriteAffichage;
    return this;
  }

  /**
   * Plus le nombre est élevé plus il apparaît en priorité dans la liste de valeurs
   * @return prioriteAffichage
   */
  @javax.annotation.Nullable
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


  public Nomenclature dateDebutValidite(Date dateDebutValidite) {
    this.dateDebutValidite = dateDebutValidite;
    return this;
  }

  /**
   * Date du début de la validité de la nomenclature
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


  public Nomenclature dateFinValidite(Date dateFinValidite) {
    this.dateFinValidite = dateFinValidite;
    return this;
  }

  /**
   * Date de la fin de la validité de la nomenclature
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


  public Nomenclature temoinVisible(Boolean temoinVisible) {
    this.temoinVisible = temoinVisible;
    return this;
  }

  /**
   * Témoin de la visibilité de la nomenclature
   * @return temoinVisible
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_TEMOIN_VISIBLE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Boolean getTemoinVisible() {
    return temoinVisible;
  }


  @JsonProperty(JSON_PROPERTY_TEMOIN_VISIBLE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setTemoinVisible(Boolean temoinVisible) {
    this.temoinVisible = temoinVisible;
  }


  public Nomenclature temoinLivre(Boolean temoinLivre) {
    this.temoinLivre = temoinLivre;
    return this;
  }

  /**
   * Témoin indiquant si la nomenclature a été livrée
   * @return temoinLivre
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_TEMOIN_LIVRE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Boolean getTemoinLivre() {
    return temoinLivre;
  }


  @JsonProperty(JSON_PROPERTY_TEMOIN_LIVRE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setTemoinLivre(Boolean temoinLivre) {
    this.temoinLivre = temoinLivre;
  }


  /**
   * Return true if this Nomenclature object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Nomenclature nomenclature = (Nomenclature) o;
    return Objects.equals(this.typeNomenclature, nomenclature.typeNomenclature) &&
        Objects.equals(this.code, nomenclature.code) &&
        Objects.equals(this.libelleCourt, nomenclature.libelleCourt) &&
        Objects.equals(this.libelleLong, nomenclature.libelleLong) &&
        Objects.equals(this.libelleAffichage, nomenclature.libelleAffichage) &&
        Objects.equals(this.prioriteAffichage, nomenclature.prioriteAffichage) &&
        Objects.equals(this.dateDebutValidite, nomenclature.dateDebutValidite) &&
        Objects.equals(this.dateFinValidite, nomenclature.dateFinValidite) &&
        Objects.equals(this.temoinVisible, nomenclature.temoinVisible) &&
        Objects.equals(this.temoinLivre, nomenclature.temoinLivre);
  }

  @Override
  public int hashCode() {
    return Objects.hash(typeNomenclature, code, libelleCourt, libelleLong, libelleAffichage, prioriteAffichage, dateDebutValidite, dateFinValidite, temoinVisible, temoinLivre);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Nomenclature {\n");
    sb.append("    typeNomenclature: ").append(toIndentedString(typeNomenclature)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    libelleCourt: ").append(toIndentedString(libelleCourt)).append("\n");
    sb.append("    libelleLong: ").append(toIndentedString(libelleLong)).append("\n");
    sb.append("    libelleAffichage: ").append(toIndentedString(libelleAffichage)).append("\n");
    sb.append("    prioriteAffichage: ").append(toIndentedString(prioriteAffichage)).append("\n");
    sb.append("    dateDebutValidite: ").append(toIndentedString(dateDebutValidite)).append("\n");
    sb.append("    dateFinValidite: ").append(toIndentedString(dateFinValidite)).append("\n");
    sb.append("    temoinVisible: ").append(toIndentedString(temoinVisible)).append("\n");
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

    // add `typeNomenclature` to the URL query string
    if (getTypeNomenclature() != null) {
      joiner.add(String.format("%stypeNomenclature%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getTypeNomenclature()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `code` to the URL query string
    if (getCode() != null) {
      joiner.add(String.format("%scode%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getCode()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `libelleCourt` to the URL query string
    if (getLibelleCourt() != null) {
      joiner.add(String.format("%slibelleCourt%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getLibelleCourt()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `libelleLong` to the URL query string
    if (getLibelleLong() != null) {
      joiner.add(String.format("%slibelleLong%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getLibelleLong()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `libelleAffichage` to the URL query string
    if (getLibelleAffichage() != null) {
      joiner.add(String.format("%slibelleAffichage%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getLibelleAffichage()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `prioriteAffichage` to the URL query string
    if (getPrioriteAffichage() != null) {
      joiner.add(String.format("%sprioriteAffichage%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getPrioriteAffichage()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `dateDebutValidite` to the URL query string
    if (getDateDebutValidite() != null) {
      joiner.add(String.format("%sdateDebutValidite%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getDateDebutValidite()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `dateFinValidite` to the URL query string
    if (getDateFinValidite() != null) {
      joiner.add(String.format("%sdateFinValidite%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getDateFinValidite()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `temoinVisible` to the URL query string
    if (getTemoinVisible() != null) {
      joiner.add(String.format("%stemoinVisible%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getTemoinVisible()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    // add `temoinLivre` to the URL query string
    if (getTemoinLivre() != null) {
      joiner.add(String.format("%stemoinLivre%s=%s", prefix, suffix, URLEncoder.encode(ApiClient.valueToString(getTemoinLivre()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
    }

    return joiner.toString();
  }
static {
  // Initialize and register the discriminator mappings.
  Map<String, Class<?>> mappings = new HashMap<String, Class<?>>();
  mappings.put("BourseAideFinanciere", BourseAideFinanciere.class);
  mappings.put("CanalCommunication", CanalCommunication.class);
  mappings.put("CategorieJuridique", CategorieJuridique.class);
  mappings.put("CategorieSocioprofessionnelle", CategorieSocioprofessionnelle.class);
  mappings.put("CategorieSocioprofessionnelle8", CategorieSocioprofessionnelle8.class);
  mappings.put("ChampsFormation", ChampsFormation.class);
  mappings.put("Commune", Commune.class);
  mappings.put("CommuneNaissance", CommuneNaissance.class);
  mappings.put("ConcoursAdmission", ConcoursAdmission.class);
  mappings.put("CursusFormation", CursusFormation.class);
  mappings.put("CursusParallele", CursusParallele.class);
  mappings.put("Departement", Departement.class);
  mappings.put("DiplomeSise", DiplomeSise.class);
  mappings.put("DomaineFormation", DomaineFormation.class);
  mappings.put("EcoleDoctorale", EcoleDoctorale.class);
  mappings.put("EtablissementEtranger", EtablissementEtranger.class);
  mappings.put("EtablissementFrancais", EtablissementFrancais.class);
  mappings.put("FinaliteFormation", FinaliteFormation.class);
  mappings.put("GradePointAverage", GradePointAverage.class);
  mappings.put("MentionBac", MentionBac.class);
  mappings.put("MentionDiplome", MentionDiplome.class);
  mappings.put("MentionHonorifique", MentionHonorifique.class);
  mappings.put("ModaliteEnseignement", ModaliteEnseignement.class);
  mappings.put("NatureDiplome", NatureDiplome.class);
  mappings.put("NatureGroupement", NatureGroupement.class);
  mappings.put("NatureTypeObjetFormation", NatureTypeObjetFormation.class);
  mappings.put("NiveauDiplome", NiveauDiplome.class);
  mappings.put("NiveauFormation", NiveauFormation.class);
  mappings.put("NotationEcts", NotationEcts.class);
  mappings.put("ParcoursTypeSise", ParcoursTypeSise.class);
  mappings.put("PaysNationalite", PaysNationalite.class);
  mappings.put("PieceFournir", PieceFournir.class);
  mappings.put("ProfilExonerant", ProfilExonerant.class);
  mappings.put("ProgrammeEchange", ProgrammeEchange.class);
  mappings.put("QuotiteActivite", QuotiteActivite.class);
  mappings.put("RegimeInscription", RegimeInscription.class);
  mappings.put("RegimeSpecialEtudes", RegimeSpecialEtudes.class);
  mappings.put("SerieBac", SerieBac.class);
  mappings.put("SituationAnneePrecedente", SituationAnneePrecedente.class);
  mappings.put("SituationEmploi", SituationEmploi.class);
  mappings.put("SituationFamiliale", SituationFamiliale.class);
  mappings.put("SituationMilitaire", SituationMilitaire.class);
  mappings.put("SpecialitesBacGeneral", SpecialitesBacGeneral.class);
  mappings.put("SpecificiteUai", SpecificiteUai.class);
  mappings.put("TitreAcces", TitreAcces.class);
  mappings.put("TypeAmenagement", TypeAmenagement.class);
  mappings.put("TypeBac", TypeBac.class);
  mappings.put("TypeClassePreparatoire", TypeClassePreparatoire.class);
  mappings.put("TypeControle", TypeControle.class);
  mappings.put("TypeDernierDiplomeObtenu", TypeDernierDiplomeObtenu.class);
  mappings.put("TypeDiplome", TypeDiplome.class);
  mappings.put("TypeFormation", TypeFormation.class);
  mappings.put("TypeGroupe", TypeGroupe.class);
  mappings.put("TypeHeure", TypeHeure.class);
  mappings.put("TypeNotation", TypeNotation.class);
  mappings.put("TypeObjetFormation", TypeObjetFormation.class);
  mappings.put("TypeResultat", TypeResultat.class);
  mappings.put("TypeUai", TypeUai.class);
  mappings.put("Nomenclature", Nomenclature.class);
  JSON.registerDiscriminator(Nomenclature.class, "typeNomenclature", mappings);
}
}
