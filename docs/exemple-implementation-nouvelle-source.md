# Exemple d'Implémentation : Nouvelle Source de Données

## Guide Pratique avec Exemple Concret

Ce document présente un exemple complet d'implémentation d'une nouvelle source de données nommée **"MonSI"** qui expose des données via une API REST JSON.

---

## Contexte de l'Exemple

**Scénario** : Votre université utilise un système propriétaire "MonSI" avec une API REST qui expose :
- Données étudiants via `/api/v1/students/{id}`
- Diplômes via `/api/v1/programs`
- Inscriptions via `/api/v1/enrollments`

**Objectif** : Intégrer cette source dans esup-siscol en respectant le modèle Générique SI.

---

## Structure du Projet

```
src/main/java/org/esupportail/referentiel/
└── rest/
    └── monsi/
        ├── MonSIController.java           # Contrôleur REST
        ├── MonSIService.java              # Service métier
        ├── MonSIAdapter.java              # Adaptateur de données
        ├── MonSIApiClient.java            # Client API REST
        └── dto/                           # DTOs spécifiques MonSI
            ├── MonSIStudent.java
            ├── MonSIProgram.java
            └── MonSIEnrollment.java
```

---

## Étape 1 : DTOs Spécifiques MonSI

### MonSIStudent.java

```java
package org.esupportail.referentiel.rest.monsi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Représente un étudiant dans le système MonSI
 */
public class MonSIStudent {
    
    @JsonProperty("student_id")
    private String studentId;
    
    @JsonProperty("first_name")
    private String firstName;
    
    @JsonProperty("last_name")
    private String lastName;
    
    @JsonProperty("birth_date")
    private String birthDate;
    
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("phone")
    private String phone;
    
    @JsonProperty("address")
    private MonSIAddress address;
    
    // Getters et Setters
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    // ... autres getters/setters
}
```

### MonSIAddress.java

```java
package org.esupportail.referentiel.rest.monsi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MonSIAddress {
    
    @JsonProperty("street")
    private String street;
    
    @JsonProperty("postal_code")
    private String postalCode;
    
    @JsonProperty("city")
    private String city;
    
    @JsonProperty("country")
    private String country;
    
    // Getters et Setters
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    
    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
    
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}
```

---

## Étape 2 : Client API REST

### MonSIApiClient.java

```java
package org.esupportail.referentiel.rest.monsi;

import org.esupportail.referentiel.rest.monsi.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

/**
 * Client pour l'API MonSI
 */
@Component
public class MonSIApiClient {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Value("${app.monsi.api.url}")
    private String apiUrl;
    
    @Value("${app.monsi.api.key}")
    private String apiKey;
    
    private final RestTemplate restTemplate;
    
    public MonSIApiClient() {
        this.restTemplate = new RestTemplate();
    }
    
    /**
     * Récupère un étudiant par son ID
     */
    public MonSIStudent getStudent(String studentId, String year) {
        try {
            String url = String.format("%s/api/v1/students/%s?year=%s", 
                apiUrl, studentId, year);
            
            HttpHeaders headers = createHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);
            
            ResponseEntity<MonSIStudent> response = restTemplate.exchange(
                url, 
                HttpMethod.GET, 
                entity, 
                MonSIStudent.class
            );
            
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            }
            
            logger.warn("API MonSI retourné le code {}", response.getStatusCode());
            return null;
            
        } catch (RestClientException e) {
            logger.error("Erreur lors de l'appel API MonSI pour l'étudiant {}", studentId, e);
            throw new RuntimeException("Erreur API MonSI", e);
        }
    }
    
    /**
     * Récupère les années d'inscription d'un étudiant
     */
    public String[] getStudentYears(String studentId) {
        try {
            String url = String.format("%s/api/v1/students/%s/years", 
                apiUrl, studentId);
            
            HttpHeaders headers = createHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);
            
            ResponseEntity<String[]> response = restTemplate.exchange(
                url, 
                HttpMethod.GET, 
                entity, 
                String[].class
            );
            
            return response.getBody();
            
        } catch (RestClientException e) {
            logger.error("Erreur lors de la récupération des années pour {}", studentId, e);
            return new String[0];
        }
    }
    
    /**
     * Crée les headers HTTP avec l'authentification
     */
    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-Key", apiKey);
        return headers;
    }
}
```

---

## Étape 3 : Adaptateur de Données

### MonSIAdapter.java

```java
package org.esupportail.referentiel.rest.monsi;

import org.esupportail.referentiel.beans.*;
import org.esupportail.referentiel.rest.monsi.dto.*;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Convertit les données MonSI vers le format Générique SI
 */
@Component
public class MonSIAdapter {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /**
     * Convertit un étudiant MonSI vers EtudiantRef
     */
    public EtudiantRef toEtudiantRef(MonSIStudent monSIStudent) {
        if (monSIStudent == null) {
            return null;
        }
        
        EtudiantRef etudiant = new EtudiantRef();
        
        // Identité
        etudiant.setCod_ind(monSIStudent.getStudentId());
        etudiant.setNompatro(monSIStudent.getLastName());
        etudiant.setPrenom(monSIStudent.getFirstName());
        
        // Contact
        etudiant.setMail(monSIStudent.getEmail());
        etudiant.setPhone(monSIStudent.getPhone());
        
        // Adresse
        if (monSIStudent.getAddress() != null) {
            MonSIAddress addr = monSIStudent.getAddress();
            etudiant.setMainAddress(addr.getStreet());
            etudiant.setPostalCode(addr.getPostalCode());
            etudiant.setTown(addr.getCity());
            etudiant.setCountry(addr.getCountry());
        }
        
        logger.debug("Conversion étudiant MonSI {} vers EtudiantRef", 
            monSIStudent.getStudentId());
        
        return etudiant;
    }
    
    /**
     * Convertit un programme MonSI vers DiplomeReduitDto
     */
    public DiplomeReduitDto toDiplomeReduitDto(MonSIProgram program) {
        if (program == null) {
            return null;
        }
        
        DiplomeReduitDto diplome = new DiplomeReduitDto();
        diplome.setCodDip(program.getProgramCode());
        diplome.setLibDip(program.getProgramName());
        diplome.setLibDipAff(program.getProgramDisplayName());
        
        return diplome;
    }
}
```

---

## Étape 4 : Service Métier

### MonSIService.java

```java
package org.esupportail.referentiel.rest.monsi;

import org.esupportail.referentiel.beans.*;
import org.esupportail.referentiel.rest.monsi.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Service métier pour MonSI
 */
@Service
public class MonSIService {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private MonSIApiClient apiClient;
    
    @Autowired
    private MonSIAdapter adapter;
    
    /**
     * Récupère un étudiant
     */
    public EtudiantRef getEtudiant(String codeEtud, String annee) {
        logger.debug("Récupération étudiant {} pour l'année {}", codeEtud, annee);
        
        try {
            // Appel API
            MonSIStudent monSIStudent = apiClient.getStudent(codeEtud, annee);
            
            // Conversion
            return adapter.toEtudiantRef(monSIStudent);
            
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération de l'étudiant", e);
            return null;
        }
    }
    
    /**
     * Récupère les années d'inscription
     */
    public List<String> getAnneesInscription(String codeEtud) {
        logger.debug("Récupération années pour {}", codeEtud);
        
        try {
            String[] years = apiClient.getStudentYears(codeEtud);
            return Arrays.asList(years);
            
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des années", e);
            return new ArrayList<>();
        }
    }
    
    /**
     * Récupère les informations administratives
     */
    public EtudiantInfoAdm getInfosAdministratives(String numEtud) {
        logger.debug("Récupération infos admin pour {}", numEtud);
        
        // Implémentation spécifique à MonSI
        // ...
        
        return new EtudiantInfoAdm();
    }
}
```

---

## Étape 5 : Contrôleur REST

### MonSIController.java

```java
package org.esupportail.referentiel.rest.monsi;

import java.util.*;
import org.esupportail.referentiel.beans.*;
import org.esupportail.referentiel.rest.generiqueSI.GeneriqueSIControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Contrôleur REST pour l'accès aux données MonSI
 */
@RestController
@RequestMapping("monsi")
@ConditionalOnProperty(name = "app.mode_monsi", havingValue = "true")
@Tag(name = "MonSI", description = "API d'accès aux données MonSI")
public class MonSIController implements GeneriqueSIControllerInterface {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MonSIService monSIService;

    @Value("${app.monsi.codeEtablissement}")
    private String codeEtablissement;

    /**
     * Récupère les informations de référence d'un étudiant
     */
    @Override
    @Operation(summary = "Récupérer les informations de référence d'un étudiant")
    @GetMapping("/etudiantRef")
    public ResponseEntity<EtudiantRef> getEtudiantRef(
            @RequestParam("codEtud") String codeEtud,
            @RequestParam("annee") String annee) {
        
        logger.info("GET /monsi/etudiantRef - codEtud: {}, annee: {}", codeEtud, annee);
        
        // Validation
        if (codeEtud == null || codeEtud.isBlank()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, 
                "Le code étudiant est obligatoire"
            );
        }
        
        if (annee == null || annee.isBlank()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, 
                "L'année est obligatoire"
            );
        }
        
        try {
            EtudiantRef etudiant = monSIService.getEtudiant(codeEtud, annee);
            
            if (etudiant == null) {
                throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Étudiant non trouvé"
                );
            }
            
            return new ResponseEntity<>(etudiant, HttpStatus.OK);
            
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Erreur inattendue", e);
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erreur lors de la récupération des données"
            );
        }
    }

    /**
     * Récupère les années d'inscription d'un étudiant
     */
    @Override
    @Operation(summary = "Récupérer les années d'inscription d'un étudiant")
    @GetMapping("/anneesIa")
    public ResponseEntity<List<String>> recupererAnneesIa(
            @RequestParam("codEtud") String codeEtud) {
        
        logger.info("GET /monsi/anneesIa - codEtud: {}", codeEtud);
        
        try {
            List<String> annees = monSIService.getAnneesInscription(codeEtud);
            
            if (annees == null || annees.isEmpty()) {
                throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Aucune année d'inscription trouvée"
                );
            }
            
            return new ResponseEntity<>(annees, HttpStatus.OK);
            
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des années", e);
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erreur lors de la récupération des années"
            );
        }
    }

    /**
     * Récupère les étapes d'un étudiant pour une année donnée
     */
    @Override
    @Operation(summary = "Récupérer les étapes d'un étudiant pour une année donnée")
    @GetMapping("/etapesByEtudiantAndAnnee")
    public ResponseEntity<ApogeeMap> etapesByEtudiantAndAnnee(
            @RequestParam("codEtud") String codeEtud,
            @RequestParam("annee") String annee) {
        
        logger.info("GET /monsi/etapesByEtudiantAndAnnee - codEtud: {}, annee: {}", 
            codeEtud, annee);
        
        // Implémentation spécifique
        return new ResponseEntity<>(new ApogeeMap(), HttpStatus.OK);
    }

    /**
     * Récupère les informations administratives d'un étudiant
     */
    @Override
    @Operation(summary = "Récupérer les informations administratives d'un étudiant")
    @GetMapping("/infosAdmEtu")
    public ResponseEntity<EtudiantInfoAdm> InfosAdmEtuV2(
            @RequestParam("numEtud") String numEtud) {
        
        logger.info("GET /monsi/infosAdmEtu - numEtud: {}", numEtud);
        
        try {
            EtudiantInfoAdm infos = monSIService.getInfosAdministratives(numEtud);
            return new ResponseEntity<>(infos, HttpStatus.OK);
            
        } catch (Exception e) {
            logger.error("Erreur récupération infos admin", e);
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erreur lors de la récupération des informations"
            );
        }
    }

    /**
     * Récupère la liste des étudiants par étape et diplôme
     */
    @Override
    @Operation(summary = "Récupérer la liste des étudiants par étape et diplôme")
    @GetMapping("/listEtuParEtapeEtDiplome")
    public ResponseEntity<List<ApprenantDto>> recupererListeEtuParEtpEtDiplome(
            @RequestParam("codeComposante") String codeComposante,
            @RequestParam("annee") String annee,
            @RequestParam("codeEtape") String codeEtape,
            @RequestParam("versionEtape") String versionEtape,
            @RequestParam("codeDiplome") String codeDiplome,
            @RequestParam("versionDiplome") String versionDiplome,
            @RequestParam(value = "codEtu", required = false) String codEtu,
            @RequestParam(value = "nom", required = false) String nom,
            @RequestParam(value = "prenom", required = false) String prenom) {
        
        logger.info("GET /monsi/listEtuParEtapeEtDiplome");
        
        // Implémentation spécifique
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    /**
     * Récupère les étapes d'inscription d'un étudiant pour une année
     */
    @Override
    @Operation(summary = "Récupérer les étapes d'inscription d'un étudiant pour une année")
    @GetMapping("/studentEtapeVets")
    public ResponseEntity<Map<String, String>> studentEtapeVets(
            @RequestParam("codEtud") String codeEtud,
            @RequestParam("annee") String annee) {
        
        logger.info("GET /monsi/studentEtapeVets - codEtud: {}, annee: {}", 
            codeEtud, annee);
        
        return new ResponseEntity<>(new LinkedHashMap<>(), HttpStatus.OK);
    }

    /**
     * Récupère la liste des étapes d'inscription d'un étudiant
     */
    @Override
    @Operation(summary = "Récupérer la liste des étapes d'inscription d'un étudiant")
    @GetMapping("/studentListeEtapeInscription")
    public ResponseEntity<List<EtapeInscription>> studentListeEtapesInscription(
            @RequestParam("codEtud") String codEtud,
            @RequestParam("annee") String annee) {
        
        logger.info("GET /monsi/studentListeEtapeInscription");
        
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    /**
     * Récupère la liste des éléments pédagogiques de stage pour une étape
     */
    @Override
    @Operation(summary = "Récupérer la liste des éléments pédagogiques de stage pour une étape")
    @GetMapping("/studentListeElpStage")
    public ResponseEntity<List<ElementPedagogique>> studentListeElpStage(
            @RequestParam("codeEtape") String codeEtape,
            @RequestParam("versionEtape") String versionEtape) {
        
        logger.info("GET /monsi/studentListeElpStage");
        
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    /**
     * Récupère la référence de l'établissement
     */
    @Override
    @Operation(summary = "Récupérer la référence de l'établissement")
    @GetMapping("/etablissementReference")
    public ResponseEntity<EtabRef> etablissementReference() {
        
        logger.info("GET /monsi/etablissementReference");
        
        EtabRef etabRef = new EtabRef();
        etabRef.setCod_etb(codeEtablissement);
        
        return new ResponseEntity<>(etabRef, HttpStatus.OK);
    }

    /**
     * Récupère la liste des étapes de référence
     */
    @Override
    @Operation(summary = "Récupérer la liste des étapes de référence")
    @GetMapping("/etapesReference")
    public ResponseEntity<Map<String, String>> getEtapesRef() {
        
        logger.info("GET /monsi/etapesReference");
        
        return new ResponseEntity<>(new HashMap<>(), HttpStatus.OK);
    }

    /**
     * Récupère la liste des diplômes de référence
     */
    @Override
    @Operation(summary = "Récupérer la liste des diplômes de référence")
    @GetMapping("/diplomesReference")
    public ResponseEntity<List<DiplomeReduitDto>> getDiplomesRef() {
        
        logger.info("GET /monsi/diplomesReference");
        
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    /**
     * Récupère la liste des diplômes de référence par composante et année
     */
    @Override
    @Operation(summary = "Récupérer la liste des diplômes de référence par composante et année")
    @GetMapping("/diplomesReferenceParComposanteEtAnnee")
    public ResponseEntity<List<DiplomeReduitDto>> getDiplomesRefParComposanteEtAnnee(
            @RequestParam("codeComposante") String codeComposante,
            @RequestParam("codeAnnee") String codeAnnee) {
        
        logger.info("GET /monsi/diplomesReferenceParComposanteEtAnnee");
        
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    /**
     * Récupère la liste des composantes principales de référence
     */
    @Override
    @Operation(summary = "Récupérer la liste des composantes principales de référence")
    @GetMapping("/composantesPrincipalesRef")
    public ResponseEntity<Map<String, String>> composantesPrincipalesRef() {
        
        logger.info("GET /monsi/composantesPrincipalesRef");
        
        return new ResponseEntity<>(new HashMap<>(), HttpStatus.OK);
    }

    /**
     * Récupère le signataire de la composante
     */
    @Override
    @Operation(summary = "Récupérer le signataire de la composante")
    @GetMapping("/composanteSignaitaireRef")
    public ResponseEntity<SignataireRef> signaitaireRef(
            @RequestParam(value = "composante", defaultValue = "SCO") String composante) {
        
        logger.info("GET /monsi/composanteSignaitaireRef - composante: {}", composante);
        
        return new ResponseEntity<>(new SignataireRef(), HttpStatus.OK);
    }
}
```

---

## Étape 6 : Configuration

### application.yml

Ajoutez dans `/etc/esup-siscol/application.yml` :

```yaml
app:
  # Activation des sources
  mode_apogee: false
  mode_pegase: false
  mode_monsi: true  # Activer MonSI
  
  # Configuration MonSI
  monsi:
    api:
      url: https://api.monsi.universite.fr
      key: ${MONSI_API_KEY:votre-cle-api}
    codeEtablissement: "ETAB01"
    timeout: 30000
```

### Variables d'Environnement

Pour la production, utilisez des variables d'environnement :

```bash
export MONSI_API_KEY="votre-cle-api-secrete"
```

---

## Étape 7 : Tests

### Test Unitaire - MonSIAdapterTest.java

```java
package org.esupportail.referentiel.rest.monsi;

import org.esupportail.referentiel.beans.EtudiantRef;
import org.esupportail.referentiel.rest.monsi.dto.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MonSIAdapterTest {

    @Test
    void testToEtudiantRef() {
        // Arrange
        MonSIAdapter adapter = new MonSIAdapter();
        
        MonSIStudent student = new MonSIStudent();
        student.setStudentId("123456");
        student.setFirstName("Jean");
        student.setLastName("Dupont");
        student.setEmail("jean.dupont@univ.fr");
        
        // Act
        EtudiantRef result = adapter.toEtudiantRef(student);
        
        // Assert
        assertNotNull(result);
        assertEquals("123456", result.getCod_ind());
        assertEquals("Dupont", result.getNompatro());
        assertEquals("Jean", result.getPrenom());
        assertEquals("jean.dupont@univ.fr", result.getMail());
    }
}
```

### Test d'Intégration - MonSIControllerTest.java

```java
package org.esupportail.referentiel.rest.monsi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MonSIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetEtudiantRef_Success() throws Exception {
        mockMvc.perform(get("/monsi/etudiantRef")
                .param("codEtud", "123456")
                .param("annee", "2024"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.cod_ind").exists());
    }

    @Test
    void testGetEtudiantRef_MissingParam() throws Exception {
        mockMvc.perform(get("/monsi/etudiantRef")
                .param("codEtud", "123456"))
            .andExpect(status().isBadRequest());
    }
}
```

---

## Étape 8 : Compilation et Déploiement

### Compilation

```bash
cd /home/abdelhamid/Documents/workspace/esup-siscol-ws-prod/esup-siscol
mvn clean install
```

### Déploiement

```bash
# Copier le WAR généré
cp target/esup-siscol-2.1.11.war /path/to/tomcat/webapps/

# Redémarrer Tomcat
systemctl restart tomcat
```

### Vérification

Accédez à Swagger UI :
```
http://localhost:8080/swagger-ui.html
```

Vous devriez voir un nouveau groupe "MonSI" avec tous les endpoints.

---

## Utilisation via Swagger

### Tester l'endpoint getEtudiantRef

1. Ouvrir Swagger UI
2. Aller dans la section "MonSI"
3. Cliquer sur `/monsi/etudiantRef`
4. Cliquer sur "Try it out"
5. Renseigner les paramètres :
   - codEtud : "123456"
   - annee : "2024"
6. Cliquer sur "Execute"

Réponse attendue :
```json
{
  "cod_ind": "123456",
  "nompatro": "Dupont",
  "prenom": "Jean",
  "mail": "jean.dupont@univ.fr",
  "phone": "0612345678",
  "mainAddress": "10 rue de l'Université",
  "postalCode": "75001",
  "town": "Paris",
  "country": "France"
}
```

---

## Résumé des Fichiers Créés

1. **DTOs** : MonSIStudent.java, MonSIAddress.java, MonSIProgram.java
2. **Client API** : MonSIApiClient.java
3. **Adaptateur** : MonSIAdapter.java
4. **Service** : MonSIService.java
5. **Contrôleur** : MonSIController.java
6. **Tests** : MonSIAdapterTest.java, MonSIControllerTest.java
7. **Configuration** : Ajout dans application.yml

Total : **~1500 lignes de code** pour une intégration complète !

---

## Points Clés à Retenir

1. **Interface standardisée** : Toujours implémenter `GeneriqueSIControllerInterface`
2. **Séparation des responsabilités** : Controller → Service → API Client
3. **Adaptateur** : Convertir vos données vers les beans standards
4. **Activation conditionnelle** : Utiliser `@ConditionalOnProperty`
5. **Logs** : Logger à tous les niveaux pour le débogage
6. **Gestion d'erreurs** : Utiliser `ResponseStatusException`
7. **Tests** : Écrire des tests unitaires et d'intégration

---

## Prochaines Étapes

- [ ] Implémenter les endpoints manquants
- [ ] Ajouter un système de cache (Spring Cache)
- [ ] Gérer la pagination pour les listes
- [ ] Ajouter des métriques (Actuator)
- [ ] Documenter l'API avec des exemples Swagger
- [ ] Créer un guide d'exploitation

---

**Bon développement !** 🚀
