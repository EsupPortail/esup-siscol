package org.esupportail.referentiel.ldap.repositories;

import java.util.List;

import org.esupportail.referentiel.ldap.entities.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.data.ldap.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends LdapRepository<Person> {
	
	Person findByUid(String uid);

	List<Person> findBySnLikeIgnoreCase(String sn);

	List<Person> findByCnLikeIgnoreCase(String cn);

	/**
	 * lke {harpege}*
	 * 
	 * @param refId
	 * @return
	 */
	List<Person> findBySupannRefIdLikeIgnoreCase(String refId);

	
	List<Person> findBySupannRefIdLikeIgnoreCaseAndEduPersonPrimaryAffiliation(String supannRefId,
			String eduPersonPrimaryAffiliation);

	List<Person> findByUidAndSupannEmpId(String uid, String refId);
	
	Streamable<Person> findByGivenNameContaining( @Param("firstname") String firstname);
	
	@Query(value="(sn={0})" ,base="ou=people")
	Streamable<Person> findBySnContaining(String lastname);

	Person findBySupannAliasLogin(String supannAliasLogin);
	
	
	

}
