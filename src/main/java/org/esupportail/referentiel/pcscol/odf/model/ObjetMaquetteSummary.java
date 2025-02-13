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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import org.esupportail.referentiel.pcscol.invoker.ApiClient;
import org.esupportail.referentiel.pcscol.invoker.JSON;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;

/** ObjetMaquetteSummary */
@JsonPropertyOrder({
    ObjetMaquetteSummary.JSON_PROPERTY_ID,
    ObjetMaquetteSummary.JSON_PROPERTY_TYPE_OBJET_MAQUETTE,
    ObjetMaquetteSummary.JSON_PROPERTY_TYPE_OBJET_FORMATION,
    ObjetMaquetteSummary.JSON_PROPERTY_CODE,
    ObjetMaquetteSummary.JSON_PROPERTY_ESPACE_LIBELLE,
    ObjetMaquetteSummary.JSON_PROPERTY_MUTUALISE,
    ObjetMaquetteSummary.JSON_PROPERTY_LIBELLE,
    ObjetMaquetteSummary.JSON_PROPERTY_LIBELLE_LONG,
    ObjetMaquetteSummary.JSON_PROPERTY_VALIDE_IN_ANY_CONTEXTE,
    ObjetMaquetteSummary.JSON_PROPERTY_PERIODE_ACTIVE
})
@jakarta.annotation.Generated(
        value = "org.openapitools.codegen.languages.JavaClientCodegen",
        date = "2024-07-04T09:22:32.722688+02:00[Europe/Paris]",
        comments = "Generator version: 7.7.0")
@JsonIgnoreProperties(
        value = "typeObjetMaquette", // ignore manually set typeObjetMaquette, it will be
        // automatically generated by Jackson during serialization
        allowSetters = true // allows the typeObjetMaquette to be set during deserialization
        )
// @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property =
// "typeObjetMaquette", visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = DescripteursFormation.class, name = "FORMATION"),
    @JsonSubTypes.Type(value = DescripteursGroupement.class, name = "GROUPEMENT"),
    @JsonSubTypes.Type(value = DescripteursObjetFormation.class, name = "OBJET-FORMATION"),
})
public class ObjetMaquetteSummary {
    public static final String JSON_PROPERTY_ID = "id";
    private UUID id;

    public static final String JSON_PROPERTY_TYPE_OBJET_MAQUETTE = "typeObjetMaquette";
    private TypeObjetMaquette typeObjetMaquette;

    public static final String JSON_PROPERTY_TYPE_OBJET_FORMATION = "typeObjetFormation";
    private String typeObjetFormation;

    public static final String JSON_PROPERTY_CODE = "code";
    private String code;

    public static final String JSON_PROPERTY_ESPACE_LIBELLE = "espaceLibelle";
    private String espaceLibelle;

    public static final String JSON_PROPERTY_MUTUALISE = "mutualise";
    private Boolean mutualise;

    public static final String JSON_PROPERTY_LIBELLE = "libelle";
    private String libelle;

    public static final String JSON_PROPERTY_LIBELLE_LONG = "libelleLong";
    private String libelleLong;

    public static final String JSON_PROPERTY_VALIDE_IN_ANY_CONTEXTE = "valideInAnyContexte";
    private Boolean valideInAnyContexte;

    public static final String JSON_PROPERTY_PERIODE_ACTIVE = "periodeActive";
    private Boolean periodeActive;

    public ObjetMaquetteSummary() {}

    public ObjetMaquetteSummary id(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Identifiant de ressource
     *
     * @return id
     */
    @jakarta.annotation.Nullable
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

    public ObjetMaquetteSummary typeObjetMaquette(TypeObjetMaquette typeObjetMaquette) {
        this.typeObjetMaquette = typeObjetMaquette;
        return this;
    }

    /**
     * Get typeObjetMaquette
     *
     * @return typeObjetMaquette
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_TYPE_OBJET_MAQUETTE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public TypeObjetMaquette getTypeObjetMaquette() {
        return typeObjetMaquette;
    }

    @JsonProperty(JSON_PROPERTY_TYPE_OBJET_MAQUETTE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTypeObjetMaquette(TypeObjetMaquette typeObjetMaquette) {
        this.typeObjetMaquette = typeObjetMaquette;
    }

    public ObjetMaquetteSummary typeObjetFormation(String typeObjetFormation) {
        this.typeObjetFormation = typeObjetFormation;
        return this;
    }

    /**
     * Type de l&#39;objet formation
     *
     * @return typeObjetFormation
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_TYPE_OBJET_FORMATION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getTypeObjetFormation() {
        return typeObjetFormation;
    }

    @JsonProperty(JSON_PROPERTY_TYPE_OBJET_FORMATION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTypeObjetFormation(String typeObjetFormation) {
        this.typeObjetFormation = typeObjetFormation;
    }

    public ObjetMaquetteSummary code(String code) {
        this.code = code;
        return this;
    }

    /**
     * Code d&#39;un diplôme
     *
     * @return code
     */
    @jakarta.annotation.Nullable
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

    public ObjetMaquetteSummary espaceLibelle(String espaceLibelle) {
        this.espaceLibelle = espaceLibelle;
        return this;
    }

    /**
     * Libellé d&#39;un objet maquette
     *
     * @return espaceLibelle
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_ESPACE_LIBELLE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getEspaceLibelle() {
        return espaceLibelle;
    }

    @JsonProperty(JSON_PROPERTY_ESPACE_LIBELLE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setEspaceLibelle(String espaceLibelle) {
        this.espaceLibelle = espaceLibelle;
    }

    public ObjetMaquetteSummary mutualise(Boolean mutualise) {
        this.mutualise = mutualise;
        return this;
    }

    /**
     * Temoin qui caracterise le caractère mutualisé de l&#39;objet
     *
     * @return mutualise
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_MUTUALISE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Boolean getMutualise() {
        return mutualise;
    }

    @JsonProperty(JSON_PROPERTY_MUTUALISE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setMutualise(Boolean mutualise) {
        this.mutualise = mutualise;
    }

    public ObjetMaquetteSummary libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    /**
     * Get libelle
     *
     * @return libelle
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_LIBELLE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getLibelle() {
        return libelle;
    }

    @JsonProperty(JSON_PROPERTY_LIBELLE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public ObjetMaquetteSummary libelleLong(String libelleLong) {
        this.libelleLong = libelleLong;
        return this;
    }

    /**
     * Get libelleLong
     *
     * @return libelleLong
     */
    @jakarta.annotation.Nullable
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

    public ObjetMaquetteSummary valideInAnyContexte(Boolean valideInAnyContexte) {
        this.valideInAnyContexte = valideInAnyContexte;
        return this;
    }

    /**
     * Témoin qui indique si un des contextes de l&#39;objet est validé
     *
     * @return valideInAnyContexte
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_VALIDE_IN_ANY_CONTEXTE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Boolean getValideInAnyContexte() {
        return valideInAnyContexte;
    }

    @JsonProperty(JSON_PROPERTY_VALIDE_IN_ANY_CONTEXTE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setValideInAnyContexte(Boolean valideInAnyContexte) {
        this.valideInAnyContexte = valideInAnyContexte;
    }

    public ObjetMaquetteSummary periodeActive(Boolean periodeActive) {
        this.periodeActive = periodeActive;
        return this;
    }

    /**
     * Témoin qui indique si la période est active, si applicable au type d&#39;espace
     *
     * @return periodeActive
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_PERIODE_ACTIVE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Boolean getPeriodeActive() {
        return periodeActive;
    }

    @JsonProperty(JSON_PROPERTY_PERIODE_ACTIVE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setPeriodeActive(Boolean periodeActive) {
        this.periodeActive = periodeActive;
    }

    /** Return true if this ObjetMaquetteSummary object is equal to o. */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ObjetMaquetteSummary objetMaquetteSummary = (ObjetMaquetteSummary) o;
        return Objects.equals(this.id, objetMaquetteSummary.id)
                && Objects.equals(this.typeObjetMaquette, objetMaquetteSummary.typeObjetMaquette)
                && Objects.equals(this.typeObjetFormation, objetMaquetteSummary.typeObjetFormation)
                && Objects.equals(this.code, objetMaquetteSummary.code)
                && Objects.equals(this.espaceLibelle, objetMaquetteSummary.espaceLibelle)
                && Objects.equals(this.mutualise, objetMaquetteSummary.mutualise)
                && Objects.equals(this.libelle, objetMaquetteSummary.libelle)
                && Objects.equals(this.libelleLong, objetMaquetteSummary.libelleLong)
                && Objects.equals(
                        this.valideInAnyContexte, objetMaquetteSummary.valideInAnyContexte)
                && Objects.equals(this.periodeActive, objetMaquetteSummary.periodeActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                typeObjetMaquette,
                typeObjetFormation,
                code,
                espaceLibelle,
                mutualise,
                libelle,
                libelleLong,
                valideInAnyContexte,
                periodeActive);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ObjetMaquetteSummary {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    typeObjetMaquette: ")
                .append(toIndentedString(typeObjetMaquette))
                .append("\n");
        sb.append("    typeObjetFormation: ")
                .append(toIndentedString(typeObjetFormation))
                .append("\n");
        sb.append("    code: ").append(toIndentedString(code)).append("\n");
        sb.append("    espaceLibelle: ").append(toIndentedString(espaceLibelle)).append("\n");
        sb.append("    mutualise: ").append(toIndentedString(mutualise)).append("\n");
        sb.append("    libelle: ").append(toIndentedString(libelle)).append("\n");
        sb.append("    libelleLong: ").append(toIndentedString(libelleLong)).append("\n");
        sb.append("    valideInAnyContexte: ")
                .append(toIndentedString(valideInAnyContexte))
                .append("\n");
        sb.append("    periodeActive: ").append(toIndentedString(periodeActive)).append("\n");
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

        // add `typeObjetMaquette` to the URL query string
        if (getTypeObjetMaquette() != null) {
            joiner.add(
                    String.format(
                            "%stypeObjetMaquette%s=%s",
                            prefix,
                            suffix,
                            URLEncoder.encode(
                                            ApiClient.valueToString(getTypeObjetMaquette()),
                                            StandardCharsets.UTF_8)
                                    .replaceAll("\\+", "%20")));
        }

        // add `typeObjetFormation` to the URL query string
        if (getTypeObjetFormation() != null) {
            joiner.add(
                    String.format(
                            "%stypeObjetFormation%s=%s",
                            prefix,
                            suffix,
                            URLEncoder.encode(
                                            ApiClient.valueToString(getTypeObjetFormation()),
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

        // add `espaceLibelle` to the URL query string
        if (getEspaceLibelle() != null) {
            joiner.add(
                    String.format(
                            "%sespaceLibelle%s=%s",
                            prefix,
                            suffix,
                            URLEncoder.encode(
                                            ApiClient.valueToString(getEspaceLibelle()),
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

        // add `valideInAnyContexte` to the URL query string
        if (getValideInAnyContexte() != null) {
            joiner.add(
                    String.format(
                            "%svalideInAnyContexte%s=%s",
                            prefix,
                            suffix,
                            URLEncoder.encode(
                                            ApiClient.valueToString(getValideInAnyContexte()),
                                            StandardCharsets.UTF_8)
                                    .replaceAll("\\+", "%20")));
        }

        // add `periodeActive` to the URL query string
        if (getPeriodeActive() != null) {
            joiner.add(
                    String.format(
                            "%speriodeActive%s=%s",
                            prefix,
                            suffix,
                            URLEncoder.encode(
                                            ApiClient.valueToString(getPeriodeActive()),
                                            StandardCharsets.UTF_8)
                                    .replaceAll("\\+", "%20")));
        }

        return joiner.toString();
    }

    static {
        // Initialize and register the discriminator mappings.
        Map<String, Class<?>> mappings = new HashMap<String, Class<?>>();
        mappings.put("ObjetMaquetteSummary", ObjetMaquetteSummary.class);
        JSON.registerDiscriminator(ObjetMaquetteSummary.class, "typeObjetMaquette", mappings);
    }
}
