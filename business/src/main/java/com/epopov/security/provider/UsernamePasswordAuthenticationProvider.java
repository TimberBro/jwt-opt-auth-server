package com.epopov.security.provider;

import com.epopov.security.authentication.AuthServerProxy;
import com.epopov.security.authentication.UsernamePassAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

  @Autowired
  private AuthServerProxy proxy;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String password = String.valueOf(authentication.getCredentials());

    proxy.sendAuth(username, password);
    return new UsernamePasswordAuthenticationToken(username, password);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePassAuthentication.class.isAssignableFrom(authentication);
  }
}