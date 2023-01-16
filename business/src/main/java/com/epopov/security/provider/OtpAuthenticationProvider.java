package com.epopov.security.provider;

import com.epopov.security.authentication.AuthServerProxy;
import com.epopov.security.authentication.OTPAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class OtpAuthenticationProvider implements AuthenticationProvider {

  @Autowired
  private AuthServerProxy proxy;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String code = String.valueOf(authentication.getCredentials());

    boolean authResult = proxy.sendOTP(username, code);
    if (authResult) {
      return new OTPAuthentication(username, code);
    } else {
      throw new BadCredentialsException("Bad credentials.");
    }
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return OTPAuthentication.class.isAssignableFrom(authentication);
  }
}
