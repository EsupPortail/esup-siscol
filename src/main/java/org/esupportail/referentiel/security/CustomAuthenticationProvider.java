package org.esupportail.referentiel.security;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider, InitializingBean {
	
	private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

	@Autowired
	CredentialComponent credentialComponent;

	
	public CustomAuthenticationProvider(CredentialComponent credentialComponent) {
		this.credentialComponent=credentialComponent;
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String name = authentication.getName();
		
		String password = authentication.getCredentials().toString();
		
		
		System.out.println(credentialComponent.getUserscredential().get(name));
		if (credentialComponent.getUserscredential().get(name) != null) {
			String userPassword = credentialComponent.getUserscredential().get(name).getPassword();
			
			if (userPassword.equals(password)) {
				Collection<GrantedAuthority> roles = new ArrayList<>();
				if (credentialComponent.getUserscredential().get(name).getRoles() != null) {
					for (String r : credentialComponent.getUserscredential().get(name).getRoles()) {
						GrantedAuthority g = new SimpleGrantedAuthority(r);
						roles.add(g);
					}
				}
				log.debug("" +  name +" : "+ roles);
				
				UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(name, password,roles);
				//user.setAuthenticated(true);

				log.debug("" + user);
				return user;
				

			}
		}
		
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.debug("INIT SECURITY LOADED " );
		Assert.notNull(credentialComponent, "credentialComponent cannot bu null !");
		
	}

}
