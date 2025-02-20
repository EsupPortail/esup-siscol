package org.esupportail.referentiel.pcscol.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.esupportail.referentiel.SpringBootTomcatApplication;
import org.esupportail.referentiel.beans.DiplomeReduitDto;
import org.esupportail.referentiel.pcscol.invoker.ApiException;
import org.esupportail.referentiel.pcscol.odf.model.ObjetMaquetteSummary;
import org.esupportail.referentiel.pcscol.odf.model.TypeObjetMaquette;
import org.esupportail.referentiel.pcscol.services.OffreFormationService;
import org.esupportail.referentiel.pcscol.services.PcscolService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@ContextConfiguration(classes = { SpringBootTomcatApplication.class })
@SpringBootTest
@SpringJUnitConfig
@ActiveProfiles("test")
public class RestTest {

	@Autowired
	private PcscolService pcscolService;

	@Autowired
	OffreFormationService offreFormationService;

	@Value("${app.pcscol.codeStructure}")
	String codeStructure = "ETAB00";

	@Value("${app.pcscol.codePeriode}")
	String codePeriode = "PER-2020";

	
	String codesPeriodesChargementFormations = null;

	@Test
	public void testDiplomes() {
		List<DiplomeReduitDto> result = pcscolService.diplomeRef(codeStructure, codesPeriodesChargementFormations);
		System.out.println(result.size());
	}

	@Test
	public void rechercherObjetMaquetteTest() throws ApiException {

		Map<String, String> test = offreFormationService.rechercherObjetMaquetteFormation(codeStructure, "PER-2021");
		for (String s : test.keySet()) {
			System.out.println(s + " : " + test.get(s));
		}
	}
	
	
	@Test
	public void rechercherObjetMaquetteTest2() throws ApiException {

		List<TypeObjetMaquette> typeObjetMaquette=new ArrayList<TypeObjetMaquette>();
		TypeObjetMaquette tom=TypeObjetMaquette.FORMATION;
		typeObjetMaquette.add(tom);
		Boolean racine=true;
		String typeObjetFormation=null;
		List<UUID> ids=null;
		String espace=null;
		
		 List<ObjetMaquetteSummary> test = offreFormationService.rechercheObjetMaquetteSummary(codeStructure,
				typeObjetMaquette,  racine, typeObjetFormation,  ids,espace);
		System.out.println(test.size());
	}
	
	
}
