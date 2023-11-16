package apogee.test.etudiant;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Base64;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import gouv.education.apogee.commun.client.ws.EtudiantMetier.EtudiantMetierServiceInterface;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.EtudiantMetierServiceInterfaceService;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.InfoAdmEtuDTO4;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.WebBaseException_Exception;
import reactor.core.publisher.Mono;

public class WSTestAuth {
	

	
	
	public void etapesByEtudiantAndAnnee() {
		
		try {
			ResponseSpec responseSpec = WebClient.builder().baseUrl("http://univ.fr:8080")
					.defaultHeaders(header -> header.setBasicAuth("user", "pwd")).build().get()
					.uri(uriBuilder -> uriBuilder.path("/aws/services").build())
					.retrieve();

			Mono<String> mono = responseSpec.bodyToMono(String.class);
			String apogeeMap = mono.block();

			System.out.println(apogeeMap);
		 
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void etudiantMetier() throws IOException {
		URL wsdlLocation = wsdlLocation("http://univ.fr/aws/services/EtudiantMetier");

		Authenticator myAuth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("user", "pwd".toCharArray());
			}
		};

		Authenticator.setDefault(myAuth);
		
		
		
		EtudiantMetierServiceInterfaceService client = new EtudiantMetierServiceInterfaceService(wsdlLocation);
		
		EtudiantMetierServiceInterface clientMetier = client.getEtudiantMetier();
		
		System.out.println(clientMetier);
		try {
			InfoAdmEtuDTO4 info = clientMetier.recupererInfosAdmEtuV4("35006817");
			
			
			System.out.println(info.getNomPatronymique());
			assertEquals("Boop", info.getNomPatronymique());
		} catch (WebBaseException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void test2() throws IOException {
		URL ndurl = new URL("http://univ.fr/aws/services/EtudiantMetier?wsdl");
	
		
		HttpURLConnection ndconn = (HttpURLConnection)ndurl.openConnection();
		//HTTP Basic authentification
		String auth = new String("user:pwd");
		Base64.getEncoder().encodeToString(auth.getBytes());
		//BASE64Encoder b64 = new BASE64Encoder();
		ndconn.setRequestProperty ("Authorization", "Basic " + Base64.getEncoder().encodeToString(auth.getBytes()));
		
		System.out.println(ndconn.getResponseCode());
		/**
		 *   con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString((applicationBootstrap.getAppConfig().getReferentielWsLogin() + ":" + applicationBootstrap.getAppConfig().getReferentielWsPassword()).getBytes()));

            if (con.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur code " + con.getResponseCode() + " " + urlWithQuery);
            }

		 */

	}
	
	
	private URL wsdlLocation(String url) {
		URL wsdlLocation = null;
		try {
			wsdlLocation = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wsdlLocation;
	}


}
