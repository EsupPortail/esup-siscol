package org.esupportail.referentiel.mappers;

import java.util.List;

import org.esupportail.referentiel.beans.EtudiantDTO2Ext;
import org.esupportail.referentiel.conf.LdapAttributesConf;
import org.esupportail.referentiel.ldap.entities.Person;
import org.esupportail.referentiel.ldap.services.interfaces.LdapServiceInterface;

public class ApoggeeLdapEtudiant {

	private LdapServiceInterface personService;

	public ApoggeeLdapEtudiant(LdapServiceInterface personService) {
		super();
		this.personService = personService;
	}

	public void MappMailEtudiant(EtudiantDTO2Ext etudiantExt) {
		Person person = personService.findByCodEtu(etudiantExt.getCodEtu());
		etudiantExt.setMail(person.getMail());
	}

	public void MappMailEtudiant(List<EtudiantDTO2Ext> etudiantsExt) {
		if (etudiantsExt != null)
			etudiantsExt.forEach(etu -> {
				MappMailEtudiant(etu);
			});
	}

}
