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
import java.util.HashMap;
import java.util.List;
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
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/** ObjetMaquetteDetail */
@JsonPropertyOrder({
    ObjetMaquetteDetail.JSON_PROPERTY_ID,
    ObjetMaquetteDetail.JSON_PROPERTY_TYPE_OBJET_MAQUETTE,
    ObjetMaquetteDetail.JSON_PROPERTY_CODE,
    ObjetMaquetteDetail.JSON_PROPERTY_ESPACE,
    ObjetMaquetteDetail.JSON_PROPERTY_MUTUALISE,
    ObjetMaquetteDetail.JSON_PROPERTY_ENFANTS,
    ObjetMaquetteDetail.JSON_PROPERTY_CONTEXTES,
    ObjetMaquetteDetail.JSON_PROPERTY_DESCRIPTEURS_OBJET_MAQUETTE,
    ObjetMaquetteDetail.JSON_PROPERTY_DESCRIPTEURS_ENQUETE,
    ObjetMaquetteDetail.JSON_PROPERTY_DESCRIPTEURS_SYLLABUS,
    ObjetMaquetteDetail.JSON_PROPERTY_FORMATS_ENSEIGNEMENT
})
@javax.annotation.Generated(
        value = "org.openapitools.codegen.languages.JavaClientCodegen",
        date = "2024-07-04T09:22:32.722688+02:00[Europe/Paris]",
        comments = "Generator version: 7.7.0")
@JsonIgnoreProperties(
        value = "typeObjetMaquette", // ignore manually set typeObjetMaquette, it will be
        // automatically generated by Jackson during serialization
        allowSetters = true // allows the typeObjetMaquette to be set during deserialization
        )
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "typeObjetMaquette",
        visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Formation.class, name = "FORMATION"),
    @JsonSubTypes.Type(value = Groupement.class, name = "GROUPEMENT"),
    @JsonSubTypes.Type(value = ObjetFormation.class, name = "OBJET-FORMATION"),
})
public class ObjetMaquetteDetail {
    public static final String JSON_PROPERTY_ID = "id";
    private UUID id;

    public static final String JSON_PROPERTY_TYPE_OBJET_MAQUETTE = "typeObjetMaquette";
    private TypeObjetMaquette typeObjetMaquette;

    public static final String JSON_PROPERTY_CODE = "code";
    private String code;

    public static final String JSON_PROPERTY_ESPACE = "espace";
    private UUID espace;

    public static final String JSON_PROPERTY_MUTUALISE = "mutualise";
    private Boolean mutualise;

    public static final String JSON_PROPERTY_ENFANTS = "enfants";
    private List<Enfant> enfants = new ArrayList<>();

    public static final String JSON_PROPERTY_CONTEXTES = "contextes";
    private List<Contexte> contextes = new ArrayList<>();

    public static final String JSON_PROPERTY_DESCRIPTEURS_OBJET_MAQUETTE =
            "descripteursObjetMaquette";
    private DescripteursObjetMaquette descripteursObjetMaquette;

    public static final String JSON_PROPERTY_DESCRIPTEURS_ENQUETE = "descripteursEnquete";
    private DescripteursEnquete descripteursEnquete;

    public static final String JSON_PROPERTY_DESCRIPTEURS_SYLLABUS = "descripteursSyllabus";
    private DescripteursSyllabus descripteursSyllabus;

    public static final String JSON_PROPERTY_FORMATS_ENSEIGNEMENT = "formatsEnseignement";
    private FormatsEnseignement formatsEnseignement;

    public ObjetMaquetteDetail() {}

    public ObjetMaquetteDetail id(UUID id) {
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

    public ObjetMaquetteDetail typeObjetMaquette(TypeObjetMaquette typeObjetMaquette) {
        this.typeObjetMaquette = typeObjetMaquette;
        return this;
    }

    /**
     * Get typeObjetMaquette
     *
     * @return typeObjetMaquette
     */
    @javax.annotation.Nullable
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

    public ObjetMaquetteDetail code(String code) {
        this.code = code;
        return this;
    }

    /**
     * Code d&#39;un diplôme
     *
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

    public ObjetMaquetteDetail espace(UUID espace) {
        this.espace = espace;
        return this;
    }

    /**
     * Identifiant de ressource
     *
     * @return espace
     */
    @javax.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_ESPACE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public UUID getEspace() {
        return espace;
    }

    @JsonProperty(JSON_PROPERTY_ESPACE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setEspace(UUID espace) {
        this.espace = espace;
    }

    public ObjetMaquetteDetail mutualise(Boolean mutualise) {
        this.mutualise = mutualise;
        return this;
    }

    /**
     * Temoin qui caracterise le caractère mutualisé de l&#39;objet
     *
     * @return mutualise
     */
    @javax.annotation.Nullable
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

    public ObjetMaquetteDetail enfants(List<Enfant> enfants) {
        this.enfants = enfants;
        return this;
    }

    public ObjetMaquetteDetail addEnfantsItem(Enfant enfantsItem) {
        if (this.enfants == null) {
            this.enfants = new ArrayList<>();
        }
        this.enfants.add(enfantsItem);
        return this;
    }

    /**
     * les enfants de l&#39;objet
     *
     * @return enfants
     */
    @javax.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_ENFANTS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<Enfant> getEnfants() {
        return enfants;
    }

    @JsonProperty(JSON_PROPERTY_ENFANTS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setEnfants(List<Enfant> enfants) {
        this.enfants = enfants;
    }

    public ObjetMaquetteDetail contextes(List<Contexte> contextes) {
        this.contextes = contextes;
        return this;
    }

    public ObjetMaquetteDetail addContextesItem(Contexte contextesItem) {
        if (this.contextes == null) {
            this.contextes = new ArrayList<>();
        }
        this.contextes.add(contextesItem);
        return this;
    }

    /**
     * les contextes de l&#39;objet
     *
     * @return contextes
     */
    @javax.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_CONTEXTES)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<Contexte> getContextes() {
        return contextes;
    }

    @JsonProperty(JSON_PROPERTY_CONTEXTES)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setContextes(List<Contexte> contextes) {
        this.contextes = contextes;
    }

    public ObjetMaquetteDetail descripteursObjetMaquette(
            DescripteursObjetMaquette descripteursObjetMaquette) {
        this.descripteursObjetMaquette = descripteursObjetMaquette;
        return this;
    }

    /**
     * Get descripteursObjetMaquette
     *
     * @return descripteursObjetMaquette
     */
    @javax.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_DESCRIPTEURS_OBJET_MAQUETTE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public DescripteursObjetMaquette getDescripteursObjetMaquette() {
        return descripteursObjetMaquette;
    }

    @JsonProperty(JSON_PROPERTY_DESCRIPTEURS_OBJET_MAQUETTE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setDescripteursObjetMaquette(DescripteursObjetMaquette descripteursObjetMaquette) {
        this.descripteursObjetMaquette = descripteursObjetMaquette;
    }

    public ObjetMaquetteDetail descripteursEnquete(DescripteursEnquete descripteursEnquete) {
        this.descripteursEnquete = descripteursEnquete;
        return this;
    }

    /**
     * Get descripteursEnquete
     *
     * @return descripteursEnquete
     */
    @javax.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_DESCRIPTEURS_ENQUETE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public DescripteursEnquete getDescripteursEnquete() {
        return descripteursEnquete;
    }

    @JsonProperty(JSON_PROPERTY_DESCRIPTEURS_ENQUETE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setDescripteursEnquete(DescripteursEnquete descripteursEnquete) {
        this.descripteursEnquete = descripteursEnquete;
    }

    public ObjetMaquetteDetail descripteursSyllabus(DescripteursSyllabus descripteursSyllabus) {
        this.descripteursSyllabus = descripteursSyllabus;
        return this;
    }

    /**
     * Get descripteursSyllabus
     *
     * @return descripteursSyllabus
     */
    @javax.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_DESCRIPTEURS_SYLLABUS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public DescripteursSyllabus getDescripteursSyllabus() {
        return descripteursSyllabus;
    }

    @JsonProperty(JSON_PROPERTY_DESCRIPTEURS_SYLLABUS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setDescripteursSyllabus(DescripteursSyllabus descripteursSyllabus) {
        this.descripteursSyllabus = descripteursSyllabus;
    }

    public ObjetMaquetteDetail formatsEnseignement(FormatsEnseignement formatsEnseignement) {
        this.formatsEnseignement = formatsEnseignement;
        return this;
    }

    /**
     * Get formatsEnseignement
     *
     * @return formatsEnseignement
     */
    @javax.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_FORMATS_ENSEIGNEMENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public FormatsEnseignement getFormatsEnseignement() {
        return formatsEnseignement;
    }

    @JsonProperty(JSON_PROPERTY_FORMATS_ENSEIGNEMENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setFormatsEnseignement(FormatsEnseignement formatsEnseignement) {
        this.formatsEnseignement = formatsEnseignement;
    }

    /** Return true if this ObjetMaquetteDetail object is equal to o. */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ObjetMaquetteDetail objetMaquetteDetail = (ObjetMaquetteDetail) o;
        return Objects.equals(this.id, objetMaquetteDetail.id)
                && Objects.equals(this.typeObjetMaquette, objetMaquetteDetail.typeObjetMaquette)
                && Objects.equals(this.code, objetMaquetteDetail.code)
                && Objects.equals(this.espace, objetMaquetteDetail.espace)
                && Objects.equals(this.mutualise, objetMaquetteDetail.mutualise)
                && Objects.equals(this.enfants, objetMaquetteDetail.enfants)
                && Objects.equals(this.contextes, objetMaquetteDetail.contextes)
                && Objects.equals(
                        this.descripteursObjetMaquette,
                        objetMaquetteDetail.descripteursObjetMaquette)
                && Objects.equals(this.descripteursEnquete, objetMaquetteDetail.descripteursEnquete)
                && Objects.equals(
                        this.descripteursSyllabus, objetMaquetteDetail.descripteursSyllabus)
                && Objects.equals(
                        this.formatsEnseignement, objetMaquetteDetail.formatsEnseignement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                typeObjetMaquette,
                code,
                espace,
                mutualise,
                enfants,
                contextes,
                descripteursObjetMaquette,
                descripteursEnquete,
                descripteursSyllabus,
                formatsEnseignement);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ObjetMaquetteDetail {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    typeObjetMaquette: ")
                .append(toIndentedString(typeObjetMaquette))
                .append("\n");
        sb.append("    code: ").append(toIndentedString(code)).append("\n");
        sb.append("    espace: ").append(toIndentedString(espace)).append("\n");
        sb.append("    mutualise: ").append(toIndentedString(mutualise)).append("\n");
        sb.append("    enfants: ").append(toIndentedString(enfants)).append("\n");
        sb.append("    contextes: ").append(toIndentedString(contextes)).append("\n");
        sb.append("    descripteursObjetMaquette: ")
                .append(toIndentedString(descripteursObjetMaquette))
                .append("\n");
        sb.append("    descripteursEnquete: ")
                .append(toIndentedString(descripteursEnquete))
                .append("\n");
        sb.append("    descripteursSyllabus: ")
                .append(toIndentedString(descripteursSyllabus))
                .append("\n");
        sb.append("    formatsEnseignement: ")
                .append(toIndentedString(formatsEnseignement))
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

        // add `espace` to the URL query string
        if (getEspace() != null) {
            joiner.add(
                    String.format(
                            "%sespace%s=%s",
                            prefix,
                            suffix,
                            URLEncoder.encode(
                                            ApiClient.valueToString(getEspace()),
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

        // add `contextes` to the URL query string
        if (getContextes() != null) {
            for (int i = 0; i < getContextes().size(); i++) {
                if (getContextes().get(i) != null) {
                    joiner.add(
                            getContextes()
                                    .get(i)
                                    .toUrlQueryString(
                                            String.format(
                                                    "%scontextes%s%s",
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

        // add `descripteursObjetMaquette` to the URL query string
        if (getDescripteursObjetMaquette() != null) {
            joiner.add(
                    getDescripteursObjetMaquette()
                            .toUrlQueryString(prefix + "descripteursObjetMaquette" + suffix));
        }

        // add `descripteursEnquete` to the URL query string
        if (getDescripteursEnquete() != null) {
            joiner.add(
                    getDescripteursEnquete()
                            .toUrlQueryString(prefix + "descripteursEnquete" + suffix));
        }

        // add `descripteursSyllabus` to the URL query string
        if (getDescripteursSyllabus() != null) {
            joiner.add(
                    getDescripteursSyllabus()
                            .toUrlQueryString(prefix + "descripteursSyllabus" + suffix));
        }

        // add `formatsEnseignement` to the URL query string
        if (getFormatsEnseignement() != null) {
            joiner.add(
                    getFormatsEnseignement()
                            .toUrlQueryString(prefix + "formatsEnseignement" + suffix));
        }

        return joiner.toString();
    }

    static {
        // Initialize and register the discriminator mappings.
        Map<String, Class<?>> mappings = new HashMap<String, Class<?>>();
        mappings.put("FORMATION", Formation.class);
        mappings.put("GROUPEMENT", Groupement.class);
        mappings.put("OBJET-FORMATION", ObjetFormation.class);
        mappings.put("ObjetMaquetteDetail", ObjetMaquetteDetail.class);
        JSON.registerDiscriminator(ObjetMaquetteDetail.class, "typeObjetMaquette", mappings);
    }
}