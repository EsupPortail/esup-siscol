package org.esupportail.referentiel.pcscol.chcv6;

import java.util.List;

import org.esupportail.referentiel.Siscol;
import org.esupportail.referentiel.beans.ElementPedagogique;
import org.esupportail.referentiel.pcscol.chcv6.model.ArbreLecture;
import org.esupportail.referentiel.pcscol.chcv6.model.CursusDCA;
import org.esupportail.referentiel.pcscol.chcv6.model.ObjetMaquette;
import org.esupportail.referentiel.pcscol.invoker.ApiException;
import org.esupportail.referentiel.pcscol.odf.model.ObjetMaquetteDetail;
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
	public void lirelisteElementPedagogiqueStageApprenantTest() throws ApiException {
		String codeApprenant = "000000001";
		String codStructure = "ETAB00";
		List<ElementPedagogique> response = chcService.lirelisteElementPedagogiqueStageApprenant(codeApprenant,
				codStructure);
		System.out.println(response);

	}

	@Test
	public void lireCusrsuApprenantTest() throws ApiException {
		String codeApprenant = "000000001";
		String codStructure = "ETAB00";
		List<CursusDCA> response = chcService.lireCusrsuApprenant(codeApprenant);
		System.out.println(response);

	}
	
	@Test
	public void lireCusrsuTest() throws ApiException {
		String codStructure = "ETAB00";
		String codePeriod="PER-2020";
		 List<ObjetMaquette> response = chcService.lireListeFormationAll(codStructure, codePeriod);
		System.out.println(response.size());
		
		ObjetMaquette o1 = response.get(0);
		ArbreLecture arbre = chcService.arbrePourUneFormation(codStructure, o1.getCodePeriode(),o1.getFormation().getCode());
		System.out.println(arbre.getFormationArbre());
		
//		response.forEach(o->{
//			System.out.println(o.getFormation().getCode()+ ":"+o.getFormation().getType()+" :"+o.getCodePeriode());
//			System.out.println("o.getCodeChemin() : "+o.getCodeChemin());
//			ArbreLecture arbre = chcService.arbrePourUneFormation(codStructure, o.getCodePeriode(),o.getFormation().getCode());
//			System.out.println(arbre);
//		});
		
	}


}
