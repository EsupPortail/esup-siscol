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
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
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

		return http.authorizeHttpRequests(request -> request.anyRequest()
                .authenticated())
            .httpBasic(Customizer.withDefaults())
            .build();
		
		
//		http
//				// HTTP Basic authentication
//				.httpBasic().and().authorizeRequests()
//				/* GET */
//				.antMatchers(HttpMethod.GET, "/cache/**", "/supann/**", "/apogee/**").hasAnyAuthority("USER", "ADMIN")
//				/* POST */
//				.antMatchers(HttpMethod.POST, "/ldap/**").hasAnyAuthority("USER", "ADMIN")
//				/* misc */
//				.and().csrf().disable().formLogin().disable();
//
//		http.authorizeRequests().antMatchers("/registration", "/js/**", "/css/**", "/img/**", "/webjars/**").permitAll()
//				.anyRequest().hasAnyAuthority("ADMIN", "USER")
//
//				.and().formLogin().loginPage("/login").permitAll().and().logout().invalidateHttpSession(true)
//				.clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//				.logoutSuccessUrl("/login?logout").permitAll();
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
