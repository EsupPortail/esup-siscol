package apogee.test.etudiant;

import org.esupportail.referentiel.beans.ApogeeMap;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.core.publisher.Mono;

public class ApogeeTestRest {

	TestRestTemplate testRestTemplate = new TestRestTemplate("root", "root",
			TestRestTemplate.HttpClientOption.ENABLE_COOKIES);

	RestTemplate restTemplate = new RestTemplate();

	String baseUrl = "http://cri146.admc.parisnanterre.fr:8080";
	String login = "root";
	String password = "root";

	@Test
	public void test1() {

		String url = baseUrl + "/apogee/etapesByEtudiantAndAnnee";

		HttpHeaders headers = new HttpHeaders();

		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<?> entity = new HttpEntity<>(headers);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("codEtud", "35006817")
				.queryParam("annee", "2020");
		ResponseEntity<ApogeeMap> result = testRestTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
				ApogeeMap.class);

		
		System.out.println(result.getBody().getListeEtapeInscriptions());
		// etapesByEtudiantAndAnnee?codeEtud=38004031&annee=2020

	}

	@Test
	public void etapesByEtudiantAndAnnee() {
		ResponseSpec responseSpec = WebClient.builder().baseUrl(baseUrl)
				.defaultHeaders(header -> header.setBasicAuth(login, password)).build().get()
				.uri(uriBuilder -> uriBuilder.path("/apogee/etapesByEtudiantAndAnnee").queryParam("codEtud", "35006817")
						.queryParam("annee", "2020").build())
				.retrieve();

		Mono<ApogeeMap> mono = responseSpec.bodyToMono(ApogeeMap.class);
		ApogeeMap apogeeMap = mono.block();

		System.out.println(apogeeMap);
	}

}