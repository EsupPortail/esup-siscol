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
import java.util.Date;
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

/** CreerBacASableRequest */
@JsonPropertyOrder({CreerBacASableRequest.JSON_PROPERTY_DATE_CONSOMMATION_NOMENCLATURES})
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
public class CreerBacASableRequest extends CreerEspaceRequest {
    public static final String JSON_PROPERTY_DATE_CONSOMMATION_NOMENCLATURES =
            "dateConsommationNomenclatures";
    private Date dateConsommationNomenclatures;

    public CreerBacASableRequest() {}

    public CreerBacASableRequest dateConsommationNomenclatures(Date dateConsommationNomenclatures) {
        this.dateConsommationNomenclatures = dateConsommationNomenclatures;
        return this;
    }

    /**
     * Date de consommation des nomenclature pour tous les objets maquette appartenant au bac à
     * sable
     *
     * @return dateConsommationNomenclatures
     */
    @jakarta.annotation.Nonnull
    @JsonProperty(JSON_PROPERTY_DATE_CONSOMMATION_NOMENCLATURES)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public Date getDateConsommationNomenclatures() {
        return dateConsommationNomenclatures;
    }

    @JsonProperty(JSON_PROPERTY_DATE_CONSOMMATION_NOMENCLATURES)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setDateConsommationNomenclatures(Date dateConsommationNomenclatures) {
        this.dateConsommationNomenclatures = dateConsommationNomenclatures;
    }

    @Override
    public CreerBacASableRequest code(String code) {
        this.setCode(code);
        return this;
    }

    @Override
    public CreerBacASableRequest libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    @Override
    public CreerBacASableRequest typeEspace(TypeEspace typeEspace) {
        this.setTypeEspace(typeEspace);
        return this;
    }

    /** Return true if this CreerBacASableRequest object is equal to o. */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreerBacASableRequest creerBacASableRequest = (CreerBacASableRequest) o;
        return Objects.equals(
                        this.dateConsommationNomenclatures,
                        creerBacASableRequest.dateConsommationNomenclatures)
                && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateConsommationNomenclatures, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreerBacASableRequest {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    dateConsommationNomenclatures: ")
                .append(toIndentedString(dateConsommationNomenclatures))
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

        // add `dateConsommationNomenclatures` to the URL query string
        if (getDateConsommationNomenclatures() != null) {
            joiner.add(
                    String.format(
                            "%sdateConsommationNomenclatures%s=%s",
                            prefix,
                            suffix,
                            URLEncoder.encode(
                                            ApiClient.valueToString(
                                                    getDateConsommationNomenclatures()),
                                            StandardCharsets.UTF_8)
                                    .replaceAll("\\+", "%20")));
        }

        return joiner.toString();
    }

    static {
        // Initialize and register the discriminator mappings.
        Map<String, Class<?>> mappings = new HashMap<String, Class<?>>();
        mappings.put("CreerBacASableRequest", CreerBacASableRequest.class);
        JSON.registerDiscriminator(CreerBacASableRequest.class, "typeEspace", mappings);
    }
}
