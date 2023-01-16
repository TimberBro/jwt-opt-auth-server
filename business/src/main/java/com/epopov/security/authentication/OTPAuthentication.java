package com.epopov.security.authentication;

import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class OTPAuthentication extends UsernamePasswordAuthenticationToken {

  public OTPAuthentication(Object principal, Object credentials) {
    super(principal, credentials);
  }

  public OTPAuthentication(Object principal, Object credentials,
      Collection<? extends GrantedAuthority> authorities) {
    super(principal, credentials, authorities);
  }
}
