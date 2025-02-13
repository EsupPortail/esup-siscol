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

package org.esupportail.referentiel.pcscol.api;


import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import org.esupportail.referentiel.pcscol.invoker.ApiClient;
import org.esupportail.referentiel.pcscol.invoker.ApiException;
import org.esupportail.referentiel.pcscol.invoker.ApiResponse;
import org.esupportail.referentiel.pcscol.odf.model.ExporterResponse;
import org.esupportail.referentiel.pcscol.odf.model.ImporterBase64Request;
import org.esupportail.referentiel.pcscol.odf.model.ImporterExistantVersEspaceRequest;
import org.esupportail.referentiel.pcscol.odf.model.MaquetteFormatsEnseignementStructure;
import org.esupportail.referentiel.pcscol.odf.model.MaquetteStructure;
import org.esupportail.referentiel.pcscol.odf.model.ObjetMaquetteDetail;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@jakarta.annotation.Generated(
        value = "org.openapitools.codegen.languages.JavaClientCodegen",
        date = "2024-07-04T09:22:32.722688+02:00[Europe/Paris]",
        comments = "Generator version: 7.7.0")
public class MaquettesApi {
    private final HttpClient memberVarHttpClient;
    private final ObjectMapper memberVarObjectMapper;
    private final String memberVarBaseUri;
    private final Consumer<HttpRequest.Builder> memberVarInterceptor;
    private final Duration memberVarReadTimeout;
    private final Consumer<HttpResponse<InputStream>> memberVarResponseInterceptor;
    private final Consumer<HttpResponse<String>> memberVarAsyncResponseInterceptor;

    public MaquettesApi() {
        this(new ApiClient());
    }

    public MaquettesApi(ApiClient apiClient) {
        memberVarHttpClient = apiClient.getHttpClient();
        memberVarObjectMapper = apiClient.getObjectMapper();
        memberVarBaseUri = apiClient.getBaseUri();
        memberVarInterceptor = apiClient.getRequestInterceptor();
        memberVarReadTimeout = apiClient.getReadTimeout();
        memberVarResponseInterceptor = apiClient.getResponseInterceptor();
        memberVarAsyncResponseInterceptor = apiClient.getAsyncResponseInterceptor();
    }

    protected ApiException getApiException(String operationId, HttpResponse<InputStream> response)
            throws IOException {
        String body = response.body() == null ? null : new String(response.body().readAllBytes());
        String message = formatExceptionMessage(operationId, response.statusCode(), body);
        return new ApiException(response.statusCode(), message, response.headers(), body);
    }

    private String formatExceptionMessage(String operationId, int statusCode, String body) {
        if (body == null || body.isEmpty()) {
            body = "[no body]";
        }
        return operationId + " call failed with: " + statusCode + " - " + body;
    }

    /**
     * Exporter une maquette au format base 64 Permet d&#39;exporter une maquette au format base 64.
     * Dans l&#39;objectif de pouvoir la réimporter dans un autre espace de travail.
     *
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param id Identifiant d&#39;une ressource (required)
     * @return ExporterResponse
     * @throws ApiException if fails to make API call
     */
    public ExporterResponse exporter(String codeStructure, UUID id) throws ApiException {
        ApiResponse<ExporterResponse> localVarResponse = exporterWithHttpInfo(codeStructure, id);
        return localVarResponse.getData();
    }

    /**
     * Exporter une maquette au format base 64 Permet d&#39;exporter une maquette au format base 64.
     * Dans l&#39;objectif de pouvoir la réimporter dans un autre espace de travail.
     *
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param id Identifiant d&#39;une ressource (required)
     * @return ApiResponse&lt;ExporterResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<ExporterResponse> exporterWithHttpInfo(String codeStructure, UUID id)
            throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = exporterRequestBuilder(codeStructure, id);
        try {
            HttpResponse<InputStream> localVarResponse =
                    memberVarHttpClient.send(
                            localVarRequestBuilder.build(),
                            HttpResponse.BodyHandlers.ofInputStream());
            if (memberVarResponseInterceptor != null) {
                memberVarResponseInterceptor.accept(localVarResponse);
            }
            try {
                if (localVarResponse.statusCode() / 100 != 2) {
                    throw getApiException("exporter", localVarResponse);
                }
                return new ApiResponse<ExporterResponse>(
                        localVarResponse.statusCode(),
                        localVarResponse.headers().map(),
                        localVarResponse.body() == null
                                ? null
                                : memberVarObjectMapper.readValue(
                                        localVarResponse.body(),
                                        new TypeReference<
                                                ExporterResponse>() {}) // closes the InputStream
                        );
            } finally {
            }
        } catch (IOException e) {
            throw new ApiException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException(e);
        }
    }

    private HttpRequest.Builder exporterRequestBuilder(String codeStructure, UUID id)
            throws ApiException {
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException(
                    400, "Missing the required parameter 'codeStructure' when calling exporter");
        }
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException(
                    400, "Missing the required parameter 'id' when calling exporter");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath =
                "/etablissement/{codeStructure}/maquette/{id}/exporter"
                        .replace("{codeStructure}", ApiClient.urlEncode(codeStructure.toString()))
                        .replace("{id}", ApiClient.urlEncode(id.toString()));

        localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));

        localVarRequestBuilder.header("Accept", "application/json, json/application");

        localVarRequestBuilder.method("GET", HttpRequest.BodyPublishers.noBody());
        if (memberVarReadTimeout != null) {
            localVarRequestBuilder.timeout(memberVarReadTimeout);
        }
        if (memberVarInterceptor != null) {
            memberVarInterceptor.accept(localVarRequestBuilder);
        }
        return localVarRequestBuilder;
    }

    /**
     * Importer une maquette au format base 64 vers un espace de travail Permet de copier une
     * maquette encodée au format base 64 vers un espace de travail. En réutilisant les objets
     * maquettes déjà présents dans l&#39;espace cible.
     *
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param importerBase64Request L&#39;objet maquette à créer (required)
     * @return ObjetMaquetteDetail
     * @throws ApiException if fails to make API call
     */
    public ObjetMaquetteDetail importerBase64VersEspace(
            String codeStructure, ImporterBase64Request importerBase64Request) throws ApiException {
        ApiResponse<ObjetMaquetteDetail> localVarResponse =
                importerBase64VersEspaceWithHttpInfo(codeStructure, importerBase64Request);
        return localVarResponse.getData();
    }

    /**
     * Importer une maquette au format base 64 vers un espace de travail Permet de copier une
     * maquette encodée au format base 64 vers un espace de travail. En réutilisant les objets
     * maquettes déjà présents dans l&#39;espace cible.
     *
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param importerBase64Request L&#39;objet maquette à créer (required)
     * @return ApiResponse&lt;ObjetMaquetteDetail&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<ObjetMaquetteDetail> importerBase64VersEspaceWithHttpInfo(
            String codeStructure, ImporterBase64Request importerBase64Request) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder =
                importerBase64VersEspaceRequestBuilder(codeStructure, importerBase64Request);
        try {
            HttpResponse<InputStream> localVarResponse =
                    memberVarHttpClient.send(
                            localVarRequestBuilder.build(),
                            HttpResponse.BodyHandlers.ofInputStream());
            if (memberVarResponseInterceptor != null) {
                memberVarResponseInterceptor.accept(localVarResponse);
            }
            try {
                if (localVarResponse.statusCode() / 100 != 2) {
                    throw getApiException("importerBase64VersEspace", localVarResponse);
                }
                return new ApiResponse<ObjetMaquetteDetail>(
                        localVarResponse.statusCode(),
                        localVarResponse.headers().map(),
                        localVarResponse.body() == null
                                ? null
                                : memberVarObjectMapper.readValue(
                                        localVarResponse.body(),
                                        new TypeReference<
                                                ObjetMaquetteDetail>() {}) // closes the InputStream
                        );
            } finally {
            }
        } catch (IOException e) {
            throw new ApiException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException(e);
        }
    }

    private HttpRequest.Builder importerBase64VersEspaceRequestBuilder(
            String codeStructure, ImporterBase64Request importerBase64Request) throws ApiException {
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException(
                    400,
                    "Missing the required parameter 'codeStructure' when calling"
                            + " importerBase64VersEspace");
        }
        // verify the required parameter 'importerBase64Request' is set
        if (importerBase64Request == null) {
            throw new ApiException(
                    400,
                    "Missing the required parameter 'importerBase64Request' when calling"
                            + " importerBase64VersEspace");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath =
                "/etablissement/{codeStructure}/maquette/importerBase64VersEspace"
                        .replace("{codeStructure}", ApiClient.urlEncode(codeStructure.toString()));

        localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));

        localVarRequestBuilder.header("Content-Type", "application/json");
        localVarRequestBuilder.header("Accept", "application/json, json/application");

        try {
            byte[] localVarPostBody =
                    memberVarObjectMapper.writeValueAsBytes(importerBase64Request);
            localVarRequestBuilder.method(
                    "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
        } catch (IOException e) {
            throw new ApiException(e);
        }
        if (memberVarReadTimeout != null) {
            localVarRequestBuilder.timeout(memberVarReadTimeout);
        }
        if (memberVarInterceptor != null) {
            memberVarInterceptor.accept(localVarRequestBuilder);
        }
        return localVarRequestBuilder;
    }

    /**
     * Importer une maquette existante vers un nouvel espace de travail Permet de copier une
     * maquette existante vers un nouvel espace de travail. En réutilisant les objets maquettes déjà
     * présents dans l&#39;espace cible.
     *
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param id Identifiant d&#39;une ressource (required)
     * @param importerExistantVersEspaceRequest L&#39;objet maquette à créer (required)
     * @return ObjetMaquetteDetail
     * @throws ApiException if fails to make API call
     */
    public ObjetMaquetteDetail importerExistantVersEspace(
            String codeStructure,
            UUID id,
            ImporterExistantVersEspaceRequest importerExistantVersEspaceRequest)
            throws ApiException {
        ApiResponse<ObjetMaquetteDetail> localVarResponse =
                importerExistantVersEspaceWithHttpInfo(
                        codeStructure, id, importerExistantVersEspaceRequest);
        return localVarResponse.getData();
    }

    /**
     * Importer une maquette existante vers un nouvel espace de travail Permet de copier une
     * maquette existante vers un nouvel espace de travail. En réutilisant les objets maquettes déjà
     * présents dans l&#39;espace cible.
     *
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param id Identifiant d&#39;une ressource (required)
     * @param importerExistantVersEspaceRequest L&#39;objet maquette à créer (required)
     * @return ApiResponse&lt;ObjetMaquetteDetail&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<ObjetMaquetteDetail> importerExistantVersEspaceWithHttpInfo(
            String codeStructure,
            UUID id,
            ImporterExistantVersEspaceRequest importerExistantVersEspaceRequest)
            throws ApiException {
        HttpRequest.Builder localVarRequestBuilder =
                importerExistantVersEspaceRequestBuilder(
                        codeStructure, id, importerExistantVersEspaceRequest);
        try {
            HttpResponse<InputStream> localVarResponse =
                    memberVarHttpClient.send(
                            localVarRequestBuilder.build(),
                            HttpResponse.BodyHandlers.ofInputStream());
            if (memberVarResponseInterceptor != null) {
                memberVarResponseInterceptor.accept(localVarResponse);
            }
            try {
                if (localVarResponse.statusCode() / 100 != 2) {
                    throw getApiException("importerExistantVersEspace", localVarResponse);
                }
                return new ApiResponse<ObjetMaquetteDetail>(
                        localVarResponse.statusCode(),
                        localVarResponse.headers().map(),
                        localVarResponse.body() == null
                                ? null
                                : memberVarObjectMapper.readValue(
                                        localVarResponse.body(),
                                        new TypeReference<
                                                ObjetMaquetteDetail>() {}) // closes the InputStream
                        );
            } finally {
            }
        } catch (IOException e) {
            throw new ApiException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException(e);
        }
    }

    private HttpRequest.Builder importerExistantVersEspaceRequestBuilder(
            String codeStructure,
            UUID id,
            ImporterExistantVersEspaceRequest importerExistantVersEspaceRequest)
            throws ApiException {
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException(
                    400,
                    "Missing the required parameter 'codeStructure' when calling"
                            + " importerExistantVersEspace");
        }
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException(
                    400,
                    "Missing the required parameter 'id' when calling importerExistantVersEspace");
        }
        // verify the required parameter 'importerExistantVersEspaceRequest' is set
        if (importerExistantVersEspaceRequest == null) {
            throw new ApiException(
                    400,
                    "Missing the required parameter 'importerExistantVersEspaceRequest' when"
                            + " calling importerExistantVersEspace");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath =
                "/etablissement/{codeStructure}/maquette/{id}/importerExistantVersEspace"
                        .replace("{codeStructure}", ApiClient.urlEncode(codeStructure.toString()))
                        .replace("{id}", ApiClient.urlEncode(id.toString()));

        localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));

        localVarRequestBuilder.header("Content-Type", "application/json");
        localVarRequestBuilder.header("Accept", "application/json, json/application");

        try {
            byte[] localVarPostBody =
                    memberVarObjectMapper.writeValueAsBytes(importerExistantVersEspaceRequest);
            localVarRequestBuilder.method(
                    "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
        } catch (IOException e) {
            throw new ApiException(e);
        }
        if (memberVarReadTimeout != null) {
            localVarRequestBuilder.timeout(memberVarReadTimeout);
        }
        if (memberVarInterceptor != null) {
            memberVarInterceptor.accept(localVarRequestBuilder);
        }
        return localVarRequestBuilder;
    }

    /**
     * Lecture des formats d&#39;enseignement structurés sous forme de maquette Lecture des formats
     * d&#39;enseignement structurés sous forme de maquette
     *
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param codePeriode (required)
     * @param ids Liste des identifiants d&#39;une ressource (required)
     * @return List&lt;MaquetteFormatsEnseignementStructure&gt;
     * @throws ApiException if fails to make API call
     */
    public List<MaquetteFormatsEnseignementStructure> lireFormatsEnseignement(
            String codeStructure, String codePeriode, List<UUID> ids) throws ApiException {
        ApiResponse<List<MaquetteFormatsEnseignementStructure>> localVarResponse =
                lireFormatsEnseignementWithHttpInfo(codeStructure, codePeriode, ids);
        return localVarResponse.getData();
    }

    /**
     * Lecture des formats d&#39;enseignement structurés sous forme de maquette Lecture des formats
     * d&#39;enseignement structurés sous forme de maquette
     *
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param codePeriode (required)
     * @param ids Liste des identifiants d&#39;une ressource (required)
     * @return ApiResponse&lt;List&lt;MaquetteFormatsEnseignementStructure&gt;&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<List<MaquetteFormatsEnseignementStructure>>
            lireFormatsEnseignementWithHttpInfo(
                    String codeStructure, String codePeriode, List<UUID> ids) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder =
                lireFormatsEnseignementRequestBuilder(codeStructure, codePeriode, ids);
        try {
            HttpResponse<InputStream> localVarResponse =
                    memberVarHttpClient.send(
                            localVarRequestBuilder.build(),
                            HttpResponse.BodyHandlers.ofInputStream());
            if (memberVarResponseInterceptor != null) {
                memberVarResponseInterceptor.accept(localVarResponse);
            }
            try {
                if (localVarResponse.statusCode() / 100 != 2) {
                    throw getApiException("lireFormatsEnseignement", localVarResponse);
                }
                return new ApiResponse<List<MaquetteFormatsEnseignementStructure>>(
                        localVarResponse.statusCode(),
                        localVarResponse.headers().map(),
                        localVarResponse.body() == null
                                ? null
                                : memberVarObjectMapper.readValue(
                                        localVarResponse.body(),
                                        new TypeReference<
                                                List<
                                                        MaquetteFormatsEnseignementStructure>>() {}) // closes the InputStream
                        );
            } finally {
            }
        } catch (IOException e) {
            throw new ApiException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException(e);
        }
    }

    private HttpRequest.Builder lireFormatsEnseignementRequestBuilder(
            String codeStructure, String codePeriode, List<UUID> ids) throws ApiException {
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException(
                    400,
                    "Missing the required parameter 'codeStructure' when calling"
                            + " lireFormatsEnseignement");
        }
        // verify the required parameter 'codePeriode' is set
        if (codePeriode == null) {
            throw new ApiException(
                    400,
                    "Missing the required parameter 'codePeriode' when calling"
                            + " lireFormatsEnseignement");
        }
        // verify the required parameter 'ids' is set
        if (ids == null) {
            throw new ApiException(
                    400,
                    "Missing the required parameter 'ids' when calling lireFormatsEnseignement");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath =
                "/etablissement/{codeStructure}/periode/{codePeriode}/racines/{ids}/lireFormatsEnseignement"
                        .replace("{codeStructure}", ApiClient.urlEncode(codeStructure.toString()))
                        .replace("{codePeriode}", ApiClient.urlEncode(codePeriode.toString()))
                        .replace("{ids}", ApiClient.urlEncode(ids.toString()));

        localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));

        localVarRequestBuilder.header("Accept", "application/json");

        localVarRequestBuilder.method("GET", HttpRequest.BodyPublishers.noBody());
        if (memberVarReadTimeout != null) {
            localVarRequestBuilder.timeout(memberVarReadTimeout);
        }
        if (memberVarInterceptor != null) {
            memberVarInterceptor.accept(localVarRequestBuilder);
        }
        return localVarRequestBuilder;
    }

    /**
     * Lecture de la structure d&#39;une maquette Lecture de la structure de la maquette
     *
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param id Identifiant d&#39;une ressource (required)
     * @return MaquetteStructure
     * @throws ApiException if fails to make API call
     */
    public MaquetteStructure lireStructureMaquette(String codeStructure, UUID id)
            throws ApiException {
        ApiResponse<MaquetteStructure> localVarResponse =
                lireStructureMaquetteWithHttpInfo(codeStructure, id);
        return localVarResponse.getData();
    }

    /**
     * Lecture de la structure d&#39;une maquette Lecture de la structure de la maquette
     *
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param id Identifiant d&#39;une ressource (required)
     * @return ApiResponse&lt;MaquetteStructure&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<MaquetteStructure> lireStructureMaquetteWithHttpInfo(
            String codeStructure, UUID id) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder =
                lireStructureMaquetteRequestBuilder(codeStructure, id);
        try {
            HttpResponse<InputStream> localVarResponse =
                    memberVarHttpClient.send(
                            localVarRequestBuilder.build(),
                            HttpResponse.BodyHandlers.ofInputStream());
            if (memberVarResponseInterceptor != null) {
                memberVarResponseInterceptor.accept(localVarResponse);
            }
            try {
                if (localVarResponse.statusCode() / 100 != 2) {
                    throw getApiException("lireStructureMaquette", localVarResponse);
                }
                return new ApiResponse<MaquetteStructure>(
                        localVarResponse.statusCode(),
                        localVarResponse.headers().map(),
                        localVarResponse.body() == null
                                ? null
                                : memberVarObjectMapper.readValue(
                                        localVarResponse.body(),
                                        new TypeReference<
                                                MaquetteStructure>() {}) // closes the InputStream
                        );
            } finally {
            }
        } catch (IOException e) {
            throw new ApiException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException(e);
        }
    }

    private HttpRequest.Builder lireStructureMaquetteRequestBuilder(String codeStructure, UUID id)
            throws ApiException {
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException(
                    400,
                    "Missing the required parameter 'codeStructure' when calling"
                            + " lireStructureMaquette");
        }
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException(
                    400, "Missing the required parameter 'id' when calling lireStructureMaquette");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath =
                "/etablissement/{codeStructure}/maquette/{id}"
                        .replace("{codeStructure}", ApiClient.urlEncode(codeStructure.toString()))
                        .replace("{id}", ApiClient.urlEncode(id.toString()));

        localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));

        localVarRequestBuilder.header("Accept", "application/json");

        localVarRequestBuilder.method("GET", HttpRequest.BodyPublishers.noBody());
        if (memberVarReadTimeout != null) {
            localVarRequestBuilder.timeout(memberVarReadTimeout);
        }
        if (memberVarInterceptor != null) {
            memberVarInterceptor.accept(localVarRequestBuilder);
        }
        return localVarRequestBuilder;
    }
}
