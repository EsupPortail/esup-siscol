package org.esupportail.referentiel.security;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Component
@ConfigurationProperties(prefix="credential")
public class CredentialComponent {
	
	
	private Map<String,CustomCredential> userscredential;
	
	public Map<String,CustomCredential> getUserscredential() {
		return userscredential;
	}

	public void setUserscredential(Map<String,CustomCredential> userscredential) {
		this.userscredential = userscredential;
	}

	

}
