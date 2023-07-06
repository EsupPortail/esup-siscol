package org.esupportail.referentiel.conf;

import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;

import javax.xml.ws.BindingProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;

import gouv.education.apogee.commun.client.ws.AdministratifMetier.AdministratifMetierServiceInterface;
import gouv.education.apogee.commun.client.ws.AdministratifMetier.AdministratifMetierServiceInterfaceService;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.EtudiantMetierServiceInterface;
import gouv.education.apogee.commun.client.ws.EtudiantMetier.EtudiantMetierServiceInterfaceService;
import gouv.education.apogee.commun.client.ws.GeographieMetier.GeographieMetierServiceInterface;
import gouv.education.apogee.commun.client.ws.GeographieMetier.GeographieMetierServiceInterfaceService;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.OffreFormationMetierServiceInterface;
import gouv.education.apogee.commun.client.ws.OffreFormationMetier.OffreFormationMetierServiceInterfaceService;
import gouv.education.apogee.commun.client.ws.PedagogiqueMetier.PedagogiqueMetierServiceInterface;
import gouv.education.apogee.commun.client.ws.PedagogiqueMetier.PedagogiqueMetierServiceInterfaceService;
import gouv.education.apogee.commun.client.ws.ReferentielMetier.ReferentielMetierServiceInterface;
import gouv.education.apogee.commun.client.ws.ReferentielMetier.ReferentielMetierServiceInterfaceService;

@Configuration
public class ApogeeWsConfig implements InitializingBean { // NO_UCD (unused code)

	private static final Logger log = LoggerFactory.getLogger(ApogeeWsConfig.class);

	// administratifMetier
	@Value("${app.apogee.urlService.administratifMetier}")
	private String administratifMetierUrlService;

	@Value("${app.apogee.urlService.administratifMetierUserName}")
	private String administratifMetierUserName;

	@Value("${app.apogee.urlService.administratifMetierPassword}")
	private String administratifMetierPassword;

	// etudiantMetier
	@Value("${app.apogee.urlService.etudiantMetier}")
	private String etudiantMetierUrlService;

	@Value("${app.apogee.urlService.etudiantMetierUserName}")
	private String etudiantMetierUserName;

	@Value("${app.apogee.urlService.etudiantMetierPassword}")
	private String etudiantMetierPassword;

	// pedagogiqueMetier
	@Value("${app.apogee.urlService.pedagogiqueMetier}")
	private String pedagogiqueMetierUrlService;

	@Value("${app.apogee.urlService.pedagogiqueMetierUserName}")
	private String pedagogiqueMetierUserName;

	@Value("${app.apogee.urlService.pedagogiqueMetierPassword}")
	private String pedagogiqueMetierPassword;

	// geographieMetier
	@Value("${app.apogee.urlService.geographieMetier}")
	private String geographieMetierUrlService;

	@Value("${app.apogee.urlService.geographieMetierUserName}")
	private String geographieMetierUserName;

	@Value("${app.apogee.urlService.geographieMetierPassword}")
	private String geographieMetierPassword;

	// referentielMetier
	@Value("${app.apogee.urlService.referentielMetier}")
	private String referentielMetierUrlService;

	@Value("${app.apogee.urlService.referentielMetierUserName}")
	private String referentielMetierUserName;

	@Value("${app.apogee.urlService.referentielMetierPassword}")
	private String referentielMetierPassword;

	@Value("${app.apogee.urlService.offreFormationMetier}")
	private String offreFormationMetierUrlService;
	
	@Value("${app.apogee.urlService.offreFormationMetierUserName}")
	private String offreFormationMetierUserName;
	@Value("${app.apogee.urlService.offreFormationMetierPassword}")
	private String offreFormationMetierPassword;

	@Override
	public void afterPropertiesSet() throws Exception {
		log.debug(getAdministratifMetierUrlService());
		log.debug(getOffreFormationMetierUrlService());
		log.debug(getReferentielMetierUrlService());
		log.debug(getPedagogiqueMetierUrlService());
		log.debug(getEtudiantMetierUrlService());
	}

	@Bean
	@RequestScope
	public EtudiantMetierServiceInterface etudiantMetier() {

		if (etudiantMetierUserName != null && etudiantMetierPassword != null) {
			Authenticator auth = new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(etudiantMetierUserName, etudiantMetierPassword.toCharArray());
				}
			};
			Authenticator.setDefault(auth);
		}

		URL wsdlLocation = wsdlLocation(etudiantMetierUrlService);
		EtudiantMetierServiceInterfaceService client = new EtudiantMetierServiceInterfaceService(wsdlLocation);
		EtudiantMetierServiceInterface clientMetier = client.getEtudiantMetier();

		return clientMetier;
	}

	@Bean("administratifMetier")
	@RequestScope
	public AdministratifMetierServiceInterface administratifMetier() {

		if (administratifMetierUserName != null && administratifMetierPassword != null) {
			Authenticator auth = new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(administratifMetierUserName,
							administratifMetierPassword.toCharArray());
				}
			};
			Authenticator.setDefault(auth);
		}

		URL wsdlLocation = wsdlLocation(administratifMetierUrlService);
		AdministratifMetierServiceInterfaceService client = new AdministratifMetierServiceInterfaceService(
				wsdlLocation);
		AdministratifMetierServiceInterface clientMetier = client.getAdministratifMetier();
		return clientMetier;

	}

	@Bean("pedagogiqueMetier")
	@RequestScope
	public PedagogiqueMetierServiceInterface pedagogiqueMetier() {

		if (pedagogiqueMetierUserName != null && pedagogiqueMetierPassword != null) {
			Authenticator auth = new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(pedagogiqueMetierUserName,
							pedagogiqueMetierPassword.toCharArray());
				}
			};
			Authenticator.setDefault(auth);
		}

		URL wsdlLocation = wsdlLocation(pedagogiqueMetierUrlService);
		PedagogiqueMetierServiceInterfaceService client = new PedagogiqueMetierServiceInterfaceService(wsdlLocation);
		PedagogiqueMetierServiceInterface clientMetier = client.getPedagogiqueMetier();
		return clientMetier;
	}

	@Bean
	@RequestScope
	public GeographieMetierServiceInterface geographieMetier() {
		
		if (geographieMetierUserName != null && geographieMetierPassword != null) {
			Authenticator auth = new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(geographieMetierUserName,
							geographieMetierPassword.toCharArray());
				}
			};
			Authenticator.setDefault(auth);
		}

		
		URL wsdlLocation = wsdlLocation(geographieMetierUrlService);
		GeographieMetierServiceInterfaceService client = new GeographieMetierServiceInterfaceService(wsdlLocation);
		GeographieMetierServiceInterface clientMetier = client.getGeographieMetier();
		return clientMetier;
	}

	@Bean
	@RequestScope
	public ReferentielMetierServiceInterface referentielMetier() {
		if (referentielMetierUserName != null && referentielMetierPassword != null) {
			Authenticator auth = new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(referentielMetierUserName,
							referentielMetierPassword.toCharArray());
				}
			};
			Authenticator.setDefault(auth);
		}
		
		
		log.debug("Instanciation ReferentielMetierServiceInterface @RequestScope ");
		URL wsdlLocation = wsdlLocation(referentielMetierUrlService);
		ReferentielMetierServiceInterfaceService client = new ReferentielMetierServiceInterfaceService(wsdlLocation);
		ReferentielMetierServiceInterface clientMetier = client.getReferentielMetier();
		return clientMetier;
	}

	@Bean
	@RequestScope
	public OffreFormationMetierServiceInterface offreFormationMetier() {
		
		if (offreFormationMetierUserName != null && offreFormationMetierPassword != null) {
			Authenticator auth = new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(offreFormationMetierUserName,
							offreFormationMetierPassword.toCharArray());
				}
			};
			Authenticator.setDefault(auth);
		}
		
		URL wsdlLocation = wsdlLocation(offreFormationMetierUrlService);
		OffreFormationMetierServiceInterfaceService client = new OffreFormationMetierServiceInterfaceService(
				wsdlLocation);
		OffreFormationMetierServiceInterface clientMetier = client.getOffreFormationMetier();
		
		return clientMetier;
	}

	private URL wsdlLocation(String url) {
		URL wsdlLocation = null;
		try {
			wsdlLocation = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			log.error("etudiantMetierUrlService wsdlLocation :", e);
		}
		return wsdlLocation;
	}

	public String getAdministratifMetierUrlService() {
		return administratifMetierUrlService;
	}

	public void setAdministratifMetierUrlService(String administratifMetierUrlService) {
		this.administratifMetierUrlService = administratifMetierUrlService;
	}

	public String getEtudiantMetierUrlService() {
		return etudiantMetierUrlService;
	}

	public void setEtudiantMetierUrlService(String etudiantMetierUrlService) {
		this.etudiantMetierUrlService = etudiantMetierUrlService;
	}

	public String getPedagogiqueMetierUrlService() {
		return pedagogiqueMetierUrlService;
	}

	public void setPedagogiqueMetierUrlService(String pedagogiqueMetierUrlService) {
		this.pedagogiqueMetierUrlService = pedagogiqueMetierUrlService;
	}

	public String getGeographieMetierUrlService() {
		return geographieMetierUrlService;
	}

	public void setGeographieMetierUrlService(String geographieMetierUrlService) {
		this.geographieMetierUrlService = geographieMetierUrlService;
	}

	public String getReferentielMetierUrlService() {
		return referentielMetierUrlService;
	}

	public void setReferentielMetierUrlService(String referentielMetierUrlService) {
		this.referentielMetierUrlService = referentielMetierUrlService;
	}

	public String getOffreFormationMetierUrlService() {
		return offreFormationMetierUrlService;
	}

	public void setOffreFormationMetierUrlService(String offreFormationMetierUrlService) {
		this.offreFormationMetierUrlService = offreFormationMetierUrlService;
	}


}