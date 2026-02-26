package org.esupportail.referentiel.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ForwardedHeaderFilter;

/**
 * Configuration pour supporter les headers X-Forwarded-* lorsque l'application
 * est derrière un proxy HTTP.
 * 
 * Cela permet à Spring de reconstruire l'URL originale du client à partir des
 * headers X-Forwarded-Proto, X-Forwarded-Host, X-Forwarded-Port et X-Forwarded-For.
 * 
 * Important : Cette classe doit être utilisée AVANT la configuration CORS pour
 * que l'origine correcte soit prise en compte.
 */
@Configuration
public class ForwardedHeaderConfig {
	
	/**
	 * Bean pour traiter les headers X-Forwarded-* du proxy HTTP.
	 * 
	 * Cet en-tête doit être configuré sur le proxy HTTP (nginx, Apache, etc.)
	 * avec des valeurs telles que :
	 * - X-Forwarded-Proto: https
	 * - X-Forwarded-Host: example.com
	 * - X-Forwarded-Port: 443
	 * - X-Forwarded-For: 192.168.1.1
	 * 
	 * @return le filtre pour traiter les headers du proxy
	 */
	@Bean
	public ForwardedHeaderFilter forwardedHeaderFilter() {
		return new ForwardedHeaderFilter();
	}
}
