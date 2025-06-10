package org.esupportail.referentiel.pcscol.config;

import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "app.mode_pegase")
@ConfigurationProperties(prefix="app.pcscol")
public class CesureUtils {
	
	private Map<String, String> cesures;
	
	public Map<String, String> getCesures() {
		return cesures;
	}

	public void setCesures(Map<String, String> cesures) {
		this.cesures = cesures;
	}
}
