/*
 * CHC Externe v1 - API Externe choix du cursus
 * ### Introduction L’API répertorie l'ensemble des services et des opérations disponibles dans le module CHC (Choix du Cursus).  ### Authentification/autorisation obligatoire Pour tout appel à une opération, vous devez être authentifié/autorisé à l’aide d’un token JWT. Ainsi, chaque requête HTTP doit contenir un token valide dans le header HTTP `Authorization`. #### Répertoire de partage contenant la documentation décrivant l'authentification Pégase https://share.pc-scol.fr/f/4487c726ade84022ae16/?dl=1  Le format est `Authorization: Bearer <token-jwt>`. Par exemple : `Authorization: Bearer xxxx.yyyy.zzzz`. Vous pouvez recevoir l'un de ces codes retour si vous n’êtes pas authentifié ou autorisé : * 401 - Unauthorized - Vous n’êtes pas authentifié     * Il n’y a pas de token passé dans le header HTTP `Authorization`     * Le token passé n’est pas au bon format (Bearer <token-jwt>) * 403 - Forbidden - Vous êtes authentifié mais pas autorisé à exécuter cette opération     * La signature du token est incorrecte / n’a pas pu être vérifiée     * Le token est expiré     * Vérifier les droits de l’utilisateur * 500 - Internal Server Error     * Il n’est pas encore actif  ### Type de données Sauf indications spécifiques données au niveau de l'opération, les types de données utilisés dans cette API sont les suivants :  * string - Chaîne de caractères encodée en UTF8 (ex : `Une chaîne de caractère`)    * Dans le cas des descripteurs de type `codeXxx`, seuls les caractères de A à Z, de 0 à 9 et le tiret(-) sont autorisés  * string($date) - Une date sous la forme d'une chaîne de caractères (ex : `2020-02-25`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))  * integer($int64) - Un entier sur 64 bits (de -9 223 372 036 854 775 808 à 9 223 372 036 854 775 807) (ex : `2542`)  * integer($int32) - Un entier sur 32 bits (de –2 147 483 648 à 2 147 483 647) (ex : `2542`)  * number($double) - Un nombre à virgule flottante en double précision  * boolean - Un booléen représenté par `true` ou `false`  ### Code retour      | Code    | Description                                                                                                         |     |---------|---------------------------------------------------------------------------------------------------------------------|     | 200     | L'opération s'est déroulée avec succès                                                                              |     | 201     | L'opération a abouti à la création d'une ressource                                                                  |     | 400     | Un ou des paramètres d'entrée sont erronés                                                                          |     |         | Une erreur fonctionnelle s'est produite                                                                             |     | 404     | La ressource demandée n'est pas trouvée                                                                             |     |         | Remarque : Dans le cas des opérations retournant une liste, on recevra un code 200 avec en résultat une liste vide  |     | 500     | Erreur technique rencontrée par le serveur                                                                          |   ## Notions métiers  ### Objet Maquette (OM) Un objet maquette représente n'importe quel nœud d'un arbre de formation : Formation, objet de formation ou groupement.  Un objet maquette est identifié par le codeChemin (chemin pédagogique), le codePeriode et le codeStructure.  ### Formation Une formation est un arbre dont les nœuds sont des objets de formation et dont la racine est la formation elle-même.  Pour apparaître dans le module CHC, la formation doit être validée dans ODF.  Pour l'utiliser dans les différents actes métiers, il faut que chaque noeud et sa descendance soit ouvert au choix du cursus et qu'au moins une inscription administrative soit validée sur un objet maquette de l’arbre de la formation.  ### Objet formation Un objet de formation est l’un des nœuds de l’arbre de formation : année, semestre, UE, EC, enseignement, etc. (hors groupement).  Pour apparaître dans le Module CHC, un objet de formation doit être validé dans ODF.  ### Groupement Un groupement est une possibilité de structurer et d'organiser une formation. Il peut contenir des objets de formations de tous les types, être lié pour décomposer des objets pères de tous les types, être avec ou sans plage de choix.  ### Plage de choix Une plage de choix permet de contraindre l’apprenant lorsqu’il effectue son CHC dans Pégase. Cette plage de choix est matérialisée par un nombre minimum et un nombre maximum d’objets de formation à sélectionner. La plage de choix est contrôlée au cours du CHC.  ### Groupe Un Groupe est une entité permettant de diviser une population d’étudiants ou d'identifier une population spécifique d’étudiants inscrits administrativement et pédagogiquement.  ### Composition Une composition est une entité permettant de rassembler des groupes. Une composition contient obligatoirement au moins un groupe.  ### Période Une période de mise en œuvre correspond à la période pendant laquelle se déroule la formation.  Elle est le point d’entrée pour chaque acte métier du module CHC.  ### Apprenant Un apprenant est un usager qui suit un cursus et pour lequel des choix pédagogiques devront être réalisés.  ### Inscription L’inscription est l’ensemble des étapes de saisie des données concernant l’apprenant : état-civil, coordonnées, situation précédente, cursus, montant de l’inscription, mode de paiement. Cette saisie peut être faite par le gestionnaire ou l’apprenant.  Elle doit être vérifiée et validée par le gestionnaire. Une inscription n'est prise en compte dans CHC qu'à partir du moment où elle est validée ou annulée.  ### Cursus Le cursus est un arbre (une arborescence) composé d'objets maquette pour lequel des choix pédagogiques doivent être réalisés à chaque période de mise en oeuvre.  Un cursus est défini par le code de l’apprenant, un objet maquette lui-même identifié par le codeChemin (chemin pédagogique), le codePeriode et le codeStructure. Un choix pédagogique est une association entre un objet maquette et un apprenant. On recense parmi les choix pédagogiques des affectations, des aménagements et des acquis capitalisés.  ### Acquis capitalisé Un acquis capitalisé est un objet de formation dont les modalités de contrôle des connaissances sont capitalisables et dont le résultat positif a été obtenu sur une période antérieure. L'acquis capitalisé est créé et stocké dans CHC après délibération de jury du module COC. Il est utilisable sur une période postérieure à son acquisition  ### Chemin pédagogique Un chemin pédagogique identifie le lien d'un objet maquette à un autre objet maquette de sa descendance.  **Exemple** ``` MASTER-RH>MASTER-1>SEMESTRE-1>UE-OPTIONS>ESPAGNOL ```  ### Règles communes pour réaliser un choix de cursus * L’affectation est possible à partir de l'objet maquette porteur du point d'inscription administrative et sur les objets maquette de sa descendance à condition que l'inscription administrative soit validée dans le module INS. * Un choix du cursus sur un objet maquette est réalisable si le témoin ouvertChoixCursus est  à true. * La désaffectation d’un apprenant à un objet maquette provoque sa désaffectation automatique à tous les objets maquette de la descendance. * Pour chaque apprenant, il est possible de réaliser un choix du cursus sur un objet maquette dans un groupement à plage de choix tant que le nombre maximum autorisé (de la plage de choix) n'est pas atteint. La valeur minimum de plage de choix n'est  pas contrôlée. * Un choix pédagogique sur un objet maquette présent plusieurs fois dans un arbre de formation ne peut être réalisée qu’une fois pour un même cursus. * La capacité d’accueil d’un objet maquette n’est pas contrôlée dans l'API car non bloquante. Les capacités d’accueil dépassées sont négatives. * Les aménagements avec prise en compte acquis et aucun ne sont pas décomptés de la capacité d’accueil d’un objet maquette. * L'utilisation d'un acquis capitalisé empêche son affectation et supprime la branche de sa descendance.
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
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Consumer;

import org.esupportail.referentiel.pcscol.chcExterneV1.model.Composition;
import org.esupportail.referentiel.pcscol.chcExterneV1.model.CompositionPourExtraction;
import org.esupportail.referentiel.pcscol.chcExterneV1.model.CursusPourext;
import org.esupportail.referentiel.pcscol.invoker.ApiClient;
import org.esupportail.referentiel.pcscol.invoker.ApiException;
import org.esupportail.referentiel.pcscol.invoker.ApiResponse;
import org.esupportail.referentiel.pcscol.invoker.Configuration;
import org.esupportail.referentiel.pcscol.invoker.Pair;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-04-24T14:22:57.626784935+02:00[Europe/Paris]", comments = "Generator version: 7.12.0")
public class ExtractionsApi {
  private final HttpClient memberVarHttpClient;
  private final ObjectMapper memberVarObjectMapper;
  private final String memberVarBaseUri;
  private final Consumer<HttpRequest.Builder> memberVarInterceptor;
  private final Duration memberVarReadTimeout;
  private final Consumer<HttpResponse<InputStream>> memberVarResponseInterceptor;
  private final Consumer<HttpResponse<String>> memberVarAsyncResponseInterceptor;

  public ExtractionsApi() {
    this(Configuration.getDefaultApiClient());
  }

  public ExtractionsApi(ApiClient apiClient) {
    memberVarHttpClient = apiClient.getHttpClient();
    memberVarObjectMapper = apiClient.getObjectMapper();
    memberVarBaseUri = apiClient.getBaseUri();
    memberVarInterceptor = apiClient.getRequestInterceptor();
    memberVarReadTimeout = apiClient.getReadTimeout();
    memberVarResponseInterceptor = apiClient.getResponseInterceptor();
    memberVarAsyncResponseInterceptor = apiClient.getAsyncResponseInterceptor();
  }

  protected ApiException getApiException(String operationId, HttpResponse<InputStream> response) throws IOException {
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
   * Extraire les compositions et les groupes associés à des formations mises en œuvre sur la période donnée pour un établissement
   * 
   * @param codeStructureEtablissement Le code structure de l&#39;établissement (required)
   * @param codePeriode Le code de la période (required)
   * @param codeFormation Paramètre optionnel qui limite la liste à certaines formations du code fourni (optional)
   * @param uniquementEmploiDuTemps Se limite aux compositions contenant des groupes planifiables pour les emplois du temps (optional)
   * @return List&lt;CompositionPourExtraction&gt;
   * @throws ApiException if fails to make API call
   */
  public List<CompositionPourExtraction> extraireCompositionsPourUnePeriode(String codeStructureEtablissement, String codePeriode, List<String> codeFormation, Boolean uniquementEmploiDuTemps) throws ApiException {
    ApiResponse<List<CompositionPourExtraction>> localVarResponse = extraireCompositionsPourUnePeriodeWithHttpInfo(codeStructureEtablissement, codePeriode, codeFormation, uniquementEmploiDuTemps);
    return localVarResponse.getData();
  }

  /**
   * Extraire les compositions et les groupes associés à des formations mises en œuvre sur la période donnée pour un établissement
   * 
   * @param codeStructureEtablissement Le code structure de l&#39;établissement (required)
   * @param codePeriode Le code de la période (required)
   * @param codeFormation Paramètre optionnel qui limite la liste à certaines formations du code fourni (optional)
   * @param uniquementEmploiDuTemps Se limite aux compositions contenant des groupes planifiables pour les emplois du temps (optional)
   * @return ApiResponse&lt;List&lt;CompositionPourExtraction&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public ApiResponse<List<CompositionPourExtraction>> extraireCompositionsPourUnePeriodeWithHttpInfo(String codeStructureEtablissement, String codePeriode, List<String> codeFormation, Boolean uniquementEmploiDuTemps) throws ApiException {
    HttpRequest.Builder localVarRequestBuilder = extraireCompositionsPourUnePeriodeRequestBuilder(codeStructureEtablissement, codePeriode, codeFormation, uniquementEmploiDuTemps);
    try {
      HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
          localVarRequestBuilder.build(),
          HttpResponse.BodyHandlers.ofInputStream());
      if (memberVarResponseInterceptor != null) {
        memberVarResponseInterceptor.accept(localVarResponse);
      }
      try {
        if (localVarResponse.statusCode()/ 100 != 2) {
          throw getApiException("extraireCompositionsPourUnePeriode", localVarResponse);
        }
        if (localVarResponse.body() == null) {
          return new ApiResponse<List<CompositionPourExtraction>>(
              localVarResponse.statusCode(),
              localVarResponse.headers().map(),
              null
          );
        }

        String responseBody = new String(localVarResponse.body().readAllBytes());
        localVarResponse.body().close();

        return new ApiResponse<List<CompositionPourExtraction>>(
            localVarResponse.statusCode(),
            localVarResponse.headers().map(),
            responseBody.isBlank()? null: memberVarObjectMapper.readValue(responseBody, new TypeReference<List<CompositionPourExtraction>>() {})
        );
      } finally {
      }
    } catch (IOException e) {
      throw new ApiException(e);
    }
    catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new ApiException(e);
    }
  }

  private HttpRequest.Builder extraireCompositionsPourUnePeriodeRequestBuilder(String codeStructureEtablissement, String codePeriode, List<String> codeFormation, Boolean uniquementEmploiDuTemps) throws ApiException {
    // verify the required parameter 'codeStructureEtablissement' is set
    if (codeStructureEtablissement == null) {
      throw new ApiException(400, "Missing the required parameter 'codeStructureEtablissement' when calling extraireCompositionsPourUnePeriode");
    }
    // verify the required parameter 'codePeriode' is set
    if (codePeriode == null) {
      throw new ApiException(400, "Missing the required parameter 'codePeriode' when calling extraireCompositionsPourUnePeriode");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath = "/extractions/{codeStructureEtablissement}/periodes/{codePeriode}/compositions"
        .replace("{codeStructureEtablissement}", ApiClient.urlEncode(codeStructureEtablissement.toString()))
        .replace("{codePeriode}", ApiClient.urlEncode(codePeriode.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "codeFormation";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("multi", "codeFormation", codeFormation));
    localVarQueryParameterBaseName = "uniquementEmploiDuTemps";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("uniquementEmploiDuTemps", uniquementEmploiDuTemps));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

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
   * Extraire les affectations au cursus pour un objet de formation sur la période donnée pour un établissement
   * 
   * @param codeStructure Le code structure de l&#39;établissement (required)
   * @param codePeriode Le code de la période (required)
   * @param codeObjetFormation Le code de l&#39;objet de formation (required)
   * @return List&lt;CursusPourext&gt;
   * @throws ApiException if fails to make API call
   */
  public List<CursusPourext> extraireCursusObjetFormationPeriode(String codeStructure, String codePeriode, String codeObjetFormation) throws ApiException {
    ApiResponse<List<CursusPourext>> localVarResponse = extraireCursusObjetFormationPeriodeWithHttpInfo(codeStructure, codePeriode, codeObjetFormation);
    return localVarResponse.getData();
  }

  /**
   * Extraire les affectations au cursus pour un objet de formation sur la période donnée pour un établissement
   * 
   * @param codeStructure Le code structure de l&#39;établissement (required)
   * @param codePeriode Le code de la période (required)
   * @param codeObjetFormation Le code de l&#39;objet de formation (required)
   * @return ApiResponse&lt;List&lt;CursusPourext&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public ApiResponse<List<CursusPourext>> extraireCursusObjetFormationPeriodeWithHttpInfo(String codeStructure, String codePeriode, String codeObjetFormation) throws ApiException {
    HttpRequest.Builder localVarRequestBuilder = extraireCursusObjetFormationPeriodeRequestBuilder(codeStructure, codePeriode, codeObjetFormation);
    try {
      HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
          localVarRequestBuilder.build(),
          HttpResponse.BodyHandlers.ofInputStream());
      if (memberVarResponseInterceptor != null) {
        memberVarResponseInterceptor.accept(localVarResponse);
      }
      try {
        if (localVarResponse.statusCode()/ 100 != 2) {
          throw getApiException("extraireCursusObjetFormationPeriode", localVarResponse);
        }
        if (localVarResponse.body() == null) {
          return new ApiResponse<List<CursusPourext>>(
              localVarResponse.statusCode(),
              localVarResponse.headers().map(),
              null
          );
        }

        String responseBody = new String(localVarResponse.body().readAllBytes());
        localVarResponse.body().close();

        return new ApiResponse<List<CursusPourext>>(
            localVarResponse.statusCode(),
            localVarResponse.headers().map(),
            responseBody.isBlank()? null: memberVarObjectMapper.readValue(responseBody, new TypeReference<List<CursusPourext>>() {})
        );
      } finally {
      }
    } catch (IOException e) {
      throw new ApiException(e);
    }
    catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new ApiException(e);
    }
  }

  private HttpRequest.Builder extraireCursusObjetFormationPeriodeRequestBuilder(String codeStructure, String codePeriode, String codeObjetFormation) throws ApiException {
    // verify the required parameter 'codeStructure' is set
    if (codeStructure == null) {
      throw new ApiException(400, "Missing the required parameter 'codeStructure' when calling extraireCursusObjetFormationPeriode");
    }
    // verify the required parameter 'codePeriode' is set
    if (codePeriode == null) {
      throw new ApiException(400, "Missing the required parameter 'codePeriode' when calling extraireCursusObjetFormationPeriode");
    }
    // verify the required parameter 'codeObjetFormation' is set
    if (codeObjetFormation == null) {
      throw new ApiException(400, "Missing the required parameter 'codeObjetFormation' when calling extraireCursusObjetFormationPeriode");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath = "/objet-formation/{codeStructure}/{codePeriode}/{codeObjetFormation}/cursus"
        .replace("{codeStructure}", ApiClient.urlEncode(codeStructure.toString()))
        .replace("{codePeriode}", ApiClient.urlEncode(codePeriode.toString()))
        .replace("{codeObjetFormation}", ApiClient.urlEncode(codeObjetFormation.toString()));

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
   * Extraire les affectations au cursus pour un type d&#39;objet de formation sur la période donnée pour un établissement
   * 
   * @param codeStructure Le code structure de l&#39;établissement (required)
   * @param codePeriode Le code de la période (required)
   * @param typeObjet Le code du type de l&#39;objet de formation (required)
   * @return List&lt;CursusPourext&gt;
   * @throws ApiException if fails to make API call
   */
  public List<CursusPourext> extraireCursusTypeObjetFormationPeriode(String codeStructure, String codePeriode, String typeObjet) throws ApiException {
    ApiResponse<List<CursusPourext>> localVarResponse = extraireCursusTypeObjetFormationPeriodeWithHttpInfo(codeStructure, codePeriode, typeObjet);
    return localVarResponse.getData();
  }

  /**
   * Extraire les affectations au cursus pour un type d&#39;objet de formation sur la période donnée pour un établissement
   * 
   * @param codeStructure Le code structure de l&#39;établissement (required)
   * @param codePeriode Le code de la période (required)
   * @param typeObjet Le code du type de l&#39;objet de formation (required)
   * @return ApiResponse&lt;List&lt;CursusPourext&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public ApiResponse<List<CursusPourext>> extraireCursusTypeObjetFormationPeriodeWithHttpInfo(String codeStructure, String codePeriode, String typeObjet) throws ApiException {
    HttpRequest.Builder localVarRequestBuilder = extraireCursusTypeObjetFormationPeriodeRequestBuilder(codeStructure, codePeriode, typeObjet);
    try {
      HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
          localVarRequestBuilder.build(),
          HttpResponse.BodyHandlers.ofInputStream());
      if (memberVarResponseInterceptor != null) {
        memberVarResponseInterceptor.accept(localVarResponse);
      }
      try {
        if (localVarResponse.statusCode()/ 100 != 2) {
          throw getApiException("extraireCursusTypeObjetFormationPeriode", localVarResponse);
        }
        if (localVarResponse.body() == null) {
          return new ApiResponse<List<CursusPourext>>(
              localVarResponse.statusCode(),
              localVarResponse.headers().map(),
              null
          );
        }

        String responseBody = new String(localVarResponse.body().readAllBytes());
        localVarResponse.body().close();

        return new ApiResponse<List<CursusPourext>>(
            localVarResponse.statusCode(),
            localVarResponse.headers().map(),
            responseBody.isBlank()? null: memberVarObjectMapper.readValue(responseBody, new TypeReference<List<CursusPourext>>() {})
        );
      } finally {
      }
    } catch (IOException e) {
      throw new ApiException(e);
    }
    catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new ApiException(e);
    }
  }

  private HttpRequest.Builder extraireCursusTypeObjetFormationPeriodeRequestBuilder(String codeStructure, String codePeriode, String typeObjet) throws ApiException {
    // verify the required parameter 'codeStructure' is set
    if (codeStructure == null) {
      throw new ApiException(400, "Missing the required parameter 'codeStructure' when calling extraireCursusTypeObjetFormationPeriode");
    }
    // verify the required parameter 'codePeriode' is set
    if (codePeriode == null) {
      throw new ApiException(400, "Missing the required parameter 'codePeriode' when calling extraireCursusTypeObjetFormationPeriode");
    }
    // verify the required parameter 'typeObjet' is set
    if (typeObjet == null) {
      throw new ApiException(400, "Missing the required parameter 'typeObjet' when calling extraireCursusTypeObjetFormationPeriode");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath = "/type-objet-formation/{codeStructure}/{codePeriode}/{typeObjet}/cursus"
        .replace("{codeStructure}", ApiClient.urlEncode(codeStructure.toString()))
        .replace("{codePeriode}", ApiClient.urlEncode(codePeriode.toString()))
        .replace("{typeObjet}", ApiClient.urlEncode(typeObjet.toString()));

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
   * Extraire les affectations aux groupes des compositions renseignées, de toutes les compositions sinon, pour la formation sur la période donnée pour un établissement
   * 
   * @param codeStructure Le code structure de l&#39;établissement (required)
   * @param codePeriode Le code de la période (required)
   * @param planification Est-ce que le groupe est planifiable? (optional)
   * @param requestBody La liste des codes de compositions à extraire ou vide si on extrait toutes les compositions (optional)
   * @return List&lt;Composition&gt;
   * @throws ApiException if fails to make API call
   */
  public List<Composition> extraireGroupesDansCompositionsPourPeriode(String codeStructure, String codePeriode, Boolean planification, List<String> requestBody) throws ApiException {
    ApiResponse<List<Composition>> localVarResponse = extraireGroupesDansCompositionsPourPeriodeWithHttpInfo(codeStructure, codePeriode, planification, requestBody);
    return localVarResponse.getData();
  }

  /**
   * Extraire les affectations aux groupes des compositions renseignées, de toutes les compositions sinon, pour la formation sur la période donnée pour un établissement
   * 
   * @param codeStructure Le code structure de l&#39;établissement (required)
   * @param codePeriode Le code de la période (required)
   * @param planification Est-ce que le groupe est planifiable? (optional)
   * @param requestBody La liste des codes de compositions à extraire ou vide si on extrait toutes les compositions (optional)
   * @return ApiResponse&lt;List&lt;Composition&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public ApiResponse<List<Composition>> extraireGroupesDansCompositionsPourPeriodeWithHttpInfo(String codeStructure, String codePeriode, Boolean planification, List<String> requestBody) throws ApiException {
    HttpRequest.Builder localVarRequestBuilder = extraireGroupesDansCompositionsPourPeriodeRequestBuilder(codeStructure, codePeriode, planification, requestBody);
    try {
      HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
          localVarRequestBuilder.build(),
          HttpResponse.BodyHandlers.ofInputStream());
      if (memberVarResponseInterceptor != null) {
        memberVarResponseInterceptor.accept(localVarResponse);
      }
      try {
        if (localVarResponse.statusCode()/ 100 != 2) {
          throw getApiException("extraireGroupesDansCompositionsPourPeriode", localVarResponse);
        }
        if (localVarResponse.body() == null) {
          return new ApiResponse<List<Composition>>(
              localVarResponse.statusCode(),
              localVarResponse.headers().map(),
              null
          );
        }

        String responseBody = new String(localVarResponse.body().readAllBytes());
        localVarResponse.body().close();

        return new ApiResponse<List<Composition>>(
            localVarResponse.statusCode(),
            localVarResponse.headers().map(),
            responseBody.isBlank()? null: memberVarObjectMapper.readValue(responseBody, new TypeReference<List<Composition>>() {})
        );
      } finally {
      }
    } catch (IOException e) {
      throw new ApiException(e);
    }
    catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new ApiException(e);
    }
  }

  private HttpRequest.Builder extraireGroupesDansCompositionsPourPeriodeRequestBuilder(String codeStructure, String codePeriode, Boolean planification, List<String> requestBody) throws ApiException {
    // verify the required parameter 'codeStructure' is set
    if (codeStructure == null) {
      throw new ApiException(400, "Missing the required parameter 'codeStructure' when calling extraireGroupesDansCompositionsPourPeriode");
    }
    // verify the required parameter 'codePeriode' is set
    if (codePeriode == null) {
      throw new ApiException(400, "Missing the required parameter 'codePeriode' when calling extraireGroupesDansCompositionsPourPeriode");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath = "/groupes/{codeStructure}/{codePeriode}"
        .replace("{codeStructure}", ApiClient.urlEncode(codeStructure.toString()))
        .replace("{codePeriode}", ApiClient.urlEncode(codePeriode.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "planification";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("planification", planification));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(requestBody);
      localVarRequestBuilder.method("POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
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

}
