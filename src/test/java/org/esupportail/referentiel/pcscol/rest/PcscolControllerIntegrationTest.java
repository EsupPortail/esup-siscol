package org.esupportail.referentiel.pcscol.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.esupportail.referentiel.beans.ApprenantDto;
import org.esupportail.referentiel.beans.EtapeInscription;
import org.esupportail.referentiel.beans.EtudiantRef;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = { "app.mode_pegase=true",
		"app.mode_apogee=false" })
@ActiveProfiles("test")
class PcscolControllerIntegrationTest {

	@LocalServerPort
	private int port=9090; // Injects the random port the app starts on

	@Value("${credential.userscredential.root.username}")
	private String username;

	@Value("${credential.userscredential.root.password}")
	private String password;

	@Autowired
	private TestRestTemplate restTemplate; // Spring's HTTP client for testing

	@BeforeEach
	void setUp() {
		// This method can be used to set up any common test data or configurations
		// before each test runs.
	}

	@Test
	void testGetEtapesRef() {
		// Arrange
		String url = "/pcscol/etapesReference";

		// Act
		ResponseEntity<Map> response = restTemplate().getForEntity(url, Map.class);

		// Assert
		// response.i
		assertEquals(true, response.getStatusCode().is2xxSuccessful());
		assertNotNull(response.getBody());

	}

	@Test
	void testGetDiplomesRef() {
		// Arrange
		String url = "/pcscol/diplomesReference";

		// Act
		ResponseEntity<Object[]> response = restTemplate().getForEntity(url, Object[].class);

		// Assert
		assertEquals(true, response.getStatusCode().is2xxSuccessful());
		assertNotNull(response.getBody());
		System.out.println("Réponse : " + response.getBody().length + " diplômes trouvés.");
	}

	@Test
	void testRecupererListeEtuParEtpEtDiplome() {

		String codeComposante = "ETAB00";
		String annee = "2021";
		String codeEtape = "MST-CIEA-A1";
		String versionEtape = "ETAB00";
		String codeDiplome = "MST-CIEA";
		String versionDiplome = "ETAB00";
		String codEtu = null;
		String nom = null;
		String prenom = null;

		String baseUrl = "/pcscol/listEtuParEtapeEtDiplome";
		String url = UriComponentsBuilder.fromUriString(baseUrl).queryParam("codeComposante", codeComposante)
				.queryParam("annee", annee).queryParam("codeEtape", codeEtape).queryParam("versionEtape", versionEtape)
				.queryParam("codeDiplome", codeDiplome).queryParam("versionDiplome", versionDiplome).toUriString();

		// Act
		ResponseEntity<ApprenantDto[]> response = restTemplate().getForEntity(url, ApprenantDto[].class);

		// Assert
		assertEquals(true, response.getStatusCode().is2xxSuccessful());
		assertNotNull(response.getBody());
		System.out.println("Réponse : " + response.getBody().length + " apprenants trouvés.");
	}

	@Test
	void testStudentListeEtapesInscription() {
		// Arrange
		String codEtud = "000000001";
		String annee = "2021";

		String url = "/pcscol/studentListeEtapeInscription?codEtud=" + codEtud + "&annee=" + annee;
		System.out.println("URL : " + url);
		ResponseEntity<EtapeInscription[]> response = restTemplate().getForEntity(url, EtapeInscription[].class);

		// Assert
		assertEquals(true, response.getStatusCode().is2xxSuccessful());
		assertNotNull(response.getBody());
		System.out.println("Réponse : " + response.getBody().length + " étapes d'inscription trouvées.");
	}

	@Test
	void testRecupererAnneesIa() {
		// Arrange
		String codEtud = "000000001";
		String url = "/pcscol/anneesIa?codEtud=" + codEtud;
		System.out.println("URL : " + url);
		ResponseEntity<List> response = restTemplate().getForEntity(url, List.class);

		// Assert
		assertTrue(response.getStatusCode().is2xxSuccessful());
		assertNotNull(response.getBody());
		assertTrue(response.getBody().size() > 0, "La liste des années d'inscription ne doit pas être vide.");
		assertEquals(2, response.getBody().size(), "La liste des années d'inscription doit contenir 2 éléments.");
		System.out.println("Réponse : " + response.getBody() + " années d'inscription trouvées.");
	}
	@Test
	void testGetEtudiantRef()  {
	
		String codeApprenant = "000000001";
		String annee = "2021";
		String url = "/pcscol/etudiantRef?codEtud=" + codeApprenant + "&annee=" + annee;
		System.out.println("URL : " + url);

		// Exécution de la requête
		ResponseEntity<EtudiantRef> response = restTemplate().getForEntity(url, EtudiantRef.class);

		// Vérification du statut de la réponse
		assertEquals(true, response.getStatusCode().is2xxSuccessful(), "La requête doit réussir avec un statut 2xx");

		// Vérification du corps de la réponse
		EtudiantRef etudiant = response.getBody();
		assertNotNull(etudiant, "Le corps de la réponse ne doit pas être null");
		assertEquals(codeApprenant, etudiant.getCod_ind(), "Le code étudiant doit correspondre à celui demandé");
	}

	/**
	 * 
	 * @return
	 */
	private TestRestTemplate restTemplate() {
		// Configure the TestRestTemplate with basic authentications
		return restTemplate.withBasicAuth(username, password);
	}

}
