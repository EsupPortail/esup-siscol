package apogee.test.etudiant;

import java.net.URI;
import java.util.List;

import org.esupportail.referentiel.beans.ApogeeMap;
import org.esupportail.referentiel.ldap.entities.FormSearch;
import org.esupportail.referentiel.ldap.entities.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.core.publisher.Mono;

public class TestLdap {

	String baseUrl = "http://cri146.admc.parisnanterre.fr:8080";
	String login = "root";
	String password = "root";

	@Test
	public void testLdapWebCLient() {
		FormSearchTest person = new FormSearchTest("boop", "");
		Mono<FormSearchTest> m = Mono.just(person);
		Mono<Person[]> result = ldapSearchByForm(m);
		Person[] list = result.block();
		for (Person p : list) {
			System.out.println(p.getCn()+ ": " +p.getEduPersonPrimaryAffiliation());
			System.out.println(p);
		}
	}

	@Test
	public void returnDefaultMessage() throws Exception {
		TestRestTemplate testRestTemplate = new TestRestTemplate(login, password,
				TestRestTemplate.HttpClientOption.ENABLE_COOKIES);
		final String url = baseUrl + "/ldap/etudiant";
		URI uri = new URI(url);
		FormSearchTest person = new FormSearchTest("boop", "");

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-PERSIST", "true");

		HttpEntity<FormSearchTest> request = new HttpEntity<>(person, headers);

		Person[] result = testRestTemplate.postForObject(uri, request, Person[].class);

		System.out.println(result[0]);

	}

	public Mono<Person[]> ldapSearchByForm(Mono<FormSearchTest> form) {
		WebClient webClient = WebClient.builder().baseUrl(baseUrl)
				.defaultHeaders(header -> header.setBasicAuth(login, password)).build();
		return webClient.post().uri("/ldap/etudiant").accept(MediaType.APPLICATION_JSON)
				.body(form, FormSearchTest.class).retrieve().bodyToMono(Person[].class);
	}
}
