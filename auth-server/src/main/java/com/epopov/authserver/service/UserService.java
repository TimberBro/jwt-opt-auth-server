package com.epopov.authserver.service;

import com.epopov.authserver.model.Otp;
import com.epopov.authserver.model.User;
import com.epopov.authserver.repository.OtpRepository;
import com.epopov.authserver.repository.UserRepository;
import com.epopov.authserver.utils.GenerateCodeUtil;
import java.util.Optional;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {


  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final OtpRepository otpRepository;


  public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository,
      OtpRepository otpRepository) {
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
    this.otpRepository = otpRepository;
  }

  public void addUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
  }

  public void auth(User user) {
    Optional<User> o =
        userRepository.findUserByUsername(user.getUsername());
    if (o.isPresent()) {
      User u = o.get();
      if (passwordEncoder.matches(
          user.getPassword(),
          u.getPassword())) {
        renewOtp(u);
      } else {
        throw new BadCredentialsException
            ("Bad credentials.");
      }
    } else {
      throw new BadCredentialsException
          ("Bad credentials.");
    }
  }

  private void renewOtp(User u) {
    String code = GenerateCodeUtil.generateCode();
    Optional<Otp> userOtp =
        otpRepository.findOtpByUsername(u.getUsername());
    if (userOtp.isPresent()) {
      Otp otp = userOtp.get();
      otp.setCode(code);
    } else {
      Otp otp = new Otp();
      otp.setUsername(u.getUsername());
      otp.setCode(code);
      otpRepository.save(otp);
    }
  }

  public boolean check(Otp otpToValidate) {
    Optional<Otp> userOtp =
        otpRepository.findOtpByUsername(
            otpToValidate.getUsername());
    if (userOtp.isPresent()) {
      Otp otp = userOtp.get();
      if (otpToValidate.getCode().equals(otp.getCode())) {
        return true;
      }
    }
    return false;
  }
}
