package apogee.test.etudiant;

import java.util.List;

import org.esupportail.referentiel.SpringBootTomcatApplication;
import org.esupportail.referentiel.ldap.entities.Person;
import org.esupportail.referentiel.ldap.services.interfaces.LdapServiceInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ldap.filter.HardcodedFilter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@ContextConfiguration(classes = { SpringBootTomcatApplication.class })
@SpringBootTest
@SpringJUnitConfig
@ActiveProfiles("test")
public class LdapMapperTest {

	@Autowired
	@Qualifier("personServiceMapperMethod")
	LdapServiceInterface ldapServiceInterface;

	@Test
	public void findByUid() {

		Person r = ldapServiceInterface.findByUid("boop");

		System.out.println(r.getSupannAliasLogin());
	}
	
	@Test
	public void findPersonByFilterTest() {
		String filter="(sn=*raga*)";
		HardcodedFilter s_f = new HardcodedFilter(filter);
		
		List<Person> ps = ldapServiceInterface.findPersonByFilter(s_f);
		System.out.println("***********************");
		System.out.println(ps);
	}

}
