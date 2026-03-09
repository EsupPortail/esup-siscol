# Guide de Création d'une Nouvelle Source de Données - Modèle Générique SI

## Table des matières
1. [Introduction](#introduction)
2. [Architecture du Modèle Générique SI](#architecture-du-modèle-générique-si)
3. [Prérequis](#prérequis)
4. [Structure de l'Interface](#structure-de-linterface)
5. [Étapes de Création](#étapes-de-création)
6. [Implémentation des Endpoints](#implémentation-des-endpoints)
7. [Configuration](#configuration)
8. [Activation Conditionnelle](#activation-conditionnelle)
9. [Tests et Validation](#tests-et-validation)
10. [Exemples de Référence](#exemples-de-référence)

---

## Introduction

Le modèle **Générique SI** (Système d'Information) définit une structure normalisée permettant d'intégrer n'importe quelle source de données compatible avec les standards de gestion des étudiants, diplômes, étapes et autres entités académiques.

Ce modèle permet de :
- **Uniformiser** l'accès aux données quelque soit la source (Apogée, Pegase/PcScol, ou autre)
- **Exposer** un ensemble d'API REST standardisées via Swagger
- **Faciliter** l'intégration de nouvelles sources de données
- **Garantir** la compatibilité avec les systèmes existants

Les implémentations actuellement disponibles :
- **Apogée** : Source de données classique (mode_apogee)
- **PcScol/Pegase** : Nouvelle génération (mode_pegase)
- **GeneriqueSI** : Implémentation de référence/test

---

## Architecture du Modèle Générique SI

### Composants Principaux

```
┌─────────────────────────────────────────────────┐
│     GeneriqueSIControllerInterface              │
│  (Interface normalisée - Contrat API)           │
└─────────────────────────────────────────────────┘
                      ▲
                      │ implements
                      │
    ┌─────────────────┼─────────────────┐
    │                 │                 │
┌───┴────────┐  ┌────┴──────┐  ┌──────┴──────┐
│  Apogée    │  │  PcScol   │  │  VotrSource │
│ Controller │  │ Controller│  │  Controller  │
└────────────┘  └───────────┘  └──────────────┘
      │               │                │
      │               │                │
      ▼               ▼                ▼
┌────────────┐  ┌───────────┐  ┌─────────────┐
│ Apogée WS  │  │ PcScol    │  │ Votre API/  │
│ Client     │  │ Service   │  │ Base données│
└────────────┘  └───────────┘  └─────────────┘
```

### Principe de Fonctionnement

1. **Interface unique** : `GeneriqueSIControllerInterface` définit tous les endpoints
2. **Implémentations multiples** : Chaque source implémente cette interface
3. **Activation conditionnelle** : Via configuration Spring (`@ConditionalOnProperty`)
4. **API Swagger unifiée** : Documentation automatique pour tous les endpoints

---

## Prérequis

### Technologies Requises
- **Java** : JDK 17 ou 21
- **Spring Boot** : Framework utilisé
- **Maven** : Gestion des dépendances
- **Swagger/OpenAPI 3** : Documentation API

### Connaissances Nécessaires
- Spring Boot et annotations REST
- Java interfaces et implémentation
- Mapping de données (DTO)
- Configuration Spring (application.yml)

---

## Structure de l'Interface

L'interface `GeneriqueSIControllerInterface` définit **17 méthodes** réparties en 3 catégories :

### 1. Gestion des Étudiants (8 méthodes)

| Méthode | Description | Paramètres | Retour |
|---------|-------------|------------|--------|
| `getEtudiantRef` | Informations de référence d'un étudiant | codEtud, annee | `EtudiantRef` |
| `recupererAnneesIa` | Années d'inscription administrative | codEtud | `List<String>` |
| `etapesByEtudiantAndAnnee` | Étapes d'un étudiant pour une année | codEtud, annee | `ApogeeMap` |
| `InfosAdmEtuV2` | Informations administratives complètes | numEtud | `EtudiantInfoAdm` |
| `recupererListeEtuParEtpEtDiplome` | Liste des étudiants par formation | codeComposante, annee, codeEtape, versionEtape, codeDiplome, versionDiplome, codEtu?, nom?, prenom? | `List<ApprenantDto>` |
| `studentEtapeVets` | Map des étapes VET | codEtud, annee | `Map<String, String>` |
| `studentListeEtapesInscription` | Liste détaillée des inscriptions | codEtud, annee | `List<EtapeInscription>` |
| `studentListeElpStage` | Éléments pédagogiques de stage | codeEtape, versionEtape | `List<ElementPedagogique>` |

### 2. Gestion du Référentiel (6 méthodes)

| Méthode | Description | Paramètres | Retour |
|---------|-------------|------------|--------|
| `etablissementReference` | Référence de l'établissement | - | `EtabRef` |
| `getEtapesRef` | Liste des étapes de référence | - | `Map<String, String>` |
| `getDiplomesRef` | Liste des diplômes de référence | - | `List<DiplomeReduitDto>` |
| `getDiplomesRefParComposanteEtAnnee` | Diplômes par composante et année | codeComposante, codeAnnee | `List<DiplomeReduitDto>` |
| `composantesPrincipalesRef` | Composantes principales | - | `Map<String, String>` |
| `signaitaireRef` | Signataire d'une composante | composante (défaut: "SCO") | `SignataireRef` |

### 3. Beans de Données Principaux

Les objets de transfert de données (DTO) à utiliser :

- **EtudiantRef** : Identité et coordonnées de l'étudiant
- **EtudiantInfoAdm** : Informations administratives détaillées
- **ApprenantDto** : Données simplifiées de l'apprenant
- **EtapeInscription** : Inscription à une étape
- **ElementPedagogique** : Élément pédagogique (UE, matière, stage)
- **DiplomeReduitDto** : Information diplôme
- **EtabRef** : Référence établissement
- **SignataireRef** : Données du signataire
- **ApogeeMap** : Structure de données Apogée (compatible)

---

## Étapes de Création

### Étape 1 : Créer le Package

Créez un nouveau package pour votre source de données :

```
src/main/java/org/esupportail/referentiel/rest/votresource/
```

### Étape 2 : Créer le Contrôleur Principal

Créez la classe contrôleur qui implémente l'interface :

```java
package org.esupportail.referentiel.rest.votresource;

import java.util.*;
import org.esupportail.referentiel.beans.*;
import org.esupportail.referentiel.rest.generiqueSI.GeneriqueSIControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("votresource")
@ConditionalOnProperty(name = "app.mode_votresource", havingValue = "true")
public class VotreSourceController implements GeneriqueSIControllerInterface {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // Injection des services nécessaires
    @Autowired
    private VotreSourceService votreSourceService;

    // Configuration depuis application.yml
    @Value("${app.votresource.url}")
    private String apiUrl;

    @Value("${app.votresource.apiKey}")
    private String apiKey;

    // Implémentation des méthodes...
}
```

### Étape 3 : Créer le Service Métier

Créez une classe de service pour la logique métier :

```java
package org.esupportail.referentiel.rest.votresource;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class VotreSourceService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Récupère les données depuis votre API/BD
     */
    public EtudiantRef recupererEtudiant(String codeEtud, String annee) {
        // Logique pour récupérer l'étudiant
        // depuis votre API, base de données, etc.
        return new EtudiantRef();
    }

    // Autres méthodes métier...
}
```

### Étape 4 : Créer un Adaptateur (Optionnel)

Si votre source de données a un format différent, créez un adaptateur :

```java
package org.esupportail.referentiel.rest.votresource;

import org.springframework.stereotype.Component;
import org.esupportail.referentiel.beans.*;

@Component
public class VotreSourceAdapter {

    /**
     * Convertit les données de votre format vers le format générique
     */
    public EtudiantRef convertirVersEtudiantRef(VotreFormatEtudiant source) {
        EtudiantRef etudiant = new EtudiantRef();
        etudiant.setCod_ind(source.getIdentifiant());
        etudiant.setNompatro(source.getNom());
        etudiant.setPrenom(source.getPrenom());
        // Mapping des autres champs...
        return etudiant;
    }

    // Autres méthodes de conversion...
}
```

---

## Implémentation des Endpoints

### Exemple Complet : getEtudiantRef

```java
@Operation(summary = "Récupérer les informations de référence d'un étudiant")
@GetMapping("/etudiantRef")
public ResponseEntity<EtudiantRef> getEtudiantRef(
        @RequestParam("codEtud") String codeEtud,
        @RequestParam("annee") String annee) {
    
    logger.debug("Récupération étudiant {} pour l'année {}", codeEtud, annee);
    
    // Validation des paramètres
    if (codeEtud == null || codeEtud.isBlank()) {
        logger.error("Code étudiant manquant");
        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST, 
            "Le code étudiant est obligatoire"
        );
    }
    
    if (annee == null || annee.isBlank()) {
        logger.error("Année manquante");
        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST, 
            "L'année est obligatoire"
        );
    }
    
    try {
        // Appel au service métier
        EtudiantRef etudiant = votreSourceService.recupererEtudiant(codeEtud, annee);
        
        if (etudiant == null) {
            logger.warn("Étudiant {} non trouvé pour l'année {}", codeEtud, annee);
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, 
                "Étudiant non trouvé"
            );
        }
        
        return new ResponseEntity<>(etudiant, HttpStatus.OK);
        
    } catch (ResponseStatusException e) {
        throw e; // Relancer les exceptions déjà gérées
    } catch (Exception e) {
        logger.error("Erreur lors de la récupération de l'étudiant", e);
        throw new ResponseStatusException(
            HttpStatus.INTERNAL_SERVER_ERROR, 
            "Erreur interne lors de la récupération des données"
        );
    }
}
```

### Exemple : recupererAnneesIa

```java
@Operation(summary = "Récupérer les années d'inscription d'un étudiant")
@GetMapping("/anneesIa")
public ResponseEntity<List<String>> recupererAnneesIa(
        @RequestParam("codEtud") String codeEtud) {
    
    logger.debug("Récupération des années IA pour l'étudiant {}", codeEtud);
    
    try {
        List<String> annees = votreSourceService.recupererAnneesInscription(codeEtud);
        
        if (annees == null || annees.isEmpty()) {
            logger.warn("Aucune année trouvée pour l'étudiant {}", codeEtud);
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Aucune année d'inscription trouvée"
            );
        }
        
        return new ResponseEntity<>(annees, HttpStatus.OK);
        
    } catch (Exception e) {
        logger.error("Erreur lors de la récupération des années IA", e);
        throw new ResponseStatusException(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Erreur lors de la récupération des années"
        );
    }
}
```

### Exemple : InfosAdmEtuV2

```java
@Operation(summary = "Récupérer les informations administratives d'un étudiant")
@GetMapping("/infosAdmEtu")
public ResponseEntity<EtudiantInfoAdm> InfosAdmEtuV2(
        @RequestParam("numEtud") String numEtud) {
    
    logger.debug("Récupération infos administratives pour {}", numEtud);
    
    try {
        EtudiantInfoAdm infos = votreSourceService.recupererInfosAdministratives(numEtud);
        
        if (infos == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Étudiant non trouvé"
            );
        }
        
        return new ResponseEntity<>(infos, HttpStatus.OK);
        
    } catch (Exception e) {
        logger.error("Erreur récupération infos admin pour {}", numEtud, e);
        throw new ResponseStatusException(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Erreur lors de la récupération des informations"
        );
    }
}
```

### Exemple : getDiplomesRef

```java
@Operation(summary = "Récupérer la liste des diplômes de référence")
@GetMapping("/diplomesReference")
public ResponseEntity<List<DiplomeReduitDto>> getDiplomesRef() {
    
    logger.debug("Récupération de tous les diplômes de référence");
    
    try {
        List<DiplomeReduitDto> diplomes = votreSourceService.recupererDiplomes();
        return new ResponseEntity<>(diplomes, HttpStatus.OK);
        
    } catch (Exception e) {
        logger.error("Erreur lors de la récupération des diplômes", e);
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

---

## Configuration

### Configuration dans application.yml

Ajoutez votre configuration dans `/etc/esup-siscol/application.yml` :

```yaml
app:
  # Modes d'activation des sources
  mode_apogee: false
  mode_pegase: false
  mode_votresource: true  # Activer votre source
  
  # Configuration spécifique à votre source
  votresource:
    url: https://api.votresource.universite.fr
    apiKey: ${VOTRESOURCE_API_KEY:cle-par-defaut}
    timeout: 30000
    codeEtablissement: "ETAB01"
    # Autres paramètres spécifiques...
```

### Variables d'Environnement

Vous pouvez utiliser des variables d'environnement pour les données sensibles :

```yaml
app:
  votresource:
    apiKey: ${VOTRESOURCE_API_KEY}
    username: ${VOTRESOURCE_USERNAME}
    password: ${VOTRESOURCE_PASSWORD}
```

### Configuration Avancée

```yaml
app:
  votresource:
    # Configuration de connexion
    connexion:
      url: https://api.votresource.fr
      timeout: 30000
      retries: 3
      
    # Configuration cache
    cache:
      enabled: true
      ttl: 3600
      
    # Configuration métier
    metier:
      codeEtablissement: "ETAB01"
      anneeParDefaut: "2024"
      formatDate: "yyyy-MM-dd"
```

---

## Activation Conditionnelle

### Annotation @ConditionalOnProperty

L'annotation permet d'activer/désactiver le contrôleur selon la configuration :

```java
@ConditionalOnProperty(
    name = "app.mode_votresource", 
    havingValue = "true"
)
```

### Conditions Multiples

Vous pouvez combiner plusieurs conditions :

```java
@ConditionalOnProperty(
    name = "app.mode_votresource", 
    havingValue = "true"
)
@ConditionalOnExpression(
    "${app.votresource.enabled:true} && ${app.votresource.production:false}"
)
```

### Configuration de Profils

Utilisez les profils Spring pour différents environnements :

```yaml
---
spring:
  profiles: dev
app:
  votresource:
    url: https://api-dev.votresource.fr
    
---
spring:
  profiles: prod
app:
  votresource:
    url: https://api.votresource.fr
```

---

## Tests et Validation

### Tests Unitaires

Créez des tests pour vos services :

```java
package org.esupportail.referentiel.rest.votresource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class VotreSourceServiceTest {

    @Autowired
    private VotreSourceService service;

    @Test
    public void testRecupererEtudiant() {
        EtudiantRef etudiant = service.recupererEtudiant("123456", "2024");
        assertNotNull(etudiant);
        assertEquals("123456", etudiant.getCod_ind());
    }
}
```

### Tests d'Intégration

Testez les endpoints via MockMvc :

```java
@SpringBootTest
@AutoConfigureMockMvc
public class VotreSourceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetEtudiantRef() throws Exception {
        mockMvc.perform(get("/votresource/etudiantRef")
                .param("codEtud", "123456")
                .param("annee", "2024"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.cod_ind").value("123456"));
    }
}
```

### Validation Swagger

Une fois déployé, accédez à Swagger UI pour tester :

```
http://localhost:8080/swagger-ui.html
```

Vérifiez que tous vos endpoints apparaissent sous `/votresource`.

---

## Exemples de Référence

### 1. Exemple Apogée (Web Services)

Le contrôleur Apogée montre comment intégrer des web services SOAP :

**Fichier** : `src/main/java/org/esupportail/referentiel/rest/apogee/ApogeeController.java`

**Points clés** :
- Utilisation d'un client WS : `EtudiantMetierClient`
- DAO pour la récupération de données : `StudentDataRepositoryDaoWS`
- Pas d'implémentation complète de l'interface (classe legacy)

### 2. Exemple PcScol/Pegase (API REST)

Le contrôleur PcScol montre une implémentation complète :

**Fichier** : `src/main/java/org/esupportail/referentiel/rest/pcscol/PcscolController.java`

**Points clés** :
- Implémentation complète de `GeneriqueSIControllerInterface`
- Service métier : `PcscolService`
- Adaptateur pour conversion : `PcscolControllerAdapter`
- Gestion des erreurs avec `ResponseStatusException`
- Logs détaillés
- Activation conditionnelle : `@ConditionalOnProperty(name = "app.mode_pegase")`

**Structure** :
```
pcscol/
├── PcscolController.java           # Contrôleur REST
├── PcscolControllerAdapter.java    # Adaptateur de données
└── services/
    ├── PcscolService.java          # Service métier
    └── impl/
        └── PcscolServiceImpl.java  # Implémentation
```

### 3. Exemple Générique SI (Référence)

Le contrôleur générique montre une structure minimale :

**Fichier** : `src/main/java/org/esupportail/referentiel/rest/generiqueSI/GeneriqueSIController.java`

**Points clés** :
- Implémentation simple avec retours vides
- Utile comme template de départ
- Toutes les méthodes définies
- Annotations Swagger complètes

---

## Checklist d'Implémentation

### Phase 1 : Préparation
- [ ] Analyser la source de données (API, BD, fichiers, etc.)
- [ ] Identifier les correspondances avec les beans existants
- [ ] Créer le package et la structure de fichiers
- [ ] Définir la configuration dans application.yml

### Phase 2 : Implémentation
- [ ] Créer le contrôleur principal
- [ ] Implémenter l'interface `GeneriqueSIControllerInterface`
- [ ] Créer les services métier nécessaires
- [ ] Créer les adaptateurs si besoin
- [ ] Ajouter les annotations Swagger

### Phase 3 : Configuration
- [ ] Ajouter la configuration dans application.yml
- [ ] Configurer l'activation conditionnelle
- [ ] Définir les paramètres de connexion
- [ ] Gérer les variables d'environnement sensibles

### Phase 4 : Tests
- [ ] Écrire les tests unitaires
- [ ] Écrire les tests d'intégration
- [ ] Tester via Swagger UI
- [ ] Valider tous les endpoints
- [ ] Tester la gestion d'erreurs

### Phase 5 : Documentation
- [ ] Documenter les endpoints spécifiques
- [ ] Documenter la configuration
- [ ] Créer un guide de déploiement
- [ ] Documenter les limitations éventuelles

---

## Bonnes Pratiques

### 1. Gestion des Erreurs

```java
// Toujours logger les erreurs
logger.error("Erreur détaillée", exception);

// Utiliser des codes HTTP appropriés
throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message clair");

// Ne jamais exposer les détails techniques dans les messages d'erreur publics
```

### 2. Logging

```java
// Utiliser les niveaux appropriés
logger.debug("Détails pour debug");
logger.info("Informations importantes");
logger.warn("Avertissements");
logger.error("Erreurs", exception);
```

### 3. Validation

```java
// Valider les paramètres en entrée
if (param == null || param.isBlank()) {
    throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST, 
        "Le paramètre est obligatoire"
    );
}
```

### 4. Performance

```java
// Utiliser le cache si approprié
@Cacheable("diplomes")
public List<DiplomeReduitDto> getDiplomes() {
    // ...
}

// Gérer les timeouts
RestTemplate restTemplate = new RestTemplate();
restTemplate.setRequestFactory(
    new HttpComponentsClientHttpRequestFactory()
);
```

### 5. Sécurité

```java
// Ne jamais logger de données sensibles
logger.debug("Traitement pour l'étudiant {}", codeEtud);
// PAS : logger.debug("Password: {}", password);

// Valider et nettoyer les entrées
String cleanCode = StringUtils.trimToEmpty(codeEtud);
```

---

## Dépannage

### Problème : Le contrôleur n'est pas activé

**Solution** : Vérifiez la configuration
```yaml
app:
  mode_votresource: true  # Doit être à true
```

### Problème : Endpoints non visibles dans Swagger

**Solution** : Vérifiez les annotations
```java
@RestController
@RequestMapping("votresource")  // Le préfixe URL
```

### Problème : Erreur 404 sur les endpoints

**Solution** : Vérifiez le mapping et l'activation conditionnelle

### Problème : Données non converties correctement

**Solution** : Vérifiez votre adaptateur et les mappings de champs

---

## Support et Ressources

### Documentation Technique
- **Interface** : `GeneriqueSIControllerInterface.java`
- **Beans** : Package `org.esupportail.referentiel.beans`
- **Exemples** : `ApogeeController.java`, `PcscolController.java`

### Configuration
- **Sample** : `/etc/esup-siscol/application.yml.sample`
- **Logs** : `/etc/esup-siscol/logback.xml`

### Swagger UI
- **URL** : `http://[serveur]:[port]/swagger-ui.html`
- **API Docs** : `http://[serveur]:[port]/v3/api-docs`

---

## Conclusion

Le modèle Générique SI offre une architecture flexible et extensible pour intégrer toute source de données compatible. En suivant ce guide, vous pouvez :

1. **Créer rapidement** une nouvelle intégration
2. **Garantir la compatibilité** avec les systèmes existants
3. **Exposer automatiquement** une API REST documentée
4. **Maintenir facilement** votre code avec une structure claire

Pour toute question ou contribution, référez-vous au projet GitHub : https://github.com/EsupPortail/esup-siscol

---

**Version du document** : 1.0  
**Date de création** : Mars 2026  
**Auteur** : Documentation technique esup-siscol
