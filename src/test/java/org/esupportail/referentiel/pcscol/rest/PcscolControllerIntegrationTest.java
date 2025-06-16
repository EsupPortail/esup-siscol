package org.esupportail.referentiel.pcscol.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;

import org.esupportail.referentiel.beans.ApprenantDto;
import org.esupportail.referentiel.beans.EtapeInscription;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PcscolControllerIntegrationTest {

	@LocalServerPort
	private int port; // Injects the random port the app starts on

	@Autowired
	private TestRestTemplate restTemplate; // Spring's HTTP client for testing

	@BeforeEach
	void setUp() {
		// This method can be used to set up any common test data or configurations
		// before each test runs.
	}

	@Test
	public void testGetEtapesRef() {
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
	public void testGetDiplomesRef() {
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
		/**
		 * TODO : valider les paramètres de la requête
		 */
		String codeComposante = "ETAB00";
		String annee = "2021";
		String codeEtape = "MST-CIEA-A1";
		String versionEtape = "ETAB00";
		String codeDiplome = "MST-CIEA";
		String versionDiplome = "ETAB00";
		String codEtu = null;
		String nom = null;
		String prenom = null;
		
		String baseUrl="/pcscol/listEtuParEtapeEtDiplome";
		String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
		        .queryParam("codeComposante", codeComposante)
		        .queryParam("annee", annee)
		        .queryParam("codeEtape", codeEtape)
		        .queryParam("versionEtape", versionEtape)
		        .queryParam("codeDiplome", codeDiplome)
		        .queryParam("versionDiplome", versionDiplome)
		        .toUriString();

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

		ResponseEntity<EtapeInscription[]> response = restTemplate().getForEntity(url, EtapeInscription[].class);

		// Assert
		assertEquals(true, response.getStatusCode().is2xxSuccessful());
		assertNotNull(response.getBody());
		System.out.println("Réponse : " + response.getBody().length + " étapes d'inscription trouvées.");
	}

	private TestRestTemplate restTemplate() {
		// Configure the TestRestTemplate with basic authentications
		return restTemplate.withBasicAuth("root", "root");
	}

}
