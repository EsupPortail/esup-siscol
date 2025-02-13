package org.esupportail.referentiel;

import java.util.Locale;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
@EnableWebMvc
@ComponentScan({ "org.esupportail.referentiel" })
public class SpringBootTomcatApplication extends SpringBootServletInitializer {

	
	 public static void main(String[] args) {
	        new SpringBootTomcatApplication().configure(new SpringApplicationBuilder(SpringBootTomcatApplication.class)).run(args);
	    }
	 public LocaleResolver localeResolver() {
			SessionLocaleResolver slr = new SessionLocaleResolver();
			slr.setDefaultLocale(Locale.FRANCE);
			return slr;
		}
}
