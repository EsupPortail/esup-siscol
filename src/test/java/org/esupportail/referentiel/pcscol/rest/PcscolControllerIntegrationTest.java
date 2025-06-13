package org.esupportail.referentiel.pcscol.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PcscolControllerIntegrationTest {

	//@Autowired
    private RestTemplate restTemplate=new RestTemplate();

    @Test
    public void testGetEtapesRef() {
        // Arrange
        String url = "/pcscol/etapesReference";

        // Act
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        System.out.println("Réponse : " + response.getBody());
    }

    @Test
    public void testGetDiplomesRef() {
        // Arrange
        String url = "/pcscol/diplomesReference";

        // Act
        ResponseEntity<Object[]> response = restTemplate.getForEntity(url, Object[].class);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        System.out.println("Réponse : " + response.getBody().length + " diplômes trouvés.");
    }

}
