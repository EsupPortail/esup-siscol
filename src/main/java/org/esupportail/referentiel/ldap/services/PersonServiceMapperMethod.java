package org.esupportail.referentiel.ldap.services;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.esupportail.referentiel.conf.LdapAttributesConf;
import org.esupportail.referentiel.ldap.entities.Person;
import org.esupportail.referentiel.ldap.repositories.PersonRepository;
import org.esupportail.referentiel.ldap.services.interfaces.LdapServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.ldap.filter.HardcodedFilter;
import org.springframework.stereotype.Service;

@Service(value = "personServiceMapperMethod")
public class PersonServiceMapperMethod implements LdapServiceInterface {

	final private transient Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${app.ldap.stringFilterTeacher}")
	private String stringFilterTeacher;

	@Value("${app.ldap.stringFilterStudent}")
	private String stringFilterStudent;

	@Value("${app.ldap.stringFilterStaff}")
	private String stringFilterStaff;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private LdapTemplate ldapTemplate;
	@Value("${app.ldap.attributes.baseDn}")
	private String dnPeople;

	@Autowired
	private LdapAttributesConf ldapAtributes;

	public String getStringFilterTeacher() {
		return stringFilterTeacher;
	}

	public void setStringFilterTeacher(String stringFilterTeacher) {
		this.stringFilterTeacher = stringFilterTeacher;
	}

	public String getStringFilterStudent() {
		return stringFilterStudent;
	}

	public void setStringFilterStudent(String stringFilterStudent) {
		this.stringFilterStudent = stringFilterStudent;
	}

	public String getStringFilterStaff() {
		return stringFilterStaff;
	}

	public void setStringFilterStaff(String stringFilterStaff) {
		this.stringFilterStaff = stringFilterStaff;
	}

	public PersonRepository getPersonRepository() {
		return personRepository;
	}

	public void setPersonRepository(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public LdapTemplate getLdapTemplate() {
		return ldapTemplate;
	}

	public void setLdapTemplate(LdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}

	public String getDnPeople() {
		return dnPeople;
	}

	public void setDnPeople(String dnPeople) {
		this.dnPeople = dnPeople;
	}

	public LdapAttributesConf getLdapAtributes() {
		return ldapAtributes;
	}

	public void setLdapAtributes(LdapAttributesConf ldapAtributes) {
		this.ldapAtributes = ldapAtributes;
	}

	@Override
	public List<Person> findFacultyByFilter(Filter filter) {
		AndFilter global_f = new AndFilter();
		global_f.and(filter).and(new HardcodedFilter(stringFilterTeacher));
		return findPersonByFilter(global_f);
	}

	@Override
	public List<Person> findStudentByFilter(Filter filter) {
		AndFilter global_f = new AndFilter();
		global_f.and(filter).and(new HardcodedFilter(stringFilterStudent));
		return findPersonByFilter(global_f);
	}

	@Override
	public List<Person> findStaffByFilter(Filter filter) {
		AndFilter global_f = new AndFilter();
		global_f.and(filter).and(new HardcodedFilter(stringFilterStaff));
		return findPersonByFilter(global_f);
	}

	@Override
	public Person findByUid(String uid) {
		try {
			List<Person> persons = ldapTemplate.search(query().base(ldapAtributes.getBaseDn()).where("objectclass")
					.is(ldapAtributes.getObjectClass()).and(ldapAtributes.getUid()).is(uid),
					new PersonContextMapper(ldapAtributes));
			return persons.get(0);
		} catch (Exception e) {
			logger.error("LDAP EXCEPTION ", e);
			return null;
		}

	}

	@Override
	public Person findByCodEtu(String CodEtu) {
		try {
			List<Person> persons = ldapTemplate.search(query().base(ldapAtributes.getBaseDn()).where("objectclass")
					.is(ldapAtributes.getObjectClass()).and(ldapAtributes.getCodEtu()).is(CodEtu),
					new PersonContextMapper(ldapAtributes));
			return persons.get(0);
		} catch (Exception e) {
			logger.error("LDAP EXCEPTION ", e);
			return null;
		}
	}

	@Override
	public Person findBySupannAliasLogin(String supannAliasLogin) {
		try {
			List<Person> persons = ldapTemplate.search(
					query().base(ldapAtributes.getBaseDn()).where("objectclass").is(ldapAtributes.getObjectClass())
							.and(ldapAtributes.getSupannAliasLogin()).is(supannAliasLogin),
					new PersonContextMapper(ldapAtributes));
			if (persons != null && !persons.isEmpty())
				return persons.get(0);
			else
				return null;
		} catch (Exception e) {
			logger.error(" ", e.getCause(), e.getCause());
			return null;
		}
	}

	@Override
	public List<Person> findPersonByFilter(Filter formAsFliter) {
		logger.debug("formAsFliter : " + formAsFliter.toString());
		List<Person> persons = ldapTemplate.search((query().base(ldapAtributes.getBaseDn()).filter(formAsFliter)),
				new PersonContextMapper(ldapAtributes));
		return persons;
	}

	@Override
	public List<Person> findPersonByFilter(String filter, String base) {
		logger.debug("formAsFliter : " + filter.toString());
		if (base != null && base.equals("default")) {
			base = dnPeople;
		}
		List<Person> persons = new ArrayList<>();
		try {
			HardcodedFilter s_f = new HardcodedFilter(filter);
			persons = ldapTemplate.search((query().countLimit(10).base(base).filter(s_f)),
					new PersonContextMapper(ldapAtributes));
		} catch (Exception e) {
			logger.error("findPersonByFilter : " + filter + " error  :" + e.getMessage() + "  cause : "
					+ e.getCause().getMessage());
			logger.debug(" findPersonByFilter  " + e);

		}
		return persons;
	}

	private static class PersonContextMapper extends AbstractContextMapper<Person> {
		protected LdapAttributesConf ldapAtributes;

		public PersonContextMapper(LdapAttributesConf ldapAtributes) {
			this.ldapAtributes = ldapAtributes;
		}

		public Person doMapFromContext(DirContextOperations context) {
			Person person = new Person();

			person.setDn(context.getDn());

			person.setCn(context.getStringAttribute(ldapAtributes.getCn()));

			if (context.getStringAttributes(ldapAtributes.getSn()) != null)
				person.setSn(Arrays.asList(context.getStringAttributes(ldapAtributes.getSn())));

			person.setDisplayName(context.getStringAttribute(ldapAtributes.getDisplayName()));

			person.setDn(context.getDn());

			person.setUid(context.getStringAttribute(ldapAtributes.getUid()));

			person.setCodEtu(context.getStringAttribute(ldapAtributes.getCodEtu()));

			person.setDisplayName(context.getStringAttribute(ldapAtributes.getDisplayName()));

			person.setEduPersonPrimaryAffiliation(
					context.getStringAttribute(ldapAtributes.getEduPersonPrimaryAffiliation()));

			if (context.getStringAttributes(ldapAtributes.getEduPersonAffiliation()) != null) {
				person.setEduPersonAffiliation(
						Arrays.asList(context.getStringAttributes(ldapAtributes.getEduPersonAffiliation())));
			}

			person.setEduPersonOrgDN(context.getStringAttribute(ldapAtributes.getEduPersonOrgDN()));

			person.setGivenName(Arrays.asList((context.getStringAttributes(ldapAtributes.getGivenName()))));

			person.setMail(context.getStringAttribute(ldapAtributes.getMail()));

			person.setSupannAliasLogin(context.getStringAttribute(ldapAtributes.getSupannAliasLogin()));

			person.setSupannAutreMail(context.getStringAttribute(ldapAtributes.getSupannAutreMail()));

			person.setSupannCivilite(context.getStringAttribute(ldapAtributes.getSupannCivilite()));

			person.setSupannEmpId(context.getStringAttribute(ldapAtributes.getSupannEmpId()));

			if (context.getStringAttributes(ldapAtributes.getSupannEntiteAffectation()) != null) {
				person.setSupannEntiteAffectation(
						Arrays.asList(context.getStringAttributes(ldapAtributes.getSupannEntiteAffectation())));
			}

			person.setSupannEntiteAffectationPrincipale(
					context.getStringAttribute(ldapAtributes.getSupannEntiteAffectationPrincipale()));

			if (context.getStringAttributes(ldapAtributes.getSupannEtuCursusAnnee()) != null) {
				person.setSupannEtuCursusAnnee(
						Arrays.asList(context.getStringAttributes(ldapAtributes.getSupannEtuCursusAnnee())));
			}

			person.setSupannEtuId(context.getStringAttribute(ldapAtributes.getSupannEtuId()));

			person.setSupannRefId(context.getStringAttribute(ldapAtributes.getSupannRefId()));

			person.setTelephoneNumber(context.getStringAttribute(ldapAtributes.getTelephoneNumber()));

			// person.setDescription(context.getStringAttribute("description"));
			return person;
		}
	}

}
