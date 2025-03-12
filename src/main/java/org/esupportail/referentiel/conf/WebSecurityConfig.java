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

@Configuration
@EnableWebSecurity
public class WebSecurityConfig { // NO_UCD (unused code)

	@Autowired
	private CustomAuthenticationProvider authProvider;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http .csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests((requests) -> requests
				.requestMatchers(HttpMethod.GET, "/apogee/**").hasAnyAuthority("ADMIN","USER_APOGEE")
				.requestMatchers(HttpMethod.POST, "/apogee/**").hasAnyAuthority("ADMIN","USER_APOGEE")
				.requestMatchers(HttpMethod.GET, "/pcscol/**").hasAnyAuthority("ADMIN","USER_PCSCOL")
				.requestMatchers(HttpMethod.POST, "/pcscol/**").hasAnyAuthority("ADMIN","USER_PCSCOL")
				.requestMatchers(HttpMethod.GET, "/ldap/**").hasAnyAuthority("ADMIN","USER_LDAP")
				.requestMatchers(HttpMethod.POST, "/ldap/**").hasAnyAuthority("ADMIN","USER_LDAP")
				.requestMatchers(HttpMethod.GET, "/cache/**").hasAnyAuthority("ADMIN")
				.requestMatchers(HttpMethod.POST, "/cache/**").hasAnyAuthority("ADMIN")
				
				.anyRequest().authenticated()).formLogin((form) -> form.loginPage("/login").permitAll()).
					logout((logout) -> logout.permitAll().invalidateHttpSession(true).clearAuthentication(true))
					 .httpBasic(Customizer.withDefaults());
				            
		//.httpBasic(Customizer.withDefaults());
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
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("DELETE");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
