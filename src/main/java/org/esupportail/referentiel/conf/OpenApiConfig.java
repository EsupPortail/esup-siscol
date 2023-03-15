package org.esupportail.referentiel.conf;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.security.SecurityRequirement;

@Configuration
@OpenAPIDefinition(servers = {
		@Server(url = "${app.server-url:/}") }, info = @Info(title = "${app.server-title}", version = "${app.server-version}"))
public class OpenApiConfig {

	@Bean
	public GroupedOpenApi usersGroup() {
		return GroupedOpenApi.builder().group("apogee").addOperationCustomizer((operation, handlerMethod) -> {
			operation.addSecurityItem(new SecurityRequirement().addList("basicScheme"));
			return operation;
		}).packagesToScan("org.esupportail.referentiel.rest.apogee").build();
	}

	@Bean
	public GroupedOpenApi ldapGroup() {
		return GroupedOpenApi.builder().group("ldap").addOperationCustomizer((operation, handlerMethod) -> {
			operation.addSecurityItem(new SecurityRequirement().addList("basicScheme"));
			return operation;
		}).packagesToScan("org.esupportail.referentiel.rest.ldap").build();
	}

	@Bean
	public GroupedOpenApi psScolGroup() {
		return GroupedOpenApi.builder().group("pcscol").addOperationCustomizer((operation, handlerMethod) -> {
			operation.addSecurityItem(new SecurityRequirement().addList("basicScheme"));
			return operation;
		}).packagesToScan("org.esupportail.referentiel.rest.pcscol").build();
	}

	@Bean
	public GroupedOpenApi all() {
		return GroupedOpenApi.builder().group("all").addOperationCustomizer((operation, handlerMethod) -> {
			operation.addSecurityItem(new SecurityRequirement().addList("basicScheme"));
			return operation;
		}).packagesToScan("org.esupportail.referentiel.rest.ldap", "org.esupportail.referentiel.rest.apogee",
				"org.esupportail.referentiel.rest.pcscol").build();
	}
}
