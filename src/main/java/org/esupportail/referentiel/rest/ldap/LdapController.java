package org.esupportail.referentiel.rest.ldap;

import java.util.List;

import org.esupportail.referentiel.ldap.entities.FormSearch;
import org.esupportail.referentiel.ldap.entities.Person;
import org.esupportail.referentiel.ldap.services.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ldap")
public class LdapController {
	
	final private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Value("${app.ldap.dnPeople}")
	private String baseLdap;

	@Autowired
	PersonService personService;

	@PostMapping("/person")
	public List<Person> serachByForm(@RequestBody FormSearch member) {
		logger.trace("serachByForm : " + member );
		List<Person> list_person = personService.findPersonByFilter(member.formAsFliter());
		logger.trace(" Result serachByForm : " + list_person );
		return list_person;
	}
	
	@PostMapping("/tuteur")
	public List<Person> serachEnseigantByForm(@RequestBody FormSearch member) {
		member.setAffiliation(null);
		member.setPrimaryAffiliation(null);
		List<Person> list_person = personService.findFacultyByFilter(member.formAsFliter());
		return list_person;
	}
	
	@PostMapping("/etudiant")
	public List<Person> serachStudentByForm(@RequestBody FormSearch member) {
		member.setAffiliation(null);
		member.setPrimaryAffiliation(null);
		List<Person> list_person = personService.findStudentByFilter(member.formAsFliter());
		return list_person;
	}
	
	@PostMapping("/staff")
	public List<Person> serachStaffByForm(@RequestBody FormSearch member) {
		member.setAffiliation(null);
		member.setPrimaryAffiliation(null);
		List<Person> list_person = personService.findStaffByFilter(member.formAsFliter());
		return list_person;
	}
	

	@GetMapping("/byFilter")
	public List<Person> findByFilter(@RequestParam(value = "filter") String filter,
			@RequestParam(value = "base", defaultValue = "default") String base) {
		logger.trace("LDAP findByFilter  : "+ base  + " : "+  filter);
		if(base.equals("default")) {
			base=this.baseLdap;
		}
		List<Person> result = personService.findPersonByFilter(filter, base);
		return result;
	}
	
	@GetMapping("/bySupannAliasLogin")
	public Person findBySupannAliasLogin(@RequestParam(value = "login") String supannAliasLogin,
			@RequestParam(value = "base", defaultValue = "default") String base) {
		logger.trace("LDAP findByFilter  : "+ base  + " : "+  supannAliasLogin);
		if(base.equals("default")) {
			base=this.baseLdap;
		}
		Person result = personService.findBySupannAliasLogin(supannAliasLogin);
		return result;
	}
	
	
	
	public String getBaseLdap() {
		return baseLdap;
	}

	public void setBaseLdap(String baseLdap) {
		this.baseLdap = baseLdap;
	}

}
