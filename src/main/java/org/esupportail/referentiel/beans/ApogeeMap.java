package org.esupportail.referentiel.beans;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;




/**
 * @author danielle Martineau 
 *
 */
public class ApogeeMap {
	
	
	/**
	 * regimeInscription
	 */
	private List<RegimeInscription> regimeInscription;
	
	/**
	 * liste des Etapes etudiant
	 */
	private LinkedHashMap<String,String> StudentSteps;
	/**
	 * liste des etapes et version etapes etudiant
	 */
	private LinkedHashMap<String, String> StudentsEtapesVets;
	
	/**
	 * liste des etapes et version etapes etudiant Pedagogique
	 */
	private LinkedHashMap<String, String> StudentsEtapesVetsPedago;
	
	/**
	 * liste des composantes etudiant
	 */
	private LinkedHashMap <String,String> StudentStudys;
	
	
	/**
	 * liste des elements pedagogiques.
	 */
	private List<ElementPedagogique> listeELPs;
	
	
	/**
	 * liste des Etapes inscriptions 
	 */
	private List<EtapeInscription> listeEtapeInscriptions;
	
	
	
	
	
	
	
	/**
	 * 
	 */
	public ApogeeMap() {
		super();
		StudentSteps = new LinkedHashMap<String, String>();
		StudentsEtapesVets = new LinkedHashMap<String, String>();
		StudentsEtapesVetsPedago = new LinkedHashMap<String, String>();
		
		listeELPs = new ArrayList<ElementPedagogique>();
		listeEtapeInscriptions = new ArrayList<EtapeInscription>();
		setStudentStudys(new LinkedHashMap<String, String>());
	}
	/**
	 * @return the studentSteps
	 */
	public LinkedHashMap<String, String> getStudentSteps() {
		return StudentSteps;
	}
	/**
	 * @param studentSteps the studentSteps to set
	 */
	public void setStudentSteps(LinkedHashMap<String, String> studentSteps) {
		StudentSteps = studentSteps;
	}
	/**
	 * @return the studentsEtapesVets
	 */
	public LinkedHashMap<String, String> getStudentsEtapesVets() {
		return StudentsEtapesVets;
	}
	/**
	 * @param studentsEtapesVets the studentsEtapesVets to set
	 */
	public void setStudentsEtapesVets(
			LinkedHashMap<String, String> studentsEtapesVets) {
		StudentsEtapesVets = studentsEtapesVets;
	}
	/**
	 * @return the studentsEtapesVetsPedago
	 */
	public LinkedHashMap<String, String> getStudentsEtapesVetsPedago() {
		return StudentsEtapesVetsPedago;
	}
	/**
	 * @param studentsEtapesVetsPedago the studentsEtapesVetsPedago to set
	 */
	public void setStudentsEtapesVetsPedago(
			LinkedHashMap<String, String> studentsEtapesVetsPedago) {
		StudentsEtapesVetsPedago = studentsEtapesVetsPedago;
	}
	
	/**
	 * @return the listeELPs
	 */
	public List<ElementPedagogique> getListeELPs() {
		return listeELPs;
	}
	/**
	 * @param listeELPs the listeELPs to set
	 */
	public void setListeELPs(List<ElementPedagogique> listeELPs) {
		this.listeELPs = listeELPs;
	}
	/**
	 * @return the listeEtapeInscriptions
	 */
	public List<EtapeInscription> getListeEtapeInscriptions() {
		return listeEtapeInscriptions;
	}
	/**
	 * @param listeEtapeInscriptions the listeEtapeInscriptions to set
	 */
	public void setListeEtapeInscriptions(
			List<EtapeInscription> listeEtapeInscriptions) {
		this.listeEtapeInscriptions = listeEtapeInscriptions;
	}
	/**
	 * @return
	 */
	public LinkedHashMap <String,String> getStudentStudys() {
		return StudentStudys;
	}
	/**
	 * @param studentStudys
	 */
	public void setStudentStudys(LinkedHashMap <String,String> studentStudys) {
		StudentStudys = studentStudys;
	}
	public List<RegimeInscription> getRegimeInscription() {
		return regimeInscription;
	}
	public void setRegimeInscription(List<RegimeInscription> regimeInscription) {
		this.regimeInscription = regimeInscription;
	}
	
	
}
