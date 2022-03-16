package org.esupportail.referentiel.ldap;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;

@Configuration
@EnableLdapRepositories( basePackages = "org.esupportail.pstage.ldap.**")
public class LdapConf {

}
