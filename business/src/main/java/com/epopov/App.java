package com.epopov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

  // TODO: 16.01.2023 Resolve dependency cycle between InitialAuthenticationFilter and SecurityConfig
  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
}
