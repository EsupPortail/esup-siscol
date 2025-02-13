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
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/** CreerObjetFormationRequest */
@JsonPropertyOrder({})
@jakarta.annotation.Generated(
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
public class CreerObjetFormationRequest extends CreerObjetMaquetteRequest {
    public CreerObjetFormationRequest() {}

    @Override
    public CreerObjetFormationRequest espaceId(UUID espaceId) {
        this.setEspaceId(espaceId);
        return this;
    }

    @Override
    public CreerObjetFormationRequest typeObjetMaquette(TypeObjetMaquette typeObjetMaquette) {
        this.setTypeObjetMaquette(typeObjetMaquette);
        return this;
    }

    @Override
    public CreerObjetFormationRequest code(String code) {
        this.setCode(code);
        return this;
    }

    @Override
    public CreerObjetFormationRequest mutualise(Boolean mutualise) {
        this.setMutualise(mutualise);
        return this;
    }

    @Override
    public CreerObjetFormationRequest descripteursObjetMaquette(
            DescripteursObjetMaquetteRequest descripteursObjetMaquette) {
        this.setDescripteursObjetMaquette(descripteursObjetMaquette);
        return this;
    }

    /** Return true if this CreerObjetFormationRequest object is equal to o. */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreerObjetFormationRequest {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
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

        // add `espaceId` to the URL query string
        if (getEspaceId() != null) {
            joiner.add(
                    String.format(
                            "%sespaceId%s=%s",
                            prefix,
                            suffix,
                            URLEncoder.encode(
                                            ApiClient.valueToString(getEspaceId()),
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

        // add `descripteursObjetMaquette` to the URL query string
        if (getDescripteursObjetMaquette() != null) {
            joiner.add(
                    getDescripteursObjetMaquette()
                            .toUrlQueryString(prefix + "descripteursObjetMaquette" + suffix));
        }

        return joiner.toString();
    }

    static {
        // Initialize and register the discriminator mappings.
        Map<String, Class<?>> mappings = new HashMap<String, Class<?>>();
        mappings.put("CreerObjetFormationRequest", CreerObjetFormationRequest.class);
        JSON.registerDiscriminator(CreerObjetFormationRequest.class, "typeObjetMaquette", mappings);
    }
}
