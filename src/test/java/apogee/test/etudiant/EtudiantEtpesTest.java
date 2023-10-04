package apogee.test.etudiant;

import java.util.List;

import org.esupportail.referentiel.Siscol;
import org.esupportail.referentiel.services.StudentComponentRepositoryDao;
import org.esupportail.referentiel.services.impl.StudentDataRepositoryDaoWS;
import org.esupportail.referentiel.ws.services.EtudiantMetierClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import gouv.education.apogee.commun.client.ws.EtudiantMetier.EtudiantCritereDTO;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.EtudiantCritereListeDTO;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.EtudiantDTO2;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.TableauDiplomes;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.TableauEtapes;

@ContextConfiguration(classes = { Siscol.class })
@SpringBootTest
@SpringJUnitConfig
@ActiveProfiles("test")
public class EtudiantEtpesTest {
	
	String numEtud = "35006817";
	String anneeEncours = "2022";
	
	String annee="2022";
	String codeVet="P1PSY";
	String verVet="201";
	String codeDip="PL2HPSY";
	String verDip="201";

	@Autowired
	EtudiantMetierClient etudiantMetierClient;

	@Autowired
	StudentDataRepositoryDaoWS studentDataRepositoryDaoWS;

	@Autowired
	StudentComponentRepositoryDao studentComponentRepositoryDao;
	
	
	private String universityCode="UPN";
	
	@Test
	void test1() {
		
		TableauEtapes etps=new TableauEtapes();
		EtudiantCritereListeDTO dto=new EtudiantCritereListeDTO();
		dto.setCode("P1PSY");
		dto.getListVersion().add("201");
		etps.getItem().add(dto);
		
		
		TableauDiplomes diplomes=new TableauDiplomes();
		EtudiantCritereListeDTO dtoDip=new EtudiantCritereListeDTO();
		dtoDip.setCode("PL2HPSY");
		dtoDip.getListVersion().add("201");
		diplomes.getItem().add(dtoDip);

		List<EtudiantDTO2> listEtu = etudiantMetierClient.recupererListeEtuParEtpEtDiplome("",annee, codeVet, verVet, codeDip, verDip);
		listEtu.forEach(r->{
			System.out.println(r);
		});
	}

}
