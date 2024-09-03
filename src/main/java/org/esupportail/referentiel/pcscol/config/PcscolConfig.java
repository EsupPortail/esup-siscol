package org.esupportail.referentiel.pcscol.config;

import java.net.URI;
import java.time.Duration;

import org.esupportail.referentiel.pcscol.api.EspacesApi;
import org.esupportail.referentiel.pcscol.api.InscriptionsApi;
import org.esupportail.referentiel.pcscol.api.MaquettesApi;
import org.esupportail.referentiel.pcscol.api.ObjetsMaquetteApi;
import org.esupportail.referentiel.pcscol.api.StructureApi;
import org.esupportail.referentiel.pcscol.ins.model.Inscription;
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
	
	@Value("${app.pcscol.api.ins.url}")
	private String apiIns;


	@Autowired
	private AccessTokenService accessTokenService;

	@Value("${app.pcscol.accesstoken.duration}")
	private long duration = 6;

	@Bean
	@SessionScope
	public StructureApi structureApi() {
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
	public MaquettesApi maquetteApi() {
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
	public ObjetsMaquetteApi objetsMaquetteApi() {
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
	public EspacesApi espacesApi() {
		try {
			String token = accessTokenService.getToken();
			EspacesApi api = new EspacesApi(apiClient(apiODF, token));
			return api;
		} catch (Exception e) {
			logger.error(e.getMessage() + " :" + e.getMessage());
			return new EspacesApi();
		}
	}
	@Bean
	@SessionScope
	public InscriptionsApi inscriptionsApi() {
		try {
			String token = accessTokenService.getToken();
			InscriptionsApi api=new InscriptionsApi(apiClient(apiIns,token));
			return api ;
			
		}catch(Exception e) {
			return new InscriptionsApi();
		}
	}

	private ApiClient apiClient(String url, String token) {
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

	public String getApiODF() {
		return apiODF;
	}

	public void setApiODF(String apiODF) {
		this.apiODF = apiODF;
	}

	public String getApiIns() {
		return apiIns;
	}

	public void setApiIns(String apiIns) {
		this.apiIns = apiIns;
	}
	
	

}
