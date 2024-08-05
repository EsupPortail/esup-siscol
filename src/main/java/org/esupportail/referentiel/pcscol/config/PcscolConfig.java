package org.esupportail.referentiel.pcscol.config;

import java.net.URI;
import java.time.Duration;

import org.esupportail.referentiel.pcscol.api.EspacesApi;
import org.esupportail.referentiel.pcscol.api.MaquettesApi;
import org.esupportail.referentiel.pcscol.api.ObjetsMaquetteApi;
import org.esupportail.referentiel.pcscol.api.StructureApi;
import org.esupportail.referentiel.pcscol.invoker.ApiClient;
import org.esupportail.referentiel.pcscol.services.AccessTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class PcscolConfig {

	final transient Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${app.pcscol.api.ref.url}")
	private String apiRef;

	@Value("${app.pcscol.api.odf.url}")
	private String apiODF;

	@Autowired
	private AccessTokenService accessTokenService;

	@Value("${app.pcscol.accesstoken.duration}")
	private long duration = 6;

	@Bean
	@SessionScope
	StructureApi structureApi() {
		try {
			String token = accessTokenService.getToken();
			StructureApi structuresApi = new StructureApi(apiClient(apiRef, token));
			return structuresApi;
		} catch (Exception e) {
			logger.error(e.getMessage() + " :" + e.getMessage());
			return new StructureApi();
		}

	}

	@Bean
	@SessionScope
	MaquettesApi maquetteApi() {
		try {
			String token = accessTokenService.getToken();
			MaquettesApi api = new MaquettesApi(apiClient(apiODF, token));
			return api;
		} catch (Exception e) {
			logger.error(e.getMessage() + " :" + e.getMessage());
			return new MaquettesApi();
		}
	}

	@Bean
	@SessionScope
	ObjetsMaquetteApi objetsMaquetteApi() {
		try {
			String token = accessTokenService.getToken();
			ObjetsMaquetteApi api = new ObjetsMaquetteApi(apiClient(apiODF, token));
			return api;
		} catch (Exception e) {
			logger.error(e.getMessage() + " :" + e.getMessage());
			return new ObjetsMaquetteApi();
		}
	}
	
	
	@Bean
	@SessionScope
	EspacesApi espacesApi() {
		try {
			String token = accessTokenService.getToken();
			EspacesApi api = new EspacesApi(apiClient(apiODF, token));
			return api;
		} catch (Exception e) {
			logger.error(e.getMessage() + " :" + e.getMessage());
			return new EspacesApi();
		}
	}

	public ApiClient apiClient(String url, String token) {
		ApiClient apiClientIns = new ApiClient();
		URI baseURI = URI.create(url);
		String scheme = baseURI.getScheme();
		String host = baseURI.getHost();
		int port = baseURI.getPort();
		apiClientIns.setScheme(scheme);
		String basePath = baseURI.getRawPath();
		apiClientIns.setPort(port);
		apiClientIns.setHost(host);
		apiClientIns.setBasePath(basePath);
		Duration t = Duration.ofSeconds(duration);
		apiClientIns.setReadTimeout(t);
		apiClientIns.setRequestInterceptor(builder -> {
			builder.setHeader("Authorization", "Bearer " + token);
		});

		return apiClientIns;

	};

	public AccessTokenService getAccessTokenService() {
		return accessTokenService;
	}

	public void setAccessTokenService(AccessTokenService accessTokenService) {
		this.accessTokenService = accessTokenService;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getApiRef() {
		return apiRef;
	}

	public void setApiRef(String apiRef) {
		this.apiRef = apiRef;
	}

}
