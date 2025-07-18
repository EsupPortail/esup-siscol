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

/** ObjetMaquetteFormatsEnseignementStructure */
@JsonPropertyOrder({
    ObjetMaquetteFormatsEnseignementStructure.JSON_PROPERTY_ID,
    ObjetMaquetteFormatsEnseignementStructure.JSON_PROPERTY_CODE,
    ObjetMaquetteFormatsEnseignementStructure.JSON_PROPERTY_LIBELLE,
    ObjetMaquetteFormatsEnseignementStructure.JSON_PROPERTY_LIBELLE_LONG,
    ObjetMaquetteFormatsEnseignementStructure.JSON_PROPERTY_MUTUALISE,
    ObjetMaquetteFormatsEnseignementStructure.JSON_PROPERTY_TYPE,
    ObjetMaquetteFormatsEnseignementStructure.JSON_PROPERTY_FORMATS_ENSEIGNEMENT,
    ObjetMaquetteFormatsEnseignementStructure.JSON_PROPERTY_ENFANTS
})
@jakarta.annotation.Generated(
        value = "org.openapitools.codegen.languages.JavaClientCodegen",
        date = "2024-07-04T09:22:32.722688+02:00[Europe/Paris]",
        comments = "Generator version: 7.7.0")
public class ObjetMaquetteFormatsEnseignementStructure {
    public static final String JSON_PROPERTY_ID = "id";
    private UUID id;

    public static final String JSON_PROPERTY_CODE = "code";
    private String code;

    public static final String JSON_PROPERTY_LIBELLE = "libelle";
    private String libelle;

    public static final String JSON_PROPERTY_LIBELLE_LONG = "libelleLong";
    private String libelleLong;

    public static final String JSON_PROPERTY_MUTUALISE = "mutualise";
    private Boolean mutualise;

    public static final String JSON_PROPERTY_TYPE = "type";
    private String type;

    public static final String JSON_PROPERTY_FORMATS_ENSEIGNEMENT = "formatsEnseignement";
    private FormatsEnseignement formatsEnseignement;

    public static final String JSON_PROPERTY_ENFANTS = "enfants";
    private List<EnfantsFormatsEnseignementStructure> enfants = new ArrayList<>();

    public ObjetMaquetteFormatsEnseignementStructure() {}

    public ObjetMaquetteFormatsEnseignementStructure id(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Identifiant de ressource
     *
     * @return id
     */
    @jakarta.annotation.Nonnull
    @JsonProperty(JSON_PROPERTY_ID)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public UUID getId() {
        return id;
    }

    @JsonProperty(JSON_PROPERTY_ID)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setId(UUID id) {
        this.id = id;
    }

    public ObjetMaquetteFormatsEnseignementStructure code(String code) {
        this.code = code;
        return this;
    }

    /**
     * Code d&#39;un diplôme
     *
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

    public ObjetMaquetteFormatsEnseignementStructure libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    /**
     * Libellé d&#39;un objet maquette
     *
     * @return libelle
     */
    @jakarta.annotation.Nonnull
    @JsonProperty(JSON_PROPERTY_LIBELLE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public String getLibelle() {
        return libelle;
    }

    @JsonProperty(JSON_PROPERTY_LIBELLE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public ObjetMaquetteFormatsEnseignementStructure libelleLong(String libelleLong) {
        this.libelleLong = libelleLong;
        return this;
    }

    /**
     * Libellé long d&#39;un objet maquette
     *
     * @return libelleLong
     */
    @jakarta.annotation.Nonnull
    @JsonProperty(JSON_PROPERTY_LIBELLE_LONG)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public String getLibelleLong() {
        return libelleLong;
    }

    @JsonProperty(JSON_PROPERTY_LIBELLE_LONG)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setLibelleLong(String libelleLong) {
        this.libelleLong = libelleLong;
    }

    public ObjetMaquetteFormatsEnseignementStructure mutualise(Boolean mutualise) {
        this.mutualise = mutualise;
        return this;
    }

    /**
     * Temoin indiquant si l&#39;objet est mutualise
     *
     * @return mutualise
     */
    @jakarta.annotation.Nonnull
    @JsonProperty(JSON_PROPERTY_MUTUALISE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public Boolean getMutualise() {
        return mutualise;
    }

    @JsonProperty(JSON_PROPERTY_MUTUALISE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setMutualise(Boolean mutualise) {
        this.mutualise = mutualise;
    }

    public ObjetMaquetteFormatsEnseignementStructure type(String type) {
        this.type = type;
        return this;
    }

    /**
     * Type de l&#39;objet maquette si OF (UE, EC, etc.)
     *
     * @return type
     */
    @jakarta.annotation.Nonnull
    @JsonProperty(JSON_PROPERTY_TYPE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public String getType() {
        return type;
    }

    @JsonProperty(JSON_PROPERTY_TYPE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setType(String type) {
        this.type = type;
    }

    public ObjetMaquetteFormatsEnseignementStructure formatsEnseignement(
            FormatsEnseignement formatsEnseignement) {
        this.formatsEnseignement = formatsEnseignement;
        return this;
    }

    /**
     * Get formatsEnseignement
     *
     * @return formatsEnseignement
     */
    @jakarta.annotation.Nonnull
    @JsonProperty(JSON_PROPERTY_FORMATS_ENSEIGNEMENT)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public FormatsEnseignement getFormatsEnseignement() {
        return formatsEnseignement;
    }

    @JsonProperty(JSON_PROPERTY_FORMATS_ENSEIGNEMENT)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setFormatsEnseignement(FormatsEnseignement formatsEnseignement) {
        this.formatsEnseignement = formatsEnseignement;
    }

    public ObjetMaquetteFormatsEnseignementStructure enfants(
            List<EnfantsFormatsEnseignementStructure> enfants) {
        this.enfants = enfants;
        return this;
    }

    public ObjetMaquetteFormatsEnseignementStructure addEnfantsItem(
            EnfantsFormatsEnseignementStructure enfantsItem) {
        if (this.enfants == null) {
            this.enfants = new ArrayList<>();
        }
        this.enfants.add(enfantsItem);
        return this;
    }

    /**
     * Get enfants
     *
     * @return enfants
     */
    @jakarta.annotation.Nonnull
    @JsonProperty(JSON_PROPERTY_ENFANTS)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public List<EnfantsFormatsEnseignementStructure> getEnfants() {
        return enfants;
    }

    @JsonProperty(JSON_PROPERTY_ENFANTS)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setEnfants(List<EnfantsFormatsEnseignementStructure> enfants) {
        this.enfants = enfants;
    }

    /** Return true if this ObjetMaquetteFormatsEnseignementStructure object is equal to o. */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ObjetMaquetteFormatsEnseignementStructure objetMaquetteFormatsEnseignementStructure =
                (ObjetMaquetteFormatsEnseignementStructure) o;
        return Objects.equals(this.id, objetMaquetteFormatsEnseignementStructure.id)
                && Objects.equals(this.code, objetMaquetteFormatsEnseignementStructure.code)
                && Objects.equals(this.libelle, objetMaquetteFormatsEnseignementStructure.libelle)
                && Objects.equals(
                        this.libelleLong, objetMaquetteFormatsEnseignementStructure.libelleLong)
                && Objects.equals(
                        this.mutualise, objetMaquetteFormatsEnseignementStructure.mutualise)
                && Objects.equals(this.type, objetMaquetteFormatsEnseignementStructure.type)
                && Objects.equals(
                        this.formatsEnseignement,
                        objetMaquetteFormatsEnseignementStructure.formatsEnseignement)
                && Objects.equals(this.enfants, objetMaquetteFormatsEnseignementStructure.enfants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id, code, libelle, libelleLong, mutualise, type, formatsEnseignement, enfants);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ObjetMaquetteFormatsEnseignementStructure {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    code: ").append(toIndentedString(code)).append("\n");
        sb.append("    libelle: ").append(toIndentedString(libelle)).append("\n");
        sb.append("    libelleLong: ").append(toIndentedString(libelleLong)).append("\n");
        sb.append("    mutualise: ").append(toIndentedString(mutualise)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    formatsEnseignement: ")
                .append(toIndentedString(formatsEnseignement))
                .append("\n");
        sb.append("    enfants: ").append(toIndentedString(enfants)).append("\n");
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

        // add `code` to the URL query string
        if (getCode() != null) {
            joiner.add(
                    String.format(
                            "%scode%s=%s",
                            prefix,
                            suffix,
                            URLEncoder.encode(
                                            ApiClient.valueToString(getCode()),
                                            StandardCharsets.UTF_8)
                                    .replaceAll("\\+", "%20")));
        }

        // add `libelle` to the URL query string
        if (getLibelle() != null) {
            joiner.add(
                    String.format(
                            "%slibelle%s=%s",
                            prefix,
                            suffix,
                            URLEncoder.encode(
                                            ApiClient.valueToString(getLibelle()),
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

        // add `mutualise` to the URL query string
        if (getMutualise() != null) {
            joiner.add(
                    String.format(
                            "%smutualise%s=%s",
                            prefix,
                            suffix,
                            URLEncoder.encode(
                                            ApiClient.valueToString(getMutualise()),
                                            StandardCharsets.UTF_8)
                                    .replaceAll("\\+", "%20")));
        }

        // add `type` to the URL query string
        if (getType() != null) {
            joiner.add(
                    String.format(
                            "%stype%s=%s",
                            prefix,
                            suffix,
                            URLEncoder.encode(
                                            ApiClient.valueToString(getType()),
                                            StandardCharsets.UTF_8)
                                    .replaceAll("\\+", "%20")));
        }

        // add `formatsEnseignement` to the URL query string
        if (getFormatsEnseignement() != null) {
            joiner.add(
                    getFormatsEnseignement()
                            .toUrlQueryString(prefix + "formatsEnseignement" + suffix));
        }

        // add `enfants` to the URL query string
        if (getEnfants() != null) {
            for (int i = 0; i < getEnfants().size(); i++) {
                if (getEnfants().get(i) != null) {
                    joiner.add(
                            getEnfants()
                                    .get(i)
                                    .toUrlQueryString(
                                            String.format(
                                                    "%senfants%s%s",
                                                    prefix,
                                                    suffix,
                                                    "".equals(suffix)
                                                            ? ""
                                                            : String.format(
                                                                    "%s%d%s",
                                                                    containerPrefix,
                                                                    i,
                                                                    containerSuffix))));
                }
            }
        }

        return joiner.toString();
    }
}
