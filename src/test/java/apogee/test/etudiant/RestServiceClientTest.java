package apogee.test.etudiant;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.esupportail.referentiel.SpringBootTomcatApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;


class RestServiceClientTest {

	
	@Test
	void testCallExternalApiWithBasicAuth() {
		ResponseEntity<String> response = new ExternalApiService(new RestTemplateBuilder())
				.callExternalApiWithBasicAuth("http://localhost:8080/ldap/bySupannAliasLogin?login=acheraga&base=default", "root", "root");
		assertEquals(HttpStatus.OK, response.getStatusCode());
		printResponse(response);
	}

	private void printResponse(ResponseEntity<String> response) {
		System.out.println("++++++++++++++++"+response.getBody());		
	}

		

}