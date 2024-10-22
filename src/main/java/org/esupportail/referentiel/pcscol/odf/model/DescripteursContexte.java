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

/** Descripteurs du contexte */
@JsonPropertyOrder({DescripteursContexte.JSON_PROPERTY_TYPE_OBJET_MAQUETTE})
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
    @JsonSubTypes.Type(value = DescripteursFormationContexte.class, name = "FORMATION"),
    @JsonSubTypes.Type(value = DescripteursGroupementContexte.class, name = "GROUPEMENT"),
    @JsonSubTypes.Type(value = DescripteursObjetFormationContexte.class, name = "OBJET-FORMATION"),
})
public class DescripteursContexte {
    public static final String JSON_PROPERTY_TYPE_OBJET_MAQUETTE = "typeObjetMaquette";
    private TypeObjetMaquette typeObjetMaquette;

    public DescripteursContexte() {}

    public DescripteursContexte typeObjetMaquette(TypeObjetMaquette typeObjetMaquette) {
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

    /** Return true if this DescripteursContexte object is equal to o. */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DescripteursContexte descripteursContexte = (DescripteursContexte) o;
        return Objects.equals(this.typeObjetMaquette, descripteursContexte.typeObjetMaquette);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeObjetMaquette);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DescripteursContexte {\n");
        sb.append("    typeObjetMaquette: ")
                .append(toIndentedString(typeObjetMaquette))
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

        return joiner.toString();
    }

    static {
        // Initialize and register the discriminator mappings.
        Map<String, Class<?>> mappings = new HashMap<String, Class<?>>();
        mappings.put("FORMATION", DescripteursFormationContexte.class);
        mappings.put("GROUPEMENT", DescripteursGroupementContexte.class);
        mappings.put("OBJET-FORMATION", DescripteursObjetFormationContexte.class);
        mappings.put("DescripteursContexte", DescripteursContexte.class);
        JSON.registerDiscriminator(DescripteursContexte.class, "typeObjetMaquette", mappings);
    }
}