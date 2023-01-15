package com.epopov.security.authentication;

import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class OTPAuthProvider extends UsernamePasswordAuthenticationToken {

  public OTPAuthProvider(Object principal, Object credentials) {
    super(principal, credentials);
  }

  public OTPAuthProvider(Object principal, Object credentials,
      Collection<? extends GrantedAuthority> authorities) {
    super(principal, credentials, authorities);
  }
}
