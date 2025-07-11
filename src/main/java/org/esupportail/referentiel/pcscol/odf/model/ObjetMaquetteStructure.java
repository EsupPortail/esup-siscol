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

/** ObjetMaquetteStructure */
@JsonPropertyOrder({
    ObjetMaquetteStructure.JSON_PROPERTY_ID,
    ObjetMaquetteStructure.JSON_PROPERTY_CONTEXTE_ID,
    ObjetMaquetteStructure.JSON_PROPERTY_CLASSE,
    ObjetMaquetteStructure.JSON_PROPERTY_TYPE,
    ObjetMaquetteStructure.JSON_PROPERTY_CODE,
    ObjetMaquetteStructure.JSON_PROPERTY_LIBELLE,
    ObjetMaquetteStructure.JSON_PROPERTY_PIA,
    ObjetMaquetteStructure.JSON_PROPERTY_VALIDE,
    ObjetMaquetteStructure.JSON_PROPERTY_MUTUALISE,
    ObjetMaquetteStructure.JSON_PROPERTY_ECTS,
    ObjetMaquetteStructure.JSON_PROPERTY_ENFANTS
})
@jakarta.annotation.Generated(
        value = "org.openapitools.codegen.languages.JavaClientCodegen",
        date = "2024-07-04T09:22:32.722688+02:00[Europe/Paris]",
        comments = "Generator version: 7.7.0")
public class ObjetMaquetteStructure {
    public static final String JSON_PROPERTY_ID = "id";
    private UUID id;

    public static final String JSON_PROPERTY_CONTEXTE_ID = "contexteId";
    private UUID contexteId;

    public static final String JSON_PROPERTY_CLASSE = "classe";
    private String classe;

    public static final String JSON_PROPERTY_TYPE = "type";
    private String type;

    public static final String JSON_PROPERTY_CODE = "code";
    private String code;

    public static final String JSON_PROPERTY_LIBELLE = "libelle";
    private String libelle;

    public static final String JSON_PROPERTY_PIA = "pia";
    private PointInscriptionAdministrative pia;

    public static final String JSON_PROPERTY_VALIDE = "valide";
    private Boolean valide = false;

    public static final String JSON_PROPERTY_MUTUALISE = "mutualise";
    private Boolean mutualise;

    public static final String JSON_PROPERTY_ECTS = "ects";
    private Double ects;

    public static final String JSON_PROPERTY_ENFANTS = "enfants";
    private List<EnfantsStructure> enfants = new ArrayList<>();

    public ObjetMaquetteStructure() {}

    public ObjetMaquetteStructure id(UUID id) {
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

    public ObjetMaquetteStructure contexteId(UUID contexteId) {
        this.contexteId = contexteId;
        return this;
    }

    /**
     * Identifiant de ressource
     *
     * @return contexteId
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_CONTEXTE_ID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public UUID getContexteId() {
        return contexteId;
    }

    @JsonProperty(JSON_PROPERTY_CONTEXTE_ID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setContexteId(UUID contexteId) {
        this.contexteId = contexteId;
    }

    public ObjetMaquetteStructure classe(String classe) {
        this.classe = classe;
        return this;
    }

    /**
     * Classe de l&#39;objet maquette (F, OF ou G)
     *
     * @return classe
     */
    @jakarta.annotation.Nonnull
    @JsonProperty(JSON_PROPERTY_CLASSE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public String getClasse() {
        return classe;
    }

    @JsonProperty(JSON_PROPERTY_CLASSE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setClasse(String classe) {
        this.classe = classe;
    }

    public ObjetMaquetteStructure type(String type) {
        this.type = type;
        return this;
    }

    /**
     * Type de l&#39;objet maquette si OF (UE, EC, etc.)
     *
     * @return type
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_TYPE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getType() {
        return type;
    }

    @JsonProperty(JSON_PROPERTY_TYPE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setType(String type) {
        this.type = type;
    }

    public ObjetMaquetteStructure code(String code) {
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

    public ObjetMaquetteStructure libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    /**
     * Libellé d&#39;un objet maquette
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

    public ObjetMaquetteStructure pia(PointInscriptionAdministrative pia) {
        this.pia = pia;
        return this;
    }

    /**
     * Get pia
     *
     * @return pia
     */
    @jakarta.annotation.Nonnull
    @JsonProperty(JSON_PROPERTY_PIA)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public PointInscriptionAdministrative getPia() {
        return pia;
    }

    @JsonProperty(JSON_PROPERTY_PIA)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setPia(PointInscriptionAdministrative pia) {
        this.pia = pia;
    }

    public ObjetMaquetteStructure valide(Boolean valide) {
        this.valide = valide;
        return this;
    }

    /**
     * Objet maquette validé
     *
     * @return valide
     */
    @jakarta.annotation.Nonnull
    @JsonProperty(JSON_PROPERTY_VALIDE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public Boolean getValide() {
        return valide;
    }

    @JsonProperty(JSON_PROPERTY_VALIDE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setValide(Boolean valide) {
        this.valide = valide;
    }

    public ObjetMaquetteStructure mutualise(Boolean mutualise) {
        this.mutualise = mutualise;
        return this;
    }

    /**
     * Témoin mutualisé
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

    public ObjetMaquetteStructure ects(Double ects) {
        this.ects = ects;
        return this;
    }

    /**
     * Crédits ECTS (European Credits Transfer System) Permet d&#39;estimer le temps d&#39;une
     * formation. Le crédit ECTS est proportionnel au volume de travail à fournir par l’apprenant et
     * permet de mesurer le niveau d’études atteint. Règles de gestion: * partie entière de 4
     * chiffres max, décimale de 2 chiffres max minimum: 0 maximum: 10000
     *
     * @return ects
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_ECTS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getEcts() {
        return ects;
    }

    @JsonProperty(JSON_PROPERTY_ECTS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setEcts(Double ects) {
        this.ects = ects;
    }

    public ObjetMaquetteStructure enfants(List<EnfantsStructure> enfants) {
        this.enfants = enfants;
        return this;
    }

    public ObjetMaquetteStructure addEnfantsItem(EnfantsStructure enfantsItem) {
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
    public List<EnfantsStructure> getEnfants() {
        return enfants;
    }

    @JsonProperty(JSON_PROPERTY_ENFANTS)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setEnfants(List<EnfantsStructure> enfants) {
        this.enfants = enfants;
    }

    /** Return true if this ObjetMaquetteStructure object is equal to o. */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ObjetMaquetteStructure objetMaquetteStructure = (ObjetMaquetteStructure) o;
        return Objects.equals(this.id, objetMaquetteStructure.id)
                && Objects.equals(this.contexteId, objetMaquetteStructure.contexteId)
                && Objects.equals(this.classe, objetMaquetteStructure.classe)
                && Objects.equals(this.type, objetMaquetteStructure.type)
                && Objects.equals(this.code, objetMaquetteStructure.code)
                && Objects.equals(this.libelle, objetMaquetteStructure.libelle)
                && Objects.equals(this.pia, objetMaquetteStructure.pia)
                && Objects.equals(this.valide, objetMaquetteStructure.valide)
                && Objects.equals(this.mutualise, objetMaquetteStructure.mutualise)
                && Objects.equals(this.ects, objetMaquetteStructure.ects)
                && Objects.equals(this.enfants, objetMaquetteStructure.enfants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id, contexteId, classe, type, code, libelle, pia, valide, mutualise, ects, enfants);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ObjetMaquetteStructure {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    contexteId: ").append(toIndentedString(contexteId)).append("\n");
        sb.append("    classe: ").append(toIndentedString(classe)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    code: ").append(toIndentedString(code)).append("\n");
        sb.append("    libelle: ").append(toIndentedString(libelle)).append("\n");
        sb.append("    pia: ").append(toIndentedString(pia)).append("\n");
        sb.append("    valide: ").append(toIndentedString(valide)).append("\n");
        sb.append("    mutualise: ").append(toIndentedString(mutualise)).append("\n");
        sb.append("    ects: ").append(toIndentedString(ects)).append("\n");
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

        // add `contexteId` to the URL query string
        if (getContexteId() != null) {
            joiner.add(
                    String.format(
                            "%scontexteId%s=%s",
                            prefix,
                            suffix,
                            URLEncoder.encode(
                                            ApiClient.valueToString(getContexteId()),
                                            StandardCharsets.UTF_8)
                                    .replaceAll("\\+", "%20")));
        }

        // add `classe` to the URL query string
        if (getClasse() != null) {
            joiner.add(
                    String.format(
                            "%sclasse%s=%s",
                            prefix,
                            suffix,
                            URLEncoder.encode(
                                            ApiClient.valueToString(getClasse()),
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

        // add `pia` to the URL query string
        if (getPia() != null) {
            joiner.add(getPia().toUrlQueryString(prefix + "pia" + suffix));
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

        // add `ects` to the URL query string
        if (getEcts() != null) {
            joiner.add(
                    String.format(
                            "%sects%s=%s",
                            prefix,
                            suffix,
                            URLEncoder.encode(
                                            ApiClient.valueToString(getEcts()),
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

        return joiner.toString();
    }
}
