package com.epopov.security.model;

public class User {

  private String username;
  private String password;
  private String OTPcode;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getOTPcode() {
    return OTPcode;
  }

  public void setOTPcode(String OTPcode) {
    this.OTPcode = OTPcode;
  }
}
