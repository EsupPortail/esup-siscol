package org.esupportail.referentiel;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "org.esupportail.referentiel" })
public class SpringBootTomcatApplication extends SpringBootServletInitializer {

}
