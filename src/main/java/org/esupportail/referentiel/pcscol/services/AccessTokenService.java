package org.esupportail.referentiel.pcscol.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.SessionScope;


@Service
@SessionScope
@ConditionalOnProperty(name = "app.mode_pegase")
public class AccessTokenService {

	// curl -d "username=svc-api&password=PASSWORD&token=true"
	// -H "Content-Type: application/x-www-form-urlencoded" -X POST
	// https://authn-app.bas-esup.pc-scol.fr/cas/v1/tickets
	
	final transient Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${app.pcscol.accesstoken.casUrl}")
	private String casUrl ;

	@Value("${app.pcscol.accesstoken.svcAcountLogin}")
	private String svcAcountLogin = "";

	@Value("${app.pcscol.accesstoken.svcAcountPassword}")
	private String  svcAcountPassword= "";
	
	@Value("${app.pcscol.accesstoken.duration}")
	private long duration = 6;


	
	private String token;
	
	private LocalDateTime tokenCreatedDateTime;

	public String getAccessToken() {
		
		
		// Headers
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		// URL
		String url = casUrl + "?username={username}&password={password}&token=true";

		// Paramètres
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("username", svcAcountLogin);
		uriVariables.put("password", svcAcountPassword);
		
		// RestTemplate
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> tokenResponse = null;

		try {
			tokenResponse = restTemplate.exchange(url, HttpMethod.POST, null, String.class, uriVariables);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("tokenResponse : "+tokenResponse);
		// Récupération du token dans la réponse
		if (tokenResponse != null && tokenResponse.getStatusCode().is2xxSuccessful()
				&& StringUtils.hasText(tokenResponse.getBody())) {
			this.token = tokenResponse.getBody();
			this.tokenCreatedDateTime = LocalDateTime.now();
			logger.debug("tokenResponse : "+token);
			return token;

		} else {
			return null;
		}

	}

	private void checkToken() {

		if (token == null || tokenExpired()) {
			getAccessToken();
		}
	}

	private boolean tokenExpired() {
		LocalDateTime ldt = LocalDateTime.now();
		return (tokenCreatedDateTime.until(ldt, ChronoUnit.HOURS) > duration);
	}

	public String getToken() {
		if (token == null) {
			checkToken();
		}
		return token;
	}

	public void cronJobCheckToken() {
		checkToken();
	}

	public String getCasUrl() {
		return casUrl;
	}

	public void setCasUrl(String casUrl) {
		this.casUrl = casUrl;
	}

	public String getSvcAcountLogin() {
		return svcAcountLogin;
	}

	public void setSvcAcountLogin(String svcAcountLogin) {
		this.svcAcountLogin = svcAcountLogin;
	}

	public String getSvcAcountPassword() {
		return svcAcountPassword;
	}

	public void setSvcAcountPassword(String svcAcountPassword) {
		this.svcAcountPassword = svcAcountPassword;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public LocalDateTime getTokenCreatedDateTime() {
		return tokenCreatedDateTime;
	}

	public void setTokenCreatedDateTime(LocalDateTime tokenCreatedDateTime) {
		this.tokenCreatedDateTime = tokenCreatedDateTime;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
