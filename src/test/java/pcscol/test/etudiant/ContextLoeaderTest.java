package pcscol.test.etudiant;

import org.esupportail.referentiel.SpringBootTomcatApplication;
import org.esupportail.referentiel.pcscol.invoker.ApiException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@ContextConfiguration(classes = { SpringBootTomcatApplication.class })
@SpringBootTest
@SpringJUnitConfig
@ActiveProfiles("test")
public class ContextLoeaderTest {



	String codeStructure = "ETAB00";
	String codePeriode = "PER-2020";
	String codeApprenant = "000000036"; // String | Le code Pegase de l'apprenant

	@Test
	public void loader() {
		//assertNotNull(stagesApi);
			}

	@Test
	public void listerInscriptionsAvecStagesTest() {
		try {
			
			
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		// TODO: test validations
	}
	
	@Test
	public void lireListeTest() throws ApiException {

		
		// TODO: test validations
	}

	@Test
	public void lireListeStructureTest() throws ApiException {
	
	
		// TODO: test validations
	}
	
	@Test
	public void lireListeAPPTest() throws ApiException {
		
		
	}
	
	

}
