package apogee.test.etudiant;

import java.net.URI;
import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

import org.esupportail.referentiel.beans.ApogeeMap;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient.RequestHeadersSpec;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.reactive.function.client.WebClient.UriSpec;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ApogeeTestRest {

	TestRestTemplate testRestTemplate = new TestRestTemplate("root", "2021@lo&&garantiE",
			TestRestTemplate.HttpClientOption.ENABLE_COOKIES);

	RestTemplate restTemplate = new RestTemplate();

	String baseUrl="https://referentiel-test.parisnanterre.fr";
	String login="root";
	String password="2021@lo&&garantiE";
	//@Test
	public void test1() {

		String url = baseUrl+"/apogee/etapesByEtudiantAndAnnee";

		HttpHeaders headers = new HttpHeaders();

		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<?> entity = new HttpEntity<>(headers);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("codEtud", "38004031")
				.queryParam("annee", "2020");
		ResponseEntity<ApogeeMap> result = testRestTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
				ApogeeMap.class);

		System.out.println(result.getBody().getElementPedagogiques());
		System.out.println(result.getBody().getListeEtapeInscriptions());
		// etapesByEtudiantAndAnnee?codeEtud=38004031&annee=2020

	}
	@Test
	public void etapesByEtudiantAndAnnee() {
		ResponseSpec responseSpec =  WebClient.builder().baseUrl(baseUrl)
				.defaultHeaders(header -> header.setBasicAuth(login, password))
				.build()
				.get()
				.uri(uriBuilder -> uriBuilder.path("/apogee/etapesByEtudiantAndAnnee")
				.queryParam("codEtud", "38004031").
				queryParam("annee", "2020").build()).
				retrieve();
			
		Mono<ApogeeMap> mono = responseSpec.bodyToMono(ApogeeMap.class);
		ApogeeMap apogeeMap = mono.block();
	
		System.out.println(apogeeMap.getRegimeInscription().get(0).getLibRg());
	}

}