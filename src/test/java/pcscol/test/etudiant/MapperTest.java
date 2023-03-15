package pcscol.test.etudiant;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.esupportail.referentiel.beans.EtudiantInfoAdm;
import org.esupportail.referentiel.pcscol.mapper.ApprenantEtuInfoAdmMapper;
import org.esupportail.referentiel.pcscol.model.sta.Apprenant;
import org.esupportail.referentiel.utils.Utils;
import org.junit.jupiter.api.Test;

public class MapperTest {
	
	
	@Test
	public void testMapperApp() {
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		
		Apprenant app=new Apprenant();
		app.setCodeApprenant("111111");
		app.setNomDeNaissance("dupond");
		app.setPrenom("jean");
		Date d=Calendar.getInstance().getTime();
		String df = dateFormat.format(d);
		app.setDateDeNaissance(d);
		
		EtudiantInfoAdm etu = ApprenantEtuInfoAdmMapper.Instance.apprenantToEtudiantInfoAdm(app);
		assertEquals("jean", etu.getPrenom1());
		assertEquals(df, etu.getDateNaissance());
	}

}
