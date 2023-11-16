package org.esupportail.referentiel.pcscol.config;

import java.net.URI;
import java.time.Duration;

import org.esupportail.referentiel.pcscol.api.FormationsApi;
import org.esupportail.referentiel.pcscol.api.StagesApi;
import org.esupportail.referentiel.pcscol.api.StructuresApi;
import org.esupportail.referentiel.pcscol.invoker.ApiClient;
import org.esupportail.referentiel.pcscol.services.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class PcscolConfig {

	@Value("${app.pcscol.api.stage.url}")
	private transient String apiSta;

	@Value("${app.pcscol.api.mof.url}")
	private transient String apiMof;

	@Autowired
	private AccessTokenService accessTokenService;

	@Value("${app.pcscol.accesstoken.duration}")
	private long duration = 6;

    @Bean
    @SessionScope
    StagesApi stagesApi() {
		String token = accessTokenService.getToken();
		StagesApi stagesApi = new StagesApi(apiClient(apiSta, token));
		return stagesApi;
	}

    @Bean
    @SessionScope
    StructuresApi structuresApi() {
		String token = accessTokenService.getToken();
		StructuresApi structuresApi = new StructuresApi(apiClient(apiMof, token));
		return structuresApi;

	}

    @Bean
    @SessionScope
    FormationsApi formationsApi() {
		String token = accessTokenService.getToken();
		FormationsApi structuresApi = new FormationsApi(apiClient(apiMof, token));
		return structuresApi;

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

	public String getApiSta() {
		return apiSta;
	}

	public void setApiSta(String apiSta) {
		this.apiSta = apiSta;
	}

	public String getApiMof() {
		return apiMof;
	}

	public void setApiMof(String apiMof) {
		this.apiMof = apiMof;
	}

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

}
