package org.esupportail.referentiel.pcscol.ins;

import java.util.ArrayList;
import java.util.List;

import org.esupportail.referentiel.Siscol;
import org.esupportail.referentiel.beans.EtapeInscription;
import org.esupportail.referentiel.beans.EtudiantRef;
import org.esupportail.referentiel.pcscol.api.InscriptionsApi;
import org.esupportail.referentiel.pcscol.ins.model.Apprenant;
import org.esupportail.referentiel.pcscol.ins.model.ApprenantEtInscriptions;
import org.esupportail.referentiel.pcscol.ins.model.InscriptionComplete;
import org.esupportail.referentiel.pcscol.ins.model.Inscriptions;
import org.esupportail.referentiel.pcscol.ins.model.Periode;
import org.esupportail.referentiel.pcscol.ins.model.StatutIne;
import org.esupportail.referentiel.pcscol.ins.model.StatutInscriptionVoeu;
import org.esupportail.referentiel.pcscol.ins.model.StatutPaiementVoeu;
import org.esupportail.referentiel.pcscol.ins.model.StatutPiecesVoeu;
import org.esupportail.referentiel.pcscol.ins.model.TriInscription;
import org.esupportail.referentiel.pcscol.invoker.ApiException;
import org.esupportail.referentiel.pcscol.mapper.ApprenantEtuInfoAdmMapperInterface;
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
public class InscriptionTest {

	@Autowired
	InscriptionsApi insApi;
	

	

	@Test
	public void testInsEtApp() throws ApiException {
		ApprenantEtInscriptions app = insApi.lireInscriptions("ETAB00", "000000001");
		Apprenant appInfo = app.getApprenant();

		EtudiantRef aa = ApprenantEtuInfoAdmMapperInterface.Instance.apprenantToEtudiantRef(appInfo);
		System.out.println(appInfo);
	}

	@Test
	public void testInsEtAppIns() throws ApiException {
		ApprenantEtInscriptions app = insApi.lireInscriptions("ETAB00", "000000249");
		Apprenant appInfo = app.getApprenant();
		List<InscriptionComplete> inscriptions = app.getInscriptions();

		// System.out.println(inscriptions.get(0));

		List<EtapeInscription> etsInscr = ApprenantEtuInfoAdmMapperInterface.Instance
				.stagesApprenantToEtapeInscription(inscriptions);
		System.out.println(etsInscr);

//		inscriptions.forEach(i->{
//			CibleInscription cible = i.getCible();
//			System.out.println(cible.getFormation().getCode()+ ","+cible.getPeriode().getCode());
//			EtapeInscription insC= ApprenantEtuInfoAdmMapperInterface.Instance.stagesApprenantToEtapeInscription(i);
//			System.out.println(insC);
//		});
	}

	@Test
	public void testInsEtAppAnnee() throws ApiException {
		List<InscriptionComplete> app = insApi.lireInscriptionsApprenantParInePourUneAnneeUniversitaireOuUnePeriode(
				"ETAB00", "511267953BT", "PER-2020", StatutInscriptionVoeu.VALIDE.getValue());
		System.out.println(app);
	}

	@Test
	public void listPeriode() throws ApiException {
		List<Periode> periodes = insApi.listerPeriodes("ETAB00");
		System.out.println(periodes);
	}

	/**
	 * String codeStructure, List<StatutInscriptionVoeu> statutsInscription,
	 * List<StatutPiecesVoeu> statutsPieces, List<StatutPaiementVoeu>
	 * statutsPaiement, List<TriInscription> tri, String rechercheIne, String
	 * recherche, String periode, String objetMaquette, String nomOuPrenom, String
	 * nomDeNaissance, String prenom, String codeApprenant, String ine,
	 * List<StatutIne> statutsIne, Integer limit
	 * 
	 */
	@Test
	public void insValide() {
		String codeStructure = "ETAB00";
		List<StatutInscriptionVoeu> statutsInscription = new ArrayList<StatutInscriptionVoeu>();
		// statutsInscription.add(StatutInscriptionVoeu.VALIDE);
		// statutsInscription.add(StatutInscriptionVoeu.ANNULEE);
		List<StatutPiecesVoeu> statutsPieces = new ArrayList<StatutPiecesVoeu>();
		List<StatutPaiementVoeu> statutsPaiement = new ArrayList<StatutPaiementVoeu>();
		List<TriInscription> tri = null;
		String rechercheIne = null;
		String recherche = null;
		String periode = "PER-2024";
		String objetMaquette = null;
		String nomOuPrenom = null;
		String nomDeNaissance = null;
		String prenom = null;
		String codeApprenant = null;
		String ine = null;
		List<StatutIne> statutsIne = null;
		Integer limit = 10;

		try {
			Inscriptions list = insApi.listerInscriptionsValidees(codeStructure, statutsInscription, statutsPieces,
					statutsPaiement, tri, rechercheIne, recherche, periode, objetMaquette, nomOuPrenom, nomDeNaissance,
					prenom, codeApprenant, ine, statutsIne, limit);
			System.out.println(list.getTotalElements());
			list.getResultats().forEach(i -> {
				System.out.println(i.getMeta().getCodeApprenant()+": "+i.getVoeu().getCible().getPeriode().getCode());
				//System.out.println(i);
			});
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
