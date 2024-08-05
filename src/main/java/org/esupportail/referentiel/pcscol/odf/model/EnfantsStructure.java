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
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import org.esupportail.referentiel.pcscol.invoker.ApiClient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/** EnfantsStructure */
@JsonPropertyOrder({
    EnfantsStructure.JSON_PROPERTY_ID,
    EnfantsStructure.JSON_PROPERTY_OBLIGATOIRE,
    EnfantsStructure.JSON_PROPERTY_OBJET_MAQUETTE
})
@javax.annotation.Generated(
        value = "org.openapitools.codegen.languages.JavaClientCodegen",
        date = "2024-07-04T09:22:32.722688+02:00[Europe/Paris]",
        comments = "Generator version: 7.7.0")
public class EnfantsStructure {
    public static final String JSON_PROPERTY_ID = "id";
    private UUID id;

    public static final String JSON_PROPERTY_OBLIGATOIRE = "obligatoire";
    private Boolean obligatoire;

    public static final String JSON_PROPERTY_OBJET_MAQUETTE = "objetMaquette";
    private ObjetMaquetteStructure objetMaquette;

    public EnfantsStructure() {}

    public EnfantsStructure id(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Identifiant de ressource
     *
     * @return id
     */
    @javax.annotation.Nonnull
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

    public EnfantsStructure obligatoire(Boolean obligatoire) {
        this.obligatoire = obligatoire;
        return this;
    }

    /**
     * nature du lien entre le père et l&#39;enfant
     *
     * @return obligatoire
     */
    @javax.annotation.Nonnull
    @JsonProperty(JSON_PROPERTY_OBLIGATOIRE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public Boolean getObligatoire() {
        return obligatoire;
    }

    @JsonProperty(JSON_PROPERTY_OBLIGATOIRE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setObligatoire(Boolean obligatoire) {
        this.obligatoire = obligatoire;
    }

    public EnfantsStructure objetMaquette(ObjetMaquetteStructure objetMaquette) {
        this.objetMaquette = objetMaquette;
        return this;
    }

    /**
     * Get objetMaquette
     *
     * @return objetMaquette
     */
    @javax.annotation.Nonnull
    @JsonProperty(JSON_PROPERTY_OBJET_MAQUETTE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public ObjetMaquetteStructure getObjetMaquette() {
        return objetMaquette;
    }

    @JsonProperty(JSON_PROPERTY_OBJET_MAQUETTE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setObjetMaquette(ObjetMaquetteStructure objetMaquette) {
        this.objetMaquette = objetMaquette;
    }

    /** Return true if this EnfantsStructure object is equal to o. */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EnfantsStructure enfantsStructure = (EnfantsStructure) o;
        return Objects.equals(this.id, enfantsStructure.id)
                && Objects.equals(this.obligatoire, enfantsStructure.obligatoire)
                && Objects.equals(this.objetMaquette, enfantsStructure.objetMaquette);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, obligatoire, objetMaquette);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EnfantsStructure {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    obligatoire: ").append(toIndentedString(obligatoire)).append("\n");
        sb.append("    objetMaquette: ").append(toIndentedString(objetMaquette)).append("\n");
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

        // add `obligatoire` to the URL query string
        if (getObligatoire() != null) {
            joiner.add(
                    String.format(
                            "%sobligatoire%s=%s",
                            prefix,
                            suffix,
                            URLEncoder.encode(
                                            ApiClient.valueToString(getObligatoire()),
                                            StandardCharsets.UTF_8)
                                    .replaceAll("\\+", "%20")));
        }

        // add `objetMaquette` to the URL query string
        if (getObjetMaquette() != null) {
            joiner.add(getObjetMaquette().toUrlQueryString(prefix + "objetMaquette" + suffix));
        }

        return joiner.toString();
    }
}
