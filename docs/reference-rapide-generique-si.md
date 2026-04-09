# Référence Rapide - Modèle Générique SI

## Vue d'Ensemble

Le modèle Générique SI d'esup-siscol permet d'intégrer n'importe quelle source de données (Apogée, Pegase/PcScol, ou source personnalisée) via une interface standardisée exposée en REST/Swagger.

---

## Architecture en 3 Couches

```
┌──────────────────────────────────────────────────────────┐
│                    COUCHE REST                           │
│  GeneriqueSIControllerInterface (17 endpoints)           │
│  - Swagger/OpenAPI Documentation automatique             │
└──────────────────────────────────────────────────────────┘
                            ↕
┌──────────────────────────────────────────────────────────┐
│                COUCHE IMPLÉMENTATION                     │
│  ┌─────────────┐  ┌──────────────┐  ┌────────────────┐  │
│  │   Apogée    │  │   PcScol     │  │  Votre Source  │  │
│  │  Controller │  │  Controller  │  │   Controller   │  │
│  └─────────────┘  └──────────────┘  └────────────────┘  │
│         ↓                 ↓                  ↓            │
│  ┌─────────────┐  ┌──────────────┐  ┌────────────────┐  │
│  │   Service   │  │   Service    │  │    Service     │  │
│  └─────────────┘  └──────────────┘  └────────────────┘  │
│         ↓                 ↓                  ↓            │
│  ┌─────────────┐  ┌──────────────┐  ┌────────────────┐  │
│  │  Adaptateur │  │  Adaptateur  │  │   Adaptateur   │  │
│  └─────────────┘  └──────────────┘  └────────────────┘  │
└──────────────────────────────────────────────────────────┘
                            ↕
┌──────────────────────────────────────────────────────────┐
│                  COUCHE DONNÉES                          │
│  ┌─────────────┐  ┌──────────────┐  ┌────────────────┐  │
│  │ Apogée WS   │  │  PcScol API  │  │   Votre API/   │  │
│  │   (SOAP)    │  │   (REST)     │  │   Base de      │  │
│  │             │  │              │  │   données      │  │
│  └─────────────┘  └──────────────┘  └────────────────┘  │
└──────────────────────────────────────────────────────────┘
```

---

## Checklist Création Nouvelle Source

### Phase 1 : Préparation (30 min)
- [ ] Analyser l'API/source de données
- [ ] Identifier les endpoints nécessaires
- [ ] Mapper les données vers les beans standards
- [ ] Créer la structure de packages

### Phase 2 : Développement Core (4h)
- [ ] Créer les DTOs spécifiques à votre source
- [ ] Implémenter le client API/DAO
- [ ] Créer l'adaptateur de données
- [ ] Implémenter le service métier
- [ ] Créer le contrôleur REST

### Phase 3 : Configuration (30 min)
- [ ] Ajouter la config dans application.yml
- [ ] Configurer l'activation conditionnelle
- [ ] Définir les variables d'environnement

### Phase 4 : Tests (2h)
- [ ] Tests unitaires (adaptateur, service)
- [ ] Tests d'intégration (contrôleur)
- [ ] Tests via Swagger UI
- [ ] Tests de charge (optionnel)

### Phase 5 : Documentation (1h)
- [ ] Documenter les endpoints spécifiques
- [ ] Guide de configuration
- [ ] Guide d'exploitation

**Temps total estimé : 8 heures**

---

## Interface Complète - 17 Endpoints

### 🎓 Gestion Étudiants (8 endpoints)

| Endpoint | Méthode | Paramètres | Retour | Description |
|----------|---------|------------|--------|-------------|
| `/etudiantRef` | GET | codEtud, annee | EtudiantRef | Info étudiant |
| `/anneesIa` | GET | codEtud | List\<String\> | Années inscription |
| `/etapesByEtudiantAndAnnee` | GET | codEtud, annee | ApogeeMap | Étapes/année |
| `/infosAdmEtu` | GET | numEtud | EtudiantInfoAdm | Infos admin |
| `/listEtuParEtapeEtDiplome` | GET | codeComposante, annee, codeEtape, versionEtape, codeDiplome, versionDiplome, codEtu?, nom?, prenom? | List\<ApprenantDto\> | Liste étudiants |
| `/studentEtapeVets` | GET | codEtud, annee | Map\<String,String\> | Map étapes VET |
| `/studentListeEtapeInscription` | GET | codEtud, annee | List\<EtapeInscription\> | Liste inscriptions |
| `/studentListeElpStage` | GET | codeEtape, versionEtape | List\<ElementPedagogique\> | ELP de stage |

### 📚 Gestion Référentiel (7 endpoints)

| Endpoint | Méthode | Paramètres | Retour | Description |
|----------|---------|------------|--------|-------------|
| `/etablissementReference` | GET | - | EtabRef | Ref établissement |
| `/etapesReference` | GET | - | Map\<String,String\> | Étapes de ref |
| `/diplomesReference` | GET | - | List\<DiplomeReduitDto\> | Diplômes de ref |
| `/diplomesReferenceParComposanteEtAnnee` | GET | codeComposante, codeAnnee | List\<DiplomeReduitDto\> | Diplômes filtrés |
| `/composantesPrincipalesRef` | GET | - | Map\<String,String\> | Composantes |
| `/composanteSignaitaireRef` | GET | composante? | SignataireRef | Signataire |
| `/regimesInscriptions` | GET | - | Map\<String,String\> | Régimes d'inscription (code -> libellé) |

---

## Beans Principaux

### EtudiantRef
```java
// Identité
String cod_ind          // Code étudiant
String nompatro         // Nom patronymique
String nommarital       // Nom marital
String prenom           // Prénom
String prenomEtatCivil  // Prénom état civil
String sexEtatCivil     // Sexe

// Contact
String mail             // Email institutionnel
String mailPerso        // Email personnel
String phone            // Téléphone
String portablePhone    // Portable

// Adresse
String mainAddress      // Adresse
String postalCode       // Code postal
String town             // Ville
String country          // Pays
```

### EtudiantInfoAdm
```java
// Identité
String numEtu           // Numéro étudiant
String codEtu           // Code étudiant
String codInd           // Code individu
String nomPatronymique  // Nom
String nomUsuel         // Nom usuel
String prenom1          // Prénom 1
String prenom2          // Prénom 2

// Administratif
String numeroINE        // INE
String numBoursier      // N° boursier
String handicap         // Handicap
String nationaliteDTO   // Nationalité
String dateNaissance    // Date naissance
String situationFamiliale
String situationMilitaire

// Années
String anneePremiereInscEnsSup
String anneePremiereInscEtb
```

### ApprenantDto
```java
String codeApprenant
String nomApprenant
String prenomApprenant
String emailApprenant
```

### DiplomeReduitDto
```java
String codDip           // Code diplôme
String libDip           // Libellé diplôme
String libDipAff        // Libellé affichage
```

---

## Détail : endpoint `/pcscol/regimesInscriptions`

- URI : `/pcscol/regimesInscriptions`
- Méthode : GET
- Paramètres : aucun
- Retour : `Map<String,String>` où la clé est le code du régime (ex : `codRgi`) et la valeur est le libellé affiché.
- Comportement :
  - Appelle `ReferentielMetier.recupererRegimeInscription(...)` pour récupérer la liste des régimes.
  - Pour chaque nomenclature, le libellé retourné est d'abord `getLibelleAffichage()` ; si celui-ci est vide ou null, on utilise `getLibelleCourt()` (fallback).
  - Utilise une `LinkedHashMap` pour préserver l'ordre d'apparition garanti par la source.
  - Les clés vides/null sont ignorées.

Exemple de réponse (JSON) :
```json
{
  "RGI01": "Inscription initiale",
  "RGI02": "Réinscription",
  "RGI03": "Admission sur titre"
}
```

Notes :
- Le endpoint ne prend pas de paramètres et renvoie l'ensemble des régimes disponibles pour l'établissement activé par la configuration (scope local du contrôleur `pcscol`).
- En cas d'erreur lors de l'appel au service de référentiel, le contrôleur renvoie une erreur 500 ou 404 selon le contexte (voir comportement standard des contrôleurs dans ce repo).

---

## Template Minimal - Contrôleur

```java
@RestController
@RequestMapping("votrenom")
@ConditionalOnProperty(name = "app.mode_votrenom", havingValue = "true")
public class VotreController implements GeneriqueSIControllerInterface {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private VotreService service;

    @Override
    @Operation(summary = "Description")
    @GetMapping("/endpoint")
    public ResponseEntity<Type> methode(@RequestParam("param") String param) {
        logger.info("GET /votrenom/endpoint - param: {}", param);
        
        try {
            Type result = service.recupererDonnees(param);
            if (result == null) {
                throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Non trouvé"
                );
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erreur", e);
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, "Erreur"
            );
        }
    }
}
```

---

## Configuration Minimale

```yaml
app:
  # Activation
  mode_apogee: false
  mode_pegase: false
  mode_votrenom: true
  
  # Config spécifique
  votrenom:
    url: https://api.exemple.fr
    apiKey: ${API_KEY}
    timeout: 30000
```

---

## Dépendances Maven

```xml
<!-- Spring Boot -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- Swagger/OpenAPI -->
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
</dependency>

<!-- Logging -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
</dependency>

<!-- Tests -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

---

## Commandes Utiles

### Compilation
```bash
mvn clean install
```

### Tests uniquement
```bash
mvn test
```

### Compiler sans tests
```bash
mvn clean install -DskipTests
```

### Lancer en local
```bash
java -jar target/esup-siscol.jar
```

### Voir les logs en direct
```bash
tail -f /var/log/esup-siscol/application.log
```

---

## URLs Importantes

| Service | URL | Description |
|---------|-----|-------------|
| Swagger UI | http://localhost:8080/swagger-ui.html | Interface de test |
| API Docs | http://localhost:8080/v3/api-docs | Spécification OpenAPI |
| Actuator | http://localhost:8080/actuator | Métriques |
| Health | http://localhost:8080/actuator/health | État de l'app |

---

## Codes HTTP Standards

| Code | Description | Usage |
|------|-------------|-------|
| 200 | OK | Requête réussie |
| 201 | Created | Ressource créée |
| 400 | Bad Request | Paramètres invalides |
| 401 | Unauthorized | Non authentifié |
| 403 | Forbidden | Accès refusé |
| 404 | Not Found | Ressource non trouvée |
| 500 | Internal Error | Erreur serveur |
| 503 | Service Unavailable | Service indisponible |

---

## Patterns de Logging

```java
// Debug - Détails techniques
logger.debug("Récupération de {} pour l'année {}", code, annee);

// Info - Actions importantes
logger.info("GET /endpoint - param: {}", param);

// Warn - Situations anormales non bloquantes
logger.warn("Étudiant {} non trouvé", code);

// Error - Erreurs avec exception
logger.error("Erreur lors de l'appel API", exception);
```

---

## Gestion d'Erreurs

```java
// Paramètre manquant
if (param == null || param.isBlank()) {
    throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST, 
        "Le paramètre est obligatoire"
    );
}

// Ressource non trouvée
if (result == null) {
    throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, 
        "Ressource non trouvée"
    );
}

// Erreur technique
catch (Exception e) {
    logger.error("Erreur inattendue", e);
    throw new ResponseStatusException(
        HttpStatus.INTERNAL_SERVER_ERROR,
        "Erreur lors du traitement"
    );
}
```

---

## Annotations Essentielles

### Spring REST
```java
@RestController              // Contrôleur REST
@RequestMapping("path")      // Préfixe URL
@GetMapping("/endpoint")     // HTTP GET
@PostMapping("/endpoint")    // HTTP POST
@RequestParam("name")        // Paramètre query
@PathVariable("id")          // Paramètre path
```

### Spring Configuration
```java
@Configuration               // Classe de config
@Value("${prop}")           // Injection propriété
@Autowired                  // Injection dépendance
@Component                  // Composant Spring
@Service                    // Service métier
```

### Conditions
```java
@ConditionalOnProperty(name = "prop", havingValue = "true")
@ConditionalOnMissingBean(Type.class)
@Profile("prod")
```

### Swagger
```java
@Tag(name = "Nom", description = "Description")
@Operation(summary = "Résumé")
@Parameter(description = "Description paramètre")
```

---

## Tests - Exemples

### Test Unitaire
```java
@Test
void testMethode() {
    // Arrange
    Service service = new Service();
    
    // Act
    Result result = service.methode(param);
    
    // Assert
    assertNotNull(result);
    assertEquals(expected, result.getValue());
}
```

### Test d'Intégration
```java
@SpringBootTest
@AutoConfigureMockMvc
class ControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void testEndpoint() throws Exception {
        mockMvc.perform(get("/endpoint")
                .param("param", "value"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.field").value("value"));
    }
}
```

---

## Exemples Référence

### Apogée (Web Services SOAP)
- **Fichier** : `rest/apogee/ApogeeController.java`
- **Client** : SOAP WS Client
- **Type** : Legacy, pas d'implémentation complète

### PcScol/Pegase (API REST)
- **Fichier** : `rest/pcscol/PcscolController.java`
- **Implémentation** : Complète avec service et adaptateur
- **Type** : Moderne, référence recommandée

### Générique SI (Template)
- **Fichier** : `rest/generiqueSI/GeneriqueSIController.java`
- **Implémentation** : Minimale avec retours vides
- **Type** : Template de démarrage

---

## Ressources

### Documentation
- [Guide complet](guide-creation-source-donnees-generique-si.md)
- [Exemple d'implémentation](exemple-implementation-nouvelle-source.md)
- [README principal](../README.md)

### Code Source
- **Interface** : `rest/generiqueSI/GeneriqueSIControllerInterface.java`
- **Beans** : Package `beans/`
- **Configuration** : `/etc/esup-siscol/application.yml`

### Liens Externes
- **GitHub** : https://github.com/EsupPortail/esup-siscol
- **Swagger** : https://swagger.io/docs/
- **Spring Boot** : https://docs.spring.io/spring-boot/

---

## Troubleshooting Rapide

| Problème | Solution |
|----------|----------|
| Contrôleur non activé | Vérifier `app.mode_xxx: true` dans config |
| 404 sur endpoints | Vérifier `@RequestMapping` et l'activation |
| Endpoints non dans Swagger | Vérifier `@RestController` et `@GetMapping` |
| Erreur compilation | Vérifier implémentation de tous les endpoints |
| Données incorrectes | Vérifier l'adaptateur et le mapping |

---

## Contact et Support

Pour toute question :
1. Consulter la documentation complète
2. Vérifier les exemples de référence (PcScol)
3. Consulter les issues GitHub
4. Contacter l'équipe projet

---

**Document de référence rapide v1.0**  
Mise à jour : Mars 2026
