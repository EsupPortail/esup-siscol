/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package apogee.test.etudiant;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.esupportail.referentiel.SpringBootTomcatApplication;
import org.esupportail.referentiel.beans.ApogeeMap;
import org.esupportail.referentiel.beans.EtabRef;
import org.esupportail.referentiel.beans.EtapeInscription;
import org.esupportail.referentiel.beans.EtudiantRef;
import org.esupportail.referentiel.beans.RegimeInscription;
import org.esupportail.referentiel.services.StudentComponentRepositoryDao;
import org.esupportail.referentiel.services.impl.StudentDataRepositoryDaoWS;
import org.esupportail.referentiel.ws.services.EtudiantMetierClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import gouv.education.apogee.commun.client.ws.AdministratifMetier.InsAdmAnuDTO2;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.CoordonneesDTO2;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.IdentifiantsEtudiantDTO2;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.InfoAdmEtuDTO4;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.ListeElementPedagogiDTO2;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.VersionDiplomeDTO3;

/**
 * @author abdelhamid
 *
 */
@ContextConfiguration(classes = { SpringBootTomcatApplication.class })
@SpringBootTest
@SpringJUnitConfig
@ActiveProfiles("test")
public class TestEtudiantMetier {

	String numEtud = "230005";
	String anneeEncours = "2023";

	@Autowired
	EtudiantMetierClient etudiantMetierClient;

	@Autowired
	StudentDataRepositoryDaoWS studentDataRepositoryDaoWS;

	@Autowired
	StudentComponentRepositoryDao studentComponentRepositoryDao;
	
	
	private String universityCode="UPN";

	@Test
	public void testgetEtabRef() {

		
		String id = "35001768";
		String annee = "2020";
		boolean temRecupAnneeAntecedente = true;

		EtabRef test = studentComponentRepositoryDao.getEtabRef(universityCode);
		System.out.println(test.getNomEtabRef());

		EtudiantRef ff = studentDataRepositoryDaoWS.recupererEtudiantApogee(universityCode, id, annee,
				temRecupAnneeAntecedente);

	}

	@Test
	public void testgetinfosAdmEtu() {
		InfoAdmEtuDTO4 student = studentDataRepositoryDaoWS.recupererInfosAdmEtuV4(numEtud);
		System.out.println(student.getNomPatronymique());
		System.out.println(student.getAnneePremiereInscEtb());
		assertEquals("DEVE", student.getNomPatronymique());
		System.out.println(student.getDepartementNaissance().getLibDept());
		System.out.println(student.getSexEtatCivil());
		System.out.println(student.getPrenomEtatCivil());
		
		

	}

	@Test
	public void tesIdentifiantsEtudiantDTO2() {
		IdentifiantsEtudiantDTO2 id = studentDataRepositoryDaoWS.recupererIdentifiantsEtudiantDTO2(numEtud);
		System.out.println(id.getEmailAnnuaire());
		assertEquals(numEtud, id.getLoginAnnuaire());

	}

	@Test
	public void tesRecupererCoordonneesDTO2() {
		CoordonneesDTO2 coordonnees = studentDataRepositoryDaoWS.recupererCoordonneesDTO2(numEtud, "2020");
		System.out.println(coordonnees.getLoginAnnuaire());
		System.out.println(coordonnees.getAdresseFixe().getCommune());
		assertEquals("MEULAN-EN-YVELINES", coordonnees.getAdresseFixe().getCommune().getNomCommune());

	}

	@Test
	public void tesrecupererIAAnnuellesV2() {

		List<String> annees = studentDataRepositoryDaoWS.recupererAnneesIa(numEtud);

		for (String annee : annees) {
			List<InsAdmAnuDTO2> insAdmAnuDTO2 = studentDataRepositoryDaoWS.recupererIAAnnuellesV2(numEtud, annee);
			for (InsAdmAnuDTO2 s : insAdmAnuDTO2) {
				System.out.println("+++++++++++++++");
				System.out.println(s.getAnneeIAA());
				System.out.println(s.getDateIAA());

			}
		}

	}

	@Test
	public void testgetEtapesByEtudiantAndAnnee() {
		ApogeeMap map = studentDataRepositoryDaoWS.recupererEtapesByEtudiantAndAnnee(numEtud, anneeEncours, "");
		System.out.println(map.getStudentsEtapesVets().get("P1PSY"));
		System.out.println(map.getListeELPs());
		System.out.println(map.getStudentSteps());


	}

	@Test
	public void testGetStudentEtapeInscription() {
		LinkedHashMap<String, String> lEtapeInscriptions = studentDataRepositoryDaoWS.recupererEtapeVetsParEtudiantAnnee(numEtud,
				"2020");
		if (lEtapeInscriptions!=null) {
			lEtapeInscriptions.forEach( (k,v)->System.out.println(k+" : "+ v+" : "));
		}

	}

	@Test
	public void testGetStudentEtapeVets() {
		List<EtapeInscription> lEtapeInscriptions = studentDataRepositoryDaoWS.recupererEtapeInscriptionParEtudiantAnnee(numEtud,
				"2020");
		for (EtapeInscription l : lEtapeInscriptions) {
			System.out.println(l.getCodeDiplome());
		}

	}

	//@Test
	public void testGetStudentELP() {
		LinkedHashMap<VersionDiplomeDTO3, List<ListeElementPedagogiDTO2>> elps = studentDataRepositoryDaoWS
				.recupererElpsParEtape("P3PSY", "201");

		for (Entry<VersionDiplomeDTO3, List<ListeElementPedagogiDTO2>> l : elps.entrySet()) {
			System.out.println(l.getKey().getOffreFormation().getListEtape().getItem().size());
			System.out.println(l.getKey() + " :" + l.getValue());

		}

		LinkedHashMap<String, String> lEtapeInscriptions = studentDataRepositoryDaoWS.recupererEtapeVetsParEtudiantAnnee(numEtud,
				"2020");

		for (Entry<String, String> l : lEtapeInscriptions.entrySet()) {
			LinkedHashMap<VersionDiplomeDTO3, List<ListeElementPedagogiDTO2>> elsps = studentDataRepositoryDaoWS
					.recupererElpsParEtape(l.getKey(), l.getValue());

			for (Entry<VersionDiplomeDTO3, List<ListeElementPedagogiDTO2>> l1 : elsps.entrySet()) {

				System.out.println(l1.getKey() + " :" + l1.getValue());
				System.out.println(l1.getKey().getOffreFormation().getListEtape().getItem().size());

			}
		}

	}
	@Test
	public void testRegimeInscription() {
		List<RegimeInscription> rr = etudiantMetierClient.regimeInscriptionEtudiant("35006817", "2021");
		etudiantMetierClient.recuperereRegimeInscription(rr.get(0).getRegimeIns(), "O");
	}
}
