package org.esupportail.referentiel.pcscol.ins;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.esupportail.referentiel.Siscol;
import org.esupportail.referentiel.beans.ApprenantDto;
import org.esupportail.referentiel.pcscol.services.PcscolService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@ContextConfiguration(classes = { Siscol.class })
@SpringBootTest
@SpringJUnitConfig
@ActiveProfiles("test")
public class Ldif {

	@Autowired
	PcscolService pcscolService;

	@Test
	public void test() {
		String codeStructure = "ETAB00";
		String objetMaquette = null;
		String periode = null;
		String nomDeNaissance = null;
		String prenom = null;
		String codeApprenant = "000000250";
		List<ApprenantDto> result = pcscolService.lireApprenantDtoFromInscriptions(codeStructure, objetMaquette, periode, nomDeNaissance, prenom,
				codeApprenant);
		System.out.println("+++++++++++++"+result.size());
		List<String> listOfall=new ArrayList<String>();
		
		Set<ApprenantDto> setApprenantDto=new HashSet<ApprenantDto>();
		setApprenantDto.addAll(result);
		System.out.println(setApprenantDto.size());
		
		setApprenantDto.forEach(r->{
			StringBuffer buffer=new StringBuffer();
			buffer.append("dn:"+"uid:"+r.getCodEtu()+",ou=people,dc=ehess,dc=fr\n");
			buffer.append("uid:"+r.getCodEtu()+"\n");
			buffer.append("supannCodeINE:"+r.getNumeroIne()+"\n");
			buffer.append("userPassword:"+"password@Test"+"\n");
			buffer.append("supannEtuId:"+r.getCodEtu()+"\n");
			buffer.append("sn:"+r.getNom()+"\n");
			buffer.append("mail:"+r.getNom()+"."+r.getPrenom()+"@ehess.fr"+"\n");
			buffer.append("supannAutreMail:"+r.getNom()+"."+r.getPrenom()+"@ehess.fr"+"\n");
			buffer.append("cn:"+r.getNom()+" "+r.getPrenom()+"\n");
			buffer.append("givenName:" +r.getPrenom()+"\n");
			buffer.append("eduPersonPrimaryAffiliation: student"+"\n");
			buffer.append("eduPersonAffiliation: student"+"\n");
			buffer.append("eduPersonAffiliation: member"+"\n"+"\n");
			
//			buffer.append("supannEntiteAffectation:"+);
//			buffer.append("supannEntiteAffectationPrincipale:"+);
//			buffer.append("supannCivilite:"+);
//			buffer.append("telephoneNumber:"+);
//			buffer.append("supannEtuCursusAnnee:"+);
//			buffer.append("eduPersonOrgDN:"+);
//			buffer.append("supannRefId:"+);
//			buffer.append("supannEtuEtape:"+);
//			buffer.append("supannEtuAnneeInscription:"+);
			listOfall.add(buffer.toString());
		});

		listOfall.forEach(l->{
			System.out.println(l);
		});
		
	}

}
