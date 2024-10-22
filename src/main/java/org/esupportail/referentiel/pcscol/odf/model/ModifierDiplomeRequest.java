/*
 * OffreDeFormation V1
 *  # Introduction  Liste l'ensemble des services et des opérations disponibles dans le module OFFRE DE FORMATION  Description service ODF  # Gestion des erreurs  ## StatusCode  | Code    | Description                                | |---------|--------------------------------------------| | 200     | Opération effectuée                        | |         | Cas particulier: Dans le cas d'APIs de     | |         | type bulk, un 200 peut aussi être retourné | |         | si des données de la requête sont          | |         | considérées en erreur                      | | 201     | Ressource créée                            | | 400     | Données envoyées par le client invalides   | | 403     | Accès refusé                               | | 404     | Ressource inexistante                      | | 409     | donnée déjà existante                      | | 500     | Erreur technique rencontrée par le serveur |   ## Codes d'erreurs  | Code      | Description                                | |-----------|--------------------------------------------| | notNull   | la propriété est obligatoire               | | notBlank  | la propriété ne doit pas être vide         | | size      | la longueur de la propriété est invalide   | | pattern   | les caractères ou la syntaxe de            | |           | la propriété est invalide                  | | genre     | le genre de la personne est invalide       | | dateEntre | la date est invalide                       | | telephone | le téléphone est invalide                  | | email     | le mail est invalide                       |
 *
 * The version of the OpenAPI document: 1.0.0
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package org.esupportail.referentiel.pcscol.odf.model;


import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import org.esupportail.referentiel.pcscol.invoker.ApiClient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/** ModifierDiplomeRequest */
@JsonPropertyOrder({
    ModifierDiplomeRequest.JSON_PROPERTY_ID,
    ModifierDiplomeRequest.JSON_PROPERTY_PERIODES_VALIDITE_ID,
    ModifierDiplomeRequest.JSON_PROPERTY_FINALITE_DIPLOME,
    ModifierDiplomeRequest.JSON_PROPERTY_INTITULE,
    ModifierDiplomeRequest.JSON_PROPERTY_LIBELLE_COURT,
    ModifierDiplomeRequest.JSON_PROPERTY_LIBELLE_LONG,
    ModifierDiplomeRequest.JSON_PROPERTY_LIBELLE_AFFICHAGE,
    ModifierDiplomeRequest.JSON_PROPERTY_DIPLOME_INTERMEDIAIRE,
    ModifierDiplomeRequest.JSON_PROPERTY_ACTIF
})
@javax.annotation.Generated(
        value = "org.openapitools.codegen.languages.JavaClientCodegen",
        date = "2024-07-04T09:22:32.722688+02:00[Europe/Paris]",
        comments = "Generator version: 7.7.0")
public class ModifierDiplomeRequest {
    public static final String JSON_PROPERTY_ID = "id";
    private UUID id;

    public static final String JSON_PROPERTY_PERIODES_VALIDITE_ID = "periodesValiditeId";
    private List<UUID> periodesValiditeId = new ArrayList<>();

    public static final String JSON_PROPERTY_FINALITE_DIPLOME = "finaliteDiplome";
    private String finaliteDiplome;

    public static final String JSON_PROPERTY_INTITULE = "intitule";
    private String intitule;

    public static final String JSON_PROPERTY_LIBELLE_COURT = "libelleCourt";
    private String libelleCourt;

    public static final String JSON_PROPERTY_LIBELLE_LONG = "libelleLong";
    private String libelleLong;

    public static final String JSON_PROPERTY_LIBELLE_AFFICHAGE = "libelleAffichage";
    private String libelleAffichage;

    public static final String JSON_PROPERTY_DIPLOME_INTERMEDIAIRE = "diplomeIntermediaire";
    private Boolean diplomeIntermediaire;

    public static final String JSON_PROPERTY_ACTIF = "actif";
    private Boolean actif;

    public ModifierDiplomeRequest() {}

    public ModifierDiplomeRequest id(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Identifiant de ressource
     *
     * @return id
     */
    @javax.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_ID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public UUID getId() {
        return id;
    }

    @JsonProperty(JSON_PROPERTY_ID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setId(UUID id) {
        this.id = id;
    }

    public ModifierDiplomeRequest periodesValiditeId(List<UUID> periodesValiditeId) {
        this.periodesValiditeId = periodesValiditeId;
        return this;
    }

    public ModifierDiplomeRequest addPeriodesValiditeIdItem(UUID periodesValiditeIdItem) {
        if (this.periodesValiditeId == null) {
            this.periodesValiditeId = new ArrayList<>();
        }
        this.periodesValiditeId.add(periodesValiditeIdItem);
        return this;
    }

    /**
     * Get periodesValiditeId
     *
     * @return periodesValiditeId
     */
    @javax.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_PERIODES_VALIDITE_ID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<UUID> getPeriodesValiditeId() {
        return periodesValiditeId;
    }

    @JsonProperty(JSON_PROPERTY_PERIODES_VALIDITE_ID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setPeriodesValiditeId(List<UUID> periodesValiditeId) {
        this.periodesValiditeId = periodesValiditeId;
    }

    public ModifierDiplomeRequest finaliteDiplome(String finaliteDiplome) {
        this.finaliteDiplome = finaliteDiplome;
        return this;
    }

    /**
     * Finalité du diplôme Doit correspondre à la nomenclature FinaliteFormation
     *
     * @return finaliteDiplome
     */
    @javax.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_FINALITE_DIPLOME)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getFinaliteDiplome() {
        return finaliteDiplome;
    }

    @JsonProperty(JSON_PROPERTY_FINALITE_DIPLOME)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setFinaliteDiplome(String finaliteDiplome) {
        this.finaliteDiplome = finaliteDiplome;
    }

    public ModifierDiplomeRequest intitule(String intitule) {
        this.intitule = intitule;
        return this;
    }

    /**
     * Intitulé du diplôme
     *
     * @return intitule
     */
    @javax.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_INTITULE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getIntitule() {
        return intitule;
    }

    @JsonProperty(JSON_PROPERTY_INTITULE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public ModifierDiplomeRequest libelleCourt(String libelleCourt) {
        this.libelleCourt = libelleCourt;
        return this;
    }

    /**
     * Libellé court du diplôme
     *
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

    public ModifierDiplomeRequest libelleLong(String libelleLong) {
        this.libelleLong = libelleLong;
        return this;
    }

    /**
     * Libellé long du diplôme
     *
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

    public ModifierDiplomeRequest libelleAffichage(String libelleAffichage) {
        this.libelleAffichage = libelleAffichage;
        return this;
    }

    /**
     * Libellé d&#39;affichage du diplôme
     *
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

    public ModifierDiplomeRequest diplomeIntermediaire(Boolean diplomeIntermediaire) {
        this.diplomeIntermediaire = diplomeIntermediaire;
        return this;
    }

    /**
     * Témoin qui indique si le diplôme est un diplôme intermédiaire
     *
     * @return diplomeIntermediaire
     */
    @javax.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_DIPLOME_INTERMEDIAIRE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Boolean getDiplomeIntermediaire() {
        return diplomeIntermediaire;
    }

    @JsonProperty(JSON_PROPERTY_DIPLOME_INTERMEDIAIRE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setDiplomeIntermediaire(Boolean diplomeIntermediaire) {
        this.diplomeIntermediaire = diplomeIntermediaire;
    }

    public ModifierDiplomeRequest actif(Boolean actif) {
        this.actif = actif;
        return this;
    }

    /**
     * Temoin qui caracterise si le diplome est autorisé à être délivrer
     *
     * @return actif
     */
    @javax.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_ACTIF)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Boolean getActif() {
        return actif;
    }

    @JsonProperty(JSON_PROPERTY_ACTIF)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    /** Return true if this ModifierDiplomeRequest object is equal to o. */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ModifierDiplomeRequest modifierDiplomeRequest = (ModifierDiplomeRequest) o;
        return Objects.equals(this.id, modifierDiplomeRequest.id)
                && Objects.equals(
                        this.periodesValiditeId, modifierDiplomeRequest.periodesValiditeId)
                && Objects.equals(this.finaliteDiplome, modifierDiplomeRequest.finaliteDiplome)
                && Objects.equals(this.intitule, modifierDiplomeRequest.intitule)
                && Objects.equals(this.libelleCourt, modifierDiplomeRequest.libelleCourt)
                && Objects.equals(this.libelleLong, modifierDiplomeRequest.libelleLong)
                && Objects.equals(this.libelleAffichage, modifierDiplomeRequest.libelleAffichage)
                && Objects.equals(
                        this.diplomeIntermediaire, modifierDiplomeRequest.diplomeIntermediaire)
                && Objects.equals(this.actif, modifierDiplomeRequest.actif);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                periodesValiditeId,
                finaliteDiplome,
                intitule,
                libelleCourt,
                libelleLong,
                libelleAffichage,
                diplomeIntermediaire,
                actif);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ModifierDiplomeRequest {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    periodesValiditeId: ")
                .append(toIndentedString(periodesValiditeId))
                .append("\n");
        sb.append("    finaliteDiplome: ").append(toIndentedString(finaliteDiplome)).append("\n");
        sb.append("    intitule: ").append(toIndentedString(intitule)).append("\n");
        sb.append("    libelleCourt: ").append(toIndentedString(libelleCourt)).append("\n");
        sb.append("    libelleLong: ").append(toIndentedString(libelleLong)).append("\n");
        sb.append("    libelleAffichage: ").append(toIndentedString(libelleAffichage)).append("\n");
        sb.append("    diplomeIntermediaire: ")
                .append(toIndentedString(diplomeIntermediaire))
                .append("\n");
        sb.append("    actif: ").append(toIndentedString(actif)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces (except the first
     * line).
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

        // add `id` to the URL query string
        if (getId() != null) {
            joiner.add(
                    String.format(
                            "%sid%s=%s",
                            prefix,
                            suffix,
                            URLEncoder.encode(
                                            ApiClient.valueToString(getId()),
                                            StandardCharsets.UTF_8)
                                    .replaceAll("\\+", "%20")));
        }

        // add `periodesValiditeId` to the URL query string
        if (getPeriodesValiditeId() != null) {
            for (int i = 0; i < getPeriodesValiditeId().size(); i++) {
                if (getPeriodesValiditeId().get(i) != null) {
                    joiner.add(
                            String.format(
                                    "%speriodesValiditeId%s%s=%s",
                                    prefix,
                                    suffix,
                                    "".equals(suffix)
                                            ? ""
                                            : String.format(
                                                    "%s%d%s", containerPrefix, i, containerSuffix),
                                    URLEncoder.encode(
                                                    ApiClient.valueToString(
                                                            getPeriodesValiditeId().get(i)),
                                                    StandardCharsets.UTF_8)
                                            .replaceAll("\\+", "%20")));
                }
            }
        }

        // add `finaliteDiplome` to the URL query string
        if (getFinaliteDiplome() != null) {
            joiner.add(
                    String.format(
                            "%sfinaliteDiplome%s=%s",
                            prefix,
                            suffix,
                            URLEncoder.encode(
                                            ApiClient.valueToString(getFinaliteDiplome()),
                                            StandardCharsets.UTF_8)
                                    .replaceAll("\\+", "%20")));
        }

        // add `intitule` to the URL query string
        if (getIntitule() != null) {
            joiner.add(
                    String.format(
                            "%sintitule%s=%s",
                            prefix,
                            suffix,
                            URLEncoder.encode(
                                            ApiClient.valueToString(getIntitule()),
                                            StandardCharsets.UTF_8)
                                    .replaceAll("\\+", "%20")));
        }

        // add `libelleCourt` to the URL query string
        if (getLibelleCourt() != null) {
            joiner.add(
                    String.format(
                            "%slibelleCourt%s=%s",
                            prefix,
                            suffix,
                            URLEncoder.encode(
                                            ApiClient.valueToString(getLibelleCourt()),
                                            StandardCharsets.UTF_8)
                                    .replaceAll("\\+", "%20")));
        }

        // add `libelleLong` to the URL query string
        if (getLibelleLong() != null) {
            joiner.add(
                    String.format(
                            "%slibelleLong%s=%s",
                            prefix,
                            suffix,
                            URLEncoder.encode(
                                            ApiClient.valueToString(getLibelleLong()),
                                            StandardCharsets.UTF_8)
                                    .replaceAll("\\+", "%20")));
        }

        // add `libelleAffichage` to the URL query string
        if (getLibelleAffichage() != null) {
            joiner.add(
                    String.format(
                            "%slibelleAffichage%s=%s",
                            prefix,
                            suffix,
                            URLEncoder.encode(
                                            ApiClient.valueToString(getLibelleAffichage()),
                                            StandardCharsets.UTF_8)
                                    .replaceAll("\\+", "%20")));
        }

        // add `diplomeIntermediaire` to the URL query string
        if (getDiplomeIntermediaire() != null) {
            joiner.add(
                    String.format(
                            "%sdiplomeIntermediaire%s=%s",
                            prefix,
                            suffix,
                            URLEncoder.encode(
                                            ApiClient.valueToString(getDiplomeIntermediaire()),
                                            StandardCharsets.UTF_8)
                                    .replaceAll("\\+", "%20")));
        }

        // add `actif` to the URL query string
        if (getActif() != null) {
            joiner.add(
                    String.format(
                            "%sactif%s=%s",
                            prefix,
                            suffix,
                            URLEncoder.encode(
                                            ApiClient.valueToString(getActif()),
                                            StandardCharsets.UTF_8)
                                    .replaceAll("\\+", "%20")));
        }

        return joiner.toString();
    }
}