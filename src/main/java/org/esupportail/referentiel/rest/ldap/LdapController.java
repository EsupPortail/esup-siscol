package org.esupportail.referentiel.rest.ldap;

import java.util.List;

import org.esupportail.referentiel.conf.LdapAttributesConf;
import org.esupportail.referentiel.ldap.entities.FormSearch;
import org.esupportail.referentiel.ldap.entities.Person;
import org.esupportail.referentiel.ldap.services.interfaces.LdapServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

	@Value("${app.ldap.attributes.baseDn}")
	private String baseLdap;

	@Value("${app.ldap.attributes.supannEntiteAffectation}")
	private String supannEntiteAffectation;

	@Value("${app.ldap.attributes.supannEtuEtape}")
	private String supannEtuEtape;

	@Value("${app.ldap.parametres.supannEtuEtapePrefix}")
	private String supannEtuEtapePrefix;

	@Value("${app.ldap.parametres.supannEtuEtapeSepVetVersion}")
	private String supannEtuEtapeSepVetVersion;
	
	@Value("${app.ldap.parametres.supannEtuEtapeExisteVetVersion}")
	private boolean supannEtuEtapeExisteVetVersion;

	@Autowired
	@Qualifier("personServiceMapperMethod")
	LdapServiceInterface personService;

	@Autowired
	private LdapAttributesConf ldapAtributes;

	@PostMapping("/person")
	public List<Person> serachByForm(@RequestBody FormSearch member) {
		logger.trace("serachByForm : " + member);
		List<Person> list_person = personService.findPersonByFilter(member.formAsFliter(ldapAtributes));
		logger.trace(" Result serachByForm : " + list_person);
		return list_person;
	}

	@PostMapping("/tuteur")
	public List<Person> serachEnseigantByForm(@RequestBody FormSearch member) {
		member.setAffiliation(null);
		member.setPrimaryAffiliation(null);
		List<Person> list_person = personService.findFacultyByFilter(member.formAsFliter(ldapAtributes));
		return list_person;
	}

	@PostMapping("/etudiant")
	public List<Person> serachStudentByForm(@RequestBody FormSearch member) {
		member.setAffiliation(null);
		member.setPrimaryAffiliation(null);
		List<Person> list_person = personService.findStudentByFilter(member.formAsFliter(ldapAtributes));
		return list_person;
	}

	@PostMapping("/staff")
	public List<Person> serachStaffByForm(@RequestBody FormSearch member) {
		member.setAffiliation(null);
		member.setPrimaryAffiliation(null);
		List<Person> list_person = personService.findStaffByFilter(member.formAsFliter(ldapAtributes));
		return list_person;
	}

	@GetMapping("/byFilter")
	public List<Person> findByFilter(@RequestParam(value = "filter") String filter,
			@RequestParam(value = "base", defaultValue = "default") String base) {
		logger.trace("LDAP findByFilter  : " + base + " : " + filter);
		if (base.equals("default")) {
			base = this.baseLdap;
		}
		List<Person> result = personService.findPersonByFilter(filter, base);
		return result;
	}

	// {SIHAM}RAVU
	// supannEntiteAffectation
	@GetMapping("/personByComposante")
	public List<Person> findByComposante(
			@RequestParam(value = "codeComposanteLdap", required = true, defaultValue = "{SIHAM}RAVU") String codeEtape) {
		String filter_base = this.supannEntiteAffectation + "=%s";
		String filter = String.format(filter_base, codeEtape);
		logger.trace("LDAP EtuByEtape  :  " + filter);

		List<Person> result = personService.findPersonByFilter(filter, this.baseLdap);

		return result;
	}

	@GetMapping("/EtuByEtape")
	public List<Person> findByEtape(
			@RequestParam(value = "codeEtapeLdap", required = true, defaultValue = "J5DRTH") String codeEtape,
			@RequestParam(value = "versionEtapeLdap", required = false) String versionEtapeLdap) {
		String codeEtapeLdap = "";
		if (this.supannEtuEtapePrefix != null && !this.supannEtuEtapePrefix.isEmpty()
				&& !this.supannEtuEtapePrefix.isBlank()) {
			codeEtapeLdap = this.supannEtuEtapePrefix + codeEtape;
		} else {
			codeEtapeLdap = codeEtape;
		}
		
		if (supannEtuEtapeExisteVetVersion) {
			if (versionEtapeLdap != null && !versionEtapeLdap.isEmpty() && !this.supannEtuEtapeSepVetVersion.isBlank()) {
				if (this.supannEtuEtapeSepVetVersion != null && !this.supannEtuEtapeSepVetVersion.isEmpty()
						&& !this.supannEtuEtapeSepVetVersion.isBlank()) {
					codeEtapeLdap = codeEtapeLdap + this.supannEtuEtapeSepVetVersion;
				}
				
				codeEtapeLdap = codeEtapeLdap + versionEtapeLdap;
			}
			
		}
		

		

		String filter_base = this.supannEtuEtape + "=%s";
		String filter = String.format(filter_base, codeEtapeLdap);
		logger.trace("LDAP EtuByEtape  :  " + filter);

		List<Person> result = personService.findPersonByFilter(filter, this.baseLdap);

		return result;
	}

	@GetMapping("/bySupannAliasLogin")
	public Person findBySupannAliasLogin(@RequestParam(value = "login") String supannAliasLogin,
			@RequestParam(value = "base", defaultValue = "default") String base) {
		logger.trace("LDAP findByFilter  : " + base + " : " + supannAliasLogin);
		if (base.equals("default")) {
			base = this.baseLdap;
		}
		Person result = personService.findBySupannAliasLogin(supannAliasLogin);
		return result;
	}

	@GetMapping("/byCodEtu")
	public Person findByCodEtu(@RequestParam(value = "codEtu") String codEtu,
			@RequestParam(value = "base", defaultValue = "default") String base) {
		logger.trace("LDAP findfindByCodEtu  : " + base + " : " + codEtu);
		if (base.equals("default")) {
			base = this.baseLdap;
		}
		Person result = personService.findByCodEtu(codEtu);
		return result;
	}

	public String getBaseLdap() {
		return baseLdap;
	}

	public void setBaseLdap(String baseLdap) {
		this.baseLdap = baseLdap;
	}

	public LdapAttributesConf getLdapAtributes() {
		return ldapAtributes;
	}

	public void setLdapAtributes(LdapAttributesConf ldapAtributes) {
		this.ldapAtributes = ldapAtributes;
	}

}
