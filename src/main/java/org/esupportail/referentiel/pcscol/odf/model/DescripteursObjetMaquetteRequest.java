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
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/** Descripteurs communs des objets maquette */
@JsonPropertyOrder({
    DescripteursObjetMaquetteRequest.JSON_PROPERTY_LIBELLE,
    DescripteursObjetMaquetteRequest.JSON_PROPERTY_LIBELLE_LONG
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
    @JsonSubTypes.Type(value = DescripteursFormationRequest.class, name = "FORMATION"),
    @JsonSubTypes.Type(value = DescripteursGroupementRequest.class, name = "GROUPEMENT"),
    @JsonSubTypes.Type(value = DescripteursObjetFormationRequest.class, name = "OBJET-FORMATION"),
})
public class DescripteursObjetMaquetteRequest {
    public static final String JSON_PROPERTY_LIBELLE = "libelle";
    private String libelle;

    public static final String JSON_PROPERTY_LIBELLE_LONG = "libelleLong";
    private String libelleLong;

    public DescripteursObjetMaquetteRequest() {}

    public DescripteursObjetMaquetteRequest libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    /**
     * Get libelle
     *
     * @return libelle
     */
    @javax.annotation.Nonnull
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

    public DescripteursObjetMaquetteRequest libelleLong(String libelleLong) {
        this.libelleLong = libelleLong;
        return this;
    }

    /**
     * Get libelleLong
     *
     * @return libelleLong
     */
    @javax.annotation.Nonnull
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

    /** Return true if this DescripteursObjetMaquetteRequest object is equal to o. */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DescripteursObjetMaquetteRequest descripteursObjetMaquetteRequest =
                (DescripteursObjetMaquetteRequest) o;
        return Objects.equals(this.libelle, descripteursObjetMaquetteRequest.libelle)
                && Objects.equals(this.libelleLong, descripteursObjetMaquetteRequest.libelleLong);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libelle, libelleLong);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DescripteursObjetMaquetteRequest {\n");
        sb.append("    libelle: ").append(toIndentedString(libelle)).append("\n");
        sb.append("    libelleLong: ").append(toIndentedString(libelleLong)).append("\n");
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

        return joiner.toString();
    }

    static {
        // Initialize and register the discriminator mappings.
        Map<String, Class<?>> mappings = new HashMap<String, Class<?>>();
        mappings.put("FORMATION", DescripteursFormationRequest.class);
        mappings.put("GROUPEMENT", DescripteursGroupementRequest.class);
        mappings.put("OBJET-FORMATION", DescripteursObjetFormationRequest.class);
        mappings.put("DescripteursObjetMaquetteRequest", DescripteursObjetMaquetteRequest.class);
        JSON.registerDiscriminator(
                DescripteursObjetMaquetteRequest.class, "typeObjetMaquette", mappings);
    }
}
