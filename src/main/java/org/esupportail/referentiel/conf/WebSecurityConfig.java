package org.esupportail.referentiel.conf;

import org.esupportail.referentiel.security.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig { // NO_UCD (unused code)

	@Autowired
	private CustomAuthenticationProvider authProvider;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http .csrf(AbstractHttpConfigurer::disable)
			// Support pour proxy HTTP - accepte les headers X-Forwarded-*
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers(HttpMethod.GET, "/apogee/**").hasAnyAuthority("ADMIN","USER_APOGEE")
				.requestMatchers(HttpMethod.POST, "/apogee/**").hasAnyAuthority("ADMIN","USER_APOGEE")
				.requestMatchers(HttpMethod.GET, "/pcscol/**").hasAnyAuthority("ADMIN","USER_PCSCOL")
				.requestMatchers(HttpMethod.POST, "/pcscol/**").hasAnyAuthority("ADMIN","USER_PCSCOL")
				.requestMatchers(HttpMethod.GET, "/ldap/**").hasAnyAuthority("ADMIN","USER_LDAP")
				.requestMatchers(HttpMethod.POST, "/ldap/**").hasAnyAuthority("ADMIN","USER_LDAP")
				.requestMatchers(HttpMethod.GET, "/cache/**").hasAnyAuthority("ADMIN")
				.requestMatchers(HttpMethod.POST, "/cache/**").hasAnyAuthority("ADMIN")
				
				.anyRequest().authenticated())
			.formLogin((form) -> form.loginPage("/login").permitAll())
			.logout((logout) -> logout.permitAll().invalidateHttpSession(true).clearAuthentication(true))
			.httpBasic(Customizer.withDefaults());
				            
		return http.build();
	}

	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.authenticationProvider(authProvider);
		return authenticationManagerBuilder.build();
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		
		// Configuration pour proxy HTTP : accepte tous les domaines
		// Quand on est derrière un proxy HTTP, l'origine peut être n'importe quel domaine
		config.setAllowCredentials(true);
		config.addAllowedOriginPattern(".*");  // Accepte toutes les origines avec pattern regex
		
		// Headers autorisés
		config.addAllowedHeader("*");
		config.addAllowedHeader("Authorization");
		config.addAllowedHeader("Content-Type");
		config.addAllowedHeader("X-Requested-With");
		config.addAllowedHeader("X-Forwarded-For");
		config.addAllowedHeader("X-Forwarded-Proto");
		config.addAllowedHeader("X-Forwarded-Host");
		
		// Méthodes HTTP autorisées
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("DELETE");
		
		// Expose certains headers de réponse
		config.addExposedHeader("Content-Type");
		config.addExposedHeader("X-Total-Count");
		
		// Max age pour preflight requests (en secondes)
		config.setMaxAge(3600L);
		
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
