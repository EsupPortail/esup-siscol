package org.esupportail.referentiel.pcscol.chcv6;

import java.util.List;

import org.esupportail.referentiel.Siscol;
import org.esupportail.referentiel.beans.ElementPedagogique;
import org.esupportail.referentiel.pcscol.invoker.ApiException;
import org.esupportail.referentiel.pcscol.services.ChcService;
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
public class CursusDcaApiTest {

	@Autowired
	private ChcService chcService;

	/**
	 * Liste des cursus de l&#39;apprenant
	 *
	 * 
	 *
	 * @throws ApiException if the Api call fails
	 */
	@Test
	public void lireCusrsuApprenantTest() throws ApiException {
		String codeApprenant = "000000001";
		String codStructure = "ETAB00";
		List<ElementPedagogique> response = chcService.lirelisteElementPedagogiqueStageApprenant(codeApprenant,
				codStructure);
		System.out.println(response);

	}

}
