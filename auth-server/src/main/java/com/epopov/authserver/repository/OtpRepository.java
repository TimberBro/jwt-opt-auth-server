package com.epopov.authserver.repository;

import com.epopov.authserver.model.Otp;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<Otp, String> {

  Optional<Otp> findOtpByUsername(String username);
}
