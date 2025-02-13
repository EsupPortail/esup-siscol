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

import org.esupportail.referentiel.pcscol.invoker.ApiClient;
import org.esupportail.referentiel.pcscol.invoker.JSON;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/** CreerPeriodeRequest */
@JsonPropertyOrder({
    CreerPeriodeRequest.JSON_PROPERTY_LIBELLE_LONG,
    CreerPeriodeRequest.JSON_PROPERTY_LIBELLE_AFFICHAGE,
    CreerPeriodeRequest.JSON_PROPERTY_VALIDITE,
    CreerPeriodeRequest.JSON_PROPERTY_ACTIVE,
    CreerPeriodeRequest.JSON_PROPERTY_ANNEE_UNIVERSITAIRE,
    CreerPeriodeRequest.JSON_PROPERTY_TYPE
})
@jakarta.annotation.Generated(
        value = "org.openapitools.codegen.languages.JavaClientCodegen",
        date = "2024-07-04T09:22:32.722688+02:00[Europe/Paris]",
        comments = "Generator version: 7.7.0")
@JsonIgnoreProperties(
        value = "typeEspace", // ignore manually set typeEspace, it will be automatically generated
        // by Jackson during serialization
        allowSetters = true // allows the typeEspace to be set during deserialization
        )
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "typeEspace",
        visible = true)
public class CreerPeriodeRequest extends CreerEspaceRequest {
    public static final String JSON_PROPERTY_LIBELLE_LONG = "libelleLong";
    private String libelleLong;

    public static final String JSON_PROPERTY_LIBELLE_AFFICHAGE = "libelleAffichage";
    private String libelleAffichage;

    public static final String JSON_PROPERTY_VALIDITE = "validite";
    private DatesValidation validite;

    public static final String JSON_PROPERTY_ACTIVE = "active";
    private Boolean active;

    public static final String JSON_PROPERTY_ANNEE_UNIVERSITAIRE = "anneeUniversitaire";
    private Integer anneeUniversitaire;

    public static final String JSON_PROPERTY_TYPE = "type";
    private Typologie type;

    public CreerPeriodeRequest() {}

    public CreerPeriodeRequest libelleLong(String libelleLong) {
        this.libelleLong = libelleLong;
        return this;
    }

    /**
     * Libellé d&#39;un espace
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

    public CreerPeriodeRequest libelleAffichage(String libelleAffichage) {
        this.libelleAffichage = libelleAffichage;
        return this;
    }

    /**
     * Libellé d&#39;un espace
     *
     * @return libelleAffichage
     */
    @jakarta.annotation.Nonnull
    @JsonProperty(JSON_PROPERTY_LIBELLE_AFFICHAGE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public String getLibelleAffichage() {
        return libelleAffichage;
    }

    @JsonProperty(JSON_PROPERTY_LIBELLE_AFFICHAGE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setLibelleAffichage(String libelleAffichage) {
        this.libelleAffichage = libelleAffichage;
    }

    public CreerPeriodeRequest validite(DatesValidation validite) {
        this.validite = validite;
        return this;
    }

    /**
     * Get validite
     *
     * @return validite
     */
    @jakarta.annotation.Nonnull
    @JsonProperty(JSON_PROPERTY_VALIDITE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public DatesValidation getValidite() {
        return validite;
    }

    @JsonProperty(JSON_PROPERTY_VALIDITE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setValidite(DatesValidation validite) {
        this.validite = validite;
    }

    public CreerPeriodeRequest active(Boolean active) {
        this.active = active;
        return this;
    }

    /**
     * Get active
     *
     * @return active
     */
    @jakarta.annotation.Nonnull
    @JsonProperty(JSON_PROPERTY_ACTIVE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public Boolean getActive() {
        return active;
    }

    @JsonProperty(JSON_PROPERTY_ACTIVE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setActive(Boolean active) {
        this.active = active;
    }

    public CreerPeriodeRequest anneeUniversitaire(Integer anneeUniversitaire) {
        this.anneeUniversitaire = anneeUniversitaire;
        return this;
    }

    /**
     * Année universitaire minimum: 0
     *
     * @return anneeUniversitaire
     */
    @jakarta.annotation.Nullable
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

    public CreerPeriodeRequest type(Typologie type) {
        this.type = type;
        return this;
    }

    /**
     * Get type
     *
     * @return type
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_TYPE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Typologie getType() {
        return type;
    }

    @JsonProperty(JSON_PROPERTY_TYPE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setType(Typologie type) {
        this.type = type;
    }

    @Override
    public CreerPeriodeRequest code(String code) {
        this.setCode(code);
        return this;
    }

    @Override
    public CreerPeriodeRequest libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    @Override
    public CreerPeriodeRequest typeEspace(TypeEspace typeEspace) {
        this.setTypeEspace(typeEspace);
        return this;
    }

    /** Return true if this CreerPeriodeRequest object is equal to o. */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreerPeriodeRequest creerPeriodeRequest = (CreerPeriodeRequest) o;
        return Objects.equals(this.libelleLong, creerPeriodeRequest.libelleLong)
                && Objects.equals(this.libelleAffichage, creerPeriodeRequest.libelleAffichage)
                && Objects.equals(this.validite, creerPeriodeRequest.validite)
                && Objects.equals(this.active, creerPeriodeRequest.active)
                && Objects.equals(this.anneeUniversitaire, creerPeriodeRequest.anneeUniversitaire)
                && Objects.equals(this.type, creerPeriodeRequest.type)
                && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                libelleLong,
                libelleAffichage,
                validite,
                active,
                anneeUniversitaire,
                type,
                super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreerPeriodeRequest {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    libelleLong: ").append(toIndentedString(libelleLong)).append("\n");
        sb.append("    libelleAffichage: ").append(toIndentedString(libelleAffichage)).append("\n");
        sb.append("    validite: ").append(toIndentedString(validite)).append("\n");
        sb.append("    active: ").append(toIndentedString(active)).append("\n");
        sb.append("    anneeUniversitaire: ")
                .append(toIndentedString(anneeUniversitaire))
                .append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

        // add `typeEspace` to the URL query string
        if (getTypeEspace() != null) {
            joiner.add(
                    String.format(
                            "%stypeEspace%s=%s",
                            prefix,
                            suffix,
                            URLEncoder.encode(
                                            ApiClient.valueToString(getTypeEspace()),
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

        // add `validite` to the URL query string
        if (getValidite() != null) {
            joiner.add(getValidite().toUrlQueryString(prefix + "validite" + suffix));
        }

        // add `active` to the URL query string
        if (getActive() != null) {
            joiner.add(
                    String.format(
                            "%sactive%s=%s",
                            prefix,
                            suffix,
                            URLEncoder.encode(
                                            ApiClient.valueToString(getActive()),
                                            StandardCharsets.UTF_8)
                                    .replaceAll("\\+", "%20")));
        }

        // add `anneeUniversitaire` to the URL query string
        if (getAnneeUniversitaire() != null) {
            joiner.add(
                    String.format(
                            "%sanneeUniversitaire%s=%s",
                            prefix,
                            suffix,
                            URLEncoder.encode(
                                            ApiClient.valueToString(getAnneeUniversitaire()),
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

        return joiner.toString();
    }

    static {
        // Initialize and register the discriminator mappings.
        Map<String, Class<?>> mappings = new HashMap<String, Class<?>>();
        mappings.put("CreerPeriodeRequest", CreerPeriodeRequest.class);
        JSON.registerDiscriminator(CreerPeriodeRequest.class, "typeEspace", mappings);
    }
}
