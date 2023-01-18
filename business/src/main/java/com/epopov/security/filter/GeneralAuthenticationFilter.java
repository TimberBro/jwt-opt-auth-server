package com.epopov.security.filter;

import com.epopov.security.authentication.JwtAuthentication;
import com.epopov.security.authentication.OTPAuthentication;
import com.epopov.security.authentication.UsernamePassAuthentication;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class GeneralAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  @Lazy
  private AuthenticationManager manager;

  @Value("${jwt.signing.key}")
  private String signingKey;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String username = request.getHeader("username");
    String password = request.getHeader("password");
    String code = request.getHeader("code");
    String bearer = request.getHeader("Authorization");

    if (code == null && bearer == null) {
      Authentication a = new UsernamePassAuthentication(username, password);
      manager.authenticate(a);
    } else if (bearer == null){
      Authentication a = new OTPAuthentication(username, code);
      manager.authenticate(a);

      SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
      String jwt = Jwts.builder()
          .setClaims(Map.of("username", username))
          .signWith(key)
          .compact();
      response.setHeader("Authorization", jwt);
    } else {
      Authentication a = new JwtAuthentication(username, bearer);
      manager.authenticate(a);
    }
  }
}
