# Diagrammes et Schémas - Modèle Générique SI

Ce document présente des diagrammes visuels pour mieux comprendre l'architecture et les flux du modèle Générique SI.

---

## 1. Architecture Globale du Système

```
┌─────────────────────────────────────────────────────────────────────┐
│                          CLIENTS                                    │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐              │
│  │   Portail    │  │ Application  │  │    Tests     │              │
│  │    Web       │  │    Mobile    │  │   Swagger    │              │
│  └──────────────┘  └──────────────┘  └──────────────┘              │
└─────────────────────────────────────────────────────────────────────┘
                              │
                              │ HTTP REST
                              │
┌─────────────────────────────▼─────────────────────────────────────┐
│                      ESUP-SISCOL (Spring Boot)                    │
│                                                                   │
│  ┌──────────────────────────────────────────────────────────┐   │
│  │         COUCHE REST (Swagger/OpenAPI)                    │   │
│  │                                                           │   │
│  │    GeneriqueSIControllerInterface (17 endpoints)         │   │
│  │    - Contrat API standardisé                             │   │
│  │    - Documentation automatique                           │   │
│  └──────────────────────────────────────────────────────────┘   │
│                              │                                    │
│                ┌─────────────┼─────────────┐                     │
│                │             │             │                     │
│  ┌─────────────▼──┐  ┌──────▼──────┐  ┌──▼──────────┐          │
│  │  Apogée        │  │   PcScol    │  │  Nouvelle   │          │
│  │  Controller    │  │  Controller │  │   Source    │          │
│  │  (Legacy)      │  │ (Moderne)   │  │ Controller  │          │
│  └────────────────┘  └─────────────┘  └─────────────┘          │
│         │                   │                 │                  │
│  ┌──────▼──────┐    ┌──────▼──────┐   ┌─────▼──────┐          │
│  │   Service   │    │   Service   │   │  Service   │          │
│  └──────┬──────┘    └──────┬──────┘   └─────┬──────┘          │
│         │                   │                 │                  │
│  ┌──────▼──────┐    ┌──────▼──────┐   ┌─────▼──────┐          │
│  │  Adaptateur │    │  Adaptateur │   │ Adaptateur │          │
│  └──────┬──────┘    └──────┬──────┘   └─────┬──────┘          │
└─────────┼─────────────────┼────────────────┼─────────────────┘
          │                  │                 │
┌─────────▼────────┐  ┌─────▼─────────┐  ┌───▼───────────┐
│  Apogée WS       │  │  PcScol API   │  │  Votre API/   │
│  (SOAP)          │  │  (REST)       │  │  Base données │
│                  │  │               │  │               │
│  - Web Services  │  │  - Pegase     │  │  - REST API   │
│  - Version 6.x   │  │  - Moderne    │  │  - SGBD       │
└──────────────────┘  └───────────────┘  └───────────────┘
```

---

## 2. Flux de Traitement d'une Requête

```
Client
  │
  │ 1. HTTP GET /votresource/etudiantRef?codEtud=123&annee=2024
  │
  ▼
┌────────────────────────────────────────────────────────┐
│ VotreSourceController                                  │
│                                                        │
│ @GetMapping("/etudiantRef")                           │
│ public ResponseEntity<EtudiantRef> getEtudiantRef()   │
│   │                                                    │
│   │ 2. Validation des paramètres                      │
│   │    - codEtud non vide?                            │
│   │    - annee valide?                                │
│   │                                                    │
│   └─► 3. Appel au Service                             │
└────────────────────┬───────────────────────────────────┘
                     │
                     ▼
┌────────────────────────────────────────────────────────┐
│ VotreSourceService                                     │
│                                                        │
│ public EtudiantRef getEtudiant(codEtud, annee)        │
│   │                                                    │
│   │ 4. Logique métier                                 │
│   │    - Cache?                                       │
│   │    - Règles métier                                │
│   │                                                    │
│   └─► 5. Appel au Client API                          │
└────────────────────┬───────────────────────────────────┘
                     │
                     ▼
┌────────────────────────────────────────────────────────┐
│ VotreSourceApiClient                                   │
│                                                        │
│ public VotreEtudiant getStudent(codEtud, annee)       │
│   │                                                    │
│   │ 6. Appel HTTP REST                                │
│   │    GET https://api.votresource.fr/students/123    │
│   │    Headers: X-API-Key, Content-Type               │
│   │                                                    │
│   └─► 7. Récupération données brutes                  │
└────────────────────┬───────────────────────────────────┘
                     │
                     │ 8. VotreEtudiant (format source)
                     │
                     ▼
┌────────────────────────────────────────────────────────┐
│ VotreSourceAdapter                                     │
│                                                        │
│ public EtudiantRef toEtudiantRef(VotreEtudiant)       │
│   │                                                    │
│   │ 9. Mapping des champs                             │
│   │    votreEtud.id        → etudRef.cod_ind         │
│   │    votreEtud.lastName  → etudRef.nompatro        │
│   │    votreEtud.firstName → etudRef.prenom          │
│   │    ...                                            │
│   │                                                    │
│   └─► 10. EtudiantRef (format standard)               │
└────────────────────┬───────────────────────────────────┘
                     │
                     │ 11. Retour au contrôleur
                     │
                     ▼
┌────────────────────────────────────────────────────────┐
│ VotreSourceController                                  │
│                                                        │
│   │ 12. Création ResponseEntity                       │
│   │     - Status: 200 OK                              │
│   │     - Body: EtudiantRef                           │
│   │                                                    │
│   └─► 13. Retour HTTP                                 │
└────────────────────┬───────────────────────────────────┘
                     │
                     │ 14. JSON Response
                     │
                     ▼
                   Client
```

---

## 3. Gestion des Erreurs

```
┌─────────────────────────────────────────────────────┐
│              Point d'Entrée                         │
│          Controller Endpoint                        │
└─────────────────┬───────────────────────────────────┘
                  │
        ┌─────────▼─────────┐
        │ Validation        │
        │ Paramètres?       │
        └─────┬──────┬──────┘
              │      │
         OK   │      │ KO
              │      │
              │      └───► ResponseStatusException
              │             HttpStatus.BAD_REQUEST
              │             "Paramètre manquant"
              │
        ┌─────▼──────────┐
        │ try {          │
        │   Service      │
        │     ↓          │
        │   Client API   │
        │     ↓          │
        │   Adaptateur   │
        │ }              │
        └─────┬──┬───┬───┘
              │  │   │
        OK    │  │   │  Erreurs
              │  │   │
              │  │   └──► ApiException
              │  │         │
              │  │         └─► ResponseStatusException
              │  │              HttpStatus.INTERNAL_SERVER_ERROR
              │  │              "Erreur API"
              │  │
              │  └──────► NotFoundException
              │            │
              │            └─► ResponseStatusException
              │                 HttpStatus.NOT_FOUND
              │                 "Étudiant non trouvé"
              │
        ┌─────▼──────────┐
        │  Données ?     │
        └─────┬──────┬───┘
              │      │
         OK   │      │ null
              │      │
              │      └───► ResponseStatusException
              │             HttpStatus.NOT_FOUND
              │             "Ressource non trouvée"
              │
        ┌─────▼──────────┐
        │ ResponseEntity │
        │ HttpStatus.OK  │
        │ Body: data     │
        └────────────────┘
```

---

## 4. Structure des Packages

```
org.esupportail.referentiel/
│
├── rest/                           # Couche REST
│   ├── generiqueSI/               # Modèle générique
│   │   ├── GeneriqueSIControllerInterface.java  ← Interface
│   │   └── GeneriqueSIController.java           ← Implémentation de base
│   │
│   ├── apogee/                    # Implémentation Apogée
│   │   └── ApogeeController.java
│   │
│   ├── pcscol/                    # Implémentation PcScol
│   │   ├── PcscolController.java
│   │   ├── PcscolControllerAdapter.java
│   │   └── services/
│   │       └── PcscolService.java
│   │
│   └── votresource/               # VOTRE implémentation
│       ├── VotreSourceController.java      ← Contrôleur REST
│       ├── VotreSourceService.java         ← Logique métier
│       ├── VotreSourceAdapter.java         ← Conversion données
│       ├── VotreSourceApiClient.java       ← Client API
│       └── dto/                            ← DTOs spécifiques
│           ├── VotreEtudiant.java
│           ├── VotreDiplome.java
│           └── ...
│
├── beans/                         # Beans standards (DTOs)
│   ├── EtudiantRef.java          # ← Utilisés par TOUTES les sources
│   ├── EtudiantInfoAdm.java
│   ├── ApprenantDto.java
│   ├── DiplomeReduitDto.java
│   └── ...
│
├── services/                      # Services transverses
│   ├── StudentDataRepository.java
│   └── ...
│
├── ldap/                          # Services LDAP
│   └── services/
│
├── ws/                            # Web Services (Apogée)
│   └── services/
│
└── conf/                          # Configuration
    └── SwaggerConfig.java
```

---

## 5. Cycle de Vie de l'Application

```
┌────────────────────────────────────────────────────────┐
│          DÉMARRAGE APPLICATION                         │
└─────────────────┬──────────────────────────────────────┘
                  │
        ┌─────────▼─────────┐
        │ Spring Boot        │
        │ Initialisation     │
        └─────────┬──────────┘
                  │
        ┌─────────▼──────────────────────────┐
        │ Lecture Configuration              │
        │ /etc/esup-siscol/application.yml   │
        │                                    │
        │ app:                               │
        │   mode_apogee: false               │
        │   mode_pegase: false               │
        │   mode_votresource: true ◄─────    │
        └─────────┬──────────────────────────┘
                  │
        ┌─────────▼────────────────────────────┐
        │ Scan des @RestController            │
        │ avec @ConditionalOnProperty         │
        └─────────┬────────────────────────────┘
                  │
        ┌─────────▼────────────┐
        │ Évaluation           │
        │ Conditions           │
        └───┬───────┬──────┬───┘
            │       │      │
    ┌───────▼──┐ ┌──▼────┐ ┌▼───────────┐
    │ Apogée   │ │PcScol │ │VotreSource │
    │ SKIP     │ │SKIP   │ │ ACTIVE ✓   │
    │ (false)  │ │(false)│ │ (true)     │
    └──────────┘ └───────┘ └─────┬──────┘
                                  │
                    ┌─────────────▼─────────────┐
                    │ Instanciation Beans       │
                    │ - VotreSourceController   │
                    │ - VotreSourceService      │
                    │ - VotreSourceAdapter      │
                    │ - VotreSourceApiClient    │
                    └─────────────┬─────────────┘
                                  │
                    ┌─────────────▼─────────────┐
                    │ Enregistrement Endpoints  │
                    │ /votresource/*            │
                    └─────────────┬─────────────┘
                                  │
                    ┌─────────────▼─────────────┐
                    │ Génération Doc Swagger    │
                    │ /swagger-ui.html          │
                    └─────────────┬─────────────┘
                                  │
                    ┌─────────────▼─────────────┐
                    │ APPLICATION READY         │
                    │ Écoute sur port 8080      │
                    └───────────────────────────┘
```

---

## 6. Activation Conditionnelle Multi-Sources

```
┌────────────────────────────────────────────────────────────┐
│              Configuration application.yml                 │
│                                                            │
│  app:                                                      │
│    mode_apogee: true    ◄─┐                               │
│    mode_pegase: true    ◄─┼─┐                             │
│    mode_votresource: false◄┼─┼─┐                          │
└────────────────────────────┼─┼─┼──────────────────────────┘
                             │ │ │
            ┌────────────────┘ │ │
            │  ┌────────────────┘ │
            │  │  ┌────────────────┘
            │  │  │
┌───────────▼──▼──▼──────────────────────────────┐
│      Spring Boot @ConditionalOnProperty        │
└───────────┬──┬──┬──────────────────────────────┘
            │  │  │
    ┌───────▼─┐│  │
    │ Apogée  ││  │    @ConditionalOnProperty(
    │ ✓ ACTIF ││  │      name="app.mode_apogee",
    │         ││  │      havingValue="true")
    │ Endpoints:│  │
    │ /apogee/*││  │
    └──────────┘│  │
                │  │
       ┌────────▼─┐│
       │ PcScol   ││    @ConditionalOnProperty(
       │ ✓ ACTIF  ││      name="app.mode_pegase",
       │          ││      havingValue="true")
       │ Endpoints:│
       │ /pcscol/*││
       └──────────┘│
                   │
          ┌────────▼──────┐
          │ VotreSource   │    @ConditionalOnProperty(
          │ ✗ INACTIF     │      name="app.mode_votresource",
          │               │      havingValue="true")
          │ Endpoints:    │
          │ (non chargés) │
          └───────────────┘

RÉSULTAT dans Swagger UI:
┌────────────────────────┐
│ Swagger UI             │
├────────────────────────┤
│ ▼ Apogee              │
│   GET /apogee/...     │
│                       │
│ ▼ Pcscol              │
│   GET /pcscol/...     │
│                       │
│ (VotreSource absent)  │
└────────────────────────┘
```

---

## 7. Mapping de Données (Adaptateur)

```
┌──────────────────────────────────────────────────────────┐
│         DONNÉES SOURCE (Format spécifique)               │
│                                                          │
│  VotreEtudiant {                                         │
│    student_id: "123456"                                  │
│    first_name: "Jean"                                    │
│    last_name: "Dupont"                                   │
│    email_address: "jean.dupont@univ.fr"                 │
│    birth_date: "1995-03-15"                             │
│    address: {                                            │
│      street: "10 rue de l'Université"                   │
│      zip: "75001"                                        │
│      city: "Paris"                                       │
│      country_code: "FR"                                  │
│    }                                                     │
│  }                                                       │
└────────────────────┬─────────────────────────────────────┘
                     │
                     │ VotreSourceAdapter
                     │ toEtudiantRef()
                     │
         ┌───────────▼────────────────────┐
         │   MAPPING LOGIQUE              │
         │                                │
         │ etudiant.cod_ind               │
         │   ← votreEtud.student_id       │
         │                                │
         │ etudiant.prenom                │
         │   ← votreEtud.first_name       │
         │                                │
         │ etudiant.nompatro              │
         │   ← votreEtud.last_name        │
         │                                │
         │ etudiant.mail                  │
         │   ← votreEtud.email_address    │
         │                                │
         │ etudiant.mainAddress           │
         │   ← votreEtud.address.street   │
         │                                │
         │ etudiant.postalCode            │
         │   ← votreEtud.address.zip      │
         │                                │
         │ etudiant.town                  │
         │   ← votreEtud.address.city     │
         │                                │
         │ etudiant.country               │
         │   ← mapCountry(               │
         │       votreEtud.address.       │
         │         country_code)          │
         └───────────┬────────────────────┘
                     │
                     ▼
┌──────────────────────────────────────────────────────────┐
│        DONNÉES STANDARD (Format générique)               │
│                                                          │
│  EtudiantRef {                                           │
│    cod_ind: "123456"                                     │
│    prenom: "Jean"                                        │
│    nompatro: "Dupont"                                    │
│    mail: "jean.dupont@univ.fr"                          │
│    mainAddress: "10 rue de l'Université"                │
│    postalCode: "75001"                                   │
│    town: "Paris"                                         │
│    country: "France"                                     │
│  }                                                       │
└──────────────────────────────────────────────────────────┘
```

---

## 8. Endpoints et Responsabilités

```
┌────────────────────────────────────────────────────────────────┐
│           GÉNÉRIQUE SI - 17 ENDPOINTS                          │
└────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────┐
│  👤 GESTION ÉTUDIANTS (8 endpoints)                     │
├─────────────────────────────────────────────────────────┤
│                                                         │
│  1. /etudiantRef                                        │
│     ↳ Identité et coordonnées complètes                │
│     ↳ Usage: Consultation fiche étudiant               │
│                                                         │
│  2. /anneesIa                                          │
│     ↳ Liste des années d'inscription                   │
│     ↳ Usage: Sélection année dans interface            │
│                                                         │
│  3. /etapesByEtudiantAndAnnee                          │
│     ↳ Étapes d'un étudiant pour une année             │
│     ↳ Usage: Parcours académique                       │
│                                                         │
│  4. /infosAdmEtu                                       │
│     ↳ Informations administratives détaillées          │
│     ↳ Usage: Dossier administratif complet             │
│                                                         │
│  5. /listEtuParEtapeEtDiplome                          │
│     ↳ Liste étudiants d'une formation                  │
│     ↳ Usage: Gestion de promotion                      │
│                                                         │
│  6. /studentEtapeVets                                   │
│     ↳ Map des VET d'inscription                        │
│     ↳ Usage: Système Apogée                            │
│                                                         │
│  7. /studentListeEtapeInscription                       │
│     ↳ Détail des inscriptions pédagogiques             │
│     ↳ Usage: Suivi inscriptions                        │
│                                                         │
│  8. /studentListeElpStage                               │
│     ↳ Éléments pédagogiques de stage                   │
│     ↳ Usage: Gestion des stages                        │
│                                                         │
└─────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────┐
│  📚 GESTION RÉFÉRENTIEL (6 endpoints)                   │
├─────────────────────────────────────────────────────────┤
│                                                         │
│  9. /etablissementReference                             │
│     ↳ Informations établissement                       │
│     ↳ Usage: Configuration globale                     │
│                                                         │
│  10. /etapesReference                                   │
│      ↳ Liste toutes les étapes                         │
│      ↳ Usage: Listes de sélection                      │
│                                                         │
│  11. /diplomesReference                                 │
│      ↳ Liste tous les diplômes                         │
│      ↳ Usage: Listes de sélection                      │
│                                                         │
│  12. /diplomesReferenceParComposanteEtAnnee            │
│      ↳ Diplômes filtrés                                │
│      ↳ Usage: Recherche contextualisée                 │
│                                                         │
│  13. /composantesPrincipalesRef                         │
│      ↳ Liste des composantes/UFR                       │
│      ↳ Usage: Organisation                             │
│                                                         │
│  14. /composanteSignaitaireRef                          │
│      ↳ Signataire d'une composante                     │
│      ↳ Usage: Édition documents officiels              │
│                                                         │
└─────────────────────────────────────────────────────────┘
```

---

## 9. Timeline de Développement

```
SEMAINE 1 : Préparation et Design
├─ Jour 1-2 : Lecture documentation
│  └─ Comprendre architecture et concepts
├─ Jour 3 : Analyse de la source de données
│  └─ API, formats, authentification
└─ Jour 4-5 : Design de l'implémentation
   └─ Mapping données, structure packages

SEMAINE 2 : Développement Core
├─ Jour 1 : DTOs et Client API
│  └─ Classes de données et communication
├─ Jour 2-3 : Service et Adaptateur
│  └─ Logique métier et conversion
└─ Jour 4-5 : Contrôleur REST
   └─ Implémentation des 8 endpoints étudiants

SEMAINE 3 : Complétion et Tests
├─ Jour 1-2 : Endpoints référentiel
│  └─ 6 endpoints restants
├─ Jour 3-4 : Tests
│  └─ Unitaires et d'intégration
└─ Jour 5 : Documentation
   └─ Configuration et exploitation

TOTAL : 3 semaines pour une implémentation complète
```

---

## 10. Checklist Visuelle de Progression

```
PHASE PRÉPARATION
[ ] Lire documentation
[ ] Analyser source de données
[ ] Créer structure packages
      └─► Temps estimé: 1 jour

PHASE DÉVELOPPEMENT
[ ] Créer DTOs spécifiques
[ ] Implémenter Client API
      └─► Temps estimé: 2 jours

[ ] Créer Adaptateur
[ ] Implémenter Service
      └─► Temps estimé: 3 jours

[ ] Créer Contrôleur
[ ] Implémenter endpoints étudiants (8)
      └─► Temps estimé: 4 jours

[ ] Implémenter endpoints référentiel (6)
      └─► Temps estimé: 2 jours

PHASE CONFIGURATION
[ ] Ajouter application.yml
[ ] Configurer activation
[ ] Définir variables environnement
      └─► Temps estimé: 0.5 jour

PHASE TESTS
[ ] Tests unitaires
[ ] Tests d'intégration
[ ] Tests Swagger
[ ] Tests de charge (opt.)
      └─► Temps estimé: 3 jours

PHASE DOCUMENTATION
[ ] Documentation endpoints
[ ] Guide configuration
[ ] Guide exploitation
      └─► Temps estimé: 1 jour

TOTAL: ~16-17 jours = 3 semaines
```

---

**Note** : Ces diagrammes sont en ASCII art pour une compatibilité maximale. Pour des versions graphiques professionnelles, utilisez des outils comme PlantUML, Draw.io ou Lucidchart.

---

*Document créé en Mars 2026*  
*Version 1.0*
