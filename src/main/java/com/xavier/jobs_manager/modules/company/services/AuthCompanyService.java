package com.xavier.jobs_manager.modules.company.services;

import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.xavier.jobs_manager.exeptions.IncorrectPasswordException;
import com.xavier.jobs_manager.modules.company.dto.AuthCompanyDTO;
import com.xavier.jobs_manager.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyService {

  @Value("${security.token.secret}")
  private String secretKey;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public String execute(AuthCompanyDTO authCompanyDTO) {
    var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
        () -> {
          throw new UsernameNotFoundException("Username/password incorrect");
        });

    var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

    if (!passwordMatches) {
      throw new IncorrectPasswordException("Username/password incorrect");
    }

    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    var token = JWT.create().withIssuer("jobs_manager").withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
        .withSubject(company.getId().toString()).sign(algorithm);

    return token;
  }
}