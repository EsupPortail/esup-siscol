package org.esupportail.referentiel.beans;
import java.io.Serializable;
/**
 * @author cleprous
 * RoadMap : Chemin de navigation.
 */
public class RoadMap implements Serializable {

	
	/*
	 ******************* PROPERTIES ******************* */

	/**
	 * 
	 */
	private static final long serialVersionUID = 5819535348008163103L;

	/**
	 * Rang du chemin.
	 */
	private Integer rang;
	
	/**
	 * Nom long du chemin.
	 */
	private String title;
	
	/**
	 * Nom court du chemin.
	 */
	private String label;
	
	/**
	 * Action permettant la redirection.
	 */
	private String action;
	
	/**
	 * A true if the current page.
	 */
	private Boolean isCurrentPage;
	
	/*
	 ******************* INIT ************************* */

	
	/**
	 * Constructors.
	 */
	public RoadMap() {
		super();
	}

	/**
	 * Constructors.
	 * @param action
	 * @param isCurrentPage
	 * @param label
	 * @param rang
	 * @param title
	 */
	public RoadMap(final String action, final Boolean isCurrentPage, final String label,
			final Integer rang, final String title) {
		super();
		this.action = action;
		this.isCurrentPage = isCurrentPage;
		this.label = label;
		this.rang = rang;
		this.title = title;
	}

	/** 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((rang == null) ? 0 : rang.hashCode());
		result = prime * result + ((isCurrentPage == null) ? 0 : isCurrentPage.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}


	/** 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (!(obj instanceof RoadMap)) { return false; }
		RoadMap other = (RoadMap) obj;
		if (action == null) {
			if (other.action != null) {	return false; }
		} else if (!action.equals(other.action)) { return false; }
		if (label == null) {
			if (other.label != null) { return false; }
		} else if (!label.equals(other.label)) { return false; }
		if (rang == null) {
			if (other.rang != null) { return false; }
		} else if (!rang.equals(other.rang)) {	return false; }
		if (isCurrentPage == null) {
			if (other.isCurrentPage != null) { return false; }
		} else if (!isCurrentPage.equals(other.isCurrentPage)) {	return false; }
		if (title == null) {
			if (other.title != null) { return false; }
		} else if (!title.equals(other.title)) {	return false; }
		return true;
	}


	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RoadMap#" + hashCode() + "[rang=[" + rang 
			+ "],[label=[" + label + "],[action=[" + action 
			+ "],[isCurrentPage=[" + isCurrentPage + "],[title=[" + title + "]]";
	}
	
	/*
	 ******************* METHODS ********************** */

	/*
	 ******************* ACCESSORS ******************** */
	
	
	/**
	 * @return the rang
	 */
	public Integer getRang() {
		return rang;
	}

	/**
	 * @param rang the rang to set
	 */
	public void setRang(final Integer rang) {
		this.rang = rang;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(final String label) {
		this.label = label;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(final String action) {
		this.action = action;
	}




	/**
	 * @return the isCurrentPage
	 */
	public Boolean getIsCurrentPage() {
		return isCurrentPage;
	}




	/**
	 * @param isCurrentPage the isCurrentPage to set
	 */
	public void setIsCurrentPage(final Boolean isCurrentPage) {
		this.isCurrentPage = isCurrentPage;
	}




	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}




	/**
	 * @param title the title to set
	 */
	public void setTitle(final String title) {
		this.title = title;
	}
	
	

}
