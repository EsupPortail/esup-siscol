package org.esupportail.referentiel.beans;

import java.util.ArrayList;
import java.util.List;

public class DiplomeReduitDto {

	/**
	 * codeDiplome.
	 */
	private String codeDiplome = "";

	/**
	 * versionDiplome.
	 */
	private String versionDiplome = "";

	/**
	 * libDiplome
	 */
	private String libDiplome = "";
	
	private List<EtapeReduiteDto> listeEtapes=new ArrayList<>();

	public String getCodeDiplome() {
		return codeDiplome;
	}

	public void setCodeDiplome(String codeDiplome) {
		this.codeDiplome = codeDiplome;
	}

	public String getVersionDiplome() {
		return versionDiplome;
	}

	public void setVersionDiplome(String versionDiplome) {
		this.versionDiplome = versionDiplome;
	}

	public String getLibDiplome() {
		return libDiplome;
	}

	public void setLibDiplome(String libDiplome) {
		this.libDiplome = libDiplome;
	}

	public List<EtapeReduiteDto> getListeEtapes() {
		return listeEtapes;
	}

	public void setListeEtapes(List<EtapeReduiteDto> listeEtapes) {
		this.listeEtapes = listeEtapes;
	}
	
	

}
