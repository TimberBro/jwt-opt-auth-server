package com.epopov.security.authentication;

import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class UsernamePassAuthentication extends UsernamePasswordAuthenticationToken {

  public UsernamePassAuthentication(Object principal, Object credentials) {
    super(principal, credentials);
  }

  public UsernamePassAuthentication(Object principal, Object credentials,
      Collection<? extends GrantedAuthority> authorities) {
    super(principal, credentials, authorities);
  }
}
