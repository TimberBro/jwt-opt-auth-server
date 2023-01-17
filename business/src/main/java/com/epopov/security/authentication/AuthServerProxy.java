package com.epopov.security.authentication;

import com.epopov.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthServerProxy {

  @Autowired
  private RestTemplate restTemplate;

  @Value("${auth.server.base.url}")
  private String baseUrl;

  public void sendAuth(String username, String password) {

    String authUrl = baseUrl + "/user/auth";

    User body = new User();
    body.setUsername(username);
    body.setPassword(password);
    HttpEntity<User> request = new HttpEntity<>(body);
    restTemplate.postForEntity(authUrl, request, Void.class);
  }

  public boolean sendOTP(String username, String code) {
    String authUrl = baseUrl + "/otp/check";

    User body = new User();
    body.setUsername(username);
    body.setCode(code);

    HttpEntity<User> request = new HttpEntity<>(body);

    ResponseEntity<Void> response = restTemplate.postForEntity(authUrl, request, Void.class);

    return response.getStatusCode().equals(HttpStatus.OK);
  }
}
