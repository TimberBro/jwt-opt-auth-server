package com.epopov.authserver.controller;

import com.epopov.authserver.model.Otp;
import com.epopov.authserver.model.User;
import com.epopov.authserver.service.UserService;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  @Autowired
  private UserService userService;

  @PostMapping("/user/add")
  public void addUser(@RequestBody User user) {
    userService.addUser(user);
  }

  @PostMapping("/user/auth")
  public void auth(@RequestBody User user) {
    userService.auth(user);
  }

  @PostMapping("/otp/check")
  public void check(@RequestBody Otp otp, HttpServletResponse response) {
    if (userService.check(otp)) {
      response.setStatus(HttpServletResponse.SC_OK);
    } else {
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }
  }
}