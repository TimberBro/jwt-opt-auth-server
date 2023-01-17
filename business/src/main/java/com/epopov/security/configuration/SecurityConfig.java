package com.epopov.security.configuration;

import com.epopov.security.filter.InitialAuthenticationFilter;
import com.epopov.security.filter.JwtAuthenticationFilter;
import com.epopov.security.provider.OtpAuthenticationProvider;
import com.epopov.security.provider.UsernamePasswordAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private InitialAuthenticationFilter initialAuthenticationFilter;
  @Autowired
  private OtpAuthenticationProvider otpAuthenticationProvider;

  @Autowired
  private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

  @Autowired
  private JwtAuthenticationFilter jwtAuthenticationFilter;

  @Bean
  protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .addFilterAt(
            initialAuthenticationFilter,
            BasicAuthenticationFilter.class)
        .addFilterAfter(
            jwtAuthenticationFilter,
            BasicAuthenticationFilter.class)
        .authorizeRequests()
        .anyRequest().authenticated();

    return http.build();
  }

  @Bean
  protected AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
        .authenticationProvider(otpAuthenticationProvider)
        .authenticationProvider(usernamePasswordAuthenticationProvider)
        .build();
  }
}
