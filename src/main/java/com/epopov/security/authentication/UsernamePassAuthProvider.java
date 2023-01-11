package com.epopov.security.authentication;

import java.util.Collection;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

public class UsernamePassAuthProvider extends UsernamePasswordAuthenticationToken {

  public UsernamePassAuthProvider(Object principal, Object credentials) {
    super(principal, credentials);
  }

  public UsernamePassAuthProvider(Object principal, Object credentials,
      Collection<? extends GrantedAuthority> authorities) {
    super(principal, credentials, authorities);
  }
}
