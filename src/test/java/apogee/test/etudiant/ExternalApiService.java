package apogee.test.etudiant;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ExternalApiService {
	
private final RestTemplate restTemplate;
    
    public ExternalApiService(RestTemplateBuilder restTemplateBuilder) {
        // Create RestTemplate with default configuration
        this.restTemplate = restTemplateBuilder.build();
    }
    
    public ResponseEntity<String> callExternalApiWithBasicAuth(String url, String username, String password) {
        // Create headers with Basic Authentication
        HttpHeaders headers = new HttpHeaders();
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
        
        // Create request entity with headers
        org.springframework.http.HttpEntity<String> entity = new org.springframework.http.HttpEntity<>(headers);
        
        // Make the request and return the response
        return restTemplate.exchange(url, org.springframework.http.HttpMethod.GET, entity, String.class);
    }

}
