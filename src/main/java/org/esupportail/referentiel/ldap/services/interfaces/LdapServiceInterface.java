package org.esupportail.referentiel.ldap.services.interfaces;

import java.util.List;

import org.esupportail.referentiel.ldap.entities.Person;
import org.springframework.ldap.filter.Filter;

public interface LdapServiceInterface {
	
	public List<Person> findFacultyByFilter(Filter filter) ;
	
	public List<Person> findStudentByFilter(Filter filter);
	
	public List<Person> findStaffByFilter(Filter filter);
	
	public Person findByUid(String uid);
	
	public Person findBySupannAliasLogin(String supannAliasLogin);
	
	public List<Person> findPersonByFilter(Filter formAsFliter);
	
	public List<Person> findPersonByFilter(String filter, String base);
	

}
