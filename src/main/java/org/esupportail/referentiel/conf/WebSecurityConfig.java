package org.esupportail.referentiel.conf;


import org.esupportail.referentiel.security.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { // NO_UCD (unused code)

	@Autowired
	private CustomAuthenticationProvider authProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				// HTTP Basic authentication
				.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.GET, "/supann/**")
				.hasAnyAuthority("USER", "ADMIN").antMatchers(HttpMethod.POST, "/ldap/**")
				.hasAnyAuthority("USER", "ADMIN").antMatchers(HttpMethod.GET, "/apogee/**").hasAnyAuthority("USER", "ADMIN").and()
				.csrf().disable().formLogin().disable();

				http.authorizeRequests().
				antMatchers("/registration", "/js/**", "/css/**", "/img/**", "/webjars/**").permitAll()
				.anyRequest().hasAnyAuthority("ADMIN","USER")
	
				.and().formLogin().loginPage("/login").permitAll()
				.and().logout().invalidateHttpSession(true).clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
				.permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		auth.inMemoryAuthentication().withUser("user").password(encoder.encode("password")).roles("USER").and()
//				.withUser("admin").password(encoder.encode("admin")).roles("USER", "ADMIN");
		auth.authenticationProvider(authProvider);
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

	public CustomAuthenticationProvider getAuthProvider() {
		return authProvider;
	}

	public void setAuthProvider(CustomAuthenticationProvider authProvider) {
		this.authProvider = authProvider;
	}
}