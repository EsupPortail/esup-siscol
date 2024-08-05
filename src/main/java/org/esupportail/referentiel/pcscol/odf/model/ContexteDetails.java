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

/** ContexteDetails */
@JsonPropertyOrder({
    ContexteDetails.JSON_PROPERTY_ID,
    ContexteDetails.JSON_PROPERTY_CHEMIN,
    ContexteDetails.JSON_PROPERTY_CHEMIN_CODES,
    ContexteDetails.JSON_PROPERTY_VALIDE,
    ContexteDetails.JSON_PROPERTY_INSCRIPTION_ADMINISTRATIVE,
    ContexteDetails.JSON_PROPERTY_DESCRIPTEURS_CONTEXTE
})
@javax.annotation.Generated(
        value = "org.openapitools.codegen.languages.JavaClientCodegen",
        date = "2024-07-04T09:22:32.722688+02:00[Europe/Paris]",
        comments = "Generator version: 7.7.0")
public class ContexteDetails {
    public static final String JSON_PROPERTY_ID = "id";
    private UUID id;

    public static final String JSON_PROPERTY_CHEMIN = "chemin";
    private List<UUID> chemin = new ArrayList<>();

    public static final String JSON_PROPERTY_CHEMIN_CODES = "cheminCodes";
    private List<String> cheminCodes = new ArrayList<>();

    public static final String JSON_PROPERTY_VALIDE = "valide";
    private Boolean valide;

    public static final String JSON_PROPERTY_INSCRIPTION_ADMINISTRATIVE =
            "inscriptionAdministrative";
    private PointInscriptionAdministrative inscriptionAdministrative;

    public static final String JSON_PROPERTY_DESCRIPTEURS_CONTEXTE = "descripteursContexte";
    private DescripteursContexte descripteursContexte;

    public ContexteDetails() {}

    public ContexteDetails id(UUID id) {
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

    public ContexteDetails chemin(List<UUID> chemin) {
        this.chemin = chemin;
        return this;
    }

    public ContexteDetails addCheminItem(UUID cheminItem) {
        if (this.chemin == null) {
            this.chemin = new ArrayList<>();
        }
        this.chemin.add(cheminItem);
        return this;
    }

    /**
     * Chemin du contexte
     *
     * @return chemin
     */
    @javax.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_CHEMIN)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<UUID> getChemin() {
        return chemin;
    }

    @JsonProperty(JSON_PROPERTY_CHEMIN)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setChemin(List<UUID> chemin) {
        this.chemin = chemin;
    }

    public ContexteDetails cheminCodes(List<String> cheminCodes) {
        this.cheminCodes = cheminCodes;
        return this;
    }

    public ContexteDetails addCheminCodesItem(String cheminCodesItem) {
        if (this.cheminCodes == null) {
            this.cheminCodes = new ArrayList<>();
        }
        this.cheminCodes.add(cheminCodesItem);
        return this;
    }

    /**
     * Chemin du contexte
     *
     * @return cheminCodes
     */
    @javax.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_CHEMIN_CODES)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<String> getCheminCodes() {
        return cheminCodes;
    }

    @JsonProperty(JSON_PROPERTY_CHEMIN_CODES)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setCheminCodes(List<String> cheminCodes) {
        this.cheminCodes = cheminCodes;
    }

    public ContexteDetails valide(Boolean valide) {
        this.valide = valide;
        return this;
    }

    /**
     * Temoin qui caracterise la validite du contexte
     *
     * @return valide
     */
    @javax.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_VALIDE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Boolean getValide() {
        return valide;
    }

    @JsonProperty(JSON_PROPERTY_VALIDE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setValide(Boolean valide) {
        this.valide = valide;
    }

    public ContexteDetails inscriptionAdministrative(
            PointInscriptionAdministrative inscriptionAdministrative) {
        this.inscriptionAdministrative = inscriptionAdministrative;
        return this;
    }

    /**
     * Get inscriptionAdministrative
     *
     * @return inscriptionAdministrative
     */
    @javax.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_INSCRIPTION_ADMINISTRATIVE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public PointInscriptionAdministrative getInscriptionAdministrative() {
        return inscriptionAdministrative;
    }

    @JsonProperty(JSON_PROPERTY_INSCRIPTION_ADMINISTRATIVE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setInscriptionAdministrative(
            PointInscriptionAdministrative inscriptionAdministrative) {
        this.inscriptionAdministrative = inscriptionAdministrative;
    }

    public ContexteDetails descripteursContexte(DescripteursContexte descripteursContexte) {
        this.descripteursContexte = descripteursContexte;
        return this;
    }

    /**
     * Get descripteursContexte
     *
     * @return descripteursContexte
     */
    @javax.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_DESCRIPTEURS_CONTEXTE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public DescripteursContexte getDescripteursContexte() {
        return descripteursContexte;
    }

    @JsonProperty(JSON_PROPERTY_DESCRIPTEURS_CONTEXTE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setDescripteursContexte(DescripteursContexte descripteursContexte) {
        this.descripteursContexte = descripteursContexte;
    }

    /** Return true if this ContexteDetails object is equal to o. */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ContexteDetails contexteDetails = (ContexteDetails) o;
        return Objects.equals(this.id, contexteDetails.id)
                && Objects.equals(this.chemin, contexteDetails.chemin)
                && Objects.equals(this.cheminCodes, contexteDetails.cheminCodes)
                && Objects.equals(this.valide, contexteDetails.valide)
                && Objects.equals(
                        this.inscriptionAdministrative, contexteDetails.inscriptionAdministrative)
                && Objects.equals(this.descripteursContexte, contexteDetails.descripteursContexte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id, chemin, cheminCodes, valide, inscriptionAdministrative, descripteursContexte);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ContexteDetails {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    chemin: ").append(toIndentedString(chemin)).append("\n");
        sb.append("    cheminCodes: ").append(toIndentedString(cheminCodes)).append("\n");
        sb.append("    valide: ").append(toIndentedString(valide)).append("\n");
        sb.append("    inscriptionAdministrative: ")
                .append(toIndentedString(inscriptionAdministrative))
                .append("\n");
        sb.append("    descripteursContexte: ")
                .append(toIndentedString(descripteursContexte))
                .append("\n");
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

        // add `chemin` to the URL query string
        if (getChemin() != null) {
            for (int i = 0; i < getChemin().size(); i++) {
                if (getChemin().get(i) != null) {
                    joiner.add(
                            String.format(
                                    "%schemin%s%s=%s",
                                    prefix,
                                    suffix,
                                    "".equals(suffix)
                                            ? ""
                                            : String.format(
                                                    "%s%d%s", containerPrefix, i, containerSuffix),
                                    URLEncoder.encode(
                                                    ApiClient.valueToString(getChemin().get(i)),
                                                    StandardCharsets.UTF_8)
                                            .replaceAll("\\+", "%20")));
                }
            }
        }

        // add `cheminCodes` to the URL query string
        if (getCheminCodes() != null) {
            for (int i = 0; i < getCheminCodes().size(); i++) {
                joiner.add(
                        String.format(
                                "%scheminCodes%s%s=%s",
                                prefix,
                                suffix,
                                "".equals(suffix)
                                        ? ""
                                        : String.format(
                                                "%s%d%s", containerPrefix, i, containerSuffix),
                                URLEncoder.encode(
                                                ApiClient.valueToString(getCheminCodes().get(i)),
                                                StandardCharsets.UTF_8)
                                        .replaceAll("\\+", "%20")));
            }
        }

        // add `valide` to the URL query string
        if (getValide() != null) {
            joiner.add(
                    String.format(
                            "%svalide%s=%s",
                            prefix,
                            suffix,
                            URLEncoder.encode(
                                            ApiClient.valueToString(getValide()),
                                            StandardCharsets.UTF_8)
                                    .replaceAll("\\+", "%20")));
        }

        // add `inscriptionAdministrative` to the URL query string
        if (getInscriptionAdministrative() != null) {
            joiner.add(
                    getInscriptionAdministrative()
                            .toUrlQueryString(prefix + "inscriptionAdministrative" + suffix));
        }

        // add `descripteursContexte` to the URL query string
        if (getDescripteursContexte() != null) {
            joiner.add(
                    getDescripteursContexte()
                            .toUrlQueryString(prefix + "descripteursContexte" + suffix));
        }

        return joiner.toString();
    }
}
