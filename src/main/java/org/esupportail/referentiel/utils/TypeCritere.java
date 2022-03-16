package org.esupportail.referentiel.utils;

public enum TypeCritere {
	CONVENTION, OFFRE, PREMIER_NIVEAU, SECOND_NIVEAU;

	public Integer get() {
		switch (this) {
		case CONVENTION:
			return 1;
		case OFFRE:
			return 2;
		case PREMIER_NIVEAU:
			return 1;
		case SECOND_NIVEAU:
			return 2;
		}
		throw new RuntimeException(" Type enum non prevu ");
	}

}
