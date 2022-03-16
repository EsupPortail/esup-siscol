package org.esupportail.referentiel.ldap.services;

import java.util.List;

import org.esupportail.referentiel.ldap.entities.Person;
import org.esupportail.referentiel.ldap.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Streamable;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.ldap.filter.HardcodedFilter;
import org.springframework.ldap.filter.WhitespaceWildcardsFilter;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
	
	
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
	@Value("${app.ldap.dnPeople}")
	private String dnPeople;

	/**
	 * 
	 * @param filter
	 * @return List<Person>
	 */
	public List<Person> findFacultyByFilter(Filter filter) {
		AndFilter global_f = new AndFilter();
		global_f.and(filter).and(new HardcodedFilter(stringFilterTeacher));
		LdapQuery query = LdapQueryBuilder.query().base(dnPeople).filter(global_f);
		logger.trace(query.toString());
		List<Person> faculties = (List<Person>) personRepository.findAll(query);
		return faculties;
	}

	/**
	 * 
	 * @param filter
	 * @return List<Person>
	 */
	public List<Person> findStudentByFilter(Filter filter) {
		HardcodedFilter s_f = new HardcodedFilter(stringFilterStudent);
		AndFilter global_f = new AndFilter();
		global_f.and(s_f).and(filter);
		LdapQuery query = LdapQueryBuilder.query().base(dnPeople).countLimit(10).filter(global_f);
		logger.trace(query.toString());
		List<Person> students = (List<Person>) personRepository.findAll(query);
		return students;
	}

	/**
	 * 
	 * @param filter
	 * @return List<Person>
	 */
	public List<Person> findStaffByFilter(Filter filter) {
		HardcodedFilter s_f = new HardcodedFilter(stringFilterStaff);
		AndFilter global_f = new AndFilter();
		global_f.and(s_f).and(filter);
		LdapQuery query = LdapQueryBuilder.query().base(dnPeople).countLimit(10).filter(global_f);
		logger.trace(query.toString());
		List<Person> students = (List<Person>) personRepository.findAll(query);
		return students;
	}

	public Person findByUid(String uid) {
		Person result = personRepository.findByUid(uid);
		return result;
	}
	
	public Person findBySupannAliasLogin(String supannAliasLogin) {
		Person result = personRepository.findBySupannAliasLogin(supannAliasLogin);
		return result;
	}
	
	

	/**
	 * 
	 * @param Filter filter
	 * @return List<Person>
	 */
	public List<Person> findPersonByFilter(Filter formAsFliter) {
		LdapQuery query = LdapQueryBuilder.query().base(dnPeople).countLimit(10).filter(formAsFliter);
		List<Person> all = (List<Person>) personRepository.findAll(query);
		return all;
	}

	public List<Person> findPersonByFilter(String filter, String base) {

		// Streamable<Person> test = personRepository.findBySnContaining(sn);
		if (base.equals("default")) {
			base = dnPeople;
		}
		HardcodedFilter s_f = new HardcodedFilter(filter);
		LdapQuery query = LdapQueryBuilder.query().base(base).countLimit(10).filter(s_f);
		List<Person> students = (List<Person>) personRepository.findAll(query);
		return students;
	}

	public String getStringFilterTeacher() {
		return stringFilterTeacher;
	}

	public void setStringFilterTeacher(String stringFilterTeacher) {
		this.stringFilterTeacher = stringFilterTeacher;
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

	public String getStringFilterStudent() {
		return stringFilterStudent;
	}

	public void setStringFilterStudent(String stringFilterStudent) {
		this.stringFilterStudent = stringFilterStudent;
	}

	public String getDnPeople() {
		return dnPeople;
	}

	public void setDnPeople(String dnPeople) {
		this.dnPeople = dnPeople;
	}

	public String getStringFilterStaff() {
		return stringFilterStaff;
	}

	public void setStringFilterStaff(String stringFilterStaff) {
		this.stringFilterStaff = stringFilterStaff;
	}

}
