package org.esupportail.referentiel;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

//@SpringBootApplication
//@EnableWebMvc
//@ComponentScan({ "org.esupportail.referentiel" })
@Deprecated
public class Siscol {

//	public static void main(String[] args) {
//		SpringApplication.run(Siscol.class, args);
//
//	}

	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.FRANCE);
		return slr;
	}
}
