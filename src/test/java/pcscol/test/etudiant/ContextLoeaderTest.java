package pcscol.test.etudiant;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.esupportail.referentiel.Siscol;
import org.esupportail.referentiel.beans.ApogeeMap;
import org.esupportail.referentiel.pcscol.api.FormationsApi;
import org.esupportail.referentiel.pcscol.api.StagesApi;
import org.esupportail.referentiel.pcscol.api.StructuresApi;
import org.esupportail.referentiel.pcscol.invoker.ApiException;
import org.esupportail.referentiel.pcscol.mapper.GlobalMapper;
import org.esupportail.referentiel.pcscol.model.formation.Formation;
import org.esupportail.referentiel.pcscol.model.formation.Structure;
import org.esupportail.referentiel.pcscol.model.sta.StagesApprenant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@ContextConfiguration(classes = { Siscol.class })
@SpringBootTest
@SpringJUnitConfig
@ActiveProfiles("test")
public class ContextLoeaderTest {

	@Autowired
	StagesApi stagesApi;

	@Autowired
	FormationsApi formationsApi;

	@Autowired
	StructuresApi structuresApi;

	String codeStructure = "ETAB00";
	String codePeriode = "PER-2020";
	String codeApprenant = "000000036"; // String | Le code Pegase de l'apprenant

	@Test
	public void loader() {
		assertNotNull(stagesApi);
		assertNotNull(formationsApi);
		assertNotNull(structuresApi);
	}

	@Test
	public void listerInscriptionsAvecStagesTest() {
		try {
			StagesApprenant response = stagesApi.listerInscriptionsAvecStages(codeStructure, codeApprenant);
			System.out.println(response.getCodeStructure());
			ApogeeMap apogeeMap=new ApogeeMap();
			GlobalMapper.stagesApprenantStagesToApogeeMap(response, apogeeMap,"2021");
			apogeeMap.getListeELPs().forEach(e->{
				System.out.println(e.getCodElp());
			});
			
		} catch (ApiException e) {
			System.err.println(e.getMessage());
		}

		// TODO: test validations
	}
	
	@Test
	public void lireListeTest() throws ApiException {

		Boolean uniquementOuvrableAInscription = false;
		Boolean uniquementOuvrableAuChoixDuCursus = false;

		List<Formation> response = formationsApi.lireListe(codeStructure, codePeriode,
				uniquementOuvrableAInscription, uniquementOuvrableAuChoixDuCursus);
		response.forEach(r -> {
			System.out.println(r.getCode());
		});

		// TODO: test validations
	}

	@Test
	public void lireListeStructureTest() throws ApiException {
	
		List<Structure> response = structuresApi.lireListeStructure(codeStructure);
		
		response.forEach(r->{
			System.out.println(r.getCode());
		});

		// TODO: test validations
	}

}
