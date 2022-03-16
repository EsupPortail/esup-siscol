package org.esupportail.referentiel.utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Configuration {

	private static final String BUNDLE_NAME = "properties/esup-pstageMessages";//$NON-NLS-1$
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private Configuration() { //singleton, pas de constructeur public
	}

	public static String getString(String key) {
		String result;
		try {
			result= RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" La constante : '"); //$NON-NLS-1$
			stringBuilder.append(key);
			stringBuilder.append("' n'est pas accessible ou n'est pas  definie "); //$NON-NLS-1$
			result = stringBuilder.toString();
		}
		return result;
	}
	

	
	
	
}
