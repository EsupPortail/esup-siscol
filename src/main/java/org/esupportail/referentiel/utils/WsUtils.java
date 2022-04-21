package org.esupportail.referentiel.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.xml.ws.BindingProvider;

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

public class WsUtils {
	public static String getPropValue(String key) {
		 String result = "";
		 try (InputStream input = WsUtils.class.getClassLoader().getResourceAsStream("configUrlServices.properties")) {
	          Properties prop = new Properties();
	          prop.load(input);
             result = prop.getProperty(key);	        
	      } catch (IOException ex) {
	            ex.printStackTrace();
	      }
		  return result;
	 }
	
	public static GeographieMetierServiceInterface initGeographieMetierService() {
		String wsdlUrl = getPropValue("geographieMetier.urlService")+"?wsdl";
		GeographieMetierServiceInterfaceService geographieMetierService;
		URL wsdlLocation;
		try {
		  wsdlLocation = new URL(wsdlUrl);		
		  geographieMetierService = new GeographieMetierServiceInterfaceService(wsdlLocation);
		} catch (MalformedURLException e) {
			geographieMetierService = new GeographieMetierServiceInterfaceService();
		}		
		GeographieMetierServiceInterface geographieMetierServiceInterface = geographieMetierService.getGeographieMetier();
		BindingProvider bp = (BindingProvider)geographieMetierServiceInterface;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, wsdlUrl);
		bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, getPropValue("geographieMetier.username"));
		bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, getPropValue("geographieMetier.password"));
		return geographieMetierServiceInterface;
	}
	
	public static ReferentielMetierServiceInterface initReferentielMetierService(String referentielMetierUrlService) {
		String wsdlUrl = referentielMetierUrlService+"?wsdl";
		ReferentielMetierServiceInterfaceService referentielMetierService;
		URL wsdlLocation;
		try {
		  wsdlLocation = new URL(wsdlUrl);		
		  referentielMetierService = new ReferentielMetierServiceInterfaceService(wsdlLocation);
		} catch (MalformedURLException e) {
			referentielMetierService = new ReferentielMetierServiceInterfaceService();
		}		
		ReferentielMetierServiceInterface referentielMetierServiceInterface = referentielMetierService.getReferentielMetier();
		BindingProvider bp = (BindingProvider)referentielMetierServiceInterface;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, wsdlUrl);
		bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "");
		bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "");
		return referentielMetierServiceInterface;
	}
	
	public static OffreFormationMetierServiceInterface initOffreFormationMetierService() {
		String wsdlUrl = getPropValue("offreFormationMetier.urlService")+"?wsdl";
		OffreFormationMetierServiceInterfaceService offreFormationMetierService;
		URL wsdlLocation;
		try {
		  wsdlLocation = new URL(wsdlUrl);		
		  offreFormationMetierService = new OffreFormationMetierServiceInterfaceService(wsdlLocation);
		} catch (MalformedURLException e) {
			offreFormationMetierService = new OffreFormationMetierServiceInterfaceService();
		}		
		OffreFormationMetierServiceInterface offreFormationMetierServiceInterface = offreFormationMetierService.getOffreFormationMetier();
		BindingProvider bp = (BindingProvider)offreFormationMetierServiceInterface;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, wsdlUrl);
		bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, getPropValue("offreFormationMetier.username"));
		bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, getPropValue("offreFormationMetier.password"));
		return offreFormationMetierServiceInterface;
	}
	
	public static PedagogiqueMetierServiceInterface initPedagogiqueMetierService(String pedagogiqueMetierUrlService) {
		String wsdlUrl = pedagogiqueMetierUrlService+"?wsdl";
		PedagogiqueMetierServiceInterfaceService pedagogiqueMetierService;
		URL wsdlLocation;
		try {
		  wsdlLocation = new URL(wsdlUrl);		
		  pedagogiqueMetierService = new PedagogiqueMetierServiceInterfaceService(wsdlLocation);
		} catch (MalformedURLException e) {
			pedagogiqueMetierService = new PedagogiqueMetierServiceInterfaceService();
		}		
		PedagogiqueMetierServiceInterface pedagogiqueMetierServiceInterface = pedagogiqueMetierService.getPedagogiqueMetier();
		BindingProvider bp = (BindingProvider)pedagogiqueMetierServiceInterface;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, wsdlUrl);
		bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, getPropValue("pedagogiqueMetier.username"));
		bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, getPropValue("pedagogiqueMetier.password"));
		return pedagogiqueMetierServiceInterface;
	}
	
	public static EtudiantMetierServiceInterface initEtudiantMetierService() {
		String wsdlUrl = getPropValue("etudiantMetier.urlService")+"?wsdl";
		EtudiantMetierServiceInterfaceService etudiantMetierService;
		URL wsdlLocation;
		try {
		  wsdlLocation = new URL(wsdlUrl);		
		  etudiantMetierService = new EtudiantMetierServiceInterfaceService(wsdlLocation);
		} catch (MalformedURLException e) {
		  etudiantMetierService = new EtudiantMetierServiceInterfaceService();
		}
		EtudiantMetierServiceInterface etudiantMetierServiceInterface = etudiantMetierService.getEtudiantMetier();
		BindingProvider bp = (BindingProvider)etudiantMetierServiceInterface;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, wsdlUrl);
		bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, getPropValue("etudiantMetier.username"));
		bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, getPropValue("etudiantMetier.password"));
		return etudiantMetierServiceInterface;
		
		
	}
	
	public static AdministratifMetierServiceInterface initAdministratifMetierService() {
		String wsdlUrl = getPropValue("administratifMetier.urlService")+"?wsdl";
		AdministratifMetierServiceInterfaceService administratifMetierService;
		URL wsdlLocation;
		try {
		  wsdlLocation = new URL(wsdlUrl);		
		  administratifMetierService = new AdministratifMetierServiceInterfaceService(wsdlLocation);
		} catch (MalformedURLException e) {
			administratifMetierService = new AdministratifMetierServiceInterfaceService();
		}		
		AdministratifMetierServiceInterface administratifMetierServiceInterface = administratifMetierService.getAdministratifMetier();
		BindingProvider bp = (BindingProvider)administratifMetierServiceInterface;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, wsdlUrl);
		bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, getPropValue("administratifMetier.username"));
		bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, getPropValue("administratifMetier.password"));
		return administratifMetierServiceInterface;
	}
	

}
