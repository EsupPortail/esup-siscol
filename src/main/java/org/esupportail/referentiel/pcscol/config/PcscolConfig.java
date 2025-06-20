package org.esupportail.referentiel.pcscol.config;

import java.net.URI;
import java.time.Duration;

import org.esupportail.referentiel.pcscol.api.ArbresApi;
import org.esupportail.referentiel.pcscol.api.CursusApi;
import org.esupportail.referentiel.pcscol.api.CursusDcaApi;
import org.esupportail.referentiel.pcscol.api.EspacesApi;
import org.esupportail.referentiel.pcscol.api.ExtractionsApi;
import org.esupportail.referentiel.pcscol.api.InscriptionsApi;
import org.esupportail.referentiel.pcscol.api.MaquettesApi;
import org.esupportail.referentiel.pcscol.api.NomenclatureApi;
import org.esupportail.referentiel.pcscol.api.ObjetsMaquetteApi;
import org.esupportail.referentiel.pcscol.api.ObjetsMaquetteApiCHC;
import org.esupportail.referentiel.pcscol.api.StructureApi;
import org.esupportail.referentiel.pcscol.invoker.ApiClient;
import org.esupportail.referentiel.pcscol.services.AccessTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
@ConditionalOnProperty(name = "app.mode_pegase")
public class PcscolConfig {

	final transient Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${app.pcscol.api.ref.url}")
	private String apiRef;

	@Value("${app.pcscol.api.odf.url}")
	private String apiODF;
	
	@Value("${app.pcscol.api.ins.url}")
	private String apiIns;
	
	@Value("${app.pcscol.api.chc.url}")
	private String apiChcV6;
	
	@Value("${app.pcscol.api.chcExterne.url}")
	private String apiChcExtrene;


	@Autowired
	private AccessTokenService accessTokenService;

	@Value("${app.pcscol.accesstoken.duration}")
	private long duration = 6;

	
	@Bean
	@SessionScope
	NomenclatureApi nomenclatureApi() {
		try {
			String token = accessTokenService.getToken();
			NomenclatureApi api = new NomenclatureApi(apiClient(apiRef, token));
			return api;
		} catch (Exception e) {
			logger.error(e.getMessage() + " :" + e.getMessage());
			return new NomenclatureApi();
		}
	}
	
	
	
	
		
	@Bean
	@SessionScope
	ObjetsMaquetteApiCHC objetsMaquetteApiCHC() {
		try {
			String token = accessTokenService.getToken();
			ObjetsMaquetteApiCHC arbreApi = new ObjetsMaquetteApiCHC(apiClient(apiChcV6, token));
			return arbreApi;
		} catch (Exception e) {
			logger.error(e.getMessage() + " :" + e.getMessage());
			return new ObjetsMaquetteApiCHC();
		}
	}
	
	@Bean
	@SessionScope
	ArbresApi arbresApi() {
		try {
			String token = accessTokenService.getToken();
			ArbresApi arbreApi = new ArbresApi(apiClient(apiChcV6, token));
			return arbreApi;
		} catch (Exception e) {
			logger.error(e.getMessage() + " :" + e.getMessage());
			return new ArbresApi();
		}
	}
	
	
	


    @Bean
    @SessionScope
    CursusDcaApi cursusDcaApi() {
		try {
			String token = accessTokenService.getToken();
			CursusDcaApi structuresApi = new CursusDcaApi(apiClient(apiChcV6, token));
			return structuresApi;
		} catch (Exception e) {
			logger.error(e.getMessage() + " :" + e.getMessage());
			return new CursusDcaApi();
		}
		
	}

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
    @Bean
    @SessionScope
    ExtractionsApi extractionsApi() {
    	try {
			String token = accessTokenService.getToken();
			ExtractionsApi api = new ExtractionsApi(apiClient(apiChcExtrene, token));
			return api;
		} catch (Exception e) {
			logger.error(e.getMessage() + " :" + e.getMessage());
			return new ExtractionsApi();
		} 
    }
    
    @Bean
    @SessionScope
    CursusApi cursusApi() {
    	try {
			String token = accessTokenService.getToken();
			CursusApi api = new CursusApi(apiClient(apiChcExtrene, token));
			return api;
		} catch (Exception e) {
			logger.error(e.getMessage() + " :" + e.getMessage());
			return new CursusApi();
		} 
    }

    @Bean
    @SessionScope
    InscriptionsApi inscriptionsApi() {
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

	public String getApiChcV6() {
		return apiChcV6;
	}

	public void setApiChcV6(String apiChcV6) {
		this.apiChcV6 = apiChcV6;
	}

	public String getApiChcExtrene() {
		return apiChcExtrene;
	}

	public void setApiChcExtrene(String apiChcExtrene) {
		this.apiChcExtrene = apiChcExtrene;
	}
	
	

}
