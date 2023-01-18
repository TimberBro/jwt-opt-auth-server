package com.epopov.security.provider;

import com.epopov.security.authentication.JwtAuthentication;
import com.epopov.security.authentication.UsernamePassAuthentication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

  @Value("${jwt.signing.key}")
  private String signingKey;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String jwtToken = String.valueOf(authentication.getCredentials());

    SecretKey key = Keys.hmacShaKeyFor(
        signingKey.getBytes(StandardCharsets.UTF_8));

    Claims claims = Jwts.parserBuilder().setSigningKey(key)
        .build()
        .parseClaimsJws(jwtToken)
        .getBody();

    String username = String.valueOf(claims.get("username"));
    GrantedAuthority a = new SimpleGrantedAuthority("user");

    //SecurityContextHolder.getContext().setAuthentication(auth);
    return new UsernamePassAuthentication(
        username,
        null,
        List.of(a));
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return JwtAuthentication.class.isAssignableFrom(authentication);
  }
}
