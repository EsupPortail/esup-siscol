package org.esupportail.referentiel.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	CredentialComponent credentialComponent;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
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
				System.out.println(roles);
				
				UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(name, password,roles);
				//user.setAuthenticated(true);

				
				return user;
				

			}
		}

		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
