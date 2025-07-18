package org.esupportail.referentiel.conf;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.ws.BindingProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

@ConditionalOnProperty(name = "app.mode_apogee")
@Configuration
public class ApogeeWsConfigBindingProviderMetohde implements InitializingBean, Serializable { // NO_UCD (unused code)

	private static final long serialVersionUID = -1521878436222655994L;

	private static final Logger log = LoggerFactory.getLogger(ApogeeWsConfigBindingProviderMetohde.class);

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
	EtudiantMetierServiceInterface etudiantMetier() throws URISyntaxException {
		String wsdlUrl = etudiantMetierUrlService + "?wsdl";
		EtudiantMetierServiceInterfaceService etudiantMetierService = null;
		String pwd = etudiantMetierPassword;
		String username = etudiantMetierUserName;

		URL wsdlLocation;
		try {
			wsdlLocation = new URI(wsdlUrl).toURL();
			etudiantMetierService = new EtudiantMetierServiceInterfaceService(wsdlLocation);
		} catch (MalformedURLException e) {
			log.error(e.getMessage());
			etudiantMetierService = new EtudiantMetierServiceInterfaceService();
		}
		EtudiantMetierServiceInterface etudiantMetierServiceInterface = etudiantMetierService.getEtudiantMetier();
		BindingProvider bp = (BindingProvider) etudiantMetierServiceInterface;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, wsdlUrl);
		bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, username);
		bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, pwd);
		return etudiantMetierServiceInterface;

	}

	@Bean("administratifMetier")
	AdministratifMetierServiceInterface administratifMetier() throws URISyntaxException {

		String wsdlUrl = administratifMetierUrlService + "?wsdl";
		String pwd = administratifMetierPassword;
		String username = administratifMetierUserName;

		AdministratifMetierServiceInterfaceService administratifMetierService;
		URL wsdlLocation;
		try {
			wsdlLocation = new URI(wsdlUrl).toURL();
			administratifMetierService = new AdministratifMetierServiceInterfaceService(wsdlLocation);
		} catch (MalformedURLException e) {
			log.error(e.getMessage());
			administratifMetierService = new AdministratifMetierServiceInterfaceService();
		}
		AdministratifMetierServiceInterface administratifMetierServiceInterface = administratifMetierService
				.getAdministratifMetier();
		BindingProvider bp = (BindingProvider) administratifMetierServiceInterface;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, wsdlUrl);
		bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, username);
		bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, pwd);
		return administratifMetierServiceInterface;

	}

	@Bean("pedagogiqueMetier")
	PedagogiqueMetierServiceInterface pedagogiqueMetier() throws URISyntaxException {

		String wsdlUrl = pedagogiqueMetierUrlService + "?wsdl";
		String pwd = pedagogiqueMetierPassword;
		String username = pedagogiqueMetierUserName;
		PedagogiqueMetierServiceInterfaceService pedagogiqueMetierService;
		URL wsdlLocation;
		try {
			wsdlLocation = new URI(wsdlUrl).toURL();
			pedagogiqueMetierService = new PedagogiqueMetierServiceInterfaceService(wsdlLocation);
		} catch (MalformedURLException e) {
			log.error(e.getMessage());
			pedagogiqueMetierService = new PedagogiqueMetierServiceInterfaceService();
		}
		PedagogiqueMetierServiceInterface pedagogiqueMetierServiceInterface = pedagogiqueMetierService
				.getPedagogiqueMetier();
		BindingProvider bp = (BindingProvider) pedagogiqueMetierServiceInterface;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, wsdlUrl);
		bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, username);
		bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, pwd);
		return pedagogiqueMetierServiceInterface;
	}

	@Bean
	GeographieMetierServiceInterface geographieMetier() throws URISyntaxException {

		String wsdlUrl = geographieMetierUrlService + "?wsdl";
		String pwd = geographieMetierPassword;
		String username = geographieMetierUserName;
		GeographieMetierServiceInterfaceService geographieMetierService;
		URL wsdlLocation;
		try {
			wsdlLocation = new URI(wsdlUrl).toURL();
			geographieMetierService = new GeographieMetierServiceInterfaceService(wsdlLocation);
		} catch (MalformedURLException e) {
			log.error(e.getMessage());
			geographieMetierService = new GeographieMetierServiceInterfaceService();
		}
		GeographieMetierServiceInterface geographieMetierServiceInterface = geographieMetierService
				.getGeographieMetier();
		BindingProvider bp = (BindingProvider) geographieMetierServiceInterface;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, wsdlUrl);
		bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, username);
		bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, pwd);
		return geographieMetierServiceInterface;
	}

	@Bean
	ReferentielMetierServiceInterface referentielMetier() throws URISyntaxException {

		String wsdlUrl = referentielMetierUrlService + "?wsdl";
		String pwd = referentielMetierPassword;
		String username = referentielMetierUserName;
		ReferentielMetierServiceInterfaceService referentielMetierService;
		URL wsdlLocation;
		try {
			wsdlLocation = new URI(wsdlUrl).toURL();
			referentielMetierService = new ReferentielMetierServiceInterfaceService(wsdlLocation);
		} catch (MalformedURLException e) {
			log.error(e.getMessage());
			referentielMetierService = new ReferentielMetierServiceInterfaceService();
		}
		ReferentielMetierServiceInterface referentielMetierServiceInterface = referentielMetierService
				.getReferentielMetier();
		BindingProvider bp = (BindingProvider) referentielMetierServiceInterface;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, wsdlUrl);
		bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, username);
		bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, pwd);
		return referentielMetierServiceInterface;
	}

	@Bean
	OffreFormationMetierServiceInterface offreFormationMetier() throws URISyntaxException {
		String wsdlUrl = offreFormationMetierUrlService + "?wsdl";
		String pwd = offreFormationMetierPassword;
		String username = offreFormationMetierUserName;

		OffreFormationMetierServiceInterfaceService offreFormationMetierService;
		URL wsdlLocation;
		try {
			wsdlLocation = new URI(wsdlUrl).toURL();
			offreFormationMetierService = new OffreFormationMetierServiceInterfaceService(wsdlLocation);
		} catch (MalformedURLException e) {
			offreFormationMetierService = new OffreFormationMetierServiceInterfaceService();
		}
		OffreFormationMetierServiceInterface offreFormationMetierServiceInterface = offreFormationMetierService
				.getOffreFormationMetier();
		BindingProvider bp = (BindingProvider) offreFormationMetierServiceInterface;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, wsdlUrl);
		bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, username);
		bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, pwd);
		return offreFormationMetierServiceInterface;
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