package org.esupportail.referentiel.security;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Component
@ConfigurationProperties(prefix="credential")
public class CredentialComponent {
	
	private static final Logger log = LoggerFactory.getLogger(CredentialComponent.class);
	
	
	private Map<String,CustomCredential> userscredential;
	
	public Map<String,CustomCredential> getUserscredential() {
		return userscredential;
	}

	public void setUserscredential(Map<String,CustomCredential> userscredential) {
		log.debug("INIT USERS : "+ userscredential);
		this.userscredential = userscredential;
	}

	

}
